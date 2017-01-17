package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Menu extends JPanel implements KeyListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -199578248231830711L;

	private enum navigationState_ {
        NULL,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        HIGHSCORE,
        SHIP,
        GO
    }
    private enum lockInState_ {
        NULL,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        HIGHSCORE

    }
    
    Graphics2D g2d;
    
    private ImageIcon[][] icons;
    Image[] image;
    Image imageInstance;
    
    private navigationState_ state = navigationState_.LEVEL1;
    private lockInState_ lockIn = lockInState_.NULL;
    
    private int ship = 1;
    private boolean shipConfirmed;
    private boolean startGame;

    private int highestUnlockedLevel;
    
    private int frameCounter;
    
    
    private double scale;
    
    private boolean shippedRight;
    private boolean shippedLeft;
    private int shippedRightCD;
    private int shippedLeftCD;
    
    public Menu(double scale, int highestUnlockedLevel) {

    	this.highestUnlockedLevel = highestUnlockedLevel;
    	
        this.scale = scale;
        icons = new ImageIcon[11][4]; 
        icons[0][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/background.png"));
        icons[0][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/levelBorder01.png"));
        icons[0][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/levelBorder02.png"));
        icons[1][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipOuterBorder01.png"));
        icons[1][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipOuterBorder02.png"));
        icons[1][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipInnerBorder01.png"));
        icons[1][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipInnerBorder02.png"));
        icons[2][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipLeft01.png"));
        icons[2][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipLeft02.png"));
        icons[2][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipRight01.png"));
        icons[2][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/shipRight02.png"));
        icons[3][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/textBorder01.png"));
        icons[3][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/textBorder02.png"));
        icons[3][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/goBorder01.png"));
        icons[3][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/goBorder02.png"));
        icons[4][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level1_01.png"));
        icons[4][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level1_02.png"));
        icons[4][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level1_01.png"));
        icons[5][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level2_01.png"));
        icons[5][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level2_02.png"));
        icons[5][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level2_03.png"));
        icons[6][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level3_01.png"));
        icons[6][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level3_02.png"));
        icons[6][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level3_03.png"));
        icons[7][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level4_01.png"));
        icons[7][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level4_02.png"));
        icons[7][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/level4_03.png"));
        icons[8][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player.png"));
        icons[8][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player.png"));
        icons[8][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player2.png"));
        icons[8][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player2_locked.png"));
        icons[9][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player3.png"));
        icons[9][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player3_locked.png"));
        icons[9][2] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player4.png"));
        icons[9][3] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Player/player4_locked.png"));
        icons[10][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/highscore1.png"));
        icons[10][1] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/highscore2.png"));
        
        image = new Image[40];
        image[0] = icons[0][0].getImage();
        image[1] = null;
        image[2] = icons[0][2].getImage();
        image[3] = icons[0][3].getImage();
        image[4] = icons[1][0].getImage();
        image[5] = icons[1][1].getImage();
        image[6] = icons[1][2].getImage();
        image[7] = icons[1][3].getImage();
        image[8] = icons[2][0].getImage();
        image[9] = icons[2][1].getImage();
        image[10] = icons[2][2].getImage();
        image[11] = icons[2][3].getImage();
        image[12] = icons[3][0].getImage();
        image[13] = icons[3][1].getImage();
        image[14] = icons[3][2].getImage();
        image[15] = icons[3][3].getImage();
        image[16] = icons[4][0].getImage();
        image[17] = icons[4][1].getImage();
        image[18] = icons[4][2].getImage();
        image[19] = icons[5][0].getImage();
        image[20] = icons[5][1].getImage();
        image[21] = icons[5][2].getImage();
        image[22] = icons[6][0].getImage();
        image[23] = icons[6][1].getImage();
        image[24] = icons[6][2].getImage();
        image[25] = icons[7][0].getImage();
        image[26] = icons[7][1].getImage();
        image[27] = icons[7][2].getImage();
        image[28] = icons[8][0].getImage();
        image[29] = icons[8][1].getImage();
        image[30] = icons[8][2].getImage();
        image[31] = icons[8][3].getImage();
        image[32] = icons[9][0].getImage();
        image[33] = icons[9][1].getImage();
        image[34] = icons[9][2].getImage();
        image[35] = icons[9][3].getImage();
        image[36] = icons[10][0].getImage();
        image[37] = icons[10][1].getImage();
    }
    
    public double getScale() {
        return scale;
    }
    
        
    public void move() {
        if(shippedRight) {
            shippedRight = false;
            shippedRightCD = 7;
        }
        if(shippedRightCD != 0) shippedRightCD--;
        if(shippedLeft) {
            shippedLeft = false;
            shippedLeftCD = 7;
        }
        if(shippedLeftCD != 0) shippedLeftCD--;
        if(frameCounter > 60) frameCounter = 0;
        frameCounter++;
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D)g;
        g2d.scale(scale, scale);
        g2d.drawImage(image[0], 0, 0, this);                                             //Background

        
        g2d.drawImage(getLevelBorderImage(1), 0, 2, this);                               //Level 1 Border
        g2d.drawImage(getLevelBorderImage(2), 413, 2, this);                             //Level 2 Border
        g2d.drawImage(getLevelBorderImage(3), 0, 261, this);                             //Level 3 Border
        g2d.drawImage(getLevelBorderImage(4), 413, 261, this);                           //Level 4 Border
        
        g2d.drawImage(getLevelImage(1), 3, 5, this);                                     //Level 1
        g2d.drawImage(getLevelImage(2), 416, 5, this);                                   //Level 2
        g2d.drawImage(getLevelImage(3), 3, 264, this);                                   //Level 3
        g2d.drawImage(getLevelImage(4), 416, 264, this);                                 //Level 4
        
        g2d.drawImage(getShipOuterBorderImage(), 0, 520, this);                          //Ship outer Border
        g2d.drawImage(getShipInnerBorderImage(), 134, 531, this);                        //Ship inner Border
        g2d.drawImage(getShipLeftImage(), 24, 550, this);                                //Ship left Arrow
        g2d.drawImage(getShipRightImage(), 317, 550,this);                               //Ship right Arrow  
        
        g2d.drawImage(getShipImage(), getShipX(), getShipY(), this);                     //Ship
        
        g2d.drawImage(image[12], 0, 731, this);                                          //Tutorial 1
        g2d.drawImage(image[13], 413, 520, this);                                        //Tutorial 2
        
        g2d.drawImage(getGoImage(), 0, 648, this);                                       //Go
        g2d.drawImage(getHighscoreImage(), 413, 648, this);
        
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 55f));
        g2d.drawString("GO", 171, 703);
        g2d.drawString("Highscore", 460, 703);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
        g2d.drawString("Choose a level and a ship.", 419, 548);
        g2d.drawString("Confirm your level-choice", 419, 568);
        g2d.drawString("by pressing Enter.", 419, 588);
        g2d.drawString("Assets used from http://kenney.itch.io/kenney-donation", 10, 760);
        
    }
    
    public Image getHighscoreImage() {
    	if(state == navigationState_.HIGHSCORE) return image[37];
    	else return image[36];
    }
    
    public int getShipX() {
        int x = 207;
        switch(ship) {
            case 1:
                x = x - icons[8][0].getIconWidth() / 2;
                break;
            case 2:
                x = x - icons[8][2].getIconWidth() / 2;
                break;
            case 3:
                x = x - icons[9][0].getIconWidth() / 2;
                break;
            case 4:
                x = x - icons[9][2].getIconWidth() / 2;
                break;
        }
        return x;
    }
    
    public int getShipY() {
        int y = 581;
        switch(ship) {
            case 1:
                y = y - icons[8][0].getIconHeight() / 2;
                break;
            case 2:
                y = y - icons[8][2].getIconHeight() / 2;
                break;
            case 3:
                y = y - icons[9][0].getIconHeight() / 2;
                break;
            case 4:
                y = y - icons[9][2].getIconHeight() / 2;
                break;
        }
        return y;
    }
    
    public Image getShipImage() {
        switch(ship) {
            case 1:
                imageInstance = image[28];
                break;
            case 2:
                if(highestUnlockedLevel > 1) imageInstance = image[30];
                else imageInstance = image[31];
                break;
            case 3:
                if(highestUnlockedLevel > 2) imageInstance = image[32];
                else imageInstance = image[33];
                break;
            case 4:
                if(highestUnlockedLevel > 3) imageInstance = image[34];
                else imageInstance = image[35];
                break;
        }
        return imageInstance;
    }
    
    public Image getLevelImage(int level) {
        switch(level) {
            case 1:
                if(lockIn == lockInState_.LEVEL1 && frameCounter > 30) imageInstance = image[17];
                else imageInstance = image[16];
                break;
            case 2:
                if(highestUnlockedLevel < 2) imageInstance = image[21];
                else if(lockIn == lockInState_.LEVEL2  && frameCounter > 30) imageInstance = image[20];
                else imageInstance = image[19];
                break;
            case 3:
                if(highestUnlockedLevel < 3) imageInstance = image[24];
                else if(lockIn == lockInState_.LEVEL3 && frameCounter > 30) imageInstance = image[23];
                else imageInstance = image[22];
                break;
            case 4:
                if(highestUnlockedLevel < 4) imageInstance = image[27];
                else if(lockIn == lockInState_.LEVEL4 && frameCounter > 30) imageInstance = image[25];
                else imageInstance = image[26];
                break;
        }
        return imageInstance;
    }
    
    public Image getGoImage() {
        if(state == navigationState_.GO) imageInstance = image[15];
        else imageInstance = image[14];
        return imageInstance;
    }
    
    public Image getShipRightImage() {
        if(shippedRightCD == 0) imageInstance = image[10];
        else imageInstance = image[11]; 
        return imageInstance;
    }
    
    public Image getShipLeftImage() {
        if(shippedLeftCD == 0) imageInstance = image[8];
        else imageInstance = image[9]; 
        return imageInstance;
    }
    
    public Image getShipInnerBorderImage() {
        if(shipConfirmed) imageInstance = image[6]; 
        else imageInstance = image[6];
        return imageInstance;
    }
    
    public Image getShipOuterBorderImage() {
        if(state == navigationState_.SHIP) imageInstance = image[5];  
        else imageInstance = image[4];
        return imageInstance;
    }
    
    public Image getLevelBorderImage(int Level) {
        switch(Level) {
            case 1:
                if(state == navigationState_.LEVEL1) imageInstance = image[3];
                else imageInstance = image[2];
                break;
            case 2:
                if(state == navigationState_.LEVEL2) imageInstance = image[3];
                else imageInstance = image[2];
                break;
            case 3:
                if(state == navigationState_.LEVEL3) imageInstance = image[3];
                else imageInstance = image[2];
                break;    
            case 4:
                if(state == navigationState_.LEVEL4) imageInstance = image[3];
                else imageInstance = image[2];
                break;     
        }
        return imageInstance;
    }
    
    public int getShip() {
        return ship;
    }
    
    public void reset() {
        lockIn = lockInState_.NULL;
        state = navigationState_.LEVEL1;
    }
        
    public void moveUp() {
        switch(state) {
            case LEVEL1:
                state = navigationState_.GO;
                break;
            case LEVEL2:
                state = navigationState_.HIGHSCORE;
                break;
            case LEVEL3:
                state = navigationState_.LEVEL1;
                break;
            case LEVEL4:
                state = navigationState_.LEVEL2;
                break;
            case HIGHSCORE:
            	state = navigationState_.LEVEL4;
            	break;
            case SHIP:
                state = navigationState_.LEVEL3;
                break;
            case GO:
                state = navigationState_.SHIP;
                break;
		case NULL:
			break;
		default:
			break;
        }
    }
    
    public void moveDown() {
        switch(state) {
            case LEVEL1:
                state = navigationState_.LEVEL3;
                break;
            case LEVEL2:
                state = navigationState_.LEVEL4;
                break;
            case LEVEL3:
                state = navigationState_.SHIP;
                break;
            case LEVEL4:
                state = navigationState_.HIGHSCORE;
                break;
            case HIGHSCORE:
            	state = navigationState_.LEVEL2;
            	break;
            case SHIP:
                state = navigationState_.GO;
                break;
            case GO:
                state = navigationState_.LEVEL1;
                break;
		case NULL:
			break;
		default:
			break;
        }
    }
    
    public void moveRight() {
        switch(state) {
            case LEVEL1:
                state = navigationState_.LEVEL2;
                break;
            case LEVEL2:
                state = navigationState_.LEVEL1;
                break;
            case LEVEL3:
                state = navigationState_.LEVEL4;
                break;
            case LEVEL4:
                state = navigationState_.LEVEL3;
                break;
            case SHIP:
                if(!shipConfirmed) ship++;
                if(ship > 3) ship = 1;
                shippedRight = true;
                break;
            case GO:
            	state = navigationState_.HIGHSCORE;
                break;
            case HIGHSCORE:
            	state = navigationState_.GO;
		case NULL:
			break;
		default:
			break;
        }
    }
    
    public void moveLeft() {
        switch(state) {
            case LEVEL1:
                state = navigationState_.LEVEL2;
                break;
            case LEVEL2:
                state = navigationState_.LEVEL1;
                break;
            case LEVEL3:
                state = navigationState_.LEVEL4;
                break;
            case LEVEL4:
                state = navigationState_.LEVEL3;
                break;
            case SHIP:
                if(!shipConfirmed) ship--;
                shippedLeft = true;
                if(ship < 1) ship = 3;
                break;
            case GO:
            	state = navigationState_.HIGHSCORE;
                break;
            case HIGHSCORE:
            	state = navigationState_.GO;
		case NULL:
			break;
		default:
			break;
        }
    }
    
    public void lockIn() {
        switch(state) {
            case LEVEL1:
                lockIn = lockInState_.LEVEL1;
                break;
            case LEVEL2:
                if(highestUnlockedLevel >= 2) {
                    lockIn = lockInState_.LEVEL2;
                }
                break;
            case LEVEL3: 
                if(highestUnlockedLevel >= 3) {
                    lockIn = lockInState_.LEVEL3;
                }
                break;
            case LEVEL4:
                if(highestUnlockedLevel >= 4) {
                    lockIn = lockInState_.LEVEL4;
                }
                break;
            case HIGHSCORE:
            	lockIn = lockInState_.HIGHSCORE;
            	launchGame();
            	break;
            case SHIP:
                break;
            case GO:
                launchGame();
                break;
		case NULL:
			break;
		default:
			break;
        }
    }
    
    public void launchGame() {
    	if(lockIn == lockInState_.HIGHSCORE) startGame = true;
    	else if(lockIn != lockInState_.NULL && highestUnlockedLevel >= ship) {
            startGame = true;
        }
        else if(lockIn == lockInState_.NULL) {
            System.out.println("Confirm your level-choice by pressing Enter while hovering over the level please.");
        }
        else if(!shipConfirmed) {
            System.out.println("Confirm your spaceship by pressing Enter after choosing your ride");
        }
    }    
    
    public boolean startGame() {
        return startGame;
    }
    
    public int getLevel() {
        switch(lockIn) {
            case LEVEL1:
                return 1;
            case LEVEL2:
                return 2;
            case LEVEL3:
                return 3;
            case LEVEL4:
                return 4;
            case HIGHSCORE:
            	return 66;
		case NULL:
			break;
		default:
			break;
        }
        return 0;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();        
         switch(key) {
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_ENTER:
                lockIn();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
             
        }
    }
    
    public void keyReleased(KeyEvent e) {           
    }
    
    public void keyTyped(KeyEvent e) {        
    }
}