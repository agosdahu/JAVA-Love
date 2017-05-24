package tenisz;

public class Player {

		private String name; 
		private String type;

		private final int WIDTH = 5;
		private final int HEIGHT = 50;
		
		private int pos_x, pos_y;
		
		private int speed;
		
		public Player(String name, int pos_x, int pos_y, int s){
			this.name = name;
			this.pos_x = pos_x;
			this.pos_y = pos_y;
			this.speed = s;
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
			pos_y = newX;
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
	
	
}
