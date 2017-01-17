package main;


public class Boss implements Actor {
	
	private enum move_ {
		spawning(0, 0, 5, 120, false, true),
		despawning(0, 0, 5, 200, false, true),
		
		shootMouth(0, 0, 3, 30, true, false),
		
		moveLeft(-6, 0, 0, 30, false, false),
		moveRight(6, 0, 0, 30, false, false),
		
		moveLeftShootMouth(-6, 0, 3, 90, true, false),
		moveRightShootMouth(6, 0, 3, 90, true, false),
		
		moveLeftUp(-6, -2, 0, 30, false, false),
		moveRightUp(6, -2, 0, 30, false, false),
		
		moveRightUpShootMouth(-6, -2, 3, 30, true, false),
		moveLeftUpShootMouth(6, -2, 3, 30, true, false),
		
		moveLeftDown(-6, 2, 0, 30, false, false),
		moveRightDown(6, 2, 0, 30, false, false),
		
		moveRightDownShootMouth(6, 2, 3, 30, true, false),
		moveLeftDownShootMouth(-6, 2, 3, 30, true, false),
		
		idyll(0,0, 0, 5,false, false),
		heal(0,0, 0, 5,false, false),
		hit(0,0, 1, 5, false, true);
	
		private int dx;
		private int dy;
		private int image;
		private boolean isShooting;
		private boolean isRecovering;
		
		private int cd;
		
		move_(int dx, int dy, int image, int cd, boolean isShooting, boolean recovering) {
			this.dx = dx;
			this.dy = dy;
			this.image = image;
			this.cd = cd;
			this.isShooting = isShooting;
			this.isRecovering = recovering;
		}
		
		
		public int getDx() {
			return dx;
		}
		
		public int getDy() {
			return dy;
		}
		
		public int getImage() {
			return image;
		}
		
		public void setImage(int i) {
			image = i;
		}
		
		public int getCD() {
			return cd;
		}
		
		public boolean isShooting() {
			return isShooting;
		}
		
		public boolean isRecovering() {
			return isRecovering;
		}
		
	}

	private move_ moveState;
	
	private Weapon currentWeapon;
	
	private int x;
	private int y;
	
	private int score;
	
	private int image;
	private double frequency;
	private int frameCounter;
	
	private int maxLifepoints;
    private int lifepoints;
    
    private int type;

    private int randomNumber;
    
    private int laserCounter;
    
    private int moveCD;
    
    
    public Boss() {
    	moveState = move_.spawning;
    	frequency = 3;
    	moveCD = 300;
    	score = 1000;
    	x = 250;
	   	y = 230;
	   	maxLifepoints = 50;
	   	lifepoints = maxLifepoints;
	    image = 0; 	
    }
	
	public Weapon getWeapon() {
		return currentWeapon;
	}

	@Override
	public void move(int Height, int Width, boolean beatLevel) {
		frameCounter++;
		image = moveState.getImage();

		applyLogic();
		
		if(moveState.isShooting()) {
			Thread e3 = new Thread(new GameSound(4, 0, 0));;
            e3.start();
		}
		
		if(moveState.isShooting()) {
			if(laserCounter > 5) {
				nextLaser();
				laserCounter = 0;
			}
			else laserCounter++;		
		}	

		else currentWeapon = new Weapon(0, this);
		if(moveCD > 0) {
			moveCD -= 1;
		}
		if(moveState == move_.spawning) {
			frequency *= 1.008;
			if((frameCounter + 60) % (int) (60 / frequency) == 0) {
				if(moveState.getImage() == 8) moveState.setImage(5);
				else moveState.setImage(++image);
			}
		}
		if(type == 2 && moveCD > 0) moveCD--;
		if(x + moveState.getDx() > 0 && x + moveState.getDx() + Width < 840) x += moveState.getDx();
		if(y + moveState.getDy() > 200 && y + moveState.getDy() + Height < 500) y += moveState.getDy();
	}

	public void applyLogic() {
		if(moveCD == 0) {
			randomNumber = (int)(Math.random()*100);
			if(randomNumber <= 50) {
				if(randomNumber < 3 && randomNumber > 0) moveState = move_.idyll;			
				else if(randomNumber < 7) moveState = move_.moveLeftDown;		
				else if(randomNumber < 11) moveState = move_.moveRightDown;		
				else if(randomNumber < 15) moveState = move_.moveLeftUp;		
				else if(randomNumber < 19) moveState = move_.moveRightUp;
				else if(randomNumber < 23) moveState = move_.moveLeft;
				else if(randomNumber < 27) moveState = move_.moveRight;
				else if(randomNumber < 35) moveState = move_.moveRightShootMouth;
				else if(randomNumber < 43) moveState = move_.moveLeftShootMouth;
				else moveState = move_.shootMouth;
				}
				else if(randomNumber <= 100) {
					if(randomNumber < 61) moveState = move_.moveLeftShootMouth;
					else if(randomNumber < 72) moveState = move_.moveRightShootMouth;
					else if(randomNumber < 78) moveState = move_.moveLeftUpShootMouth;
					else if(randomNumber < 84) moveState = move_.moveRightUpShootMouth;
					else if(randomNumber < 90) moveState = move_.moveLeftDownShootMouth;
					else if(randomNumber < 96) moveState = move_.moveRightDownShootMouth;
					else {
						moveState = move_.heal;
						if(lifepoints < maxLifepoints - 10) lifepoints = lifepoints + 10;
					}
				}
			moveCD = moveState.getCD();
		}
		
	}

    
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDX() {
		return moveState.getDx();
	}

	public int getDY() {
		return moveState.getDy();
	}

	public int getImage() {
		return image;
	}

	public int getDamage() {
		return currentWeapon.getDamage();
	}

	public int getLifepoints() {
		return lifepoints;
	}
	
	public int getMaxLifepoints() {
    	return maxLifepoints;
    }

	public void gotHit(Actor actor) {
		if(!moveState.isRecovering()) {
			int lifepointsPrev = lifepoints;
			lifepoints = lifepoints - actor.getDamage();
			if(lifepointsPrev / 10 > lifepoints / 10) moveState = move_.hit;
			if(lifepoints <= 0) {
				x = 5000;
				y = 5000;
				moveState = move_.despawning;
			}
        }
	}
	
	public void nextLaser() {
		switch(currentWeapon.getType()) {	
			default: 
				currentWeapon = new Weapon(100, this);
				break;
			case 100:
				currentWeapon = new Weapon(101, this);
				break;
			case 101:
				currentWeapon = new Weapon(102, this);
				break;	
			case 102:
				currentWeapon = new Weapon(103, this);
				break;	
			case 103:
				currentWeapon = new Weapon(104, this);
				break;	
			case 104:
				currentWeapon = new Weapon(105, this);
				break;
			case 105:
				currentWeapon = new Weapon(106, this);
				break;
			}	
	}

	public boolean createProjectile() {
		return moveState.isShooting();
	}
	
	public int getScore() {
		return score;
	}

	public void createdProjectile(boolean Boolean) {
		
	}

	public void moveLeft() {
		
	}

	public void moveRight() {
		
	}
	
	public void shoot() {
		
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
