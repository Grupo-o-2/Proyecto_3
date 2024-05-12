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
		System.out.println("3: Consultar dinero actual, valor máximo de compra y valor de mi colección.\n");
		System.out.println("4: Ver mis piezas actuales y consultar su estado.\n");
		System.out.println("5: Ver mi historial de piezas (Piezas antiguas y piezas actuales). \n");
		System.out.println("6: Participar en una subasta (Realizar una oferta). \n");
		System.out.println("7: Solicitar un aumento para el valor máximo de compra. \n");
		System.out.println("8: Ver la historia de un artista.\n");
		System.out.println("9: Ver la historia de una pieza.\n");
	}
	
	public static void mostrarMenuConsulta(){
		System.out.println("1: Sí.\n");
		System.out.println("2: No.\n");
	
	}
	

	
	public ConsolaComprador()
	{
		
	}
	


public static void main(String[] args ) throws PropietarioErroneoException, UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, ConsignacionExistenteException, DineroOfrecidoInsuficienteException {

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
			Usuario comprador;
			int verificacion = galeriaInicio.verificacionSesion(usuario, contraseña, TIPO_COMPRADOR);
			if (verificacion == 1) {
				comprador = galeriaInicio.obtenerUsuarioPorLogin(usuario);
				int eleccionDos;
				int deseaConsultar;
				boolean centinelaDos = true;
				boolean consignacion = true;
				boolean compra = true;
				boolean money = true;
				boolean observar = true;
				boolean miHistorial = true;
				boolean subasta = true;
				boolean solicitud = true;
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
					
					//Hacer consginación
					else if (eleccionDos == 1 ) {
						consignacion = true;{
						while(consignacion) {
						ArrayList<Pieza> piezasDisponibles = ((Comprador)comprador).getPiezasActuales();
						
						if (piezasDisponibles.isEmpty() == true) {
							System.out.println("No tienes piezas disponibles para consignar. \n");
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
						else {
							System.out.println("Las piezas disponibles para consignar son: \n");
						for (Pieza pieza: piezasDisponibles) {
							System.out.println(pieza.getTitulo()+ "\n");
						}
							String tituloPieza = pedirCadenaAlUsuario("Ingrese el nombre la pieza a consignar");
							
							String utilización = pedirCadenaAlUsuario("Si desea que la pieza sea exhibida escriba -> 1 \n"
									+ "Si desea que la pieza sea vendida escriba -> 2  \n"
									+ "Si desea que la pieza sea subastada escriba -> 3 \n"
									+ "Si desea dos o tres de estas opciones escriba los números correspondientes juntos (Ej: 123 o 12) \n");
							
							String fechaLimite = pedirCadenaAlUsuario("Ingrese la fecha límite de la consignación (Formato -> añomesdía (Ej: 20280529))");
							String fechaActual = pedirCadenaAlUsuario("Ingrese la fecha actual (Formato -> añomesdía (Ej: 20280529))");
							Pieza piezaAConsignar = ((Comprador)comprador).obtenerPiezaporTitulo(tituloPieza);
							
							if (piezasDisponibles.contains(piezaAConsignar)) {
								((Comprador)comprador).consignarPieza(piezaAConsignar, fechaLimite, galeriaInicio, utilización, fechaActual );
								System.out.println("\n");
								System.out.println("Esperando verificación de registro..."+"\n");
								System.out.println("La pieza ha sido consignada exitosamente en la galería."+"\n");
								consignacion = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
							
							else {System.out.println("Esta pieza no existe o no se encuentra disponible para consignar."+"\n");
							consignacion = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");}
						
						
					}
						}

					}
					}
					
					//Comprar una pieza
					else if(eleccionDos == 2) {
						compra = true;
						while(compra) {
						ArrayList<Pieza> piezasEnGaleria = galeriaInicio.getPiezasActuales();
						
						if (piezasEnGaleria.isEmpty() == true) {
							System.out.println("La galería no tiene piezas en venta. \n");
							
							}
						
						else {
							System.out.println("\n Las piezas disponibles para comprar y sus respectivos precios son: \n");	
						ArrayList<Pieza> piezasDisponibles = new ArrayList<Pieza>();		
						for (Pieza pieza: piezasEnGaleria) {
							if (pieza.isDispventa() == true) {
								piezasDisponibles.add(pieza);
							}		
						}
						
						for (Pieza pieza: piezasDisponibles) {
							System.out.println(pieza.getTitulo()+": "+pieza.getValor()+" "+"\n");
						}
						System.out.println(" --> Escriba 'Ninguna' si no desea comprar ninguna de las piezas anteriores. \n");
						String tituloPieza = pedirCadenaAlUsuario("Ingrese el nombre la pieza a comprar");
						Pieza piezaAComprar = galeriaInicio.obtenerPiezaporTitulo(tituloPieza);
						
						if(piezasDisponibles.contains(piezaAComprar)) {
						String fechaActual = pedirCadenaAlUsuario("Ingrese la fecha actual (Formato: año-mes-dia -> 2024-05-06)");
							((Comprador)comprador).comprarPieza(piezaAComprar, galeriaInicio, fechaActual);
							System.out.println("\n");
							System.out.println("Esperando verificación de compra..."+"\n");
							System.out.println("Has comprado esta pieza exitosamente."+"\n");
							compra = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
								
						}
						
						else if (tituloPieza.compareToIgnoreCase("Ninguna")== 0) {
							compra = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
						else {
							System.out.println("Esta pieza no existe o no se encuentra disponible para comprar."+"\n");
							compra = false;
						
						}
						
						
						}
						}
						
					}
					
					
					//Ver dinero, valor de compra y valor de colección
					else if(eleccionDos == 3) {
						money = true;
						while(money) {
							System.out.println("\n");
							System.out.println("Dinero actual: "+Integer.toString(((Comprador)comprador).getDinero())+"\n");
							System.out.println("Valor máximo de compras: "+Integer.toString(((Comprador)comprador).getValorMaximoCompras())+"\n");
							System.out.println("Valor de su colección: "+Integer.toString(((Comprador)comprador).getValorColeccion())+"\n");
							money = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
						
					}
					
					
					//Consultar piezas actuales y consultar su estado
					else if(eleccionDos == 4) {
						observar = true;
						while(observar) {
							
							System.out.println("\n");
							System.out.println("Piezas actuales:\n");
							for(Pieza pieza:((Comprador)comprador).getPiezasActuales()) {
								System.out.println(" • "+pieza.getTitulo()+ "\n");
							}
							
							System.out.println("\n");
							mostrarMenuConsulta();
							deseaConsultar= (int) pedirNumeroAlUsuario("¿Desea consultar el estado de alguna de sus piezas actuales?");
							if ( deseaConsultar == 1) {
								
								String tituloPieza = pedirCadenaAlUsuario("Escribe el nombre de la pieza que desea consultar");
								Pieza piezaEscogida = galeriaInicio.obtenerPiezaGlobalesporTitulo(tituloPieza);
								System.out.println("\n");
								System.out.println("Titulo: "+ piezaEscogida.getTitulo()+ "\n");
								if (piezaEscogida.isConsignacion() == false) {
	
									System.out.println(" • La pieza no se encuentra en consignación.\n");
									System.out.println(" • La pieza no se encuentra disponible para ser subastada.\n");
									System.out.println(" • La pieza no se encuentra disponible para ser vendida.\n");
									System.out.println("\n");
									System.out.println(" --> Si quiere conocer más detalles de su pieza diríjase a la sección de ver historia de una pieza.\n");
								}
								
								else {
									System.out.println(" • La pieza se encuentra en consignación.\n");
									
									if (piezaEscogida.isExhibida() == true) {
										System.out.println(" • La pieza se encuentra exhibida.\n");
									}
									else {
										System.out.println(" • La pieza se encuentra en bodega.\n");
									}
									
									if (piezaEscogida.isDispsubasta() == true) {
										System.out.println(" • La pieza se encuentra disponible para ser subastada.\n");
									}
									
									else {
										System.out.println(" • La pieza no se encuentra disponible para ser subastada.\n");
									}
									
									if (piezaEscogida.isDispventa() == true) {
										System.out.println(" • La pieza se encuentra disponible para ser vendida.\n");
									}
									
									else {
										System.out.println(" • La pieza no se encuentra disponible para ser vendida.\n");
									}
									System.out.println("\n");
									System.out.println(" --> Si quiere conocer más detalles de su pieza diríjase a la sección de ver historia de una pieza.\n");
								}
								
								observar = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
							
							else if(deseaConsultar == 2) {
								observar = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
							
							else if( deseaConsultar != 1 && deseaConsultar != 2) {
								System.out.println(" Esta no es una opción válida.\n");
							}
							
							
						}
					}
					
					
					//Ver historial de piezas
					else if(eleccionDos == 5) {
						miHistorial = true;
						while(miHistorial) {
							
							System.out.println("\n");
							System.out.println("  Piezas actuales con sus fechas de adquisición:\n");
							for(Pieza pieza:((Comprador)comprador).getPiezasActuales()) {
								System.out.println("  • "+pieza.getTitulo()+ ": "+((Comprador)comprador).obtenerFechaAdquisicion(pieza)+"\n");
							}
							
							System.out.println("\n");
							System.out.println("  Piezas antiguas con sus fechas de adquisición:\n");
							for(Pieza pieza:((Comprador)comprador).obtenerPiezasAntiguas()) {
								System.out.println("  • "+pieza.getTitulo()+": "+((Comprador)comprador).obtenerFechaAdquisicion(pieza)+ "\n");
							}
							
							miHistorial = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							
						}
					}
					
					//Crear oferta en Subasta
					else if (eleccionDos == 6) {
						subasta = true;
						while(subasta) {
							ArrayList<Subasta> subastasComprador = new ArrayList<Subasta>();
							System.out.println("\n");
							System.out.println("  Las subastas en las que puede participar son:\n");
							for (Subasta subasta1 : galeriaInicio.getSubastas()) {
								if (subasta1.getParticipantes().contains(comprador) == true ){
									subastasComprador.add(subasta1);
									System.out.println("  • "+subasta1.getNombre()+"\n");
								}
							}
							
							if ( subastasComprador.isEmpty() == true) {
								System.out.println("  • No tienes ninguna subasta disponible en este momento.\n");
								subasta = false;
								mostrarMenu();
								eleccionDos= (int) pedirNumeroAlUsuario("Elección");
							}
							
							String nombreSubasta = pedirCadenaAlUsuario("Escribe el nombre de la subasta en la que desea participar");
							Subasta subastaEscogida = galeriaInicio.obtenerSubastaPorNombre(nombreSubasta);
							System.out.println("  Las piezas subastadas con sus respectivos valores iniciales en la subasta elegida son:\n");
							for (Pieza pieza : subastaEscogida.getPiezasSubastadas().keySet()) {
								System.out.println("  • "+pieza.getTitulo()+": "+Integer.toString(subastaEscogida.getPiezasSubastadas().get(pieza).get(1))+"\n");
							}
							String nombrePieza = pedirCadenaAlUsuario("Escriba el nombre de la pieza por la cuál desea ofertar");
							Pieza piezaEscogida = galeriaInicio.obtenerPiezaGlobalesporTitulo(nombrePieza);
							int valorOferta =  (int) pedirNumeroAlUsuario("Escriba el valor de su oferta");
							
							((Comprador)comprador).realizarOfertaEnSubasta(piezaEscogida, valorOferta, subastaEscogida);
							
							System.out.println(" Esperando el registro de la oferta por parte del operador...\n");
							System.out.println(" La oferta se ha realizado exitosamente.\n");
							
							
							
							
							subasta = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
					}
					
					
					//Solicitud aumento valor máximo de compra
					else if (eleccionDos == 7) {
						solicitud = true;
						while(solicitud) {
							String textoItalico = "\u001B[3mEl valor se aumentará únicamente si el dinero actual es mayor al valor máximo de compras actual.\u001B[0m";
							System.out.println(" "+ textoItalico +"\n");
							String textoItalico2 = "\u001B[3mSi el valor de compras se aumenta, se cambiará por su dinero actual.\u001B[0m";
							System.out.println(" "+ textoItalico2 +"\n");
							if ( ((Comprador)comprador).solicitudAumentoValorCompra(galeriaInicio) == true  ) {
								System.out.println("  El administrador aceptó la solicitud y aumento su valor máximo de compras."+ "\n");
							}
							
							else {
								System.out.println("  El administrador rechazó la solicitud debido a que su dinero es menor que su valor máximo de compras."+ "\n");
							}
							solicitud = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
					}
					
					//Historia Artista
					else if(eleccionDos == 8) {
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
					else if(eleccionDos == 9) {
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
				System.out.println("Este login no pertenece a un comprador. \n");
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