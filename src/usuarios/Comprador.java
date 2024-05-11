package usuarios;

import java.util.ArrayList;

import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class Comprador extends Usuario{
	
	private int dinero;
	private String telefono;
	private int valorMaximoCompras;
	private int valorColeccion;
	private HashMap<Pieza, String> historialPiezas;
	private ArrayList<Pieza> piezasActuales;
	private static final String TIPO = "Comprador";
	
	public Comprador(String login, String password,  String nombre, int valorMaximoCompras, int valorColeccion, 
			HashMap<Pieza, String> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, String telefono) {
		super(login, password, nombre, telefono);
		this.dinero = dinero;
		this.telefono = telefono;
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
	
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getValorColeccion() {
		return valorColeccion;
	}

	public void setValorColeccion(int nuevoValor) {
		this.valorColeccion = this.valorColeccion + nuevoValor;
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
	
	public void añadirPieza(Pieza pieza, String fecha){
		this.historialPiezas.put(pieza, fecha);
		this.piezasActuales.add(pieza);
		this.valorColeccion+= pieza.getValor();
	}
	
	public void añadirPiezaHistorial(Pieza pieza, String fecha){
		this.historialPiezas.put(pieza, fecha);
	}
	
	public void consignarPieza(Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta ) throws PropietarioErroneoException {
			galeria.realizarConsignacion(this, piezaAConsignar, fechaLimite, galeria, exhibaVendaoSubasta);	
	}
	
	public void comprarPieza(Pieza piezaAcomprar, Galeria galeria, String fecha) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
		((Cajero)galeria.getUnCajero()).venderPieza(this, piezaAcomprar, galeria, fecha);
	}
	
	public void realizarOfertaEnSubasta(Pieza piezaSubastada, int valorOfertado, Subasta subasta ) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		( (Operador )subasta.getOperador() ).registrarOferta(valorOfertado, piezaSubastada, this, subasta);
	}
}