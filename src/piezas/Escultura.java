package piezas;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.*;
import usuarios.*;

public class Escultura extends Pieza {

	private double alto; //Se cambio alto, ancho, profundidad y peso de int a double
	private double ancho;
	private double profundidad;
	private String materiales;
	private double peso;
	private boolean electricidad;
	private boolean otroDetalle;
	private static final String TIPO = "Escultura";
	
	public Escultura(String titulo, int valor, String fecha, String lugar, Usuario propietario,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, double alto, double ancho, double profundidad, String materiales, double peso, boolean electricidad, boolean otroDetalle) {
		super(titulo, valor, fecha, lugar, propietario, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida,
				dispsubasta, dispventa);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.materiales = materiales;
		this.peso = peso;
		this.electricidad = electricidad;
		this.otroDetalle = otroDetalle;
		
	}
	
	
	public String getTipo() {
		return TIPO;
	}
	
	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public String getMateriales() {
		return materiales;
	}

	public double getPeso() {
		return peso;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public boolean isOtroDetalle() {
		return otroDetalle;
	}

	

}
	

