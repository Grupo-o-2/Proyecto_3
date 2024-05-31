package usuarios;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ConsignacionExistenteException;
import exceptions.DineroInsuficienteException;
import exceptions.FechaInvalidaException;
import exceptions.MismoCompradorException;
import exceptions.PropietarioErroneoException;
import exceptions.UsuarioInexistenteException;
import exceptions.ValorMaximoExcedidoException;
import exceptions.VentaImposibleException;
import modelo.*;
import piezas.*;
import persistencia.*;



public class Administrador extends Empleado{
	
	private static final String TIPO = "Administrador";

	public Administrador(String login, String password, String telefono, String nombre) {
		super(login, password, telefono, nombre);
		// TODO Auto-generated constructor stub
	}

	public String getTipo() {
		return TIPO;
	}
	
	public void registrarPiezas(Pieza nuevaPieza, Galeria galeria) {
	 
		galeria.getPiezasActuales().add(nuevaPieza);
		galeria.getHistorialPiezas().add(nuevaPieza);
			
	}

 	public void devoluciondePiezas(Pieza pieza, Galeria galeria, Usuario usuario ) {
	 
 		galeria.getPiezasActuales().remove(pieza);
 		galeria.getPiezasAntiguas().add(pieza);
	
 	}
 	
 	public boolean devolverPiezasConsignadas(Usuario propietario, Pieza piezaAConsginar, String fechaActual, Galeria galeria) throws FechaInvalidaException {
 		Consignacion nuevaConsignacion = new Consignacion(propietario, piezaAConsginar);
 		if (nuevaConsignacion.revisionFechaLimite(piezaAConsginar, fechaActual, propietario, galeria) == true) {
 			return true;
 		}
 		else 
 		{
 			return false;
 		}
 	}
 	
 	
 	public boolean verificarUsuariosSubasta(ArrayList<Usuario> participantes, Galeria galeria) throws UsuarioInexistenteException {
 		ArrayList<Boolean>  usuariosVerificados = new ArrayList<Boolean>();
 		
 		for (Usuario participante : participantes) {
 			if (  galeria.verificarUsuario(participante) == true ) {
 				usuariosVerificados.add(true);
 			}
 		}
 		if (usuariosVerificados.contains(false)) {
 			return false;
 		}
 		else {
 			return true;
 		}
 	}
	

 	
 	public void verificacionDeCompra(Pieza pieza, Usuario comprador, Galeria galeria, String fecha) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException {
 	
 		VentaPiezas nuevaVenta = new VentaPiezas(comprador, pieza);
 		if ((nuevaVenta.verificarEstadoDeVenta(pieza, galeria)) == true ){
 			
 			if (galeria.verificarUsuario(comprador) == true) {
 				nuevaVenta.venderPieza(comprador, pieza, galeria, fecha);
 			}}}
	
 	public void verificacionDeCompraTarjeta(Pieza pieza, Usuario comprador, Galeria galeria, String fecha, String nombrePasarela, String numeroTarjeta) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, IOException,ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
 	 	
 		VentaPiezas nuevaVenta = new VentaPiezas(comprador, pieza);
 		if ((nuevaVenta.verificarEstadoDeVenta(pieza, galeria)) == true ){
 			
 			if (galeria.verificarUsuario(comprador) == true) {
 				nuevaVenta.venderPiezaTarjeta(comprador, pieza, galeria, fecha, nombrePasarela, numeroTarjeta);
 			}
 		}
 	}
 	
 	public void registrarPiezaPorConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta, String fechaActual) throws PropietarioErroneoException, FechaInvalidaException, ConsignacionExistenteException {
 		
 		Consignacion nuevaConsignacion = new Consignacion(propietario, piezaAConsignar);
 		nuevaConsignacion.generarConsignacion(propietario, piezaAConsignar, fechaLimite, galeria, exhibaVendaoSubasta, fechaActual);
 	}
 	
 	public void aumentarValorMaximo(Usuario usuario, int nuevoValor) {
 	((Comprador) usuario).setValorMaximoCompras(nuevoValor);
 	}
 	
 	public boolean verificarValorMaximo(Usuario usuario) {
 		 if (((Comprador) usuario).getDinero() > ((Comprador) usuario).getValorMaximoCompras()) {
 			 return true;
 		 }
 		 else return false;
 	}

	}