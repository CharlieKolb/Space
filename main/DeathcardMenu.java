package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class DeathcardMenu extends JPanel {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean backToMenu;
	private boolean displayDeathcard;
	
	private Graphics2D g2d;
	
	private BasicStroke rectStroke = new BasicStroke(6);
	private BasicStroke oldStroke = new BasicStroke(1);

	
	public void move(int inputValue) {

		if(inputValue == 8) {	//Enter
			backToMenu = true;
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        
        g2d = (Graphics2D)g;

        if(displayDeathcard) {
        	
        	//main
        	g2d.setColor(Color.GRAY);
        	g2d.fillRect(20, 320, 780, 120);
        	g2d.setColor(Color.BLACK);     	
        	g2d.setStroke(rectStroke);
        	g2d.drawRect(20, 320, 780, 120);
        	g2d.setStroke(oldStroke);
        	

        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 90f));
        	g2d.drawString("you dead", 200, 390);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 25f));
        
        	
        	g2d.drawString("Press Enter to get back to menu", 190, 415);
        }
        
        Toolkit.getDefaultToolkit().sync();       
	}
	
	public void showScreen() {
		displayDeathcard = true;
	}
	
	public boolean getBackToMenu() {
		return backToMenu;
	}
	
	
}







