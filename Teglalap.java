package tenisz;

import java.awt.Graphics;

import javax.swing.JPanel;


public class Teglalap extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Player player1;
	Player player2;
	//Uto uto1;
	//Uto uto2;
	Ball ball;
	
	public Teglalap(Control control) {
		this.player1 = control.getPlayer1();
		this.player2 = control.getPlayer2();
	//	uto1 = new Uto(player1);
	//	uto2 = new Uto(player2);
		this.ball = control.getBall();
		repaint();
	}
	
		
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(5, 100, 600, 400);
		g.fillRect(305, 100, 3, 400);
		g.fillRect(player1.getX() + 5, player1.getY() + 100, player1.getWidth(), player1.getHeight());
		g.fillRect(player2.getX() + 5, player2.getY() + 100, player2.getWidth(), player2.getHeight());
		g.fillOval(ball.getX() + 5, ball.getY() + 100, ball.getWidth(), ball.getHeight());

	}

}
