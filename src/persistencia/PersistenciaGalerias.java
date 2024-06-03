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
		String stringfechasCompras = "";
		HashMap<String, Integer> fechasCompras = galeria.getFechasCompras();
		if (! fechasCompras.keySet().isEmpty())
		{
			for (String fecha: galeria.getFechasCompras().keySet())
			{
				stringfechasCompras = stringfechasCompras + fecha + "," + fechasCompras.get(fecha) + ";";
			}
		}
		else
		{
			stringfechasCompras= "Vacío";
		}
		jPiezas.put( "Compras por fecha", stringfechasCompras);
		String compras = "";
		if(! galeria.getCompras().isEmpty())
		{
			for (ArrayList<String> arrayCommpra: galeria.getCompras())
			{
				String valoresCompras = "";
				if (! arrayCommpra.isEmpty())
				{
					for (String informacion: arrayCommpra)
					{
						valoresCompras = valoresCompras + informacion + ",";
					}
				}
				else
				{
					valoresCompras= "Vacio";
				}
				compras = compras + valoresCompras + ";";
			}
		}
		else
		{
			compras = "Vacio";
		} 
		jPiezas.put("Compras",compras);
		String ofertas = "";
		if(! galeria.getOfertas().isEmpty())
		{
			for (ArrayList<String> arrayCommpra: galeria.getOfertas())
			{
				String valoresOfertas = "";
				if (! arrayCommpra.isEmpty())
				{
					for (String informacion: arrayCommpra)
					{
						valoresOfertas = valoresOfertas + informacion + ",";
					}
				}
				else
				{
					valoresOfertas= "Vacio";
				}
				ofertas = ofertas + valoresOfertas + ";";
			}
		}
		else
		{
			ofertas = "Vacio";
		} 
		jPiezas.put("Ofertas",ofertas);
		jObject.put("Galeria", jPiezas);
	}

	public void cargarGalerias(JSONObject jGaleria, Galeria galeria, HashMap<String, Pieza> identificacionPieza)
	{
		ArrayList<Pieza> nHistorialPiezas = new ArrayList<Pieza>();
		ArrayList<Pieza> nPiezasAntiguas = new ArrayList<Pieza>();
		HashMap<String, Integer> nFechasCompras = new HashMap<String, Integer>();
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
		
		String FechasCompras = jGaleria.getString("Compras por fecha");
		if (! FechasCompras.equals("Vacío"))
		{
			for (String fecha: FechasCompras.split(";"))
			nFechasCompras.put(fecha.split(",")[0],Integer.parseInt(fecha.split(",")[1]));
		}
		ArrayList<ArrayList<String>> nCompras = new ArrayList<ArrayList<String>>();
		String compras = jGaleria.getString("Compras");
		if (! compras.equals("Vacio"))
		{
			for (String valoresCompras: compras.split(";")) {
			ArrayList<String> nValores = new ArrayList<String>();
			nValores.add(valoresCompras.split(",")[0]);
			nValores.add(valoresCompras.split(",")[1]);
			nValores.add(valoresCompras.split(",")[2]);
			nValores.add(valoresCompras.split(",")[3]);
			nValores.add(valoresCompras.split(",")[4]);
			nCompras.add(nValores);
			}
		}
		ArrayList<ArrayList<String>> nOfertas = new ArrayList<ArrayList<String>>();
		String ofertas = jGaleria.getString("Ofertas");
		if (! ofertas.equals("Vacio"))
		{
			for (String valoresCompras: ofertas.split(";")) {
			ArrayList<String> nValores = new ArrayList<String>();
			nValores.add(valoresCompras.split(",")[0]);
			nValores.add(valoresCompras.split(",")[1]);
			nValores.add(valoresCompras.split(",")[2]);
			nValores.add(valoresCompras.split(",")[3]);
			nOfertas.add(nValores);
			}
		}
		galeria.setHistorialPiezas(nHistorialPiezas);
		galeria.setPiezasActuales(nPiezasActuales);
		galeria.setPiezasAntiguas(nPiezasAntiguas);
		galeria.setFechasCompras(nFechasCompras);
		galeria.setCompras(nCompras);
		galeria.setOfertas(nOfertas);
	}
}


