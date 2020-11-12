package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.EntidadDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Entidad;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("entidadDao")
@SuppressWarnings("unchecked")
public class EntidadDaoImpl extends GenericDaoImpl<Entidad, BigDecimal> implements EntidadDao {

	@Override
	public List<Entidad> getEntidades() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Entidad.class);		
		return criteria.list();
	}

	@Override
	public List<Entidad> getEntidadesPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Entidad.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

}
