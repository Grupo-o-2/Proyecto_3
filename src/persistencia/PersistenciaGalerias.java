package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.*;
import piezas.*;
import usuarios.*;

public class PersistenciaGalerias {

	public PersistenciaGalerias()
	{
		
	}
	
	public void salvarGaleria(Galeria galeria, JSONObject jObject, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jPiezas = new JSONObject();
		ArrayList<Pieza> piezasAntiguas = galeria.getPiezasAntiguas();
		String piezasAntiguasString = "";
		if (!piezasAntiguas.isEmpty())
		{
			for (Pieza pieza: piezasAntiguas)
			{
				piezasAntiguasString = piezasAntiguasString + identificadorPieza.get(pieza) + ","; 
			}
		}
		else
		{
			piezasAntiguasString = "Vacio";
		}
		jPiezas.put("Piezas Antiguas", piezasAntiguasString);
		ArrayList<Pieza> piezasActuales = galeria.getPiezasActuales();
		String piezasActualesString = "";
		if (!piezasActuales.isEmpty())
		{
			for (Pieza pieza: piezasActuales)
			{
				piezasActualesString = piezasActualesString + identificadorPieza.get(pieza) + ","; 
			}
		}
		else
		{
			piezasActualesString = "Vacio";
		}
		jPiezas.put("Piezas Actuales", piezasActualesString);
		jObject.put("Galeria", jPiezas);
	}

	public void cargarGalerias(JSONObject jGaleria, Galeria galeria, HashMap<String, Pieza> identificacionPieza)
	{
		ArrayList<Pieza> nHistorialPiezas = new ArrayList<Pieza>();
		ArrayList<Pieza> nPiezasAntiguas = new ArrayList<Pieza>();
		if (! jGaleria.getString("Piezas Antiguas").equals("Vacio"))
		{
			for(String identificador: jGaleria.getString("Piezas Antiguas").split(","))
			{
				nPiezasAntiguas.add(identificacionPieza.get(identificador));
				nHistorialPiezas.add(identificacionPieza.get(identificador));
			}
		}
		ArrayList<Pieza> nPiezasActuales = new ArrayList<Pieza>();
		if (! jGaleria.getString("Piezas Actuales").equals("Vacio"))
		{ 
			for(String identificador: jGaleria.getString("Piezas Actuales").split(","))
			{
				nPiezasActuales.add(identificacionPieza.get(identificador));
				nHistorialPiezas.add(identificacionPieza.get(identificador));
			}
		}
		galeria.setHistorialPiezas(nHistorialPiezas);
		galeria.setPiezasActuales(nPiezasActuales);
		galeria.setPiezasAntiguas(nPiezasAntiguas);
		}
}

