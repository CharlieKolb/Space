package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Color;
//import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;


public class Gameboard extends JPanel {

	private static final long serialVersionUID = 4066457437335181101L;
	private Actor player;
    private int playerShipHitboxExtra;

    private DeathcardMenu deathcard;
    private int deathcardCounter;
    private PauseMenu pauseMenu;
    private EndcardMenu endcard;
    
    private Actor boss;
    
    private Actor[] minion;
    private int amountOfMinions;
    private int minionSpawn;
    private int lastMinionSpawn;
    private boolean spawnedNextWave;
    
    private Actor[] despawnMinion;
    private int[] despawnMinionCounter;
    
    private Actor[] obstacle;
    private Actor[] despawnObstacle;
    private int[] despawnObstacleCounter;
    private int[] obstacleType;
    private int amountOfObstacles;
    
    private Projectile[] projectiles;
    private int lastProjectilePosition;
    
    private Explosion[] explosion;
    private double explosionDelay;
    
    Graphics2D g2d;
    private double fadeOutTransparency;
    
    private GameGraphics GameGraphics;
    
    private int level;
    private int ship;
    
    private int bossHitbox;
    
    private int highscore;
    private int combo;
    private int time;	//time in seconds
    private int frameCounter;
    
    Random random = new Random();
    
    private Background background0;
    private Background background1;
    private Background background2;
    private Background background3;
    private Background background4;
    
    private InputHandler inputHandler;
    private Input input;
    private int inputValue;
    
    private boolean isPaused;
    private boolean finishedLevel;
    private boolean beatLevel;
    private boolean cancelledLevel;
    
    private boolean spawnedBoss;
    
    private boolean wasPaused;
    
    private int pauseCooldown;
    
    private double scale;	//Reduces windowsize if window is too big
    
    GameSound backgroundSound;
    Thread a1;
    
	GameSound bossFight2 = new GameSound(3, 0, -20);
	Thread a2;
    
    
    public Gameboard(int Level, int playerShip, double scale) {
        input = new Input();
        inputHandler = new InputHandler();
        addKeyListener(input);
        addMouseListener(input.getMouseListener());
        addMouseMotionListener(input.getMouseMotionListener());
        setFocusable(true);
        setDoubleBuffered(true);
        
        deathcard = new DeathcardMenu();
        pauseMenu = new PauseMenu();
        endcard = new EndcardMenu(Level, playerShip);
        
        GameGraphics = new GameGraphics();
        
        level = Level;
        
        backgroundSound = new GameSound(0, (int) (Math.random()*6), -20);
        backgroundSound.loopMusic();
        a1 = new Thread(backgroundSound);
        a1.start();
        
        background0 = new Background(0, level, true);
        background1 = new Background(1, level, true);
        background2 = new Background(2, level, true);
        background3 = new Background(3, level, false);
        background4 = new Background(4, level, false);      
        
        this.scale = scale;
        fadeOutTransparency = 0;
        
        player = new Player(playerShip);
        ship = playerShip;
        playerShipHitboxExtra = 30;

        spawnMinions();
        spawnObstacles();
        projectiles = new Projectile[100];
        lastProjectilePosition = 0;
        
        explosion = new Explosion[25];
        
        
        
        
    }
    
    public void move() {
    	if(player.getLifepoints() <= 0) {
    		input.disableMouse();
    		if(deathcard.getBackToMenu()) cancelledLevel = true;
    		else {
    			deathcardCounter++;
    			moveEndcard();
    			moveProjectiles();
    			moveObstacles();
    			if(deathcardCounter > 240) {
    				deathcard.showScreen();
        			deathcard.move(input.getInput());
    			}
    			for(int i = 0; i < 22; i++) {
    				if(explosion[i] != null) explosion[i].move();
    			}
    			repaint();
    		}
    	}
    	else if(!isPaused && !beatLevel) {
			frameCounter++;
			if(frameCounter % 60 == 0) time++;
			input.move(player.getX(), player.getX() + GameGraphics.getWidth(0, player.getImage()));
	        inputValue = input.getInput();
	        /*
	         * Player Input
	         */
	        Command playerCommand = inputHandler.handleInput(inputValue);
	        if(inputValue == 9 && !wasPaused) {
	        	isPaused = true;
	        }
	        if(playerCommand != null) {
	            playerCommand.execute(player); 
	        }
	        /* 
	         *  Logik
	         */
	        if(level == 4) bossHitbox = 50 + (int) (200*Math.random());
	        
	        if(pauseCooldown > 0) pauseCooldown -= 1;
	        else wasPaused = false;

	        moveBackground();
	        moveActors();
	        moveProjectiles();
	        createProjectiles();
	        hitDetection();
	        spawnNextWave();
	        spawnNewObstacles();
	        for(int i = 0; i < 25; i++) {
				if(explosion[i] != null) {
					explosion[i].move();
					if(explosion[i].getDuration() < 10) {
						if(despawnMinion[explosion[i].getArrayNumber()] != null) {
							explosion[i] = new Explosion(
									explosion[i].getOX(), explosion[i].getOY(), explosion[i].getArrayNumber());
							Thread e10 = new Thread(new GameSound(4, 0, -20));;
		                    e10.start();
						}
						else explosion[i] = null;
					}
				}
			}
	        /*
	         * Grafik
	         */
	        repaint();
		}
		else if(beatLevel) {
			input.disableMouse();
			if(endcard.getBackToMenu()) finishedLevel = true;	
			for(int i = 0; i < 25; i++) {
				if(explosion[i] != null) {
					explosion[i].move();
					if(explosion[i].getDuration() < 10) {
						if(despawnMinion[explosion[i].getArrayNumber()] != null) {
							explosion[i] = new Explosion(
									explosion[i].getOX(), explosion[i].getOY(), explosion[i].getArrayNumber());
							Thread e10 = new Thread(new GameSound(4, 0, -20));;
		                    e10.start();
						}
						else explosion[i] = null;
					}
				}
			}
			if(player.getY() <= 300) {
				if(time > 0) {
					highscore += 0.01 * time;
					time -= 0.01 * time; 
				}
				endcard.showScreen();
				endcard.move(input.getInput(), highscore, time);
			}
			else {
				moveBackground();
				moveActors();
				moveProjectiles();
				repaint();
			}
		}
		else if(isPaused) {
			input.disableMouse();
			pauseMenu.move(input.getInput());
			if(pauseMenu.getBackToGame()) {
				isPaused = false;
				wasPaused = true;
				pauseCooldown = 10;
			}
			else if(pauseMenu.getBackToMenu()) cancelledLevel = true;
		}
	    if(minionSpawn == amountOfMinions && boss == null && !beatLevel) {
	    	beatLevel = true;   
	    	Highscore.addScore(ship, level, endcard.getHighscore());
	    	fadeOutTransparency = 1;
	    }
	}
    
	public void stahp() {
		if(a1 != null) a1.interrupt();
		if(a2 != null) a2.interrupt();
    }
    
    public void spawnMinions() {
        // minion(type, x, y, delay, maxY)
        switch(level) {
            case 1: 
                amountOfMinions = 17;
                minion = new Minion[amountOfMinions];
                despawnMinion = new Minion[amountOfMinions];
                despawnMinionCounter = new int[amountOfMinions];
                minion[0] = new Minion(1, 100, 5, 1200, 345); 
                minion[1] = new Minion(2, 300, 5, 1200, 340);               
                minion[2] = new Minion(1, 500, 5, 1200, 335);
                break;
            case 2:
            	amountOfMinions = 27;
                minion = new Minion[amountOfMinions];
                despawnMinion = new Minion[amountOfMinions];
                despawnMinionCounter = new int[amountOfMinions];
                minion[0] = new Minion(11, 100, 5, 400, 335); 
                minion[1] = new Minion(12, 200, 5, 400, 340);               
                minion[2] = new Minion(13, 350, 5, 50, 230);
                minion[3] = new Minion(13, 450, 5, 50, 230); 
                minion[4] = new Minion(12, 600, 5, 400, 340);               
                minion[5] = new Minion(11, 700, 5, 400, 335);
                break;
            case 3:
            	amountOfMinions = 23;
            	minion = new Minion[amountOfMinions];
                despawnMinion = new Minion[amountOfMinions];
                despawnMinionCounter = new int[amountOfMinions];
                minion[0] = new Minion(21, 150, 5, 100, 230);
                minion[1] = new Minion(21, 700, 5, 100, 230);
                minion[2] = new Minion(22, 50, 5, 200, 320);
                minion[3] = new Minion(22, 150, 5, 200, 320);
                minion[4] = new Minion(22, 650, 5, 200, 320);
                minion[5] = new Minion(22, 750, 5, 200, 320);
                break;
            case 4:
            	amountOfMinions = 14;
                minion = new Minion[amountOfMinions];
                despawnMinion = new Minion[amountOfMinions];
                despawnMinionCounter = new int[amountOfMinions];
                minion[0] = new Minion(35, 200, 5, 300, 240);   
                minion[1] = new Minion(35, 300, 5, 100, 290);
                minion[2] = new Minion(35, 500, 5, 100, 290);
                minion[3] = new Minion(35, 600, 5, 300, 240);
                minion[4] = new Minion(33, 400, 5, 900, 300);
                break;
            	
            	
        }
    }
    
    public void spawnNextWave() {
        switch(level) {
	        case 1: 
		        if(!spawnedNextWave) {
		            switch(minionSpawn) {
		                case 3:
		                    spawnedNextWave = true;
		                    minion[3] = new Minion(5, 150, 5, 60, 300); 
		                    minion[4] = new Minion(4, 365, 5, 0, 350);
		                    minion[5] = new Minion(5, 550, 5, 60, 300); 
		                    break;
		                case 6:
		                    spawnedNextWave = true;
		                    minion[6] = new Minion(1, 50, 5, 0, 400);
		                    minion[7] = new Minion(1, 650, 5, 0, 400);
		                    minion[8] = new Minion(2, 150, 5, 400, 300);
		                    minion[9] = new Minion(2, 550, 5, 400, 300);
		                    minion[10] = new Minion(3, 350, 5, 800, 215);
		                    break;
		                case 11:
		                    spawnedNextWave = true;
		                    minion[11] = new Minion(5, 250, 5, 0, 215);
		                    minion[12] = new Minion(5, 250, 5, 0, 300);
		                    minion[13] = new Minion(5, 250, 5, 0, 400);
		                    minion[14] = new Minion(5, 300, 5, 0, 250);
		                    minion[15] = new Minion(5, 300, 5, 0, 350);
		                    minion[16] = new Minion(5, 300, 5, 0, 220);
		                    break;
		            }
		        }
		        break;
	        case 2: 
	        	if(!spawnedNextWave) {
	        		switch(minionSpawn) {
	        			case 6:
	        				spawnedNextWave = true;
	        				minion[6] = new Minion(15, 50, 5, 10, 320);
	        				minion[7] = new Minion(14 , 120, 5, 50, 220);
	        				minion[8] = new Minion(15, 250, 5, 300, 320);
	        				minion[9] = new Minion(15, 550, 5, 300, 320);
	        				minion[10] = new Minion(14, 670, 5, 50, 220);
	        				minion[11] = new Minion(15, 730, 5, 10, 320);
	        				break;
	        			case 12:
	        				spawnedNextWave = true;
	        				minion[12] = new Minion(11, 50, 5, 0, 225);
	        				minion[13] = new Minion(11, 50, 5, 430, 225);
	        				minion[14] = new Minion(13, 250, 5, 250, 320);
	        				minion[15] = new Minion(15, 300, 5, 10, 270);
	        				minion[16] = new Minion(15, 400, 5, 10, 270);
	        				minion[17] = new Minion(13, 530, 5, 250, 320);
	        				minion[18] = new Minion(11, 730, 5, 430, 225);
	        				minion[19] = new Minion(11, 730, 5, 0, 225);
	        				break;
	        			case 20:
	        				spawnedNextWave = true;
	        				minion[20] = new Minion(11, 50, 5, 0, 210);
	        				minion[21] = new Minion(11, 150, 5, 0, 210);
	        				minion[22] = new Minion(11, 250, 5, 0, 210);
	        				minion[23] = new Minion(11, 350, 5, 0, 210);
	        				minion[24] = new Minion(11, 450, 5, 0, 210);
	        				minion[25] = new Minion(11, 550, 5, 0, 210);
	        				minion[26] = new Minion(11, 650, 5, 0, 210);
	        				break;
	        				
	        		}
	        	}
	        	break;
	        case 3: 
	        	if(!spawnedNextWave) {
	        		switch(minionSpawn) {
		        		case 6:
		        			spawnedNextWave = true;
		        			minion[6] = new Minion(25, 600, 5, 300, 210);
		        			minion[7] = new Minion(25, 200, 5, 300, 210);
		        			minion[8] = new Minion(23, 100, 5, 500, 380);
		        			minion[9] = new Minion(23, 400, 5, 500, 390);
		        			minion[10] = new Minion(23, 700, 5, 500, 380);       			
		        			break;
		        		case 10:
		        			minion[11] = new Minion(23, 30, 5, 0, 260);
		        			minion[12] = new Minion(23, 400, 5, 0, 240);
		        			minion[13] = new Minion(23, 770, 5, 0, 260);
		        			minion[14] = new Minion(24, 200, 5, 0, 400);
		        			minion[15] = new Minion(24, 600, 5, 0, 400);
		        			break;
		        		case 15:
		        			minion[16] = new Minion(21, 20, 5, 100, 210);
		        			minion[17] = new Minion(21, 70, 5, 100, 270);
		        			minion[18] = new Minion(21, 720, 5, 100, 270);
		        			minion[19] = new Minion(21, 770, 5, 100, 210);
		        			minion[20] = new Minion(22, 300, 5, 0, 380);
		        			minion[21] = new Minion(22, 500, 5, 0, 380);
		        			minion[22] = new Minion(23, 400, 5, 400, 220);
		        			break;

	        		}
	        	}
	        	break;
	        case 4: 
	        	if(!spawnedNextWave) {
	        		switch(minionSpawn) {
		        		case 5:
		        			spawnedNextWave = true;
		        			minion[5] = new Minion(34, 200, 5, 0, 400);
		        			minion[6] = new Minion(34, 400, 5, 0, 400);
		        			minion[7] = new Minion(32, 200, 5, 100, 240);
		        			minion[8] = new Minion(32, 400, 5, 100, 240);
		        			break;
		        		case 9: 
		        			spawnedNextWave = true;
		        			minion[9] = new Minion(33, 100, 5, 0, 320);
		        			minion[10] = new Minion(33, 500, 5, 0, 320);
		        			break;
		        		case 11:
		        			spawnedNextWave = true;
		        			minion[11] = new Minion(35, 222, 5, 0, 275);
		        			minion[12] = new Minion(35, 444, 5, 0, 275);
		        			minion[13] = new Minion(33, 333, 5, 300, 220);
		        			break;
		        		case 14:
		        			spawnedNextWave = true;
		                	/*
		                	 * Canceles normal track and starts bosstrack
		                	 */
		        			a1.interrupt();

		        			if(!spawnedBoss) {
		        				bossFight2.loopMusic();
		        				a2 = new Thread(bossFight2); 
		        				a2.start();
		        			}
		        			if(boss == null && !spawnedBoss) boss = new Boss();
		        			spawnedBoss = true;
		        			break;
	        		}
	        	}
	        	break;
        }
        if(lastMinionSpawn != minionSpawn) {
        	spawnedNextWave = false;
            lastMinionSpawn = minionSpawn;
        }
    }

    public void moveEndcard() {
    	if(explosionDelay > 0) explosionDelay--;
		if(deathcardCounter < 120 && explosionDelay == 0) {
			for(int i = 0; i < 22; i++) {
				if(explosion[i] == null) {
					explosion[i] = new Explosion(player.getX(), player.getY(), i); 
					Thread e5 = new Thread(new GameSound(4, 0, -20));;
                    e5.start();
					explosionDelay = 30 - deathcardCounter/8;
					break;
				}
			}
		}
		
		else if(deathcardCounter < 180 && explosionDelay == 0) {
			for(int i = 0; i < 22; i++) {
				if(explosion[i] == null && explosion[i+1] == null) {
					explosion[i] = new Explosion(player.getX(), player.getY(), i); 
					explosion[i+1] = new Explosion(player.getX(), player.getY(), i); 
					explosionDelay = 30 - deathcardCounter/8;
					break;
				}
			}
		}
		else if(explosionDelay == 0) {
			for(int i = 0; i < 22; i++) {
				if(explosion[i] == null && explosion[i+1] == null && explosion[i+2] == null) {
					explosion[i] = new Explosion(player.getX(), player.getY(), i); 
					Thread e7 = new Thread(new GameSound(4, 0, -20));
                    e7.start();
					explosionDelay = 30 - deathcardCounter/8;
					break;
				}
			}
		}		   					    	
    }

    public void spawnObstacles() {
        switch(level) {
            case 1: 
                amountOfObstacles = 10;
                obstacle = new Obstacle[amountOfObstacles];
                obstacleType = new int[amountOfObstacles];
                despawnObstacle = new Obstacle[amountOfObstacles];
                despawnObstacleCounter = new int[amountOfObstacles];
                obstacle[0] = new Obstacle(1, random.nextInt(200) + 200, 5, 2000);
                obstacle[1] = new Obstacle(1, random.nextInt(600), 5, 2450);
                obstacle[2] = new Obstacle(1, random.nextInt(500) + 100, 5, 2200);
                obstacle[3] = new Obstacle(1, random.nextInt(800), 5, 2300);
                obstacle[4] = new Obstacle(1, random.nextInt(800), 5, 2200);
                obstacle[5] = new Obstacle(1, random.nextInt(800), 5, 3200);
                obstacle[6] = new Obstacle(1, random.nextInt(800), 5, 2650);
                obstacle[7] = new Obstacle(1, random.nextInt(800), 5, 2720);
                obstacle[8] = new Obstacle(1, random.nextInt(800), 5, 2810);
                obstacle[9] = new Obstacle(1, random.nextInt(800), 5, 2120);
                for(int i = 0; i < amountOfObstacles; i++) {
                	obstacleType[i] = obstacle[i].getType();
                }
                break;
            case 2:
                amountOfObstacles = 5;
                obstacle = new Obstacle[amountOfObstacles];
                obstacleType = new int[amountOfObstacles];
                despawnObstacle = new Obstacle[amountOfObstacles];
                despawnObstacleCounter = new int[amountOfObstacles];
                obstacle[0] = new Obstacle(1, random.nextInt(300) + 200, 5, 0);
                obstacle[1] = new Obstacle(1, random.nextInt(600), 5, 450);
                obstacle[2] = new Obstacle(1, random.nextInt(500) + 100, 5, 200);
                obstacle[3] = new Obstacle(1, random.nextInt(800), 5, 300);
                obstacle[4] = new Obstacle(1, random.nextInt(800), 5, 200);
                for(int i = 0; i < amountOfObstacles; i++) {
                	obstacleType[i] = obstacle[i].getType();
                }
                break;
            case 3:
            	amountOfObstacles = 8;
                obstacle = new Obstacle[amountOfObstacles];
                despawnObstacle = new Obstacle[amountOfObstacles];
                despawnObstacleCounter = new int[amountOfObstacles];
                obstacleType = new int[amountOfObstacles];
            	obstacle[0] = new Obstacle(1, random.nextInt(200) + 200, 5, 0);
                obstacle[1] = new Obstacle(1, random.nextInt(600), 5, 450);
                obstacle[2] = new Obstacle(1, random.nextInt(500) + 100, 5, 200);
                obstacle[3] = new Obstacle(1, random.nextInt(800), 5, 300);
                obstacle[4] = new Obstacle(1, random.nextInt(800), 5, 200);
                obstacle[5] = new Obstacle(2, random.nextInt(800), 5, 300);
                obstacle[6] = new Obstacle(2, random.nextInt(800), 5, 200);
                obstacle[7] = new Obstacle(2, random.nextInt(800), 5, 300);
                for(int i = 0; i < amountOfObstacles; i++) {
                	obstacleType[i] = obstacle[i].getType();
                }
                break;
                
            	
        }
        
    }
   
    public void spawnNewObstacles() {
        for(int i = 0; i < amountOfObstacles; i++) {
            if(obstacle[i] == null && despawnObstacle[i] == null && !beatLevel && player.getLifepoints() >= 0) obstacle[i] = new Obstacle(obstacleType[i], random.nextInt(800), 5, random.nextInt(1000));
        }       
    }
    
    public void hitDetection() {
    	if(!beatLevel) {
	        for(int h=0; h < amountOfObstacles; h++) {                        //Player shoots Obstacle
	            if(obstacle[h] != null) {
	                int oX = obstacle[h].getX();
	                int oY = obstacle[h].getY();
	                int oXW = oX + GameGraphics.getWidth(13, obstacle[h].getImage());
	                int oYH = oY + GameGraphics.getHeight(13, obstacle[h].getImage());
	                
	                for(int k=0; k < lastProjectilePosition; k++) { 
	                    if(projectiles[k] != null && projectiles[k].getActor() == player) {
	                        int pX = projectiles[k].getX();
	                        int pY = projectiles[k].getY();
	                        int pXW = pX + GameGraphics.getWidth(10, projectiles[k].getImage());
	                        //int pYH = pY + GameGraphics.getHeight(10, projectiles[k].getImage());
	                   
	                        if(pY <= oYH &&
	                           pY >= oY &&
	                           pX >= oX &&
	                           pX <= oXW ||
	                           pY <= oYH &&
	                           pY >= oY &&
	                           pXW >= oX && 
	                           pXW <= oXW) {
	                            if(!projectiles[k].wasUsed()) {
	                                obstacle[h].gotHit(player);
	                                if(obstacle[h].getLifepoints() <= 0) {
	                                	if(despawnObstacle[h] == null) {
	                            	    	despawnObstacle[h] = obstacle[h];
	                            	    	despawnObstacleCounter[h] = 40;
	                            	    }
	                            	    obstacle[h] = null;
	                            	    
	                                }
	                                Thread e12 = new Thread(new GameSound(4, 0, -20));;
                                    e12.start();
	                                projectiles[k].hit();
	                                projectiles[k].despawn(GameGraphics.getHeight(10, projectiles[k].getImage()), GameGraphics.getWidth(10, projectiles[k].getImage()), 
	                                                       GameGraphics.getHeight(10, projectiles[k].getImage() + 1), GameGraphics.getWidth(10, projectiles[k].getImage() + 1));
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        for(int i=0; i < amountOfMinions; i++) {                            //Player shoots Minion
	            if(minion[i] != null) {
	                int mX = minion[i].getX();
	                int mY = minion[i].getY();
	                int mXW = mX + GameGraphics.getWidth(1, minion[i].getImage());
	                int mYH = mY + GameGraphics.getHeight(1, minion[i].getImage());
	                
	                for(int k=0; k < lastProjectilePosition; k++) { 
	                    if(projectiles[k] != null && projectiles[k].getActor() == player) {
	                        int pX = projectiles[k].getX();
	                        int pY = projectiles[k].getY();
	                        int pXW = pX + GameGraphics.getWidth(10, projectiles[k].getImage());
	                        //int pYH = pY + GameGraphics.getHeight(10, projectiles[k].getImage());
	                   
	                        if(pY <= mYH &&
	                           pY >= mY &&
	                           pX >= mX &&
	                           pX <= mXW ||
	                           pY <= mYH &&
	                           pY >= mY &&
	                           pXW >= mX && 
	                           pXW <= mXW) {
	                            if(!projectiles[k].wasUsed()) {
	                                minion[i].gotHit(player);
	                                if(minion[i].getLifepoints() <= 0) {                              
	                                    combo++;
	                                    highscore += minion[i].getScore() * combo;
	                                    destroyMinion(i);
	                                    minionSpawn++;
	                                }
	                                else {
	                                	Thread e1 = new Thread(new GameSound(4, 0, -20));;
	                                    e1.start();
	                                }
	                                projectiles[k].hit();
	                                projectiles[k].despawn(GameGraphics.getHeight(10, projectiles[k].getImage()), GameGraphics.getWidth(10, projectiles[k].getImage()), 
	                                                       GameGraphics.getHeight(10, projectiles[k].getImage() + 1), GameGraphics.getWidth(10, projectiles[k].getImage() + 1));
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        for(int m=0; m <= lastProjectilePosition; m++) {            //Actor shoots player
	            if(projectiles[m] != null && projectiles[m].getActor() != player) {
	                int plX = player.getX();
	                int plY = player.getY() + playerShipHitboxExtra;
	                int plXW = plX + GameGraphics.getWidth(0, player.getImage());
	                int plYH = plY - playerShipHitboxExtra + GameGraphics.getHeight(0, player.getImage());
	                
	                int pX = projectiles[m].getX();
	                int pY = projectiles[m].getY();
	                int pXW = pX + GameGraphics.getWidth(10, projectiles[m].getImage());
	                int pYH = pY + GameGraphics.getHeight(10, projectiles[m].getImage()) - 10;
	                if(pYH > plY && 
	                   plY <= pYH && 
	                   (plYH >= pYH  || (plY > pY && plYH < pYH))&&
	                   plX <= pX &&
	                   plXW >= pX ||
	                   plY <= pYH && 
	                   (plYH >= pYH || (plY > pY && plYH < pYH)) &&
	                   plX <= pXW &&
	                   plXW >= pXW) {              
	                       if(!projectiles[m].wasUsed()) {
	                           player.gotHit(projectiles[m].getActor());
	                           if(projectiles[m].getActor().getClass() != Boss.class) {
	                        	   Thread e3 = new Thread(new GameSound(4, 0, -10));
	                        	   e3.start();
	                           }
	                           
	                           combo = 0;
	                           projectiles[m].hit();
	                           if(projectiles[m].getActor().getClass() != Boss.class) projectiles[m].despawn(-GameGraphics.getHeight(10, projectiles[m].getImage()), GameGraphics.getWidth(10, projectiles[m].getImage()), 
	                           -GameGraphics.getHeight(10, projectiles[m].getImage() + 1), GameGraphics.getWidth(10, projectiles[m].getImage() + 1));
	                       }                  
	                }
	            }
	            if(boss != null) {	
	            	 int bX = boss.getX();
		             int bY = boss.getY();
		             //int bXW = bX + GameGraphics.getWidth(2, boss.getImage());
		             //int bYH = bY + GameGraphics.getHeight(2, boss.getImage());
		             int bXW = bX + GameGraphics.getWidth(2, 0);
		             int bYH = bY + GameGraphics.getHeight(2, 0);
		             
		             for(int k=0; k < lastProjectilePosition; k++) { 
		                 if(projectiles[k] != null && projectiles[k].getActor() == player) { //player hits boss
		                	 int pX = projectiles[k].getX();
		                   	 int pY = projectiles[k].getY();
		                     int pXW = pX + GameGraphics.getWidth(10, projectiles[k].getImage());
		                        //int pYH = pY + GameGraphics.getHeight(10, projectiles[k].getImage());
		                   
		                        if(pY <= bYH - bossHitbox &&
		                           pY >= bY &&
		                           pX >= bX &&
		                           pX <= bXW ||
		                           pY <= bYH - bossHitbox &&
		                           pY >= bY &&
		                           pXW >= bX && 
		                           pXW <= bXW) {
		                            if(!projectiles[k].wasUsed()) {
		                                boss.gotHit(player);
		                                if(boss.getLifepoints() <= 0) {
		                                	combo++;
		                                	highscore += boss.getScore() * combo;
		                                    boss = null;
		                                }
		                                projectiles[k].hit();
		                                projectiles[k].despawn(GameGraphics.getHeight(10, projectiles[k].getImage()), GameGraphics.getWidth(10, projectiles[k].getImage()), 
		                                                       GameGraphics.getHeight(10, projectiles[k].getImage() + 1), GameGraphics.getWidth(10, projectiles[k].getImage() + 1));
		                            }
		                        }
		                    }
		             }
	            }
		        for(int n=0; n < amountOfObstacles; n++) {          //Obstacle hits player
		            if(obstacle[n] != null) {
		                int plX = player.getX();
		                int plY = player.getY() + playerShipHitboxExtra;
		                int plXW = plX + GameGraphics.getWidth(0, player.getImage());
		                int plYH = plY - playerShipHitboxExtra + GameGraphics.getHeight(0, player.getImage());
		                
		                int oX = obstacle[n].getX();
		                int oY = obstacle[n].getY();
		                int oXW = oX + GameGraphics.getWidth(13, obstacle[n].getImage());
		                int oYH = oY + GameGraphics.getHeight(13, obstacle[n].getImage());
		                
		                if(oYH > plY && 
		                   plY <= oYH && 
		                   plYH >= oYH &&
		                   plX <= oX &&
		                   plXW >= oX ||
		                   plY <= oYH && 
		                   plYH >= oYH &&
		                   plX <= oXW &&
		                   plXW >= oXW) {                
		                       player.gotHit(obstacle[n]);
                               Thread e11 = new Thread(new GameSound(4, 0, -10));;
                                   e11.start();
                               
		                       if(combo > 0) combo -= 1;
		                       obstacle[n] = null;
		                }                     
		           	}
		        }
	        }
    	} 
    }
    
    public void moveBackground() {
        background0.move();
        background1.move();
        background2.move();
        background3.move();
        background4.move();
    }
    
    public void moveActors() {
        player.move(GameGraphics.getWidth(0, player.getImage()), GameGraphics.getHeight(0, player.getImage()), beatLevel);        
        for(int i = 0; i < amountOfMinions; i++) {
            if(minion[i] != null) {
                minion[i].move(GameGraphics.getWidth(1, minion[i].getImage()), GameGraphics.getHeight(1, minion[i].getImage()), beatLevel);      
            }
            if(despawnMinion[i] != null) {
            	if(despawnMinionCounter[i]-- == 0) {
            		despawnMinion[i] = null;
            	}
            }
        }
         
        if(boss != null && level == 4) {
        	boss.move(GameGraphics.getHeight(2, boss.getImage()), GameGraphics.getWidth(2, boss.getImage()), beatLevel);
        }
        
        moveObstacles();
    }
    
    public void moveObstacles() {
    	if(player.getLifepoints() > 0) {
	    	for(int i = 0; i < amountOfObstacles; i++) {
	            if(obstacle[i] != null) {
	                obstacle[i].move(GameGraphics.getWidth(1, obstacle[i].getImage()), GameGraphics.getHeight(1, obstacle[i].getImage()), beatLevel);   
	                if(obstacle[i].getY() > 1000) obstacle[i] = null;
	            }
	            if(despawnObstacle[i] != null) {
	            	if(despawnObstacleCounter[i]-- <= 0) {
	            		despawnObstacle[i] = null;
	            		
	            	}
	            }
	        } 
    	}
    }

    public void moveProjectiles() {
        for(int i = 0; i <= lastProjectilePosition; i++) {
            if(projectiles[i] != null) {
                if(projectiles[i].issuedDespawn()) {
                    projectiles[i] = null;
                    break;
                }
                projectiles[i].move();
                if(projectiles[i].getTravelY() >= projectiles[i].getActor().getWeapon().getRange()) {
                    projectiles[i] = null;
                    if(i >= lastProjectilePosition) {
                        while(lastProjectilePosition  > -1) {
                            if(projectiles[lastProjectilePosition] != null) break;
                            lastProjectilePosition -= 1;
                        }
                    }                   
                }
            }
        }        
    }
    
    public void createProjectiles() {
        if(player.createProjectile()) {
           for(int i = 0; i <= lastProjectilePosition + 1; i++) {
               if(projectiles[i] == null) {
                   projectiles[i] = new Projectile(player.getWeapon().getProjectileA(), player, player.getX() + player.getDX() + player.getWeapon().getRelativeX(0), player.getY() + player.getDY() + player.getWeapon().getRelativeY(0));
                   if(lastProjectilePosition < i+1) lastProjectilePosition = i+1;
                   Thread b1 = new Thread(new GameSound(2, 0, -35));
                   b1.start();

                   player.createdProjectile(true);
                   break;
               }
           } 
           if(player.getWeapon().getAmountOfProjectiles() == 2) {              
               for(int k = 0; k <= lastProjectilePosition + 1; k++) {
                   if(projectiles[k] == null) {
                       projectiles[k] = new Projectile(player.getWeapon().getProjectileB(), player, player.getX() + player.getDX() + player.getWeapon().getRelativeX(1), player.getY() + player.getDY() + player.getWeapon().getRelativeY(1));
                       if(lastProjectilePosition < k) lastProjectilePosition = k;
                       break;
                   }
               }
           }
        }
        for(int j = 0; j < amountOfMinions; j++) {
            if(minion[j] != null) {
                if(minion[j].createProjectile()) {
                   for(int m = 0; m <= lastProjectilePosition + 1; m++) {
                       if(projectiles[m] == null) {
                           projectiles[m] = new Projectile(minion[j].getWeapon().getProjectileA(), minion[j], minion[j].getX() + minion[j].getDX() + minion[j].getWeapon().getRelativeX(0), minion[j].getY() + minion[j].getDY() + minion[j].getWeapon().getRelativeY(0));
                           if(lastProjectilePosition < m+1) lastProjectilePosition = m+1;
                           Thread b2 = new Thread(new GameSound(2, 0, -45));
                           b2.start();
                           minion[j].createdProjectile(true);
                           break;
                       }
                   } 
                   if(minion[j].getWeapon().getAmountOfProjectiles() > 1) {   
                       for(int n = 0; n <= lastProjectilePosition + 1; n++) {
                           if(projectiles[n] == null) {
                               projectiles[n] = new Projectile(minion[j].getWeapon().getProjectileB(), minion[j], minion[j].getX() + minion[j].getDX()+ minion[j].getWeapon().getRelativeX(1), minion[j].getY() + minion[j].getDY() + minion[j].getWeapon().getRelativeY(1));
                               if(lastProjectilePosition < n) lastProjectilePosition = n;
                               break;
                           }
                       }
                   }
                   if(minion[j].getWeapon().getAmountOfProjectiles() > 2) {
                       for(int o = 0; o <= lastProjectilePosition + 1; o++) {
                           if(projectiles[o] == null) {
                               projectiles[o] = new Projectile(minion[j].getWeapon().getProjectileC(), minion[j], minion[j].getX() + minion[j].getDX() + minion[j].getWeapon().getRelativeX(2), minion[j].getY() + minion[j].getDY()  + minion[j].getWeapon().getRelativeY(2));
                               if(lastProjectilePosition < o) lastProjectilePosition = o;
                               break;
                           }
                        }
                   }
                }
            }
        }
        if(boss != null) {
        	if(boss.createProjectile()) {
        		for(int p = 0; p <= lastProjectilePosition + 1; p++) {
                    if(projectiles[p] == null) {
                        projectiles[p] = new Projectile(boss.getWeapon().getProjectileA(), boss, boss.getX() + boss.getWeapon().getRelativeX(0), boss.getY() + boss.getWeapon().getRelativeY(0) );
                        if(lastProjectilePosition < p+1) lastProjectilePosition = p+1;
                        boss.createdProjectile(true);
                        break;
                    }
                } 
        	}
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        g2d = (Graphics2D)g;
        g2d.scale(scale,scale);
        g2d.setColor(Color.BLACK);
        //Draw moving background
        g2d.drawImage(main.GameGraphics.getImage(11, background0.getImage()), 0, background0.getY(), this);
        g2d.drawImage(main.GameGraphics.getImage(11, background1.getImage()), 0, background1.getY(), this);        
        g2d.drawImage(main.GameGraphics.getImage(11, background2.getImage()), 0, background2.getY(), this);        
        g2d.drawImage(main.GameGraphics.getImage(11, background3.getImage()), 0, background3.getY(), this);
        g2d.drawImage(main.GameGraphics.getImage(11, background4.getImage()), 0, background4.getY(), this);        
        
        
        
        if(boss != null && level == 4) g2d.drawImage(main.GameGraphics.getImage(2, boss.getImage()), boss.getX(), boss.getY(), this);//Boss
        
        
        for(int i = 0; i <= lastProjectilePosition; i++) {                                                         //Projectiles launched
            if(projectiles[i] != null && projectiles[i].getImage() % 2 == 1) {
                g2d.drawImage(main.GameGraphics.getImage(10, projectiles[i].getImage()), projectiles[i].getX(), projectiles[i].getY(), this);
            }
        }
        
        for(int i = 0; i < amountOfObstacles; i++) {                                                               //Obstacles
            if(obstacle[i] != null) {
            	g2d.drawImage(main.GameGraphics.getImage(13, obstacle[i].getImage()), obstacle[i].getX(), obstacle[i].getY(), this);
            }
            if(despawnObstacle[i] != null) {
            	g2d.drawImage(main.GameGraphics.getImage(13, despawnObstacle[i].getImage()), despawnObstacle[i].getX(), despawnObstacle[i].getY(), this);            	
            }
        }
        
        for(int i = 0; i < amountOfMinions; i++) {                                                                   //Minions
            if(minion[i] != null) {
            	g2d.drawImage(main.GameGraphics.getImage(1, minion[i].getImage()), minion[i].getX(), minion[i].getY(), this);
            	handleHpBar(minion[i]);
            }
            else if(despawnMinion[i] != null) {
            	g2d.drawImage(main.GameGraphics.getImage(1, despawnMinion[i].getImage()), despawnMinion[i].getX(), despawnMinion[i].getY(), this);            	
            }
        }
        
        if(player.isVisible()) g2d.drawImage(main.GameGraphics.getImage(0, player.getImage()), player.getX(), player.getY(), this);    //Player
        
        for(int i = 0; i <= lastProjectilePosition; i++) {                                                             //Projectiles hit
        	if(projectiles[i] != null && projectiles[i].getImage() % 2 == 0) {
        		g2d.drawImage(main.GameGraphics.getImage(10, projectiles[i].getImage()), projectiles[i].getX(), projectiles[i].getY(), this);
        	}    
        }
        
        for(int i = 0; i < 25; i++) {
        	if(explosion[i] != null && explosion[i].getDuration() > 0) {									//Explosions
        		if(player.getY() > 500)	g2d.drawImage(main.GameGraphics.getImage(14, 0), explosion[i].getX() - GameGraphics.getWidth(14, 0) / 2, explosion[i].getY() - GameGraphics.getHeight(14, 0) / 2, this);
        	}
        }
	        
        g2d.drawImage(main.GameGraphics.getImage(12, 0), 0, 0, this);                                                     //Hud_Background
        g2d.drawString("Current Heat: " + player.getWeapon().getHeat(), 10, 110);
        g2d.drawString("Maximum Heat: " + player.getWeapon().getMaxHeat(), 10, 130);
        g2d.drawString("Highscore: " + highscore, 10, 160);
        g2d.drawString("Combo: " + combo, 10, 180);
        if(!player.getWeapon().isAbleToShoot()) {
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        	g2d.setColor(Color.RED);
        	if(frameCounter % 30 < 15) g2d.drawString("WEAPON OVERHEATED", 250, 110);
        	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
        	g2d.setColor(Color.BLACK);
        	}
        g2d.drawImage(main.GameGraphics.getImage(12, ship+1), 5, 5, this);
        for(int i = 0; i < player.getMaxLifepoints(); i++) {
        	if(i < player.getLifepoints()) g2d.drawImage(main.GameGraphics.getImage(12, 6), 15 + (GameGraphics.getWidth(12, 6)*i) + 2*i, 41, this);
        	else g2d.drawImage(main.GameGraphics.getImage(12, 7), 15 + (GameGraphics.getWidth(12, 7)*i) + 2*i, 41, this);
        }
        
        drawTime(g2d);
        
        
        if(!input.useMouse()) g2d.drawString("Press M to activate mouse", 480, 183);
        else g2d.drawString("Press M to activate keyboard", 435, 183);
        g2d.drawString("Press Esc to pause the game", 448, 163);
        
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 50f));
        if(boss != null) g2d.drawString("Boss-HP: " + boss.getLifepoints(), 280, 110);
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));

        
        if(player.getY() < 700) {
        	if(fadeOutTransparency * 1.03 >= 255) g2d.setColor(new Color(0, 0, 0, 255));
        	else g2d.setColor(new Color(0, 0, 0, (int)(fadeOutTransparency *= 1.025)));
        	g2d.fillRect(0, 0, 1000, 1000);
        }
        if(player.getLifepoints() <= 0) {
        	deathcard.paint(g);    
        	g2d.setColor(new Color(0, 0, 0, 120));
        	g2d.fillRect(0, 0, 1000, 1000);
        }
        if(isPaused) {
        	pauseMenu.paint(g);
        	g2d.setColor(new Color(0, 0, 0, 70));
        	g2d.fillRect(0, 0, 1000, 1000);
        }
        if(beatLevel) endcard.paint(g);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void handleHpBar(Actor actor) {
    	double pixelPerHP = (double) (GameGraphics.getWidth(1, actor.getImage()) - actor.getMaxLifepoints()) / actor.getMaxLifepoints();
		int displayedPixelPerHP = (int)pixelPerHP;
		int additionalPixel = (int) ((pixelPerHP - (double)displayedPixelPerHP) * actor.getMaxLifepoints());
		int x = actor.getX() + additionalPixel / 4;
		int y = actor.getY() - 10;
		int currentLength = 0;
		int amountOfBlackBars = 1;
		int lifepoints = actor.getLifepoints();
    	
		g2d.drawImage(main.GameGraphics.getImage(3,0), x , y, this);
		currentLength++;
		amountOfBlackBars++;
		for(int i = 1; i<= actor.getMaxLifepoints(); i++) {
			while(currentLength - amountOfBlackBars < i * displayedPixelPerHP) {
				if(lifepoints < i) g2d.drawImage(main.GameGraphics.getImage(3,2), x + currentLength, y, this);
				else g2d.drawImage(main.GameGraphics.getImage(3,1), x + currentLength, y, this);
				currentLength++; 
			}
			g2d.drawImage(main.GameGraphics.getImage(3,0), x + currentLength, y, this);
			currentLength++;
			amountOfBlackBars++;
		}
    }
    
    public void drawTime(Graphics2D g2d) {
    	int tx = 380;
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40f));
    	if(time < 10) g2d.drawString("00:0" + time, tx, 50);
    	else if(time < 60) g2d.drawString("00:" + time, tx, 50);
    	else if(time / 60 < 10 && time % 60 > 9) g2d.drawString("0" + time/60 + ":" + time % 60, tx, 50);
    	else if(time / 60 < 10) g2d.drawString("0" + time/60 + ":0" + time % 60, tx, 50);
    	else if(time % 60 < 10) g2d.drawString(time / 60 + ":0" + time % 60, tx, 50);
    	else g2d.drawString(time / 60 + ":" + time % 60, tx, 50);
    	g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22f));
    }
    
    public void destroyMinion(int i) {
	    if(despawnMinion[i] == null) {
	    	despawnMinion[i] = minion[i];
	    	despawnMinionCounter[i] = 100;
	    	for(int k = 0; k < 24; k++) {
	    		if(explosion[k] == null && explosion[k+1] == null) {
	    			explosion[k] = new Explosion(minion[i].getX(), minion[i].getY(), i);
                    Thread e1 = new Thread(new GameSound(4, 0, -20));;
                    e1.start();
	    			explosion[k+1] = new Explosion(minion[i].getX(), minion[i].getY(), i);
	    			break;
	    		}
	    	}
	    }
	    minion[i] = null;
    }
    
    public double getScale() {
        return scale;
    }
        
    public boolean finishedLevel() {
        return finishedLevel;
    }
    
    public boolean cancelledLevel() {
    	return cancelledLevel;
    }
    
    public void reset() {
        spawnObstacles();
        spawnMinions();
        killProjectiles();
    }
    
    public void killProjectiles() {
        for(int i = 0; i <= lastProjectilePosition; i++) {
            if(projectiles[i] != null) projectiles[i] = null;
        }
    }

}