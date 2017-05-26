package tenisz;

public class Score {

			private int score;

			private int currentScorePlayer1;
			private int currentScorePlayer2;

			public Score(int score, int currentScorePlayer1, int currentScorePlayer2){
				this.score = score;
				this.currentScorePlayer1 = currentScorePlayer1;
				this.currentScorePlayer2 = currentScorePlayer2;
			}
			
			public int getScore(){
				return score;
			}
				
			
			public void setScore(int newScore){
				score = newScore;
			}
			
			public void setCurrentScorePlayer1(int scorePlayer1) {
				this.currentScorePlayer1 = scorePlayer1;
			}
			
			public void setCurrentScorePlayer2(int scorePlayer2) {
				this.currentScorePlayer2 = scorePlayer2;
			}

			public int getCurrentScorePlayer1() {
				return currentScorePlayer1;
			}
			
			public int getCurrentScorePlayer2() {
				return currentScorePlayer2;
			}

			public void setCurrentScore(int score1, int score2) {
				setCurrentScorePlayer1(score1);
				setCurrentScorePlayer2(score2);
				
			}


}
