package mx.inntecsa.smem;

public class Empleado {

    private int id;
    
    private String nombre;
    
    private String departamento;
   
    private long salario;

	public Empleado(int id, String nombre, String departamento, long salario) {
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public long getSalario() {
		return salario;
	}

	public void setSalario(long salario) {
		this.salario = salario;
	}

	
}
