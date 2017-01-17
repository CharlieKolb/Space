package main;

public class Highscore {


	private static int[][] highscore = new int[5][5];
	private static int[] highestScore = new int[5];
	
	public static void addScore(int ship, int level, int score) {
		if(highscore[ship][level] < score) highscore[ship][level] = score;
		if(highestScore[level] < highscore[ship][level]) highestScore[level] = highscore[ship][level];
	}
	
	public static int getScore(int ship, int level) {
		return highscore[ship][level];
	}
	
	public static int getHighestScore(int level) {
		return highestScore[level];
	}

	
	public static void save(int level) {
		new HighscoreSave().save(level);
	}
	
	public static int[][] getHighscoreArray() {
		return highscore;
	}
}
