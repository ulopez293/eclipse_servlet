package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.SubtipoContratoDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SubtipoContrato;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("subtipoContratoDao")
@SuppressWarnings("unchecked")
public class SubtipoContratoDaoImpl extends GenericDaoImpl<SubtipoContrato, BigDecimal> implements SubtipoContratoDao {

	@Override
	public List<SubtipoContrato> getSubtiposContratos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SubtipoContrato.class);		
		return criteria.list();
	}

	  @Override
	  public SubtipoContrato getSubtiposContratosPorId(Integer idSubtipoContrato){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SubtipoContrato.class)
		           .add(Restrictions.eq("idSubtipoContrato", idSubtipoContrato));
	    
		  return (SubtipoContrato) criteria.uniqueResult();
	  }
	@Override
	public List<SubtipoContrato> getSubtiposContratosPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SubtipoContrato.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<SubtipoContrato> getSubtipoContratosPorNombre(String string) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SubtipoContrato.class)
		    .add(Restrictions.eq("subtipoContrato", string));
		return criteria.list();
	
	}
	
   public List<SubtipoContrato> getSubtipoContratosPorNombre(String string, Integer idSubtipoContrato) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SubtipoContrato.class)
		         .add(Restrictions.eq("subtipoContrato", string))
			     .add(Restrictions.not(Restrictions.eq("idSubtipoContrato", idSubtipoContrato)));
		return criteria.list();
   }

  
}

