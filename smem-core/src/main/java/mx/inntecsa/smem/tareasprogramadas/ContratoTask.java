package mx.inntecsa.smem.tareasprogramadas;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.ProgramacionServicioDto;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.service.ContratoService;
import mx.inntecsa.smem.service.ProgramacionServicioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContratoTask {

	private Logger log = Logger.getLogger(ContratoTask.class);
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private ProgramacionServicioService programacionServicioService;
	
	
	public void exec() {
		try {
			log.info(">>>Task de contratos, se buscaran los contratos vigentes : ");
			List<ContratoDto> contratosDto = contratoService.getContratoPorEstatusContrato(EstatusContrato.VIGENTE);
			
			for(ContratoDto contratoDto : contratosDto) {
				System.out.println(">>>Fecha del contrato " + contratoDto.getVigenciaFinContrato());
				System.out.println(">>>Fecha hoy " + new Date());
				 
				if(new Date().after(contratoDto.getVigenciaFinContrato())) {
					//buscar� la programacionServicio
					ProgramacionServicioDto programacionServicioDto = programacionServicioService
						.getProgramacionServicioPorIdContratoDetalle(contratoDto.getIdContrato());
					log.info(">>>Encontrando un contrato " + contratoDto.getIdContrato());
					
					if(programacionServicioDto != null && !programacionServicioDto.getEstatus().equals(EstatusServicio.CANCELADO)
						&& programacionServicioDto.getEstatus().equals(EstatusServicio.CERRADO_EXITOSO)) {
						log.info(">>>Encontre una Programacion del servivicio " + programacionServicioDto.getIdProgramacionServicio());
						programacionServicioDto.setEstatus(EstatusServicio.SEGUIMIENTO_SUPERVISOR);
						programacionServicioDto.setFechaModificacion(new Date());
						programacionServicioService.actualizarProgramacionServicio(programacionServicioDto);	
					}
					contratoDto.setEstatus(EstatusContrato.NO_VIGENTE);
					contratoDto.setFechaModificacion(new Date());
					contratoService.actualizarContrato(contratoDto);
				}			
			}
			
		} catch(Exception e) {
			log.info(">>>Error ", e);
		}
	}
	
	public static void main(String args[]) throws ParseException {
		 SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		 Date date = f.parse("25-12-2010");
		 System.out.println("fecha " + date);
		 System.out.println("fecha hoy" + new Date());
		 System.out.println("Es antes? " + (new Date().after(date)));		
	}
	
}
