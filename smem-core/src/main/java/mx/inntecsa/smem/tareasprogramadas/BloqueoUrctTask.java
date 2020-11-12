package mx.inntecsa.smem.tareasprogramadas;


import java.util.List;


import mx.inntecsa.smem.service.BloqueoCTService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BloqueoUrctTask {
	
	private Logger log = Logger.getLogger(BloqueoUrctTask.class);
	
	@Autowired
	private BloqueoCTService bloqueoCTService;
	
	public void exec() {
		log.info("Inicia cron bloqueos");
		List<Integer> ctBloqueados = bloqueoCTService.getCentrosTrabajoBloqueados();
		log.info("centros bloqueados: " + ctBloqueados.size());
		if (!ctBloqueados.isEmpty()){
			bloqueoCTService.actualizarEstatusCT(ctBloqueados, true);
		}
	}

}
