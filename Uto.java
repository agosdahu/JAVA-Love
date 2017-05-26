package tenisz;

public class Uto /*extends JPanel*/{
	
	//public Player player;
	public int posx;
	public int posy;
	
	public Uto(Player player){
		this.posx = player.getX() + 5;
		this.posy = player.getY() + 100;
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
