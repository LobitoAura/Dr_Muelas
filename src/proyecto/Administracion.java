package proyecto;

import proyecto.ConnectionDB;
import proyecto.Paciente;

import java.sql.*;
import java.time.*;
import java.time.format.*;
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
	
	public Scanner open_Scanner() {
		Scanner teclado = new Scanner(System.in);
		return teclado;
	}
	
	public Connection open_Connection() {
		Connection sql_DB = ConnectionDB.getConnection();
		return sql_DB;
	}
	
	public void close_Connection() {
			ConnectionDB.disconnect();
	}
	
	public void generar_Turno() {
		System.out.println("¿Cuantos turnos va a crear?");
		int cantidad = open_Scanner().nextInt();
		System.out.println("Ingrese la fecha:");
		String fecha = open_Scanner().nextLine();	
		System.out.println("Ingrese la hora:");
		int hora = open_Scanner().nextInt();
 
		String INSERT_TURNO = "INSERT INTO turno(categoria,fecha,disponibilidad) VALUES(?,?,?)";

		boolean flag;
			try{
				PreparedStatement sql = open_Connection().prepareStatement(INSERT_TURNO);
				for(int i=0; i<cantidad; i++) {
					String fecha_turno = fecha+" "+hora+" Hs.";
					if(i<(cantidad / 2)) {
						flag = true;
					}else {
						flag = false;
						}
			        //set the values for the prepared statement
			        sql.setBoolean(1, flag); // El segundo valor es 0=False,1=Verdadero
			        sql.setString(2, fecha_turno);
			        sql.setInt(3, 0);
		
			        //Executar los statment
			        sql.executeUpdate();
			        
			        hora = hora + 1;
				}
				System.out.println("LOS TURNOS SE ACTUALIZARON A LA BASE DE DATOS");
		    }catch (Exception e){
		    	System.out.println(e);
		    }            
		close_Connection();
	}
	
	public void generar_Informe() {
		
	}
		
} 
