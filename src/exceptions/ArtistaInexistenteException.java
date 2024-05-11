package exceptions;


@SuppressWarnings("serial")
public class ArtistaInexistenteException extends Exception{	
	
	public  ArtistaInexistenteException(String login) {
		super("El artista identificado con el login " + login + " no se encuentra registrado en esta galeria. \n");	
	}

}