package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.UsuarioDao;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.dto.UsuarioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.Rol;
import mx.inntecsa.smem.enums.TipoUsuario;
import mx.inntecsa.smem.pojo.Usuario;
import mx.inntecsa.smem.service.UsuarioService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("usuarioService")
@SuppressWarnings("unchecked")
public class UsuarioServiceImpl implements UsuarioService {
	
	private Logger log = Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<UsuarioDto> getUsuarios() {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>(); //Lista de Usuarios (DTO)

		for (Usuario usuario : usuarioDao.getUsuarios()) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			usuariosDto.add(usuarioDto);
		}
		
		return usuariosDto;
	}
	
	@Override
	public List<UsuarioDto> getUsuariosPorEstatus(Estatus estatus) {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>(); //Lista de Usuarios (DTO)

		for (Usuario usuario : usuarioDao.getUsuariosPorEstatus(estatus)) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			usuariosDto.add(usuarioDto);
		}
		
		return usuariosDto;
	}

	@Override
	public List<UsuarioDto> getUsuariosPorTipoUsuario(TipoUsuario tipoUsuario) {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>(); //Lista de Usuarios (DTO)

		for (Usuario usuario : usuarioDao.getUsuariosPorTipoUsuario(tipoUsuario)) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			usuariosDto.add(usuarioDto);
		}
		
		return usuariosDto;
	}

	@Override
	public UsuarioDto getUsuarioPorIdCentroTrabajo(Integer idCentroTrabajo) {
		Usuario usuario = usuarioDao.getUsuarioPorIdCentroTrabajo(idCentroTrabajo);
		
		if(usuario != null) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			return usuarioDto;
		}
		
		return null;
	}

	@Override
	public UsuarioDto getUsuarioPorUsuario(String usuarioAlias) {
		Usuario usuario = usuarioDao.getUsuarioPorUsuario(usuarioAlias);
		
		if(usuario != null) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			return usuarioDto;
		}
		
		return null;
	}

	@Override
	public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);		
		
		try {
			usuarioDao.save(usuario);
			return usuarioDto;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return null;
		}
			
	}

	@Override
	public boolean actualizarUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
				
		try{
			usuarioDao.update(usuario);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
			
	}

	@Override
	public boolean eliminarUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
		
		try{
			usuarioDao.update(usuario);
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}
		
	private UsuarioDto manualModelMapper(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setIdUsuario(usuario.getIdUsuario());
		usuarioDto.setUsuario(usuario.getUsuario());
		usuarioDto.setContrasenia(usuario.getContrasenia());
		usuarioDto.setConfirmarContrasenia(usuario.getContrasenia());
		usuarioDto.setContraseniaAuxiliar(usuario.getContrasenia());
		usuarioDto.setIdProveedor(usuario.getIdProveedor());
		usuarioDto.setIdCentroTrabajo(usuario.getIdCentroTrabajo());			
		usuarioDto.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDto.setRol(Rol.getRol(usuario.getRol()));
		usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
		usuarioDto.setFechaRegistro(usuario.getFechaRegistro());
		usuarioDto.setFechaModificacion(usuario.getFechaModificacion());
		usuarioDto.setFechaBaja(usuario.getFechaBaja());
		usuarioDto.setBaja(usuario.getBaja());
		
		CentroTrabajoDto centroTrabajoDto = new CentroTrabajoDto();			
		
		if(usuario.getCentroTrabajo() != null) {
			EntidadDto entidadDto = new EntidadDto();
			entidadDto.setIdEntidad(usuario.getCentroTrabajo().getUnidadRegional().getEntidad().getIdEntidad());
			entidadDto.setEntidad(usuario.getCentroTrabajo().getUnidadRegional().getEntidad().getEntidad());
			
			UnidadRegionalDto unidadRegionalDto = new UnidadRegionalDto();
			unidadRegionalDto.setEntidad(entidadDto);
			unidadRegionalDto.setIdUnidadRegional(usuario.getCentroTrabajo().getUnidadRegional().getIdUnidadRegional());
			unidadRegionalDto.setUnidadRegional(usuario.getCentroTrabajo().getUnidadRegional().getUnidadRegional());
			
			centroTrabajoDto.setUnidadRegional(unidadRegionalDto);
			centroTrabajoDto.setDescripcion(usuario.getCentroTrabajo().getDescripcion());
			centroTrabajoDto.setIdCentroTrabajo(usuario.getCentroTrabajo().getIdCentroTrabajo());	
			centroTrabajoDto.setUrct(usuario.getCentroTrabajo().getUrct());
		}

		ProveedorDto proveedorDto = new ProveedorDto();
		
		if(usuario.getProveedor() != null) {
			proveedorDto.setIdProveedor(usuario.getProveedor().getIdProveedor());
			proveedorDto.setProveedor(usuario.getProveedor().getProveedor());
			proveedorDto.setTelefono(usuario.getProveedor().getTelefono());
			proveedorDto.setEmail(usuario.getProveedor().getEmail());
			proveedorDto.setDireccion(usuario.getProveedor().getDireccion());
		}
		
		usuarioDto.setProveedor(proveedorDto);
		usuarioDto.setCentroTrabajo(centroTrabajoDto);	
		
		return usuarioDto;
		
	}

	@Override
	public List<UsuarioDto> getUsuariosSMEMParaReporte() {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarioDao.getUsuariosSMEMParaReporte()) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			usuariosDto.add(usuarioDto);
		}
		return usuariosDto;
	}
	
	@Override
	public List<UsuarioDto> getUsuariosProveedoresParaReporte() {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarioDao.getUsuariosProveedoresParaReporte()) {
			UsuarioDto usuarioDto = this.manualModelMapper(usuario);
			usuariosDto.add(usuarioDto);
		}
		return usuariosDto;
	}
}
