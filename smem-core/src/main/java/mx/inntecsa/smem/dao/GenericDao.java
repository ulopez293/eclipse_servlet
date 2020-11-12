package mx.inntecsa.smem.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.HibernateException;

/**
 * Interface donde enuncian los metodos para almacenar, editar, eliminar o buscar un objeto de 
 * cualquier tipo de clase, ya que al agregar la anotacion <T, ID extends Serializable>
 * nos permite trabajar con cualquier tipo de objeto que este mapeado a una tabla (Pojos). 
 * coordenadas de su  centro. De esta clase extenderan todas las interfaces(Dao) que tengan
 * interaccion con la Base de Datos.
 * @version 1.0
 * @author INNTECSA.
 */
public interface GenericDao<T, ID extends Serializable>  {
	
	/**
    * Metodo para guardar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 guardar.
    */	
	public T save(T entidad) throws HibernateException;
	
	/**
    * Metodo para editar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 actualizar o editar.
    */				
	public void update(T entidad) throws HibernateException;
	
	/**
    * Metodo para eliminar un objeto(registro en la tabla) en la Base de Datos.
    * @param entidad, Es el objeto de cualquier tipo de clase que se desea
    * 		 eliminar.
    */			
	public void delete(T entidad) throws HibernateException;
	
	
	/**
    * Metodo para para obtener un registro(Objeto) de cualquier tabla en la Base de Datos.
    * @param id, es la llave primaria de tipo Serializable por la cual se buscará 
    * 		 nuestro objeto.
    */					
	public T get(ID id) throws HibernateException;
	
	
	/**
    * Metodo para guardar un una lista de objetos(registro en la tabla) en la Base de Datos.
    * @param entidades, coleccion de cualquier tipo de clase que se desea
    * 		 guardar.
    */	
	public void saveAll(Collection<T> entidades) throws HibernateException;
	
	/**
	* Metodo para guardar un una lista de objetos(registro en la tabla) en la Base de Datos.
	* @param entidades, coleccion de cualquier tipo de clase que se desea
	* 		 guardar.
	*/	
	public void updateAll(Collection<T> entidades) throws HibernateException;

}
