package usuarios;

	abstract public class Usuario {
	private String login;
	private String password;
	private String nombre;
	private String telefono;

	
	
	public Usuario(String login, String password, String nombre, String telefono) {
		
		this.login = login;
		
		this.password = password;
		
		this.nombre = nombre;
		
		this.telefono = telefono;

	}
	
	public Usuario() {}



	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return this.telefono;
	} 

	public abstract String getTipo();

	}


