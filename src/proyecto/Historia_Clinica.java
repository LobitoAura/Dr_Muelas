package proyecto;

public class Historia_Clinica extends Paciente{
	
	private String tratamiento;

	public Historia_Clinica(String nombre, String apellido, int dni, int edad, String domicilio, String telefono,
			String genero, String email, String usuario, String clave, boolean emergencia, String tratamiento) {
		super(nombre, apellido, dni, edad, domicilio, telefono, genero, email, usuario, clave, emergencia);
		this.tratamiento = tratamiento;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	public void generar_Informe() {
		System.out.println("Nombre: "+getNombre());
	}

}
