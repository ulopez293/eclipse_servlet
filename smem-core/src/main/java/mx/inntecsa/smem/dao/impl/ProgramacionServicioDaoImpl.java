package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ProgramacionServicioDao;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ProgramacionServicio;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("programacionServicioDao")
@SuppressWarnings("unchecked")
public class ProgramacionServicioDaoImpl extends GenericDaoImpl<ProgramacionServicio, BigDecimal> implements ProgramacionServicioDao {

	private Logger log = Logger.getLogger(ProgramacionServicioDaoImpl.class);
			
	@Override
	public List<ProgramacionServicio> getProgramacionServiciosPorEstatus(
			String urct, EstatusServicio[] estatus) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		criteria.createAlias("u.centroTrabajo", "ct");
		
		criteria.add(Restrictions.in("ps.estatus",estatus));
		criteria.add(Restrictions.eq("ct.urct", urct));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.addOrder( Order.desc("ss.fechaInicio"));
		
		return criteria.list();	
	}

	@Override
	public List<ProgramacionServicio> getProgramacionServiciosPorParametros(
			int idCentroTrabajo, TipoServicio tipoServicio,
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		criteria.createAlias("u.centroTrabajo", "ct");
		
		criteria.add(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		
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
	
	@Override
	public ProgramacionServicio getUltimaProgramacioPorUniverso(Long idUniverso) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");
		
		criteria.add(Restrictions.eq("u.idUniverso", idUniverso));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.eq("ps.estatus", EstatusServicio.CERRADO_EXITOSO));
		criteria.addOrder( Order.desc("ss.fechaInicio"));
	
		criteria.setMaxResults(1);
		
		return (ProgramacionServicio) criteria.uniqueResult();
	}

	@Override
	public boolean getExisteCorrectivoEnProcesoPorUniverso(Long idUniverso) {

		EstatusServicio []estatus = {EstatusServicio.CERRADO_NO_EXITOSO, EstatusServicio.ENPROCESO, EstatusServicio.ENPROGRAMACION,
			EstatusServicio.INICIADO};
		
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		criteria.createAlias("cd.contrato", "c");

		criteria.add(Restrictions.eq("ps.tipoServicio",TipoServicio.SERVICIO_CORRECTIVO));
		criteria.add(Restrictions.eq("u.idUniverso", idUniverso));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.in("ps.estatus", estatus));
		criteria.setMaxResults(1);
		
		ProgramacionServicio programacionServicio = (ProgramacionServicio) criteria.uniqueResult();
		
		if(programacionServicio != null){
			return true;
		}
		
		return false;
		
	}

	@Override
	public ProgramacionServicio getProgramacionServicioPorIdContratoDetalle(Integer idContratoDetalle) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.add(Restrictions.eq("cd.idContratoDetalle", idContratoDetalle));
		criteria.setMaxResults(1);		
		return (ProgramacionServicio) criteria.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public boolean getExistenProgramacionesAbiertasPorProveedor(int idProveedor) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("select id_programacion_servicio from programacion_servicio ps ");
		strQuery.append("inner join solicitud_servicio ss on ps.id_solicitud_servicio=ss.id_solicitud_servicio ");
		strQuery.append("inner join contrato_detalle cd on ss.id_contrato_detalle = cd.id_contrato_detalle ");
		strQuery.append("inner join contrato c on cd.id_contrato = c.id_contrato ");
		strQuery.append("inner join proveedor p on c.id_proveedor = p.id_proveedor ");
		strQuery.append("where p.id_proveedor = :idProveedor and ps.estatus in(1,2,3,5)  ");
		
		log.info("query servicios abiertos por proveedor: " + strQuery.toString());
		
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		sqlQuery.setParameter("idProveedor", idProveedor);
		
		List result = sqlQuery.list();
		if(result != null && result.size() > 0){
			return true;
		}

		return false;
	}

	@Override
	public boolean getExistenServiciosPorIdCentroTrabajo(int idCentroTrabajo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query criteria = sesion.createSQLQuery("SELECT FIRST 1 * " +
			"FROM solicitud_servicio ss, programacion_servicio ps, " +
			"contrato_detalle cd, universo u, cat_centro_trabajo ct " +
			"WHERE ss.id_solicitud_servicio = ps.id_solicitud_servicio " +
			"AND ss.id_contrato_detalle = cd.id_contrato_detalle " +
			"AND cd.id_universo = u.id_universo " +
			"AND u.id_centro_trabajo = ct.id_centro_trabajo " +
			"AND ps.estatus IN (1, 2, 3, 5) " +
			"AND ct.id_centro_trabajo = :idCentroTrabajo")
			.addEntity(ProgramacionServicio.class)
			.setInteger("idCentroTrabajo", idCentroTrabajo);		
		
		return criteria.list().size() > 0;
	}

	@Override
	public boolean getServiciosEnProcesoPorUniverso(Long idUniverso) {
		EstatusServicio []estatus = {EstatusServicio.CERRADO_NO_EXITOSO, 
			EstatusServicio.ENPROCESO, EstatusServicio.ENPROGRAMACION,
			EstatusServicio.INICIADO};
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(ProgramacionServicio.class, "ps");
		criteria.createAlias("ps.solicitudServicio", "ss");
		criteria.createAlias("ss.contratoDetalle", "cd");
		criteria.createAlias("cd.universo", "u");
		
		criteria.add(Restrictions.eq("u.idUniverso", idUniverso));		
		criteria.add(Restrictions.in("ps.estatus",estatus));

		return (criteria.list().size() > 0 );
	}
	
	public String getUltimoConsecutivoFolio(int anio){
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query criteria = sesion.createSQLQuery("SELECT NVL(MAX(folio[5,10]),0) " +
				"FROM programacion_servicio " +
				"WHERE folio[12,15]= :anio")
				.setInteger("anio", anio);	
		return (String) criteria.uniqueResult();
	}
}