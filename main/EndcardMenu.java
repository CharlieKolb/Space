package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class EndcardMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161799569483084766L;

	private int level;
	private int ship;
	
	private int frameCounter;
	
	private int highscore;
	private int time;
	private int bonusScore;
	private int oldHighscore;
	private int prevHighscore;
	
	private boolean backToMenu;
	private boolean displayEndcard;
	
	private boolean highscoreFinal;
	
	private Graphics2D g2d;
	
	private BasicStroke rectStroke = new BasicStroke(6);
	private BasicStroke tableStroke = new BasicStroke(5);
	private BasicStroke oldStroke = new BasicStroke(1);
	
	public EndcardMenu(int Level, int Ship) {
		level = Level;
		ship = Ship;
		time = -1;
		oldHighscore = Highscore.getScore(Ship, Level);
	}
	
	public void move(int inputValue, int hscore, int t) {
		if(highscore == 0) highscore = hscore;	
		if(time == -1) {
			bonusScore = 5000 - time*10;
			prevHighscore = highscore;
			time = t;
		}
		frameCounter++;
		if(prevHighscore + 5000 - time*10 > highscore) highscore += bonusScore / 100;
		else {
			highscoreFinal = true;
			Highscore.addScore(ship, level, highscore);
			Highscore.save(level);
		}
		
		if(inputValue == 8 && highscoreFinal) {	//Enter
			backToMenu = true;
		}
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        
        g2d = (Graphics2D)g;

        if(displayEndcard) {
        	g2d.setColor(Color.DARK_GRAY);
        	g2d.fillRect(0, 0, 1000, 1000);
        	
        	//main
        	g2d.setColor(Color.GRAY);
        	g2d.fillRect(30, 250, 760, 500);
        	g2d.setColor(Color.BLACK);     	
        	g2d.setStroke(rectStroke);
        	g2d.drawRect(30, 250, 760, 500);
        	g2d.setStroke(oldStroke);
        	

        	//left
        	g2d.setColor(new Color(127, 127, 127));
        	g2d.fillRect(30, 90, 350, 130);
        	g2d.setColor(Color.BLACK);     	
        	g2d.setStroke(rectStroke);
        	g2d.drawRect(30, 90, 350, 130);
        	g2d.setStroke(oldStroke);
        	
        	
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));
        	if(time < 10) g2d.drawString("00:0" + time, 90, 168);
        	else if(time < 60) g2d.drawString("00:" + time, 90, 168);
        	else if(time / 60 < 10 && time % 60 > 9) g2d.drawString("0" + time/60 + ":" + time % 60, 90, 168);
        	else if(time / 60 < 10) g2d.drawString("0" + time/60 + ":0" + time % 60, 90, 168);
        	else if(time % 60 < 10) g2d.drawString(time / 60 + ":0" + time % 60, 90, 168);
        	else g2d.drawString(time / 60 + ":" + time % 60, 90, 168);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
        	g2d.drawString("your time", 150, 200);
        
        	//right
        	g2d.setColor(new Color(127, 127, 127));
        	g2d.fillRect(440, 90, 350, 130);
        	g2d.setColor(Color.BLACK);     	
        	g2d.setStroke(rectStroke);
        	g2d.drawRect(440, 90, 350, 130);
        	g2d.setStroke(oldStroke);
        	
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));
        	g2d.drawString(Integer.toString(highscore), 530, 168);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
        	g2d.drawString("your score", 560, 200);
        	
        	
        	g2d.setStroke(tableStroke);
        	//vertical Lines
        	g2d.drawLine(190, 350, 190, 710);
        	g2d.drawLine(337, 350, 337, 650);
        	g2d.drawLine(484, 350, 484, 650);
        	g2d.drawLine(630, 350, 630, 710);
        	
        	//horizontal Lines
        	g2d.drawLine(190, 350, 630, 350);
        	g2d.drawLine(190, 410, 630, 410);
        	g2d.drawLine(190, 470, 630, 470);
        	g2d.drawLine(190, 530, 630, 530);
        	g2d.drawLine(190, 590, 630, 590);
        	g2d.drawLine(190, 650, 630, 650);
        	g2d.drawLine(190, 710, 630, 710);
        	
        	//Ships
        	g2d.drawImage(GameGraphics.getImage(0, 1), 210, 270, this);
        	g2d.drawImage(GameGraphics.getImage(0, 6), 355, 270, this);
        	g2d.drawImage(GameGraphics.getImage(0, 11), 510, 270, this);
        	
        	//Levels
            for(int i = 0; i < 4; i++) {
            	g2d.drawString("Level " + (i+1), 70, 380 + 60*i);
            }
            
            g2d.drawString("Total ", 70, 620);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 17f));

            g2d.drawString("Best of each ", 34, 677);
            g2d.drawString("level combined ", 34, 697);


        	//Highscores
            //4x4 Field with blinking new score
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 23f));
        	for(int i = 0; i < 3; i++) {
        		for(int k = 0; k < 4; k++) {
        			if(i+1 != ship || k+1 != level) {
        				g2d.drawString(Integer.toString(Highscore.getScore(i+1, k+1)), 200 + 147 * i, 383 + 60 * k);
        			}
        		}
        	}
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28f));
        	if(frameCounter > 30 && highscore > oldHighscore && highscoreFinal) {
        		g2d.drawString(Integer.toString(highscore), 200 + 147 * (ship-1), 383 + 60 * (level-1));
        	}
        	else if(highscoreFinal) g2d.drawString(Integer.toString(Highscore.getScore(ship, level)), 200 + 147 * (ship-1), 383 + 60 * (level-1));
        	if(frameCounter > 60) frameCounter = 0;
        	
        	//Total Scores
        	g2d.drawString(Integer.toString(Highscore.getScore(1,1) + Highscore.getScore(1,2) + Highscore.getScore(1,3) + Highscore.getScore(1,4)), 200, 623);
        	g2d.drawString(Integer.toString(Highscore.getScore(2,1) + Highscore.getScore(2,2) + Highscore.getScore(2,3) + Highscore.getScore(2,4)), 347, 623);
        	g2d.drawString(Integer.toString(Highscore.getScore(3,1) + Highscore.getScore(3,2) + Highscore.getScore(3,3) + Highscore.getScore(3,4)), 494, 623);
        	
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 33f));
        	g2d.drawString(Integer.toString(Highscore.getHighestScore(1) + Highscore.getHighestScore(2) + Highscore.getHighestScore(3)), 355, 690);
        	
        	//Enter to conitnue
        	g2d.drawImage(GameGraphics.getImage(6,0), 660, 610, this);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 25f));
        	g2d.drawString("continue", 650, 720);
        }
        
        Toolkit.getDefaultToolkit().sync();       
    }
	
	public int getHighscore() {
		return highscore;
	}
	
	public void showScreen() {
		displayEndcard = true;
	}
	
	public boolean getBackToMenu() {
		return backToMenu;
	}
	
	
}







