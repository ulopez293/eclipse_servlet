package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.CentroTrabajoDto;
import mx.inntecsa.smem.enums.Estatus;



public interface CentroTrabajoService {
	
	public List<CentroTrabajoDto> getCentrosTrabajo();
	public List<CentroTrabajoDto> getCentrosTrabajoPorEstatus(Estatus estatus);
	public CentroTrabajoDto getCentroTrabajoPorDescripcion(String descripcion);
	public List<CentroTrabajoDto> getCentrosTrabajoPoridUnidadRegional(Integer idUnidadRegional);
	public CentroTrabajoDto getCentroTrabajo(Integer idCentroTrabajo);
	public CentroTrabajoDto getCentroTrabajoPorURCT(String urct);
	public CentroTrabajoDto guardarCentroTrabajo(CentroTrabajoDto centroTrabajoDto);
	public boolean actualizarCentroTrabajo(CentroTrabajoDto centroTrabajoDto);
	public boolean eliminarCentroTrabajo(CentroTrabajoDto centroTrabajoDto);
	public List<CentroTrabajoDto> getCentrosTrabajoActivosPorURCT(String urct);
	public List<CentroTrabajoDto> getCentrosTrabajoActivosPorURCT(String urct,Integer idCentroTrabajo);
}
