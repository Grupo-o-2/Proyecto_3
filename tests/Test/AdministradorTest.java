package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import usuarios.*;
import piezas.*;
import modelo.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class AdministradorTest {
	
	private static final String LOGIN_ADMIN = "loginAlejandra";
	private static final String PASSWORD_ADMIN = "Contraseña123";
	private static final String NOMBRE_ADMIN = "Alejandra";
	private static final String NUMERO_ADMIN = "3012849079";
	
	private static final String LOGIN_OPERADOR = "loginFranco";
	private static final String PASSWORD_OPERADOR = "Contraseña1";
	private static final String NOMBRE_OPERADOR = "Franco";
	private static final String NUMERO_OPERADOR = "3135453738";
	
	private static final String LOGIN_CAJERO = "loginVal";
	private static final String PASSWORD_CAJERO = "Nojiji";
	private static final String NOMBRE_CAJERO = "Val";
	private static final String NUMERO_CAJERO = "3283228947";
	
	private static final String LOGIN_COMPRADOR = "loginElias";
	private static final String PASSWORD_COMPRADOR = "12345";
	private static final String NOMBRE_COMPRADOR= "Elias";
	private static final String NUMERO_COMPRADOR = "3012485522";
	
	private static final String LOGIN_ARTISTA = "loginJose";
	private static final String PASSWORD_ARTISTA = "Integrales";
	private static final String NOMBRE_ARTISTA= "Jose";
	private static final String NUMERO_ARTISTA = "3012849098";
	
	private static final String NOMBRE_PIEZA = "El grito";
	private static final String FECHA_CREACION = "20240514";
	private static final String LUGAR_CREACION = "Noruega";
	private static final double ALTO = 91;
	private static final double ANCHO = 73.5;
	private static final int VALOR = 120000000;
	
	private static final String NOMBRE_PIEZA2 = "Venus de Milo";
	private static final String FECHA_CREACION2 = "10001205";
	private static final String LUGAR_CREACION2 = "Grecia";
	private static final int VALOR2 = 15800000;
	private static final double ANCHO2 = 90;
	private static final double ALTO2 = 211;
	private static final double PROFUNDIDAD = 60;
	private static final String MATERIALES = "Marmol";
	private static final double PESO = 900;
	
	private static final String NOMBRE_GALERIA = "Las maravillas";
	
	
	
	
	
	private Galeria g1;
	

    @BeforeEach
    void setUp( ) throws Exception
    {  
        		
    }
    
    
    void crearGaleria1( ) {
    	ArrayList<Subasta> subastas = new ArrayList<Subasta>();
    	ArrayList<Pieza> piezas = new ArrayList<Pieza>();
    	piezas.add(new Pintura(NOMBRE_PIEZA, VALOR, FECHA_CREACION, LUGAR_CREACION,
    			));
    	
    	g1 = new Galeria (NOMBRE_GALERIA, subastas,  )
    	
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

}
//(String login, String password, String nombre, int telefono)