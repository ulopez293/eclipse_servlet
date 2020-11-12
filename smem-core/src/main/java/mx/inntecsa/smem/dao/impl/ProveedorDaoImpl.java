package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.ProveedorDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Proveedor;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("proveedorDao")
@SuppressWarnings("unchecked")
public class ProveedorDaoImpl extends GenericDaoImpl<Proveedor, BigDecimal> implements ProveedorDao {

	@Override
	public List<Proveedor> getProveedores() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Proveedor.class);	
		criteria.addOrder(Order.asc("proveedor") );
		return criteria.list();
	}

	@Override
	public List<Proveedor> getProveedoresPorEstatus(Estatus estatus) {
		
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Proveedor.class)
			.add(Restrictions.eq("baja", estatus));
		
		criteria.addOrder(Order.asc("proveedor") );
		return criteria.list();
		
	}

	@Override
	public Proveedor getProveedorPorIdProveedor(Integer idProveedor) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Proveedor.class)
			.add(Restrictions.eq("idProveedor", idProveedor));		
		return (Proveedor) criteria.uniqueResult();
	}
	
	@Override
	public List<Proveedor> getProveedoresPorNombre(String nombre) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Proveedor.class,"p")
			.add(Restrictions.eq("p.proveedor", nombre));	
		return criteria.list();
	}
	
	@Override
	public List<Proveedor> getProveedoresPorNombre(String nombre, Integer idProveedor) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Proveedor.class,"p")
			.add(Restrictions.eq("p.proveedor", nombre))
			.add(Restrictions.not(Restrictions.eq("p.idProveedor", idProveedor)));
		return criteria.list();
	}

}
