package persistencia;

import piezas.*; 
import usuarios.*;
import modelo.*;
import fabrica.*;
import exceptions.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;
public class PersistenciaUsuarios {
	
	private static final String tipoAdministrador = "Administrador";
	private static final String tipoCajero = "Cajero";
	private static final String tipoComprador = "Comprador";
	private static final String tipoEmpleado = "Empleado";
	private static final String tipoOperador = "Operador";
	private static final String tipoArtista = "Artista";
	private static final String login = "Login";
	private static final String password = "Password";
	private static final String nombre = "Nombre";
	private static final String telefono = "Telefono";
	private static final String tipo = "Tipo ";

	public PersistenciaUsuarios() {

	}

	public void salvarUsuarios(ArrayList<Usuario> usuarios, JSONObject jobject, HashMap<Pieza, String> identificadorPieza) 
	{
		JSONArray jUsuarios = new JSONArray( );
		for (Usuario usuario: usuarios) {

			if (tipoComprador.equals(usuario.getTipo()))
			{
				salvarComprador((Comprador) usuario, jUsuarios, identificadorPieza );
			}
			else if (tipoAdministrador.equals(usuario.getTipo()) || "Cajero".equals(usuario.getTipo()) || "Operador".equals(usuario.getTipo()))
			{
				salvarAdministradorOperadorCajero(usuario, jUsuarios);
			}
			else if (tipoEmpleado.equals(usuario.getTipo()))
			{
				salvarEmpleado((Empleado) usuario, jUsuarios);
			}
			else if (tipoArtista.equals(usuario.getTipo()))
			{
				salvarArtista((Artista) usuario, jUsuarios);
			}
		}
		jobject.put("Usuarios", jUsuarios);
	}

	public void salvarComprador(Comprador comprador, JSONArray jCompradores, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, comprador.getLogin() );
		jComprador.put( password, comprador.getPassword() );
		jComprador.put( nombre, comprador.getNombre( ) );
		jComprador.put( "Dinero", comprador.getDinero( ) );
		jComprador.put( telefono, comprador.getTelefono() );
		jComprador.put( "Valor Maximo Compras", comprador.getValorMaximoCompras( ) );
		jComprador.put("Valor Coleccion", comprador.getValorColeccion());
		String piezasActuales = "";
		if (! comprador.getPiezasActuales().isEmpty())
		{
			for (Pieza pieza: comprador.getPiezasActuales())
			{
				piezasActuales = piezasActuales + identificadorPieza.get(pieza) + ",";
				comprador.getHistorialPiezas().remove(pieza);
			}
		}
		else
		{
			piezasActuales= "Vacío";
		}
		jComprador.put( "Piezas Actuales", piezasActuales );
		String historialPiezas = "";
		HashMap<Pieza, String> historialPiezasComprador = comprador.getHistorialPiezas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (Pieza pieza: comprador.getHistorialPiezas().keySet())
			{
				historialPiezas = historialPiezas + identificadorPieza.get(pieza) + "," + historialPiezasComprador.get(pieza) + ";";
			}
		}
		else
		{
			historialPiezas= "Vacío";
		}
		jComprador.put( "Historial Piezas", historialPiezas);
		jComprador.put( tipo, comprador.getTipo( ) );

		jCompradores.put( jComprador );
	}
	
	public void salvarArtista(Artista artista, JSONArray jArtistas)
	{
		JSONObject jArtista = new JSONObject( );
		jArtista.put( login, artista.getLogin() );
		jArtista.put( password, artista.getPassword() );
		jArtista.put( nombre, artista.getNombre( ) );
		jArtista.put( telefono, artista.getTelefono() );
		jArtista.put( tipo, artista.getTipo( ) );
		jArtistas.put( jArtista );
	}
	
	public void salvarAdministradorOperadorCajero(Usuario usuario, JSONArray jCompradores)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, usuario.getLogin() );
		jComprador.put( password, usuario.getPassword() );
		jComprador.put( nombre, usuario.getNombre( ) );
		jComprador.put( telefono, usuario.getTelefono() );
		jComprador.put( tipo, usuario.getTipo( ) );

		jCompradores.put( jComprador );
	}
	
	public void salvarEmpleado(Empleado empleado, JSONArray jCompradores)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, empleado.getLogin() );
		jComprador.put( password, empleado.getPassword() );
		jComprador.put( nombre, empleado.getNombre( ) );
		jComprador.put( telefono, empleado.getTelefono() );
		jComprador.put( tipo, empleado.getTipo( ) );

		jCompradores.put( jComprador );
	}

	public void cargarUsuarios(JSONArray jUsuarios, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<Comprador , String> piezasActuales,
			HashMap<Comprador , String> historialCompradores) throws LoginException
	{
		Fabrica fabrica = galeria.getFabrica(); 
		int numeroCompradores = jUsuarios.length();
		for (int i = 0 ; i < numeroCompradores ; i++)
		{
			JSONObject usuario = jUsuarios.getJSONObject( i );
			String tipoUsuario = usuario.getString(tipo);
				if (tipoComprador.equals(tipoUsuario))
				{
					cargarComprador(usuario, galeria, loginUsuarios, piezasActuales ,historialCompradores, fabrica);
				}
				else if (tipoEmpleado.equals(tipoUsuario))
				{
					cargarEmpleado(usuario, galeria, loginUsuarios, fabrica);
				}
				else if (tipoAdministrador.equals(tipoUsuario))
				{
					cargarAdministrador(usuario, galeria, loginUsuarios, fabrica);						
				}
				else if (tipoCajero.equals(tipoUsuario))
				{
					cargarCajero(usuario, galeria, loginUsuarios, fabrica);
				}
				else if (tipoOperador.equals(tipoUsuario))
				{
					cargarOperador(usuario, galeria, loginUsuarios, fabrica);
				}
				else if (tipoArtista.equals(tipoUsuario))
				{
					cargarArtista(usuario, galeria, loginUsuarios, fabrica);
				}
		}

	}
	
	public void cargarComprador(JSONObject jComprador, Galeria galeria, HashMap<String, Usuario> loginUsuarios,
			HashMap<Comprador , String> piezasActuales, HashMap<Comprador , String> historialCompradores, Fabrica fabrica) throws LoginException
	{
			String nLogin = jComprador.getString(login);
			String nPassword = jComprador.getString(password);
			String nNombre = jComprador.getString(nombre);
			int nDinero = jComprador.getInt("Dinero");
			int nValorMaximoCompras = jComprador.getInt("Valor Maximo Compras");
			String nTelefono = jComprador.getString(telefono);
			int nValorColeccion = jComprador.getInt("Valor Coleccion");
			HashMap<Pieza, String> nHistorialPiezas = new HashMap<Pieza, String>();
			ArrayList<Pieza> nPiezasActuales = new ArrayList<Pieza>();
			Comprador nComprador = fabrica.crearComprador(nLogin, nPassword, nNombre, nValorMaximoCompras, nValorColeccion, nHistorialPiezas, nPiezasActuales, nDinero, nTelefono, galeria);
			loginUsuarios.put(nLogin, nComprador);
			String nHistorialPiezasString = jComprador.getString("Piezas Actuales");
			if (! nHistorialPiezasString.equals("Vacío"))
			{
				piezasActuales.put(nComprador,nHistorialPiezasString );
			}
			String nPiezasActualesString = jComprador.getString("Historial Piezas");
			if (! nPiezasActualesString.equals("Vacío"))
			{
				historialCompradores.put(nComprador, nPiezasActualesString);
			}
	}
	
	
	
	
	public void cargarArtista(JSONObject jArtista, Galeria galeria, HashMap<String, Usuario> loginUsuarios, Fabrica fabrica) throws LoginException
	{
			String nLogin = jArtista.getString(login);
			String nPassword = jArtista.getString(password);
			String nNombre = jArtista.getString(nombre);
			String nTelefono = jArtista.getString(telefono);
			ArrayList<Pieza> nPiezasCreadas = new ArrayList<Pieza>();
			Artista nArtista = fabrica.crearArtista(nLogin, nPassword, nNombre, nTelefono, nPiezasCreadas, galeria);
			loginUsuarios.put(nLogin, nArtista);			
		}
	
	public void cargarAdministrador(JSONObject jAdministrador, Galeria galeria,  HashMap<String, Usuario> loginUsuarios,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jAdministrador.getString(login);
			String nPassword = jAdministrador.getString(password);
			String nNombre = jAdministrador.getString(nombre);
			String nTelefono = jAdministrador.getString(telefono);
			Administrador nAdministrador = fabrica.crearAdministrador(nLogin, nPassword,nTelefono, nNombre, galeria);
			loginUsuarios.put(nLogin, nAdministrador);
		}
	
	public void cargarCajero(JSONObject jCajero, Galeria galeria,  HashMap<String, Usuario> loginUsuarios,
			Fabrica fabrica) throws LoginException
	{
		String nLogin = jCajero.getString(login);
		String nPassword = jCajero.getString(password);
		String nNombre = jCajero.getString(nombre);
		String nTelefono = jCajero.getString(telefono);
		Cajero nCajero = fabrica.crearCajero(nLogin, nPassword,nTelefono, nNombre, galeria);
		loginUsuarios.put(nLogin, nCajero);
		}
	
	public void cargarOperador(JSONObject jOperador, Galeria galeria,  HashMap<String, Usuario> loginUsuarios,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jOperador.getString(login);
			String nPassword = jOperador.getString(password);
			String nNombre = jOperador.getString(nombre);
			String nTelefono = jOperador.getString(telefono);
			Operador nOperador = fabrica.crearOperador(nLogin, nPassword, nTelefono, nNombre, galeria);
			loginUsuarios.put(nLogin, nOperador);
		}

	public void cargarEmpleado(JSONObject jEmpleado, Galeria galeria, HashMap<String, Usuario> loginUsuarios,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jEmpleado.getString(login);
			String nPassword = jEmpleado.getString(password);
			String nNombre = jEmpleado.getString(nombre);
			String nTelefono = jEmpleado.getString(telefono);
			Empleado nEmpleado = fabrica.crearEmpleado(nLogin, nPassword, nTelefono, nNombre, galeria);
			loginUsuarios.put(nLogin, nEmpleado);
		}	
}
