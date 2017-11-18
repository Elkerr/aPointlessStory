package LP;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static Comun.clsConstantes.*;

public class PJPrincipal extends JLabel
{
	private static final long serialVersionUID = 1L;
	private boolean movimiento; 
	private int ContadorPos=0;
	private String MovSITUACION;
	private String IntentoMovSITUACION;
	private ImageIcon a,b;
	private Icon icono;
	public PJPrincipal() 
	{
		b=new ImageIcon();
		movimiento=false;
		MovSITUACION="";
		IntentoMovSITUACION="STOP";
		setSize(100,100);
		setLocation(400,175);
		a=new ImageIcon(".\\resources\\PPrincipal\\MainF1.png"); 	
	}

	
	public String getIntentoMovSITUACION() {
		return IntentoMovSITUACION;
	}


	public void setIntentoMovSITUACION(String intentoMovSITUACION) {
		IntentoMovSITUACION = intentoMovSITUACION;
	}


	public boolean isMovimiento() {
		return movimiento;
	}


	public void setMovimiento(boolean movimiento) {
		this.movimiento = movimiento;
	}


	public String getMovSITUACION() {
		return MovSITUACION;
	}


	public void setMovSITUACION(String movSITUACION) {
		MovSITUACION = movSITUACION;
	}


	public int getContadorPos() {
		return ContadorPos;
	}


	public void setContadorPos(int contadorPos) {
		ContadorPos = contadorPos;
	}


	public void paintComponent(Graphics g)
	{
		icono = new ImageIcon(a.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		setIcon(icono);
        super.paintComponent(g);

	}
	
	public void setA(ImageIcon m)
	{
		a=m;
		
	}
	public void IconUp()
	{
		if((b=new ImageIcon(U1)).equals(a)==false)a=b;
	}
	public void IconDown()
	{
		if(ContadorPos<280)
		{
			if((b=new ImageIcon(D1)).equals(a)==false)a=b;
			ContadorPos++;
		}
		else if(ContadorPos<300)
		{
			if((b=new ImageIcon(D2)).equals(a)==false)a=b;
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;
		}	

	}
	public void IconRight()
	{
		if(ContadorPos<90)
		{
			if((b=new ImageIcon(R1)).equals(a)==false)a=b;
		}
		else if(ContadorPos<120)
		{
			if((b=new ImageIcon(R2)).equals(a)==false)a=b;
		}
		
		if(ContadorPos<120)
		{
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;	
		}
		
		
	}
	public void IconLeft()
	
	{
		if(ContadorPos<90)
		{
			if((b=new ImageIcon(L1)).equals(a)==false)a=b;
		}
		else if(ContadorPos<120)
		{
			if((b=new ImageIcon(L2)).equals(a)==false)a=b;
		}
		if(ContadorPos<120)
		{
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;	
		}
	}
}
