package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;

import mx.inntecsa.smem.dao.ConfiguracionDao;
import mx.inntecsa.smem.pojo.Configuracion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository("configuracionDao")
public class ConfiguracionDaoImpl extends GenericDaoImpl<Configuracion, BigDecimal> implements ConfiguracionDao{
	
	public Configuracion getConfiguracion(){
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Configuracion.class);
		criteria.setMaxResults(1);
		
		return (Configuracion) criteria.uniqueResult();
	}
}
