package tenisz;

import java.io.Serializable;

public class Player implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private String name; 
		private String type;

		private final int WIDTH = 5;
		private final int HEIGHT = 50;
		
		private int pos_x, pos_y;
		
		private int speed = 5;
		
		public Player(String name, int pos_x, int pos_y, int s){
			this.name = name;
			this.pos_x = pos_x;
			this.pos_y = pos_y;
			this.speed = s;
		}
		
		public Player (Player player){
			this.name = player.name;
			this.pos_x = player.pos_x;
			this.pos_y = player.pos_y;
			this.speed = player.speed;
		}
		
		public Player(){
			this.name = "Anonimus";
			this.pos_x = 0;
			this.pos_y = 0;
			this.speed = 1;
		}
		
		public String getName(){
			return name;
		}
				
		public int getWidth(){
			return WIDTH;
		}
		
		public int getHeight(){
			return HEIGHT;
		}
		
		public int getX(){
			return pos_x;
		}
		
		public int getY(){
			return pos_y;
		}
		
		public int getSpeed(){
			return speed;
		}
		
		public void setName(String newName){
			name = newName;
		}
		
		public void setX(int newX){
			pos_x = newX;
		}
		
		public void setY(int newY){
			pos_y = newY;
		}
		
		public void setSpeed(int s){
			speed = s;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public void copyPlayer (Player player){
			this.name = player.name;
			this.pos_x = player.pos_x;
			this.pos_y = player.pos_y;
			this.speed = player.speed;
		}
	
	
}
