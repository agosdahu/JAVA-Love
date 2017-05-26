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
	Uto uto1;
	Uto uto2;
	Ball ball;
	
	public Teglalap(Control control) {
		this.player1 = control.getPlayer1();
		this.player2 = control.getPlayer2();
		uto1 = new Uto(player1);
		uto2 = new Uto(player2);
		this.ball = control.getBall();
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(5, 100, 600, 400);
		g.fillRect(305, 100, 3, 400);
		g.fillRect(uto1.posx, uto1.posy, 5, 50);
		g.fillRect(uto2.posx, uto2.posy, 5, 50);
		g.fillOval(ball.getX() + 5, ball.getY() + 100, ball.getWidth(), ball.getHeight());

	}

}
