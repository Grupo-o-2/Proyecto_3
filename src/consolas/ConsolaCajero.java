package consolas;


import java.util.ArrayList;

import exceptions.ConsignacionExistenteException;
import exceptions.DineroInsuficienteException;
import exceptions.DineroOfrecidoInsuficienteException;
import exceptions.FechaInvalidaException;
import exceptions.MismoCompradorException;
import exceptions.PropietarioErroneoException;
import exceptions.UsuarioInexistenteException;
import exceptions.ValorMaximoExcedidoException;
import exceptions.VentaImposibleException;
import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;
import piezas.Pieza;
import usuarios.Artista;
import usuarios.Usuario;
import usuarios.*;

public class ConsolaCajero  extends Consola{
	private static final String TIPO_CAJERO = "Cajero";

	

	public static void mostrarMenu(){
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Registrar un pago (Hacer una venta).\n");
		System.out.println("2: Ver la historia de un artista.\n");
		System.out.println("3: Ver la historia de una pieza.\n");
	}
	
	public ConsolaCajero() {}

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
			Usuario cajero;
			int verificacion = galeriaInicio.verificacionSesion(usuario, contraseña, TIPO_CAJERO);
			if (verificacion == 1) {
				cajero = galeriaInicio.obtenerUsuarioPorLogin(usuario);
				int eleccionDos;
				boolean centinelaDos = true;
				boolean pago = true;
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
					
					
					//Aceptar pago
					else if (eleccionDos == 1) {
						pago = true;
						while(pago) {
							System.out.println(" Los usuarios con solicitudes activas de compra son:"+"\n");
							for (Usuario usuario1 : galeriaInicio.obtenerCompradores()) {
								if ( usuario1.getNombre().compareTo("fake") != 0) {
									System.out.println(" • "+usuario1.getNombre()+ "\n");
								}
							}
							
							String nombreComprador = pedirCadenaAlUsuario("Escribe el nombre del comprador cuya solicitud deseas atender");
							Usuario compradorElegido = galeriaInicio.obtenerUsuarioPorNombre(nombreComprador);
							
							System.out.println(" Las piezas disponibles para la venta son:"+"\n");
							for (Pieza pieza: galeriaInicio.getPiezasActuales()) {
								if (pieza.isDispventa() == true) {
									System.out.println(" • "+pieza.getTitulo()+ "\n");
								}
							}
							
							
							String tituloPieza = pedirCadenaAlUsuario("Escribe el nombre de la pieza que el comprador desea adquirir");
							Pieza piezaElegida = galeriaInicio.obtenerPiezaGlobalesporTitulo(tituloPieza);
							String fechaActual = pedirCadenaAlUsuario("Ingrese la fecha actual (Formato -> añomesdía (Ej: 20280529))");
							String métodoPago = pedirCadenaAlUsuario("Ingrese su método de pago ( Transferencia Bancaria - Efectivo - Tarjeta de crédito)");
							
							if (galeriaInicio.getPiezasActuales().contains(piezaElegida) && piezaElegida.isDispventa() == true) {
								
								((Cajero)cajero).venderPieza(((Comprador)compradorElegido), piezaElegida, galeriaInicio,fechaActual );
								
								System.out.println("\n");
								System.out.println("Esperando verificación de compra..."+"\n");
								System.out.println("Se ha registrado el pago exitosamente."+"\n");
							}
							
							else {
								System.out.println("Esta pieza no existe o no está disponible para la venta"+"\n");
							}
							
							
							pago = false;
							mostrarMenu();
							eleccionDos= (int) pedirNumeroAlUsuario("Elección");
						}
					}

					
					
					
					//Historia Artista
					else if(eleccionDos == 2) {
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
					else if(eleccionDos == 3) {
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
