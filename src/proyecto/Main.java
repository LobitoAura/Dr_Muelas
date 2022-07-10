package proyecto;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Hacer panel para ingresar como usuario o admin
		Scanner teclado = new Scanner(System.in);
		System.out.println("""
				****************************************************
				**   BIENVENIDO AL CONSULTORIO Dr. Muelas \t  **
				**           DIGITE UN NUMERO             \t  **
				** --> Opcion 1 : Administraci�n          \t  **
				** --> Opcion 2 : Usuario                 \t  **
				** --> Opcion 3 : Informaci�n             \t  **
				****************************************************""");
		int opcion = teclado.nextInt();
		switch (opcion) {
			case 1 -> {
				Administracion admin = new Administracion("Secretaria");
				admin.panel_admin(admin.getFuncion());
			}
			case 2 -> {
				Persona persona = new Persona();
				persona.panel_Persona();
			}
			case 3 -> System.out.print("""
					Proyecto Dr.Muelas
					1000 Programadores Salte�os - JAVA 2022
					Universidad Nacional de Salta(UnSa)
					""");
		}
		teclado.close();		
	}

}
