package usuarios;


public class Empleado extends Usuario{
	
	private static final String TIPO = "Empleado";
		
	public Empleado(String login, String password, String telefono, String nombre) {
		super(login, password,  nombre, telefono);
	}
	

	public String getTipo() {
		return TIPO;
	}
}