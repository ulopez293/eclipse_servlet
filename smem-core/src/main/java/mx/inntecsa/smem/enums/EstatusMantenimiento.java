package mx.inntecsa.smem.enums;

public enum EstatusMantenimiento {
	
	EXITOSO(1, "FUNCIONANDO"),
	NO_EXITOSO(0, " NO FUNCIONANDO");
	
	private Integer idEstatus;
	private String descripcion;
	
	private EstatusMantenimiento(Integer idEstatus, String descripcion) {
		this.idEstatus = idEstatus;
		this.descripcion = descripcion;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static EstatusMantenimiento getEstatusMantenimiento(Integer idEstatus) {
	    
		for (EstatusMantenimiento estatusManto : EstatusMantenimiento.values()) {	     
			if (estatusManto.getIdEstatus().equals(idEstatus)) 
				return estatusManto;
	    }
		
		return NO_EXITOSO;
	}

}
