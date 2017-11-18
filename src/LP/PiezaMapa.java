package LP;



import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class PiezaMapa extends JLabel
{
	private static final long serialVersionUID = 1L;
	private ImageIcon fot;
	private boolean movilidad;

	public PiezaMapa(ImageIcon c) 
	{
		setSize(50, 50);
		fot = c;
		setIcon(fot);
		movilidad=true;
	}

	public boolean isMovilidad() {
		return movilidad;
	}

	public void setMovilidad(boolean b) {
		this.movilidad=b;
		
	}
	
	
}
