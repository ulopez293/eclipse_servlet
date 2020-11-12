package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.SolicitudServicioDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.SolicitudServicio;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("solicitudServicioDao")
@SuppressWarnings("unchecked")
public class SolicitudServicioDaoImpl extends GenericDaoImpl<SolicitudServicio, BigDecimal> implements SolicitudServicioDao {

	@Override
	public List<SolicitudServicio> getSolicitudesServicios() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SolicitudServicio.class);		
		return criteria.list();
	}

	@Override
	public List<SolicitudServicio> getSolicitudesServiciosByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(SolicitudServicio.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

}
