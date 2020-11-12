package mx.inntecsa.smem.service.impl;


import java.util.List;

import mx.inntecsa.smem.dao.BloqueoCTDao;
import mx.inntecsa.smem.dto.CentroTrabajoBloqueadoDto;
import mx.inntecsa.smem.service.BloqueoCTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("bloqueoCTService")
public class BloqueoCTServiceImpl implements BloqueoCTService {
	
	@Autowired
	private BloqueoCTDao bloqueoCTDao;

	@Override
	public void actualizarEstatusCT(List<Integer> centrosTrabajo, boolean bloqueo) {
		bloqueoCTDao.actualizarEstatusCT(centrosTrabajo, bloqueo);
	}

	@Override
	public List<Integer> getCentrosTrabajoBloqueados() {
		return bloqueoCTDao.getCentrosTrabajoBloqueados();
	}

	@Override
	public List<CentroTrabajoBloqueadoDto> getConsultaCtblq(int entidad,
			int unidadRegional) {
		return bloqueoCTDao.getConsultaCtblq(entidad, unidadRegional);
	}

}
