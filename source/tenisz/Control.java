package tenisz;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

class Control {

    private GUI gui = new GUI();
    private Network net = null;
    private DB db = new DB();

    private Player player1 = new Player("Player 1", 0, 0, 5);
    private boolean player1Up = false;
    private boolean player1Down = false;

    private Player player2 = new Player("Player 2", 0, 0, 5);
    private boolean player2Up = false;
    private boolean player2Down = false;

    private Ball ball = new Ball(0, 0, 5, 5, 10);
    private boolean ballUp = true;    // true: UP direction, false: DOWN direction
    private boolean ballRight = false; //true: RIGHT direction, false: LEFT direction

    private Score score = new Score(3, 0, 0);

    public String target = "localhost";

    public Control() {
        showMenu();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public boolean getplayer1Up() {
        return player1Up;
    }

    public void setplayer1Up(boolean up) {
        this.player1Up = up;
    }

    public boolean getplayer1Down() {
        return player1Down;
    }

    public void setplayer1Down(boolean down) {
        this.player1Down = down;
    }

    public boolean getplayer2Up() {
        return player2Up;
    }

    public void setplayer2Up(boolean up) {
        this.player2Up = up;
    }

    public boolean getplayer2Down() {
        return player2Down;
    }

    public void setplayer2Down(boolean down) {
        this.player2Down = down;
    }

    public void selectOptions() {
        gui.showOptions(this);
    }

    public void setName(String newName) {
        player1.setName(newName);
    }

    public void setScore(int newScore) {
        score.setScore(newScore);
    }

    public void showMenu() {
		if (net != null) net.disconnect();
		score.setCurrentScorePlayer1(0);
		score.setCurrentScorePlayer2(0);
        gui.showMenu(this);
    }

    public void startServer() {
        if (net != null) net.disconnect();
        player1.setType("Server");
        player2.setType("Client");
        System.out.println(player1.getType());
        System.out.println(player2.getType());

        net = new Server(this);
        net.connect("localhost");

    }

    public void startClient() {
        if (net != null) net.disconnect();
        player1.setType("Client");
        player2.setType("Server");
        System.out.println(player1.getType());
        System.out.println(player2.getType());

        net = new Client(this);
        net.connect(target);

    }

    public void newGame() {
        gui.showGameField(this);
    }

    public void joinSuccesfull() {

        //gui.showGameField(this);
        newGame();
    }

    public void startGame() {
        score.setScore(score.getScore());
        System.out.println("Set start game config");
        score.setCurrentScorePlayer1(0);
        score.setCurrentScorePlayer2(0);
        try {
            startNewSet();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public void loadGame() {
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
        else {
            try {
                if (compare <= 0) {
                    score.setCurrentScorePlayer1(db.getPlayer1Score(record));
                    score.setCurrentScorePlayer2(db.getPlayer2Score(record));
                } else {
                    score.setCurrentScorePlayer1(db.getPlayer2Score(record));
                    score.setCurrentScorePlayer2(db.getPlayer1Score(record));
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            score.setScore(score.getScore());
            try {
                startNewSet();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void saveGame(int player1Score, int player2Score) {

        try {
            db.save(player1.getName(), player2.getName(), player1Score, player2Score);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startNewSet() {
        if (player1.getType() == "Server") {
            player1.setX(0);
            player1.setY(175);
            player2.setX(595);
            player2.setY(175);
        } else {
            player1.setX(595);
            player1.setY(175);
            player2.setX(0);
            player2.setY(175);
        }

        ball.setX(300);
        ball.setY(175);
        ball.setSpeed(ball.getNormalSpeed());

        try {
            startSet();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startSet() throws Exception {
        //startNewSet();								/*!!!!!!*/
        gui.refreshgui(this);
        //System.out.println("GUI is refreshed");
                /*!!!!!!*/
        Control c = this;
        SwingWorker myWorker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                while (score.getCurrentScorePlayer1() < score.getScore() && score.getCurrentScorePlayer2() < score.getScore()) {
                    TimeUnit.MILLISECONDS.sleep(30);
                    //System.out.println("GUI is refreshed");


                    if (player1.getType() == "Server") {

                        net.updateGame();

                        ballPos(ball.getX(), ball.getY());

                        racketPos(player1.getY(), 200);

                        //updateScore();

				/*!!!!!!*/
                        gui.refreshgui(c);
                    }
                    if (player1.getType() == "Client") {

                        net.updateGame();

                        racketPos(player1.getX(), player1.getY());


                        gui.refreshgui(c);
                    }


                    if (score.getCurrentScorePlayer1() >= score.getScore() || score.getCurrentScorePlayer2() >= score.getScore()) {
                        //if(player1.getType()=="Server")
                        //saveGame(0, 0);
                        gui.showResult(c);
                        break;
                    }

					if (isGoal()) {
						startNewSet();
						if (score.getCurrentScorePlayer1() >= score.getScore() || score.getCurrentScorePlayer2() >= score.getScore()) {
							//if(player1.getType()=="Server")
							//saveGame(0, 0);
							gui.showResult(c);
						}
						break;
					}
                }
                return null;
            }
        };
        myWorker.execute();

    }

    private void ballPos(int currentX, int currentY) {

        //x-direction motion
        if (ballRight && hitRacket(true)) { //hits the right racket
            if (getplayer1Up() || getplayer1Down() || player2Up || player2Down) {
                ball.setSpeed(ball.getBendSpeed());
            }

            ball.setX(ball.getX() - ball.getSpeed());
            ballRight = false;
        } else if (ballRight && !hitRacket(true)) { //if moving right and not at max
            ball.setX(ball.getX() + ball.getSpeed());
        } else if (!ballRight && hitRacket(false)) {    //hits the left racket
            if (getplayer1Up() || getplayer1Down()) {
                ball.setSpeed(ball.getBendSpeed());
            }
            ball.setX(ball.getX() + ball.getSpeed());
            ballRight = true;
        } else if (!ballRight && !hitRacket(false)) { //if moving left, but not at max
            ball.setX(ball.getX() - ball.getSpeed());
        }

        //y-direction motion
        if (ballUp && ball.getY() > 0) { //if moving up and not at max
            ball.setY(ball.getY() - ball.getSpeed());
        } else if (ballUp && ball.getY() <= 0) { //if moving up, but at max
            ball.setSpeed(ball.getNormalSpeed());
            ball.setY(ball.getY() + ball.getSpeed());
            ballUp = false;
        } else if (!ballUp && ball.getY() < (400 - ball.getHeight())) { //if moving down, but not at max
            ball.setY(ball.getY() + ball.getSpeed());
        } else if (!ballUp && ball.getY() >= (400 - ball.getHeight())) { //if moving down, but at max
            ball.setSpeed(ball.getNormalSpeed());
            ball.setY(ball.getY() - ball.getSpeed());
            ballUp = true;
        }
    }

    private boolean hitRacket(boolean right) {
        if (right) {
            if (ball.getX() >= player2.getX() && ball.getX() < (player2.getX() + player2.getWidth()) && ball.getY() >= player2.getY() && ball.getY() <= player2.getY() + player2.getHeight()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (ball.getX() <= player1.getX() && ball.getX() > (player1.getX() - player1.getWidth()) && ball.getY() >= player1.getY() && ball.getY() <= player1.getY() + player1.getHeight()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void racketPos(int currentX, int currentY) {
        if (player1Up && player1.getY() > 0)
            player1.setY(player1.getY() - player1.getSpeed());
        else if (player1Down && player1.getY() < (400 - player1.getHeight()))
            player1.setY(player1.getY() + player1.getSpeed());
        else
            player1.setY(player1.getY());
        player2.setY(player2.getY());			/*!!!!!!*/
    }

    private void updateScore() {
        if (player1.getType() == "Server") {
            if (ball.getX() < player1.getX()) {
                score.setCurrentScorePlayer2((score.getCurrentScorePlayer2() + 1));
            }

            if (ball.getX() > player2.getX()) {
                score.setCurrentScorePlayer1((score.getCurrentScorePlayer1() + 1));
            }
        }

        if (player1.getType() == "Client") {
            if (ball.getX() < player2.getX()) {
                score.setCurrentScorePlayer1((score.getCurrentScorePlayer1() + 1));
            }

            if (ball.getX() > player1.getX()) {
                score.setCurrentScorePlayer2((score.getCurrentScorePlayer2() + 1));
            }
        }
    }

    private boolean isGoal() {
        if (player1.getType() == "Server") {
            if (ball.getX() < player1.getX()) {
                updateScore();
                return true;
            }

            if (ball.getX() > player2.getX()) {
                updateScore();
                return true;
            }
        } else
            return false;
        return false;


    }


}
