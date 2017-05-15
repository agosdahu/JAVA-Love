package tenisz;

public abstract class Network {
	

	abstract void connect(String string);
	abstract void disconnect();
	
	public void sendRacketPos(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public int getRacketPosX() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getRacketPosY() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void sendData(int x, int y, int player1Score, int player2Score) {
		// TODO Auto-generated method stub
		
	}
	public int[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
	




}
