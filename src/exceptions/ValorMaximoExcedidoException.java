package exceptions;

@SuppressWarnings("serial")
public class ValorMaximoExcedidoException extends Exception{	
	
	public  ValorMaximoExcedidoException (String usuario)  {
			
		super("El usuario " + usuario + " ha excedido el valor máximo de compras.\n");
	}
}