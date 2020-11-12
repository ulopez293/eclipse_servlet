package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.EquipoDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Equipo;
import mx.inntecsa.smem.pojo.GrupoClave;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;



@SuppressWarnings("unchecked")
@Repository("equipoDao")
public class EquipoDaoImpl extends GenericDaoImpl<Equipo, BigDecimal> implements EquipoDao {

	@Override	
	public List<Equipo> getEquipos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Criteria criteria = sesion.createCriteria(Equipo.class)
				.addOrder( Order.asc("equipo"));
		return criteria.list();
	}

	public List<Equipo> getEquiposActivos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Criteria criteria = sesion.createCriteria(Equipo.class)
		.add(Restrictions.eq("baja", Estatus.ACTIVO))
		.addOrder( Order.asc("equipo"));
		
		return criteria.list();
	}

	@Override
	public List<Equipo> getEquiposConGrupoClave() {
		DetachedCriteria subquery = DetachedCriteria.forClass(GrupoClave.class, "gc");
		subquery.createAlias("gc.equipo", "e");		
		subquery.add(Restrictions.eq("gc.baja", Estatus.ACTIVO));
		subquery.setProjection(Property.forName("e.idEquipo"));
			    
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Equipo.class, "e");
		criteria.add(Restrictions.eq("e.baja", Estatus.ACTIVO));		
		criteria.add(Subqueries.propertyIn("e.idEquipo", subquery)); 
			
		return criteria.list();
	}
	
	
}
