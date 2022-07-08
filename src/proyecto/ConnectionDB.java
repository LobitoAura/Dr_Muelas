package proyecto;

import java.sql.*;

public class ConnectionDB {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/dr_muelas";
	private static final String USER = "root";
	private static final String PASS = "Virusp@nda20";
	
	private static Connection conn = null;
	    	    
	public static Connection getConnection() {
		//System.out.println("CONECTANDO A LA BASE DE DATOS...");
		if(conn == null) {
		    try {
		        Class.forName(JDBC_DRIVER);
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);
		        //System.out.println("CONEXION EXITOSA");
		    }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		}
		return conn;
	}
	
    public static void disconnect() {
        try {
            conn.close();
            conn = null;
            //System.out.println("DESCONEXION EXITOSA");
        } catch (SQLException ex) {
            //System.out.println("Error al cerrar BD");
        }
    }
}
