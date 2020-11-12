package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ContratoDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.pojo.Contrato;
import mx.inntecsa.smem.utils.Fecha;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("contratoDao")
@SuppressWarnings("unchecked")
public class ContratoDaoImpl extends GenericDaoImpl<Contrato, BigDecimal> implements ContratoDao {

	@Override
	public List<Contrato> getContratos() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class);		
		return criteria.list();
	}

	@Override
	public List<Contrato> getContratosPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<Contrato> getContratosPorParametros(EstatusContrato estatusContrato, 
		Integer idProveedor, Date fechaInicio, Date fechaFin) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class, "c");
		criteria.createAlias("c.proveedor", "p");
		
		if(estatusContrato != null) {
			criteria.add(Restrictions.eq("estatus", estatusContrato));
		}
		
		if(idProveedor != null) {
			criteria.add(Restrictions.eq("p.idProveedor", idProveedor));
		}
		
		if(fechaInicio != null && fechaFin != null) {			
			fechaFin = Fecha.agregarDiaFecha(fechaFin);
			criteria.add(Restrictions.between("vigenciaInicioContrato", fechaInicio, fechaFin));
		}
		
		criteria.add(Restrictions.eq("baja", Estatus.ACTIVO));
		return criteria.list();
	}

	@Override
	public Contrato getContratoPorNumeroContrato(String numeroContrato) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class)
			.add(Restrictions.eq("numeroContrato", numeroContrato));
		return (Contrato) criteria.uniqueResult();
	}

	@Override
	public Contrato getContratoPorIdContrato(Integer idContrato) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class)
			.add(Restrictions.eq("idContrato", idContrato));
		return (Contrato) criteria.uniqueResult();
	}

	@Override
	public List<Contrato> getContratoPorEstatusContrato(EstatusContrato estatusContrato) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Contrato.class)
			.add(Restrictions.eq("estatus", estatusContrato))
			.add(Restrictions.eq("baja", Estatus.ACTIVO));
		return criteria.list();
	}

}
