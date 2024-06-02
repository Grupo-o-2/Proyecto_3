package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fabrica.Fabrica;
import modelo.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class SubastaTest {
	
	Galeria galeria;
	Comprador alice;
	Comprador lucy;
	Comprador john;
	Comprador fake;
	Operador operador;
	Video video2;
	Escultura escultura2;
	Pintura pintura2;
	
	@BeforeEach
    void setUp( ) throws Exception
    {  
        Fabrica fabrica = new Fabrica();
        this.galeria = fabrica.crearGaleria("GaleriaTestsAdministrador.json", new ArrayList<Subasta>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
        this.galeria.cargarGaleria("GaleriaTestsAdministrador.json");
        this.alice = (Comprador) galeria.obtenerUsuarioPorNombre("Alice");
        this.lucy = (Comprador) galeria.obtenerUsuarioPorNombre("Lucy");
        this.john = (Comprador) galeria.obtenerUsuarioPorNombre("John");
        this.fake = (Comprador) galeria.obtenerUsuarioPorNombre("fake");
        this.video2 = (Video) galeria.obtenerPiezaGlobalesporTitulo("Video abstruso");
        this.escultura2 = (Escultura) galeria.obtenerPiezaGlobalesporTitulo("La musa del renacimiento");
        this.pintura2 = (Pintura) galeria.obtenerPiezaGlobalesporTitulo("Amanacer en París");
        this.operador = (Operador) galeria.getUnOperador();
    }
	
	@Test
	void crearSubastaTest()
	{
		
		try
		{
			assertTrue(galeria.getSubastas().isEmpty(), "La galeria se inicializa con subastas.");
			
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(alice);
			participantes1.add(lucy);
			participantes1.add(john);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); 
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(alice, 1700);
			ofertaVideo.put(john, 1800);
			ofertaVideo.put(lucy, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(lucy, 7500);
			ofertaEscultura.put(john, 7600);
			ofertaEscultura.put(alice, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			galeria.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			Subasta subasta = galeria.obtenerSubastaPorNombre("Subasta VIP");
			assertEquals(participantes1, subasta.getParticipantes(), "La subasta se crea con usuarios que no son.");
			assertEquals(operador, subasta.getOperador(), "La subasta se crea con el operador que no es.");
			assertEquals(ofertas1, subasta.getRegistroSubasta(), "La subasta se crea con el registro de ofertas que no es.");
			assertEquals(subasta1, subasta.getPiezasSubastadas(), "La subasta se crea con las piezas y precios que no son.");
			
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void hacerOfertaTest()
	{
		
		try
		{			
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(alice);
			participantes1.add(lucy);
			participantes1.add(john);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); 
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(alice, 1700);
			ofertaVideo.put(john, 1800);
			ofertaVideo.put(lucy, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(lucy, 7500);
			ofertaEscultura.put(john, 7600);
			ofertaEscultura.put(alice, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			galeria.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			Subasta subasta = galeria.obtenerSubastaPorNombre("Subasta VIP");
			alice.realizarOfertaEnSubasta(escultura2, 8000, subasta, galeria);
			assertEquals(3, subasta.getRegistroSubasta().size(), "El historial de registros de ofertas no se actualiza de forma correcta.");
			assertTrue(subasta.getRegistroSubasta().keySet().contains(escultura2), "El historial de registros de ofertas no actualiza las piezas ofertas de forma correcta.");
			assertTrue(subasta.getRegistroSubasta().get(escultura2).keySet().contains(alice), "El historial de registros de ofertas no actualiza los participantes que ofertan de forma correcta.");
			assertEquals(8000, subasta.getRegistroSubasta().get(escultura2).get(alice), "El historial de registros de ofertas no actualiza los precios de las ofertas de forma correcta.");
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void hacerOfertathrowDineroOfrecidoInsuficienteTest()
	{
		Exception exception = assertThrows(DineroOfrecidoInsuficienteException.class, ()->{
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(alice);
			participantes1.add(lucy);
			participantes1.add(john);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); 
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(alice, 1700);
			ofertaVideo.put(john, 1800);
			ofertaVideo.put(lucy, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(lucy, 7500);
			ofertaEscultura.put(john, 7600);
			ofertaEscultura.put(alice, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			galeria.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			Subasta subasta = galeria.obtenerSubastaPorNombre("Subasta VIP");
			alice.realizarOfertaEnSubasta(escultura2, 0, subasta, galeria);
    	}); 
    	assertEquals("El dinero ofrecido por la pieza es menor al valor inicial de la subasta. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
	}
	
	@Test
	void hacerOfertathrowDineroInsuficienteTest()
	{
		Exception exception = assertThrows(DineroInsuficienteException.class, ()->{
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(alice);
			participantes1.add(lucy);
			participantes1.add(john);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); 
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(alice, 1700);
			ofertaVideo.put(john, 1800);
			ofertaVideo.put(lucy, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(lucy, 7500);
			ofertaEscultura.put(john, 7600);
			ofertaEscultura.put(alice, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			galeria.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			Subasta subasta = galeria.obtenerSubastaPorNombre("Subasta VIP");
			john.realizarOfertaEnSubasta(escultura2, 80001, subasta, galeria);
    	}); 
    	assertEquals("El usuario no cuenta con el dinero suficiente para realizar dicha oferta.\n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
	}
	
	@Test
	void terminarSubastaTest()
	{
		try
		{			
			ArrayList<Usuario> participantes1 = new ArrayList<Usuario>();
			participantes1.add(alice);
			participantes1.add(lucy);
			participantes1.add(john);
			
			ArrayList<Integer> valoresVideo = new ArrayList<Integer>();
			valoresVideo.add(1600);
			valoresVideo.add(1500);
			
			ArrayList<Integer> valoresEscultura = new ArrayList<Integer>();
			valoresEscultura.add(7500);
			valoresEscultura.add(7000);
			
			ArrayList<Integer> valoresPintura = new ArrayList<Integer>();
			valoresPintura.add(8900);
			valoresPintura.add(8700);
			
			HashMap<Pieza, ArrayList<Integer>> subasta1 = new HashMap<Pieza, ArrayList<Integer>>(); 
			subasta1.put(video2, valoresVideo);
			subasta1.put(escultura2, valoresEscultura);
			subasta1.put(pintura2, valoresPintura);
			
			HashMap<Pieza, HashMap<Usuario, Integer>> ofertas1 = new HashMap<Pieza, HashMap<Usuario, Integer>>();
			
			HashMap<Usuario, Integer> ofertaVideo = new HashMap<Usuario, Integer>();
			ofertaVideo.put(alice, 1700);
			ofertaVideo.put(john, 1800);
			ofertaVideo.put(lucy, 2000);
			ofertas1.put(video2, ofertaVideo);
			
			HashMap<Usuario, Integer> ofertaEscultura = new HashMap<Usuario, Integer>();
			ofertaEscultura.put(lucy, 7500);
			ofertaEscultura.put(john, 7600);
			ofertaEscultura.put(alice, 7800);
			ofertas1.put(escultura2, ofertaEscultura);
			
			HashMap<Usuario, Integer> ofertaPintura = new HashMap<Usuario, Integer>();
			ofertas1.put(pintura2, ofertaPintura);
			
			galeria.crearSubasta("Subasta VIP",participantes1, operador, ofertas1, subasta1);
			
			Subasta subasta = galeria.obtenerSubastaPorNombre("Subasta VIP");
			
			assertFalse(lucy.getPiezasActuales().contains(video2), "Lucy tiene la pieza impresion antes de la compra.");
	    	assertEquals(256, lucy.getValorColeccion(), "El valor de la colección de Lucy no tiene el dinero que corresponde antes de la compra.");
	    	assertEquals(200000, lucy.getDinero(), "Lucy no tiene el dinero que corresponde antes de la compra.");
			subasta.finalizarSubasta(galeria, "20240826");
	    	assertTrue(lucy.getPiezasActuales().contains(video2), "Lucy no tiene la pieza  después de la compra.");
	    	assertFalse(video2.isConsignacion(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(video2.isDispsubasta(),"No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertFalse(video2.isDispventa(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(video2.isExhibida(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertTrue(lucy.getHistorialPiezas().containsKey(video2),"La pieza no se agregó al historial de Lucy.");
	    	assertEquals("20240826" ,lucy.getHistorialPiezas().get(video2),"La pieza no se agregó al historial de Lucy con la fecha correcta.");
	    	assertTrue(video2.getHistorialDueños().contains(lucy), "Lucy no se agregó al historial de dueños de la pieza.");
			
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
