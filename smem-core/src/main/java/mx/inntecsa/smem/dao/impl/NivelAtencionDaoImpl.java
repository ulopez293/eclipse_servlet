package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.NivelAtencionDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.NivelAtencion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("nivelAtencionDao")
@SuppressWarnings("unchecked")
public class NivelAtencionDaoImpl extends GenericDaoImpl<NivelAtencion, BigDecimal> implements NivelAtencionDao {

	@Override
	public List<NivelAtencion> getNivelesAtencion() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(NivelAtencion.class);		
		return criteria.list();
	}

	@Override
	public List<NivelAtencion> getNivelesAtencionByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(NivelAtencion.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}
	
	@Override
	public List<NivelAtencion> getNivelesAtencionPorDescripcion(String descripcion) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(NivelAtencion.class,"na")
			.add(Restrictions.eq("na.descripcion", descripcion));	
		return criteria.list();
	}
	
	@Override
	public List<NivelAtencion> getNivelesAtencionPorDescripcion(String descripcion, Integer idNivelAtencion) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(NivelAtencion.class,"na")
			.add(Restrictions.eq("na.descripcion", descripcion))
			.add(Restrictions.not(Restrictions.eq("na.idNivelAtencion", idNivelAtencion)));
		return criteria.list();
	}
	
	@Override
	public NivelAtencion getNivelAtencionPorId(Integer idNivelAtencion) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(NivelAtencion.class, "na")				
			.add(Restrictions.eq("na.idNivelAtencion", idNivelAtencion));					
		return (NivelAtencion) criteria.uniqueResult();
	}

}
