package LP;

import java.awt.Point;

import javax.swing.JPanel;

import LN.clsArrayC;

public class Cuadrante extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static boolean movimiento;
	private Point numCuadrante;
	private PiezaMapa[][] Piezas;
	private Point pActual;
	
	public Cuadrante(String[][] a)
	{
		setLayout(null);
		movimiento=false;
		Piezas=clsArrayC.ConversorJLabel(a);
		
		numCuadrante=new Point(Integer.parseInt(a[0][15]),Integer.parseInt(a[1][15]));
		setBounds(1000*numCuadrante.x,750*numCuadrante.y,1000,750);
		
	}
	public void addpiezas()
	{
		for(int c=0;c<15;c++)
		{
			for(int d=0;d<20;d++)
			{
				this.add(Piezas[d][c]);
			}
		}
	}
	
	public Point getNumCuadrante() 
	{
		return numCuadrante;
	}
	public void setNumCuadrante(Point numCuadrante) 
	{
		this.numCuadrante = numCuadrante;
	}
	public PiezaMapa[][] getPiezas() 
	{
		return Piezas;
	}
	public void setPiezas(PiezaMapa[][] piezas) 
	{
		Piezas = piezas;
	}

	public static boolean isMovimiento() 
	{
		return movimiento;
	}

	public static void setMovimiento(boolean movimiento) 
	{
		Cuadrante.movimiento = movimiento;
	}
	public Point getpInit() 
	{
		return pActual;
	}

	public void setpInit(Point pInit) 
	{
		this.pActual = pInit;
	}

	public void moveUp()
	{
		pActual=this.getLocation();

		pActual=new Point((pActual.x),(pActual.y-1));
		this.setLocation(pActual);
		
	}
	
	public void moveDown()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x),(pActual.y+1));
		this.setLocation(pActual);
			

	}
	public void moveRight()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x+1),(pActual.y));
		this.setLocation(pActual);
		
	}
	public void moveLeft()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x-1),(pActual.y));
		this.setLocation(pActual);
	}
	
	

}
