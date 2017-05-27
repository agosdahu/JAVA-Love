package tenisz;

public class MySocket {
	private int bx;
	private int by ;
	private int rPosC;
	private int rPosS;
	private int playersc1;
	private int playersc2;
	
	Player playerC = new Player();
	Player playerS = new Player();
	
	public MySocket (Control control){
		initialise (control);
	}
	
	public void initialise (Control C){
		setBallX(C);
		setBallY(C);
		
	}
	
	
	public void setBallX(Control c) {
		this.bx = c.getBall().getX();
	}
	
	public int getBallX (){
		return this.bx;
	}
	
	public void setBallY(Control c){
		this.by = c.getBall().getY();
	}
	
	public int getBallY(){
		return this.by;
	}
	
	public void setRposC(int posC){
		this.rPosC = posC;
	}
	
	public int getRposC(){
		return this.rPosC;
	}
	
	public void setRposS(int posS){
		this.rPosS = posS;
	}
	
	public int getRposS(){
		return this.rPosS;
	}
	
	public void setP1score(int p1){
		this.playersc1 = p1;
	}
	
	public int getP1score(){
		return this.playersc1;
	}
	
	public void setP2score(int p2){
		this.playersc2 = p2;
	}
	
	public int getP2score(){
		return this.playersc2;
	}

}
