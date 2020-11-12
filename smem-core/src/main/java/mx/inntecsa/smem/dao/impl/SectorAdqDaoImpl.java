package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.SectorAdqDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SectorAdq;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("sectorAdqDao")
@SuppressWarnings("unchecked")
public class SectorAdqDaoImpl extends GenericDaoImpl<SectorAdq, BigDecimal> implements SectorAdqDao {

	@Override
	public List<SectorAdq> getSectoresAdq() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SectorAdq.class);		
		return criteria.list();
	}

	@Override
	public List<SectorAdq> getSectoresAdqPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SectorAdq.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}
	
	@Override
	public List<SectorAdq> getSectorAdquisicionPorNombre(String nombre) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SectorAdq.class,"sa")
			.add(Restrictions.eq("sa.sectorAdq", nombre));	
		return criteria.list();
	}
	
	@Override
	public List<SectorAdq> getSectorAdquisicionPorNombre(String nombre, Integer idSectorAdq) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SectorAdq.class,"sa")
			.add(Restrictions.eq("sa.sectorAdq", nombre))
			.add(Restrictions.not(Restrictions.eq("sa.idSectorAdq", idSectorAdq)));
		return criteria.list();
	}
	
	@Override
	public SectorAdq getSectorAdquisicionPorId(Integer idSectorAdq) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SectorAdq.class, "sa")				
			.add(Restrictions.eq("sa.idSectorAdq", idSectorAdq));					
		return (SectorAdq) criteria.uniqueResult();
	}

}
