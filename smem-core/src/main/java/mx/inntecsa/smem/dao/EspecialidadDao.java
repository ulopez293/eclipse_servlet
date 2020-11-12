package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Especialidad;

@SuppressWarnings("rawtypes")
public interface EspecialidadDao extends GenericDao {
	
	public List<Especialidad> getEspecialidades();
	public List<Especialidad> getEspecialidadesByEstatus(Estatus estatus);
	public Especialidad getEspecialidadPorId(Integer idEspecialidad);
	public List<Especialidad> getEspecialidadesPorNombre(String nombre);
	public List<Especialidad> getEspecialidadesPorNombre(String nombre, Integer idEespecialidad);

}
