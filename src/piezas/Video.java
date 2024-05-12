package piezas;

import java.util.ArrayList;
import java.util.HashMap;

import fabrica.Fabrica;
import modelo.*;
import usuarios.*;

public class Video extends Pieza {

	private double alto;
	private double ancho;
	private int duracion;
	private String formato;
	private static final String TIPO= "Video";
	
	public Video(String titulo, int valor, String fecha, String lugar, Usuario propietario,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa,  String fechaLimite, double alto, double ancho, int duracion, String formato) {
		super(titulo, valor, fecha, lugar, propietario, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida,
				dispsubasta, dispventa, fechaLimite);
		this.alto = alto;
		this.ancho = ancho;
		this.duracion = duracion;
		this.formato = formato;
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

	public int getDuracion() {
		return duracion;
	}

	public String getFormato() {
		return formato;
	}
	
	public static void main(String[] args)
	{
		Fabrica fabrica = new Fabrica();
		Galeria galeria = fabrica.crearGaleria("Probar nueva persistencia", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		try
		{
		galeria.crearArtista("Prueba", "abeurP", "Pepe", "123456", new ArrayList<Pieza>());
		ArrayList<Artista> autor = new ArrayList<Artista>();
		autor.add((Artista) galeria.obtenerUsuarioPorLogin("Prueba"));
		galeria.crearComprador("PruebaNoArtista", "Ped", "Manuel", 1000, 0, new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 10000, "4839274");
		galeria.crearVideo("Titulo", 100, "Fecha", "Lugar",(Comprador) galeria.obtenerUsuarioPorLogin("PruebaNoArtista"), autor, new ArrayList<Usuario>() , new HashMap<String, Integer>(), "exhibaVendaoSubasta", true, false, true,
				false, 1720, 1980, 13, "Formato");
		galeria.salvarGaleria("Video.json");
		Fabrica fabricaDos = new Fabrica();
		Galeria galeriaDos = fabrica.crearGaleria("Galeria Dos", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaDos.cargarGaleria("Video.json");
		galeriaDos.salvarGaleria("VideoClonado.json");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}