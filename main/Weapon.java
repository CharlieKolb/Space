package main;

public class Weapon {

    private int damage;
    private int cd;
    private int range = 600;
    
    private int amountOfProjectiles;
    
    private int type;
    
    private int cooldown;    
    private int cooldownHeat;
    private int heat;
    private int heatMax;
    private int cooldownHeatDecrease;
    private int cooldownMaxHeat;
    
    private int[] relativeX;
    private int[] relativeY;
    
    private boolean isAbleToShoot;
    private boolean hitMax;
    
    private Projectile projectileA;
    private Projectile projectileB;
    private Projectile projectileC;
    
    public Weapon(int Type, Actor actor) {
        relativeX = new int[5];
        relativeY = new int[5];
        type = Type;
        isAbleToShoot = true;
        getValues();        
    }
    
    //Implements different Weapons depending on type
    /*
     *  1X: Player weapons
     *  2X: level1 minion weapons
     *  3X: level2 minion weapons
     *  4X: level3 minion weapons
     *  5x: level4 minion weapons
     *  100 - 106: Boss Weapon laser
     *  110 - 116: Player4 weapon laser
     */
    public void getValues() {
        switch(type) {
            case 0: 
                    amountOfProjectiles = 0;
                    damage = 0;
                    cd = 0;
                    range = 0;
                    projectileA = new Projectile(0, 0, 0);
                    break;
            case 11: 
                    amountOfProjectiles = 1;
                    damage = 4;
                    cd = 30;
                    heatMax = 10;
                    cooldownHeat = 30;
                    cooldownMaxHeat = 40;
                    cooldownHeatDecrease = 20;
                    projectileA = new Projectile(1, 0, -8);
                    relativeX[0] = 44;
                    break;
            case 12: 
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 15;
                    heatMax = 30;
                    cooldownHeat = 25;
                    cooldownMaxHeat = 12;
                    cooldownHeatDecrease = 10;
                    projectileA = new Projectile(2, 0, -10);
                    relativeX[0] = 44;
                    break;
            case 13: 
	                amountOfProjectiles = 1;
	                damage = 15;
	                cd = 40;
	                heatMax = 5;
	                cooldownHeat = 50;
	                cooldownMaxHeat = 100;
	                cooldownHeatDecrease = 20;
	                projectileA = new Projectile(3, 0, -12);
	                relativeX[0] = 44;
	                break;
            case 21: 
                    amountOfProjectiles = 1;
                    damage = 1;
                    cd = 35;
                    projectileA = new Projectile(4, 0, 4);
                    relativeX[0] = 40;
                    relativeY[0] = 22; 
                    break;
            case 22:
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 60;
                    projectileA = new Projectile(4, 0, 8);
                    relativeX[0] = 47;
                    relativeY[0] = 37;
                    break;
            case 23:
                    amountOfProjectiles = 2;
                    damage = 1;
                    cd = 40;
                    projectileA = new Projectile(5, 0, 4);
                    projectileB = new Projectile(5, 0, 4);
                    relativeX[0] = 13;
                    relativeX[1] = 84;
                    relativeY[0] = 37;
                    relativeY[1] = 37;
                    break;
            case 24:
                    amountOfProjectiles = 1;
                    damage = 1;
                    cd = 35;
                    projectileA = new Projectile(4, 0, 4);
                    relativeX[0] = 37;
                    relativeY[0] = 37;
                    break;
            case 25:
                    amountOfProjectiles = 1;
                    damage = 1;
                    cd = 35;
                    projectileA = new Projectile(6, 0, 5);
                    relativeX[0] = 43;
                    relativeY[0] = 35;
                    break;
                    
            case 31: 
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 32;
                    projectileA = new Projectile(5, 0, 5);
                    relativeX[0] = 40;
                    relativeY[0] = 22; 
                    break;
            case 32:
                    amountOfProjectiles = 1;
                    damage = 3;
                    cd = 50;
                    projectileA = new Projectile(5, 0, 7);
                    relativeX[0] = 47;
                    relativeY[0] = 37;
                    break;
            case 33:
                    amountOfProjectiles = 2;
                    damage = 1;
                    cd = 32;
                    projectileA = new Projectile(6, 0, 5);
                    projectileB = new Projectile(6, 0, 5);
                    relativeX[0] = 13;
                    relativeX[1] = 84;
                    relativeY[0] = 37;
                    relativeY[1] = 37;
                    break;
            case 34:
                    amountOfProjectiles = 1;
                    damage = 1;
                    cd = 35;
                    projectileA = new Projectile(4, 0, 4);
                    relativeX[0] = 37;
                    relativeY[0] = 37;
                    break;
            case 35:
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 32;
                    projectileA = new Projectile(4, 0, 6);
                    relativeX[0] = 43;
                    relativeY[0] = 35;
                    break;    
                    
            case 41: 
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 30;
                    projectileA = new Projectile(6, 0, 6);
                    relativeX[0] = 40;
                    relativeY[0] = 22; 
                    break;
            case 42:
                    amountOfProjectiles = 1;
                    damage = 3;
                    cd = 40;
                    projectileA = new Projectile(6, 0, 9);
                    relativeX[0] = 47;
                    relativeY[0] = 37;
                    break;
            case 43:
                    amountOfProjectiles = 2;
                    damage = 2;
                    cd = 30;
                    projectileA = new Projectile(5, 0, 5);
                    projectileB = new Projectile(5, 0, 5);
                    relativeX[0] = 13;
                    relativeX[1] = 84;
                    relativeY[0] = 37;
                    relativeY[1] = 37;
                    break;
            case 44:
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 32;
                    projectileA = new Projectile(4, 0, 5);
                    relativeX[0] = 37;
                    relativeY[0] = 37;
                    break;
            case 45:
                    amountOfProjectiles = 1;
                    damage = 3;
                    cd = 30;
                    projectileA = new Projectile(4, 0, 7);
                    relativeX[0] = 43;
                    relativeY[0] = 35;
                    break;
                 
            case 51: 
                    amountOfProjectiles = 1;
                    damage = 3;
                    cd = 28;
                    projectileA = new Projectile(5, 0, 7);
                    relativeX[0] = 40;
                    relativeY[0] = 22; 
                    break;
            case 52:
                    amountOfProjectiles = 1;
                    damage = 4;
                    cd = 38;
                    projectileA = new Projectile(4, 0, 11);
                    relativeX[0] = 47;
                    relativeY[0] = 37;
                    break;
            case 53:
                    amountOfProjectiles = 3;
                    damage = 3;
                    cd = 28;
                    projectileA = new Projectile(4, 0, 6);
                    projectileB = new Projectile(4, 0, 6);
                    projectileC = new Projectile(4, 0, 6);
                    relativeX[0] = 13;
                    relativeX[1] = 70;
                    relativeX[2] = 42;
                    relativeY[0] = 37;
                    relativeY[1] = 37;
                    relativeY[2] = 37;
                    break;
            case 54:
                    amountOfProjectiles = 1;
                    damage = 2;
                    cd = 30;
                    projectileA = new Projectile(5, 0, 6);
                    relativeX[0] = 37;
                    relativeY[0] = 37;
                    break;
            case 55:
                    amountOfProjectiles = 1;
                    damage = 4;
                    cd = 28;
                    projectileA = new Projectile(6, 0, 7);
                    relativeX[0] = 43;
                    relativeY[0] = 35;
                    break;
            case 100: 
            		amountOfProjectiles = 1;
            		damage = 1;
            		cd = 10;
            		projectileA = new Projectile(10, 0, 600);
            		relativeX[0] = 101;
            		relativeY[0] = 196;
            		break;
            case 101: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(11, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
            case 102: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(12, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
            case 103: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(13, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
            case 104: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(14, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
            case 105: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(15, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
            case 106: 
	        		amountOfProjectiles = 1;
	        		damage = 1;
	        		cd = 10;
	        		projectileA = new Projectile(16, 0, 600);
	        		relativeX[0] = 101;
	        		relativeY[0] = 196;
	        		break;
        }
    }
    
    public boolean isAbleToShoot() {
        return isAbleToShoot;
    }
    
    public void shot() {
        cooldown = cooldownHeat;
        if(heat < 0) heat = 0;
        heat++;
    }
    
    public void move() {
        if(cooldown != 0) cooldown -= 1;
        if(hitMax) {
            isAbleToShoot = false; 
            if(heat > 0 && cooldown == 0) {
                heat -= 1;
                cooldown = cooldownMaxHeat;
            }
            else if(heat == 0) hitMax = false;
        }
        else {
            isAbleToShoot = true;
            if(cooldown == 0 && heat > 0) {
                heat -= 1;
                cooldown = cooldownHeatDecrease;
            }
            if(heat == heatMax) {
                hitMax = true;
            }
        }
    }
    
    public int getHeat() {
        return heat;
    } 
    
    public int getMaxHeat() {
        return heatMax;
    }
    
    
    public int getAmountOfProjectiles() {
        return amountOfProjectiles;
    }
    
    public int getDamage() {
        return damage;
    }
     
    public Projectile getProjectileA() {
        return projectileA;
    }
    
    public Projectile getProjectileB() {
        return projectileB;
    }
    
    public Projectile getProjectileC() {
        return projectileC;
    }

    public int getRelativeX(int i) {
        return relativeX[i];
    }
    
    public int getRelativeY(int i) {
        return relativeY[i];
    }
    
    public int getType() {
    	return type;
    }
    
    public int getCD() {
        return cd;
    }
     
    public int getRange() {
        return range;
    }

}