package tenisz;

import java.io.Serializable;

public class DataFromServer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int posY;
	public int ballPosX;
	public int ballPosY;
	public int serverScore;
	public int clientScore;
	public boolean serverUp;
	public boolean serverDown;
	
	public int test1;
	public int test2;
	
	transient Control c;

	
	public DataFromServer(){
		this.posY = 0;
		this.ballPosX = 0;
		this.ballPosY = 0;
		this.serverScore = 0;
		this.clientScore = 0;

	}
	
	public DataFromServer (Control control){
		c = control;
		updateData ();
	}

	public void updateData() {
		this.posY = c.getPlayer1().getY();
		this.serverUp = c.getplayer1Up();
		this.serverDown = c.getplayer1Down();
		this.ballPosX = c.getBall().getX();
		this.ballPosY = c.getBall().getY();
		
	}
	
	
}
