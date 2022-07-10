package proyecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Turno {
	
	public static void generar_Turno() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese la fecha:");
		String fecha = teclado.next();	
		System.out.println("Ingrese la hora de apertura: (Cada turno dura 1hs)";
		int hora = teclado.nextInt();
		System.out.println("¿Cuantos turnos va a crear?");	   
		int cantidad = teclado.nextInt();	
			if (hora+cantidad=24){
				System.out.println("La cantidad de turnos supera el horario de atención, se establecerá una cantidad de "+23-hora+" turnos./n";
				cantidad=23-hora;
			}
 
		String INSERT_TURNO = "INSERT INTO turno(categoria,fecha,disponibilidad) VALUES(?,?,?)";

		boolean flag;
			try{
				PreparedStatement sql = ConnectionDB.getConnection().prepareStatement(INSERT_TURNO);
				for(int i=1; i<=cantidad*2; i++) {
					String fecha_turno = fecha+" "+hora+" Hs.";
					flag = i < (cantidad);
			        //set the values for the prepared statement
			        sql.setBoolean(1, flag); // El segundo valor es 0=False,1=Verdadero
			        sql.setString(2, fecha_turno);
			        sql.setInt(3, 0);
		
			        //Executar los statment
			        sql.executeUpdate();
			        
			        hora = hora + 1;
				if (i=cantidad){
					hora = hora - cantidad;
				}
				}
				System.out.println("LOS TURNOS SE ACTUALIZARON A LA BASE DE DATOS");
		    }catch (Exception e){
		    	System.out.println(e);
		    }            
		ConnectionDB.disconnect();
		teclado.close();
	}
	
	public static void estado_Turno() {
		String SELECT_TURNO = "SELECT * FROM turno;";
		
		try{
			
			Statement sql = ConnectionDB.getConnection().createStatement();
			ResultSet rs = sql.executeQuery(SELECT_TURNO);
			String disponibilidad,sector;
			
            while(rs.next()) {
            	//Recibir por tipo de columna
            	int sql_Id = rs.getInt("id");
            	boolean sql_sector = rs.getBoolean("categoria");
            	String sql_Fecha = rs.getString("fecha");
            	boolean sql_disponibilidad = rs.getBoolean("disponibilidad");
            	
            	if (!sql_disponibilidad & !sql_sector) {
            		disponibilidad = "Disponible";
            		sector = "SALA A (Menores)";
            	}else {
            		disponibilidad = "No Disponible";
            		sector = "SALA B (Mayores)";
            	}
            	//mostrar valores
            	System.out.println("-> N°"+sql_Id+"		"+sector+"	Estado:"+disponibilidad+"		"+sql_Fecha);
            }
            rs.close();
      
        }catch (Exception e){
        	System.out.println(e);
        }
	}
	
}
