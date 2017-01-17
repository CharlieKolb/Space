package main;

import java.io.*;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;



public class Game extends JFrame implements ActionListener {
    
	private static final long serialVersionUID = 1930219170076115789L;
	
	private static double size_x;
    private static double size_y;
    
    private static double scale;

    GameState_ gameState;
    GameState_ lastGameState;
    
    private static int highestUnlockedLevel;
    
    private Font spaceFont;
    private Font normalFont;
    
    private Landing landing;
    private Menu menu;
    private HighscoreCard highscoreCard;
    private HighscoreSave highscoreSave;
    private Gameboard level1;
    private Gameboard level2;
    private Gameboard level3;
    private Gameboard level4;
    
    private Timer timer;
        
    public Game() {
    	loadHighscore();
        addKeyListener(new TAdapter());
        scale = 1.0;
        
        size_x = 830*scale;
        size_y = 830*scale;
        if(highscoreSave != null) highestUnlockedLevel = highscoreSave.getHighestUnlockedLevel();
        else highestUnlockedLevel = 1;
        spaceFont = null;
        try {
            spaceFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/main/font.ttf")).deriveFont(Font.PLAIN, 22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(spaceFont);
        }
        catch(Exception e) {
            System.out.println("No pretty font for you");
        }
        
        landing = new Landing(scale, normalFont);
        landing.setFont(spaceFont);
        gameState = GameState_.LANDING;     
        lastGameState = gameState;
        add(landing);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int)size_x, (int)size_y);
        setLocationRelativeTo(null);
        setTitle("Space");
        setResizable(false);
        setVisible(true);
        
        timer = new Timer(1000/60, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(gameState != lastGameState) {
            switch(lastGameState) {
                case LANDING:
                    remove(landing);
                    scale = landing.getScale();
                    add(menu = new Menu(scale, highestUnlockedLevel));
                    menu.setFont(spaceFont);
                case MENU:
                    switch(gameState) {
                        case LEVEL1:
                            remove(menu);
                            add(level1 = new Gameboard(1, menu.getShip(), scale));
                            level1.requestFocusInWindow();
                            level1.setFont(spaceFont);
                            break;
                        case LEVEL2:
                            remove(menu);
                            add(level2 = new Gameboard(2, menu.getShip(),scale));
                            level2.requestFocusInWindow();
                            level2.setFont(spaceFont);
                            break;
                        case LEVEL3:
                            remove(menu);
                            add(level3 = new Gameboard(3, menu.getShip(), scale));
                            level3.requestFocusInWindow();
                            level3.setFont(spaceFont);
                            break;
                        case LEVEL4:
                            remove(menu);
                            add(level4 = new Gameboard(4, menu.getShip(), scale));
                            level4.requestFocusInWindow();
                            level4.setFont(spaceFont);
                            break;
                        case HIGHSCORE:
                        	remove(menu);
                        	add(highscoreCard = new HighscoreCard(scale));
                        	highscoreCard.addKeyListener(new TAdapter());
                        	highscoreCard.requestFocusInWindow();
                        	highscoreCard.setFont(spaceFont);
					case CREDITS:
						break;
					case LANDING:
						break;
					case MENU:
						break;
					default:
						break;
                    }
                    break;
                case HIGHSCORE:
                	remove(highscoreCard);
                	add(menu = new Menu(scale, highestUnlockedLevel));
                	menu.setFont(spaceFont);
                	requestFocusInWindow();
                	break;
                case LEVEL1:
                	if(level1.finishedLevel() && highestUnlockedLevel < 2 ) highestUnlockedLevel = 2;                   
                    add(menu = new Menu(scale, highestUnlockedLevel));
                    remove(level1);
                    level1.stahp();
                    menu.setFont(spaceFont);      
                    requestFocusInWindow();
                    break;
                case LEVEL2:                    
                	if(level2.finishedLevel() && highestUnlockedLevel < 3 ) highestUnlockedLevel = 3;                   
                    add(menu = new Menu(scale, highestUnlockedLevel));  
                    remove(level2);
                    level2.stahp();
                    menu.setFont(spaceFont);      
                    requestFocusInWindow();
                    break;
                case LEVEL3:                   
                	if(level3.finishedLevel() && highestUnlockedLevel < 4 ) highestUnlockedLevel = 4;                   
                    add(menu = new Menu(scale, highestUnlockedLevel));  
                    remove(level3);
                    level3.stahp();
                    menu.setFont(spaceFont);      
                    requestFocusInWindow();
                    break;
                case LEVEL4:
                    level4.stahp();
                    remove(level4);
                    add(menu = new Menu(scale, highestUnlockedLevel));
                    menu.setFont(spaceFont);      
                    requestFocusInWindow();
                    break;
			case CREDITS:
				break;
			default:
				break;
            }
            lastGameState = gameState;
        }
        switch(gameState) {
            case LANDING: 
                landing.move();
                if(landing.goToMenu()) {
                    gameState = GameState_.MENU;
                    size_x *= landing.getScale();
                    size_y *= landing.getScale();
                    setSize((int)size_x, (int)size_y);
                }
                break;
            case MENU:
                menu.move();
                if(menu.startGame()) {
                    switch(menu.getLevel()) {
                        case 1:
                            gameState = GameState_.LEVEL1;
                            break;
                        case 2:
                            gameState = GameState_.LEVEL2;
                            break;
                        case 3:
                            gameState = GameState_.LEVEL3;
                            break;
                        case 4:
                            gameState = GameState_.LEVEL4;
                            break;
                        case 66:
                        	gameState = GameState_.HIGHSCORE;
                        	break;
                    }
                }
                break;
            case HIGHSCORE:
            	highscoreCard.move();
            	if(highscoreCard.goToMenu()) {
            		gameState = GameState_.MENU;
            	}
            	break;
            case LEVEL1:
                level1.move();
                if(level1.finishedLevel() || level1.cancelledLevel()) gameState = GameState_.MENU;
                break;
            case LEVEL2:
                level2.move();
                if(level2.finishedLevel() || level2.cancelledLevel()) gameState = GameState_.MENU;
                break;
            case LEVEL3: 
                level3.move();
                if(level3.finishedLevel() || level3.cancelledLevel()) gameState = GameState_.MENU;
                break;
            case LEVEL4:
                level4.move();
                if(level4.finishedLevel() || level4.cancelledLevel()) gameState = GameState_.MENU;
                break;
            default:
            	break;            
        }
        repaint();
    }    
    
    public void loadHighscore() {
    	try{
        	FileInputStream fileIn = new FileInputStream("Space_saveFile.ser");
        	ObjectInputStream in = new ObjectInputStream(fileIn);
        	highscoreSave = (HighscoreSave) in.readObject();
        	highscoreSave.updateHighscore();
        	in.close();
        	fileIn.close();
    	} catch (Exception i) {
    		i.printStackTrace();
    	}
    }
    
    public static int getHighestUnlockedLevel() {
    	return highestUnlockedLevel;
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
    public static int get_size_x()
    {
        return (int)size_x;
    }
    public static int get_size_y()
    {
        return (int)size_y;
    }
      
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            if(gameState == GameState_.LANDING) landing.keyReleased(e);
            if(gameState == GameState_.MENU && menu != null) menu.keyReleased(e);
            if(gameState == GameState_.HIGHSCORE && highscoreCard != null) highscoreCard.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            if(gameState == GameState_.LANDING) landing.keyPressed(e);
            if(gameState == GameState_.MENU && menu != null) menu.keyPressed(e);
            if(gameState == GameState_.HIGHSCORE && highscoreCard != null) highscoreCard.keyPressed(e);

        }
    }
}