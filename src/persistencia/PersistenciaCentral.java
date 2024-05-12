package persistencia;

import piezas.*; 
import usuarios.*;
import modelo.*;
import fabrica.*;
import exceptions.*;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersistenciaCentral {

	private PersistenciaPiezas persistenciaPiezas = new PersistenciaPiezas();
	private PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
	private PersistenciaSubastas persistenciaSubastas = new PersistenciaSubastas();
	private PersistenciaGalerias persistenciaGalerias = new PersistenciaGalerias();
	
	public PersistenciaCentral()
	{
		
	}
	
	public void salvarGaleria(String archivo, ArrayList<Usuario> usuarios, ArrayList<Pieza> piezas, Galeria galeria) throws IOException
	{
		JSONObject jobject = new JSONObject( );
		HashMap<Pieza, String> identificadorPieza = new HashMap<Pieza, String>();
		persistenciaPiezas.salvarPiezas(piezas, jobject, identificadorPieza);
		persistenciaUsuarios.salvarUsuarios(usuarios, jobject, identificadorPieza);
		persistenciaSubastas.salvarSubastas(galeria.getSubastas(), jobject, identificadorPieza);
		persistenciaGalerias.salvarGaleria(galeria, jobject, identificadorPieza);
		File ruta = new File("Persistencia\\Galeria");
		File carpeta= new File(ruta.getAbsolutePath());
		File nArchivo = new File(carpeta, archivo);
		PrintWriter pw = new PrintWriter( new FileWriter(nArchivo) );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}

	public void cargarGaleria(String archivo, Galeria galeria) throws IOException, LoginException, LoginInexistenteException, UsuarioInexistenteException
	{
		HashMap<String, Usuario> loginUsuarios = new HashMap<String, Usuario>();
		HashMap<String, Pieza> identificacionPieza = new HashMap<String, Pieza>();
		HashMap<Comprador , String> historialCompradores = new HashMap<Comprador, String>();
		HashMap<Comprador , String> piezasActuales = new HashMap<Comprador, String>();
		File ruta = new File("Persistencia\\Galeria");
		File carpeta= new File(ruta.getAbsolutePath());
		String jsonCompleto = new String( Files.readAllBytes( new File(carpeta,archivo ).toPath( ) ) );
		JSONObject raiz = new JSONObject( jsonCompleto );

		JSONArray usuarios = raiz.getJSONArray( "Usuarios" ); 
		persistenciaUsuarios.cargarUsuarios(usuarios , galeria, loginUsuarios, piezasActuales, historialCompradores);
		JSONArray piezas = raiz.getJSONArray( "Piezas" );
		persistenciaPiezas.cargarPiezas(piezas, galeria, loginUsuarios, identificacionPieza);
		JSONArray subastas = raiz.getJSONArray( "Subastas" );
		persistenciaSubastas.cargarSubastas(subastas, galeria, loginUsuarios, identificacionPieza);
		JSONObject pGaleria = raiz.getJSONObject( "Galeria" );
		persistenciaGalerias.cargarGalerias(pGaleria, galeria, identificacionPieza);
		if (! historialCompradores.keySet().isEmpty())
			for (Comprador comprador: historialCompradores.keySet())
			{
				for (String llaveValor : historialCompradores.get(comprador).split(";"))
				{
					comprador.añadirPiezaHistorial(identificacionPieza.get(llaveValor.split(",")[0]), identificacionPieza.get(llaveValor.split(",")[0]).getFechaCreacion());
				}
			}		
	}

	public PersistenciaPiezas getPersistenciaPiezas() {
		return persistenciaPiezas;
	}

	public void setPersistenciaPiezas(PersistenciaPiezas persistenciaPiezas) {
		this.persistenciaPiezas = persistenciaPiezas;
	}

	public PersistenciaUsuarios getPersistenciaUsuarios() {
		return persistenciaUsuarios;
	}

	public void setPersistenciaUsuarios(PersistenciaUsuarios persistenciaUsuarios) {
		this.persistenciaUsuarios = persistenciaUsuarios;
	}
	
	
	
}