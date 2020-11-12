package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Proveedor;

@SuppressWarnings("rawtypes")
public interface ProveedorDao extends GenericDao {
	
	public List<Proveedor> getProveedores();
	public List<Proveedor> getProveedoresPorEstatus(Estatus estatus);
	public Proveedor getProveedorPorIdProveedor(Integer idProveedor);
	public List<Proveedor> getProveedoresPorNombre(String nombre);
	public List<Proveedor> getProveedoresPorNombre(String nombre, Integer idProveedor);

}
