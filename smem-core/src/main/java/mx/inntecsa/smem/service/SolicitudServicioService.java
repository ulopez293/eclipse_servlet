package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.enums.Estatus;



public interface SolicitudServicioService {
	
	public List<SolicitudServicioDto> getSolicitudesServicios();
	public List<SolicitudServicioDto> getSolicitudesServiciosByEstatus(Estatus estatus);
}
