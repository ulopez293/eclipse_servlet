package mx.inntecsa.smem.util;

public enum Columna {
	
	ID_CONTRATO(0,"ID Contrato"),
	ID_UNIVERSO(1, "ID Universo"),
	PERIODO(2,"Periodo"),
	CONSECUTIVO(3, "Consecutivo"),
	INICIO_PERIODO(4, "Inicio del Periodo"),
	FIN_PERIODO(5, "Fin del Periodo"),
	MES_ENERO(6, "Enero"),
	MES_FEBRERO(7, "Febrero"),
	MES_MARZO(8, "Marzo"),
	MES_ABRIL(9, "Abril"),
	MES_MAYO(10, "Mayo"),
	MES_JUNIO(11, "Junio"),
	MES_JULIO(12, "Julio"),
	MES_AGOSTO(13, "Agosto"),
	MES_SEPTIEMBRE(14, "Septiembre"),
	MES_OCTUBRE(15, "Octubre"),
	MES_NOVIEMBRE(16, "Noviembre"),
	MES_DICIEMBRE(17, "Diciembre"),
	VIGENCIA(18, "Vigencia"),
	NO_VALIDA(315,"Columna no valida");
	
	private Integer idColumna; 
	private String descripcion;
	
	

	private Columna(Integer idColumna, String descripcion) {
		this.idColumna = idColumna;
		this.descripcion = descripcion;
	}

	public Integer getIdColumna() {
		return idColumna;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static Columna getColumna(Integer idColumna) {
	    
		for (Columna columna : Columna.values()) {	     
			if (columna.getIdColumna()==idColumna) { 
				return columna;
			}	
	    }
		
		return NO_VALIDA;
	}
	

}
