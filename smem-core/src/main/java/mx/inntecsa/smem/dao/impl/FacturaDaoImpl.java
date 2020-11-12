package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.FacturaDao;
import mx.inntecsa.smem.dto.DetalleFacturaDto;
import mx.inntecsa.smem.enums.Mes;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.Bitacora;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;


@Repository("facturaDao")
public class FacturaDaoImpl extends GenericDaoImpl<Bitacora, BigDecimal> implements FacturaDao {

	private static Log log = LogFactory.getLog(FacturaDaoImpl.class);
			
	private String query_correctivo = " SELECT cast('Correctivo' as varchar(20)) AS tiposervicio, programacion_servicio.folio AS folio, " +
										" cast('2.5' as varchar(3)) AS porcentajepenalizacion, " +
										" contrato.numero_contrato AS numerocontrato, contrato_detalle.id_universo AS idequipo, " +
										" cat_equipo.equipo AS equipo, universo.serie AS serie, universo.inventario AS inventario, " +
										" universo.marca as marca, universo.modelo as modelo, programacion_servicio.id_tipo_servicio AS idtiposervicio," +
										" cat_centro_trabajo.descripcion AS centrotrabajo," +
										" contrato_detalle.consecutivo_contrato AS consecutivocontrato, " +
										" contrato.ejercicio as ejercicio, contrato_detalle.:mes AS costo, solicitud_servicio.fecha_inicio AS fechainicial, " +
										" solicitud_servicio.fecha_fin AS fechalimite," +
										" (SELECT MAX(a.fecha_final_servicio) FROM acta_entrega_recepcion a" +
										"     WHERE a.id_programacion_servicio = programacion_servicio.id_programacion_servicio) AS fechareal " +
										" FROM universo  " +
										" INNER JOIN contrato_detalle  " +
										" ON universo.id_universo = contrato_detalle.id_universo " +
										" INNER JOIN contrato                                                                                                                           " +
										" ON contrato_detalle.id_contrato = contrato.id_contrato  "+										
										" INNER JOIN cat_equipo    " +
										" ON universo.id_equipo = cat_equipo.id_equipo  " +
										" INNER JOIN cat_centro_trabajo " +
										" ON universo.id_centro_trabajo = cat_centro_trabajo.id_centro_trabajo  " +
										" INNER JOIN solicitud_servicio " +
										" ON contrato_detalle.id_contrato_detalle = solicitud_servicio.id_contrato_detalle " +
										" INNER JOIN programacion_servicio  " +
										" ON solicitud_servicio.id_solicitud_servicio = programacion_servicio.id_solicitud_servicio " +
										" WHERE programacion_servicio.id_tipo_servicio = 2 " +
										" AND programacion_servicio.estatus IN (1, 2, 3, 5, 6) " +
										" AND contrato_detalle.id_contrato = :idContrato " +
										" AND (:fechaFin BETWEEN solicitud_servicio.fecha_inicio AND solicitud_servicio.fecha_fin " +
										" OR solicitud_servicio.fecha_fin <= :fechaFin)  " +
										" AND ((SELECT MAX(a.fecha_final_servicio) FROM acta_entrega_recepcion a " +
										"         WHERE a.id_programacion_servicio = programacion_servicio.id_programacion_servicio) IS NULL  " +
										" OR ((SELECT MAX(a.fecha_final_servicio) FROM acta_entrega_recepcion a " +
										"         WHERE a.id_programacion_servicio = programacion_servicio.id_programacion_servicio) BETWEEN :fechaInicio AND :fechaFin " +
										"         AND programacion_servicio.estatus IN(4, 5, 6))) " +
										" ORDER BY contrato_detalle.id_universo, programacion_servicio.id_tipo_servicio " ;
										
	private String query_preventivo = " SELECT cast('Preventivo' as varchar(10)) AS tiposervicio, programacion_servicio.folio AS folio, " +
										" cast('2.5' as varchar(3)) AS porcentajepenalizacion, " +
										" contrato.numero_contrato AS numerocontrato, contrato_detalle.id_universo AS idequipo, " +
										" cat_equipo.equipo AS equipo, universo.serie AS serie, universo.inventario AS inventario, " +
										" universo.marca as marca, universo.modelo as modelo, programacion_servicio.id_tipo_servicio AS idtiposervicio," +
										" cat_centro_trabajo.descripcion AS centrotrabajo," +
										" contrato_detalle.consecutivo_contrato AS consecutivocontrato, " +
										" contrato.ejercicio as ejercicio, contrato_detalle.:mes AS costo, solicitud_servicio.fecha_inicio AS fechainicial, " +
										" solicitud_servicio.fecha_fin AS fechalimite," +
										" (SELECT MAX(a.fecha_final_servicio) FROM acta_entrega_recepcion a" +
										"     WHERE a.id_programacion_servicio = programacion_servicio.id_programacion_servicio) AS fechareal " +			
										" FROM universo    " +
										" INNER JOIN contrato_detalle " +
										" ON universo.id_universo = contrato_detalle.id_universo " +
										" INNER JOIN contrato       " +
										" ON contrato_detalle.id_contrato = contrato.id_contrato " +  										
										" INNER JOIN cat_equipo           " +
										" ON universo.id_equipo = cat_equipo.id_equipo " +
										" INNER JOIN cat_centro_trabajo                " +
										" ON universo.id_centro_trabajo = cat_centro_trabajo.id_centro_trabajo  " +
										" INNER JOIN solicitud_servicio   " +
										" ON contrato_detalle.id_contrato_detalle = solicitud_servicio.id_contrato_detalle   " +
										" INNER JOIN programacion_servicio      " +
										" ON solicitud_servicio.id_solicitud_servicio = programacion_servicio.id_solicitud_servicio     " +
										" WHERE programacion_servicio.id_tipo_servicio = 1      " +
										" AND contrato_detalle.id_contrato = :idContrato " +
										" AND (:fechaFin BETWEEN solicitud_servicio.fecha_inicio AND solicitud_servicio.fecha_fin  " +
										" OR solicitud_servicio.fecha_fin <= :fechaFin)  " +
										" AND solicitud_servicio.fecha_fin = (SELECT MAX(ss.fecha_fin) FROM solicitud_servicio ss             " +
										"                                            WHERE ss.id_contrato_detalle = contrato_detalle.id_contrato_detalle      " +
										"                                            AND (:fechaFin BETWEEN ss.fecha_inicio AND ss.fecha_fin      " +
										"                                            OR ss.fecha_fin <= :fechaFin))     " +
										" ORDER BY contrato_detalle.id_universo, programacion_servicio.id_tipo_servicio ";

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleFacturaDto> getDetallesFactura(int idContrato, TipoServicio tipoServicio, Mes mesBusqueda) {
		
		//datos
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String mes = mesBusqueda.getDescripcion().toLowerCase();
		Date fechaInicio = Fecha.getPrimerDiaDelMes(mesBusqueda.getIdMes());
		Date fechaFin = Fecha.getUltimoDiaDelMes(mesBusqueda.getIdMes());
		String query="";
		
		//valdiar si es un servicio preventivo, correctivo o ambos.
		if(tipoServicio == TipoServicio.SERVICIO_PREVENTIVO_CORRECTIVO) {
		
			query = "SELECT * FROM (" + query_preventivo + ") AS preventivo UNION SELECT * FROM (" + query_correctivo + ") AS correctivo " +
					" ORDER BY idequipo, idtiposervicio ";
		}else{
			
			if( tipoServicio == TipoServicio.SERVICIO_CORRECTIVO){
				query= this.query_correctivo;
			}
			else if(tipoServicio == TipoServicio.SERVICIO_PREVENTIVO) {
				query = this.query_preventivo;
			}
		}

		query = query.replaceAll(":mes", mes);
		
		log.info(">>>Query: " + query);
		log.info(">>>mes " + mes + " fechaInicio " + fechaInicio +"fechaFin" + fechaFin + " contrato " + idContrato + " tipoServicio " + tipoServicio);

		Query sqlQuery = session.createSQLQuery(query);
		sqlQuery.setParameter("idContrato", idContrato);
		sqlQuery.setParameter("fechaFin", fechaFin);
		
		if(tipoServicio == TipoServicio.SERVICIO_PREVENTIVO_CORRECTIVO || tipoServicio == TipoServicio.SERVICIO_CORRECTIVO){
			sqlQuery.setParameter("fechaInicio", fechaInicio);
		}
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(DetalleFacturaDto.class));
		return sqlQuery.list();			
		
	}

}
