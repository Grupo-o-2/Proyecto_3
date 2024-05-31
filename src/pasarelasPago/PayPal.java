package pasarelasPago;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class PayPal extends PasarelaPago{

	public void registrarPago(String nombre, String telefono, String numeroTarjeta, int monto, boolean resultado ) throws IOException{
	
		String registro =nombre + "," + telefono + "," + numeroTarjeta + "," + monto + ",";
		if(resultado) {
			registro = registro + "Aprobado";
		}
		else {
			registro = registro + "Rechazado";
		}
		File ruta = new File("Registro Pasarelas de Pago\\RegistroPayPal.txt");
		File archivo= new File(ruta.getAbsolutePath());
		String registros = new String( Files.readAllBytes( archivo.toPath( ) ) );
		PrintWriter pw = new PrintWriter( new FileWriter(archivo));
		if(registros.equals("")){
			registros =registro;
		}
		else {
			registros = registros + "\n" + registro;
		}
		pw.append(registros);
		pw.close( );
	}

}
