package main;

public class Projectile {

    private int type;
    private int x;
    private int y;
    private int dx;
    private int dy;
    
    private int image;
    
    private int despawnCounter;
    
    private boolean issueDespawn;
    private boolean hit;
    
    private Actor actor;
    private int actY;
    
    private int travelY;
    
    public Projectile(int Type, int Dx, int Dy) {
        type = Type;
        switch(type) {
            case 1: 
                image = 1;
                break;
            case 2: 
                image = 3;
                break;
            case 3: 
                image = 5;
                break;
            case 4:
            	image = 23;
            	break;
            case 5:
            	image = 25;
            	break;
            case 6:
            	image = 27;
            	break;
            case 10:
            	image = 10;
            	break;
            case 11: 
            	image = 12;
            	break;
            case 12:
            	image = 14;
            	break;
            case 13:
            	image = 16;
            	break;
            case 14: 
            	image = 18;
            	break;
            case 15:
            	image = 20;
            	break;
            case 16:
            	image = 22;
            	break;
        }
        dx = Dx;
        dy = Dy;
    }
    
    public Projectile(Projectile prototype, Actor actor, int X, int Y) {
        type = prototype.getType();
        this.actor = actor;
        actY = actor.getY();
        image = prototype.getImage();
        x = X;
        y = Y;
        dy = prototype.getDY();
        dx = prototype.getDX();
    }
    
    public void move() {
        if(despawnCounter != 0 && despawnCounter <= 10) despawnCounter++;        
        if(despawnCounter == 10) issueDespawn = true;
        
        if(actor.getClass() == Boss.class) {
        	if(actor.getY() != actY) y += actor.getDY();
        	actY = actor.getY();
        } 
        travelY = travelY + dy;       
        x = x + dx;
        y = y + dy;
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
    
    public int getType() {
        return type;
    }
    
    public int getTravelY() {
        int returnValue = 0;
        if(travelY < 0) returnValue = -travelY;
        else returnValue = travelY;
        return returnValue;
    }
    
    public void despawn(int projectileHeight, int projectileWidth, int explosionHeight, int explosionWidth) {
        despawnCounter = 1;
        image++;
        x = x + projectileWidth/2 - explosionWidth/2;
        y = y - explosionHeight/2;
        dy = 0;
    }
    
    public int getDespawnCounter() {
        return despawnCounter;
    }
    
    public boolean issuedDespawn() {
        return issueDespawn;
    }
    
    public Actor getActor() {
        return actor;
    }
    
    public void hit() {
        hit = true;
    }
    
    public boolean wasUsed() {
        return hit;
    }
}