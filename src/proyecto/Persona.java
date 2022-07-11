package proyecto;

import java.util.*;
import java.sql.*;

public class Persona {

	protected String nombre;
	protected String apellido;
	protected int dni;
	protected int edad;
	protected String domicilio;
	protected String telefono;
	protected String genero;
	protected String email;
	protected String usuario;
	protected String clave;
	
	public Persona(String nombre, String apellido, int dni, int edad, String domicilio, 
			String telefono, String genero, String email, String usuario, String clave) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.genero = genero;
		this.email = email;
		this.usuario = usuario;
		this.clave = clave;
	}
	
	//Constructor vacio para instanciar un objeto y luego iniciar el metodo registrar
	public Persona() {

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Paciente generar_Paciente() {
		return new Paciente(getNombre(),getApellido(),getDni(),getEdad(),
		getDomicilio(),getTelefono(),getGenero(),getEmail(),getUsuario(),getClave(),false);
	}
	
	public static Scanner open_Scanner() {
		return new Scanner(System.in);
	}
	
	public Connection open_Connection() {
		return ConnectionDB.getConnection();
	}
	
	public void close_Connection() {
			ConnectionDB.disconnect();
	}
	
	public void panel_Persona(){
		System.out.println("""
				********************************************
				**      REGISTRAR O INICIAR SESIÓN \t  **
				**         DIGITE UN NUMERO        \t  **
				** --> Opcion 1 : Iniciar Sesion   \t  **
				** --> Opcion 2 : Registrar        \t  **
				** --> Opcion 3 : Salir            \t  **
				********************************************""");
		int opcion = open_Scanner().nextInt();
		switch(opcion) {
			case 1: iniciar_Usuario();
					break;
					
			case 2:	registrar_Usuario();
					break;
					
			case 3:	break;
		}
	}
	
	//Se trae los datos de la BD para hacer una verificacion si es un usuario registrado
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
			    //Si las credenciales son correctas se instancia un paciente
			    generar_Paciente().panel_Paciente();      
			//Retiene los errores y lo muestra
			}else {
		    	System.out.println("Usuario o contraseña incorrectos");
		    		}
		}catch (Exception e){
			System.out.println("ERROR AL BUSCAR DATOS: "+e);
		}
	}
	//Este metodo es para registrar a los nuevos usuarios,se necesitan los get y setter.
	public void registrar_Usuario() {
		System.out.println("--> REGISTRAR NUEVO USUARIO <--");
		System.out.println("Ingrese Nombre: ");
		setNombre(open_Scanner().next());
		System.out.println("Ingrese Apellido: ");
		setApellido(open_Scanner().next());
		System.out.println("Ingrese DNI: ");
		setDni(open_Scanner().nextInt());
		//Convierte String de la fecha de nacimiento a LocalDate
		System.out.println("Ingrese Edad: ");
		setEdad(open_Scanner().nextInt());
		System.out.println("Ingrese Domicilio: ");
		setDomicilio(open_Scanner().next());
		System.out.println("Ingrese Telefono: ");
		setTelefono(open_Scanner().next());
		System.out.println("""
				Ingrese Genero:\s
				--> Masculino = M\s
				--> Femenino = F\s
				--> No especificar = -\s""");
		String genero = open_Scanner().next();
		if(genero.length() == 1 ) {
			setGenero(genero);
		}else {
			System.out.println("Ingrese la primera letra del genero elegido.");
		}
		System.out.println("Ingrese email: ");
		setEmail(open_Scanner().next());
		System.out.println("Crear Usuario: ");
		setUsuario(open_Scanner().next());
		System.out.println("Crear clave: ");
		setClave(open_Scanner().next());
		System.out.println(mostrar_Datos());
		System.out.println("¿Confirma los datos ingresados? [SI = 1 - NO = 2]");
		int opcion = open_Scanner().nextInt();
		if(opcion>0 & opcion<=2) {
			switch(opcion) {
				case 1: insertarDB();
						break;
				case 2: System.out.println("A DECIDIDO CANCELAR EL REGISTRO");
						break;
			}
		
		}else {
			System.out.println("NO INGRESO NINGUNA OPCION O INGRESO UNA OPCION NO VALIDA");
		}
	}
	
	public String mostrar_Datos() {
		return "Nombre: "+getNombre()+"\n"+
				"Apellido: "+getApellido()+"\n"+
				"DNI: "+getDni()+"\n"+
				"Edad: "+getEdad()+" Años \n"+
				"Domicilio: "+getDomicilio()+"\n"+
				"Telefono: "+getTelefono()+"\n"+
				"Genero: "+getGenero()+"\n"+
				"Email: "+getEmail()+"\n";
	}
	
	//Subir los datos del metodo registrarUsuario a la base de datos
	public void insertarDB() {
		String INSERT_PERSONA = "INSERT INTO persona(dni,nombre,apellido,edad,domicilio,telefono,genero,email,usuario,clave) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try{
            PreparedStatement sql = open_Connection().prepareStatement(INSERT_PERSONA);
        
            //Setea los valores oara subirse a la base de datos
            sql.setInt(1, getDni());
            sql.setString(2, getNombre());
            sql.setString(3, getApellido());
            sql.setInt(4, getEdad());
            sql.setString(5, getDomicilio());
            sql.setString(6, getTelefono());
            sql.setString(7, getGenero());
            sql.setString(8, getEmail());
            sql.setString(9, getUsuario());
            sql.setString(10, getClave());
            
            //Executa los comandos
            sql.executeUpdate();
            System.out.println("EXITO SE HA REGISTRADO");
        //Retiene los errores y lo muestra
        }catch (Exception e){
        	System.out.println("ERROR AL REGISTRAR PERSONA: "+e);
        }
		String INSERT_PACIENTE = "INSERT INTO `dr_muelas`.`paciente` (`id_dni`,`emergencia`) VALUES (?,?);";
		try{
            PreparedStatement sql = open_Connection().prepareStatement(INSERT_PACIENTE);
        
            //Setea los valores oara subirse a la base de datos
            sql.setInt(1,getDni());
            sql.setBoolean(2,generar_Paciente().isEmergencia());
            
            //Executa los comandos
            sql.executeUpdate();
            System.out.println("EXITO SE HA REGISTRADO DATOS DE PACIENTE");
        //Retiene los errores y lo muestra
        }catch (Exception e){
        	System.out.println("ERROR AL REGISTRAR PACIENTE: "+e);
        }

	}
	
}
