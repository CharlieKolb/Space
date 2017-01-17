package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighscoreSave implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int[][] highscore;
	
	private int highestUnlockedLevel;
	 
	public void save(int level) {
		highscore = Highscore.getHighscoreArray();
		if(level < 4) highestUnlockedLevel = level + 1;
		else if(level == 4) highestUnlockedLevel = level;
		try {
			FileOutputStream fileOut = new FileOutputStream("Space_saveFile.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch(IOException f) {
			f.printStackTrace();
		}
	}
	
	public void updateHighscore() {
		for(int i = 0; i < 5; i++) {
			for(int k = 0; k < 5; k++) {
				Highscore.addScore(i, k, highscore[i][k]);
			}
		}
	}
	
	public int getHighestUnlockedLevel() {
		return highestUnlockedLevel;
	}
}
