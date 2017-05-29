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
	public int player1Score;
	public int player2Score;

	
	public DataFromServer(){
		this.posY = 0;
		this.ballPosX = 0;
		this.ballPosY = 0;
		this.player1Score = 0;
		this.player2Score = 0;

	}

	public DataFromServer(int racketPosY, int ballPosX, int ballPosY, int player1Score, int player2Score) {
		this.posY = racketPosY;
		this.ballPosX = ballPosX;
		this.ballPosY = ballPosY;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		
	}
	
}
