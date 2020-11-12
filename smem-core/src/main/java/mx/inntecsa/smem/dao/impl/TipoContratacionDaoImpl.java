package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.TipoContratacionDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.TipoContratacion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("tipoContratacionDao")
@SuppressWarnings("unchecked")
public class TipoContratacionDaoImpl extends GenericDaoImpl<TipoContratacion, BigDecimal> implements TipoContratacionDao {

	@Override
	public List<TipoContratacion> getTiposContrataciones() {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(TipoContratacion.class);		
		return criteria.list();
		}
	
	@Override
	public TipoContratacion getTiposContratacionPorId(Integer idTipoContratacion) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(TipoContratacion.class)
		           .add(Restrictions.eq("idTipoContratacion", idTipoContratacion));
	    
		  return (TipoContratacion) criteria.uniqueResult();
	  	}

	
	@Override
	public List<TipoContratacion> getTiposContratacionesPorNombre(String string) {
		   System.out.print("1.3111 ***");
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		System.out.print("1.3112 ***");
		Criteria criteria = sesion.createCriteria(TipoContratacion.class)
				.add(Restrictions.eq("tipoContratacion", string));
		System.out.print("1.3113 ***");
		return criteria.list();
		}
	
	@Override
	public List<TipoContratacion> getTiposContratacionesPorNombre(String string, Integer idTipoContratacion) {
		System.out.print("1.4111 ***");
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(TipoContratacion.class)
				.add(Restrictions.eq("tipoContratacion", string))
		        .add(Restrictions.not(Restrictions.eq("idTipoContratacion", idTipoContratacion)));
		return criteria.list();                                           
		}
	
   @Override
	public List<TipoContratacion> getTiposContratacionesPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(TipoContratacion.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}


}
