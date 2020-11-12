package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.GrupoClaveDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.GrupoClave;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("gruposClaveDao")
@SuppressWarnings("unchecked")
public class GrupoClaveDaoImpl extends GenericDaoImpl<GrupoClave, BigDecimal> implements GrupoClaveDao {
	
	private Logger log = Logger.getLogger(GrupoClaveDaoImpl.class);

	@Override
	public List<GrupoClave> getGruposClaves() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class);		
		return criteria.list();
	}

	@Override
	public List<GrupoClave> getGruposClavesByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<GrupoClave> getGruposClavesPorIdEquipo(Integer idEquipo) {
	    Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")
			.createAlias("gc.equipo", "e")	
			.add(Restrictions.eq("gc.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("e.idEquipo", idEquipo));
		return criteria.list();
	}

	@Override
	public List<GrupoClave> getGruposClavesPorIdGrupo(Integer idGrupo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")
			.createAlias("gc.grupo", "g")	
			.add(Restrictions.eq("gc.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("g.idGrupo", idGrupo));
		return criteria.list();
	}

	@Override
	public GrupoClave getGrupoClavePorParametros(Integer idEquipo, Integer idGrupo, Integer idClave) {
		log.info(">>>idEquipo " + idEquipo);
		log.info(">>>idGrupo " + idGrupo);
		log.info(">>>idClave" + idClave);
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")
			.createAlias("gc.grupo", "g")	
			.createAlias("gc.equipo", "e")
			.createAlias("gc.clave", "c")
			.add(Restrictions.eq("gc.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("g.idGrupo", idGrupo))
			.add(Restrictions.eq("c.idClave", idClave))
			.add(Restrictions.eq("e.idEquipo", idEquipo));
		
		if(criteria.list().size() > 0) {
			return (GrupoClave) criteria.list().get(0);
		}
		
		return null;
	}
	
	@Override
	public List<GrupoClave> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")
			.createAlias("gc.grupo", "g")	
			.createAlias("gc.equipo", "e")
			.createAlias("gc.clave", "c")
			.add(Restrictions.eq("g.idGrupo", idGrupo))
			.add(Restrictions.eq("c.idClave", idClave))
			.add(Restrictions.eq("e.idEquipo", idEquipo));
		return criteria.list();
	}
	
	@Override
	public List<GrupoClave> getGruposClavesPorParametros(Integer idGrupo, Integer idClave, Integer idEquipo, Integer idGrupoClave) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")
			.createAlias("gc.grupo", "g")	
			.createAlias("gc.equipo", "e")
			.createAlias("gc.clave", "c")
			.add(Restrictions.eq("g.idGrupo", idGrupo))
			.add(Restrictions.eq("c.idClave", idClave))
			.add(Restrictions.eq("e.idEquipo", idEquipo))
			.add(Restrictions.not(Restrictions.eq("gc.idGrupoClave", idGrupoClave)));
		return criteria.list();
	}
	
	@Override
	public GrupoClave getGrupoClavePorId(Integer idGrupoClave) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(GrupoClave.class, "gc")				
			.add(Restrictions.eq("gc.idGrupoClave", idGrupoClave));					
		return (GrupoClave) criteria.uniqueResult();
	}

}
