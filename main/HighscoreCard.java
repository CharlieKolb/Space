package main;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HighscoreCard extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3843103028418321645L;
	
    private Graphics2D g2d;
    
    private BasicStroke rectStroke = new BasicStroke(6);
	private BasicStroke tableStroke = new BasicStroke(5);
	
	private ImageIcon[] icons;
    
    private double scale;
    private boolean goToMenu;
    
    public HighscoreCard(double scale) {
        this.scale = scale;
        icons = new ImageIcon[5];
        icons[0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player.png"));
        icons[1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player2.png"));
        icons[2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player3.png"));
        icons[3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player4.png"));
        icons[4] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/enter.png"));
    }
    
    public double getScale() {
        return scale;
    }
    
    public void move() {
        repaint();
    }
    
    public void paint(Graphics g) {
        g2d = (Graphics2D)g;
        
        g2d.scale(scale, scale);
        
        g2d.setColor(Color.DARK_GRAY);
    	g2d.fillRect(0, 0, 1000, 1000);
    	
    	
    	
    	//highscoreBox
    	g2d.setColor(Color.GRAY);
    	g2d.fillRect(130, 35, 540, 100);
    	g2d.setColor(Color.BLACK);     	
    	g2d.setStroke(rectStroke);
    	g2d.drawRect(130, 35, 540, 100);
        g2d.setStroke(tableStroke);
        
        //"highscore"
        g2d.setColor(Color.BLACK);
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));
    	g2d.drawString("Highscore", 170, 110);
    	
    	//mainBox
    	g2d.setColor(Color.GRAY);
    	g2d.fillRect(120, 150, 560, 500);
    	g2d.setColor(Color.BLACK);     	
    	g2d.setStroke(rectStroke);
    	g2d.drawRect(120, 150, 560, 500);
        g2d.setStroke(tableStroke);
        
    	//vertical Lines
    	g2d.drawLine(290, 250, 290, 610);
    	g2d.drawLine(400, 250, 400, 550);
    	g2d.drawLine(510, 250, 510, 550);
    	g2d.drawLine(620, 250, 620, 610);
    	
    	//horizontal Lines
    	g2d.drawLine(290, 250, 620, 250);
    	g2d.drawLine(290, 310, 620, 310);
    	g2d.drawLine(290, 370, 620, 370);
    	g2d.drawLine(290, 430, 620, 430);
    	g2d.drawLine(290, 490, 620, 490);
    	g2d.drawLine(290, 550, 620, 550);
    	g2d.drawLine(290, 610, 620, 610);
    	
    	//Ships
    	g2d.drawImage(icons[0].getImage(), 297, 170, this);
    	g2d.drawImage(icons[1].getImage(), 401, 170, this);
    	g2d.drawImage(icons[2].getImage(), 517, 170, this);
    	
    	//Levels
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 23f));

        for(int i = 0; i < 4; i++) {
        	g2d.drawString("Level " + (i+1), 170, 280 + 60*i);
        }
        
        g2d.drawString("Total ", 170, 520);
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 17f));

        g2d.drawString("Best of each ", 134, 577);
        g2d.drawString("level combined ", 134, 597);


    	//Highscores
        //4x4 Field with blinking new score
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 23f));
    	for(int i = 0; i < 3; i++) {
    		for(int k = 0; k < 4; k++) {
    			g2d.drawString(Integer.toString(Highscore.getScore(i+1, k+1)), 300 + 110 * i, 283 + 60 * k);
    		}
    	}

    	
    	//Total Scores
    	g2d.drawString(Integer.toString(Highscore.getScore(1,1) + Highscore.getScore(1,2) + Highscore.getScore(1,3) + Highscore.getScore(1,4)), 300, 523);
    	g2d.drawString(Integer.toString(Highscore.getScore(2,1) + Highscore.getScore(2,2) + Highscore.getScore(2,3) + Highscore.getScore(2,4)), 410, 523);
    	g2d.drawString(Integer.toString(Highscore.getScore(3,1) + Highscore.getScore(3,2) + Highscore.getScore(3,3) + Highscore.getScore(3,4)), 520, 523);
    	
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 33f));
    	g2d.drawString(Integer.toString(Highscore.getHighestScore(1) + Highscore.getHighestScore(2) + Highscore.getHighestScore(3) + Highscore.getHighestScore(4)), 400, 590);
    
    	//Back
    	g2d.drawImage(icons[4].getImage(), 235, 670, this);
    	g2d.drawString("Press               to go back to menu", 115, 740);
    }

    
    public boolean goToMenu() {
        return goToMenu;
    }
    
    public void keyReleased(KeyEvent e) {
        
    }   
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_ENTER) {
            goToMenu = true;     
        }
    }
}