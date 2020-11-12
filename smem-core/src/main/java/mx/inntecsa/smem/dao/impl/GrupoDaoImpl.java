package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.GrupoDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Grupo;
import mx.inntecsa.smem.pojo.GrupoClave;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;


@Repository("grupoDao")
@SuppressWarnings("unchecked")
public class GrupoDaoImpl extends GenericDaoImpl<Grupo, BigDecimal> implements GrupoDao {

	@Override
	public List<Grupo> getGrupos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class);		
		return criteria.list();
	}

	@Override
	public List<Grupo> getGruposByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<Grupo> getGruposConGrupoClavePorIdEquipo(Integer idEquipo) {
		DetachedCriteria subquery = DetachedCriteria.forClass(GrupoClave.class, "gc");
		subquery.createAlias("gc.grupo", "g");	
		subquery.createAlias("gc.equipo", "e");
		subquery.add(Restrictions.eq("gc.baja", Estatus.ACTIVO));
		subquery.add(Restrictions.eq("e.idEquipo", idEquipo));
		subquery.setProjection(Property.forName("g.idGrupo"));
			    
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class, "g");
		criteria.add(Restrictions.eq("g.baja", Estatus.ACTIVO));		
		criteria.add(Subqueries.propertyIn("g.idGrupo", subquery)); 
			
		return criteria.list();
	}
	
	@Override
	public List<Grupo> getGruposActivos(){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
		Criteria criteria = sesion.createCriteria(Grupo.class)
			.add(Restrictions.eq("baja", Estatus.ACTIVO))
			.addOrder( Order.asc("grupo"));
					
		return criteria.list();
	}
	
	@Override
	public List<Grupo> getGruposPorNombre(String nombre) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class,"g")
			.add(Restrictions.eq("g.grupo", nombre));	
		return criteria.list();
	}
	
	@Override
	public List<Grupo> getGruposPorNombre(String nombre, Integer idGrupo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class,"g")
			.add(Restrictions.eq("g.grupo", nombre))
			.add(Restrictions.not(Restrictions.eq("g.idGrupo", idGrupo)));
		return criteria.list();
	}
	
	@Override
	public Grupo getGrupoPorId(Integer idGrupo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Grupo.class, "g")				
			.add(Restrictions.eq("g.idGrupo", idGrupo));					
		return (Grupo) criteria.uniqueResult();
	}

}
