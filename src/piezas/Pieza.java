package piezas;

import java.util.*;

import modelo.*;
import usuarios.*;




abstract public class Pieza {
	
	private String titulo;
	private int valor;
	private String fechaCreacion; //Estaba el año pero con la fecha es suficiente
	private String lugarCreacion;
	private Usuario propietario;
	
	private ArrayList<Artista> autores; //cambiado
	private ArrayList<Usuario> historialDueños;  //nuevo
	private HashMap<Integer, String> historialVentas; // nuevo - Por cuánto ha sido vendida y cuándo
	
	private String exhibaVendaoSubasta;	
	private boolean consignacion;

	private boolean exhibida;
	private boolean dispsubasta;
	private boolean dispventa;

	
	public Pieza(String titulo,  int valor, String fechaCreacion, String lugarCreacion, Usuario propietario,  
			ArrayList<Artista> autores, ArrayList<Usuario> historialDueños, HashMap<Integer, String> historialVentas, 
			String exhibaVendaoSubasta, boolean consignacion,
			boolean exhibida, boolean dispsubasta, boolean dispventa) {
		
		this.titulo = titulo;
		this.valor = valor;
		this.fechaCreacion = fechaCreacion;
		this.lugarCreacion = lugarCreacion;
		this.propietario = propietario;
		this.autores = autores;
		this.historialDueños = historialDueños;
		this.historialVentas = historialVentas;
		this.exhibaVendaoSubasta = exhibaVendaoSubasta;
		this.consignacion = consignacion; 
		this.dispsubasta = dispsubasta;
		this.dispventa = dispventa;
		
		
	}

	public String getTipo() {
		return null;
	}
	
	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public String getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getLugarCreacion() {
		return lugarCreacion;
	}


	public void setLugarCreacion(String lugarCreacion) {
		this.lugarCreacion = lugarCreacion;
	}


	public Usuario getPropietario() {
		return propietario;
	}


	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}


	public ArrayList<Artista> getAutores() {
		return autores;
	}


	public void setAutores(ArrayList<Artista> autores) {
		this.autores = autores;
	}


	public ArrayList<Usuario> getHistorialDueños() {
		return historialDueños;
	}


	public void setHistorialDueños(ArrayList<Usuario> historialDueños) {
		this.historialDueños = historialDueños;
	}


	public HashMap<Integer, String> getHistorialVentas() {
		return historialVentas;
	}


	public void setHistorialVentas(HashMap<Integer, String> historialVentas) {
		this.historialVentas = historialVentas;
	}


	public String getExhibaVendaoSubasta() {
		return exhibaVendaoSubasta;
	}


	public void setExhibaVendaoSubasta(String exhibaVendaoSubasta) {
		this.exhibaVendaoSubasta = exhibaVendaoSubasta;
	}


	public boolean isConsignacion() {
		return consignacion;
	}


	public void setConsignacion(boolean consignacion) {
		this.consignacion = consignacion;
	}


	public boolean isExhibida() {
		return exhibida;
	}


	public void setExhibida(boolean exhibida) {
		this.exhibida = exhibida;
	}


	public boolean isDispsubasta() {
		return dispsubasta;
	}


	public void setDispsubasta(boolean dispsubasta) {
		this.dispsubasta = dispsubasta;
	}


	public boolean isDispventa() {
		return dispventa;
	}


	public void setDispventa(boolean dispventa) {
		this.dispventa = dispventa;
	}
	
	

	
}

