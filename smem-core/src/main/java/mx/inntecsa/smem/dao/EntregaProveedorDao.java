package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.EntregaProveedor;

@SuppressWarnings("rawtypes")
public interface EntregaProveedorDao extends GenericDao {
	
	public List<EntregaProveedor> getEntregasProveedores();
	public List<EntregaProveedor> getEntregasProveedoresByEstatus(Estatus estatus);

}
