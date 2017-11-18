package LP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Comun.clsConstantes;
import LD.clsLectura;

public class PantallaPrincipal extends JFrame {
	
private static final long serialVersionUID = 1L;

private static PantallaPrincipal MiPantalla;
private JPanel PPrincipal;
private KeyEvent event;
private ArrayList<Cuadrante> Cuad;
private final int MaxCuad=3;
private String[][] map;
private int ALTO_PANT=639, ANCHO_PANT=816, cont=0,s=10;
private boolean Funciona, Funciona2, posibilidad=false, bol=false;
private MiHilo ElHilo;
private MovMapa OtroHilo;
private HiloMusica Musica;
private Cuadrante cuadran;
private PJPrincipal pj;
private static Thread HiloCoche;
private static Thread HiloMapa;
private static Thread HiloMusica;
private Point CuadActual=new Point(0,0);
private Point source=new Point(8,4);
private MouseListener BotonPlayOpt;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MiPantalla = new PantallaPrincipal();
					MiPantalla.setVisible(true);
					MiPantalla.requestFocus();
					
					MiPantalla.ElHilo=MiPantalla.new MiHilo();
					MiPantalla.OtroHilo=MiPantalla.new MovMapa();
					MiPantalla.Musica=MiPantalla.new HiloMusica();
					
					HiloCoche=new Thread(MiPantalla.ElHilo);
					HiloMapa=new Thread(MiPantalla.OtroHilo);
					HiloMusica=new Thread(MiPantalla.Musica);
					
					HiloMusica.start();
				}catch (Exception e) 
				{
					e.printStackTrace();	
				}
			}
		});
	}

	
	public PantallaPrincipal() throws IOException 
	{
		
		setSize(ANCHO_PANT, ALTO_PANT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		PPrincipal=new JPanel();
		PPrincipal.setLayout(null);
		
		setContentPane(PPrincipal);

		JLabel Portada1 = new JLabel();
		PPrincipal.add(Portada1);
		
		Portada1.setBounds(0, 0, 800, 600);
		Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Portada.png"));
		
		BotonPlayOpt = new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Portada.png"));
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int x=e.getX();
			    int y=e.getY();
			
				if (x>258 && x<577 && y>260 && y<329)
				{
					bol=true;
					Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Play.png"));
					
				}
				else if (x>258 && x<577 && y>360 && y<430)
				{
					Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Click.png"));
				}
				
			}
		
			public void mouseClicked(MouseEvent e) 
			{
				if (bol==true)
				{
					PPrincipal.removeAll();
					PPrincipal.removeMouseListener(BotonPlayOpt);
					CargaJuego();
					PPrincipal.repaint();
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		
		this.addMouseListener(BotonPlayOpt);
		
	}
	
	public void CargaJuego()
	{
	
		pj=new PJPrincipal();
		PPrincipal.add(pj);
		PPrincipal.setComponentZOrder(pj, 0);
		
		Cuad=new ArrayList<Cuadrante>();
		cargadelmapa();
		
		addKeyListener(new KeyListener()
				{
					@Override
					public void keyPressed(KeyEvent e)
					{
						event=null;
						posibilidad=false;
						
						if(cont==1)event=e;
						
						if(Cuadrante.isMovimiento()==false)
						{
							pj.setMovimiento(true);
							cont=1;
							
							if(KeyEvent.VK_RIGHT==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Derecha);
								Cuadrante.setMovimiento(true);
							}

							if(KeyEvent.VK_DOWN==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Abajo);
								Cuadrante.setMovimiento(true);
								
							}

							if(KeyEvent.VK_LEFT==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Izquierda);
								Cuadrante.setMovimiento(true);
							}

							if(KeyEvent.VK_UP==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Arriba);
								Cuadrante.setMovimiento(true);
							}
						}
					}
					@Override
					public void keyReleased(KeyEvent arg0) 
					{
						posibilidad=true;
					}

					@Override
					public void keyTyped(KeyEvent arg0) {}
			
				});
		
		HiloCoche.start();
		HiloMapa.start();
	}
	
	
	
	private class MiHilo implements Runnable
	{

		@Override
		public void run()
		{	
			Funciona2=true;
			pj.setMovimiento(true);
			s=10;
			
			while(Funciona2)
			{
				if(pj.isMovimiento()==true)
				{
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)	
					{
						pj.IconLeft();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
					{
						pj.IconUp();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
					{
						pj.IconRight();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
					{
						pj.IconDown();
					}
				}
				try 
				{
					Thread.sleep(s);
				}catch (Exception e) {}
			}
		}
	}

	private class MovMapa implements Runnable
	{
		
		@Override
		public void run()
		{	
			Funciona=true;
			SeleccionC();
			
			while(Funciona)
			{
				if(Cuadrante.isMovimiento()==true)
				{
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)
					{
						comprobacion();
						
					
						if(cuadran.getPiezas()[source.x-1][source.y].isMovilidad())
						{
							source.setLocation(source.x-1, source.y);
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveRight();
								}
								

								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								pj.setMovimiento(false);
								cont=0;
								pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
								}
							
							}
							
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.x==20)
							{
								source.x=0;
								CuadActual.y=CuadActual.y+1;
								SeleccionC();
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
							pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
					{
						comprobacion();
						if(cuadran.getPiezas()[source.x+1][source.y].isMovilidad())
						{
							source.setLocation(source.x+1, source.y);
					
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveLeft();
								}
								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								pj.setMovimiento(false);
								cont=0;
								pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.x==-1)
							{
								source.x=19;
								CuadActual.y=CuadActual.y-1;
								SeleccionC();
								
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
							pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
					{
						comprobacion();
						
						if(cuadran.getPiezas()[source.x][source.y-1].isMovilidad())
						{
							source.setLocation(source.x, source.y-1);
					
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveDown();
								}
								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
									Cuadrante.setMovimiento(false);
									cont=0;
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.y==15)
							{
								source.y=0;
								CuadActual.x=CuadActual.x+1;
								SeleccionC();
							}
							Cuadrante.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
						}	
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
					{
						comprobacion();
						if(cuadran.getPiezas()[source.x][source.y+1].isMovilidad())
						{
							source.setLocation(source.x, source.y+1);
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveUp();
								}
								if(event!=null)IntentoMovimiento();
						
								try 
								{
									Thread.sleep(6);
								}
								
								catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								cont=0;
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.y==-1)
							{
								source.y=14;
								CuadActual.x=CuadActual.x-1;
								SeleccionC();
							}
							
							Cuadrante.setMovimiento(false);
							cont=0;
						}
					}
				}
				try 
				{
					Thread.sleep(8);
				}catch (Exception e) {}
				MiPantalla.requestFocus();
			}
		}
	}
	
	public class HiloMusica implements Runnable {
		@Override
		public void run() {
			while(true)
			{
				try 
				{
					 	AudioInputStream audioInputStream = null;
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File(".\\resources\\Musica\\Musica1.wav"));
						} catch (UnsupportedAudioFileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        Clip clip = null;
						try {
							clip = AudioSystem.getClip();
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        try {
							clip.open(audioInputStream);
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        clip.start();
				       clip.loop(clip.LOOP_CONTINUOUSLY);
					Thread.sleep(100000);
				} catch (Exception e) {}
			}

		}
	};
	
	private void cargadelmapa()
	{
		clsLectura a=new clsLectura();
		
		for(int NumCuadr=0;NumCuadr<=MaxCuad;NumCuadr++)
		{
			try 
			{
				map=a.LeerMapa(NumCuadr);
			} 	
			catch (IOException d){} 
			
			Cuadrante j=new Cuadrante(map);
			j.addpiezas();
			Cuad.add(j);
		}
		
		for(Cuadrante z:Cuad)
		{
			PPrincipal.add(z);
		}

	}
	private void IntentoMovimiento()
	{
		if(KeyEvent.VK_RIGHT==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Derecha);
		}

		if(KeyEvent.VK_DOWN==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Abajo);
		}

		if(KeyEvent.VK_LEFT==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Izquierda);
		}

		if(KeyEvent.VK_UP==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Arriba);
		}
	}
	private void comprobacion()
	{
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)
		{
			if(source.x-1==-1)
			{
				source.x=20;
				CuadActual.y=CuadActual.y-1;
				SeleccionC();
			}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
		{
		if(source.x+1==20)
		{
			source.x=-1;
			CuadActual.y=CuadActual.y+1;
			SeleccionC();
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
		{
		if(source.y+1==15)
		{
			source.y=-1;
			CuadActual.x=CuadActual.x+1;
			SeleccionC();
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
		{
		if(source.y-1==-1)
		{
			source.y=15;
			CuadActual.x=CuadActual.x-1;
			SeleccionC();
		}
		}
	}
	private void SeleccionC()
	{
		for(Cuadrante cc:Cuad)
		{
			if(CuadActual.x==cc.getNumCuadrante().x&&CuadActual.y==cc.getNumCuadrante().y)
			{
				cuadran=cc;
				return;
			}
		}
	}
	

}
