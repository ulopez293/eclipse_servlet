package mx.inntecsa.smem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.inntecsa.smem.util.VistasEnum;
import mx.inntecsa.smem.dto.BitacoraDto;
import mx.inntecsa.smem.service.BitacoraService;

@Scope("session")
@Component
public class BitacoraBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9022555865787697104L;

	private Logger log = Logger.getLogger(BitacoraBean.class);
	
	private int pagina; // Paginador para la tabla
	private List<BitacoraDto> bitacora; // lista de equipos
	
	//parametros de busqueda
	private String[] tiposMovimiento = {"GUARDO","ACTUALIZO","ELIMINO"};
	private String usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private String movimiento;
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario

	@Autowired
	private LoginBean loginBean;
	
	@Autowired
	private BitacoraService bitacoraService;

	public String inicializar(){
		
		if(loginBean.esUsuarioAdministrador()){
			log.info("Metodo inicializar. Obteniendo la lista de bitacora para usuario administrador");
			usuario="";
			bitacora = bitacoraService.getBitacora(null);
	    }else{
	    	log.info("Metodo inicializar. Obteniendo la lista de bitacora para unidad");
	    	usuario = loginBean.getUsername();
	        bitacora = bitacoraService.getBitacora(loginBean.getUsername());
	    }
		
		this.pagina = 1;
		
		return VistasEnum.BITACORA.getIdVista();
	}
	
	public void buscarRegistros(){
		
		if(usuario == null && fechaInicio == null && fechaFin == null && movimiento.equals("")){
			
			if(loginBean.esUsuarioAdministrador()){
				bitacora = bitacoraService.getBitacora(null);
		    }else{
		        bitacora = bitacoraService.getBitacora(loginBean.getUsername());
		    }			
		}else{
			log.info("Busqueda de bitacora: usuario: " + usuario + " fechaI: " + fechaInicio
					+ " fechaF: " + fechaFin  + " movimiento: " + movimiento);
			bitacora = bitacoraService.getBitacoraPorParametros(usuario, fechaInicio, fechaFin, movimiento);
		}
	}
	
	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public List<BitacoraDto> getBitacora() {
		return bitacora;
	}

	public String[] getTiposMovimiento() {
		return tiposMovimiento;
	}

}
