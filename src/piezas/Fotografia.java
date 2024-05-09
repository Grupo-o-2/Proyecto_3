package piezas;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.*;
import usuarios.*;

public class Fotografia extends Pieza{

	private double alto;
	private double ancho;
	private String formato;
	private boolean enmarcado;
	private static final String TIPO = "Fotografia";
	
	public Fotografia(String titulo, int valor, String fecha, String lugar, Usuario propietario,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<Integer, String> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, double alto,double ancho, String formato, boolean enmarcado ) {
		super(titulo, valor, fecha, lugar, propietario, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida,
				dispsubasta, dispventa);
	
		this.alto = alto;
		this.ancho = ancho;
		this.formato = formato;
		this.enmarcado = enmarcado;
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

	public String getFormato() {
		return formato;
	}

	public boolean isEnmarcado() {
		return enmarcado;
	}


}
