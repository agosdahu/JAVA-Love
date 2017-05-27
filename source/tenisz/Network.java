package tenisz;

import tenisz.Control;

public abstract class Network {
	
	protected Control ctrl;
	
	Network(Control c) {
		ctrl = c;
	}
		
	abstract void connect(String host);
	
	abstract void disconnect();
		
	abstract public void sendRacketPos(int pos);
	
	abstract public int getRacketPos();
	
	abstract public void sendData(int ball_x, int ball_y, int player1Score, int player2Score);
	
	abstract public int getX();
	
	abstract public int getY();
	
	abstract public int getScore1();
	
	abstract public int getScore2();

	public void sendData() {
		// TODO Auto-generated method stub
		
	}
}