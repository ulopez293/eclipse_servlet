package mx.inntecsa.smem.enums;

public enum TipoServicio {
	
	SERVICIO_PREVENTIVO(1,"SERVICIO PREVENTIVO"),
	SERVICIO_CORRECTIVO(2,"SERVICIO CORRECTIVO"),
	SERVICIO_PREVENTIVO_CORRECTIVO(3,"SERVICIO PREVENTIVO-CORRECTIVO");
	
	private Integer idTipoServicio;
	private String descripcion;
	
	private TipoServicio(Integer idTipoServicio, String descripcion) {
		this.idTipoServicio = idTipoServicio;
		this.descripcion = descripcion;
	}

	public Integer getIdTipoServicio() {
		return idTipoServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static TipoServicio getTipoServicio(Integer idTipoServicio) {
	    
		for (TipoServicio tipoServicio : TipoServicio.values()) {	     
			if (tipoServicio.getIdTipoServicio().equals(idTipoServicio)) 
				return tipoServicio;
	    }
		
		return SERVICIO_PREVENTIVO_CORRECTIVO;
	}

}
