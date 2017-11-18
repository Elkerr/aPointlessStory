package LP;

public class User 
{
	private String nick;
	private int money;
	private int lvl;
	private int xp;
	private PJPrincipal pjLinked;

	public User(String nomb) 
	{
		nick=nomb;
		money=0;
		lvl=1;
		xp=0;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getNick() {
		return nick;
	}

	public int getMoney() {
		return money;
	}

	public int getLvl() {
		return lvl;
	}

	public PJPrincipal getPjLinked() {
		return pjLinked;
	}
	
	

	
	
	
}
