package mx.inntecsa.smem.enums;

public enum EstatusRequiereServicio {
	
	REQUIERE_SERVICIO(1,"SI"),
	NO_REQUIERE_SERVICIO(0, "NO");
	
	private Integer idEstatusRequiereServicio;
	private String descripcion;
	
	private EstatusRequiereServicio(Integer idEstatusRequiereServicio, String descripcion) {
		this.idEstatusRequiereServicio = idEstatusRequiereServicio;
		this.descripcion = descripcion;
	}
		
	public Integer getIdEstatusRequiereServicio() {
		return idEstatusRequiereServicio;
	}

	public void setIdEstatusRequiereServicio(Integer idEstatusRequiereServicio) {
		this.idEstatusRequiereServicio = idEstatusRequiereServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static EstatusRequiereServicio getEstatusRequiereServicio(Integer idEstatusRequiereServicio) {
	    
		for (EstatusRequiereServicio estatusRequiereServicio : EstatusRequiereServicio.values()) {	     
			if (estatusRequiereServicio.getIdEstatusRequiereServicio().equals(idEstatusRequiereServicio)) 
				return estatusRequiereServicio;
	    }
		
		return NO_REQUIERE_SERVICIO;
	}


}
