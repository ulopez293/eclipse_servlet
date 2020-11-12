package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ContratoDetalleDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.EstatusRequiereServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.pojo.SolicitudServicio;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;


@Repository("contratoDetalleDao")
@SuppressWarnings("unchecked")
public class ContratoDetalleDaoImpl extends GenericDaoImpl<ContratoDetalle, BigDecimal> implements ContratoDetalleDao {

	private Logger log = Logger.getLogger(ContratoDetalleDaoImpl.class);
	
	@Override
	public List<ContratoDetalle> getContratosDetalles() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class);		
		return criteria.list();
	}

	@Override
	public List<ContratoDetalle> getContratosDetallesPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	//Regresa los contratos detalle con una fecha inicio ingresada 
	//y una fecha fin no mayor a la fecha actual (para generar servicios preventivos)
	@Override
	public List<ContratoDetalle> getContratosDetallesPorFechaInicio(Date fechaInicio){
		
		log.info("Buscando contratos detalle con fecha de inicio menor a: " + fechaInicio);
		
		DetachedCriteria subquery = DetachedCriteria.forClass(SolicitudServicio.class, "ss");
		subquery.createAlias("ss.programacionServicios", "ps");
		subquery.createAlias("ss.contratoDetalle", "cd");
		subquery.add(Restrictions.eq("ps.tipoServicio", TipoServicio.SERVICIO_PREVENTIVO));
		subquery.setProjection(Property.forName("cd.idContratoDetalle"));
			    
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd");
		criteria.createAlias("cd.contrato", "cc");
		criteria.createAlias("cd.universo", "u");
		
		criteria.add(Restrictions.eq("cd.baja", Estatus.ACTIVO));
		criteria.add(Restrictions.eq("cc.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.eq("u.requiereServicio",EstatusRequiereServicio.REQUIERE_SERVICIO));
		criteria.add(Restrictions.eq("u.baja",Estatus.ACTIVO));
		criteria.add(Restrictions.le("cd.inicioPeriodo", fechaInicio));
		criteria.add(Restrictions.ge("cd.finPeriodo", new Date()));
		criteria.add(Subqueries.propertyNotIn("cd.idContratoDetalle", subquery)); 
		
		return criteria.list();
	
	}

	@Override
	public List<ContratoDetalle> getContratosDetallesPorIdContrato(Integer idContrato) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd")
			.createAlias("cd.contrato", "c")	
			.add(Restrictions.eq("c.idContrato", idContrato))
			.add(Restrictions.eq("cd.baja", Estatus.ACTIVO));
		return criteria.list();
	}

	@Override
	public Integer getMaxPeriodoPorIdUniverso(Long idUniverso) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd")
			.createAlias("cd.universo", "u")
			.setProjection(Projections.max("cd.periodo"))
			.add(Restrictions.eq("u.idUniverso", idUniverso));
		
		if (criteria.uniqueResult() != null) {
			return (Integer) criteria.uniqueResult();
		}
		
		return 0;
	}
	
	@Override
	public ContratoDetalle getContratoDetallePorIdUniverso(Long idUniverso) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd")
			.createAlias("cd.contrato", "cc")
			.createAlias("cd.universo", "u")
			.createAlias("cc.proveedor", "p");

		criteria.add(Restrictions.eq("cd.baja", Estatus.ACTIVO));
		criteria.add(Restrictions.eq("cc.baja", Estatus.ACTIVO));
		criteria.add(Restrictions.eq("cc.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.eq("u.idUniverso",idUniverso));
		criteria.addOrder( Order.asc("cd.periodo"));
		criteria.setMaxResults(1);
		
		return (ContratoDetalle) criteria.uniqueResult();
	}

	@Override
	public Integer getConsecutivoVigentePorIdUniverso(Long idUniverso) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd")
			.createAlias("cd.universo", "u")			
			.add(Restrictions.eq("u.idUniverso", idUniverso))
			.add(Restrictions.eq("cd.baja", Estatus.ACTIVO));
		
		if (criteria.list().size() != 0) {
			ContratoDetalle contratoDetalle = (ContratoDetalle) criteria.list().get(0);
			return contratoDetalle.getConsecutivoContrato();
		}
		
		return 0;
	}

	@Override
	public ContratoDetalle getContratoDetallePorParametros(Integer idContrato,
		Integer periodo, Long idUniverso) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();		
		Criteria criteria = sesion.createCriteria(ContratoDetalle.class, "cd")
			.createAlias("cd.contrato", "c")
			.createAlias("cd.universo", "u")
			.add(Restrictions.eq("c.idContrato", idContrato))
			.add(Restrictions.eq("u.idUniverso",idUniverso))
			.add(Restrictions.eq("cd.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("cd.periodo", periodo));			
			criteria.setMaxResults(1);
			
		return (ContratoDetalle) criteria.uniqueResult();		
	}

}
