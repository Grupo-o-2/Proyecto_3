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

public class ConsolaAdministrador extends Consola{
	private static final String TIPO_ADMINISTRADOR = "Administrador";

	public static void mostrarMenu(){
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Registrar ingreso de una pieza al inventario.\n");
		System.out.println("2: Confirmar realización de una venta.\n");
		System.out.println("3: Revisión y devolución de piezas consignadas en la galería.\n");
		System.out.println("4: Crear nueva subasta.\n");
		System.out.println("5: Aumentar valor máximo de compras de un comprador. \n");
		System.out.println("6: Ver la historia de un comprador. \n");
		System.out.println("7: Ver la historia de un artista.\n");
		System.out.println("8: Ver la historia de una pieza.\n");
	}
	
	public ConsolaAdministrador(){}
	
	public static void main(String[] args ) throws PropietarioErroneoException, UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, ConsignacionExistenteException, DineroOfrecidoInsuficienteException {
		Fabrica fabricaInicio= new Fabrica();
		
		ArrayList<Pieza> historialpiezasGaleria = new ArrayList<Pieza>();
		ArrayList<Pieza> actualesPiezasGaleria = new ArrayList<Pieza>();
		ArrayList<Pieza> antiguasPiezasGaleria = new ArrayList<Pieza>();

		HashMap<Pieza, String> historialComprador1 = new HashMap<Pieza, String>();
		HashMap<Pieza, String> historialComprador2 = new HashMap<Pieza, String>();
		HashMap<Pieza, String> historialComprador3 = new HashMap<Pieza, String>();
		
		
		
		
		Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), historialpiezasGaleria,
				actualesPiezasGaleria, antiguasPiezasGaleria, new ArrayList<Usuario>());
			try {
			Fabrica fabricaGuardarDos = new Fabrica();
			
			
			//Compradores
			galeriaInicio.crearComprador("loginalice", "hola123", "Alice", 2000000, 2003,
					historialComprador1, new ArrayList<Pieza>(), 100000, "1234567890");
			galeriaInicio.crearComprador("loginlucy", "lucy456", "Lucy", 3000000, 256,
					historialComprador2, new ArrayList<Pieza>(), 200000, "567890123");
			galeriaInicio.crearComprador("loginjohn", "john789", "John", 1800000, 400,
					historialComprador3, new ArrayList<Pieza>(), 80000, "234567890");
			galeriaInicio.crearComprador("fake", "fake", "fake", 1800000, 400,
					new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 80000, "fake");
			
			Comprador primerComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginalice");
			Comprador segundoComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginlucy");
			Comprador tercerComprador = (Comprador) galeriaInicio.obtenerUsuarioPorLogin("loginjohn");
			
			//Comprador de prueba -> No usar
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

			Artista artista1 = fabricaGuardarDos.crearArtista("loginvincent", "dai", "Vincent", "31256789", new ArrayList<Pieza>(), galeriaInicio);
			Artista artista2 = fabricaGuardarDos.crearArtista("loginpicasso", "guernica", "Picasso", "32198765", new ArrayList<Pieza>(),galeriaInicio);
			Artista artista3 = fabricaGuardarDos.crearArtista("loginkahlo", "frida", "Kahlo", "33344455",new ArrayList<Pieza>(), galeriaInicio);
			Artista artista4 = fabricaGuardarDos.crearArtista("loginmonet", "impression", "Monet", "44455566",new ArrayList<Pieza>(), galeriaInicio);
			Artista artista5 = fabricaGuardarDos.crearArtista("loginvangogh", "starrynight", "Van Gogh", "55566677",new ArrayList<Pieza>(), galeriaInicio);
			Artista artista6 = fabricaGuardarDos.crearArtista("logindali", "surrealism", "Dali", "66677788",new ArrayList<Pieza>(), galeriaInicio);
			
			
			
			ArrayList<Artista> autoresVideo1 = new ArrayList<Artista>();
			autoresVideo1.add(artista1);
			autoresVideo1.add(artista2);
			
			ArrayList<Artista> autoresPintura1 = new ArrayList<Artista>();
			autoresPintura1.add(artista3);
			autoresPintura1.add(artista4);
			
			ArrayList<Artista> autoresImpresion1 = new ArrayList<Artista>();
			autoresImpresion1.add(artista5);
			
			ArrayList<Artista> autoresFotografia1 = new ArrayList<Artista>();
			autoresFotografia1.add(artista6);
			
			
			ArrayList<Artista> autoresEscultura1 = new ArrayList<Artista>();
			autoresEscultura1.add(artista3);
			autoresEscultura1.add(artista4);
			autoresEscultura1.add(artista5);
			autoresEscultura1.add(artista6);
			
			
			//Usuarios de cada pieza (personas que han tenido o tienen la pieza) - compradores
			ArrayList<Usuario> usuariosVideo1 = new ArrayList<Usuario>();
			usuariosVideo1.add(primerComprador);
			usuariosVideo1.add(segundoComprador);
			
			ArrayList<Usuario> usuariosPintura1 = new ArrayList<Usuario>();
			usuariosPintura1.add(primerComprador);
			usuariosPintura1.add(tercerComprador);
			usuariosPintura1.add(segundoComprador);
			
			ArrayList<Usuario> usuariosImpresion1 = new ArrayList<Usuario>();
			usuariosImpresion1.add(primerComprador);
			
			ArrayList<Usuario> usuariosFotografia1 = new ArrayList<Usuario>();
			usuariosFotografia1.add(segundoComprador);
			usuariosFotografia1.add(tercerComprador);
			
			ArrayList<Usuario> usuariosEscultura1 = new ArrayList<Usuario>();
			usuariosEscultura1.add(tercerComprador);
		
			
			//Historial de ventas de piezas - compradores
			HashMap<String, Integer> historialVideo1 = new HashMap<String, Integer>();
			historialVideo1.put("2024-03-19", 50);
			historialVideo1.put("2020-10-08", 30);
			
			HashMap<String, Integer> historialPintura1 = new HashMap<String, Integer>();
			historialPintura1.put("2023-12-12", 1800);
			historialPintura1.put("2018-05-13", 1500);
			historialPintura1.put("2010-01-29", 1000);
			
			HashMap<String, Integer> historialImpresion1 = new HashMap<String, Integer>();
			historialImpresion1.put("2024-01-05", 73);

			HashMap<String, Integer> historialFotografia1 = new HashMap<String, Integer>();
			historialFotografia1.put("2022-07-07", 256);
			historialFotografia1.put("2012-09-25", 150);
			
			HashMap<String, Integer> historialEscultura1 = new HashMap<String, Integer>();
			historialEscultura1.put("2021-08-14", 400);




			
			
			//Piezas pertenecientes a compradores
			galeriaInicio.crearVideo("Video artístico", 50, "20180201" ,"Puerto Rico",  primerComprador, autoresVideo1, usuariosVideo1,
					historialVideo1, "123", false, false, false,false, null, 92.3, 80.2,24, "mp4"  );
			Video video1 = (Video) galeriaInicio.obtenerPiezaGlobalesporTitulo("Video artístico");
			galeriaInicio.crearPintura("Selene", 1880, "19921008" ,"Francia" , primerComprador, autoresPintura1 , usuariosPintura1,
					historialPintura1,"123", false, false, false, false, null , 15.6, 78);
		    Pintura pintura1 = (Pintura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Selene"); 
			galeriaInicio.crearImpresion("Esperanza", 73, "19980822" ,"Colombia" , primerComprador, autoresImpresion1, usuariosImpresion1,
					historialImpresion1,"123", false, false, false, false, null , 72, 25);
			Impresion impresion1 = (Impresion) galeriaInicio.obtenerPiezaGlobalesporTitulo("Esperanza");
			galeriaInicio.crearFotografia("Posibilidad", 256, "20000319" ,"Estonia" , segundoComprador, autoresFotografia1, usuariosFotografia1,
					historialFotografia1,"123", false, false, false, false, null , 50, 50, "jpg", true);
			Fotografia fotografia1 = (Fotografia) galeriaInicio.obtenerPiezaGlobalesporTitulo("Posibilidad");
			galeriaInicio.crearEscultura("Venus de Milo", 400, "18760426" ,"Italia" , tercerComprador, autoresEscultura1, usuariosEscultura1,
					historialEscultura1,"123", false, false, false, false, null , 750, 60,70, "Marmol", 456, false, false);
			Escultura escultura1 = (Escultura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Venus de Milo");

			
			
			
			//Usuarios de cada pieza (personas que han tenido o tienen la pieza) - galería
			ArrayList<Usuario> usuariosVideo2 = new ArrayList<Usuario>();
			usuariosVideo2.add(segundoComprador);
			
			ArrayList<Usuario> usuariosPintura2 = new ArrayList<Usuario>();

			
			ArrayList<Usuario> usuariosImpresion2 = new ArrayList<Usuario>();
			usuariosImpresion2.add(segundoComprador);
			usuariosImpresion2.add(tercerComprador);
			
			ArrayList<Usuario> usuariosFotografia2 = new ArrayList<Usuario>();
			usuariosFotografia2.add(primerComprador);
			
			ArrayList<Usuario> usuariosEscultura2 = new ArrayList<Usuario>();
			
		
			
			//Historial de ventas de piezas - galería
			HashMap<String, Integer> historialVideo2 = new HashMap<String, Integer>();
			historialVideo2.put("2017-06-24", 55);

			
			HashMap<String, Integer> historialPintura2 = new HashMap<String, Integer>();

			
			HashMap<String, Integer> historialImpresion2 = new HashMap<String, Integer>();
			historialImpresion2.put("2015-05-15", 84);
			historialImpresion2.put("2014-05-15", 74);

			HashMap<String, Integer> historialFotografia2 = new HashMap<String, Integer>();
			historialFotografia2.put("2021-02-14", 153);
			
			
			HashMap<String, Integer> historialEscultura2 = new HashMap<String, Integer>();
			
			
			
			//Piezas pertenencientes a la galería
			galeriaInicio.crearVideo("Video abstruso", 60, "20150315", "Argentina", compradorGaleria, autoresVideo1, usuariosVideo2,
					historialVideo2, "123", false, false, true, true, null , 100.5, 90.1, 30, "avi");
			Video video2 = (Video) galeriaInicio.obtenerPiezaGlobalesporTitulo("Video abstruso");
		
			galeriaInicio.crearPintura("Amanecer en París", 1915, "19900805", "España", compradorGaleria, autoresPintura1, usuariosPintura2,
					historialPintura2, "123", false, false, true, true, null , 20.8, 67);
		    Pintura pintura2 = (Pintura) galeriaInicio.obtenerPiezaGlobalesporTitulo("Amanecer en París"); 
		    
			galeriaInicio.crearImpresion("Reflejos urbanos", 90, "20011230", "México", compradorGaleria, autoresImpresion1, usuariosImpresion2,
					historialImpresion2, "123", false, false, true, true, null ,85, 35);
			Impresion impresion2 = (Impresion) galeriaInicio.obtenerPiezaGlobalesporTitulo("Reflejos urbanos");
			
			galeriaInicio.crearFotografia("Serenidad en la playa", 180, "20180704", "Grecia", compradorGaleria, autoresFotografia1, usuariosFotografia2,
					historialFotografia2, "123", false, false, true, true, null ,45, 35, "png", false);
			Fotografia fotografia2 = (Fotografia) galeriaInicio.obtenerPiezaGlobalesporTitulo("Serenidad en la playa");
			
			galeriaInicio.crearEscultura("La musa del renacimiento", 5500, "14980315", "Francia", compradorGaleria, autoresEscultura1, usuariosEscultura2,
					historialEscultura2, "123", false, false, true, true, null , 900, 70, 80, "Bronce", 789, false, false);
			Escultura escultura2 = (Escultura) galeriaInicio.obtenerPiezaGlobalesporTitulo("La musa del renacimiento");
			
			galeriaInicio.añadirPieza(video2);
			galeriaInicio.añadirPieza(pintura2);
			galeriaInicio.añadirPieza(impresion2);
			galeriaInicio.añadirPieza(fotografia2);
			galeriaInicio.añadirPieza(escultura2);
			
			
			galeriaInicio.getHistorialPiezas().add(video1);
			galeriaInicio.getHistorialPiezas().add(pintura1);
			galeriaInicio.getHistorialPiezas().add(impresion1);
			galeriaInicio.getHistorialPiezas().add(escultura1);
			galeriaInicio.getHistorialPiezas().add(fotografia1);
			
			galeriaInicio.getPiezasAntiguas().add(video1);
			galeriaInicio.getPiezasAntiguas().add(pintura1);
			galeriaInicio.getPiezasAntiguas().add(impresion1);
			galeriaInicio.getPiezasAntiguas().add(escultura1);
			galeriaInicio.getPiezasAntiguas().add(fotografia1);
			
			
			//Historial piezas compradores
			historialComprador1.put(video1, "2024-03-19");
			historialComprador1.put(pintura1, "2023-12-12");
			historialComprador1.put(impresion1, "2024-01-05");
			historialComprador1.put(fotografia2, "2021-02-14");
			
			historialComprador2.put(video1, "2020-10-08");
			historialComprador2.put(pintura1, "2010-01-29");
			historialComprador2.put(fotografia1, "2022-07-07");
			historialComprador2.put(video2, "2017-06-24");
			historialComprador2.put(impresion2, "2015-05-15");
			
			historialComprador3.put(pintura1, "2018-05-13");
			historialComprador3.put(fotografia1, "2012-09-25");
			historialComprador3.put(escultura1, "2021-08-14");
			historialComprador3.put(impresion2, "2014-05-15");
			
			
			//Subasta
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(primerComprador);
			participantes1.add(segundoComprador);
			participantes1.add(tercerComprador);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); //Piezas participantes
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(primerComprador, 1700);
			ofertaVideo.put(tercerComprador, 1800);
			ofertaVideo.put(segundoComprador, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(segundoComprador, 7500);
			ofertaEscultura.put(tercerComprador, 7600);
			ofertaEscultura.put(primerComprador, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			Subasta subastaIncial = galeriaInicio.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			//galeriaInicio.salvarGaleria("Consola.json");
			//Galeria galeriaClonada = fabricaInicio.crearGaleria("Galeria Clonada", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				//	new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
			//galeriaClonada.cargarGaleria("Consola.json");
			//galeriaClonada.salvarGaleria("ConsolaClonada.json");
			//System.out.println("Se clonó :D.");
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
			int eleccion= (int) pedirNumeroAlUsuario("Elección");
			if(eleccion == 1)
			{
				System.out.println("------------------------------------------------------------------------------------------\n");
				System.out.println("Bienvenido a la galería.\n");
				System.out.println("Inicie sesión para empezar\n");
				String usuario= pedirCadenaAlUsuario("Login");
				String contraseña= pedirCadenaAlUsuario("Password");
				Usuario comprador;
				int verificacion = galeriaInicio.verificacionSesion(usuario, contraseña, TIPO_ADMINISTRADOR);
				if (verificacion == 1) {
					comprador = galeriaInicio.obtenerUsuarioPorLogin(usuario);
					int eleccionDos;
					int deseaConsultar;
					boolean centinelaDos = true;
					boolean historiaArtista = true;
					boolean historiaPieza = true;
					mostrarMenu();
					eleccionDos= (int) pedirNumeroAlUsuario("Elección");
					while (centinelaDos)
					{
						if (eleccionDos == 0)
						{
							System.out.println("Se cerró la sesión. \n");
							centinelaDos=false;
						}
						
						
						//Historia Artista
						else if(eleccionDos == 7) {
							historiaArtista = true;
							while(historiaArtista) {
								System.out.println(" Artistas de la galería:"+"\n");
								for (Usuario artista: galeriaInicio.obtenerArtistas()) {
									System.out.println(" • "+artista.getNombre()+ "\n");
								}
								String artistaEscogido = pedirCadenaAlUsuario("Escribe el nombre del artista que deseas ver su historia");
								Usuario artistaSeleccionado = galeriaInicio.obtenerUsuarioPorNombre(artistaEscogido);
								
								if (galeriaInicio.getUsuarios().contains(artistaSeleccionado)) {
									System.out.println("Historial del artista:"+ "\n");
									for (Pieza pieza:  ((Artista)artistaSeleccionado).getPiezasCreadas()) {
										System.out.println(" • Titulo: "+pieza.getTitulo()+ "\n");
										System.out.println("     • Fecha de creación: "+galeriaInicio.formatearFecha(pieza.getFechaCreacion())+ "\n");
										System.out.println("     • Historial de ventas (Fecha y valor de venta): "+ "\n");
										
										if (pieza.getHistorialVentas().keySet().isEmpty() == false) {
											for (String fechaVenta: pieza.getHistorialVentas().keySet() ) {
												System.out.println("         • "+fechaVenta+": "+pieza.getHistorialVentas().get(fechaVenta) +"\n");	
											}
											System.out.println("\n");
										}
										
										else {
												System.out.println("         • Esta pieza nunca ha sido vendida. "+ "\n");
											System.out.println("\n");
										}
									}
								}
								
								else {
									System.out.println("Este artista no existe."+ "\n");
								}
								
								historiaArtista = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
						}
						
						
						//Historia Pieza
						else if(eleccionDos == 8) {
							historiaPieza = true;
							while(historiaPieza) {
								
								System.out.println(" Todas las piezas:"+"\n");
								for (Pieza pieza: galeriaInicio.getHistorialPiezas()) {
									System.out.println(" • "+pieza.getTitulo()+ "\n");
								}
								
								String titulopieza = pedirCadenaAlUsuario("Escribe el nombre de la pieza que deseas ver su historia");
								Pieza piezaEscogida = galeriaInicio.obtenerPiezaGlobalesporTitulo(titulopieza);
								
								if (galeriaInicio.getHistorialPiezas().contains(piezaEscogida)) {
									System.out.println("Historial de la pieza:"+ "\n");
									System.out.println(" • Titulo: "+piezaEscogida.getTitulo()+ "\n");
									System.out.println("     • Datos generales: \n");
									StringBuilder stringBuilder = new StringBuilder();
									stringBuilder.append("        • Fecha de creación: ");
									stringBuilder.append(Galeria.formatearFecha(piezaEscogida.getFechaCreacion()));
									stringBuilder.append("\n");
									System.out.println(stringBuilder.toString());
									System.out.println("        • Lugar de creación: "+piezaEscogida.getLugarCreacion()+ "\n");
									System.out.println("        • Valor actual: "+Integer.toString(piezaEscogida.getValor())+ "\n");
									
									if (piezaEscogida.getPropietario().getNombre().compareTo("fake") != 0) {
										System.out.println("        • Propietario actual: "+piezaEscogida.getPropietario().getNombre()+ "\n");
									}
									
									else {
										System.out.println("        • Propietario actual: La galería es la dueña actual de esta pieza.\n");
									}
									
									
							        if (piezaEscogida.getAutores().isEmpty() != true) {
							        	
							        	System.out.println("        • Autores: \n");
							        	for (Artista autor : piezaEscogida.getAutores()) {
							        		System.out.println("          • "+  autor.getNombre()+"\n");
							        		
							        	}
							        }
							        else {
							        	System.out.println("        • Autores: Esta pieza no tiene ningún autor.\n");
							        }
									
									System.out.println("     • Historial de dueños: \n");
									if (piezaEscogida.getHistorialDueños().isEmpty() != true) {
										
										for (Usuario dueño: piezaEscogida.getHistorialDueños()) {
											System.out.println("        • "+dueño.getNombre()+"\n");
										}
									}
									
									else {
										
										System.out.println("         • Esta pieza nunca ha sido vendida. "+ "\n");
									}
									
									
									System.out.println("     • Historial de ventas (Fecha y valor de venta): "+ "\n");
									
									if (piezaEscogida.getHistorialVentas().keySet().isEmpty() == false) {
										for (String fechaVenta: piezaEscogida.getHistorialVentas().keySet() ) {
											System.out.println("         • "+fechaVenta+": "+piezaEscogida.getHistorialVentas().get(fechaVenta) +"\n");	
										}
										System.out.println("\n");
									}
									
									else {
											System.out.println("         • Esta pieza nunca ha sido vendida. "+ "\n");
										System.out.println("\n");
									}
								
									
								}
								historiaPieza = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
						}
						}
					}
				
				
				else if ( verificacion == 0) {
					System.out.println("El login ingresado no existe. \n");
					System.out.println("Vuelve a intentarlo. \n");
					
				}
				
				else if( verificacion == 2) {
					System.out.println("Este login no pertenece a un administrador. \n");
					System.out.println("Vuelve a intentarlo. \n");	
				}
				
				else if( verificacion == 3) {
					System.out.println("La contraseña no es correcta. \n");
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
