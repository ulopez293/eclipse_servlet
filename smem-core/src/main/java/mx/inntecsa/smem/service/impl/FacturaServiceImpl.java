package mx.inntecsa.smem.service.impl;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import mx.inntecsa.smem.dao.FacturaDao;
import mx.inntecsa.smem.dto.DetalleFacturaDto;
import mx.inntecsa.smem.dto.FacturaDto;
import mx.inntecsa.smem.enums.Mes;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.FacturaService;
import mx.inntecsa.smem.utils.Fecha;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("facturaService")
public class FacturaServiceImpl implements FacturaService {

	private Logger log = Logger.getLogger(FacturaServiceImpl.class);

	@Autowired
	private FacturaDao facturaDao;

	@Override
	public FacturaDto getServiciosObjectoPago(int idContrato, Mes mes, TipoServicio tipoServicio) {
		
		FacturaDto facturaDto = new FacturaDto();	
		facturaDto.setListaDetalles(facturaDao.getDetallesFactura(idContrato, tipoServicio, mes));
		
		facturaDto = this.FormatoYCalculoObjectosPago(facturaDto, mes);
		
		return facturaDto;
	}
	
	@Override
	public FacturaDto getServiciosConPenalizacion(int idContrato, Mes mes, TipoServicio tipoServicio) {
		
		FacturaDto facturaDto = new FacturaDto();	
		
		List<DetalleFacturaDto> listaDetalles = facturaDao.getDetallesFactura(idContrato, tipoServicio, mes);
		listaDetalles = this.FormatoYCalculoPenasConvencionales(listaDetalles, mes);
		facturaDto.setListaDetalles(listaDetalles);
		return facturaDto;
	}
	
	private FacturaDto FormatoYCalculoObjectosPago(FacturaDto facturaDto, Mes mesBusqueda) {
		//inicializando variables
		int registro = 0;
		Long diasAtraso = 0L;
		Double total = 0.0; 
		Double subtotal = 0.0;
		Double iva = 16.0;
		Double totalPenaConvencional = 0.0;
		Double totalDescuento = 0.0;
		Locale locale = new Locale("es","MX");
		Date fechaConsulta = Fecha.getUltimoDiaDelMes(mesBusqueda.getIdMes());
		
		for(DetalleFacturaDto detalleFactura : facturaDto.getListaDetalles()) {			
			//obteniendo la fechas			
			Date fecha = detalleFactura.getFechareal() != null ? detalleFactura.getFechareal() : detalleFactura.getFechalimite();
			Calendar fechaServicio = Calendar.getInstance();
			Calendar fechaLimite = Calendar.getInstance();
			
			fechaServicio.setTime(fecha);
			fechaLimite.setTime(detalleFactura.getFechalimite());
			Integer idTipoServicio = detalleFactura.getIdtiposervicio();
			
			//Verifcar si la fecha del servicio es mayor a la fecha de consulta y es un preventivo
			if(fechaConsulta.compareTo(fechaServicio.getTime()) < 0 
				&& (idTipoServicio == null || idTipoServicio == TipoServicio.SERVICIO_PREVENTIVO.getIdTipoServicio().intValue())) {						
				diasAtraso = 0L;
							
			} else {				
								
				if(detalleFactura.getFechareal() != null) {
					diasAtraso = Fecha.calcularDiferenciaDiasEntreFechas(fechaLimite.getTime(), fechaServicio.getTime());
					
				} else {
					diasAtraso = Fecha.calcularDiferenciaDiasEntreFechas(fechaLimite.getTime(), fechaConsulta);
				}				
			}
			
			//calculando las penas convencionales	
			double penaConvencional = 0;
			double descuento = 0;	
			if(diasAtraso < 0) {
				diasAtraso = 0L;
			}else{
				if(diasAtraso == 1) {
					penaConvencional = detalleFactura.getCosto() * .025; 
					totalPenaConvencional += penaConvencional;
				}
				
				if(diasAtraso == 2) {
					penaConvencional = detalleFactura.getCosto() * .050; 
					totalPenaConvencional += penaConvencional;
				}
				
				if(diasAtraso == 3) {
					penaConvencional = detalleFactura.getCosto() * .075; 
					totalPenaConvencional += penaConvencional;
				}
				
				if(diasAtraso == 4) {
					penaConvencional = detalleFactura.getCosto() * .1; 
					totalPenaConvencional += penaConvencional;
				}
			
				//se convierte en un descuento total				
				if(diasAtraso > 4) {
					descuento = detalleFactura.getCosto();
					totalDescuento += descuento;
				}
			}
			
			//Almacenando descuentos y penalizaciones
			detalleFactura.setPenalizacion(penaConvencional);
			detalleFactura.setDescuento(descuento);
			
			/*** AJUSTAR CUANDO A UN CORRECTIVO LE ANTECEDE UN PREVENTIVO DEL MISMO EQUIPO ***/
			if(registro != 0 && idTipoServicio != null && idTipoServicio == 2) {
				DetalleFacturaDto objetoPagoAnterior = facturaDto.getListaDetalles().get(registro);
				
				//Si ambos tienen descuentos solo se resta un descuento
				if(objetoPagoAnterior.getDescuento() != 0 && detalleFactura.getDescuento() != 0) { 
					totalDescuento -= descuento;				
				}
				
				//Si ambos tienen penalizacion solo se resta la mayor
				if(objetoPagoAnterior.getPenalizacion() > detalleFactura.getPenalizacion()) {
					totalPenaConvencional -= penaConvencional;					
					
				} else {
					totalPenaConvencional -= objetoPagoAnterior.getPenalizacion();
					
				}
			}
			registro ++;
			/***/
			
			//obteniendo el subtotal
			subtotal += detalleFactura.getCosto();		
			detalleFactura.setDiasatraso(diasAtraso);
			detalleFactura.setTextocosto(NumberFormat.getCurrencyInstance(locale).format(detalleFactura.getCosto()));					
			detalleFactura.setTextopenalizacion(NumberFormat.getCurrencyInstance(locale).format(penaConvencional));
			detalleFactura.setTextodescuento(NumberFormat.getCurrencyInstance(locale).format(descuento));
		}
				
		total = (iva / 100 + 1) * (subtotal - totalDescuento);
		facturaDto.setIva(iva.toString());
		facturaDto.setDescuento(NumberFormat.getCurrencyInstance(locale).format(totalDescuento));		
		facturaDto.setPenalizacion(NumberFormat.getCurrencyInstance(locale).format(totalPenaConvencional));
		facturaDto.setSubtotal(NumberFormat.getCurrencyInstance(locale).format(subtotal));
		facturaDto.setTotal(NumberFormat.getCurrencyInstance(locale).format(total));
		
		log.info(">>>TOTAL " + total);
		log.info(">>>PENALIZACION " + totalPenaConvencional);
		log.info(">>>DESCUENTO " + totalDescuento);
		
		return facturaDto;

	}

	private List<DetalleFacturaDto> FormatoYCalculoPenasConvencionales(List<DetalleFacturaDto> detalleFactura, Mes mesBusqueda) {
		//inicializando variables
		Long diasAtraso = 0L;
		Locale locale = new Locale("es","MX");
		Date fechaConsulta = Fecha.getUltimoDiaDelMes(mesBusqueda.getIdMes());
		List<DetalleFacturaDto> nuevasPenalizaciones = new ArrayList<DetalleFacturaDto>();
		
		for(DetalleFacturaDto penalizacion : detalleFactura) {			
			//obteniendo la fechas			
			Date fecha = penalizacion.getFechareal() != null ? penalizacion.getFechareal() : penalizacion.getFechalimite();
			Calendar fechaServicio = Calendar.getInstance();
			Calendar fechaLimite = Calendar.getInstance();
			
			fechaServicio.setTime(fecha);
			fechaLimite.setTime(penalizacion.getFechalimite());
			Integer idTipoServicio = penalizacion.getIdtiposervicio();
			
			//Verifcar si la fecha del servicio es mayor a la fecha de consulta y es un preventivo
			if(fechaConsulta.compareTo(fechaServicio.getTime()) < 0 
				&& (idTipoServicio == null || idTipoServicio == TipoServicio.SERVICIO_PREVENTIVO.getIdTipoServicio().intValue())) {						
				diasAtraso = 0L;
			} else {	
								
				if(penalizacion.getFechareal() != null) {
					diasAtraso = Fecha.calcularDiferenciaDiasEntreFechas(fechaLimite.getTime(), fechaServicio.getTime());
					
				} else {
					diasAtraso = Fecha.calcularDiferenciaDiasEntreFechas(fechaLimite.getTime(), fechaConsulta);
				}				
			}
			
			//calculando las penas convencionales	
			penalizacion.setPenalizacion(0.0);
			penalizacion.setDescuento(0.0);	
			if(diasAtraso < 0) {
				diasAtraso = 0L;
			}else{
				if(diasAtraso == 1) {
					penalizacion.setPenalizacion(penalizacion.getCosto() * .025); 				
				}
				
				if(diasAtraso == 2) {
					penalizacion.setPenalizacion(penalizacion.getCosto() * .050); 
				}
				
				if(diasAtraso == 3) {
					penalizacion.setPenalizacion(penalizacion.getCosto() * .075); 
				}
				
				if(diasAtraso == 4) {
					penalizacion.setPenalizacion(penalizacion.getCosto() * .1); 
				}
				
				//se convierte en un descuento total
				if(diasAtraso > 4) {
					penalizacion.setDescuento(new Double(penalizacion.getCosto()));
				}
			}
						
			
			//formato de moneda
			penalizacion.setDiasatraso(diasAtraso);
			penalizacion.setTextocosto(NumberFormat.getCurrencyInstance(locale).format(penalizacion.getCosto()));					
			penalizacion.setTextopenalizacion(NumberFormat.getCurrencyInstance(locale).format(penalizacion.getPenalizacion()));
			penalizacion.setTextodescuento(NumberFormat.getCurrencyInstance(locale).format(penalizacion.getDescuento()));
			
			//Agregar aquellas que solo tengan penas convencionales
			if(penalizacion.getPenalizacion()!= 0.0) {
				nuevasPenalizaciones.add(penalizacion);
			}
		}
				
		return nuevasPenalizaciones;

	}
}
