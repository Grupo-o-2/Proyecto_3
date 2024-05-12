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
	private static final String TIPO_COMPRADOR = "Comprador";

	 
	
	
	public static void mostrarMenu(){
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Hacer una consignación.\n");
		System.out.println("2: Comprar una pieza.\n");
		System.out.println("3: Realizar una oferta en una subasta.\n");
		System.out.println("4: Ver mis piezas actuales.\n");
		System.out.println("5: Ver la historia de un artista.\n");
		System.out.println("6: Ver la historia de una pieza.\n");
	}
	

	
	public ConsolaComprador()
	{
		
	}
	


public static void main(String[] args ) throws PropietarioErroneoException, UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
	Fabrica fabricaInicio= new Fabrica();
	
	ArrayList<Pieza> historialpiezasGaleria = new ArrayList<Pieza>();
	ArrayList<Pieza> actualesPiezasGaleria = new ArrayList<Pieza>();
	ArrayList<Pieza> antiguasPiezasGaleria = new ArrayList<Pieza>();
	
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), historialpiezasGaleria,
			actualesPiezasGaleria, antiguasPiezasGaleria, new ArrayList<Usuario>());
		try {
		Fabrica fabricaGuardarDos = new Fabrica();
		
		
		//Compradores
		galeriaInicio.crearComprador("loginalice", "hola123", "Alice", 2000000, 2003,
				new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 100000, "1234567890");
		galeriaInicio.crearComprador("loginlucy", "lucy456", "Lucy", 3000000, 256,
                new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 200000, "567890123");
		galeriaInicio.crearComprador("loginjohn", "john789", "John", 1800000, 400,
                new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 80000, "234567890");
		galeriaInicio.crearComprador("fake", "fake", "fake", 1800000, 400,
				new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 80000, "fake");
		Comprador primerComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginalice");
		Comprador segundoComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginlucy");
		Comprador tercerComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginjohn");
		Comprador compradorGaleria = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("fake");
		
		
		
		//Empleados
		galeriaInicio.crearAdministrador("loginbob", "adios456","912345678", "Bob");
		galeriaInicio.crearCajero("logineve", "saludos789","891234567", "Eve");
		galeriaInicio.crearOperador("loginneo", "secreto","789123456", "Neo");
		galeriaInicio.crearEmpleado("loginThomasAnderson", "enlamatrix","789123456", "Thomas Anderson");
		Administrador administrador = (Administrador) galeriaInicio.obtenerUsuarioPorLogin("loginbob");
		Cajero cajero = (Cajero) galeriaInicio.obtenerUsuarioPorLogin("logineve");
		Operador operador = (Operador) galeriaInicio.obtenerUsuarioPorLogin("loginneo");
		Empleado empleado = (Empleado) galeriaInicio.obtenerUsuarioPorLogin("loginThomasAnderson");
		
		
		//Artistas
		galeriaInicio.crearArtista("loginvincent", "dai", "Vincent", "31256789", new ArrayList<Pieza>() );
		galeriaInicio.crearArtista("loginpicasso", "guernica", "Picasso", "32198765", new ArrayList<Pieza>());
		galeriaInicio.crearArtista("loginkahlo", "frida", "Kahlo", "33344455", new ArrayList<Pieza>());
		galeriaInicio.crearArtista("loginmonet", "impression", "Monet", "44455566", new ArrayList<Pieza>());
		galeriaInicio.crearArtista("loginvangogh", "starrynight", "Van Gogh", "55566677", new ArrayList<Pieza>());
		galeriaInicio.crearArtista("logindali", "surrealism", "Dali", "66677788", new ArrayList<Pieza>());
		Artista artista1 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("loginvincent");
		Artista artista2 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("loginpicasso");
		Artista artista3 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("loginkahlo");
		Artista artista4 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("loginmonet");
		Artista artista5 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("loginvangogh");
		Artista artista6 = (Artista) galeriaInicio.obtenerUsuarioPorLogin("logindali");
		
		
		ArrayList<Artista> autoresVideo1 = new ArrayList<Artista>();
		autoresVideo1.add(artista1);
		autoresVideo1.add(artista2);
		
		ArrayList<Artista> autoresPintura1 = new ArrayList<Artista>();
		autoresPintura1.add(artista3);
		autoresPintura1.add(artista4);
		
		ArrayList<Artista> autoresImpresion1 = new ArrayList<Artista>();
		autoresImpresion1.add(artista5);
		
		ArrayList<Artista> autoresFotografia1 = new ArrayList<Artista>();
		autoresImpresion1.add(artista6);
		
		
		ArrayList<Artista> autoresEscultura1 = new ArrayList<Artista>();
		autoresEscultura1.add(artista3);
		autoresEscultura1.add(artista4);
		autoresEscultura1.add(artista5);
		autoresEscultura1.add(artista6);
		
		
		
		
		//Piezas pertenecientes a compradores
		galeriaInicio.crearVideo("Video artístico", 50, "20220201" ,"Puerto Rico",  primerComprador, autoresVideo1, new ArrayList<Usuario>(),
				new HashMap<String, Integer>(), "123", false, false, true,true, 92.3, 80.2,24, "mp4"  );
		Video video1 = (Video) galeriaInicio.obtenerPiezaGlobalesporTitulo("Video artístico");
	
		galeriaInicio.crearPintura("Selene", 1880, "19921008" ,"Francia" , primerComprador, autoresPintura1 , new ArrayList<Usuario>(),
	    		new HashMap<String, Integer>(),"123", false, false, true, true,  15.6, 78);
	    Pintura pintura1 = (Pintura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Selene"); 
	    
		galeriaInicio.crearImpresion("Esperanza", 73, "19980822" ,"Colombia" , primerComprador, autoresImpresion1, new ArrayList<Usuario>(),
	    		new HashMap<String, Integer>(),"123", false, false, true, true,  72, 25);
		Impresion impresion1 = (Impresion) galeriaInicio.obtenerPiezaGlobalesporTitulo("Esperanza");
		
		galeriaInicio.crearFotografia("Posibilidad", 256, "20200319" ,"Estonia" , segundoComprador, autoresFotografia1, new ArrayList<Usuario>(),
	    		new HashMap<String, Integer>(),"123", false, false, true, true,  50, 50, "jpg", true);
		Fotografia fotografia1 = (Fotografia) galeriaInicio.obtenerPiezaGlobalesporTitulo("Posibilidad");
		
		galeriaInicio.crearEscultura("Venus de Milo", 400, "18760426" ,"Italia" , tercerComprador, autoresEscultura1, new ArrayList<Usuario>(),
	    		new HashMap<String, Integer>(),"123", false, false, true, true,  750, 60,70, "Marmol", 456, false, false);
		Escultura escultura1 = (Escultura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Venus de Milo");
		
		
		
		//Piezas pertenencientes a la galería
		galeriaInicio.crearVideo("Video abstruso", 60, "20220315", "Argentina", compradorGaleria, autoresVideo1, new ArrayList<Usuario>(),
                new HashMap<String, Integer>(), "123", false, false, true, true, 100.5, 90.1, 30, "avi");
		Video video2 = (Video) galeriaInicio.obtenerPiezaGlobalesporTitulo("Video abstruso");
	
		galeriaInicio.crearPintura("Amanecer en París", 1915, "19900805", "España", compradorGaleria, autoresPintura1, new ArrayList<Usuario>(),
		        new HashMap<String, Integer>(), "123", false, false, true, true, 20.8, 67);
	    Pintura pintura2 = (Pintura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Amanecer en París"); 
	    
		galeriaInicio.crearImpresion("Reflejos urbanos", 90, "20011230", "México", compradorGaleria, autoresImpresion1, new ArrayList<Usuario>(),
		        new HashMap<String, Integer>(), "123", false, false, true, true, 85, 35);
		Impresion impresion2 = (Impresion) galeriaInicio.obtenerPiezaGlobalesporTitulo("Reflejos urbanos");
		
		galeriaInicio.crearFotografia("Serenidad en la playa", 180, "20180704", "Grecia", compradorGaleria, autoresFotografia1, new ArrayList<Usuario>(),
		        new HashMap<String, Integer>(), "123", false, false, true, true, 45, 35, "png", false);
		Fotografia fotografia2 = (Fotografia) galeriaInicio.obtenerPiezaGlobalesporTitulo("Serenidad en la playa");
		
		galeriaInicio.crearEscultura("La musa del renacimiento", 550, "14980315", "Francia", compradorGaleria, autoresEscultura1, new ArrayList<Usuario>(),
		        new HashMap<String, Integer>(), "123", false, false, true, true, 900, 70, 80, "Bronce", 789, false, false);
		Escultura escultura2 = (Escultura) galeriaInicio.obtenerPiezaGlobalesporTitulo("La musa del renacimiento");
		
		galeriaInicio.añadirPieza(video2);
		galeriaInicio.añadirPieza(pintura2);
		galeriaInicio.añadirPieza(impresion2);
		galeriaInicio.añadirPieza(fotografia2);
		galeriaInicio.añadirPieza(escultura2);
		
		HashMap<Pieza, String> historial1 = primerComprador.getHistorialPiezas();
		historial1.put(fotografia1, fotografia1.getFechaCreacion());
		primerComprador.setHistorialPiezas(historial1);
		
		HashMap<Pieza, String> historial2 = segundoComprador.getHistorialPiezas();
		historial2.put(pintura1, pintura1.getFechaCreacion());
		historial2.put(fotografia2, fotografia2.getFechaCreacion());
		historial2.put(video2, video2.getFechaCreacion());
		segundoComprador.setHistorialPiezas(historial2);
		
		ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
		participantes1.add(primerComprador);
		participantes1.add(segundoComprador);
		participantes1.add(tercerComprador);
		
		ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
		valoresVideo.add(100000);
		valoresVideo.add(150000);
		
		ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
		valoresEscultura.add(123456);
		valoresEscultura.add(654321);
		
		ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
		valoresPintura.add(8765343);
		valoresPintura.add(8765344);
		
		HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>();
		subasta1.put(video1, valoresVideo);
		subasta1.put(escultura2, valoresEscultura);
		subasta1.put(pintura2, valoresPintura);
		
		HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
		
		HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
		ofertaVideo.put(primerComprador, 123456);
		ofertaVideo.put(tercerComprador, 123457);
		ofertaVideo.put(segundoComprador, 20000);
		ofertas1.put(video1, ofertaVideo);
		
		HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
		ofertaEscultura.put(segundoComprador, 1232456);
		ofertaEscultura.put(tercerComprador, 1235457);
		ofertaEscultura.put(primerComprador, 125900);
		ofertas1.put(escultura2, ofertaEscultura);
		
		HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
		ofertas1.put(pintura2, ofertaPintura);
		
		galeriaInicio.crearSubasta(participantes1, operador, ofertas1, subasta1);
		
		galeriaInicio.salvarGaleria("Consola.json");
		Galeria galeriaClonada = fabricaInicio.crearGaleria("Galeria Clonada", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaClonada.cargarGaleria("Consola.json");
		galeriaClonada.salvarGaleria("ConsolaClonada.json");
		System.out.println("Se clonó :D.");
		/*fabricaGuardarDos.getPersistencia().salvarGaleria("Prueba.json" , fabricaGuardarDos.getUsuariosCreados(), fabricaGuardarDos.getPiezasCreadas());
		/*Fabrica fabricaCargar = new Fabrica();
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
			Usuario comprador;
			int verificacion = galeriaInicio.verificacionSesion(usuario, contraseña, TIPO_COMPRADOR);
			if (verificacion == 1) {
				comprador = galeriaInicio.obtenerUsuarioPorLogin(usuario);
				int eleccionDos;
				boolean centinelaDos = true;
				boolean consignacion = true;
				boolean compra = true;
				boolean observar = true;
				mostrarMenu();
				eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
				while (centinelaDos)
				{
					if (eleccionDos == 0)
					{
						System.out.println("Se cerró la sesión. \n");
						centinelaDos=false;
					}
					
					//Hacer consginación
					else if (eleccionDos == 1 ) {
						while(consignacion) {
						ArrayList<Pieza> piezasDisponibles = ((Comprador)comprador).getPiezasActuales();
						
						if (piezasDisponibles.isEmpty() == true) {
							System.out.println("No tienes piezas disponibles para consignar \n");
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
						}
						else {
							System.out.println("Las piezas disponibles para consignar son: \n");
						for (Pieza pieza: piezasDisponibles) {
							System.out.println(pieza.getTitulo()+ "\n");
						}
							String tituloPieza = pedirCadenaAlUsuario("Ingrese el nombre la pieza a consignar");
							Pieza piezaAConsignar = ((Comprador)comprador).obtenerPiezaporTitulo(tituloPieza);
							
							if (piezasDisponibles.contains(piezaAConsignar)) {
								((Comprador)comprador).consignarPieza(piezaAConsignar, "20280505", galeriaInicio, "123");
								System.out.println("La pieza ha sido consignada en la galería"+"\n");
								consignacion = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
							}
							
							else {System.out.println("Esta pieza no existe o no se encuentra disponible para consignar"+"\n");
							consignacion = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");}
						
						
					}
						}

					}
					
					//Comprar una pieza
					else if(eleccionDos == 2) {
						while(compra) {
						ArrayList<Pieza> piezasEnGaleria = galeriaInicio.getPiezasActuales();
						
						if (piezasEnGaleria.isEmpty() == true) {
							System.out.println("La galería no tiene piezas en venta \n");
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
							}
						
						else {
							System.out.println("Las piezas disponibles para comprar son: \n");	
						ArrayList<Pieza> piezasDisponibles = new ArrayList<Pieza>();		
						for (Pieza pieza: piezasEnGaleria) {
							if (pieza.isDispventa() == true) {
								piezasDisponibles.add(pieza);
							}		
						}
						
						for (Pieza pieza: piezasDisponibles) {
							System.out.println(pieza.getTitulo()+ "\n");
						}
						String tituloPieza = pedirCadenaAlUsuario("Ingrese el nombre la pieza a comprar");
						Pieza piezaAComprar = galeriaInicio.obtenerPiezaporTitulo(tituloPieza);
						
						if(piezasDisponibles.contains(piezaAComprar)) {
						String fechaActual = pedirCadenaAlUsuario("Ingrese la fecha actual (Formato: añomesdia -> 20240506)");
							((Comprador)comprador).comprarPieza(piezaAComprar, galeriaInicio, fechaActual);
							System.out.println("Has comprado esta pieza exitosamente."+"\n");
							compra = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
								
						}
						
						else {
							System.out.println("Esta pieza no existe o no se encuentra disponible para comprar"+"\n");
							compra = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
						}
						
						
						}
						}
					}
					
					else if(eleccionDos == 4) {
						while(observar) {
							for(Pieza pieza:((Comprador)comprador).getPiezasActuales()) {
								System.out.println(pieza.getTitulo()+ "\n");
							}
							observar = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección:\n");
						}
					}
					
					else if(eleccionDos == 123237892)
					{
						
					}
					
					}
				}
			
			
			else if ( verificacion == 0) {
				System.out.println("El login ingresado no existe. \n");
				System.out.println("Vuelve a intentarlo. \n");
				
			}
			
			else if( verificacion == 2) {
				System.out.println("Este login no pertenece a un comprador. \n");
				System.out.println("Vuelve a intentarlo. \n");	
			}
			
			else if( verificacion == 3) {
				System.out.println("LLa contraseña no es correcta. \n");
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