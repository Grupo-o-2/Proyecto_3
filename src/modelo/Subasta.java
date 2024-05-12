package modelo;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;


import piezas.Pieza;

public class Subasta {
	private String nombre;
	private ArrayList<Usuario> participantes;
	private Usuario operador;
	private HashMap<Pieza,HashMap<Usuario, Integer>> registroSubasta;
	private HashMap<Pieza, ArrayList<Integer>> piezasSubastadas;
	
// El HashMap de piezas subastadas contiene como llaves las piezas subastadas y como valores Arrays los cuáles en 
// la primera posición tienen el valor mínimo de la pieza y en segunda posición el valor inicial de la misma ---- valorInicial -> conocido 
	
	
public Subasta(String nombre, ArrayList<Usuario> participantes, Usuario operador, HashMap<Pieza,HashMap<Usuario, 
		Integer>> registroSubasta, HashMap<Pieza, ArrayList<Integer>> piezasSubastadas) {
		this.nombre = nombre;
		this.participantes = participantes;
		this.operador = operador;
		this.registroSubasta = registroSubasta;
		this.piezasSubastadas = piezasSubastadas;
	}

public ArrayList<Usuario> getParticipantes() {
	return participantes;
}

	public String getNombre() {
	return nombre;
}

	public Usuario getOperador() {
	return operador;
}
	public HashMap<Pieza,HashMap<Usuario, Integer>> getRegistroSubasta()
	{
		return this.registroSubasta;
	}
	
	public HashMap<Pieza,ArrayList<Integer>> getPiezasSubastadas()
	{
		return this.piezasSubastadas;
	}

	public boolean revisarOferta(Pieza piezaEnRevision, int valorOfertado, Usuario ofertador ) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		Integer valorInicial = this.piezasSubastadas.get(piezaEnRevision).get(1);
		
		if (valorInicial > ((Integer)valorOfertado)) {
			
			throw new DineroOfrecidoInsuficienteException();
		}
		
		else if (((Comprador) ofertador).getDinero() < valorOfertado ) {
			throw new DineroInsuficienteException();	
		}
		
		else {
				return true;
			}
	}

	public void registrarOferta (Usuario ofertador, int valorOfertado, Pieza piezaSubastada ) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		
		if  ( (this.revisarOferta(piezaSubastada, valorOfertado, ofertador)) == true ){
			this.registroSubasta.get(piezaSubastada).put(ofertador, ((Integer)valorOfertado));
		}
	}
	
//Este método revisa si algún ofertador llego al valor mínimo de la o las piezas subastas, si es así se vende la pieza o las piezas 
// al usuario que haya hecho la oferta más alta.
	
	public void finalizarSubasta(Galeria galeria, String fecha) {
		
		registroSubasta.forEach( (pieza, ofertas) -> {
			
			ofertas.forEach((usuario, valor) -> {
				
				Usuario mejorPostor = null;
				Integer ofertaMayor = 0;
				
				if ( valor > ofertaMayor) {
					ofertaMayor = valor;
					mejorPostor = usuario;
					
				}
				
				Integer valorminimo = this.piezasSubastadas.get(pieza).get(0);
				
				if (ofertaMayor > valorminimo && pieza.isConsignacion() == true) {
					
					((Comprador)mejorPostor).añadirPieza(pieza, fecha);
					
					
					int dineroActualizadoPropietarioAnterior = ((Comprador)pieza.getPropietario()).getDinero() + ofertaMayor;
					((Comprador)pieza.getPropietario()).setDinero(dineroActualizadoPropietarioAnterior);
					((Comprador)pieza.getPropietario()).setValorColeccionMenor(ofertaMayor);
					((Comprador)pieza.getPropietario()).getPiezasActuales().remove(pieza);
					
					pieza.setDispsubasta(false);
					pieza.setDispventa(false);
					pieza.setPropietario(usuario);
					pieza.setConsignacion(false);
					pieza.setExhibida(false);
					galeria.getPiezasActuales().remove(pieza);
					int dineroActualizado = ((Comprador)mejorPostor).getDinero() - ofertaMayor;
					((Comprador)mejorPostor).setValorColeccion(ofertaMayor);
					((Comprador)mejorPostor).setDinero(dineroActualizado);
				}
				else if (ofertaMayor > valorminimo) {
					
					
					((Comprador)mejorPostor).añadirPieza(pieza, fecha);
					pieza.setDispsubasta(false);
					pieza.setDispventa(false);
					pieza.setPropietario(usuario);
					pieza.setConsignacion(false);
					pieza.setExhibida(false);
					galeria.getPiezasActuales().remove(pieza);
					int dineroActualizado = ((Comprador)mejorPostor).getDinero() - ofertaMayor;
					((Comprador)mejorPostor).setValorColeccion(ofertaMayor);
					((Comprador)mejorPostor).setDinero(dineroActualizado);
				}
			});
		});
		
	}

	
	
}

