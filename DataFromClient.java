package tenisz;

import java.io.Serializable;

public class DataFromClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int posY;
	public boolean player1Up;
	public boolean player1Down;
	
	public DataFromClient(){
		this.posY = 0;
		this.player1Up = false;
		this.player1Down = false;
	}

	public DataFromClient(int racketPosY, boolean player1Up, boolean player1Down) {
		this.posY = racketPosY;
		this.player1Up = player1Up;
		this.player1Down = player1Down;
		
	}
	
}