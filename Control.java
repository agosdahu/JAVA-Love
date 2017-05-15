package tenisz;

class Control {
	
	private GUI gui;
	private Network net = null;
	private Player player1;
	private Player player2;
	private Ball ball;
	private Score score;

	

	Control() {
		showMenu();
	}
	
	public void selectOptions(){
		gui.showOptions();
	}
	
	public void setName(String newName){
		player1.setName(newName);
	}
	
	public void setScore(int newScore){
		score.setScore(newScore);
	}
	
	public void showMenu(){
		gui.showMenu();
	}
	
	public void startServer() {
		if (net != null)
			net.disconnect();
		net = new Server(this);
		net.connect("localhost");
		player1.setType("Server");
	}
	
		public void startClient() {
		if (net != null)
			net.disconnect();
		net = new Client(this);
		net.connect("localhost");
		player1.setType("Client");
	}
	
	public void joinSuccesfull(Player player){
		player2 = player;
		gui.showGameField();
	}

	public void startGame(){
		score.setCurrentScore(0, 0);
		this.startNewSet();
	}
	
	/*
	public void loadGame(){
		int player1Score;
		int player2Score;
		score.setCurrentScore(player1Score, player2Score);
		this.startNewSet();
	}
	*/
		
	private void startNewSet() {
		player1.setX(30);
		player1.setY(175);
		player2.setX(550);
		player2.setY(175);
		ball.setX(30+player1.getWidth()/2);
		ball.setY(175);
	}
	
	public void startSet(){
		int player1Score = score.getCurrentScore()[1];
		int player2Score = score.getCurrentScore()[2];
		
		while(player1Score != score.getScore() || player2Score != score.getScore()){
			if(player1.getType() == "Server"){
				ballPos(ball.getX(), ball.getY());
				racketPos(player1.getX(), player1.getY());
				updateScore(player1Score, player2Score);
				net.sendData(ball.getX(), ball.getY(), player1Score, player2Score);
			}
			if(player1.getType() == "Client"){
				racketPos(player1.getX(), player1.getY());
				ball.setX(net.getData()[0]);
				ball.setY(net.getData()[1]);
				score.setCurrentScore(net.getData()[3], net.getData()[2]);
				}
		}
	}
	
	private void ballPos(int currentX, int currentY){
		int newX;
		int newY;
		
		ball.setX(newX);
		ball.setY(newY);
	}
	
	private boolean hitWall(){
		
		return false;
	}
	
	private void wallReflection(){
		
	}
	
	private boolean hitRacket(){
		
		return false;
	}
	
	private boolean fixRacket(){
		
		return false;
	}
	
	private boolean moveRacket(){
		
		return false;
	}
	
	private void racketReflection(){
		
	}
	
	private void racketBend(){
		
	}
	
	private void racketPos(int currentX, int currentY){
		net.sendRacketPos(player1.getX(), player1.getY());
		player2.setX(net.getRacketPosX());
		player2.setY(net.getRacketPosY());
	}
	
	public void racketPosUP(){
		int newPosY = player1.getY() + player1.getSpeed();
		player1.setX(player1.getX());
		if((newPosY + player1.getHeight()/2) >= 350)
			player1.setY(325);
		else
			player1.setY(newPosY);
	}
	
	public void racketPosDOWN(){
		int newPosY = player1.getY() - player1.getSpeed();
		player1.setX(player1.getX());
		if((newPosY - player1.getHeight()/2) <= 0)
			player1.setY(25);
		else
			player1.setY(newPosY);
	}
	
	private void updateScore(int player1Score, int player2Score) {
			if(isGoal()){
				if(ball.getX() < 100)
					score.setCurrentScore(player1Score++, player2Score);
				else
					score.setCurrentScore(player1Score, player2Score++);
			}
	}

	private boolean isGoal() {
		if((ball.getX() < player1.getX()) || (ball.getX() > player2.getX()))
			return true;
		else
			return false;
	}
	
	
}
