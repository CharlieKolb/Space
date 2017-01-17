package main;

public class Player implements Actor {

    
    private int x;
    private int y;
    
    private int ship;
    private int maxLifepoints;
    private int lifepoints;
    
    private int windowSize = 830;
    
    private int dx;
    private int tempDX;
    private int dy;
    
    private int invincFrames; 
    
    private int shootCooldown;
    
    private boolean isShooting;
    
    private int image;
    
    private boolean visible;
    
    private Weapon weapon;       
    
    public Player(int Ship) {
        x = 320;
        y = 900;
        dy = -2;
        visible = true;
        switch(Ship) {
            case 1:
                ship = Ship;
                tempDX = 5;
                maxLifepoints = 14;
                lifepoints = maxLifepoints;
                image = 1;    
                weapon = new Weapon(11, this);
                break;
            case 2:
            	tempDX = 7;
                ship = Ship;
                maxLifepoints = 12;
                lifepoints = maxLifepoints;
                image = 6;
                weapon = new Weapon(12, this);
                break;
            case 3:
            	tempDX = 6;
                ship = Ship;
                maxLifepoints = 5;
                lifepoints = maxLifepoints;
                image = 11;
                weapon = new Weapon(13, this);
                break;
        }
    }
    
    public void move(int Width, int Height, boolean finishedLevel) {
        weapon.move();
        updateCooldowns();
        //Walls
        if(x + dx < 0) {
            dx = 0;            
        }
        else if(x + Width >= windowSize - dx - 5) {
            dx = 0;
        }
        
        // x-Movement
        x = x + dx;
        
        //fly-In at the beginning
        if(y != 700 && !finishedLevel) y += dy;
        
        //fly-Out in the end
        if(finishedLevel) {
        	if(y > 600) y -= 1;
        	else if(y > 400) y -= 2;
        	else if(y > 200 - Height) y -= 4;
        	dx = 0;
        }
    }
    
    public void updateCooldowns() {
        if(shootCooldown != 0) shootCooldown++;
        if(shootCooldown >= weapon.getCD()) {
            shootCooldown = 0;
        }
        if(invincFrames > 0) {
        	invincFrames--;
        	if(invincFrames % 5 == 0) {
        		if(visible) visible = false;
        		else visible = true;
        	}
        } else visible = true;
        
    }
    
    public Weapon getWeapon() {
        return weapon;
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
        return weapon.getDamage();
    }
    
    public int getLifepoints() {
        return lifepoints;
    }
    
    public int getMaxLifepoints() {
    	return maxLifepoints;
    }
    
    public boolean createProjectile() {
        if(y >= 700) return isShooting;
        else return false;
    }
    
    public void createdProjectile(boolean Boolean) {
        if(Boolean) {
            isShooting = false;
            weapon.shot();
        }
    }
    
    public int getScore() { return 0; }
    
    public void wasUsed() {}
    
    public void gotHit(Actor actor) {
        if(invincFrames == 0) {
        	invincFrames = 50;
        	lifepoints = lifepoints - actor.getDamage();
        	if(lifepoints < 0) lifepoints = 0;
        }
    }
    
    public boolean isVisible() {
    	return visible;
    }
    
    public void moveLeft() {
        dx = -tempDX;
    }
    
    public void moveRight() {
        dx = tempDX;
    }
    
    public void shoot() {
        if(shootCooldown == 0 && weapon.isAbleToShoot()) {
            isShooting = true;
            shootCooldown++;
        }
    } 
    
    public void noCommand() {
        dx = 0;
    }

	public int getType() {
		return ship;
	}
}