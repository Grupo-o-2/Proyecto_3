package piezas;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.*;
import usuarios.*;

public class Pintura extends Pieza{
	private double alto;
	private double ancho;
	private static final String TIPO = "Pintura";
	
	public Pintura(String titulo, int valor, String fecha, String lugar, Usuario propietario,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, double alto, double ancho ) {
		super(titulo, valor, fecha, lugar, propietario, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida,
				dispsubasta, dispventa);
		
		this.alto = alto;
		this.ancho = ancho;
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

		
}