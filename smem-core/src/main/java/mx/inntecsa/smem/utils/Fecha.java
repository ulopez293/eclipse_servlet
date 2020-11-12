package mx.inntecsa.smem.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Fecha {
	
	public static Date agregarDiaFecha(Date fecha) {
		
		if(fecha != null) {	
			Calendar calendario = new GregorianCalendar();
			calendario.setTimeInMillis(fecha.getTime());
			calendario.add(Calendar.DATE, 1);
		    Date nuevaFecha = new Date(calendario.getTimeInMillis());
			return nuevaFecha;
		}
		return null;
	}	

	public static Date agregarDiasAFecha(Date fecha, int dias) {
		
		if(fecha != null) {	
			Calendar calendario = new GregorianCalendar();
			calendario.setTimeInMillis(fecha.getTime());
			calendario.add(Calendar.DATE, dias);
		    Date nuevaFecha = new Date(calendario.getTimeInMillis());
			return nuevaFecha;
		}
		return null;
	}	
	
	public static long calcularDiferenciaDiasEntreFechas(Date fechaInicio, Date fechaFin){
		  // Crear 2 instancias de Calendar
		  Calendar cal1 = Calendar.getInstance();
		  Calendar cal2 = Calendar.getInstance();
		 
		  cal1.setTime(fechaInicio);
		  cal2.setTime(fechaFin);
		
		  //conseguir la representacion de la fecha en milisegundos
		  long milisegundosDate1 = cal1.getTimeInMillis();
		  long milisegundosDate2 = cal2.getTimeInMillis();
		
		  // calcular la diferencia en milisengundos
		  long diferencia = milisegundosDate2 - milisegundosDate1;
		  long diferenciaEnDias = diferencia / (24 * 60 * 60 * 1000);
		  
		  return diferenciaEnDias;
	}
	
	@SuppressWarnings("static-access")
	public static String calculaAniosFechas(Date fechaInicio, Date fechaFin) {
		String anios = "";
		Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();	 
	    cal1.setTime(fechaInicio);
	    cal2.setTime(fechaFin);
		
	    if(cal1.get(cal1.YEAR) <= cal2.get(cal2.YEAR)) {
			while(cal1.get(cal1.YEAR) <= cal2.get(cal2.YEAR)) {
				anios += cal1.get(cal1.YEAR) + "-";
				cal1.add(Calendar.YEAR, 1);  
			}	  
			anios = anios.substring(0, anios.length()-1);
	    }
	    
		return anios;
	}

	public static Date getPrimerDiaDelMes(int mes) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes -1);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) ,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}

	public static Date getUltimoDiaDelMes(int mes) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes -1);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) , cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}

}
