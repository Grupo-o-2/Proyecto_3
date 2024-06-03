package modelo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*; 
import fabrica.*;
import pasarelasPago.*;
import piezas.*;
import usuarios.*;
import exceptions.*;


public class VentaPiezas {

	private Usuario comprador;
	private Pieza piezaAVender;
	
	
	public VentaPiezas(Usuario comprador, Pieza pieza) {
		this.comprador = comprador;
		this.piezaAVender = pieza;
		
	}
	
// Se verifica si la pieza está disponible para la venta
	public boolean verificarEstadoDeVenta(Pieza piezaAVender, Galeria galeria) throws VentaImposibleException {
		if ((galeria.getPiezasActuales().contains(piezaAVender) == false) || (piezaAVender.isDispventa() == false)){
			throw new VentaImposibleException(piezaAVender.getTitulo());
		}
		else {
			return true;
		}
	}
	
	
	public void venderPieza( Usuario  comprador, Pieza piezaAVender, Galeria galeria, String fecha) throws DineroInsuficienteException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException {
		
	
			if (((Comprador)comprador).getDinero() < piezaAVender.getValor()) {
				throw new DineroInsuficienteException(comprador.getNombre());
				}
			else if (  ((Comprador)comprador).getPiezasActuales().contains(piezaAVender)) {
				throw new MismoCompradorException();
			}
			else if (((Comprador)comprador).getValorMaximoCompras() < piezaAVender.getValor()) {
				throw new ValorMaximoExcedidoException(comprador.getNombre());
			}
			
			else if (Galeria.esFechaValida2(fecha) == false) {
				throw new FechaInvalidaException(fecha);
			}
			else if(piezaAVender.isConsignacion() == true) {
				
				((Comprador)comprador).añadirPieza(piezaAVender, fecha);
				galeria.getPiezasActuales().remove(piezaAVender);
				
				//cambiar plata consignada
				int dineroActualizadoPropietarioAnterior = ((Comprador)piezaAVender.getPropietario()).getDinero() + piezaAVender.getValor();
				((Comprador)piezaAVender.getPropietario()).setDinero(dineroActualizadoPropietarioAnterior);
				((Comprador)piezaAVender.getPropietario()).setValorColeccionMenor(piezaAVender.getValor());
				((Comprador)piezaAVender.getPropietario()).getPiezasActuales().remove(piezaAVender); //Elimina de la lista de piezas actuales del propietario anterior
				
				
				piezaAVender.setConsignacion(false);
				piezaAVender.setDispsubasta(false);	
				piezaAVender.setExhibida(false);
				piezaAVender.setDispventa(false);
				piezaAVender.setPropietario(comprador);
				piezaAVender.getHistorialVentas().put(fecha, piezaAVender.getValor());
				piezaAVender.getHistorialDueños().add(comprador);
				
				
				
				int dineroActualizado = ((Comprador)comprador).getDinero() - piezaAVender.getValor();
				((Comprador)comprador).setDinero(dineroActualizado);
				
				galeria.añadirCompra(piezaAVender.getTitulo(), comprador.getNombre(), fecha, "otro", "otro");
				
				
				HashMap<String, Integer> fechasCompras = galeria.getFechasCompras();
				if (fechasCompras.containsKey(fecha)) {
					int comprasAntiguas = fechasCompras.get(fecha);
					fechasCompras.put(fecha, comprasAntiguas +1);
				}else {
					fechasCompras.put(fecha, 1);
				}
				
			}
			else  {
				galeria.añadirCompra(piezaAVender.getTitulo(), comprador.getNombre(), fecha, "otro", "otro");
				((Comprador)comprador).añadirPieza(piezaAVender, fecha);;
				galeria.getPiezasActuales().remove(piezaAVender);
				piezaAVender.setConsignacion(false);
				piezaAVender.setDispsubasta(false);	
				piezaAVender.setDispventa(false);
				piezaAVender.setExhibida(false);
				piezaAVender.setPropietario(comprador);
				piezaAVender.getHistorialVentas().put(fecha, piezaAVender.getValor());
				piezaAVender.getHistorialDueños().add(comprador);
				
				int valorPiezaAVender = piezaAVender.getValor();
				int dineroActualizado = ((Comprador)comprador).getDinero() - valorPiezaAVender;
				((Comprador)comprador).setDinero(dineroActualizado);
				
				HashMap<String, Integer> fechasCompras = galeria.getFechasCompras();
				if (fechasCompras.containsKey(fecha)) {
					int comprasAntiguas = fechasCompras.get(fecha);
					fechasCompras.put(fecha, comprasAntiguas +1);
				}else {
					fechasCompras.put(fecha, 1);
				}
				
				}
			
			
			}
	
	
	public void venderPiezaTarjeta( Usuario  comprador, Pieza piezaAVender, Galeria galeria, String fecha, String nombrePasarela, String numeroTarjeta) throws DineroInsuficienteException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, IOException,ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
		
		
		Class clase = Class.forName(nombrePasarela);
		PasarelaPago pasarela = (PasarelaPago)  clase.getDeclaredConstructor(null).newInstance(null);
		
		if (((Comprador)comprador).getDinero() < piezaAVender.getValor()) {
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),false);
			throw new DineroInsuficienteException(comprador.getNombre());
			}
		else if (  ((Comprador)comprador).getPiezasActuales().contains(piezaAVender)) {
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),false);
			throw new MismoCompradorException();
		}
		else if (((Comprador)comprador).getValorMaximoCompras() < piezaAVender.getValor()) {
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),false);
			throw new ValorMaximoExcedidoException(comprador.getNombre());
		}
		
		else if (Galeria.esFechaValida2(fecha) == false) {
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),false);
			throw new FechaInvalidaException(fecha);
		}
		else if(piezaAVender.isConsignacion() == true) {
			
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),true);
			((Comprador)comprador).añadirPieza(piezaAVender, fecha);
			galeria.getPiezasActuales().remove(piezaAVender);
			
			//cambiar plata consignada
			int dineroActualizadoPropietarioAnterior = ((Comprador)piezaAVender.getPropietario()).getDinero() + piezaAVender.getValor();
			((Comprador)piezaAVender.getPropietario()).setDinero(dineroActualizadoPropietarioAnterior);
			((Comprador)piezaAVender.getPropietario()).setValorColeccionMenor(piezaAVender.getValor());
			((Comprador)piezaAVender.getPropietario()).getPiezasActuales().remove(piezaAVender); //Elimina de la lista de piezas actuales del propietario anterior
			
			
			piezaAVender.setConsignacion(false);
			piezaAVender.setDispsubasta(false);	
			piezaAVender.setExhibida(false);
			piezaAVender.setDispventa(false);
			piezaAVender.setPropietario(comprador);
			piezaAVender.getHistorialVentas().put(fecha, piezaAVender.getValor());
			piezaAVender.getHistorialDueños().add(comprador);
			
			galeria.añadirCompra(piezaAVender.getTitulo(), comprador.getNombre(), fecha, nombrePasarela, numeroTarjeta);
			int dineroActualizado = ((Comprador)comprador).getDinero() - piezaAVender.getValor();
			((Comprador)comprador).setDinero(dineroActualizado);
			
			
			HashMap<String, Integer> fechasCompras = galeria.getFechasCompras();
			if (fechasCompras.containsKey(fecha)) {
				int comprasAntiguas = fechasCompras.get(fecha);
				fechasCompras.put(fecha, comprasAntiguas +1);
			}else {
				fechasCompras.put(fecha, 1);
			}
			
		}
		else  {
			pasarela.registrarPago(comprador.getNombre(), comprador.getTelefono(), numeroTarjeta, piezaAVender.getValor(),true);
			((Comprador)comprador).añadirPieza(piezaAVender, fecha);;
			galeria.getPiezasActuales().remove(piezaAVender);
			piezaAVender.setConsignacion(false);
			piezaAVender.setDispsubasta(false);	
			piezaAVender.setDispventa(false);
			piezaAVender.setExhibida(false);
			piezaAVender.setPropietario(comprador);
			piezaAVender.getHistorialVentas().put(fecha, piezaAVender.getValor());
			piezaAVender.getHistorialDueños().add(comprador);
			galeria.añadirCompra(piezaAVender.getTitulo(), comprador.getNombre(), fecha, nombrePasarela, numeroTarjeta);
			
			int valorPiezaAVender = piezaAVender.getValor();
			int dineroActualizado = ((Comprador)comprador).getDinero() - valorPiezaAVender;
			((Comprador)comprador).setDinero(dineroActualizado);
			
			HashMap<String, Integer> fechasCompras = galeria.getFechasCompras();
			if (fechasCompras.containsKey(fecha)) {
				int comprasAntiguas = fechasCompras.get(fecha);
				fechasCompras.put(fecha, comprasAntiguas +1);
			}else {
				fechasCompras.put(fecha, 1);
			}
			
			
			}
	}
	
}
