package modelo;

import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class Galeria {

	private String nombre;
	private ArrayList<Subasta> subastas;
	private ArrayList<Pieza> historialPiezas;
	private ArrayList<Pieza> piezasActuales;
	private ArrayList<Pieza> piezasAntiguas;
	private ArrayList<Usuario> usuarios;
	private HashMap<String, Integer> fechasCompras =  new HashMap<String, Integer>();
	private Fabrica fabrica = new Fabrica();
	private static final String TIPO_ARTISTA = "Artista";
	private static final String TIPO_COMPRADOR = "Comprador";

	public Galeria(String nombre, ArrayList<Subasta> subastas, ArrayList<Pieza> historialPiezas,
			ArrayList<Pieza> piezasActuales, ArrayList<Pieza> piezasAntiguas, ArrayList<Usuario> usuarios )
	{
		this.nombre = nombre;
		this.subastas = subastas;
		this.historialPiezas = historialPiezas;
		this.piezasActuales = piezasActuales;
		this.piezasAntiguas = piezasAntiguas;
		this.usuarios = usuarios;
	}



	public String getNombre() {
		return nombre;
	}

	public ArrayList<Subasta> getSubastas() {
		return subastas;
	}

	public ArrayList<Pieza> getHistorialPiezas() {
		return historialPiezas;
	}
	
	public void setHistorialPiezas(ArrayList<Pieza> historialPiezas)
	{
		this.historialPiezas = historialPiezas;
	}
	
	public void setPiezasAntiguas(ArrayList<Pieza> piezasAntiguas)
	{
		this.piezasAntiguas = piezasAntiguas;
	}
	
	public void setPiezasActuales(ArrayList<Pieza> piezasActuales)
	{
		this.piezasActuales = piezasActuales;
	}

	public ArrayList<Pieza> getPiezasActuales() {
		return piezasActuales;
	}

	public ArrayList<Pieza> getPiezasAntiguas() {
		return piezasAntiguas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Fabrica getFabrica() {
		return this.fabrica;
	}
	
	public HashMap<String, Integer> getFechasCompras(){
		return this.fechasCompras;
	}
	
	public void setFechasCompras(HashMap<String, Integer> fechasCompras) {
		this.fechasCompras = fechasCompras;
	}

	public ArrayList<Pieza> obtenerPiezasEnBodega (){
		
		ArrayList<Pieza> piezasBodega = new ArrayList<Pieza>();
		for (Pieza pieza: this.piezasActuales) {
			
			if ( pieza.isExhibida() == false) {
				piezasBodega.add(pieza); }
			
		}
		return piezasBodega;
	}
	
	public ArrayList<Pieza> obtenerPiezasEnExhibicion (){
		
		ArrayList<Pieza> piezasExhibidas = new ArrayList<Pieza>();
		for (Pieza pieza: this.piezasActuales) {
			
			if ( pieza.isExhibida()) {
				piezasExhibidas.add(pieza); }
			
		}
		return piezasExhibidas;
	}
	
	public void añadirPieza(Pieza nuevaPieza){
		this.piezasActuales.add(nuevaPieza);
		this.historialPiezas.add(nuevaPieza);
	}
	
	public void añadirPiezaHistorial(Pieza pieza)
	{
		this.historialPiezas.add(pieza);
	}
	
	public boolean verificarUsuario(Usuario usuarioAVerificar) throws UsuarioInexistenteException {

		if ((this.usuarios.contains(usuarioAVerificar)) == false ) {

			throw new UsuarioInexistenteException(usuarioAVerificar.getNombre());
		}
		else {
			return true;
		}

	}
	
	public HashMap<String, String> obtenerLoginContraseña(){
		HashMap<String, String> logins = new HashMap<String,String>();
		String login;
		String contraseña;
		
		for (Usuario usuario : this.usuarios) {
			login = usuario.getLogin();
			contraseña = usuario.getPassword();
			logins.put(login, contraseña);
		}
		return logins;	
	}
	
	public Usuario obtenerUsuarioPorLogin(String login) {
		Usuario usuario1 = null;
		for (Usuario usuario:this.usuarios) {
			if (usuario.getLogin().compareTo(login) == 0) {
				usuario1 = usuario;
			}
		}
		return usuario1;
	}
	
	public Usuario obtenerUsuarioPorNombre(String nombre) {
		Usuario usuario1 = null;
		for (Usuario usuario:this.usuarios) {
			if (usuario.getNombre().compareTo(nombre) == 0) {
				usuario1 = usuario;
			}
		}
		return usuario1;
	}
	
	public static boolean esFechaValida(String fecha) {
        if (fecha == null || fecha.length() != 8) {
            return false;
        }

        try {
            int año = Integer.parseInt(fecha.substring(0, 4));
            int mes = Integer.parseInt(fecha.substring(4, 6));
            int dia = Integer.parseInt(fecha.substring(6));

            if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                return false;
            }

            boolean esBisiesto = (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
            int diasEnMes = diasEnMes(mes, esBisiesto);
            return dia <= diasEnMes;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	private static int diasEnMes(int mes, boolean esBisiesto) {
        switch (mes) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return esBisiesto ? 29 : 28;
            default:
                return 31;
        }
    }
	
	//Si retorna 0, pertenece a los usuarios y es el tipo adecuado, si retorna 1 no pertenece a los usuarios, si retorna 2 no es el tipo adecuado.
	public int verificacionSesion (String login, String contraseña, String tipo) {
		HashMap<String, String>  logins = obtenerLoginContraseña();
		
		
		
		if (logins.containsKey(login)) {
			
			if ((logins.get(login).compareTo(contraseña) ) == 0) {
				if(obtenerUsuarioPorLogin(login).getTipo() == tipo) {
					return 1;
				}
				else 
					return 2;
			}
			else
				return 3;
		}
		
		else 
			return 0;
		
	}
	
	
	

	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void crearComprador(String login, String password,  String nombre, int valorMaximoCompras, int valorColeccion, 
			HashMap<Pieza, String> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, String telefono) throws LoginException
	{
		getFabrica().crearComprador( login, password, nombre, valorMaximoCompras, valorColeccion, 
			 historialPiezas,  piezasActuales, dinero, telefono, this);
	}
	
	public void crearArtista(String login, String password, String nombre, String telefono, ArrayList<Pieza> piezasCreadas) throws LoginException
	{
		getFabrica().crearArtista( login, password, nombre, telefono, piezasCreadas, this);
	}
	
	public void crearAdministrador(String login, String password, String nombre, String telefono) throws LoginException
	{
		getFabrica().crearAdministrador( login, password, nombre, telefono, this);
	}
	
	public void crearOperador(String login, String password, String nombre, String telefono) throws LoginException
	{
		getFabrica().crearOperador( login, password, nombre, telefono, this);
	}
	
	public void crearCajero(String login, String password, String nombre, String telefono) throws LoginException
	{
		getFabrica().crearCajero( login, password, nombre, telefono, this);
	}
	
	public void crearEmpleado(String login, String password, String nombre, String telefono) throws LoginException
	{
		getFabrica().crearEmpleado( login, password, nombre, telefono, this);
	}
	
	public void crearEscultura(String titulo, int valor, String fecha, String lugar, Comprador creador,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, String fechaLimite, double alto, double ancho, double profundidad, String materiales, double peso, boolean electricidad, boolean otroDetalle)
	{
		getFabrica().crearEscultura(titulo, valor, fecha, lugar, creador, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida, dispsubasta, dispventa, fechaLimite, alto, ancho, profundidad, materiales, peso, electricidad, otroDetalle);
	}
	
	public void crearFotografia(String titulo, int valor, String fecha, String lugar, Comprador creador,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, String fechaLimite, double alto,double ancho, String formato, boolean enmarcado )
	{
		getFabrica().crearFotografia(titulo, valor, fecha, lugar, creador, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida, dispsubasta, dispventa, fechaLimite, alto, ancho, formato, enmarcado);
	}
	
	public void crearImpresion(String titulo, int valor, String fecha, String lugar, Comprador creador,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa,String fechaLimite, double alto, double ancho)
	{
		getFabrica().crearImpresion(titulo, valor, fecha, lugar, creador, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida, dispsubasta, dispventa, fechaLimite, alto, ancho);
	}
	
	public void crearPintura(String titulo, int valor, String fecha, String lugar, Comprador creador,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, String fechaLimite, double alto, double ancho )
	{
		getFabrica().crearPintura(titulo, valor, fecha, lugar, creador, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida, dispsubasta, dispventa, fechaLimite, alto, ancho);
	}
	
	public void crearVideo(String titulo, int valor, String fecha, String lugar, Comprador creador,
			ArrayList<Artista> autores, ArrayList<Usuario> dueños, HashMap<String, Integer> ventas,
			String exhibaVendaoSubasta, boolean consignacion, boolean exhibida, boolean dispsubasta,
			boolean dispventa, String fechaLimite, double alto, double ancho, int duracion, String formato)
	{
		getFabrica().crearVideo(titulo, valor, fecha, lugar, creador, autores, dueños, ventas, exhibaVendaoSubasta, consignacion, exhibida, dispsubasta, dispventa, fechaLimite, alto, ancho, duracion, formato);
	}
	
	
	public void cargarGaleria(String archivo)
	{
		try
		{
			getFabrica().cargarGaleria(archivo, this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void salvarGaleria(String archivo)
	{
		try
		{
			getFabrica().salvarGaleria(archivo, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	// ¡Crear usuario! y pieza. 

	public Usuario getAdministrador() {
		Usuario administrador = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Administrador") {
				administrador = usuario;}
		}
		return administrador;
	}


	public Usuario getUnCajero() {

		Usuario cajero = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Cajero") {
				cajero = usuario;}
		}
		return cajero;
	}

	public Usuario getUnOperador() {

		Usuario operador = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Operador") {
				operador = usuario;}
		}
		return operador;
	}

	public Pieza obtenerPiezaporTitulo(String titulo) {
		for (Pieza pieza:this.piezasActuales) {
			if (pieza.getTitulo().compareTo(titulo) == 0) {
				return pieza;
			}
		}
		return null;
	}
	

	public Pieza obtenerPiezaGlobalesporTitulo(String titulo) {
		for (Pieza pieza:getFabrica().getPiezasCreadas()) {
			if (pieza.getTitulo().compareTo(titulo) == 0) {
				return pieza;
			}
		}
		return null;
	}
	
	public ArrayList<Usuario> obtenerArtistas() {
		ArrayList<Usuario> artistas = new ArrayList<Usuario>();
		 
		for (Usuario artista: this.getUsuarios()) {
			if (artista.getTipo() == TIPO_ARTISTA ) {
				artistas.add(artista);
			}
		}
		
		return artistas;
	}
	
	public ArrayList<Usuario> obtenerCompradores() {
		ArrayList<Usuario> compradores = new ArrayList<Usuario>();
		
		for (Usuario comprador: this.getUsuarios()) {
			if (comprador.getTipo() == TIPO_COMPRADOR ) {
				compradores.add(comprador);
			}
		}
		
		return compradores;
	}
	
	public static boolean esFechaValida2(String fecha) {
        if (fecha == null || fecha.length() != 10) {
            return false;
        }

        try {
            int año = Integer.parseInt(fecha.substring(0, 4));
            int mes = Integer.parseInt(fecha.substring(5, 7));
            int dia = Integer.parseInt(fecha.substring(8));

            if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                return false;
            }

            boolean esBisiesto = (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
            int diasEnMes = diasEnMes2(mes, esBisiesto);
            return dia <= diasEnMes;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int diasEnMes2(int mes, boolean esBisiesto) {
        switch (mes) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return esBisiesto ? 29 : 28;
            default:
                return 31;
        }
    }
    
    public static String formatearFecha(String fecha) {
        if (fecha == null || fecha.length() != 8) {
            return "Formato de fecha incorrecto";
        }

        StringBuilder sb = new StringBuilder(fecha);
        sb.insert(4, "-"); // Inserta "-" después del año
        sb.insert(7, "-"); // Inserta "-" después del mes
        return sb.toString();
    }
	
	public void realizarConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta, String fechaActual) throws PropietarioErroneoException, FechaInvalidaException, ConsignacionExistenteException {
		((Administrador )this.getAdministrador()).registrarPiezaPorConsignacion(propietario, piezaAConsignar, fechaLimite, this, exhibaVendaoSubasta,  fechaActual);

	}

	public Subasta crearSubasta(String nombre,ArrayList<Usuario> participantes, Usuario operador, HashMap<Pieza,HashMap<Usuario, 
			Integer>> registroSubasta, HashMap<Pieza, ArrayList<Integer>> piezasSubastadas) throws UsuarioInexistenteException {

		Subasta nuevaSubasta = new Subasta(null, null, null, null, null);
		if (  ((Administrador )this.getAdministrador()).verificarUsuariosSubasta(participantes, this) == true  ) {

			nuevaSubasta = new Subasta(nombre, participantes, operador, registroSubasta, piezasSubastadas);
			this.subastas.add(nuevaSubasta);

		}
		return nuevaSubasta;
	}

	public void finalizarSubasta(Subasta subasta, String fecha)  {

		subasta.finalizarSubasta(this, fecha);
	}
	
	public Subasta obtenerSubastaPorNombre(String nombreSubasta) {
		Subasta subasta = null;
		for (Subasta subasta1 : this.getSubastas()) {
			if ( subasta1.getNombre().compareTo(nombreSubasta) == 0 ) {
				return subasta1;
			}
			
		}
		return subasta;
	}
	
    public static boolean verificarFormato(String nombres) throws FormatoIncorrectoException {
        if (!nombres.matches("([a-zA-Z]+-)*[a-zA-Z]+")) {
            throw new FormatoIncorrectoException();
        }
        
        else {
        	return true;
        }
    }



	

}