package proyecto;

import java.sql.*;
import java.util.Scanner;

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
		Turno.generar_Turno();
	}
	
	public void panel_admin(String cargo) {	
		Scanner teclado = new Scanner(System.in);
		System.out.println("****************************************************"+"\n"+
						"** BIENVENIDO ADMINISTRADOR - FUNCION:"+cargo+"  **\n"+
						"**           DIGITE UN NUMERO             \t  **"+"\n"+
						"** --> Opcion 1 : Generar Turno          \t  **"+"\n"+
						"** --> Opcion 2 : Generar Informe        \t  **"+"\n"+
						"****************************************************");
		int opcion = teclado.nextInt();
		switch(opcion) {
		
		case 1: Administracion admin = new Administracion("Secretaria");
				admin.generar_Turno();
				break;
				
		case 2:	Persona persona = new Persona();
				persona.panel_Persona();
				break;
		}
		teclado.close();
	}
	
	public void generar_Informe() {
		System.out.println ("Pronto se generará el informe de atenciones diaria..." + "\n"
	                          + "Espere un instante...");
		String CONTAR_ATENCIONES = "SELECT * FROM turno";

		Statement sql;
		try {
			sql = ConnectionDB.getConnection().createStatement();
		    ResultSet rs = sql.executeQuery(CONTAR_ATENCIONES);
		    int atenciones = 0;
		    while (rs.next()) {
		    	atenciones+= rs.getInt("disponibilidad");
		    	System.out.println ("**RESULTADO DEL INFORME* " + "\n"
		    						+ "Pacientes atendidos: " + atenciones );
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
} 
