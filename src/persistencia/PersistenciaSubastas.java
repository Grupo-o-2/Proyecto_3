package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.*;
import modelo.*;
import piezas.Pieza;
import usuarios.*;


public class PersistenciaSubastas {

	
	public PersistenciaSubastas()
	{
		
	}
	
	public void salvarSubastas(ArrayList<Subasta> subastas, JSONObject jObject, HashMap<Pieza, String> identificadorPieza )
	{ 
		if (subastas.size() != 0)
		{
			JSONArray jSubastas = new JSONArray();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("Vacio", "No");
			jSubastas.put(jsonobject);
			for (Subasta subasta: subastas)
			{
				JSONObject jSubasta = new JSONObject();
				String participantes = ""; 
				if (! subasta.getParticipantes().isEmpty())
				{
					for(Usuario participante: subasta.getParticipantes())
					{
						participantes = participantes + participante.getLogin() + ",";
					}
				}
				else
				{
					participantes= "Vacio";
				}
				jSubasta.put("Participantes", participantes);
				jSubasta.put("Operador", subasta.getOperador().getLogin());
				jSubasta.put("Nombre", subasta.getNombre());
				String registroSubasta = "";
				if(! subasta.getRegistroSubasta().keySet().isEmpty())
				{
					for (Pieza pieza: subasta.getRegistroSubasta().keySet())
					{
						String usuarioOferta = "";
						if (! subasta.getRegistroSubasta().get(pieza).isEmpty())
						{
							for (Usuario usuario: subasta.getRegistroSubasta().get(pieza).keySet())
							{
								usuarioOferta = usuarioOferta + usuario.getLogin() + "," + subasta.getRegistroSubasta().get(pieza).get(usuario) + ";";
							}
						}
						else
						{
							usuarioOferta= "Vacio";
						}
						registroSubasta = registroSubasta + identificadorPieza.get(pieza) + "'" + usuarioOferta + "/";
					}
				}
				else
				{
					registroSubasta = "Vacio";
				}
				jSubasta.put("Registro Subasta",registroSubasta);
				String piezasSubastadas = "";
				if(! subasta.getPiezasSubastadas().keySet().isEmpty())
				{
					for (Pieza pieza: subasta.getPiezasSubastadas().keySet())
					{
						String valoresPieza = "";
						if (! subasta.getPiezasSubastadas().get(pieza).isEmpty())
						{
							for (int valor: subasta.getPiezasSubastadas().get(pieza))
							{
								valoresPieza = valoresPieza + valor + ",";
							}
						}
						else
						{
							valoresPieza= "Vacio";
						}
						piezasSubastadas = piezasSubastadas + identificadorPieza.get(pieza) + ";" + valoresPieza + "'";
					}
				}
				else
				{
					registroSubasta = "Vacio";
				} 
				jSubasta.put("Piezas Subastadas",piezasSubastadas);
				jSubastas.put(jSubasta);	
			}
			jObject.put("Subastas",jSubastas);
		}
		else
		{
			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("Vacio", "Sí");
			jsonarray.put(jsonobject);
			jObject.put("Subastas",jsonarray);
		}
	}
	
	
	public void cargarSubastas(JSONArray jSubastas, Galeria galeria, HashMap<String, Usuario> loginUsuarios, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException, UsuarioInexistenteException
	{
		if(!jSubastas.getJSONObject(0).getString("Vacio").equals("Sí"))
		{		
			int numeroSubastas = jSubastas.length();
			for (int i = 1 ; i < numeroSubastas ; i++)
			{
				JSONObject subasta = jSubastas.getJSONObject( i );
				String loginOperador = subasta.getString("Operador");
				if (! loginUsuarios.containsKey(loginOperador))
				{
					throw new LoginInexistenteException(loginOperador);
				}
				Operador nOperador = (Operador) loginUsuarios.get(subasta.getString("Operador"));
				String nNombre = subasta.getString("Nombre");
				ArrayList<Usuario> nParticipantes = new ArrayList<Usuario>();
				if(! subasta.getString("Participantes").equals("Vacio"))
				{
					for (String login: subasta.getString("Participantes").split(","))
						{
						if (! loginUsuarios.containsKey(loginOperador))
						{
							throw new LoginInexistenteException(loginOperador);
						}
						nParticipantes.add(loginUsuarios.get(login));
						}
				}	
				HashMap<Pieza,HashMap<Usuario, Integer>> nRegistroSubasta = new HashMap<Pieza,HashMap<Usuario, Integer>>();
				if (! subasta.getString("Registro Subasta").equals("Vacio"))
				{
					for (String pieza: subasta.getString("Registro Subasta").split("/"))
					{
						HashMap<Usuario, Integer> oferta = new HashMap<Usuario, Integer>();
						if (! pieza.split("'")[1].equals("Vacio"))
						{
							for (String login: pieza.split("'")[1].split(";"))
							{
								if (! loginUsuarios.containsKey(loginOperador))
								{
									throw new LoginInexistenteException(loginOperador);
								}
								oferta.put(loginUsuarios.get(login.split(",")[0]), Integer.parseInt(login.split(",")[1]));
							}
						}
						nRegistroSubasta.put(identificacionPieza.get(pieza.split("'")[0]), oferta);
					}
				}
				else
				{
					if(! subasta.getString("Piezas Subastadas").equals("Vacio"))
					{
						for (String identificadorPieza: subasta.getString("Piezas Subastadas").split("'"))
						{
							HashMap<Usuario, Integer> nHistorialPieza = new HashMap<Usuario, Integer>();
							nRegistroSubasta.put(identificacionPieza.get(identificadorPieza.split(";")[0]), nHistorialPieza);
						}
					}
				}
				HashMap<Pieza,ArrayList<Integer>> nPiezasSubastadas = new HashMap<Pieza,ArrayList<Integer>>();
				if (! subasta.getString("Piezas Subastadas").equals("Vacio"))
				{
					for (String identificadorPieza: subasta.getString("Piezas Subastadas").split("'"))
					{
						ArrayList<Integer> valores = new ArrayList<Integer>(); 
						if (! identificadorPieza.split(";")[1].equals("Vacio"))
						{
							for (String valoresString:identificadorPieza.split(";")[1].split(","))
							{
								valores.add(Integer.parseInt(valoresString));
							}
						}
						nPiezasSubastadas.put(identificacionPieza.get(identificadorPieza.split(";")[0]), valores);
					}
				}
				galeria.crearSubasta(nNombre, nParticipantes, nOperador, nRegistroSubasta, nPiezasSubastadas);
			}
		}
	}
}
