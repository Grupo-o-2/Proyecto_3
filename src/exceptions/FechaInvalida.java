package exceptions;

@SuppressWarnings("serial")
public class FechaInvalida extends Exception{	
	
	public  FechaInvalida(String fecha) {
		super("La fecha " + fecha + " es inválida. \n");	
	}
	
	public FechaInvalida() {
		super("La fecha límite ya sucedió. \n");
	}
}
