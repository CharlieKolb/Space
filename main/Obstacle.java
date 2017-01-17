package main;

public class Obstacle implements Actor {

    private int x;
    private int y;
    
    private int dx;
    private int dy;
    
    private int delay;
    
    private int maxLifepoints;
    private int lifepoints;
    
    private int damage;
    
    private int score;
    
    private int image;
    
    private int type;
    
    private boolean wasUsed;
    private boolean isDespawned;
    
    public Obstacle (int type, int x, int y, int delay) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.delay = delay;
        switch(type) {
            case 0:
                maxLifepoints = -1;
                lifepoints = -1;
                damage = 0;
                break;
            case 1:
            	score = 0;
                image = 1;
                maxLifepoints = 1;
                lifepoints = maxLifepoints;
                damage = 1;
                break;
            case 2:
            	score = 0;
            	image = 3;
            	maxLifepoints = 5;
            	lifepoints = maxLifepoints;
            	damage = 3;
            	break;
        }
    }
    
    public void move(int Width, int Height, boolean finishedLevel) {
	     if(!isDespawned) {
    		if(wasUsed) damage = 0;
	        moveDown();
	        x = x + dx;
	        delay -= dy;
	        if(delay <= 0 && (!finishedLevel || y > 200)) y = y + dy;
	     }
    }
   
    public void applyMovement(int Width, int Height) {
        
    }
    
    public Weapon getWeapon() {
        return null;
    }
    
    public int getX() {
        return x;
    }
     
    public int getY() {
        return y;
    }
    
    public int getDX() {
        return dx;
    }
    
    public int getDY() {
        return dy;
    }
    
    public int getImage() {
        return image;
    }
    
    public int getDamage() {
        x = 2000;
        y = 2000;
        dx = 0;
        dy = 0;       
        return damage;
    }
    
    public int getLifepoints() {
        return lifepoints;
    }
    
    public int getMaxLifepoints() {
    	return maxLifepoints;
    }
    
    public void gotHit(Actor actor) {
        lifepoints = lifepoints - actor.getDamage();
        if(lifepoints <= 0) {
            isDespawned = true;
            image++;
        }
    }
    
    public int getScore() {
    	return score;
    }
    
    public boolean createProjectile() {
        return false;
    }
    
    public void createdProjectile(boolean Boolean) {
    }
    
    public int getType() {
    	return type;
    }

    public void moveLeft() {
        switch(type) {
            case 1:
                dx = -3;
                break;
        }
    }
    
    public void moveRight() {
        switch(type) {
            case 1:
                dx = 3;
                break;
        }
    }
    
    public void moveDown() {
        switch(type) {
            case 1:
                dy = 4;
                break;
            case 2:
            	dy = 3;
            	break;
        }
    }
    
    public void shoot() {
        
    } 
    
    public void noCommand() {
        
    }

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}