package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.UniversoDao;
import mx.inntecsa.smem.dto.UniversoContratoDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.EstatusRequiereServicio;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ProgramacionServicio;
import mx.inntecsa.smem.pojo.Universo;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;


@Repository("universoDao")
@SuppressWarnings("unchecked")
public class UniversoDaoImpl extends GenericDaoImpl<Universo, BigDecimal> implements UniversoDao {

	private Logger log = Logger.getLogger(UniversoDaoImpl.class);
	
	@Override
	public List<Universo> getUniversos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class);		
		return criteria.list();
	}

	@Override
	public List<Universo> getUniversosPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public Universo getUniversoPorIdUniverso(Long idUniverso) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class)
			.add(Restrictions.eq("idUniverso", idUniverso));
		return (Universo) criteria.uniqueResult();
	}

	@Override
	public List<Universo> getUniversosEnContratoPorParametros(String urct, Integer idEquipo,
			Long identificador, String serie, String inventario) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class, "u");
		criteria.createAlias("u.centroTrabajo", "ct");
		criteria.createAlias("u.contratosDetalle", "cd");
		criteria.createAlias("u.equipo", "eq");
		criteria.createAlias("cd.contrato", "c");
		
		criteria.add(Restrictions.eq("u.baja", Estatus.ACTIVO));
		criteria.add(Restrictions.eq("u.requiereServicio", EstatusRequiereServicio.REQUIERE_SERVICIO));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.eq("cd.periodo", 1));

		if(urct != null){
			criteria.add(Restrictions.eq("ct.urct", urct));
		}
		
		if(idEquipo != null){
			criteria.add(Restrictions.eq("eq.idEquipo",idEquipo));
		}

		if(identificador != null){
			criteria.add(Restrictions.eq("u.idUniverso", identificador));
		}

		if(serie != null && !serie.trim().equals("")){
			criteria.add(Restrictions.eq("u.serie", serie));
		}

		if(inventario != null && !inventario.trim().equals("")){
			criteria.add(Restrictions.eq("u.inventario", inventario));
		}
		
		criteria.addOrder( Order.asc("u.idUniverso"));
		
		return criteria.list();
	}
	
	public List<Universo> getUniversosEnContratoSinServicios(String urct, Integer idEquipo,
			Long identificador, String serie, String inventario) {
		
		EstatusServicio []estatus = {EstatusServicio.CERRADO_NO_EXITOSO, EstatusServicio.ENPROCESO, EstatusServicio.ENPROGRAMACION,
				EstatusServicio.INICIADO};
		DetachedCriteria subquery = DetachedCriteria.forClass(ProgramacionServicio.class, "ps");
		subquery.createAlias("ps.solicitudServicio", "ss");
		subquery.createAlias("ss.contratoDetalle", "cd");
		subquery.createAlias("cd.universo", "u");
		subquery.createAlias("cd.contrato", "c");
		subquery.add(Restrictions.eq("ps.tipoServicio",TipoServicio.SERVICIO_CORRECTIVO));
		subquery.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		subquery.add(Restrictions.in("ps.estatus", estatus));
		subquery.setProjection(Property.forName("u.idUniverso"));
			
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class, "u");
		criteria.createAlias("u.centroTrabajo", "ct");
		criteria.createAlias("u.contratosDetalle", "cd");
		criteria.createAlias("u.equipo", "eq");
		criteria.createAlias("cd.contrato", "c");
		
		criteria.add(Restrictions.eq("u.baja", Estatus.ACTIVO));
		criteria.add(Restrictions.eq("u.requiereServicio", EstatusRequiereServicio.REQUIERE_SERVICIO));
		criteria.add(Restrictions.eq("c.estatus", EstatusContrato.VIGENTE));
		criteria.add(Restrictions.eq("cd.periodo", 1));
		criteria.add(Subqueries.propertyNotIn("u.idUniverso", subquery)); 

		if(urct != null){
			criteria.add(Restrictions.eq("ct.urct", urct));
		}
		
		if(idEquipo != null){
			criteria.add(Restrictions.eq("eq.idEquipo",idEquipo));
		}

		if(identificador != null){
			criteria.add(Restrictions.eq("u.idUniverso", identificador));
		}

		if(serie != null && !serie.trim().equals("")){
			criteria.add(Restrictions.eq("u.serie", serie));
		}

		if(inventario != null && !inventario.trim().equals("")){
			criteria.add(Restrictions.eq("u.inventario", inventario));
		}
		
		criteria.addOrder( Order.asc("u.idUniverso"));
		
		return criteria.list();
	}
	
	@Override
	public List<Universo> getUniversosPorIdCentroTrabajo(Integer idCentroTrabajo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class, "u")
			.createAlias("u.centroTrabajo", "ct")	
			.add(Restrictions.eq("u.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo));
		return criteria.list();
	}

	@Override
	public List<Universo> getUniversosPorParametros(Integer idUnidadRegional,
		Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso) {
		log.info("Universos por parametros ");
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
        
		Criteria criteria = sesion.createCriteria(Universo.class, "u")
			.createAlias("u.centroTrabajo", "ct")	
			.createAlias("ct.unidadRegional", "ur")
			.add(Restrictions.eq("u.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("ct.idCentroTrabajo", idCentroTrabajo))
			.add(Restrictions.eq("ur.idUnidadRegional", idUnidadRegional));
		
		if(inventario != null) {//JAC CAMBIAR A INTEGER
			criteria.add(Restrictions.eq("u.inventario", inventario.toString()));
		}
		
		if(serie != null && !serie.equals("")) {
			criteria.add(Restrictions.ilike("u.serie", "%" + serie + "%"));
		}
		
		if(idUniverso!=null){
			criteria.add(Restrictions.eq("u.idUniverso",idUniverso));
		}
		
		return criteria.list();
	}

	@Override
	public Universo getUniversoPorInventario(Integer inventario) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Universo.class)
			.add(Restrictions.eq("inventario", inventario.toString()));//JAC cambiar a entero en futuro
		
		if(criteria.list().size() > 0) {
			return (Universo) criteria.list().get(0);
		}
		
		return null;
	}
	
	public List<UniversoContratoDto> getUniversosCompletosPorIdCentroTrabajo(Integer idCentroTrabajo) {
		log.info("***Universo con informacion de los detalles del contrato del centro de trabajo: " + idCentroTrabajo);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("SELECT "+
				"U.id_universo as idUniverso, "+
				"VCD.numero_contrato as numeroContrato, "+
				"VCD.consecutivo_contrato as consecutivoContrato, "+
				"VP1.inicio_periodo as inicioPeriodo1, "+
				"VP1.fin_periodo as finPeriodo1, "+
				"VP2.inicio_periodo as inicioPeriodo2, "+
				"VP2.fin_periodo as finPeriodo2, "+
				"VP3.inicio_periodo as inicioPeriodo3, "+
				"VP3.fin_periodo as finPeriodo3, "+
				"VP4.inicio_periodo as inicioPeriodo4, "+
				"VP4.fin_periodo as finPeriodo4 "+
			"FROM "+
				"universo U "+ 
					"LEFT JOIN contratos_activos AS VCD "+
					"ON U.id_universo = VCD. id_universo "+ 
						"LEFT JOIN periodo1 AS VP1 "+ 
						"ON U.id_universo = VP1. id_universo "+ 
							"LEFT JOIN periodo2 AS VP2 "+ 
							"ON U.id_universo = VP2.id_universo "+ 
								"LEFT JOIN periodo3 AS VP3 "+ 
								"ON U.id_universo = VP3.id_universo "+ 
									"LEFT JOIN periodo4 AS VP4 "+ 
									"ON U.id_universo = VP4.id_universo "+ 
			"WHERE "+
				"U.id_funcionalidad IN (1,2) AND "+
				"U.id_centro_trabajo= :idCentroTrabajo");
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		sqlQuery.setParameter("idCentroTrabajo", idCentroTrabajo);
		log.info("***Consulta: "+sqlQuery.getQueryString());
		sqlQuery.setResultTransformer(Transformers.aliasToBean(UniversoContratoDto.class));
		List<UniversoContratoDto> result = sqlQuery.list();
		if(result != null && result.size() > 0){
			log.info("*** Registros: " + result.size());
		}
		return result;
	}
	
	public List<UniversoContratoDto> getUniversosCompletosPorParametros(Integer idUnidadRegional,
			Integer idCentroTrabajo, Integer inventario, String serie, Long idUniverso) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer strQuery =  new StringBuffer();
		strQuery.append("SELECT "+
				"U.id_universo as idUniverso, "+
				"VCD.numero_contrato as numeroContrato, "+
				"VCD.consecutivo_contrato as consecutivoContrato, "+
				"VP1.inicio_periodo as inicioPeriodo1, "+
				"VP1.fin_periodo as finPeriodo1, "+
				"VP2.inicio_periodo as inicioPeriodo2, "+
				"VP2.fin_periodo as finPeriodo2, "+
				"VP3.inicio_periodo as inicioPeriodo3, "+
				"VP3.fin_periodo as finPeriodo3, "+
				"VP4.inicio_periodo as inicioPeriodo4, "+
				"VP4.fin_periodo as finPeriodo4 "+
			"FROM "+
				"universo U "+ 
					"LEFT JOIN cat_centro_trabajo ct "+ 
					"ON U.id_centro_trabajo=ct.id_centro_trabajo "+ 
						"LEFT JOIN cat_unidad_regional ur "+ 
						"ON ct.id_unidad_regional=ur.id_unidad_regional "+ 
							"LEFT JOIN contratos_activos AS VCD "+
							"ON U.id_universo = VCD. id_universo "+ 
								"LEFT JOIN periodo1 AS VP1 "+ 
								"ON U.id_universo = VP1. id_universo "+ 
									"LEFT JOIN periodo2 AS VP2 "+ 
									"ON U.id_universo = VP2.id_universo "+ 
										"LEFT JOIN periodo3 AS VP3 "+ 
										"ON U.id_universo = VP3.id_universo "+ 
											"LEFT JOIN periodo4 AS VP4 "+ 
											"ON U.id_universo = VP4.id_universo "+ 
			"WHERE "+
				"U.id_funcionalidad IN (1,2) AND "+
				"ct.id_centro_trabajo= :idCentroTrabajo AND "+
				"ur.id_unidad_regional= :idUnidadRegional ");
		if(inventario != null) {
			strQuery.append("AND U.inventario= :inventario ");
		}
		
		if(serie != null && !serie.equals("")) {
			strQuery.append("AND U.serie= :serie ");
		}
		if(idUniverso!=null){
			strQuery.append("AND U.id_universo= :idUniverso ");
		}
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		sqlQuery.setParameter("idUnidadRegional", idUnidadRegional);
		sqlQuery.setParameter("idCentroTrabajo", idCentroTrabajo);
		if(inventario != null) {
			sqlQuery.setParameter("inventario", inventario.toString());
		}
		
		if(serie != null && !serie.equals("")) {
			sqlQuery.setParameter("serie", serie);
		}
		if(idUniverso!=null){
			sqlQuery.setParameter("idUniverso", idUniverso);
		}
		log.info("***Consulta: "+sqlQuery.getQueryString());
		sqlQuery.setResultTransformer(Transformers.aliasToBean(UniversoContratoDto.class));
		List<UniversoContratoDto> result = sqlQuery.list();
		if(result != null && result.size() > 0){
			log.info("*** Registros: " + result.size());
		}
		return result;
	}

}
