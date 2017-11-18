package LP;

import javax.swing.ImageIcon;

public class Soldier extends PiezaMapa 
{
	
	private static final long serialVersionUID = 1L;
	private static ImageIcon p;
	public Soldier() 
	{
		super(p);
		p=new ImageIcon("resources/Soldier1.png") ;
		setIcon(p);
		setLocation(0,250);
		setSize(50,55);

	}

}
