package tenisz;

import java.io.Serializable;

public class Ball implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private final int WIDTH = 10;
		private final int HEIGHT = 10;

		private int x;
		private int y;
		
		private int speed = 1;	
		private int normalSpeed = 1;
		private int bendSpeed = 2;

		public Ball(int x, int y, int s, int normalSpeed, int bendSpeed){
			this.x = x;
			this.y = y;
			this.speed = s;
			this.normalSpeed = normalSpeed;
			this.bendSpeed = bendSpeed;
		}
		
		public Ball(){
			this.x = 0;
		}
		
		public int getWidth(){
			return WIDTH;
		}
		
		public int getHeight(){
			return HEIGHT;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getSpeed(){
			return speed;
		}
		
		public void setX(int newX){
			x = newX;
		}
		
		public void setY(int newY){
			y = newY;
		}
		
		public void setSpeed(int s){
			speed = s;
		}

		public int getBendSpeed() {
			return bendSpeed;
		}

		public void setBendSpeed(int bendSpeed) {
			this.bendSpeed = bendSpeed;
		}

		public int getNormalSpeed() {
			return normalSpeed;
		}

		public void setNormalSpeed(int normalSpeed) {
			this.normalSpeed = normalSpeed;
		}

}
