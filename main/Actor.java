package main;

public interface Actor {

    public Weapon getWeapon();
    
    public void move(int Width, int Height, boolean finishedLevel);
   
    public int getX();
    
    public int getY();
    
    public int getDX();
    
    public int getDY();
    
    public int getImage();
    
    public int getDamage();
    
    public int getLifepoints();
    
    public int getMaxLifepoints();
    
    public int getScore();
    
    public void gotHit(Actor actor);
    
    public boolean createProjectile();
    
    public void createdProjectile(boolean Boolean);
    
    public boolean isVisible();
    
    public void moveLeft();
    
    public void moveRight();
    
    public void shoot();

    public void noCommand();

	public int getType();
}