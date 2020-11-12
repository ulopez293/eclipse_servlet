package mx.inntecsa.smem.enums;

public enum Funcionalidad {
	
	FUNCIONANDO(1, false, "FUNCIONANDO"),
	NO_FUNCIONANDO(2, false ," NO FUNCIONANDO"),
	BAJA(3,true, "BAJA");
//	PROXIMA_BAJA(4,true, "PROXIMA BAJA"),
//	TRANSFERENCIA(5,true, "TRANSFERENCIA"),
//	OTRA(6,true, "OTRA"),
//	A_DISPOSICION(7,true, "A DISPOSICION"),
//	BAJA_POR_DUPLICIDAD(9,true, "BAJA POR DUPLICIDAD");
	
	private Integer idFuncionalidad;
	private boolean esNegocio;
	private String descripcion;
	
	private Funcionalidad(Integer idFuncionalidad,boolean esNegocio, String descripcion) {
		this.idFuncionalidad = idFuncionalidad;
		this.esNegocio = esNegocio;
		this.descripcion = descripcion;
	}
	
	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public boolean getEsNegocio() {
		return esNegocio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static Funcionalidad getFuncionalidad(Integer idFuncionalidad) {
	    
		for (Funcionalidad funcionalidad : Funcionalidad.values()) {	     
			if (funcionalidad.getIdFuncionalidad().equals(idFuncionalidad)) 
				return funcionalidad;
	    }
		
		return FUNCIONANDO;
	}

}
