package main;

import java.lang.Math;

public class Minion implements Actor {

    
    private int x;
    private int y;
    
    private int score;
    
    private double action;
    private int logicCooldown;

    
    private int dx;
    private int tempDx;
    private int dy;
    private int tempDy;
    
    private int delay;
    
    private int maxY;
    
    private int image;
    
    private int maxLifepoints;
    private int lifepoints;
    
    private int type;
    
    private boolean isDespawned;
    
    private boolean isShooting;
    private int shootCooldown;
    
    private Weapon weapon;
    
    public Minion(int type, int x, int y, int delay, int maxY) {
    	score = 100;
        this.type = type;
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.maxY = maxY;
        switch(type) {
            case 0:
                maxLifepoints = -1;
                lifepoints = -1;
                break;
            case 1:
                tempDx = 2;
                tempDy = 2;
                maxLifepoints = 10;
                lifepoints = maxLifepoints;
                weapon = new Weapon(21, this);
                break;
            case 2:
                tempDx = 3;
                tempDy = 3;
                maxLifepoints = 5;
                lifepoints = maxLifepoints;
                weapon = new Weapon(22, this);
                break;
            case 3:
                tempDx = 2;
                tempDy = 3;
                maxLifepoints = 8;
                lifepoints = maxLifepoints;
                weapon = new Weapon(23, this);
                break;
            case 4:
                tempDx = 1;
                tempDy = 1;
                maxLifepoints = 13;
                lifepoints = maxLifepoints;
                weapon = new Weapon(24, this);
                break;
            case 5:
                tempDx = 2;
                tempDy = 2;
                maxLifepoints = 10;
                lifepoints = maxLifepoints;
                weapon = new Weapon(25, this);
                break;
                
            case 11:
                tempDx = 3;
                tempDy = 3;
                maxLifepoints = 12;
                lifepoints = maxLifepoints;
                weapon = new Weapon(31, this);
                break;
            case 12:
                tempDx = 4;
                tempDy = 4;
                maxLifepoints = 7;
                lifepoints = maxLifepoints;
                weapon = new Weapon(32, this);
                break;
            case 13:
                tempDx = 3;
                tempDy = 4;
                maxLifepoints = 10;
                lifepoints = maxLifepoints;
                weapon = new Weapon(33, this);
                break;
            case 14:
                tempDx = 1;
                tempDy = 1;
                maxLifepoints = 17;
                lifepoints = maxLifepoints;
                weapon = new Weapon(34, this);
                break;
            case 15:
                tempDx = 3;
                tempDy = 3;
                maxLifepoints = 9;
                lifepoints = maxLifepoints;
                weapon = new Weapon(35, this);
                break;
            
            case 21:
                tempDx = 3;
                tempDy = 3;
                maxLifepoints = 15;
                lifepoints = maxLifepoints;
                weapon = new Weapon(41, this);
                break;
            case 22:
                tempDx = 5;
                tempDy = 5;
                maxLifepoints = 10;
                lifepoints = maxLifepoints;
                weapon = new Weapon(42, this);
                break;
            case 23:
                tempDx = 4;
                tempDy = 4;
                maxLifepoints = 12;
                lifepoints = maxLifepoints;
                weapon = new Weapon(43, this);
                break;
            case 24:
                tempDx = 2;
                tempDy = 1;
                maxLifepoints = 25;
                lifepoints = maxLifepoints;
                weapon = new Weapon(44, this);
                break;
            case 25:
                tempDx = 4;
                tempDy = 4;
                maxLifepoints = 11;
                lifepoints = maxLifepoints;
                weapon = new Weapon(45, this);
                break;
                
            case 31:
                tempDx = 4;
                tempDy = 4;
                maxLifepoints = 17;
                lifepoints = maxLifepoints;
                weapon = new Weapon(51, this);
                break;
            case 32:
                tempDx = 5;
                tempDy = 5;
                maxLifepoints = 12;
                lifepoints = maxLifepoints;
                weapon = new Weapon(52, this);
                break;
            case 33:
                tempDx = 5;
                tempDy = 5;
                maxLifepoints = 14;
                lifepoints = maxLifepoints;
                weapon = new Weapon(53, this);
                break;
            case 34:
                tempDx = 2;
                tempDy = 2;
                maxLifepoints = 40;
                lifepoints = maxLifepoints;
                weapon = new Weapon(54, this);
                break;
            case 35:
                tempDx = 5;
                tempDy = 5;
                maxLifepoints = 13;
                lifepoints = maxLifepoints;
                weapon = new Weapon(55, this);
                break;
        }
        image = type;
    }
    
    public void move(int Width, int Height, boolean finishedLevel) {
        if(!isDespawned) {
            if(logicCooldown > 0) logicCooldown -= 1;
            if(shootCooldown != 0) shootCooldown++;
            if(shootCooldown >= weapon.getCD()) {
                shootCooldown = 0;
            }
            applyMovement(Width, Height);
            applyLogic();
            x = x + dx;
            delay -= dy;
            if(delay <= 0) y = y + dy;
        }
    }
   
    public void applyMovement(int Width, int Height) {
        if(y < maxY) moveDown();
        else dy = 0;
        if(x + Width >= 834) moveLeft();
        else if(x <= 0) moveRight();
    }
    
    public void applyLogic() {
        if(logicCooldown == 0) {
            action = Math.random() * 100;
            dx = 0;
        }
        if(y > maxY - 1 ) {
            int typeInt = type % 10; 
            switch(typeInt) {
                case 1:
                    if(action <= 20) {
                        shoot();
                        if(logicCooldown == 0) logicCooldown = 45;
                    }
                    else if(action <= 40) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 60) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 75) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else if(action <= 90) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else {
                        if(logicCooldown == 0) logicCooldown = 30;
                    }
                    break;
                case 2:
                    if(action <= 30) {
                        shoot();
                        if(logicCooldown == 0) logicCooldown = 45;
                    }
                    else if(action <= 50) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 70) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 80) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else if(action <= 90) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else {
                        if(logicCooldown == 0) logicCooldown = 30;
                    }
                    break;
                case 3:
                    if(action <= 20) {
                        shoot();
                        if(logicCooldown == 0) logicCooldown = 45;
                    }
                    else if(action <= 40) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 60) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 75) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else if(action <= 90) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else {
                        if(logicCooldown == 0) logicCooldown = 30;
                    }
                    break;
                case 4:
                    if(action <= 10) {
                        shoot();
                        if(logicCooldown == 0) logicCooldown = 45;
                    }
                    else if(action <= 35) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 60) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 75) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else if(action <= 90) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else {
                        if(logicCooldown == 0) logicCooldown = 30;
                    }
                    break;
                case 5:
                    if(action <= 20) {
                        shoot();
                        if(logicCooldown == 0) logicCooldown = 45;
                    }
                    else if(action <= 40) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 60) {
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 120;
                    }
                    else if(action <= 75) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = 2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else if(action <= 90) {
                        shoot();
                        if(dx > 0) moveRight(); 
                        else if(dx < 0) moveLeft();
                        else dx = -2;
                        if(logicCooldown == 0) logicCooldown = 60;
                    }
                    else {
                        if(logicCooldown == 0) logicCooldown = 30;
                    }
                    break;
            }
        }
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
    
    public void gotHit(Actor actor) {
        lifepoints = lifepoints - actor.getDamage();
        if(lifepoints <= 0) {
            isDespawned = true;
        }
    }
    
    public boolean createProjectile() {
        return isShooting;
    }
    
    public int getScore() {
    	return score;
    }
    
    public void createdProjectile(boolean Boolean) {
        isShooting = !Boolean;
    }

    public void moveLeft() {
        dx = -tempDx;
    }
    
    public void moveRight() {
        dx = tempDx;
    }
    
    public void moveDown() {
        dy = tempDy;
    }
    
    public void shoot() {
        if(shootCooldown == 0) {
            isShooting = true;
            shootCooldown++;
        }    
    } 
    
    public void noCommand() {
        
    }

	@Override
	public int getType() {
		return type;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}