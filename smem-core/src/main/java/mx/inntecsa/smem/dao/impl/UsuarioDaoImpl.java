package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import mx.inntecsa.smem.dao.UsuarioDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.TipoUsuario;
import mx.inntecsa.smem.pojo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("usuarioDao")
@SuppressWarnings("unchecked")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, BigDecimal> implements UsuarioDao {


	@Override
	public List<Usuario> getUsuarios() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class);		
		return criteria.list();
	}
	
	@Override
	public List<Usuario> getUsuariosPorEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}

	@Override
	public List<Usuario> getUsuariosPorTipoUsuario(TipoUsuario tipoUsuario) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class)
			.add(Restrictions.eq("tipoUsuario", tipoUsuario));
		return criteria.list();
	}

	@Override
	public Usuario getUsuarioPorIdCentroTrabajo(Integer idCentrotrabajo) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class, "u")
			.createAlias("u.centroTrabajo", "ct")	
			.add(Restrictions.eq("ct.idCentroTrabajo", idCentrotrabajo));
		return (Usuario) criteria.uniqueResult();		
	}

	@Override
	public Usuario getUsuarioPorIdProveedor(Integer idProveedor) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class, "u")
			.createAlias("u.proveedor", "p")	
			.add(Restrictions.eq("p.idProveedor", idProveedor));
		return (Usuario) criteria.uniqueResult();		
	}
	
	@Override
	public Usuario getUsuarioPorUsuario(String usuario) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class, "u").add(Restrictions.eq("u.usuario", usuario));
		return (Usuario) criteria.uniqueResult();		
	}
	
	@Override
	public List<Usuario> getUsuariosSMEMParaReporte() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class,"u")
			.add(Restrictions.eq("u.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("u.tipoUsuario", TipoUsuario.ISSSTE));
		return criteria.list();
	}
	
	@Override
	public List<Usuario> getUsuariosProveedoresParaReporte() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Usuario.class,"u")
			.add(Restrictions.eq("u.baja", Estatus.ACTIVO))
			.add(Restrictions.eq("u.tipoUsuario", TipoUsuario.PROVEEDOR));
		return criteria.list();
	}

}
