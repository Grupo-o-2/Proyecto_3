package usuarios;

import java.util.ArrayList;
import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class Artista extends Usuario{
	
	private static final String TIPO = "Artista";

	private ArrayList<Pieza> piezasCreadas;
	
	public Artista(String login, String password, String nombre, String telefono) {
		super(login, password, nombre, telefono);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTipo() {
		return TIPO;
	}
}
