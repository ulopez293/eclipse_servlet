package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.ClaveDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Clave;
import mx.inntecsa.smem.pojo.GrupoClave;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;


@Repository("claveDao")
@SuppressWarnings("unchecked")
public class ClaveDaoImpl extends GenericDaoImpl<Clave, BigDecimal> implements ClaveDao {

	@Override
	public List<Clave> getClaves() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class);		
		return criteria.list();
	}

	@Override
	public List<Clave> getClavesByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}
	
	@Override
	public List<Clave> getClavesConGrupoClavePorIdGrupoYIdEquipo(Integer idEquipo, Integer idGrupo) {
		DetachedCriteria subquery = DetachedCriteria.forClass(GrupoClave.class, "gc");
		subquery.createAlias("gc.grupo", "g");	
		subquery.createAlias("gc.clave", "c");
		subquery.createAlias("gc.equipo", "e");
		subquery.add(Restrictions.eq("gc.baja", Estatus.ACTIVO));
		subquery.add(Restrictions.eq("g.idGrupo", idGrupo));
		subquery.add(Restrictions.eq("e.idEquipo", idEquipo));
		subquery.setProjection(Property.forName("c.idClave"));
			    
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class, "c");
		criteria.add(Restrictions.eq("c.baja", Estatus.ACTIVO));		
		criteria.add(Subqueries.propertyIn("c.idClave", subquery)); 
			
		return criteria.list();
	}
	
	@Override
	public List<Clave> getClavesActivas(){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
		Criteria criteria = sesion.createCriteria(Clave.class)
			.add(Restrictions.eq("baja", Estatus.ACTIVO))
			.addOrder( Order.asc("clave"));
					
		return criteria.list();
	}

	@Override
	public List<Clave> getClavesConGrupoClavePorIdClave(Integer idClave) {

		DetachedCriteria subquery = DetachedCriteria.forClass(GrupoClave.class, "gc");
		subquery.createAlias("gc.grupo", "g");	
		subquery.createAlias("gc.clave", "c");
		subquery.add(Restrictions.eq("gc.baja", Estatus.ACTIVO));
		subquery.add(Restrictions.eq("c.idClave", idClave));
		subquery.setProjection(Property.forName("c.idClave"));
			    
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class, "c");
		criteria.add(Restrictions.eq("c.baja", Estatus.ACTIVO));		
		criteria.add(Subqueries.propertyIn("c.idClave", subquery)); 
			
		return criteria.list();
	}
	
	@Override
	public List<Clave> getClavesPorNombre(String clave) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class,"c")
			.add(Restrictions.eq("c.clave", clave));	
		return criteria.list();
	}
	
	@Override
	public List<Clave> getClavesPorNombre(String clave, Integer idClave) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class,"c")
			.add(Restrictions.eq("c.clave", clave))
			.add(Restrictions.not(Restrictions.eq("c.idClave", idClave)));
		return criteria.list();
	}
	
	@Override
	public Clave getClavePorId(Integer idClave) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Clave.class, "c")				
			.add(Restrictions.eq("c.idClave", idClave));					
		return (Clave) criteria.uniqueResult();
	}
}
