package mx.inntecsa.smem.enums;

public enum EstatusServicio {
	
	INICIADO(1, "INICIADO", "Se acaba de generar"),
	ENPROGRAMACION(2, "PROGRAMACION VISITA", "El proveedor agenda visita"),
	ENPROCESO(3, "EN PROCESO", "En proceso de visita del proveedor"),
	CANCELADO(4, "CANCELADO", "Servicio correctivo cancelado"),
	CERRADO_NO_EXITOSO(5, "CERRADO NO EXITOSO", "Servicio no exitoso"),
	CERRADO_EXITOSO(6, "CERRADO", "Servicio cerrado con exito"),
	SEGUIMIENTO_SUPERVISOR(7, "SEGUIMIENTO SUPERVISOR", "Servicio en seguimiento del supervisor");
	
	private Integer idEstatus;
	private String nombre;
	private String descripcion;
	
	private EstatusServicio(Integer idEstatus, String nombre, String descripcion) {
		this.idEstatus = idEstatus;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Integer getIdEstatus() {
		return idEstatus;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static EstatusServicio getEstatusServicio(Integer idEstatus) {
	    
		for (EstatusServicio estatusServicio : EstatusServicio.values()) {	     
			if (estatusServicio.getIdEstatus().equals(idEstatus)) 
				return estatusServicio;
	    }
		
		return INICIADO;
	}

}
