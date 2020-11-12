package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ActaEntregaRecepcionDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ActaEntregaRecepcion;
import mx.inntecsa.smem.utils.Fecha;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("actaEntregaRecepcionDao")
@SuppressWarnings("unchecked")
public class ActaEntregaRecepcionDaoImpl extends GenericDaoImpl<ActaEntregaRecepcion, BigDecimal> implements ActaEntregaRecepcionDao {

	@Override
	public List<ActaEntregaRecepcion> getActasEntregasRecepcion() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class);		
		return criteria.list();
	}

	@Override
	public List<ActaEntregaRecepcion> getActasEntregasRecepcionByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public ActaEntregaRecepcion getUltimaActaEntregaPorIdProgramacion(int idProgramacionServicio) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class);
		criteria.createAlias("programacionServicio","ps");

		criteria.add(Restrictions.eq("ps.idProgramacionServicio",idProgramacionServicio));
		criteria.addOrder( Order.desc("fechaRegistro"));
		
		criteria.setMaxResults(1);
		
		return (ActaEntregaRecepcion) criteria.uniqueResult();
	}
	
	@Override
	public ActaEntregaRecepcion getUltimaActaEntregaPorIdUniverso(long idUniverso){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class);
		criteria.createAlias("programacionServicio","ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		criteria.createAlias("c.proveedor", "p");
		criteria.add(Restrictions.eq("u.idUniverso", idUniverso));
		/*Se comentaron estas dos lineas ya que se comento que debe de salir el ultimo mantenimiento
		 *  no importa si fue exitoso o no, o si fue correctivo o preventivo, tal y como se muestra en el reporte
		 *  del SMEM V1*/
		//criteria.add(Restrictions.eq("ps.tipoServicio",TipoServicio.SERVICIO_CORRECTIVO));
		//criteria.add(Restrictions.eq("mantenimientoExitoso",1));
		criteria.addOrder( Order.desc("fechaRegistro"));
		criteria.setMaxResults(1);
		return (ActaEntregaRecepcion) criteria.uniqueResult();
	}
	
	public List<ActaEntregaRecepcion> getActasEntregaRecepcionPorUrctYEstatus(String urct, EstatusServicio []estatus){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class,"aer");
		criteria.createAlias("aer.programacionServicio","ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		criteria.createAlias("u.centroTrabajo", "ct");
		criteria.add(Restrictions.eq("baja", Estatus.ACTIVO));
		criteria.add(Restrictions.in("ps.estatus",estatus));
		criteria.add(Restrictions.eq("ct.urct", urct));
		//criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.addOrder( Order.desc("ss.fechaInicio"));
		return criteria.list();
	}
	
	public List<ActaEntregaRecepcion> getActasEntregaRecepcionPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ActaEntregaRecepcion.class,"aer");
		criteria.createAlias("aer.programacionServicio","ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		criteria.createAlias("u.centroTrabajo", "ct");
		criteria.add(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo));
		//criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		if(estatusServicio != null)
			criteria.add(Restrictions.eq("ps.estatus",estatusServicio));
		if(tipoServicio != null && tipoServicio != TipoServicio.SERVICIO_PREVENTIVO_CORRECTIVO)
			criteria.add(Restrictions.eq("ps.tipoServicio",tipoServicio));
		if(fechaInicio != null && fechaFin != null){			
			fechaFin = Fecha.agregarDiaFecha(fechaFin);
			criteria.add(Restrictions.between("ss.fechaInicio",fechaInicio, fechaFin));
		}
		if(folio != null && !folio.equals("")){
			criteria.add(Restrictions.eq("ps.folio", folio));
		}
		criteria.addOrder( Order.desc("ss.fechaInicio"));
		return criteria.list();	
	}
}
