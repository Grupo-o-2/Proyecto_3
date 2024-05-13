package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import usuarios.*;
import piezas.*;
import modelo.*;
import fabrica.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.*;



public class AdministradorTest {
	
	Galeria galeria;
	Administrador administrador;
	Fabrica fabricaGaleria;
	
    @BeforeEach
    void setUp( ) throws Exception
    {  
        Fabrica fabrica = new Fabrica();
        this.galeria = fabrica.crearGaleria("GaleriaTestsAdministrador.json", new ArrayList<Subasta>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
        this.galeria.cargarGaleria("GaleriaTestsAdministrador.json");
        this.administrador = (Administrador) galeria.getAdministrador();
        this.fabricaGaleria = galeria.getFabrica();
    } 
     
    @Test
    void registrarDevolverPiezasTest()
    {
    	try
    	{
	    	Comprador john = (Comprador) galeria.obtenerUsuarioPorNombre("John");
	    	Artista dali = (Artista) galeria.obtenerUsuarioPorLogin("logindali");
	    	ArrayList<Artista> autor = new ArrayList<Artista>();
	    	autor.add(dali);
	    	Pintura persistencia = fabricaGaleria.crearPintura("La persistencia de la galería", 1111110, "20240513", "Java", john, autor, new ArrayList<Usuario>() , new HashMap<String, Integer>(), "123", false, false, true, true, "0", 89.5, 59.8);
	    	assertFalse(galeria.getPiezasActuales().contains(persistencia), "La galería tiene la pieza en sus piezas acutales cuando esta no ha sido consignada aún.");
	    	assertFalse(galeria.getPiezasAntiguas().contains(persistencia), "La galería tiene la pieza en sus piezas antiguas cuando esta no ha sido consignada y devuelta aún.");
	    	assertFalse(galeria.getHistorialPiezas().contains(persistencia), "La galería tiene la pieza en su historial de piezas antiguas cuando esta no ha sido consignada aún.");
	    	administrador.registrarPiezas(persistencia, galeria);
	    	assertTrue(galeria.getPiezasActuales().contains(persistencia), "La galería no tiene la pieza en sus puezas acutales cuando esta ya ha sido consignada y no se ha devuelto.");
	    	assertFalse(galeria.getPiezasAntiguas().contains(persistencia), "La galería tiene la pieza en sus piezas antiguas cuando esta ha sido consignada y no se ha devuelto.");
	    	assertTrue(galeria.getHistorialPiezas().contains(persistencia), "La galería no tiene la pieza en su historial de piezas antiguas cuando esta ya ha sido consignada .");
	    	administrador.devoluciondePiezas(persistencia, galeria, john);
	    	assertFalse(galeria.getPiezasActuales().contains(persistencia), "La galería tiene la pieza en sus puezas acutales cuando esta ya ha sido consignada y devuelta.");
	    	assertTrue(galeria.getPiezasAntiguas().contains(persistencia), "La galería no tiene la pieza en sus piezas antiguas cuando esta ha sido consignada y devuelta.");
	    	assertTrue(galeria.getHistorialPiezas().contains(persistencia), "Al devolver la pieza, el historial de piezas de la galeria se modifica de manera incorrecta.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
   @Test
    void revisarConsignacionPiezaTest()
    {
    	try
    	{
	    	Comprador john = (Comprador) galeria.obtenerUsuarioPorLogin("loginjohn");
	    	Escultura escultura = (Escultura) galeria.obtenerPiezaporTitulo("Venus de Milo");
	    	// La fecha límite de escultura es 20250101 (1 de enero de 2025) y se comprueba con la fecha 20240525 (25 de mayo de 2024)
	    	assertFalse(administrador.devolverPiezasConsignadas(john, escultura, "20240525", galeria), "La escultura Venus de Milo aún no debe ser devuelta.");
	    	Comprador alice = (Comprador) galeria.obtenerUsuarioPorLogin("loginalice");
	    	Impresion impresion = (Impresion) galeria.obtenerPiezaporTitulo("Esperanza");
	    	// La fecha límite de impresion es 20240524 (24 de mayo de 2025) y se comprueba con la fecha 20240525 (25 de mayo de 2024)
	    	assertTrue(administrador.devolverPiezasConsignadas(alice, impresion, "20240525", galeria), "La impresion Esperanza debe ser devuelta.");
	    	assertFalse(impresion.isConsignacion(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isDispsubasta(),"No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertFalse(impresion.isDispventa(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isExhibida(),"No se cambió el atributo 'consignacion' de la pieza");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
   
   @Test
   void revisarConsignacionPiezathrowFechaInvalidaTest()
   {
   	try
   	{
   		Exception exception = assertThrows(FechaInvalidaException.class, ()->{
   			Comprador john = (Comprador) galeria.obtenerUsuarioPorLogin("loginjohn");
	    	Escultura escultura = (Escultura) galeria.obtenerPiezaporTitulo("Venus de Milo");
	    	administrador.devolverPiezasConsignadas(john, escultura, "202405256", galeria);
    	}); 
    	assertEquals("La fecha 202405256 es inválida. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");	    	
   	}
   	catch(Exception e)
   	{
   		e.printStackTrace();
   	}
   }
    /*
    @Test
    void crearSubastaTest()
    {
    	try
    	{
	    	Comprador john = (Comprador) galeria.obtenerUsuarioPorLogin("loginjohn");
	    	Comprador alice = (Comprador) galeria.obtenerUsuarioPorLogin("loginalice");
	    	Escultura escultura = (Escultura) galeria.obtenerPiezaporTitulo("Venus de Milo");
	    	Pintura pintura = (Pintura) galeria.obtenerPiezaGlobalesporTitulo("Amancer en París");
	    	Impresion impresion = (Impresion) galeria.obtenerPiezaporTitulo("Esperanza");
	    	Video video = (Video) galeria.obtenerPiezaporTitulo("Video abstruso");
	    	ArrayList
	    	assertEquals(0,galeria.getSubastas().size(), "Hay subastas antes de que estas sean creadas");
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    */
    
}