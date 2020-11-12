package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.UnidadRegionalDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.UnidadRegional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("unidadRegionalDao")
@SuppressWarnings("unchecked")
public class UnidadRegionalDaoImpl extends GenericDaoImpl<UnidadRegional, BigDecimal> implements UnidadRegionalDao {

	@Override
	public List<UnidadRegional> getUnidadesRegionales() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(UnidadRegional.class);		
		return criteria.list();
	}

	@Override
	public List<UnidadRegional> getUnidadesRegionalesPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(UnidadRegional.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<UnidadRegional> getUnidadesRegionalesPorIdEntidad(Integer idEntidad) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(UnidadRegional.class)
			.add(Restrictions.eq("baja", Estatus.ACTIVO))
			.add(Restrictions.eq("entidad.idEntidad", idEntidad));
		return criteria.list();
	}

}
