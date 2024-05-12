package exceptions;

@SuppressWarnings("serial")
public class MismoComprador extends Exception{	
	
	public  MismoComprador() {
		super("No puedes comprar esta pieza debido a que ya es de tu propiedad. \n");	
	}

}