package mx.inntecsa.smem.enums;

public enum TipoUsuario {
	
	ISSSTE(1, "Usuario ISSSTE"),
	PROVEEDOR(2, "Usuario Proveedor"),;
	
	private Integer idTipoUsuario;
	private String descripcion;
	
	private TipoUsuario(Integer idTipoUsuario, String descripcion) {
		this.idTipoUsuario = idTipoUsuario;
		this.descripcion = descripcion;
	}

	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public static TipoUsuario getTipoUsuario(Integer idTipoUsuario) {
	    
		for (TipoUsuario tipoUsuarioEnum : TipoUsuario.values()) {	     
			if (tipoUsuarioEnum.getIdTipoUsuario().intValue() == idTipoUsuario.intValue()) return tipoUsuarioEnum;
	    }
		return ISSSTE;
	}

}
