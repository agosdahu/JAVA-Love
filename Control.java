package tenisz;

class Control {
	
	private GUI gui = new GUI();
	private Network net = null;
	private DB db;
	private String ipAddress;
	private int port;
	
	private Player player1;
	private boolean player1Up = false;
	private boolean player1Down = false;
	
	private Player player2;
	
	private Ball ball;
	private boolean ballUp = true;	// true: UP direction, false: DOWN direction
	private boolean ballRight = true; //true: RIGHT direction, false: LEFT direction
	
	private Score score;

	

	Control() {
		// showMenu();
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
		ipAddress = gui.getIPaddress();
		port = gui.getPort();
		net.connect(ipAddress, port);
		player1.setType("Server");
	}
	
		public void startClient() {
		if (net != null)
			net.disconnect();
		net = new Client(this);
		ipAddress = gui.getIPaddress();
		port = gui.getPort();
		net.connect(ipAddress, port);
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
	

	public void loadGame(){
		int record = 0;
		int compare = player1.getName().compareToIgnoreCase(player2.getName());
		try {
			record = db.searchRecord(player1.getName(), player2.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (record == 0)
			startGame();
		else{
			try {
				if(compare <=0){
					score.setCurrentScore(db.getPlayer1Score(record), db.getPlayer2Score(record));
				}else{
					score.setCurrentScore(db.getPlayer2Score(record), db.getPlayer1Score(record));
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startNewSet();
		}
	}
	
	public void saveGame(int player1Score, int player2Score){
		
		try {
			db.save(player1.getName(), player2.getName(), player1Score, player2Score);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private void startNewSet() {
		if(player1.getType() == "Server"){
			player1.setX(30);
			player1.setY(175);
			player2.setX(550);
			player2.setY(175);
		}
		else{
			player1.setX(550);
			player1.setY(175);
			player2.setX(30);
			player2.setY(175);
		}
					
		ball.setX(300);
		ball.setY(175);
		ball.setSpeed(ball.getNormalSpeed());
	}
	
	public void startSet() throws Exception{
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
		if(player1Score == score.getScore() || player2Score == score.getScore()){
			saveGame(0, 0);
			gui.showResult();
		}
	}
	
	private void ballPos(int currentX, int currentY){
		
		if(isGoal()){
			startNewSet();
		}
		
		//x-direction motion
		if(ballRight && hitRacket(true)){ //hits the right racket
			ball.setSpeed(ball.getBendSpeed());
			ball.setX(ball.getX() - ball.getSpeed());
			ballRight = false;
		}
		else if(ballRight && !hitRacket(true)){ //if moving right and not at max
			ball.setX(ball.getX() + ball.getSpeed());
		}
		
		else if(!ballRight && hitRacket(false)){ //hits the left racket
			ball.setSpeed(ball.getBendSpeed());
			ball.setX(ball.getX() + ball.getSpeed());
			ballRight = true;
		}
		else if(!ballRight && !hitRacket(false)){ //if moving left, but not at max
			ball.setX(ball.getX() - ball.getSpeed());
		}
						
		//y-direction motion
		if(ballUp && !hitWall(true)){ //if moving up and not at max
			ball.setY(ball.getY() - ball.getSpeed());
		}
		else if(ballUp && hitWall(true)){ //if moving up, but at max
			ball.setSpeed(ball.getNormalSpeed());
			ball.setY(ball.getY() + ball.getSpeed());
			ballUp = false;
		}
		else if(!ballUp && !hitWall(false)){ //if moving down, but not at max
			ball.setY(ball.getY() + ball.getSpeed());
		}
		else if(!ballUp && hitWall(false)){ //if moving down, but at max
			ball.setSpeed(ball.getNormalSpeed());
			ball.setY(ball.getY() - ball.getSpeed());
			ballUp = true;
		}
	}
	
	private boolean hitWall(boolean up){
		if(up){
			if(ball.getY() <= 0)
				return true;
			else 
				return false;
		}
		else{
			if(ball.getY() + ball.getHeight() >= gui.getHeight())
				return true;
			else
				return false;
		}
	}
	
	private boolean hitRacket(boolean right){
		if(right){
			if(ball.getX() >= player2.getX() && ball.getX() < (player2.getX() + player2.getWidth()) && ball.getY() >= player2.getY() && ball.getY() <= player2.getY() + player2.getHeight()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(ball.getX() <= player1.getX() && ball.getX() > (player1.getX() - player1.getWidth()) && ball.getY() >= player1.getY() && ball.getY() <= player1.getY() + player1.getHeight()){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	private void racketPos(int currentX, int currentY){
		if(player1Up)
			player1.setY(player1.getY() + player1.getSpeed());
		else if(player1Down)
			player1.setY(player1.getY() - player1.getSpeed());
		else 
			player1.setY(player1.getY());
		net.sendRacketPos(player1.getY());
		player2.setY(net.getRacketPos());
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
