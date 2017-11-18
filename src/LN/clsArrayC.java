package LN;
import java.io.IOException;

import javax.swing.*;

import LP.PiezaMapa;

/** Clase encargada de transformar el array de chars o ints en una list de Jlabels que contengan todos los atributos del laberinto*/
public class clsArrayC
{
	/**Clase encragada de crear y convertir un array de dos dimensiones de JLabels y rellenarlo con la informacion adecuada para crear el
     * mapa del juego */
	public static PiezaMapa[][] ConversorJLabel(String[][] aMapaPrevio)
	{
		final int tamx=20;
		final int tamy=15;
		PiezaMapa[][] aMapa= new PiezaMapa[20][15];

		for(int y=0; tamy>y;y++)
		{
			for(int x=0; tamx>x;x++)
			{
				switch(aMapaPrevio[x][y])
				{
					//Grass
					
				case "G1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\GrassNF.png"));
					break;
				case "G2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Grass1.png"));
					break;
				case "G3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\NOFGrass1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
					//Tree
				case "T":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\OakTree1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "T1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TreeBL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "T2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TreeBR1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "T3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TreeTL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "T4":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TreeTR1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
					//bush
				case "BL":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\BushL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "BR":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\BushR1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
					
					//Montaña
					
				case "MM":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\SMount1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "M1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\SMount1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
					
					//agua
				case "W0":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Water1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandBR2.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandTL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandTopH1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W4":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandTR.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W5":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandVLeft1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W6":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandVRight1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W7":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandBL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "W8":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WaterLandBottH1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
					
					//fuente 
					
				case "F1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\FountainDL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;

				case "F2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\FountainDR1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "F3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\FountainTL1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "F4":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\FountainTR1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				
					//camino tierra
					
				case "R1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Road_DWPR1.png"));
					break;
				case "R2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Road_UPWL1.png"));
					break;
				case "R3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\RoadH1.png"));
					break;
				case "R4":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Road_DWL1.png"));
					break;
				case "R5":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\RoadV1.png"));
					break;
				case "R6":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Road_UPR1.png"));
					break;
					
					//camino piedra
					
				case "S1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\RoadH1 .png"));
					break;
				case "S2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\RoadV2.png"));
					break;
				case "S3":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TurnBL1.png"));
					break;
				case "S4":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TurnBR1.png"));
					break;
				case "S5":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TurnTL1.png"));
					break;
				case "S6":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\TurnTR1.png"));
					break;
					
					//Ciudad
			
				case "B1":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\BrickW1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "B2":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\BrickW2.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "TB":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Table1.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				case "WF":
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\WoodFHor1.png"));
					break;

					
					
					
				default:
					aMapa[x][y]=new PiezaMapa(new ImageIcon(".\\resources\\Error.png"));
					aMapa[x][y].setMovilidad(false);
					break;
				}
					aMapa[x][y].setLocation((x*50), (y*50));
				
			}
		}
		return aMapa;

	}
	/**Clase encargada de convertir el formato mediante el que se guarda en el .txt en uno el cual java reconozca un array 
	 * @throws IOException */
	public static void ConvertirFormato (String[][]mapa, String text, int fila) throws IOException
	{

		String[] LineaPrevio;
		LineaPrevio= text.split(",");
		if(LineaPrevio.length<19)
		{
			mapa[0][fila]=LineaPrevio[0];
			mapa[1][fila]=LineaPrevio[1];
			return;
		}
		for(int x=0; x<mapa.length;x++)
		{
			mapa[x][fila]=LineaPrevio[x];
		}

	}
}
