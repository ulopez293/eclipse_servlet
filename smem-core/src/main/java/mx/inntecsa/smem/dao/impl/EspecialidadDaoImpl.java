package mx.inntecsa.smem.dao.impl;


import java.math.BigDecimal;
import java.util.List;


import mx.inntecsa.smem.dao.EspecialidadDao;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Especialidad;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("especialidadDao")
@SuppressWarnings("unchecked")
public class EspecialidadDaoImpl extends GenericDaoImpl<Especialidad, BigDecimal> implements EspecialidadDao {

	@Override
	public List<Especialidad> getEspecialidades() {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Especialidad.class);
		return criteria.list();
	}

	@Override
	public List<Especialidad> getEspecialidadesByEstatus(Estatus estatus) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Especialidad.class)
			.add(Restrictions.eq("baja", estatus));
		return criteria.list();
	}
	
	@Override
	public Especialidad getEspecialidadPorId(Integer idEspecialidad) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Especialidad.class, "e")				
			.add(Restrictions.eq("e.idEspecialidad", idEspecialidad));					
		return (Especialidad) criteria.uniqueResult();
	}
	
	@Override
	public List<Especialidad> getEspecialidadesPorNombre(String nombre) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Especialidad.class,"e")
			.add(Restrictions.eq("e.especialidad", nombre));	
		return criteria.list();
	}
	
	@Override
	public List<Especialidad> getEspecialidadesPorNombre(String nombre, Integer idEespecialidad) {
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = sesion.createCriteria(Especialidad.class,"e")
			.add(Restrictions.eq("e.especialidad", nombre))
			.add(Restrictions.not(Restrictions.eq("e.idEspecialidad", idEespecialidad)));
		return criteria.list();
	}

}
