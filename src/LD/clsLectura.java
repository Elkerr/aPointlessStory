package LD;

import java.io.*;
import java.util.ArrayList;

import Comun.clsConstantes.enumFicheros;
import LN.clsArrayC;

/** Clase encargada de leer y cargar un mapa creado por un usuario, se valera de un metodo filereader para convertir un archivo .map (.txt)
 *  en un array de chars o ints*/

public class clsLectura
{
	private final String fichero_info_ranura1 = ".\\data\\Ranura1\\info.rpg";
	private final String fichero_info_ranura2 = ".\\data\\Ranura2\\info.rpg";
	private final String fichero_info_ranura3 = ".\\data\\Ranura3\\info.rpg";
	private final String fichero_pj_ranura1 = ".\\data\\Ranura1\\pj.rpg";
	private final String fichero_pj_ranura2 = ".\\data\\Ranura2\\pj.rpg";
	private final String fichero_pj_ranura3 = ".\\data\\Ranura3\\pj.rpg";
	private final String R1_info_cuad0 = ".\\data\\Ranura1\\Mapa\\Cuad0.txt";
	private final String R1_info_cuad1 = ".\\data\\Ranura1\\Mapa\\Cuad1.txt";
	private final String R1_info_cuad2 = ".\\data\\Ranura1\\Mapa\\Cuad2.txt";
	private final String R1_info_cuad3 = ".\\data\\Ranura1\\Mapa\\Cuad3.txt";

	private ObjectInputStream ois;
	/**Clase encargada de seleccionar el fichero donde se leeran los datos*/
	private String setFichero(enumFicheros fichero)
	{
		switch(fichero)
		{
			case FICHERO_INFO_RANURA1:
			{
				return fichero_info_ranura1;
			}
			case FICHERO_INFO_RANURA2:
			{
				return fichero_info_ranura2;
			}
			case FICHERO_INFO_RANURA3:
			{
				return fichero_info_ranura3;
			}
			case FICHERO_PJ_RANURA1:
			{
				return fichero_pj_ranura1;
			}
			case FICHERO_PJ_RANURA2:
			{
				return fichero_pj_ranura2;
			}
			case FICHERO_PJ_RANURA3:
			{
				return fichero_pj_ranura3;
			}
			
			}
		return null;
		}
		private String setCuadrante(int ranura)
		{
			switch(ranura)
			{
				case 0:
				{
					return R1_info_cuad0;
				}
				case 1:
				{
					return R1_info_cuad1;
				}
				case 2:
				{
					return R1_info_cuad2;
				}
				case 3:
				{
					return R1_info_cuad3;
				}
			}

		return null;
	}
	/**
	 * 
	 * @param fichero: enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a leer. 
	 * @throws IOException: excepción lanzada en caso de que se dé un error de lectura/escritura en fichero.
	 * Método que crea un objectInputStream para la lectura del fichero indicado previamente.
	 */
	public void ComenzarRead(enumFicheros fichero) throws IOException 
	{
		String ruta=setFichero(fichero);
		File fic;

		fic=new File(ruta);

		if(fic.exists())
		{
			ois=new ObjectInputStream(new FileInputStream(fic));
		}
		else
		{
			fic.createNewFile();
			ois=new ObjectInputStream(new FileInputStream(fic));
		}
	}

	/**
	 * 
	 * @return ArrayList<Serializable>: Devuelve un arraylist con los datos leídos, en el tipo
	 * Serializable.
	 */
	public ArrayList<Serializable> Read()
	{
		ArrayList<Serializable> lista = new  ArrayList<Serializable>();
		Serializable a= null;

		try 
		{
			while((a=(Serializable)ois.readObject())!=null)
			{
				lista.add(a);
			}
		}
		catch (ClassNotFoundException | IOException e){} 

		return lista;
	}
	/**
	 * Método que cierra el fichero del que se ha leído.
	 */
	public void TerminarRead()
	{
		try {
			ois.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String[][] LeerMapa(int fich) throws IOException, FileNotFoundException
	{
		final int tamx=20;
		final int tamy=16;
		boolean comprobacion=true;
		String[][] mapa;
		String text;
		FileReader fr;
		BufferedReader br = null;
		File fic = null;
		
		mapa= new String[tamx][tamy];
		String ruta=this.setCuadrante(fich);
		fic= new File(ruta);
		
		try
		{ 
			fr= new FileReader(ruta);
			br= new BufferedReader(fr);
			
			for(int i=0 ; i<tamy ; i++)
			{
				text=br.readLine();
				
				if(text != null && !text.isEmpty())
				{
					comprobacion=false;
					clsArrayC.ConvertirFormato(mapa, text, i);
				}
				if(comprobacion==true)
				{
					mapa=null;
				}
			}
		}
		catch(IOException f)
		{
			fic.createNewFile();
			throw f;
		}
	
		br.close();
		return mapa;
	}
	public void ResetFile (enumFicheros fichero)
	{

	}
	
}