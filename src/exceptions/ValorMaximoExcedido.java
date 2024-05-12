package exceptions;

@SuppressWarnings("serial")
public class ValorMaximoExcedido extends Exception{	
	
	public  ValorMaximoExcedido (String usuario)  {
			
		super("El usuario " + usuario + " ha excedido el valor máximo de compras.\n");
	}
}