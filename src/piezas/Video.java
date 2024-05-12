package piezas;

import java.util.ArrayList;
import java.util.HashMap;

import fabrica.Fabrica;
import modelo.*;
import usuarios.*;

public class Video extends Pieza {

	private double alto;
	private double ancho;
	private int duracion;
	private String formato;
	private static final String TIPO= "Video";
	
	public Video(String titulo, int valor, String fecha, String lugar, Usuario propietario,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa,  String fechaLimite, double alto, double ancho, int duracion, String formato) {
		super(titulo, valor, fecha, lugar, propietario, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida,
				dispsubasta, dispventa, fechaLimite);
		this.alto = alto;
		this.ancho = ancho;
		this.duracion = duracion;
		this.formato = formato;
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

	public int getDuracion() {
		return duracion;
	}

	public String getFormato() {
		return formato;
	}
}