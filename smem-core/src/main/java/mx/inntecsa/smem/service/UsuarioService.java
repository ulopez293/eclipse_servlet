package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.UsuarioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.TipoUsuario;


public interface UsuarioService {

	public List<UsuarioDto> getUsuarios();
	public List<UsuarioDto> getUsuariosPorEstatus(Estatus estatus);
	public List<UsuarioDto> getUsuariosPorTipoUsuario(TipoUsuario tipoUsuario);
	public UsuarioDto getUsuarioPorIdCentroTrabajo(Integer idCentroTrabajo);
	public UsuarioDto getUsuarioPorUsuario(String usuario);
	public UsuarioDto guardarUsuario(UsuarioDto usuarioDto);
	public boolean actualizarUsuario(UsuarioDto usuarioDto);
	public boolean eliminarUsuario(UsuarioDto usuarioDto);
	public List<UsuarioDto> getUsuariosSMEMParaReporte() ;
	public List<UsuarioDto> getUsuariosProveedoresParaReporte();

}