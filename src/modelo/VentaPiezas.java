package modelo;

import java.util.*; 
import fabrica.*;
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
				((Comprador)comprador).setValorColeccion(piezaAVender.getValor());
				((Comprador)comprador).setDinero(dineroActualizado);
				
				
				
			}
			else  {
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
				((Comprador)comprador).setValorColeccion(piezaAVender.getValor());
				((Comprador)comprador).setDinero(dineroActualizado);
				
				}
			
			
			}
	
	
}
