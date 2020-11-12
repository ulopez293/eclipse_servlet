package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import mx.inntecsa.smem.dao.BloqueoCTDao;
import mx.inntecsa.smem.dto.CentroTrabajoBloqueadoDto;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.pojo.CentroTrabajo;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository("bloqueoCTDao")
public class BloqueoCTDaoImpl extends GenericDaoImpl<CentroTrabajo, BigDecimal> implements BloqueoCTDao {
	
	private Logger log = Logger.getLogger(BloqueoCTDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCentrosTrabajoBloqueados() {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("SELECT DISTINCT ct.id_centro_trabajo ");
		strQuery.append("FROM solicitud_servicio ss ");
		strQuery.append("INNER JOIN programacion_servicio ps ");
		strQuery.append("ON ss.id_solicitud_servicio = ps.id_solicitud_servicio ");
		strQuery.append("INNER JOIN contrato_detalle cd ON ss.id_contrato_detalle = cd.id_contrato_detalle ");
		strQuery.append("INNER JOIN universo u ON cd.id_universo = u.id_universo ");
		strQuery.append("INNER JOIN cat_centro_trabajo ct ON u.id_centro_trabajo = ct.id_centro_trabajo ");
		strQuery.append("WHERE ps.estatus in ( :estatus ) ");
		strQuery.append("AND ss.fecha_fin < CAST (CURRENT AS DATE) ");
		strQuery.append("AND ss.fecha_inicio >= CAST(YEAR(CURRENT)||'-01-01' AS DATE) ");
		strQuery.append("AND ct.estatus = 0");
		
		log.info("Query: " + strQuery.toString());
		
		List<Integer> estatus = new ArrayList<Integer>();
		estatus.add(EstatusServicio.INICIADO.getIdEstatus());
		estatus.add(EstatusServicio.ENPROGRAMACION.getIdEstatus());
		estatus.add(EstatusServicio.ENPROCESO.getIdEstatus());
		estatus.add(EstatusServicio.CERRADO_NO_EXITOSO.getIdEstatus());
		
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		sqlQuery.setParameterList("estatus", estatus);
		
		log.info("Parametros ok, ejecuta query");
		
		return sqlQuery.list();
	}
	
	@Override
	public void actualizarEstatusCT(List<Integer> centrosTrabajo, boolean bloqueo) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String bloq = bloqueo ? "1" : "0";
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("update cat_centro_trabajo ");
		strQuery.append("set estatus = " + bloq);
		strQuery.append(" where id_centro_trabajo in ( :ids )");
		log.info("Update query: " + strQuery.toString());
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		sqlQuery.setParameterList("ids", centrosTrabajo);
		int result = sqlQuery.executeUpdate();
		
		if(result > 0) {
			log.info("Actualizo: " + result);
		} else {
			log.info("No actualizo");
		}
		
		return;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CentroTrabajoBloqueadoDto> getConsultaCtblq(int entidad, 
			int unidadRegional) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("SELECT ct.id_centro_trabajo AS id, ");
		strQuery.append("cur.unidad_regional AS entidad, ");
		strQuery.append("ct.urct AS urct, ");
		strQuery.append("ct.descripcion AS unidad, ");
		strQuery.append("cs.nombre AS supervisor ");
		strQuery.append("FROM cat_centro_trabajo ct ");
		strQuery.append("INNER JOIN cat_unidad_regional cur ON ct.id_unidad_regional = cur.id_unidad_regional ");
		strQuery.append("INNER JOIN cat_supervisor cs ON cur.id_supervisor = cs.id_supervisor ");
		strQuery.append("INNER JOIN cat_entidad ce ON cur.id_entidad = ce.id_entidad ");
		strQuery.append("WHERE ct.estatus = 1 ");
		if (entidad != 0) {
			strQuery.append("AND ce.id_entidad = :idEntidad ");
			strQuery.append("AND ct.id_unidad_regional = :idUnidadRegional ");
		}
		log.info("Query: " + strQuery.toString());
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		if (entidad != 0) {
			sqlQuery.setParameter("idEntidad", entidad);
			sqlQuery.setParameter("idUnidadRegional", unidadRegional);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(CentroTrabajoBloqueadoDto.class));
		
		return sqlQuery.list();
	}

}
