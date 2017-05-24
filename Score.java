package tenisz;

public class Score {

			private int score;

			private int[] currentScore = new int[2];

			public Score(int score, int[] currentScore){
				this.score = score;
				this.currentScore = currentScore;
			}
			
			public int getScore(){
				return score;
			}
				
			public int[] getCurrentScore(){
				return currentScore;
			}
			
			public void setScore(int newScore){
				score = newScore;
			}
			
			public void setCurrentScore(int scorePlayer1, int scorePlayer2){
				currentScore[0] = scorePlayer1;
				currentScore[1] = scorePlayer2;
			}
			

}
