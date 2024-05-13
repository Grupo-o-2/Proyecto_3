package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.*;
import fabrica.Fabrica;
import modelo.*;
import piezas.*;
import usuarios.*;

public class CompradorTest {

	
	Galeria galeria;
	Comprador alice;
	Comprador lucy;
	Comprador john;
	Comprador fake;
	Fabrica fabricaGaleria;
	
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
        this.fabricaGaleria = galeria.getFabrica();
    }
    
    @Test
    void CompraTest()
    {
    	try
    	{
	    	Impresion impresion = (Impresion) alice.obtenerPiezaporTitulo("Esperanza");
	    	assertTrue(alice.getPiezasActuales().contains(impresion), "Alice no tiene la pieza impresion antes de la compra.");
	    	assertFalse(lucy.getPiezasActuales().contains(impresion), "Lucy tiene la pieza impresion antes de la compra.");
	    	assertEquals(2003, alice.getValorColeccion(), "El valor de la colección de Alice no tiene el dinero que corresponde antes de la compra.");
	    	assertEquals(256, lucy.getValorColeccion(), "El valor de la colección de Lucy no tiene el dinero que corresponde antes de la compra.");
	    	assertEquals(200000, lucy.getDinero(), "Lucy no tiene el dinero que corresponde antes de la compra.");
	    	assertEquals(1000000000, alice.getDinero(), "Alice no tiene el dinero que corresponde antes de la compra.");
	    	lucy.comprarPieza(impresion, galeria, "2024-05-13");
	    	assertEquals(1000000073, alice.getDinero(), "Alice no tiene el dinero que corresponde después de la compra." );
	    	assertEquals(199927, lucy.getDinero(), "Lucy no tiene el dinero que corresponde después de la compra." );
	    	assertFalse(alice.getPiezasActuales().contains(impresion), "Alice tiene la pieza impresion después de la compra.");
	    	assertTrue(lucy.getPiezasActuales().contains(impresion), "Lucy no tiene la pieza impresion después de la compra.");
	    	assertEquals(lucy, impresion.getPropietario(), "El propietario de la pieza no cambió cuando se realizó la compra");
	    	assertFalse(impresion.isConsignacion(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isDispsubasta(),"No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertFalse(impresion.isDispventa(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isExhibida(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertTrue(lucy.getHistorialPiezas().containsKey(impresion),"La pieza no se agregó al historial de Lucy.");
	    	assertEquals("2024-05-13" ,lucy.getHistorialPiezas().get(impresion),"La pieza no se agregó al historial de Lucy con la fecha correcta.");
	    	assertTrue(impresion.getHistorialDueños().contains(lucy), "Lucy no se agregó al historial de dueños de la pieza.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void CompraDosTest()
    {
    	try
    	{
	    	Impresion impresion = (Impresion) fake.obtenerPiezaporTitulo("Reflejos urbanos");
	    	assertTrue(fake.getPiezasActuales().contains(impresion), "La galería no tiene la pieza Reflejos Urbanos antes de la compra.");
	    	assertFalse(alice.getPiezasActuales().contains(impresion), "Alice tiene la piesa Reflejos Urbanos antes de la compra.");
	    	assertEquals(2003, alice.getValorColeccion(), "El valor de la colección de Alice no tiene el dinero que corresponde antes de la compra.");
	    	assertEquals(1000000000, alice.getDinero(), "Alice no tiene el dinero que corresponde antes de la compra.");
	    	alice.comprarPieza(impresion, galeria, "2024-05-13");
	    	assertEquals(999999910, alice.getDinero(), "Alice no tiene el dinero que corresponde después de la compra." );
	    	assertTrue(alice.getPiezasActuales().contains(impresion), "Alice no tiene la pieza Reflejos Urbanos después de la compra.");
	    	assertEquals(alice, impresion.getPropietario(), "El propietario de la pieza no cambió cuando se realizó la compra");
	    	assertFalse(impresion.isConsignacion(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isDispsubasta(),"No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertFalse(impresion.isDispventa(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(impresion.isExhibida(),"No se cambió el atributo 'consignacion' de la pieza");
	    	assertTrue(alice.getHistorialPiezas().containsKey(impresion),"La pieza no se agregó al historial de Alice.");
	    	assertEquals("2024-05-13" ,alice.getHistorialPiezas().get(impresion),"La pieza no se agregó al historial de Alice con la fecha correcta.");
	    	assertTrue(impresion.getHistorialDueños().contains(alice), "Alice no se agregó al historial de dueños de la pieza.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void comprarThrowVentaImposibleTest()
    {
    	Exception exception = assertThrows(VentaImposibleException.class, ()->{
    		Escultura escultura = (Escultura) john.obtenerPiezaporTitulo("Venus de Milo");
    		lucy.comprarPieza(escultura, galeria, "2024-05-13");
    	}); 
    	assertEquals("La pieza Venus de Milo no se encuentra disponible para la venta. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	
    	Exception exceptionDos = assertThrows(VentaImposibleException.class, ()->{
    		Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
    		lucy.comprarPieza(pintura, galeria, "2024-05-13");
    	}); 
    	assertEquals("La pieza Selene no se encuentra disponible para la venta. \n", exceptionDos.getMessage(), "La excepción tiene un mensaje erróneo.");
    }
    
    @Test
    void comprarThrowDineroInsuficienteTest()
    {
    	Exception exception = assertThrows(DineroInsuficienteException.class, ()->{
    		Pintura pintura = (Pintura) fake.obtenerPiezaporTitulo("Amanecer en París");
    		lucy.comprarPieza(pintura, galeria, "2024-05-13");
    	}); 
    	assertEquals("El usuario Lucy no cuenta con el dinero suficiente para comprar esta pieza.\n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    }
    
    @Test
    void comprarThrowMismoCompradorTest()
    {
    	Exception exception = assertThrows(MismoCompradorException.class, ()->{
    		Impresion impresion = (Impresion) alice.obtenerPiezaporTitulo("Esperanza");
    		alice.comprarPieza(impresion, galeria, "2024-05-13");
    	}); 
    	assertEquals("No puedes comprar esta pieza debido a que ya es de tu propiedad. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    }
    
    @Test
    void comprarThrowValorMaximoExcedidoTest()
    {
    	Exception exception = assertThrows(ValorMaximoExcedidoException.class, ()->{
    		Pintura pintura = (Pintura) fake.obtenerPiezaporTitulo("Amanecer en París");
    		alice.comprarPieza(pintura, galeria, "2024-05-13");
    	}); 
    	assertEquals("El usuario Alice ha excedido el valor máximo de compras.\n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    }
    
    @Test
    void comprarFechaInvalidaTest()
    {
    	Exception exception = assertThrows(FechaInvalidaException.class, ()->{
    		Impresion impresion = (Impresion) alice.obtenerPiezaporTitulo("Esperanza");
    		lucy.comprarPieza(impresion, galeria, "20240513");
    	}); 
    	assertEquals("La fecha 20240513 es inválida. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    }

    @Test
    void consignacionTest()
    {
    	try
    	{
    		Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
	    	assertFalse(galeria.getPiezasActuales().contains(pintura), "La galería tiene la pieza en sus piezas acutales cuando esta no ha sido consignada aún.");
	    	alice.consignarPieza(pintura, "20250101", galeria, "123", "20240513");
	    	assertTrue(galeria.getPiezasActuales().contains(pintura), "La galería no tiene la pieza en sus puezas acutales cuando esta ya ha sido consignada.");
	    	assertTrue(galeria.getHistorialPiezas().contains(pintura), "La galería no tiene la pieza en su historial de piezas antiguas cuando esta ya ha sido consignada.");
	    	assertTrue(pintura.isConsignacion(), "No se cambió el atributo 'consignacion' de la pieza");
	    	assertTrue(pintura.isDispsubasta(), "No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertTrue(pintura.isDispventa(), "No se cambió el atributo 'dispventa' de la pieza");
	    	assertTrue(pintura.isExhibida(), "No se cambió el atributo 'Exhibida' de la pieza");
	    	assertEquals("123", pintura.getExhibaVendaoSubasta(), "No se cambió el atributo 'exhibaVendaoSubasta' de la pieza");
	    	assertEquals("20250101", pintura.getFechaLimite(), "No se cambió el atributo 'fechaLimite' de la pieza.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacion1Test()
    {
    	try
    	{
    		Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
	    	alice.consignarPieza(pintura, "20250101", galeria, "23", "20240513");
	    	assertTrue(pintura.isDispsubasta(), "No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertTrue(pintura.isDispventa(), "No se cambió el atributo 'dispventa' de la pieza");
	    	assertFalse(pintura.isExhibida(), "Se cambió el atributo 'exhibida' de la pieza cuando no se debía cambiar.");

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacion2Test()
    {
    	try
    	{
    		Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
	    	alice.consignarPieza(pintura, "20250101", galeria, "13", "20240513");
	    	assertTrue(pintura.isConsignacion(), "No se cambió el atributo 'consignacion' de la pieza");
	    	assertTrue(pintura.isDispsubasta(), "No se cambió el atributo 'dispsubasta' de la pieza");
	    	assertFalse(pintura.isDispventa(), "Se cambió el atributo 'dispventa' de la pieza cuando no se debía cambiar.");
	    	assertTrue(pintura.isExhibida(), "No se cambió el atributo 'exhibida' de la pieza.");

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacion3Test()
    {
    	try
    	{
    		Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
	    	alice.consignarPieza(pintura, "20250101", galeria, "12", "20240513");
	    	assertTrue(pintura.isConsignacion(), "No se cambió el atributo 'consignacion' de la pieza");
	    	assertFalse(pintura.isDispsubasta(), "Se cambió el atributo 'dispsubasta' de la pieza cuando no se debía cambiar.");
	    	assertTrue(pintura.isDispventa(), "No se cambió el atributo 'dispventa' de la pieza.");
	    	assertTrue(pintura.isExhibida(), "No se cambió el atributo 'exhibida' de la pieza.");

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacionthrowPropietarioErroneoTest()
    {
    	try
    	{
    		Exception exception = assertThrows(PropietarioErroneoException.class, ()->{
    			Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
    			lucy.consignarPieza(pintura, "20250101", galeria, "123", "20240513");
        	}); 
        	assertEquals("El usuarioLucy no es el dueño de la piezaSelene.\n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacionthrowFechaInvalida1Test()
    {
    	try
    	{
    		Exception exception = assertThrows(FechaInvalidaException.class, ()->{
    			Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
    			alice.consignarPieza(pintura, "202501016", galeria, "123", "20240513");
        	}); 
        	assertEquals("La fecha 202501016 es inválida. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacionthrowFechaInvalida2Test()
    {
    	try
    	{
    		Exception exception = assertThrows(FechaInvalidaException.class, ()->{
    			Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
    			alice.consignarPieza(pintura, "20250101", galeria, "123", "202405130");
        	}); 
        	assertEquals("La fecha 202405130 es inválida. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacionthrowFechaInvalida3Test()
    {
    	try
    	{
    		Exception exception = assertThrows(FechaInvalidaException.class, ()->{
    			Pintura pintura = (Pintura) alice.obtenerPiezaporTitulo("Selene");
    			alice.consignarPieza(pintura, "20250101", galeria, "123", "20250513");
        	}); 
        	assertEquals("La fecha límite ya sucedió. \n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void consignacionthrowConsignacionExistenteTest()
    {
    	try
    	{
    		Exception exception = assertThrows(ConsignacionExistenteException.class, ()->{
    			Impresion impresion = (Impresion) alice.obtenerPiezaporTitulo("Esperanza");
    			alice.consignarPieza(impresion, "20250101", galeria, "123", "20240513");
        	}); 
        	assertEquals("La pieza Esperanza no se puede consignar, ya que, ya se encuentra consignada.\n", exception.getMessage(), "La excepción tiene un mensaje erróneo.");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    @Test
    void solicitarAumentoValorCompratest()
    {
    	assertEquals(2000000, alice.getValorMaximoCompras(), "El valor maximo para compras antes de solicitar el aumento no corresponde.");
    	assertTrue(alice.solicitudAumentoValorCompra(galeria), "No se aumentó el valor maximo para compra cuando sí debía aumentarse.");
    	assertEquals(1000000000, alice.getValorMaximoCompras(), "El aumento del valor maximo para compras no es correcto.");
    	assertEquals(3000000, lucy.getValorMaximoCompras(), "El valor maximo para compras antes de solicitar el aumento no corresponde.");
    	assertFalse(lucy.solicitudAumentoValorCompra(galeria), "Se aumentó el valor maximo para compra cuando no debía aumentarse.");
    	assertEquals(3000000, lucy.getValorMaximoCompras(), "Se modificó el valor maximo para compras cuando esto no debía suceder.");
    }
    
    @Test
    void revisarEstadoPiezatest()
    {
    	Escultura escultura = (Escultura) john.obtenerPiezaporTitulo("Venus de Milo");
    	Fotografia fotografia = (Fotografia) lucy.obtenerPiezaporTitulo("Posibilidad");
    	assertEquals("La pieza se encuentra exhibida", john.revisarEstadoPieza(escultura));
    	assertEquals("La pieza se encuentra en bodega", alice.revisarEstadoPieza(fotografia));
    }
    
    @Test
    void historialPiezasTest()
    {
    	try {
			galeria.crearComprador("loginCP", "passwordCP", "Comprador Prueba", 10000000, 0, new HashMap<Pieza, String>(), new ArrayList<Pieza>(), 1000000000, "1234332132");
			Comprador comprador = (Comprador) galeria.obtenerUsuarioPorLogin("loginCP");
			assertTrue(comprador.getHistorialPiezas().isEmpty(), "El historial del comprador no se inicializa vacío");
			Impresion impresion = (Impresion) alice.obtenerPiezaporTitulo("Esperanza");
			Fotografia fotografia = (Fotografia) fake.obtenerPiezaporTitulo("Serenidad en la playa");
			comprador.comprarPieza(impresion, galeria, "2024-05-13");
			assertEquals(1, comprador.getHistorialPiezas().size(), "El historial del comprador no se actualiza al realizar compras.");
			assertTrue(comprador.getHistorialPiezas().keySet().contains(impresion), "El historial se actualiza con llaves incorrectas.");
			assertEquals("2024-05-13", comprador.getHistorialPiezas().get(impresion), "El historial es actualiza con valores incorrectas.");
			comprador.comprarPieza(fotografia, galeria, "2024-08-26");
			assertEquals(2, comprador.getHistorialPiezas().size(), "El historial del comprador no se actualiza al realizar compras.");
			assertTrue(comprador.getHistorialPiezas().keySet().contains(fotografia), "El historial se actualiza con llaves incorrectas.");
			assertEquals("2024-08-26", comprador.getHistorialPiezas().get(fotografia), "El historial es actualiza con valores incorrectas.");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}


