package mx.inntecsa.smem.enums;

public enum EstatusContrato {
	
	VIGENTE(1,"VIGENTE"),
	NO_VIGENTE(2, "NO VIGENTE");
	
	private Integer idEstatus;
	private String descripcion;
	
	private EstatusContrato(Integer idEstatus, String descripcion) {
		this.idEstatus = idEstatus;
		this.descripcion = descripcion;
	}
	
	public Integer getIdEstatus() {
		return idEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static EstatusContrato getEstatusContrato(Integer idEstatus) {
	    
		for (EstatusContrato estatusContrato : EstatusContrato.values()) {	     
			if (estatusContrato.getIdEstatus().equals(idEstatus)) 
				return estatusContrato;
	    }
		
		return VIGENTE;
	}

}
