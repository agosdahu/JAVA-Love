package tenisz;

public class Ball {

		private final int WIDTH = 10;
		private final int HEIGHT = 10;

		private int x;
		private int y;
		
		private int speed;
		private int normalSpeed;
		private int bendSpeed;

		public Ball(int x, int y, int s, int bendSpeed){
			this.x = x;
			this.y = y;
			this.speed = s;
			this.setBendSpeed(bendSpeed);
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
