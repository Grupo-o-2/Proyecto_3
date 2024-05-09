package consolas;

import fabrica.Fabrica;
import modelo.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class ConsolaComprador extends Consola{
	
	
	public static void mostrarMenu(){
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Hacer una consignación.\n");
		System.out.println("2: Comprar una pieza.\n");
		System.out.println("3: Realizar una oferta en una subasta.\n");
	}
	

	
	public ConsolaComprador()
	{
		
	}
	


public static void main(String[] args ) {
	Fabrica fabricaInicio= new Fabrica();
	Galeria galeriaInicio= fabricaInicio.crearGaleria("Galería Inicio", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());

	System.out.println("------------------------------------------------------------------------------------------\n");
	System.out.println("Bienvenido a la galería.\n");
	System.out.println("Inicie sesión para empezar\n");
	String usuario= pedirCadenaAlUsuario("Login");
	String contraseña= pedirCadenaAlUsuario("Password");
	boolean verificacion = galeriaInicio.verificacionSesion(usuario, contraseña);
	if (verificacion == true) {
		
		mostrarMenu();
		
	}
	
	else if ( verificacion == false) {
		System.out.println("Tu login o tu password son incorrectos. \n");
		System.out.println("Vuelve a intentarlo. \n");
		
	}
	
	
		
}

}