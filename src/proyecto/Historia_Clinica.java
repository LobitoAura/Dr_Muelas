package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public void generar_pacienteHC() {
		System.out.println("Nombre: "+getNombre());
		File archivo = new File("./dowloads/historia_clinica.txt");
		FileWriter escritura;
		try {
			escritura = new FileWriter(archivo, true);
			escritura.write("HISTORIAL CLINICO: "+"\n"+
					"Paciente: "+getNombre()+" "+getApellido()+"\n"+
					"Tratamientos: "+getTratamiento());
			escritura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Se ha generado y descargado su historal clinico.Comprueba su descarga.");
	}

}
