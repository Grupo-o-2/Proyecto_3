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

public class PersistenciaPiezas {
	
	private static final String tipoEscultura = "Escultura";
	private static final String tipoFotografia = "Fotografia";
	private static final String tipoImpresion = "Impresion";
	private static final String tipoPintura = "Pintura";
	private static final String tipoVideo = "Video";
	private static final String identificador = "Identificador";
	private static final String titulo = "Titulo";
	private static final String fechaCreacion = "fechaCreacion";
	private static final String lugarCreacion = "LugarCreacion";
	private static final String exhibida = "Exhibida";
	private static final String valor = "Valor";
	private static final String exhibaVendaoSubasta= "Exhiba venda o subasta";
	private static final String tipo = "Tipo";
	private static final String consignacion = "Consignacion";
	private static final String dispsubasta = "Dispsubasta";
	private static final String dispventa = "Dispventa";
	
	public PersistenciaPiezas() {

	}

	public void salvarPiezasCreadas (String archivo, ArrayList<Pieza> piezas) throws IOException
	{
		JSONObject jobject = new JSONObject( );
		HashMap<Pieza, String> identificadorPieza = new HashMap<Pieza,String>();
		salvarPiezas(piezas, jobject, identificadorPieza);
		File carpeta = new File("C:\\Users\\naran\\Desktop\\workspace_eclipse\\Proyecto-1\\Entrega 2\\ProyectoGaleria\\Persistencia\\Piezas");
		File nArchivo = new File(carpeta, archivo);
		PrintWriter pw = new PrintWriter( new FileWriter(nArchivo) );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}


	public void salvarPiezas(ArrayList<Pieza> piezas, JSONObject jobject, HashMap<Pieza, String> identificadorPieza ) 
	{
		if (piezas.size()!=0)
		{
			int identificador = 0;
			JSONArray jPiezas = new JSONArray( );
			for (Pieza pieza: piezas) {
				String sIdentificador = String.valueOf(identificador);

				if (tipoVideo.equals(pieza.getTipo()))
				{
					salvarVideos((Video) pieza, jPiezas, sIdentificador, identificadorPieza );
				}
				else if (tipoPintura.equals(pieza.getTipo()))
				{
					salvarPinturas((Pintura) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoFotografia.equals(pieza.getTipo()))
				{
					salvarFotografias((Fotografia) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoImpresion.equals(pieza.getTipo()))
				{
					salvarImpresiones((Impresion) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoEscultura.equals(pieza.getTipo()))
				{
					salvarEsculturas((Escultura) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				identificador++;
			}
			jobject.put("Piezas", jPiezas);
		}
		else 
		{
			jobject.put("Piezas", "Vacio");
		}
	}

	public void salvarVideos (Video video, JSONArray jVideos, String sIdentificador,  HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jVideo = new JSONObject( );
		jVideo.put( identificador, sIdentificador );
		jVideo.put( titulo, video.getTitulo( ) );
		jVideo.put( fechaCreacion, video.getFechaCreacion( ) );
		jVideo.put( lugarCreacion, video.getLugarCreacion( ) );
		ArrayList<Artista> artistas = video.getAutores( );
		String autores = "";
		if (! artistas.isEmpty())
		{
			for (Artista artista: artistas)
			{
				autores = autores + artista.getLogin() + ",";
			}
		}
		else
		{
			autores = "Vacio";
		}
		jVideo.put( "Autores", autores );
		ArrayList<Usuario> dueños = video.getHistorialDueños( );
		String dueño = "";
		if (! dueños.isEmpty())
		{
			for (Usuario usuario: dueños)
			{
				dueño = dueño + usuario.getLogin()+ ",";
			}
		}
		else
		{
			dueño = "Vacio";
		}
		jVideo.put( "Historial Dueños", dueño );
		String fechaPrecio = "";
		if (! video.getHistorialVentas().keySet().isEmpty())
		{
			for (String fecha: video.getHistorialVentas().keySet())
			{
				fechaPrecio = fechaPrecio + fecha + "," + video.getHistorialVentas().get(fecha) + ";";
			}
		}
		else
		{
			fechaPrecio = "Vacio";
		}
		jVideo.put( "Historial Dueños", fechaPrecio );
		String historialVentas = "";
		HashMap<String, Integer> historialPiezasComprador = video.getHistorialVentas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (String fecha: historialPiezasComprador.keySet())
			{
				historialVentas = historialVentas + "," + fecha + ";" + historialPiezasComprador.get(fecha).toString() ;
			}
		}
		else
		{
			historialVentas = "Vacio";
		}
		jVideo.put( "Fecha Precio", historialVentas);
		jVideo.put( exhibida, video.isExhibida() );
		jVideo.put( valor, String.valueOf(video.getValor( )));
		jVideo.put( "Proprietario", video.getPropietario( ).getLogin() );
		jVideo.put( exhibaVendaoSubasta , video.getExhibaVendaoSubasta() );
		jVideo.put( tipo, video.getTipo( ) );
		jVideo.put( "Alto", String.valueOf(video.getAlto( )) );
		jVideo.put( "Ancho", String.valueOf(video.getAncho( )) );
		jVideo.put( "Duracion", String.valueOf(video.getDuracion( )) );
		jVideo.put( "Formato", video.getFormato( ) );
		jVideo.put( consignacion, video.isConsignacion( ) );
		jVideo.put( dispsubasta, video.isDispsubasta( ) );
		jVideo.put( dispventa, video.isDispventa( ) );
		identificadorPieza.put(video, sIdentificador);

		jVideos.put( jVideo );
	}

	public void salvarPinturas (Pintura pintura, JSONArray jPinturas, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jPintura = new JSONObject( );
		jPintura.put( identificador, sIdentificador );
		jPintura.put( titulo, pintura.getTitulo( ) );
		jPintura.put( fechaCreacion, pintura.getFechaCreacion( ) );
		jPintura.put( lugarCreacion, pintura.getLugarCreacion( ) );
		ArrayList<Artista> artistas = pintura.getAutores( );
		String autores = "";
		if (! artistas.isEmpty())
		{
			for (Artista artista: artistas)
			{
				autores = autores + artista.getLogin()+ ",";
			}
		}
		else
		{
			autores = "Vacio";
		}
		jPintura.put( "Autores", autores );
		String historialDueños = "";
		if (! pintura.getHistorialDueños().isEmpty())
		{
			for (Usuario usuario: pintura.getHistorialDueños())
			{
				historialDueños = historialDueños + usuario.getLogin() + ",";
			}
		}
		else
		{
			historialDueños = "Vacio";
		}
		jPintura.put( "Historial Dueños", historialDueños );
		String historialVentas = "";
		HashMap<String, Integer> historialPiezasComprador = pintura.getHistorialVentas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (String fecha: historialPiezasComprador.keySet())
			{
				historialVentas = historialVentas +  fecha + "," + historialPiezasComprador.get(fecha).toString() + ";";
			}
		}
		else
		{
			historialVentas = "Vacio";
		}
		jPintura.put( "Fecha Precio", historialVentas);
		jPintura.put( exhibida, pintura.isExhibida() );
		jPintura.put( valor, String.valueOf(pintura.getValor( )) );
		jPintura.put( "Proprietario", pintura.getPropietario( ).getLogin() );
		jPintura.put( exhibaVendaoSubasta, pintura.getExhibaVendaoSubasta() );
		jPintura.put( tipo, pintura.getTipo( ) );
		jPintura.put( "Alto", String.valueOf(pintura.getAlto( ) ));
		jPintura.put( "Ancho", String.valueOf(pintura.getAncho( )) );
		jPintura.put( consignacion, pintura.isConsignacion( ) );
		jPintura.put( dispsubasta, pintura.isDispsubasta( ) );
		jPintura.put( dispventa, pintura.isDispventa( ) );
		identificadorPieza.put(pintura, sIdentificador);

		jPinturas.put( jPintura );
	}

	public void salvarImpresiones (Impresion impresion, JSONArray jImpresiones, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jImpresion = new JSONObject( );
		jImpresion.put( identificador, sIdentificador );
		jImpresion.put( titulo, impresion.getTitulo( ) );
		jImpresion.put( fechaCreacion, impresion.getFechaCreacion( ) );
		jImpresion.put( lugarCreacion, impresion.getLugarCreacion( ) );
		ArrayList<Artista> artistas = impresion.getAutores( );
		String autores = "";
		if (! artistas.isEmpty())
		{
			for (Artista artista: artistas)
			{
				autores = autores + artista.getLogin() + ",";
			}
		}
		else
		{
			autores = "Vacio";
		}
		jImpresion.put( "Autores", autores );
		String historialDueños = "";
		if (! impresion.getHistorialDueños().isEmpty())
		{
			for (Usuario usuario: impresion.getHistorialDueños())
			{
				historialDueños = historialDueños + usuario.getLogin() +  ",";
			}
		}
		else
		{
			historialDueños ="Vacio";
		}
		jImpresion.put( "Historial Dueños", historialDueños );
		String historialVentas = "";
		HashMap<String, Integer> historialPiezasComprador = impresion.getHistorialVentas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (String fecha: historialPiezasComprador.keySet())
			{
				historialVentas = historialVentas + fecha +  "," + historialPiezasComprador.get(fecha).toString() + ";";
			}
		}
		else
		{
			historialVentas = "Vacio";
		}
		jImpresion.put( "Fecha Precio", historialVentas);
		jImpresion.put( exhibida, impresion.isExhibida() );
		jImpresion.put( "Valor", String.valueOf(impresion.getValor( )) );
		jImpresion.put( "Proprietario", impresion.getPropietario( ).getLogin() );
		jImpresion.put( exhibaVendaoSubasta, impresion.getExhibaVendaoSubasta() );
		jImpresion.put( tipo, impresion.getTipo( ) );
		jImpresion.put( "Alto", String.valueOf(impresion.getAlto( )) );
		jImpresion.put( "Ancho", String.valueOf(impresion.getAncho( ) ));
		jImpresion.put( consignacion, impresion.isConsignacion( ) );
		jImpresion.put( dispsubasta, impresion.isDispsubasta( ) );
		jImpresion.put( dispventa, impresion.isDispventa( ) );
		identificadorPieza.put(impresion, sIdentificador);

		jImpresiones.put( jImpresion );
	}

	public void salvarFotografias (Fotografia fotografia, JSONArray jFotografias, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jFotografia = new JSONObject( );
		jFotografia.put( identificador, sIdentificador );
		jFotografia.put( titulo, fotografia.getTitulo( ) );
		jFotografia.put( fechaCreacion, fotografia.getFechaCreacion( ) );
		jFotografia.put( lugarCreacion, fotografia.getLugarCreacion( ) );
		ArrayList<Artista> artistas = fotografia.getAutores( );
		String autores = "";
		if (! artistas.isEmpty())
		{
			for (Artista artista: artistas)
			{
				autores = autores + artista.getLogin() +  ",";
			}
		}
		else
		{
			autores = "Vacio";
		}
		jFotografia.put( "Autores", autores );
		String historialDueños = "";
		if (! fotografia.getHistorialDueños().isEmpty())
		{
			for (Usuario usuario: fotografia.getHistorialDueños())
			{
				historialDueños = historialDueños + usuario.getLogin() +  ",";
			}
		}
		else
		{
			historialDueños= "Vacio";
		}
		jFotografia.put( "Historial Dueños", historialDueños );
		String historialVentas = "";
		HashMap<String, Integer> historialPiezasComprador = fotografia.getHistorialVentas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (String fecha: historialPiezasComprador.keySet())
			{
				historialVentas = historialVentas + fecha +  "," + historialPiezasComprador.get(fecha).toString() +  ";" ;
			}
		}
		else
		{
			historialVentas = "Vacio";
		}
		jFotografia.put( "Fecha Precio", historialVentas);
		jFotografia.put( exhibida, fotografia.isExhibida() );
		jFotografia.put( valor, String.valueOf(fotografia.getValor( )) );
		jFotografia.put( "Proprietario", fotografia.getPropietario( ).getLogin() );
		jFotografia.put( exhibaVendaoSubasta, fotografia.getExhibaVendaoSubasta() );
		jFotografia.put( tipo, fotografia.getTipo( ) );
		jFotografia.put( "Alto", String.valueOf(fotografia.getAlto( )) );
		jFotografia.put( "Ancho", String.valueOf(fotografia.getAncho( )) );
		jFotografia.put( "Formato", fotografia.getFormato( ) );
		jFotografia.put( "Enmarcado", fotografia.isEnmarcado( ) );
		jFotografia.put( consignacion, fotografia.isConsignacion( ) );
		jFotografia.put( dispsubasta, fotografia.isDispsubasta( ) );
		jFotografia.put( dispventa, fotografia.isDispventa( ) );
		identificadorPieza.put(fotografia, sIdentificador);

		jFotografias.put( jFotografia );
	}

	public void salvarEsculturas (Escultura escultura, JSONArray jEsculturas, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jEscultura = new JSONObject( );
		jEscultura.put( identificador, sIdentificador );
		jEscultura.put( titulo, escultura.getTitulo( ) );
		jEscultura.put( fechaCreacion, escultura.getFechaCreacion( ) );
		jEscultura.put( lugarCreacion, escultura.getLugarCreacion( ) );
		ArrayList<Artista> artistas = escultura.getAutores( );
		String autores = "";
		if (! artistas.isEmpty())
		{
			for (Artista artista: artistas)
			{
				autores = autores + artista.getLogin() +  ",";
			}
			jEscultura.put( "Autores", autores );
		}
		else
		{
			autores = "Vacio";
		}
		String historialDueños = "";
		if (! escultura.getHistorialDueños().isEmpty())
		{
			for (Usuario usuario: escultura.getHistorialDueños())
			{
				historialDueños = historialDueños +  usuario.getLogin() +  ",";
			}
		}
		else
		{
			historialDueños = "Vacio";
		}
		jEscultura.put( "Historial Dueños", historialDueños );
		String historialVentas = "";
		HashMap<String, Integer> historialPiezasComprador = escultura.getHistorialVentas();
		if (! historialPiezasComprador.keySet().isEmpty())
		{
			for (String fecha: historialPiezasComprador.keySet())
			{
				historialVentas = historialVentas + fecha + "," + historialPiezasComprador.get(fecha).toString() +  "," ;
			}
		}
		else
		{
			historialVentas = "Vacio";
		}
		jEscultura.put( "Fecha Precio", historialVentas);
		jEscultura.put( exhibida, escultura.isExhibida() );
		jEscultura.put( valor, String.valueOf(escultura.getValor( )) );
		jEscultura.put( "Proprietario", escultura.getPropietario( ).getLogin() );
		jEscultura.put( exhibaVendaoSubasta, escultura.getExhibaVendaoSubasta() );
		jEscultura.put( tipo, escultura.getTipo( ) );
		jEscultura.put( "Alto", String.valueOf(escultura.getAlto( ) ));
		jEscultura.put( "Ancho", String.valueOf(escultura.getAncho( ) ));
		jEscultura.put( "Profundidad", String.valueOf(escultura.getProfundidad( ) ));
		jEscultura.put( "Materiales", escultura.getMateriales( ) );
		jEscultura.put( "Peso", String.valueOf(escultura.getPeso( )) );
		jEscultura.put( "Electricidad", escultura.isElectricidad( ) );
		jEscultura.put( "Otro detalle", escultura.isOtroDetalle( ) );
		jEscultura.put( consignacion, escultura.isConsignacion( ) );
		jEscultura.put( dispsubasta, escultura.isDispsubasta( ) );
		jEscultura.put( dispventa, escultura.isDispventa( ) );
		identificadorPieza.put(escultura, sIdentificador);

		jEsculturas.put( jEscultura );
	}

	public void cargarPiezas(JSONArray jPiezas, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{ 
		int numeroPiezas = jPiezas.length();
		for (int i = 0 ; i < numeroPiezas ; i++)
		{
			JSONObject pieza = jPiezas.getJSONObject( i );
			String tipoPieza = pieza.getString(tipo);
			if (tipoVideo.equals(tipoPieza))
			{
				cargarVideo(pieza, galeria, loginUsuarios, identificacionPieza);
			}
			else if (tipoPintura.equals(tipoPieza))
			{
				cargarPintura(pieza, galeria, loginUsuarios, identificacionPieza);
			}
			else if (tipoFotografia.equals(tipoPieza))
			{
				cargarFotografia(pieza, galeria, loginUsuarios, identificacionPieza);
			}
			else if (tipoImpresion.equals(tipoPieza))
			{
				cargarImpresion(pieza, galeria, loginUsuarios, identificacionPieza);
			}
			else if (tipoEscultura.equals(tipoPieza))
			{
				cargarEscultura(pieza, galeria, loginUsuarios, identificacionPieza);
			}
		}
	}

	public void cargarEscultura(JSONObject jEscultura, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String loginsArtistas = jEscultura.getString("Autores");
		ArrayList<Artista> nAutores = new ArrayList<Artista>();
		if (!loginsArtistas.equals("Vacio"))
		{
			for(String loginAutor: loginsArtistas.split(","))
			{
				nAutores.add((Artista) loginUsuarios.get(loginAutor));
			}
		}
		String loginsDueños = jEscultura.getString("Historial Dueños");
		ArrayList<Usuario> nDueños = new ArrayList<Usuario>();
		if (!loginsDueños.equals("Vacio"))
		{
			for(String loginDueño: loginsDueños.split(","))
			{
				nDueños.add(loginUsuarios.get(loginDueño));
			}
		}
		String fechasPrecios = jEscultura.getString("Fecha Precio");
		HashMap<String, Integer> historialVentas = new HashMap<String, Integer>();
		if (!fechasPrecios.equals("Vacio"))
		{
			for(String fechaPrecio: fechasPrecios.split(";"))
			{
				historialVentas.put(fechaPrecio.split(",")[0], Integer.valueOf(fechaPrecio.split(",")[1]));
			}
		}
		String nLugarCreacion = jEscultura.getString(lugarCreacion);
		String nTitulo = jEscultura.getString(titulo);
		int nValor = Integer.parseInt(jEscultura.getString(valor));
		String nExhibaVendaoSubaste = jEscultura.getString(exhibaVendaoSubasta);
		String nFechaCreacion = jEscultura.getString(fechaCreacion);
		String loginComprador = jEscultura.getString("Proprietario");
		if (! loginUsuarios.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = (Comprador) loginUsuarios.get(loginComprador);
		boolean nExhibida = jEscultura.getBoolean(exhibida);
		double nAlto = Double.parseDouble(jEscultura.getString("Alto"));
		double nAncho = Double.parseDouble(jEscultura.getString("Ancho"));
		double nProfundidad = Double.parseDouble(jEscultura.getString("Profundidad"));
		String nMateriales = jEscultura.getString("Materiales");
		double nPeso = Double.parseDouble(jEscultura.getString("Peso"));
		boolean nElectricidad = jEscultura.getBoolean("Electricidad");
		boolean nOtroDetalle = jEscultura.getBoolean("Otro detalle");
		boolean nConsignacion = jEscultura.getBoolean(consignacion);
		boolean nDispsubasta = jEscultura.getBoolean(dispsubasta);
		boolean nDispventa = jEscultura.getBoolean(dispventa);
		Escultura escultura = galeria.getFabrica().crearEscultura(nTitulo, nValor, nFechaCreacion, nLugarCreacion, nPropietario, nAutores, nDueños, historialVentas, nExhibaVendaoSubaste, nConsignacion, nExhibida, nDispsubasta, nDispventa, nAlto, nAncho, nProfundidad, nMateriales, nPeso, nElectricidad, nOtroDetalle);
		identificacionPieza.put(jEscultura.getString(identificador), escultura);
		
	}

	public void cargarFotografia(JSONObject jFotografia, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String loginsArtistas = jFotografia.getString("Autores");
		ArrayList<Artista> nAutores = new ArrayList<Artista>();
		if (!loginsArtistas.equals("Vacio"))
		{
			for(String loginAutor: loginsArtistas.split(","))
			{
				nAutores.add((Artista) loginUsuarios.get(loginAutor));
			}
		}
		String loginsDueños = jFotografia.getString("Historial Dueños");
		ArrayList<Usuario> nDueños = new ArrayList<Usuario>();
		if (!loginsDueños.equals("Vacio"))
		{
			for(String loginDueño: loginsDueños.split(","))
			{
				nDueños.add(loginUsuarios.get(loginDueño));
			}
		}
		String fechasPrecios = jFotografia.getString("Fecha Precio");
		HashMap<String, Integer> historialVentas = new HashMap<String, Integer>();
		if (!fechasPrecios.equals("Vacio"))
		{
			for(String fechaPrecio: fechasPrecios.split(";"))
			{
				historialVentas.put(fechaPrecio.split(",")[0], Integer.valueOf(fechaPrecio.split(",")[1]));
			}
		}
		String nLugarCreacion = jFotografia.getString(lugarCreacion);
		String nTitulo = jFotografia.getString(titulo);
		int nValor = Integer.parseInt(jFotografia.getString(valor));
		String nExhibaVendaoSubaste = jFotografia.getString(exhibaVendaoSubasta);
		String nFechaCreacion = jFotografia.getString(fechaCreacion);
		String loginComprador = jFotografia.getString("Proprietario");
		if (! loginUsuarios.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = (Comprador) loginUsuarios.get(loginComprador);
		boolean nExhibida = jFotografia.getBoolean(exhibida);
		double nAlto = Double.parseDouble(jFotografia.getString("Alto"));
		double nAancho = Double.parseDouble(jFotografia.getString("Ancho"));
		String nFormato = jFotografia.getString("Formato");
		boolean nEnmarcado = jFotografia.getBoolean("Enmarcado");
		boolean nConsignacion = jFotografia.getBoolean(consignacion);
		boolean nDispsubasta = jFotografia.getBoolean(dispsubasta);
		boolean nDispventa = jFotografia.getBoolean(dispventa);
		Fotografia fotografia = galeria.getFabrica().crearFotografia(nTitulo, nValor, nFechaCreacion, nLugarCreacion, nPropietario, nAutores, nDueños, historialVentas, nExhibaVendaoSubaste, nConsignacion, nExhibida, nDispsubasta, nDispventa, nAlto, nAancho, nFormato, nEnmarcado);
		identificacionPieza.put(jFotografia.getString(identificador), fotografia);

	}

	public void cargarImpresion(JSONObject jImpresion, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String loginsArtistas = jImpresion.getString("Autores");
		ArrayList<Artista> nAutores = new ArrayList<Artista>();
		if (!loginsArtistas.equals("Vacio"))
		{
			for(String loginAutor: loginsArtistas.split(","))
			{
				nAutores.add((Artista) loginUsuarios.get(loginAutor));
			}
		}
		String loginsDueños = jImpresion.getString("Historial Dueños");
		ArrayList<Usuario> nDueños = new ArrayList<Usuario>();
		if (!loginsDueños.equals("Vacio"))
		{
			for(String loginDueño: loginsDueños.split(","))
			{
				nDueños.add(loginUsuarios.get(loginDueño));
			}
		}
		String fechasPrecios = jImpresion.getString("Fecha Precio");
		HashMap<String, Integer> historialVentas = new HashMap<String, Integer>();
		if (!fechasPrecios.equals("Vacio"))
		{
			for(String fechaPrecio: fechasPrecios.split(";"))
			{
				historialVentas.put(fechaPrecio.split(",")[0], Integer.valueOf(fechaPrecio.split(",")[1]));
			}
		}
		String nLugarCreacion = jImpresion.getString(lugarCreacion);
		String nTitulo = jImpresion.getString(titulo);
		int nValor = Integer.parseInt(jImpresion.getString(valor));
		String nExhibaVendaoSubaste = jImpresion.getString(exhibaVendaoSubasta);
		String nFechaCreacion = jImpresion.getString(fechaCreacion);
		String loginComprador = jImpresion.getString("Proprietario");
		if (! loginUsuarios.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = (Comprador) loginUsuarios.get(loginComprador);
		boolean nExhibida = jImpresion.getBoolean(exhibida);
		double nAlto = Double.parseDouble(jImpresion.getString("Alto"));
		double nAncho = Double.parseDouble(jImpresion.getString("Ancho"));
		boolean nConsignacion = jImpresion.getBoolean(consignacion);
		boolean nDispsubasta = jImpresion.getBoolean(dispsubasta);
		boolean nDispventa = jImpresion.getBoolean(dispventa);
		Impresion impresion = galeria.getFabrica().crearImpresion(nTitulo, nValor, nFechaCreacion, nLugarCreacion, nPropietario, nAutores, nDueños, historialVentas, nExhibaVendaoSubaste, nConsignacion, nExhibida, nDispsubasta, nDispventa, nAlto, nAncho);
		identificacionPieza.put(jImpresion.getString(identificador), impresion);

	}

	public void cargarPintura(JSONObject jPintura, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String loginsArtistas = jPintura.getString("Autores");
		ArrayList<Artista> nAutores = new ArrayList<Artista>();
		if (!loginsArtistas.equals("Vacio"))
		{
			for(String loginAutor: loginsArtistas.split(","))
			{
				nAutores.add((Artista) loginUsuarios.get(loginAutor));
			}
		}
		String loginsDueños = jPintura.getString("Historial Dueños");
		ArrayList<Usuario> nDueños = new ArrayList<Usuario>();
		if (!loginsDueños.equals("Vacio"))
		{
			for(String loginDueño: loginsDueños.split(","))
			{
				nDueños.add(loginUsuarios.get(loginDueño));
			}
		}
		String fechasPrecios = jPintura.getString("Fecha Precio");
		HashMap<String, Integer> historialVentas = new HashMap<String, Integer>();
		if (!fechasPrecios.equals("Vacio"))
		{
			for(String fechaPrecio: fechasPrecios.split(";"))
			{
				historialVentas.put(fechaPrecio.split(",")[0], Integer.valueOf(fechaPrecio.split(",")[1]));
			}
		}
		String nLugarCreacion = jPintura.getString(lugarCreacion);
		String nTitulo = jPintura.getString(titulo);
		int nValor = Integer.parseInt(jPintura.getString(valor));
		String nExhibaVendaoSubaste = jPintura.getString(exhibaVendaoSubasta);
		String nFechaCreacion = jPintura.getString(fechaCreacion);
		String loginComprador = jPintura.getString("Proprietario");
		if (! loginUsuarios.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = (Comprador) loginUsuarios.get(loginComprador);
		boolean nExhibida = jPintura.getBoolean(exhibida);
		double nAlto = Double.parseDouble(jPintura.getString("Alto"));
		double nAncho = Double.parseDouble(jPintura.getString("Ancho"));
		boolean nConsignacion = jPintura.getBoolean(consignacion);
		boolean nDispsubasta = jPintura.getBoolean(dispsubasta);
		boolean nDispventa = jPintura.getBoolean(dispventa);
		Pintura pintura = galeria.getFabrica().crearPintura(nTitulo, nValor, nFechaCreacion, nLugarCreacion, nPropietario, nAutores, nDueños, historialVentas, nExhibaVendaoSubaste, nConsignacion, nExhibida, nDispsubasta, nDispventa, nAlto, nAncho);
		identificacionPieza.put(jPintura.getString(identificador), pintura);

	}


	public void cargarVideo(JSONObject jVideo, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String loginsArtistas = jVideo.getString("Autores");
		ArrayList<Artista> nAutores = new ArrayList<Artista>();
		if (!loginsArtistas.equals("Vacio"))
		{
			for(String loginAutor: loginsArtistas.split(","))
			{
				nAutores.add((Artista) loginUsuarios.get(loginAutor));
			}
		}
		String loginsDueños = jVideo.getString("Historial Dueños");
		ArrayList<Usuario> nDueños = new ArrayList<Usuario>();
		if (!loginsDueños.equals("Vacio"))
		{
			for(String loginDueño: loginsDueños.split(","))
			{
				nDueños.add(loginUsuarios.get(loginDueño));
			}
		}
		String fechasPrecios = jVideo.getString("Fecha Precio");
		HashMap<String, Integer> historialVentas = new HashMap<String, Integer>();
		if (!fechasPrecios.equals("Vacio"))
		{
			for(String fechaPrecio: fechasPrecios.split(";"))
			{
				historialVentas.put(fechaPrecio.split(",")[0], Integer.valueOf(fechaPrecio.split(",")[1]));
			}
		}
		String nLugarCreacion = jVideo.getString(lugarCreacion);
		String nTitulo = jVideo.getString(titulo);
		int nValor = Integer.parseInt(jVideo.getString(valor));
		String nExhibaVendaoSubaste = jVideo.getString(exhibaVendaoSubasta);
		String nFechaCreacion = jVideo.getString(fechaCreacion);
		String loginComprador = jVideo.getString("Proprietario");
		if (! loginUsuarios.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = (Comprador) loginUsuarios.get(loginComprador);
		boolean nExhibida = jVideo.getBoolean(exhibida);
		double nAlto = Double.parseDouble(jVideo.getString("Alto"));
		double nAncho = Double.parseDouble(jVideo.getString("Ancho"));
		int nDuracion = Integer.parseInt(jVideo.getString("Duracion"));
		String nFormato = jVideo.getString("Formato");
		boolean nConsignacion = jVideo.getBoolean(consignacion);
		boolean nDispsubasta = jVideo.getBoolean(dispsubasta);
		boolean nDispventa = jVideo.getBoolean(dispventa);
		Video video = galeria.getFabrica().crearVideo(nTitulo, nValor, nFechaCreacion, nLugarCreacion, nPropietario, nAutores, nDueños, historialVentas, nExhibaVendaoSubaste, nConsignacion, nExhibida, nDispsubasta, nDispventa, nAlto, nAncho, nDuracion, nFormato);
		identificacionPieza.put(jVideo.getString(identificador), video);
	}
}



