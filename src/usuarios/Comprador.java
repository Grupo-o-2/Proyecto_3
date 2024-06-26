package usuarios;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class Comprador extends Usuario{
	
	private int dinero;
	private int valorMaximoCompras;
	private int valorColeccion;
	private HashMap<Pieza, String> historialPiezas; //Pieza con fecha
	private ArrayList<Pieza> piezasActuales;
	private static final String TIPO = "Comprador";
	
	public Comprador(String login, String password,  String nombre, int valorMaximoCompras, int valorColeccion, 
			HashMap<Pieza, String> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, String telefono) {
		super(login, password, nombre, telefono);
		this.dinero = dinero;
		this.valorMaximoCompras = valorMaximoCompras;
		this.historialPiezas = historialPiezas;
		this.piezasActuales = piezasActuales;	}

	public int getValorMaximoCompras() {
		return valorMaximoCompras;
	}

	public String getTipo() {
		return TIPO;
	}
	
	public void setValorMaximoCompras(int valorMaximoCompras) {
		this.valorMaximoCompras = valorMaximoCompras;
	}

	public HashMap<Pieza, String> getHistorialPiezas() {
		return historialPiezas;
	}

	public void setHistorialPiezas(HashMap<Pieza, String> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}

	public ArrayList<Pieza> getPiezasActuales() {
		return piezasActuales;
	}

	public void setPiezasActuales(ArrayList<Pieza> piezasActuales) {
		this.piezasActuales = piezasActuales;
	}

	public int getDinero() {
		return dinero;
	}
	
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getValorColeccion() {
		return this.valorColeccion;
	}

	public void setValorColeccion(int nuevoValor) {
		this.valorColeccion = this.valorColeccion + nuevoValor;
	}
	
	public void setValorColeccionMenor(int nuevoValor) {
		this.valorColeccion = this.valorColeccion - nuevoValor;
	}

	public  String revisarEstadoPieza( Pieza pieza) {
		String estado = null;
		if (pieza.isExhibida() == true) {
			estado = "La pieza se encuentra exhibida";
		}
		else {
			estado = "La pieza se encuentra en bodega";
		}
		return estado;
	}
	
	public ArrayList<Pieza> obtenerPiezasAntiguas(){
		ArrayList<Pieza> piezasAntiguas = new ArrayList<Pieza>();
		
		for (Pieza pieza : this.historialPiezas.keySet()) {
			if (this.piezasActuales.contains(pieza) != true) {
				piezasAntiguas.add(pieza);
			}
		}
		
		return piezasAntiguas;
	}
	
	public void añadirPieza(Pieza pieza, String fecha){
		this.historialPiezas.put(pieza, fecha);
		this.piezasActuales.add(pieza);
		valorColeccion += pieza.getValor();
	}
	
	public void añadirPiezaHistorial(Pieza pieza, String fecha){
		this.historialPiezas.put(pieza, fecha);
	}
	
	public Pieza obtenerPiezaporTitulo(String titulo) {
		for (Pieza pieza:this.piezasActuales) {
			if (pieza.getTitulo().compareTo(titulo) == 0) {
				return pieza;
			}
		}
		return null;
	}
	
	
	public String obtenerFechaAdquisicion(Pieza pieza) {
		
		return this.historialPiezas.get(pieza);
	}
	
	public boolean solicitudAumentoValorCompra(Galeria galeria) {
		
		if ( ((Administrador)galeria.getAdministrador()).verificarValorMaximo(this) == true){
			((Administrador)galeria.getAdministrador()).aumentarValorMaximo(this, this.getDinero());	
			return true;
		}
		
		else return false;
	}
	
	 
	
	public void consignarPieza(Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta, String fechaActual ) throws PropietarioErroneoException, FechaInvalidaException, ConsignacionExistenteException {
			galeria.realizarConsignacion(this, piezaAConsignar, fechaLimite, galeria, exhibaVendaoSubasta, fechaActual);	
	}
	
	public void comprarPieza(Pieza piezaAcomprar, Galeria galeria, String fecha) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException {
		((Cajero)galeria.getUnCajero()).venderPieza(this, piezaAcomprar, galeria, fecha);
	}
	
	public void comprarPiezaTarjeta(Pieza piezaAcomprar, Galeria galeria, String fecha, String nombrePasarela, String numeroTarjeta) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, IOException,ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		((Cajero)galeria.getUnCajero()).venderPiezaTarjeta(this, piezaAcomprar, galeria, fecha, nombrePasarela, numeroTarjeta);
	}
	
	public void realizarOfertaEnSubasta(Pieza piezaSubastada, int valorOfertado, Subasta subasta, Galeria galeria ) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		( (Operador )subasta.getOperador() ).registrarOferta(valorOfertado, piezaSubastada, this, subasta, galeria);
	}
}