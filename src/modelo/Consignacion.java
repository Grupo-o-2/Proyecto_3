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
	
	public void generarConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta) throws PropietarioErroneoException {
		ArrayList <Pieza> piezasUsuario = ((Comprador) propietario).getPiezasActuales();
		
		if ((piezasUsuario.contains(piezaAConsignar) == false)) {
			
			throw new PropietarioErroneoException(propietario.getNombre(), piezaAConsignar.getTitulo());
			
		}
		else {
			
			piezaAConsignar.setFechaCreacion(fechaLimite);
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
		
		
		if (((piezaConsignada.getFechaCreacion().compareTo(fechaActual) == 0) || 
				(piezaConsignada.getFechaCreacion().compareTo(fechaActual) > 0) ) 
				&& (piezaConsignada.getPropietario() == propietario )){
			
			piezaConsignada.setConsignacion(false);
			piezaConsignada.setDispsubasta(false);
			galeria.getPiezasActuales().remove(piezaConsignada);
		}
	}
}
	
	
	

