package mx.inntecsa.smem.dao;


import java.util.List;

import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.TipoUsuario;
import mx.inntecsa.smem.pojo.Usuario;



@SuppressWarnings("rawtypes")
public interface UsuarioDao extends GenericDao {
	
	public List<Usuario> getUsuarios();
	public List<Usuario> getUsuariosPorEstatus(Estatus estatus);
	public List<Usuario> getUsuariosPorTipoUsuario(TipoUsuario tipoUsuario);
	public Usuario getUsuarioPorIdCentroTrabajo(Integer idCentrotrabajo);
	public Usuario getUsuarioPorIdProveedor(Integer idProveedor);
	public Usuario getUsuarioPorUsuario(String usuario);
	public List<Usuario> getUsuariosSMEMParaReporte();
	public List<Usuario> getUsuariosProveedoresParaReporte();

}
