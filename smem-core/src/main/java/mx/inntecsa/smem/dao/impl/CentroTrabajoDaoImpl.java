package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.CentroTrabajoDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.CentroTrabajo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("centroTrabajoDao")
@SuppressWarnings("unchecked")
public class CentroTrabajoDaoImpl extends GenericDaoImpl<CentroTrabajo, BigDecimal> implements CentroTrabajoDao {

	@Override
	public List<CentroTrabajo> getCentrosTrabajo() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class);		
		return criteria.list();
	}

	@Override
	public List<CentroTrabajo> getCentrosTrabajoPorEstatus(Estatus estatus) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class).add(Restrictions.eq("baja", estatus));
		return criteria.list();
		
	}

	@Override
	public CentroTrabajo getCentroTrabajoPorDescripcion(String descripcion) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class)
			.add(Restrictions.eq("descripcion", descripcion));
		return (CentroTrabajo) criteria.uniqueResult();		
	}

	@Override
	public List<CentroTrabajo> getCentrosTrabajoPoridUnidadRegional(Integer idUnidadRegional) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class, "ct")
			.createAlias("ct.unidadRegional", "u")	
			.add(Restrictions.eq("u.idUnidadRegional", idUnidadRegional))	
			.add(Restrictions.eq("baja", Estatus.ACTIVO));
		return criteria.list();
	}

	@Override
	public CentroTrabajo getCentroTrabajo(Integer idCentroTrabajo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class, "ct")				
			.add(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo));					
		return (CentroTrabajo) criteria.uniqueResult();
	}

	@Override
	public CentroTrabajo getCentroTrabajoPorURCT(String urct) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class, "ct")				
			.add(Restrictions.eq("ct.urct", urct));					
		return (CentroTrabajo) criteria.uniqueResult();
	}
	
	@Override
	public List<CentroTrabajo> getCentrosTrabajoActivosPorURCT(String urct) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class,"ct")
			.add(Restrictions.eq("ct.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("ct.urct", urct));	
		return criteria.list();
	}
	
	public List<CentroTrabajo> getCentrosTrabajoActivosPorURCT(String urct,Integer idCentroTrabajo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(CentroTrabajo.class,"ct")
			.add(Restrictions.eq("ct.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("ct.urct", urct))
			.add(Restrictions.not(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo)));
		return criteria.list();
	}

}
