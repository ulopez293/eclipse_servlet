package mx.inntecsa.smem.enums;

public enum Rol {
	
	ADMINISTRADOR(1, "ROLE_ADMINISTRADOR", "Administrador"),
	ADMINSTRADOR_GENERAL(2, "ROLE_ADMINISTRADOR_GENERAL", "Administrador General"),
	UNIDAD_TRABAJO(3,"ROLE_UNIDAD", "Unidad de Trabajo"),
	PROVEEDOR(4,"ROLE_PROVEEDOR", "Proveedor");
	
	private int idRol;
	private String perfilRol;
	private String descripcionRol;
	
	private Rol(String perfilRol) {
		this.perfilRol = perfilRol;
	}
	
	private Rol(int idRol, String perfilRol, String descripcionRol) {
		this.idRol = idRol;
		this.perfilRol = perfilRol;
		this.descripcionRol = descripcionRol;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getPerfilRol() {
		return perfilRol;
	}

	public void setPerfilRol(String perfilRol) {
		this.perfilRol = perfilRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}	
	
	public static Rol getRol(String rol) {
	    
		for (Rol rolEnum : Rol.values()) {	     
			if (rolEnum.getPerfilRol().equals(rol)) return rolEnum;
	    }
		return ADMINISTRADOR;
	}

}
