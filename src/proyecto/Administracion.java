package proyecto;

public class Administracion {

	public String funcion;
		
	public Administracion(String funcion) {

		this.funcion = funcion;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	
	public void generar_Turno() {
		Turno.generar_Turno(getFuncion());
	}
	
	public void generar_Informe() {
		
	}
		
} 
