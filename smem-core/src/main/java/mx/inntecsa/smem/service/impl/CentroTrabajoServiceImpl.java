package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.CentroTrabajoDao;
import mx.inntecsa.smem.dao.UsuarioDao;
import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.CentroTrabajo;
import mx.inntecsa.smem.pojo.Usuario;
import mx.inntecsa.smem.service.CentroTrabajoService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("centroTrabajoService")
@SuppressWarnings("unchecked")
public class CentroTrabajoServiceImpl implements CentroTrabajoService {
	
	private Logger log = Logger.getLogger(CentroTrabajoService.class);
	
	@Autowired
	private CentroTrabajoDao centroTrabajoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public List<CentroTrabajoDto> getCentrosTrabajo() {
		List<CentroTrabajoDto> centroTrabajoesDto = new ArrayList<CentroTrabajoDto>(); //Lista de CentrosTrabajo(DTO)

		for (CentroTrabajo centroTrabajo : centroTrabajoDao.getCentrosTrabajo()) {
			CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo);
			centroTrabajoesDto.add(centroTrabajoDto);
		}
		
		return centroTrabajoesDto;
	}

	@Override
	public List<CentroTrabajoDto> getCentrosTrabajoPorEstatus(Estatus estatus){
		List<CentroTrabajoDto> centroTrabajoesDto = new ArrayList<CentroTrabajoDto>(); //Lista de CentrosTrabajo(DTO)
		List<CentroTrabajo> centrosTrabajoDAO = centroTrabajoDao.getCentrosTrabajoPorEstatus(estatus);
		
		if(centrosTrabajoDAO !=null && centrosTrabajoDAO.size() > 0){
			log.info("CentroTrabajoService - consultando centros de trabajo: " + centrosTrabajoDAO.size());
			for (CentroTrabajo centroTrabajo : centrosTrabajoDAO) {
				CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo);
				centroTrabajoesDto.add(centroTrabajoDto);
			}
		}
		
		return centroTrabajoesDto;
	}
	
	@Override
	public List<CentroTrabajoDto> getCentrosTrabajoPoridUnidadRegional(Integer idUnidadRegional) {
		List<CentroTrabajoDto> centroTrabajoesDto = new ArrayList<CentroTrabajoDto>(); //Lista de CentrosTrabajo(DTO)

		for (CentroTrabajo centroTrabajo : centroTrabajoDao.getCentrosTrabajoPoridUnidadRegional(idUnidadRegional)) {
			CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo); 			
			centroTrabajoesDto.add(centroTrabajoDto);
		}
		
		return centroTrabajoesDto;
	}
	
	@Override
	public CentroTrabajoDto getCentroTrabajoPorDescripcion(String descripcion) {
		CentroTrabajo centroTrabajo = centroTrabajoDao.getCentroTrabajoPorDescripcion(descripcion);
		CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo);
		return centroTrabajoDto;
	}
	
	@Override
	public CentroTrabajoDto getCentroTrabajo(Integer idCentroTrabajo) {
		CentroTrabajo centroTrabajo = centroTrabajoDao.getCentroTrabajo(idCentroTrabajo);
		CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo); 			
		return centroTrabajoDto;
	}

	@Override
	public CentroTrabajoDto getCentroTrabajoPorURCT(String urct) {
		CentroTrabajo centroTrabajo = centroTrabajoDao.getCentroTrabajoPorURCT(urct);
		CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo); 			
		return centroTrabajoDto;
	}
	
	private CentroTrabajoDto manualModelMapper(CentroTrabajo centroTrabajo) {
		CentroTrabajoDto centroTrabajoDto = new CentroTrabajoDto(); 			
		centroTrabajoDto.setIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
		centroTrabajoDto.setDescripcion(centroTrabajo.getDescripcion());
		centroTrabajoDto.setTelefono(centroTrabajo.getTelefono());
		centroTrabajoDto.setDireccion(centroTrabajo.getDireccion());
		centroTrabajoDto.setResponsable(centroTrabajo.getResponsable());
		centroTrabajoDto.setUrct(centroTrabajo.getUrct());
		centroTrabajoDto.setEstatus(centroTrabajo.getEstatus());
		centroTrabajoDto.setResidenteCargo(centroTrabajo.getResidenteCargo());
		centroTrabajoDto.setResidenteCorreo(centroTrabajo.getResidenteCorreo());
		centroTrabajoDto.setResidenteJefe(centroTrabajo.getResidenteJefe());
		centroTrabajoDto.setResidenteCorreoJefe(centroTrabajo.getResidenteCorreoJefe());
		centroTrabajoDto.setResidenteTelefonoJefe(centroTrabajo.getResidenteTelefonoJefe());
		centroTrabajoDto.setResidenteCargoJefe(centroTrabajo.getResidenteCargoJefe());
		centroTrabajoDto.setBaja(centroTrabajo.getBaja());
		centroTrabajoDto.setFechaRegistro(centroTrabajo.getFechaRegistro());
		centroTrabajoDto.setFechaModificacion(centroTrabajo.getFechaModificacion());
		centroTrabajoDto.setFechaBaja(centroTrabajo.getFechaBaja());
		
		UnidadRegionalDto unidadRegionalDto = new UnidadRegionalDto();
		
		if(centroTrabajo.getUnidadRegional() != null) {
			unidadRegionalDto.setIdUnidadRegional(centroTrabajo.getUnidadRegional().getIdUnidadRegional());
			unidadRegionalDto.setUnidadRegional(centroTrabajo.getUnidadRegional().getUnidadRegional());
			
			EntidadDto entidadDto = new EntidadDto();
			
			if(centroTrabajo.getUnidadRegional().getEntidad() != null) {
				entidadDto.setIdEntidad(centroTrabajo.getUnidadRegional().getEntidad().getIdEntidad());
				entidadDto.setEntidad(centroTrabajo.getUnidadRegional().getEntidad().getEntidad());
			}
			
			unidadRegionalDto.setEntidad(entidadDto);
		}
		
		centroTrabajoDto.setUnidadRegional(unidadRegionalDto);
		return centroTrabajoDto;
	}

	
	@Override
	public CentroTrabajoDto guardarCentroTrabajo(CentroTrabajoDto centroTrabajoDto) {
		CentroTrabajo centroTrabajo = new ModelMapper().map(centroTrabajoDto, CentroTrabajo.class);		
		
		try {
			Integer idCentroTrabajo = (Integer) centroTrabajoDao.save(centroTrabajo);
			centroTrabajoDto.setIdCentroTrabajo(idCentroTrabajo);
			return centroTrabajoDto;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return null;
		}
		
	}

	@Override
	public boolean actualizarCentroTrabajo(CentroTrabajoDto centroTrabajoDto) {
		CentroTrabajo centroTrabajo = new ModelMapper().map(centroTrabajoDto, CentroTrabajo.class);		
		
		try {
			//actualizando centro de trabajo
			centroTrabajoDao.update(centroTrabajo);
			
			//actualizando usuario
			Usuario usuario = usuarioDao.getUsuarioPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
			if(usuario != null) {
				usuario.setUsuario(centroTrabajo.getUrct());
				usuario.setNombreUsuario(centroTrabajo.getDescripcion());
				usuarioDao.update(usuario);
			}	
			
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}

	@Override
	public boolean eliminarCentroTrabajo(CentroTrabajoDto centroTrabajoDto) {
		CentroTrabajo centroTrabajo = new ModelMapper().map(centroTrabajoDto, CentroTrabajo.class);		
		
		try {
			//actualizando centro de trabajo
			centroTrabajoDao.update(centroTrabajo);
			
			//actualizando usuario
			Usuario usuario = usuarioDao.getUsuarioPorIdCentroTrabajo(centroTrabajo.getIdCentroTrabajo());
			if(usuario != null) {
				usuario.setFechaBaja(new Date());
				usuario.setBaja(Estatus.INACTIVO);
				usuarioDao.update(usuario);
			}
			return true;
			
		} catch(Exception e) {
			log.error(">>>Error ", e);
			return false;
		}
	}
	
	@Override
	public List<CentroTrabajoDto> getCentrosTrabajoActivosPorURCT(String urct) {
		List<CentroTrabajoDto> centrosTrabajoDto = new ArrayList<CentroTrabajoDto>();
		for (CentroTrabajo centroTrabajo : centroTrabajoDao.getCentrosTrabajoActivosPorURCT(urct)) {
			CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo);
			centrosTrabajoDto.add(centroTrabajoDto);
		}
		return centrosTrabajoDto;
	}
	
	@Override
	public List<CentroTrabajoDto> getCentrosTrabajoActivosPorURCT(String urct,Integer idCentroTrabajo) {
		List<CentroTrabajoDto> centrosTrabajoDto = new ArrayList<CentroTrabajoDto>();
		for (CentroTrabajo centroTrabajo : centroTrabajoDao.getCentrosTrabajoActivosPorURCT(urct,idCentroTrabajo)) {
			CentroTrabajoDto centroTrabajoDto = this.manualModelMapper(centroTrabajo);
			centrosTrabajoDto.add(centroTrabajoDto);
		}
		return centrosTrabajoDto;
	}
}
