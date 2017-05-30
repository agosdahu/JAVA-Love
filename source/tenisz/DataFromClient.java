package tenisz;

import java.io.Serializable;

public class DataFromClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int posY;
	public boolean ClientUp;
	public boolean ClientDown;
	
	public int test1;
	public int test2;
	public boolean handshake;
	
	transient Control c;
	
	public DataFromClient (Control control){
		c = control;
		updateData ();
	}
	
	public void updateData() {
		this.posY = c.getPlayer1().getY();
		this.ClientUp = c.getplayer1Up();
		this.ClientDown = c.getplayer1Down();
		
	}
}