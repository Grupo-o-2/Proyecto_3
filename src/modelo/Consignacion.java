package modelo;

import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class Consignacion {
	private Usuario propietario;
	private Pieza piezaAConsignar;
	
	public Consignacion(Usuario propietario, Pieza piezaAConsignar) {
		this.propietario = propietario;
		this.piezaAConsignar = piezaAConsignar;
	}
	
	public void generarConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta, String fechaActual) throws PropietarioErroneoException, FechaInvalidaException, ConsignacionExistenteException {
		ArrayList <Pieza> piezasUsuario = ((Comprador) propietario).getPiezasActuales();
		
		if ((piezasUsuario.contains(piezaAConsignar) == false)) {
			
			throw new PropietarioErroneoException(propietario.getNombre(), piezaAConsignar.getTitulo());
			
		}
		
		else if (Galeria.esFechaValida(fechaLimite) == false) {
			throw new FechaInvalidaException(fechaLimite);
		}
		
		else if (Galeria.esFechaValida(fechaActual) == false) {
			throw new FechaInvalidaException(fechaActual);
		}
		
		else if ( fechaLimite.compareTo(fechaActual) < 0) {
			throw new FechaInvalidaException();
		}
		
		else if (piezaAConsignar.isConsignacion() == true) {
			throw new ConsignacionExistenteException(piezaAConsignar.getTitulo());
		}
		else {
			
			
			galeria.getPiezasActuales().add(piezaAConsignar);
			galeria.getHistorialPiezas().add(piezaAConsignar);
			piezaAConsignar.setConsignacion(true);
			piezaAConsignar.setExhibaVendaoSubasta(exhibaVendaoSubasta);
			piezaAConsignar.setFechaLimite(fechaLimite);
			
			if ( exhibaVendaoSubasta.contains("3")) {
				piezaAConsignar.setDispsubasta(true);
			}
			if (exhibaVendaoSubasta.contains("2")) {
				piezaAConsignar.setDispventa(true);
			}
			if (exhibaVendaoSubasta.contains("1")) {
				piezaAConsignar.setExhibida(true);;
			}
			
		}
	}
	
	public boolean revisionFechaLimite(Pieza piezaConsignada, String fechaActual, Usuario propietario, Galeria galeria) throws FechaInvalidaException {
		
		if (Galeria.esFechaValida(fechaActual) == false) {
			throw new FechaInvalidaException(fechaActual);
		}
		
		else if (((piezaConsignada.getFechaLimite().compareTo(fechaActual) == 0) || 
				(piezaConsignada.getFechaLimite().compareTo(fechaActual) < 0) ) 
				&& (piezaConsignada.getPropietario() == propietario )){
			
			piezaConsignada.setConsignacion(false);
			piezaConsignada.setDispsubasta(false);
			piezaConsignada.setDispventa(false);
			piezaConsignada.setExhibida(false);
			galeria.getPiezasActuales().remove(piezaConsignada);
			
			return true;
		}
		
		else {
			return false;
		}
	}
}
	
	
	

