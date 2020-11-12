package mx.inntecsa.smem.enums;

public enum EstatusCentroTrabajo {
	
	DESBLOQUEADO(0, "Bloqueado"),
	BLOQUEADO(1, "Desbloqueado");
	
	private Integer idEstatus;
	private String descripcion;
	
	private EstatusCentroTrabajo(Integer idEstatus, String descripcion) {
		this.idEstatus = idEstatus;
		this.descripcion = descripcion;
	}
	
	public Integer getIdEstatus() {
		return idEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static EstatusCentroTrabajo getEstatusCentroTrabajo(Integer idEstatus) {
	    
		for (EstatusCentroTrabajo estatusCentroTrabajo : EstatusCentroTrabajo.values()) {	     
			if (estatusCentroTrabajo.getIdEstatus().equals(idEstatus)) 
				return estatusCentroTrabajo;
	    }
		
		return DESBLOQUEADO;
	}


}
