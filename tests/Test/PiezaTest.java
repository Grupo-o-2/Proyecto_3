package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fabrica.Fabrica;
import modelo.*;
import piezas.*;
import usuarios.*;

public class PiezaTest {
	
	Galeria galeria;

	@BeforeEach
    void setUp( ) throws Exception
    {  
        Fabrica fabrica = new Fabrica();
        this.galeria = fabrica.crearGaleria("GaleriaTestsAdministrador.json", new ArrayList<Subasta>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
        this.galeria.cargarGaleria("GaleriaTestsAdministrador.json");
    }
	
	@Test
	void HistorialPiezaTest()
	{
		try
		{
			galeria.crearArtista("loginPrueba", "passwordPrueba", "Artista Prueba", "12345678", new ArrayList<Pieza>());
    		Artista artista = (Artista) galeria.obtenerUsuarioPorLogin("loginPrueba");
    		Comprador comprador = (Comprador) galeria.obtenerUsuarioPorLogin("loginlucy");
    		ArrayList<Artista> autor = new ArrayList<Artista>();
    		autor.add(artista);
    		galeria.crearVideo("Video Prueba", 0, "20250513", "Bogota", comprador, autor, new ArrayList<Usuario>(), new HashMap<String, Integer>(), "123", false, false, false, false, "0", 0, 0, 0, ".mov");
			Video video = (Video) galeria.obtenerPiezaGlobalesporTitulo("Video Prueba");
			assertTrue(video.getHistorialDueños().isEmpty(), "El historial de dueños de la pieza no se inicializa vacío");
			assertTrue(video.getHistorialVentas().isEmpty(), "El historial de ventas de la pieza no se inicializa vacío");
			Comprador alice = (Comprador) galeria.obtenerUsuarioPorNombre("Alice");
			comprador.consignarPieza(video, "20250101", galeria, "123", "20240510");
			alice.comprarPieza(video, galeria, "2024-05-13");
			assertEquals(1 ,video.getHistorialDueños().size(), "El tamaño del historial de dueños de la pieza no se actualiza de forma correcta.");
			assertTrue(video.getHistorialDueños().contains(alice), "El historial de dueños no se actualiza de forma correcta.");
			assertEquals(1 ,video.getHistorialVentas().size(), "El tamaño del historial de ventas de la pieza no se actualiza de forma correcta.");
			assertTrue(video.getHistorialVentas().keySet().contains("2024-05-13"), "El historial de ventas no almacena las llaves correctas.");
			assertEquals(0, video.getHistorialVentas().get("2024-05-13"), "El historial de ventas no almacena los valores correctos.");
			alice.consignarPieza(video, "20250101", galeria, "123", "20240514");
			comprador.comprarPieza(video, galeria, "2024-05-25");
			assertEquals(2 ,video.getHistorialDueños().size(), "El tamaño del historial de dueños de la pieza no se actualiza de forma correcta.");
			assertTrue(video.getHistorialDueños().contains(comprador), "El historial de dueños no se actualiza de forma correcta.");
			assertEquals(2 ,video.getHistorialVentas().size(), "El tamaño del historial de ventas de la pieza no se actualiza de forma correcta.");
			assertTrue(video.getHistorialVentas().keySet().contains("2024-05-25"), "El historial de ventas no almacena las llaves correctas.");
			assertEquals(0, video.getHistorialVentas().get("2024-05-25"), "El historial de ventas no almacena los valores correctos.");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
