package usuarios;
import java.util.ArrayList;

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

	public void venderPieza (Comprador comprador, Pieza pieza, Galeria galeria, String fecha) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException, MismoComprador, ValorMaximoExcedido {
		
		((Administrador )galeria.getAdministrador()).verificacionDeCompra(pieza, comprador, galeria, fecha);
			
		}
	}

