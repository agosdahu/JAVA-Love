package tenisz;

public abstract class Network {
	

	abstract void connect(String ipAddress, int port);
	abstract void disconnect();
	
	public void sendRacketPos(int y) {
		// TODO Auto-generated method stub
		
	}
	public int getRacketPos() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void sendData(int x, int y, int player1Score, int player2Score) {
		// TODO Auto-generated method stub
		
	}
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getScore1() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getScore2() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	




}
