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
	
	public void generarConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta, String fechaActual) throws PropietarioErroneoException, FechaInvalida {
		ArrayList <Pieza> piezasUsuario = ((Comprador) propietario).getPiezasActuales();
		
		if ((piezasUsuario.contains(piezaAConsignar) == false)) {
			
			throw new PropietarioErroneoException(propietario.getNombre(), piezaAConsignar.getTitulo());
			
		}
		
		else if (Galeria.esFechaValida(fechaLimite) == false) {
			throw new FechaInvalida(fechaLimite);
		}
		
		else if (Galeria.esFechaValida(fechaActual) == false) {
			throw new FechaInvalida(fechaActual);
		}
		
		else if ( fechaLimite.compareTo(fechaActual) < 0) {
			throw new FechaInvalida();
		}
		else {
			
			
			galeria.getPiezasActuales().add(piezaAConsignar);
			galeria.getHistorialPiezas().add(piezaAConsignar);
			piezaAConsignar.setConsignacion(true);
			piezaAConsignar.setExhibaVendaoSubasta(exhibaVendaoSubasta);
			
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
	
	public void revisionFechaLimite(Pieza piezaConsignada, String fechaActual, Usuario propietario, Galeria galeria) {
		
		
		if (((piezaConsignada.getFechaLimite().compareTo(fechaActual) == 0) || 
				(piezaConsignada.getFechaLimite().compareTo(fechaActual) < 0) ) 
				&& (piezaConsignada.getPropietario() == propietario )){
			
			piezaConsignada.setConsignacion(false);
			piezaConsignada.setDispsubasta(false);
			galeria.getPiezasActuales().remove(piezaConsignada);
		}
	}
}
	
	
	

