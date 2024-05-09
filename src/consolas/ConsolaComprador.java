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
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		try {
		Fabrica fabricaGuardarDos = new Fabrica();
		Comprador primerComprador = fabricaGuardarDos.crearComprador("loginalice", "hola123", "Alice", 200, 0,
				new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 0, "1234567890", galeriaInicio);
		Administrador administrador = fabricaGuardarDos.crearAdministrador("loginbob", "adios456","912345678", "Bob", galeriaInicio);
		Cajero cajero = fabricaGuardarDos.crearCajero("logineve", "saludos789","891234567", "Eve", galeriaInicio);
		Operador operador = fabricaGuardarDos.crearOperador("loginneo", "secreto","789123456", "Neo", galeriaInicio);
		Empleado empleado = fabricaGuardarDos.crearEmpleado("logiThomasAnderson", "enlamatrix","789123456", "Thomas Anderson", galeriaInicio);
		/*
		Video video = fabricaGuardarDos.crearVideo("Video artístico", 2018, 2000 ,"Puerto Rico", "Autor artista", true, primerComprador, 
				"1", 1920, 1080, 152, ".mp4", false, true, false, "20/09/2024" );
		Pintura pintura = fabricaGuardarDos.crearPintura("Selene", 1880, 2000000 ,"Francia" , "Albert Aublet", true, primerComprador, 
				"1", 1920, 1080, false, true, true, "20/09/2024");
		Impresion impresion = fabricaGuardarDos.crearImpresion("Tarea", 2024, 1 ,"Bogotá", "Epson", true, primerComprador,  "1", 1920, 1080, false,  true, true,"26/04/2024" );	
		Fotografia fotografia = fabricaGuardarDos.crearFotografia("Fotografía artística", 1816, 2000 ,"Foto Japón", "fotógrafo", true, primerComprador, 
				"1", 1920, 1080, ".png", false, true, true, true, "20/09/2024" );
		Escultura escultura = fabricaGuardarDos.crearEscultura("Escultura artística", -6000, 2000000 ,	"Mesopotamia", "Desonocido", true, primerComprador, 
				"1", 1920, 1080, 152, "Piedra", 376, false, false,true ,false,  false, "20/09/2024" );
		fabricaGuardarDos.getPersistencia().salvarGaleria("Prueba.json" , fabricaGuardarDos.getUsuariosCreados(), fabricaGuardarDos.getPiezasCreadas());
		Fabrica fabricaCargar = new Fabrica();
		fabricaCargar.getPersistencia().cargarGaleria(rutaPersistencia, galeriaCargar);
		for (Usuario usuario: galeriaCargar.getUsuarios())
		{
			if (usuario.getTipo().equals("Comprador"))
			{
				Comprador comprador = (Comprador) usuario;
				System.out.println("Hola, soy " + comprador.getNombre() + " y tengo " + comprador.getDinero() + " de dinero. Si te quieres "
						+ "poner en contacto conmigo, llámame al " + comprador.getTelefono() + " . Mi login es " + comprador.getLogin() + " y mi contraseña es " + comprador.getPassword() +
						". Además, tengo " + comprador.getHistorialPiezas().size() + " piezas en el historial que son: \n");
				if (! comprador.getHistorialPiezas().isEmpty())
				{
					for (Pieza pieza : comprador.getHistorialPiezas())
					{
						System.out.println(pieza.getTitulo());
					}
					System.out.println(". \n");
				}
				System.out.println(" Y " + comprador.getPiezasActuales().size() + " piezas actuales que son: " );
				if (! comprador.getHistorialPiezas().isEmpty())
				{
					for (Pieza pieza : comprador.getHistorialPiezas())
					{
						System.out.println(pieza.getTitulo());
					}
					System.out.println(". \n");
				}
			}
			else if ("Empleado".equals(usuario.getTipo()))
			{
				System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
				+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
			}
			else if (usuario.getTipo().equals("Administrador"))
			{
				System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
				+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
			}
			else if (usuario.getTipo().equals("Operador"))
			{
				System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
				+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
			}
			else if (usuario.getTipo().equals("Cajero") )
			{
				System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
				+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
			}
			*/
		
	}catch (LoginException l)
	{
		System.out.println(l.getMessage());
	}catch (Exception e)
	{
		e.printStackTrace();
	}
	
	
	boolean centinela = true;
	while (centinela)
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Bienvenido a la aplicación.\n");
		System.out.println("Escoja una opción:\n");
		System.out.println("1. Entrar a galería.\n");
		System.out.println("0. salir.\n");
		int eleccion= (int) pedirNumeroAlUsuario("Elección:\n");
		if(eleccion == 1)
		{
			System.out.println("------------------------------------------------------------------------------------------\n");
			System.out.println("Bienvenido a la galería.\n");
			System.out.println("Inicie sesión para empezar\n");
			String usuario= pedirCadenaAlUsuario("Login");
			String contraseña= pedirCadenaAlUsuario("Password");
			boolean verificacion = galeriaInicio.verificacionSesion(usuario, contraseña);
			if (verificacion == true) {
				int eleccionDos;
				boolean centinelaDos =true;
				while (centinelaDos)
				{
					mostrarMenu();
					eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
					if (eleccionDos == 0)
					{
						System.out.println("Se cerró la sesión. \n");
						centinelaDos=false;
					}
				}
			}
			
			else if ( verificacion == false) {
				System.out.println("Tu login o tu password son incorrectos. \n");
				System.out.println("Vuelve a intentarlo. \n");
				
			}
		}
		else if(eleccion == 0)
		{
			System.out.println("Gracias por usar el programa. \n");
			centinela=false;
		}
		else 
		{
			System.out.println("Opción no disponible. \n");

		}
	}
	
		
}

}