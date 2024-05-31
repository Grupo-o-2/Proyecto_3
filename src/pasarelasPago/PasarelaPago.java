package pasarelasPago;

import java.io.IOException;

public abstract class PasarelaPago {
	
	public abstract void registrarPago(String nombre, String telefono, String numeroTarjeta, int monto, boolean resultado ) throws IOException;
}
