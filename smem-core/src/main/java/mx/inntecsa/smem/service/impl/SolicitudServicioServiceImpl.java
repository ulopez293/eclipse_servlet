package mx.inntecsa.smem.service.impl;


import java.util.List;

import mx.inntecsa.smem.dao.SolicitudServicioDao;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.service.SolicitudServicioService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("solicitudServicioService")
public class SolicitudServicioServiceImpl implements SolicitudServicioService{

	private static Log log = LogFactory.getLog(SolicitudServicioServiceImpl.class);
	
	@Autowired
	private SolicitudServicioDao solicitudServicioDao;
	
	@Override
	public List<SolicitudServicioDto> getSolicitudesServicios() {
		log.info("");
		return null;
	}

	@Override
	public List<SolicitudServicioDto> getSolicitudesServiciosByEstatus(
			Estatus estatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
