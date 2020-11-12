package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.EntidadDao;
import mx.inntecsa.smem.dto.EntidadDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Entidad;
import mx.inntecsa.smem.service.EntidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entidadService")
public class EntidadServiceImpl implements EntidadService {
	
	@Autowired
	private EntidadDao entidadDao;

	@Override
	public List<EntidadDto> getEntidades() {
		List<EntidadDto> entidadesDto = new ArrayList<EntidadDto>(); //Lista de Entidades(DTO)

		for (Entidad entidad : entidadDao.getEntidades()) {
			EntidadDto entidadDto = this.manualModelMapper(entidad);
			entidadesDto.add(entidadDto);
		}
		
		return entidadesDto;
	}

	@Override
	public List<EntidadDto> getEntidadesPorEstatus(Estatus estatus) {
		List<EntidadDto> entidadesDto = new ArrayList<EntidadDto>(); //Lista de Entidades(DTO)

		for (Entidad entidad : entidadDao.getEntidadesPorEstatus(estatus)) {
			EntidadDto entidadDto = this.manualModelMapper(entidad);
			entidadesDto.add(entidadDto);
		}
		
		return entidadesDto;
	}
	
	private EntidadDto manualModelMapper(Entidad entidad) {
		EntidadDto entidadDto = new EntidadDto();
		entidadDto.setIdEntidad(entidad.getIdEntidad());			
		entidadDto.setEntidad(entidad.getEntidad());
		entidadDto.setBaja(entidad.getBaja());
		entidadDto.setFechaBaja(entidad.getFechaBaja());
		entidadDto.setFechaModificacion(entidad.getFechaModificacion());
		entidadDto.setFechaRegistro(entidad.getFechaRegistro());
		return entidadDto;
	}

}
