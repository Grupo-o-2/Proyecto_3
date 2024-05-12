package exceptions;


@SuppressWarnings("serial")
public class ConsignacionExistenteException extends Exception{	
	
	public  ConsignacionExistenteException (String pieza)  {
			
		super("La pieza " + pieza + " no se puede consignar, ya que, ya se encuentra consignada.\n");
	}
	
}



 