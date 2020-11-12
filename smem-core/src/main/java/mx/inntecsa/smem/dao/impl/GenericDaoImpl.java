package mx.inntecsa.smem.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;


import mx.inntecsa.smem.dao.GenericDao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Clase generica para almacenar, editar, eliminar o buscar un objeto de 
 * cualquier tipo, ya que al agregar la anotacion <Object, ID extends Serializable>
 * nos permite trabajar con cualquier tipo de objeto que este mapeado a una tabla (Pojos). 
 * coordenadas de su  centro.
 * @version 1.0
 * @author INNTECSA.
 */
@SuppressWarnings({ "hiding", "rawtypes" })
public class GenericDaoImpl<Object, ID extends Serializable> extends HibernateDaoSupport implements GenericDao {
	
	private Class<Object> clasePersistente;
	
	/**
    * Metodo para inicializar o cargar el SessionFactory.
    * @param factory, es la session que se toma de archivo de configuracion
    * 		 applicationContext.xml.
    */
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	}
	
	
	/**
    * Constructor de la clase que extrae el tipo de clase al que pertence el Objeto.
    */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		clasePersistente = (Class<Object>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	
	/**
    * Metodo para guardar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 guardar.
    */	
	public java.lang.Object save(java.lang.Object entidad) throws HibernateException {
		entidad = getHibernateTemplate().save(entidad);		
		
		return entidad;
	}
		
	
	/**
    * Metodo para editar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 actualizar o editar.
    */			
	public void update(java.lang.Object entidad) throws HibernateException {
		getHibernateTemplate().update(entidad);
	}

	
	/**
    * Metodo para eliminar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 eliminar.
    */			
	public void delete(java.lang.Object entidad) throws HibernateException {
		getHibernateTemplate().delete(entidad);
	}

	
	/**
    * Metodo para para obtener un registro(Objeto) de cualquier tabla en la Base de Datos.
    * @param id, es la llave primaria de tipo Serializable por la cual se buscara 
    * 		 nuestro objeto.
    */			
	public java.lang.Object get(Serializable id) throws HibernateException {
		return getHibernateTemplate().get(clasePersistente.getClass().getName(), id);	
	}


	@Override
	public void saveAll(Collection entidades) throws HibernateException {
		int i = 0;
		
		for (java.lang.Object object : entidades) {
			getHibernateTemplate().save(object);
			++i;
		    
			//log.info("Guardado #: " + i);
		    if ( i % 100 == 0 || i == entidades.size()) {
		        //flush a batch of inserts and release memory:
		    	getSession().flush();
		    	getSession().clear();
		    }
		}		
		
	}


	@Override
	public void updateAll(Collection entidades) throws HibernateException {
		int i = 0;
		
		for (java.lang.Object object : entidades) {
			getHibernateTemplate().update(object);
			++i;
		    
			//log.info("Guardado #: " + i);
		    if ( i % 100 == 0 || i == entidades.size()) {
		        //flush a batch of inserts and release memory:
		    	getSession().flush();
		    	getSession().clear();
		    }
		}		
	}

}
