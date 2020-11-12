package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.UnidadRegionalDao;
import mx.inntecsa.smem.dto.UnidadRegionalDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.UnidadRegional;
import mx.inntecsa.smem.service.UnidadRegionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("unidadRegionalService")
public class UnidadRegionalServiceImpl implements UnidadRegionalService {
	
	@Autowired
	private UnidadRegionalDao unidadRegionalDao;

	@Override
	public List<UnidadRegionalDto> getUnidadesRegionales() {
		List<UnidadRegionalDto> unidadRegionalesDto = new ArrayList<UnidadRegionalDto>(); //Lista de UnidadesRegionales(DTO)

		for (UnidadRegional unidadRegional : unidadRegionalDao.getUnidadesRegionales()) {
			UnidadRegionalDto unidadRegionalDto =this.manualModelMapper(unidadRegional);
			unidadRegionalesDto.add(unidadRegionalDto);
		}
		
		return unidadRegionalesDto;
	}

	@Override
	public List<UnidadRegionalDto> getUnidadesRegionalesByEstatus(Estatus estatus) {
		List<UnidadRegionalDto> unidadRegionalesDto = new ArrayList<UnidadRegionalDto>(); //Lista de UnidadesRegionales(DTO)

		for (UnidadRegional unidadRegional : unidadRegionalDao.getUnidadesRegionalesPorEstatus(estatus)) {
			UnidadRegionalDto unidadRegionalDto = this.manualModelMapper(unidadRegional);
			unidadRegionalesDto.add(unidadRegionalDto);
		}
		
		return unidadRegionalesDto;
	}

	@Override
	public List<UnidadRegionalDto> getUnidadesRegionalesPorIdEntidad(
		Integer idEntidad) {
		List<UnidadRegionalDto> unidadRegionalesDto = new ArrayList<UnidadRegionalDto>(); //Lista de UnidadesRegionales(DTO)

		for (UnidadRegional unidadRegional : unidadRegionalDao.getUnidadesRegionalesPorIdEntidad(idEntidad)) {
			UnidadRegionalDto unidadRegionalDto = this.manualModelMapper(unidadRegional);
			unidadRegionalesDto.add(unidadRegionalDto);
		}
		
		return unidadRegionalesDto;
	}
	
	private UnidadRegionalDto manualModelMapper(UnidadRegional unidadRegional) {
		UnidadRegionalDto unidadRegionalDto = new UnidadRegionalDto();
		unidadRegionalDto.setUnidadRegional(unidadRegional.getUnidadRegional());
		unidadRegionalDto.setBaja(unidadRegional.getBaja());
		unidadRegionalDto.setFechaBaja(unidadRegional.getFechaBaja());
		unidadRegionalDto.setFechaModificacion(unidadRegional.getFechaModificacion());
		unidadRegionalDto.setFechaRegistro(unidadRegional.getFechaRegistro());
		unidadRegionalDto.setIdUnidadRegional(unidadRegional.getIdUnidadRegional());
		return unidadRegionalDto;
	}

}
