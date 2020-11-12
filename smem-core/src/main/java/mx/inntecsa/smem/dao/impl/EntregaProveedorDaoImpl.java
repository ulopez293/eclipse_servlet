package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.EntregaProveedorDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.EntregaProveedor;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("entregaProveedorDao")
@SuppressWarnings("unchecked")
public class EntregaProveedorDaoImpl extends GenericDaoImpl<EntregaProveedor, BigDecimal> implements EntregaProveedorDao {

	@Override
	public List<EntregaProveedor> getEntregasProveedores() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(EntregaProveedor.class);		
		return criteria.list();
	}

	@Override
	public List<EntregaProveedor> getEntregasProveedoresByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(EntregaProveedor.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

}
