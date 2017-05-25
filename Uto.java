package tenisz;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Uto /*extends JPanel*/{
	
	Player player;
	int posx;
	int posy;
	
	public Uto(Player player){
		this.player = player;
		this.posx = this.player.pos_x + 5;
		this.posy = this.player.pos_y + 100;
		//repaint();
	}
	
//	public void paint(Graphics g) {
//		super.paint(g);
//		
//		System.out.println("oooh");
//		g.fillRect(posx, posy, 5, 50);
//		System.out.println("posx: " + posx);
//		System.out.println("posy: " + posy);
//	}

}
