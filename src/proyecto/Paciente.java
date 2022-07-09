package proyecto;

import java.sql.*;

public class Paciente extends Persona{

	private boolean emergencia;
	

	public Paciente(String nombre, String apellido, int dni, int edad, String domicilio, String telefono,
			String genero, String email, String usuario, String clave, boolean emergencia) {
		super(nombre, apellido, dni, edad, domicilio, telefono, genero, email, usuario, clave);
		this.emergencia = emergencia;
	}

	public Paciente() {
		super();
			
	}

	public boolean isEmergencia() {
		return emergencia;
	}

	public void setEmergencia(boolean emergencia) {
		this.emergencia = emergencia;
	}
	
	public boolean sector_Edad() {
		if(super.getEdad()>14) {
			return true;
		}else {
			return false;
		}
	}
	
	public void iniciar_Usuario() {
		String usuario,clave;
		System.out.println("--> INICIAR SESIÓN <--");
		System.out.println("Ingresar Usuario: ");
		usuario = open_Scanner().next();
		System.out.println("Ingresar clave: ");
		clave = open_Scanner().next();
		System.out.println("COMPROBANDO CREDENCIALES... ");
		String SELECT_USUARIO_CLAVE = "SELECT * FROM dr_muelas.persona WHERE usuario = '"+usuario+"' AND clave = '"+clave+"'";
		try{
			
			Statement sql = open_Connection().createStatement();

			ResultSet rs = sql.executeQuery(SELECT_USUARIO_CLAVE);
			if (rs.next()) {
		    	System.out.println("BIENVENIDO "+" "+usuario);
		    	
			    setNombre(rs.getString("nombre"));
			    setApellido(rs.getString("apellido"));
			    setDni(rs.getInt("dni"));
			    setEdad(rs.getInt("edad"));
			    setDomicilio(rs.getString("domicilio"));
			    setTelefono(rs.getString("telefono"));
			    setGenero(rs.getString("genero"));
			    setEmail(rs.getString("email"));
			    setUsuario(rs.getString("usuario"));
			    setClave(rs.getString("clave"));
			    
			    panel_Paciente();   
			//Retiene los errores y lo muestra
			}else {
		    	System.out.println("Usuario o contraseña incorrectos");
		    		}
		}catch (Exception e){
			System.out.println("ERROR AL BUSCAR DATOS: "+e);
		}
	}
	
	public void panel_Paciente(){
		System.out.println("********************************************"+"\n"+
				"** \t ELEGIR TIPO DE ATENCION    \t  **"+"\n"+
				"** \t DIGITE UN NUMERO     \t\t  **"+"\n"+
				"** --> Opcion 1 : Mostrar mis datos   \t\t  **"+"\n"+
				"** --> Opcion 2 : Obtener Turno Normal \t  **"+"\n"+
				"** --> Opcion 3 : obtener Turno Emergencia \t  **"+"\n"+
				"** --> Opcion 4 : Generar Historia Clinica "+"\n"+
				"** --> Opcion 5 : Cancelar un Turno "+"\n"+
				"** --> Opcion 6 : Cerrar Sesion \t  **"+"\n"+
				"********************************************");
		int opcion = super.open_Scanner().nextInt();
		if(opcion>0 & opcion<=5) {
			switch(opcion) {
				case 1: System.out.println(mostrar_Datos());
						panel_Paciente();
						break;
						
				case 2: setEmergencia(false);
						estado_Turno();
						elegir_Turno();
						break;
						
				case 3: setEmergencia(true);
						estado_Turno();
						elegir_Turno();
						break;
						
				case 4: Historia_Clinica HC = new Historia_Clinica(
						getNombre(),
						getApellido(),
			            getDni(),
			            getEdad(),
			            getDomicilio(),
			            getTelefono(),
			            getGenero(),
			            getEmail(),
			            getUsuario(),
			            getClave(),
			            isEmergencia(),
			            "Extraccón"
						);
						HC.generar_Informe();
					
				case 5: cancelar_Turno();
						break;
				
				case 6: super.panel_Persona();
						break;
					
			}
		}else {
			System.out.println("INGRESO UNA OPCION NO VALIDA");
		}
	}
	
	public void estado_Turno() {
		String SELECT_TURNO = "SELECT * FROM turno;";
		
		try{
			
			Statement sql = super.open_Connection().createStatement();
			ResultSet rs = sql.executeQuery(SELECT_TURNO);
			String disponibilidad,sector;
			
            while(rs.next()) {
            	//Recibir por tipo de columna
            	int sql_Id = rs.getInt("id");
            	boolean sql_sector = rs.getBoolean("categoria");
            	String sql_Fecha = rs.getString("fecha");
            	boolean sql_disponibilidad = rs.getBoolean("disponibilidad");
            	
            	if (sql_disponibilidad == false & sql_sector == sector_Edad()) {
            		disponibilidad = "Disponible";
            		sector = "SALA A (Menores)";
            	}else {
            		disponibilidad = "No Disponible";
            		sector = "SALA B (Mayores)";
            	}
            	//mostrar valores
            	System.out.println("-> N°"+sql_Id+" "+sector+"	Estado:"+disponibilidad+" "+sql_Fecha);
            }
            rs.close();
      
        }catch (Exception e){
        	System.out.println(e);
        }
	}
	
	public void elegir_Turno() {
		System.out.println("Seleccione el turno: ");
		int turno = super.open_Scanner().nextInt();
		System.out.println("¿Confirma el turno seleccionado? [SI = 1 - NO = 2]");
		int opcion = super.open_Scanner().nextInt();
		if(opcion == 1 || opcion == 2) {
			switch(opcion) {
											//INSERT INTO `muelas_kd`.`paciente` (`id_persona`, `emergencia`) VALUES ('18224411', '1');
				case 1: String ELEGIR_TURNO = "UPDATE `dr_muelas`.`turno` SET `disponibilidad` = '1' WHERE (`id` = '"+turno+"');";
				
						try{
								
							Statement sql = super.open_Connection().createStatement();
							sql.executeUpdate(ELEGIR_TURNO);
							System.out.println("El turno n°"+turno+ " ha sido registrado con éxito.");
						}catch (Exception e){
					        System.out.println("ERROR AL CARGAR EL TURNO"+e);
					        }	
						super.close_Connection();	
						break;
				case 2: System.out.println("A DECIDIDO CANCELAR LA SELECCION DE TURNO");
						break;
			}
		}else {
			System.out.println("NO INGRESO NINGUNA OPCION O INGRESO UNA OPCION NO VALIDA");
		}
	}
	
	public void cancelar_Turno() {
		
	}
	
	@Override
	public void insertarDB() {
		String INSERT_PACIENTE = "INSERT INTO `dr_muelas`.`paciente` (`emergencia`) VALUES ('?');";
		try{
            PreparedStatement sql = super.open_Connection().prepareStatement(INSERT_PACIENTE);
        
            //Setea los valores oara subirse a la base de datos
            //sql.setInt(1,getDni());
            sql.setBoolean(1, isEmergencia());
            
            //Executa los comandos
            sql.executeUpdate();
            System.out.println("EXITO SE A REGISTRADO DATOS DE PACIENTE");
        //Retiene los errores y lo muestra
        }catch (Exception e){
        	System.out.println("ERROR AL REGISTRAR: "+e);
        }

	}
	
}
	