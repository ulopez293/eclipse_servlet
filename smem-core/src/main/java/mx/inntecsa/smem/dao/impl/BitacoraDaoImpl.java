package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.BitacoraDao;
import mx.inntecsa.smem.pojo.Bitacora;
import mx.inntecsa.smem.utils.Fecha;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;



@Repository("bitacoraDao")
public class BitacoraDaoImpl extends GenericDaoImpl<Bitacora, BigDecimal> implements BitacoraDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Bitacora> getBitacoraPorParametros(String usuario,
			Date fechaInicio, Date fechaFin, String movimiento) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Bitacora.class);
		
		if(usuario!=null && !usuario.equals(""))
			criteria.add(Restrictions.eq("usuario",usuario));
		
		if(fechaInicio != null && fechaFin != null){			
			fechaFin = Fecha.agregarDiaFecha(fechaFin);
			criteria.add(Restrictions.between("fecha",fechaInicio, fechaFin));
		}
		
		if(movimiento != null && !movimiento.equals(""))
			criteria.add(Restrictions.eq("operacion",movimiento));

		criteria.setMaxResults(500);
		criteria.addOrder(Order.desc("fecha") );
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bitacora> getBitacora(String usuario) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Criteria criteria = sesion.createCriteria(Bitacora.class);
		
		if(usuario!=null)
			criteria.add(Restrictions.eq("usuario",usuario));
		
		criteria.addOrder(Order.desc("fecha") );
		criteria.setMaxResults(500);
		return criteria.list();
	}


}
