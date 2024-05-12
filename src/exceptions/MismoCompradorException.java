package exceptions;

@SuppressWarnings("serial")
public class MismoCompradorException extends Exception{	
	
	public  MismoCompradorException() {
		super("No puedes comprar esta pieza debido a que ya es de tu propiedad. \n");	
	}

}