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
		System.out.println("1: Registrar ingreso de una pieza nueva al inventario.\n");
		System.out.println("2: Revisión y devolución de piezas consignadas en la galería.\n");
		System.out.println("3: Crear nueva subasta.\n");
		System.out.println("4: Mostrar subastas actuales.\n");
		System.out.println("5: Aumentar valor máximo de compras de un comprador. \n");
		System.out.println("6: Ver la historia de un comprador. \n");
		System.out.println("7: Ver la historia de un artista.\n");
		System.out.println("8: Ver la historia de una pieza.\n");
	}
	
	public static void mostrarMenuSubasta(){
		System.out.println("1: Sí.\n");
		System.out.println("2: No.\n");
	
	}
	
	public static void mostrarMenuPieza(){
		System.out.println("1: Vídeo.\n");
		System.out.println("2: Pintura.\n");
		System.out.println("3: Impresión.\n");
		System.out.println("4: Fotografía.\n");
		System.out.println("5: Escultura.\n");
	
	}
	
	
	public ConsolaAdministrador(){}
	
	public static void main(String[] args ) throws PropietarioErroneoException, UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, ConsignacionExistenteException, DineroOfrecidoInsuficienteException, FormatoIncorrectoException {
		
		Fabrica fabricaInicio= new Fabrica();		
		Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaInicio.cargarGaleria("Galeria.json");
		galeriaInicio.salvarGaleria("GaleriaAnterior.json");
		
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
				Usuario administrador;
				int verificacion = galeriaInicio.verificacionSesion(usuario, contraseña, TIPO_ADMINISTRADOR);
				if (verificacion == 1) {
					administrador = galeriaInicio.obtenerUsuarioPorLogin(usuario);
					int eleccionDos;
					boolean centinelaDos = true;
					boolean nuevaPieza = true;
					boolean crearSubasta = true;
					boolean mostrarSubasta = true;
					boolean valorCompras = true;
					boolean revision = true; 
					boolean historiaComprador = true;
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
						
						//Confirmar realización de venta
						else if (eleccionDos == 1) {
							nuevaPieza = true;
							while(nuevaPieza) {
								System.out.println("Eliga el tipo de pieza de la nueva pieza: \n");
								mostrarMenuPieza();
								int tipoPieza= (int) pedirNumeroAlUsuario("Escribe el número correspondiente al tipo de pieza deseado");
								
								String nombrePieza = pedirCadenaAlUsuario("Desígnale el nombre de tu preferencia la nueva pieza");
								String fechaCreacion = pedirCadenaAlUsuario("Ingresa la fecha de creación de la pieza (Formato -> añomesdía (Ej: 20280529))");
								String lugarCreacion = pedirCadenaAlUsuario("Ingresa el lugar de creación de la pieza");
								int valor = (int) pedirNumeroAlUsuario("Ingresa el valor fijo de la pieza");
								
								System.out.println("Seleccione los autores de la nueva pieza: \n");
								for (Usuario artista : galeriaInicio.obtenerArtistas()) {
									if (artista.getNombre() != "Van Gogh") {
										System.out.println(" • "+artista.getNombre()+ "\n");
										
									}
								}
								String artistasEscogidos = pedirCadenaAlUsuario("Ingrese los nombres de los autores de esta pieza (Formato -> Nombre-Nombre (Ej: Dali-Kahlo))");
								ArrayList<Artista> autores = new ArrayList<Artista>();
								
								for (Usuario artista: galeriaInicio.obtenerArtistas()){
									if (artista.getNombre() != "fake" && Galeria.verificarFormato(artistasEscogidos) == true) {
										if ( artistasEscogidos.contains(artista.getNombre()) ) {
											autores.add(((Artista)artista));
										}
									}
								}
								ArrayList<Usuario>  dueños = new ArrayList<Usuario>();
								HashMap<String, Integer> ventas = new HashMap<String, Integer>();
								if (tipoPieza == 1) {
									int alto= (int) pedirNumeroAlUsuario("Ingrese el alto del vídeo");
									int ancho= (int) pedirNumeroAlUsuario("Ingrese el ancho del vídeo");
									int duración= (int) pedirNumeroAlUsuario("Ingrese la duración del vídeo en minutos");
									String formato = pedirCadenaAlUsuario("Ingrese el tipo de formato en que se encuentra el vídeo (Ej: mp4)");
									
									
									galeriaInicio.crearVideo(nombrePieza, valor, fechaCreacion, lugarCreacion, ((Comprador)galeriaInicio.obtenerUsuarioPorLogin("fake")), autores , dueños, ventas, "123", false, true, true, true, null, alto, ancho, duración, formato);
									Video videoN = (Video) galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
									galeriaInicio.añadirPieza(videoN);
									
									System.out.println(" La pieza de tipo vídeo fue creada exitosamente.\n");
								}
								
								else if (tipoPieza == 2) {
									int alto= (int) pedirNumeroAlUsuario("Ingrese el alto de la pintura");
									int ancho= (int) pedirNumeroAlUsuario("Ingrese el ancho de la pintura");
									galeriaInicio.crearPintura(nombrePieza, valor, fechaCreacion, lugarCreacion, ((Comprador)galeriaInicio.obtenerUsuarioPorLogin("fake")), autores, dueños, ventas, "123", false, true, true, true, null, alto, ancho);
									
									Pintura pinturaN = (Pintura) galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
									galeriaInicio.añadirPieza(pinturaN);
									
									System.out.println(" La pieza de tipo pintura fue creada exitosamente.\n");
								}
								
								else if (tipoPieza == 3) {
									int alto= (int) pedirNumeroAlUsuario("Ingrese el alto de la impresión");
									int ancho= (int) pedirNumeroAlUsuario("Ingrese el ancho de la impresión");
									galeriaInicio.crearImpresion(nombrePieza, valor, fechaCreacion, lugarCreacion, ((Comprador)galeriaInicio.obtenerUsuarioPorLogin("fake")), autores, dueños, ventas, "123", false, true, true, true, null, alto, ancho);
									
									Impresion impresionN = (Impresion) galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
									galeriaInicio.añadirPieza(impresionN);
									
									System.out.println(" La pieza de tipo impresión fue creada exitosamente.\n");
								}
								
								else if (tipoPieza == 4) {
									int alto= (int) pedirNumeroAlUsuario("Ingrese el alto de la fotografía");
									int ancho= (int) pedirNumeroAlUsuario("Ingrese el ancho de la fotografía");
									String formato = pedirCadenaAlUsuario("Ingrese el tipo de formato en que se encuentra la fotografía (Ej: jpg)");

									galeriaInicio.crearFotografia(nombrePieza, valor, fechaCreacion, lugarCreacion, ((Comprador)galeriaInicio.obtenerUsuarioPorLogin("fake")), autores, dueños, ventas, "123", false, true, true, true, null, alto, ancho, formato, true);
									
									Fotografia fotografiaN = (Fotografia) galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
									galeriaInicio.añadirPieza(fotografiaN);
									
									System.out.println(" La pieza de tipo fotografía fue creada exitosamente.\n");
								}
								
								else if (tipoPieza == 5) {
									int alto= (int) pedirNumeroAlUsuario("Ingrese el alto de la escultura");
									int ancho= (int) pedirNumeroAlUsuario("Ingrese el ancho de la escultura");
									int profundidad= (int) pedirNumeroAlUsuario("Ingrese la profundidad de la escultura");
									String materiales = pedirCadenaAlUsuario("Ingrese el tipo de materiales que se utilizaron para la escultura");
									int peso= (int) pedirNumeroAlUsuario("Ingrese el peso de la escultura");
									galeriaInicio.crearEscultura(nombrePieza, valor, fechaCreacion, lugarCreacion, ((Comprador)galeriaInicio.obtenerUsuarioPorLogin("fake")), autores, dueños, ventas, "123", false, true, true, true, null, alto, ancho, profundidad, materiales, peso, false, false);
								
									Escultura esculturaN = (Escultura) galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
									galeriaInicio.añadirPieza(esculturaN);
									
									System.out.println(" La pieza de tipo escultura fue creada exitosamente.\n");
								
								}
								
								
								
								nuevaPieza = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
						}
						
						//Revisión y devolución de piezas
						else if(eleccionDos == 2) {
							revision = true;
							while (revision) {
								ArrayList<Pieza> consignadas = new ArrayList<Pieza>(); 
								
								for (Pieza pieza : galeriaInicio.getPiezasActuales()) {
									if ( pieza.isConsignacion() == true) {
										consignadas.add(pieza);
										
									}
								}
								
								if  ( consignadas.isEmpty() == true ){
									System.out.println(" No se encuentran piezas consginadas en este momento.\n");
								}
								
								else {
									System.out.println(" Las piezas que se encuentran en consignación actualmente son:\n");
									for (Pieza pieza : consignadas ) {
							
											System.out.println(" • "+pieza.getTitulo()+ "\n");
										}
									
									String tituloPieza = pedirCadenaAlUsuario("Escoge la pieza para verificar su fecha límite y en caso de ser necesario devolverla a su dueño");
									Pieza piezaEscogida = galeriaInicio.obtenerPiezaGlobalesporTitulo(tituloPieza);
									String fechaActual= pedirCadenaAlUsuario("Ingrese la fecha actual (Formato -> añomesdía (Ej: 20280529))");
									
									if  ( ((Administrador) administrador).devolverPiezasConsignadas(piezaEscogida.getPropietario(), piezaEscogida, fechaActual, galeriaInicio) == true ){
										System.out.println("\n");
										System.out.println(" Fecha límite:"+Galeria.formatearFecha(piezaEscogida.getFechaLimite())+"\n");
										System.out.println(" Fecha actual:"+Galeria.formatearFecha(fechaActual)+"\n");
										System.out.println(" La fecha límite había expirado, por tanto, la pieza fue devuelta a su propietario.\n");
									}
									
									else {
										System.out.println("\n");
										System.out.println(" Fecha límite:"+Galeria.formatearFecha(piezaEscogida.getFechaLimite())+"\n");
										System.out.println(" Fecha actual:"+Galeria.formatearFecha(fechaActual)+"\n");
										System.out.println(" La fecha límite no ha sucedido, por tanto, la pieza sigue en la galería.\n");
									}
									}
								
								
								revision = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
							}
			
						
						//Crear nueva subasta
						else if (eleccionDos == 3) {
							crearSubasta = true;
							while(crearSubasta) {
								String nombreSubasta = pedirCadenaAlUsuario("Desígnale el nombre de tu preferencia la nueva subasta");
								System.out.println(" Escoge los compradores que pueden participar de esta subasta:"+"\n");
								
								for (Usuario comprador1: galeriaInicio.obtenerCompradores()){
									if (comprador1.getNombre() != "fake") {
										System.out.println(" • "+comprador1.getNombre()+ "\n");
									}
								}
								String nombresCompradores = pedirCadenaAlUsuario("Escribe los nombres elegidos (Formato: Alice-Lucy o John)");
								ArrayList<Usuario> participantes = new ArrayList<Usuario>();
								
								for (Usuario comprador1: galeriaInicio.obtenerCompradores()){
									if (comprador1.getNombre() != "fake" && Galeria.verificarFormato(nombresCompradores) == true) {
										if ( nombresCompradores.contains(comprador1.getNombre()) ) {
											participantes.add(comprador1);
										}
									}
								}
								
								System.out.println(" Escoge las piezas que serán subastadas, así mismo, elige el valor inicial y el valor final de cada pieza: "+"\n");
								
								HashMap<Pieza, ArrayList<Integer>> piezasSubastadas = new  HashMap<Pieza, ArrayList<Integer>>();
								
								for (Pieza pieza : galeriaInicio.getPiezasActuales()) {
									ArrayList<Integer> valores = new ArrayList<Integer>();
									if (pieza.isDispsubasta() == true) {
										System.out.println("\n");
										System.out.println(" • "+pieza.getTitulo()+"\n");
										mostrarMenuSubasta();
										int eleccionSubasta = (int) pedirNumeroAlUsuario("  ¿Deseas añadir esta pieza a la subasta?");
										if (eleccionSubasta == 1) {
											int valorMinimo = (int) pedirNumeroAlUsuario("  Escribe el valor mínimo para esta pieza");
											int valorInicial = (int) pedirNumeroAlUsuario("  Escribe el valor inicial para esta pieza");
											valores.add(0, valorMinimo);
											valores.add(1, valorInicial);
											piezasSubastadas.put(pieza, valores);
										}
										else if( eleccionSubasta != 1 && eleccionSubasta != 2) {
											System.out.println(" Esta no es una opción válida.\n");
										}
									}
								}
								if (piezasSubastadas.size() == 0) {
									System.out.println(" La subasta no pudo ser creada debido a que no se eligió ninguna pieza para que fuera subastada.\n");
								}
								
								else {
									
									HashMap<Pieza,HashMap<Usuario, Integer>> registroOfertas = new HashMap<Pieza,HashMap<Usuario, Integer>>();
									galeriaInicio.crearSubasta(nombreSubasta, participantes, galeriaInicio.getUnOperador(), registroOfertas , piezasSubastadas);
									
									System.out.println(" La subasta fue creada exitosamente.\n");
								}
								
								crearSubasta = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
						}
						
						
						//Mostrar subastas actuales
						else if (eleccionDos == 4) {
							mostrarSubasta = true;
							while(mostrarSubasta) {
								System.out.println(" La subastas actuales son:\n");
								for (Subasta subasta1 : galeriaInicio.getSubastas()) {
									System.out.println(" •" +subasta1.getNombre()+"\n");
								}
								
								mostrarSubasta = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
						}
						
						//Aumentar valor máximo de compras
						else if (eleccionDos == 5) {
							valorCompras = true;
							while(valorCompras) {
								
								System.out.println(" Compradores con solicitudes actuales para aumentar el valor máximo de sus compras:"+"\n");
								for (Usuario comprador1: galeriaInicio.obtenerCompradores()){
									if (comprador1.getNombre() != "fake") {
										System.out.println(" • "+comprador1.getNombre()+ "\n");
									}
								}
								String nombreComprador = pedirCadenaAlUsuario("Escribe el nombre del comprador del cuál deseas atender la solicitud");
								Usuario compradorSeleccionado = galeriaInicio.obtenerUsuarioPorNombre(nombreComprador);
								
								String textoItalico = "\u001B[3mEl valor se aumentará únicamente si el dinero actual del comprador es mayor al valor máximo de compras actual.\u001B[0m";
								System.out.println(" "+ textoItalico +"\n");
								String textoItalico2 = "\u001B[3mSi el valor de compras se aumenta, se cambiará por el dinero actual del comprador.\u001B[0m";
								System.out.println(" "+ textoItalico2 +"\n");
								
								if ( ((Administrador) administrador).verificarValorMaximo(compradorSeleccionado) == true ) {
									((Administrador) administrador).aumentarValorMaximo(compradorSeleccionado, ((Comprador)compradorSeleccionado).getDinero());
									System.out.println(" La solicitud fue aceptada y el valor máximo de compras aumentó."+ "\n");
								}
								
								else {
									System.out.println(" La solicitud fue rechazada debido a que el dinero del comprador es menor que su valor máximo de compras."+ "\n");
								}
								
								valorCompras = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}							
						}
						
						
						//Historia de un comprador
						else if (eleccionDos == 6) {
							historiaComprador = true;
							while(historiaComprador) {
								System.out.println(" Compradores de la galería:"+"\n");
								for (Usuario comprador1: galeriaInicio.obtenerCompradores()){
									if (comprador1.getNombre() != "fake") {
										System.out.println(" • "+comprador1.getNombre()+ "\n");
									}
								}
								String nombreComprador = pedirCadenaAlUsuario("Escribe el nombre del comprador que deseas ver su historia");
								Usuario compradorSeleccionado = galeriaInicio.obtenerUsuarioPorNombre(nombreComprador);
								
								if (galeriaInicio.getUsuarios().contains(compradorSeleccionado)) {
									System.out.println("Historial del comprador:"+ "\n");
									System.out.println(" • Valor de su colección: "+Integer.toString(((Comprador)compradorSeleccionado).getValorColeccion())+ "\n");
									System.out.println(" • Piezas actuales del comprador: \n");
									for (Pieza pieza : ((Comprador)compradorSeleccionado).getPiezasActuales() ) {
										System.out.println("    • "+pieza.getTitulo() +"\n");
									}
									
									System.out.println(" • Historial de compras: \n");
									for (Pieza pieza : ((Comprador)compradorSeleccionado).getHistorialPiezas().keySet() ) {
										
										if ( ((Comprador)compradorSeleccionado).getHistorialPiezas().keySet().isEmpty() == false ) {
											
												System.out.println("    • "+pieza.getTitulo()+": "+((Comprador)compradorSeleccionado).getHistorialPiezas().get(pieza) +"\n");	
												
										}
										
										else {
											System.out.println("         • Esta pieza nunca ha sido vendida. "+ "\n");
										}
									}
									System.out.println("\n");
								}
								
								
								else {
										System.out.println("Este comprador no existe."+ "\n");
									}
								
								historiaComprador = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
								}
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
				galeriaInicio.salvarGaleria("Galeria.json");
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
