package main;

import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Landing extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3843103028418321645L;

	private enum navi_ {
       YES,
       NO
    }
    
    private ImageIcon[] icons;
    
    private navi_ navi = navi_.YES;
    
    private Graphics2D g2d;
    
    private double scale;
    private boolean goToMenu;
    
    public Landing(double scale, Font normalFont) {
        this.scale = scale;
        icons = new ImageIcon[5];
        icons[0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Landing/sizeOuterBorder.png"));
        icons[1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Landing/sizeInnerBorder01.png"));
        icons[2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Landing/sizeInnerBorder02.png"));
        icons[3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Landing/background.jpg"));
        icons[4] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Landing/credits.png"));
        
    }
    
    public double getScale() {
        return scale;
    }
    
    public void move() {
        
    }
    
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D)g;
        
        Font spaceFont = g2d.getFont();
        
        g2d.drawImage(icons[3].getImage(), 0, 0, this);
        g2d.drawImage(icons[0].getImage(), 22, 580, this);
        g2d.drawImage(getLeftBoxImage(), 37, 660, this);
        g2d.drawImage(getRightBoxImage(), 425, 660, this);
        g2d.drawImage(icons[4].getImage(), 22, 30, this);
        
        
        /*
        g2d.setFont(spaceFont.deriveFont(Font.BOLD, 90f));
        g2d.setColor(Color.BLACK);
		g2d.drawString("HEADHUNTER", 90, 120);
		
		g2d.setFont(sFont.deriveFont(Font.PLAIN, 22f));
		g2d.drawString("A Game By Charlie Kolb", 280, 160);
		
		g2d.drawString("Resources used from: ", 80, 240);
		
		g2d.setFont(sFont.deriveFont(Font.PLAIN, 18f));
		g2d.drawString("kenney (http://kenney.nl/)", 80, 280);
		g2d.drawString("Ships, Backgrounds, Projectiles", 500, 280);
		g2d.drawString("Stellardrone (http://stellardrone.bandcamp.com/)", 80, 320);
		g2d.drawString("Music (Track-Name: Twillight)", 500, 320);
		g2d.drawString("Alex Ortmeier", 80, 360);
		g2d.drawString("Boss-Images", 500, 360);
		g2d.drawString("Nicolae Berbece (nick@thoseawesomeguys.com)", 80, 400);
		g2d.drawString("Control Icons", 500, 400);
		g2d.drawString("Metthew Pablo (www.matthewpablo.com)", 80, 440);
		g2d.drawString("Bossfight-Music", 500, 440);
		g2d.drawString("Lucas Beyer (http://lucasb.eyer.be)", 80, 480);
		g2d.drawString("Explosion-Image", 500, 480);
		g2d.drawString("http://ccmixter.org/people/NenadSimic", 80, 520);
		g2d.drawString("Explosion-Sound", 500, 520);
		
		*/
		
		
		g2d.setFont(spaceFont.deriveFont(Font.PLAIN, 22f));
        g2d.drawString("Is the Window completely visible for you?", 132, 632);
        g2d.drawString("Yes, I can see everything", 45, 690);
        g2d.drawString("No, the window is too big", 435, 690);
        g2d.drawString("Confirm by pressing the Enter-Key", 165, 733);
        
    }
    
    public Image getLeftBoxImage() {
        if(navi == navi_.YES) return icons[2].getImage();
        else return icons[1].getImage();
    }
    
    public Image getRightBoxImage() {
        if(navi == navi_.NO) return icons[2].getImage();
        else return icons[1].getImage();
    }
    
    public boolean goToMenu() {
        return goToMenu;
    }
    
    public void keyReleased(KeyEvent e) {
        
    }   
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT) {
            if(navi == navi_.YES) navi = navi_.NO;
            else navi = navi_.YES;
        }
        if(key == KeyEvent.VK_LEFT) {
            if(navi == navi_.YES) navi = navi_.NO;
            else navi = navi_.YES;
        }
        if(key == KeyEvent.VK_ENTER) {
            if(navi == navi_.YES) {
                goToMenu = true;
            }
            else {
                goToMenu = true;
                scale = 0.88;
            }
        }
    }
}