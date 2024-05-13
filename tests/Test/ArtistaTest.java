package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;
import piezas.*;
import usuarios.*;

public class ArtistaTest {

	Galeria galeria;
	Fabrica fabricaGaleria;
	
    @BeforeEach
    void setUp( ) throws Exception
    {  
        Fabrica fabrica = new Fabrica();
        this.galeria = fabrica.crearGaleria("GaleriaTestsAdministrador.json", new ArrayList<Subasta>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
        this.galeria.cargarGaleria("GaleriaTestsAdministrador.json");
    }
	
    @Test
    void HistorialPiezasTest()
    {
    	try
    	{
    		galeria.crearArtista("loginPrueba", "passwordPrueba", "Artista Prueba", "12345678", new ArrayList<Pieza>());
    		Artista artista = (Artista) galeria.obtenerUsuarioPorLogin("loginPrueba");
    		Comprador comprador = (Comprador) galeria.obtenerUsuarioPorLogin("loginalice");
    		assertTrue(artista.getPiezasCreadas().isEmpty(), "El historial de piezas del artista no se inicializa vacío");
    		ArrayList<Artista> autor = new ArrayList<Artista>();
    		autor.add(artista);
    		galeria.crearVideo("Video Prueba", 10, "20250513", "Bogota", comprador, autor, new ArrayList<Usuario>(), new HashMap<String, Integer>(), "123", false, false, false, false, "0", 0, 0, 0, ".mov");
			Video video = (Video) galeria.obtenerPiezaGlobalesporTitulo("Video Prueba");
    		assertEquals(1, artista.getPiezasCreadas().size(), "El historial de piezas del artista no se actualiza al realizar compras.");
			assertTrue(artista.getPiezasCreadas().contains(video), "El historial se actualiza con objetos incorrectos.");
			 galeria.crearPintura("La persistencia de la galería", 1111110, "20240513", "Java", comprador, autor, new ArrayList<Usuario>() , new HashMap<String, Integer>(), "123", false, false, true, true, "0", 89.5, 59.8);
			 Pintura persistencia = (Pintura) galeria.obtenerPiezaGlobalesporTitulo("La persistencia de la galería");
			assertEquals(2, artista.getPiezasCreadas().size(), "El historial de piezas del artista no se actualiza al realizar compras.");
			assertTrue(artista.getPiezasCreadas().contains(persistencia), "El historial se actualiza con objetos incorrectos.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
}
