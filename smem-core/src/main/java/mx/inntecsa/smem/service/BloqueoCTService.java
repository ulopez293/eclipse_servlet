package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.CentroTrabajoBloqueadoDto;



public interface BloqueoCTService {
	
	public List<Integer> getCentrosTrabajoBloqueados();
	public List<CentroTrabajoBloqueadoDto> getConsultaCtblq(int entidad, int unidadRegional);
	public void actualizarEstatusCT(List<Integer> centrosTrabajo, boolean bloqueo);
	
}
