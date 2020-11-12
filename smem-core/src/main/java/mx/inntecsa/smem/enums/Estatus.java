package mx.inntecsa.smem.enums;

public enum Estatus {

	ACTIVO(false, "Activo"), 
	INACTIVO(true, "Inactivo");

	private Boolean idEstatus;
	private String descripcion;
	
	private Estatus(Boolean idEstatus, String descripcion) {
		this.idEstatus = idEstatus;
		this.descripcion = descripcion;
	}

	public Boolean getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Boolean idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static Estatus getEstatus(Boolean idEstatus) {
		if (idEstatus) return INACTIVO;
		return ACTIVO;
	}

}
