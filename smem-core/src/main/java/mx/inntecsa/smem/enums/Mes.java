package mx.inntecsa.smem.enums;

public enum Mes {
	
	ENERO(1,"ENERO"),
	FEBRERO(2,"FEBRERO"),
	MARZO(3,"MARZO"),
	ABRIL(4,"ABRIL"),
	MAYO(5,"MAYO"),
	JUNIO(6,"JUNIO"),
	JULIO(7,"JULIO"),
	AGOSTO(8,"AGOSTO"),
	SEPTIEMBRE(9,"SEPTIEMBRE"),
	OBTUBRE(10,"OCTUBRE"),
	NOVIEMBRE(11,"NOVIEMBRE"),
	DICIEMBRE(12,"DICIEMBRE");
	
	private Integer idMes;
	private String descripcion;
	
	private Mes(Integer idMes, String descripcion) {
		this.idMes = idMes;
		this.descripcion = descripcion;
	}

	public Integer getIdMes() {
		return idMes;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static Mes getMes(int idMes) {
	    
		for (Mes mesEnum : Mes.values()) {	     
			if (mesEnum.getIdMes().intValue() == idMes) return mesEnum;
	    }
		
		return ENERO;
	}
	 public static Mes getMesPorDescripcion(String descripcion) {
	     for (Mes mes : Mes.values()) {
	         if (mes.getDescripcion().equals(descripcion)) return mes;
	     }
	  return ENERO;
	 }
	
}
