package usuarios;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;


public class Cajero extends Empleado {
	
	private static final String TIPO = "Cajero";
	
	public Cajero(String login, String password, String telefono, String nombre) {
		super(login, password, telefono, nombre);
		// TODO Auto-generated constructor stub
	}

	public String getTipo() {
		
		return TIPO;
	}

	public void venderPieza (Comprador comprador, Pieza pieza, Galeria galeria, String fecha) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException {
		
		((Administrador )galeria.getAdministrador()).verificacionDeCompra(pieza, comprador, galeria, fecha);
		}
	
public void venderPiezaTarjeta (Comprador comprador, Pieza pieza, Galeria galeria, String fecha, String nombrePasarela, String numeroTarjeta) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoCompradorException, ValorMaximoExcedidoException, FechaInvalidaException, IOException,ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		((Administrador )galeria.getAdministrador()).verificacionDeCompraTarjeta(pieza, comprador, galeria, fecha, nombrePasarela, numeroTarjeta);
			
		}
	}

	
