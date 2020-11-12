package mx.inntecsa.smem.tareasprogramadas;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import mx.inntecsa.smem.dto.ConfiguracionDto;
import mx.inntecsa.smem.dto.ContratoDetalleDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.dto.SolicitudServicioDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.ConfiguracionService;
import mx.inntecsa.smem.service.ContratoDetalleService;
import mx.inntecsa.smem.service.ProgramacionServicioService;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgramacionServiciosPreventivosTask {

	private Logger log = Logger.getLogger(ProgramacionServiciosPreventivosTask.class);
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;
	
	@Autowired
	private ContratoDetalleService contratoDetalleService;

	@Autowired
	private ConfiguracionService configuracionService;

	
	public void exec(){
		
		ConfiguracionDto configuracion = configuracionService.getConfiguracion();
		
		Date fechaActual = new Date();
		fechaActual = Fecha.agregarDiasAFecha(fechaActual, configuracion.getDiasPreventivos());
		
		log.info("En el metodo task, dias configuracion preventivos: " + configuracion.getDiasPreventivos());
		
		try{
			
			List<ContratoDetalleDto> contratosDetalles = contratoDetalleService.getContratosDetallesPorFechaInicio(fechaActual);
			log.info("ContratosDetalle... " + contratosDetalles.size());
			
			for(ContratoDetalleDto detalle: contratosDetalles){
				log.info("Se generara el preventivo para el detalle: " + detalle.getIdContratoDetalle() + " fechaInicio: " + detalle.getInicioPeriodo()
						 + " fechaFin: " + detalle.getFinPeriodo());
	
				/*long idUniverso = detalle.getUniverso().getIdUniverso();
				String idUniversoS="";
				if(idUniverso < 10){
					idUniversoS = "0" + idUniverso;
				}else{
					idUniversoS = ""+idUniverso;
				}
				String folio = "PRE" + "-"+ detalle.getUniverso().getCentroTrabajo().getUrct() + "-"+ idUniversoS;*/
				int anioActual=GregorianCalendar.getInstance().get(Calendar.YEAR);
				String ultimoConsecutivo=programacionServicioService.getUltimoConsecutivoFolio(anioActual);
				log.info("***Ultimo Consecutivo: "+ultimoConsecutivo+" del anio: "+anioActual);
				Integer consecutivo=Integer.parseInt(ultimoConsecutivo)+1;
				String folio = new String();
				//Estructura nueva del Folio: PRE-000000(Consecutivo 6 digitos)/0000(A�o 4 digitos)
				if(consecutivo<10){
					folio="PRE"+"-"+"00000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<100){
					folio="PRE"+"-"+"0000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<1000){
					folio="PRE"+"-"+"000"+consecutivo+"/"+anioActual;
				}else if(consecutivo<10000){
					folio="PRE"+"-"+"00"+consecutivo+"/"+anioActual;
				}else if(consecutivo<100000){
					folio="PRE"+"-"+"0"+consecutivo+"/"+anioActual;
				}else if(consecutivo<1000000){
					folio="PRE"+"-"+consecutivo+"/"+anioActual;
				}else{
					folio=null;
				}
				if(folio!=null && !folio.equals("")){
					ProgramacionServicioDto programacionServicioDto = new ProgramacionServicioDto(); 
					programacionServicioDto.setTipoServicio(TipoServicio.SERVICIO_PREVENTIVO);
					programacionServicioDto.setFolio(folio);
					programacionServicioDto.setEstatus(EstatusServicio.INICIADO);
					programacionServicioDto.setFechaRegistro(new Date());
					programacionServicioDto.setNprogramacion(0);
					programacionServicioDto.setBaja(Estatus.ACTIVO);
					
					SolicitudServicioDto solicitudServicioDto = new SolicitudServicioDto();
					solicitudServicioDto.setContratoDetalle(detalle);
					solicitudServicioDto.setFechaInicio(detalle.getInicioPeriodo());
					solicitudServicioDto.setFechaFin(detalle.getFinPeriodo());
					solicitudServicioDto.setMotivoSolicitud("Creacion de servicio preventivo tarea programada.");
					solicitudServicioDto.setFechaRegistro(new Date());
					solicitudServicioDto.setBaja(Estatus.ACTIVO);
	
					programacionServicioDto.setSolicitudServicio(solicitudServicioDto);
					log.info("Guardando el preventivo con folio: " + folio);
					programacionServicioService.guardarProgramacionServicioPreventivo(programacionServicioDto);
				}else{
					log.info("Se han asignado el numero maximo de folios posibles (999999). Por favor consultelo con su administrador.");
					break;
				}
			}
		}catch(Exception e){
			log.error(">>>Error",e);
		}
	}
}
