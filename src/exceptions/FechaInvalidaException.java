package exceptions;

@SuppressWarnings("serial")
public class FechaInvalidaException extends Exception{	
	
	public  FechaInvalidaException(String fecha) {
		super("La fecha " + fecha + " es inválida. \n");	
	}
	
	public FechaInvalidaException() {
		super("La fecha límite ya sucedió. \n");
	}
}
