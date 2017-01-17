package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PauseMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161799569483084766L;

	private enum pauseState_ {
		proceed,
		leave,
		confirmYes,
		confirmNo;
	}
	
	private boolean backToMenu;
	private boolean backToGame;
	
	private int cooldown = 10;
	private boolean allowToSwitch;
	
	private Graphics2D g2d;
	
	private pauseState_ pauseState = pauseState_.proceed;
	
	public void move(int inputValue) {
		backToGame = false;
		if(cooldown != 0) {
			cooldown -= 1;
			allowToSwitch = false;
		}
		else allowToSwitch = true;
		
		if(allowToSwitch) {
			if(inputValue == 6 || inputValue == 3) {
				cooldown = 10;
				switch(pauseState) {
					case proceed:
						pauseState = pauseState_.leave;
						break;
					case leave:
						pauseState = pauseState_.proceed;
					default:
						break;
					
				}
			}
			else if(inputValue == 1 || inputValue == 2) {
				cooldown = 12;
				switch(pauseState) {
					case confirmYes:
						pauseState = pauseState_.confirmNo;
						break;
					case confirmNo:
						pauseState = pauseState_.confirmYes;
						break;
					default:
						break;
				}
			}
			
			else if(inputValue == 8) {
				cooldown = 12;
				switch(pauseState) {
					case confirmYes:
						backToMenu = true;
						break;
					case confirmNo:
						pauseState = pauseState_.leave;
						break;
					case proceed:
						backToGame = true;
						break;
					case leave:
						pauseState = pauseState_.confirmYes;
						break;
				default:
					break;
				
				}
			}
			
			else if(inputValue == 9) {
				cooldown = 10;
				switch(pauseState) {
					case confirmYes: case confirmNo:
						pauseState = pauseState_.leave;
						break;
					default: 
						backToGame = true;
						break;
				}
			}
		}
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        
        g2d = (Graphics2D)g;
        
        g2d.drawImage(GameGraphics.getImage(5, 0), 200, 300, this);											//Draws outer Border
        
        if(pauseState == pauseState_.proceed) g2d.drawImage(GameGraphics.getImage(5, 2), 210, 345, this);	//Draws active "Proceed" button
        else g2d.drawImage(GameGraphics.getImage(5, 1), 210, 345, this);									//Draws inactive "Proceed" button
        
        if(pauseState == pauseState_.leave) g2d.drawImage(GameGraphics.getImage(5, 2), 210, 382, this);		//Draws active "Exit" button
        else g2d.drawImage(GameGraphics.getImage(5, 1), 210, 382, this);									//Draws inactive "Exit" button
        
        g2d.drawString("Game paused", 320, 330);
        g2d.drawString("Continue", 345, 370);
        g2d.drawString("Leave", 359,  405);
        
        if(pauseState == pauseState_.confirmNo || pauseState == pauseState_.confirmYes) {
        	g2d.drawImage(GameGraphics.getImage(5, 3), 240, 310, this);
        	
        	
        	if(pauseState == pauseState_.confirmNo) {
        		g2d.drawImage(GameGraphics.getImage(5, 5), 377, 340, this);
        		g2d.drawImage(GameGraphics.getImage(5, 6), 250, 340, this);
        	}
        	else {
        		g2d.drawImage(GameGraphics.getImage(5, 4), 377, 340, this);
        		g2d.drawImage(GameGraphics.getImage(5, 7), 250, 340, this);
        	}
        	
        	g2d.drawString("Return to menu?", 293, 332);
        	
        	g2d.drawString("yes", 290, 377);
        	g2d.drawString("no", 482, 377);
        	
        }
        
        Toolkit.getDefaultToolkit().sync();       
    }
	
	public boolean getBackToMenu() {
		return backToMenu;
	}
	
	public boolean getBackToGame() {
		return backToGame;
	}
	
}







