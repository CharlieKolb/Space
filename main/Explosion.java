package main;


public class Explosion {

	private int duration;
	private int arrayNumber;
	private int x;
	private int y;
	private int oX;
	private int oY;
	
	public Explosion(int x, int y, int arrNumber) {
		duration = 33;
		arrayNumber = arrNumber;
		oX = x;
		oY = y;
		this.x = x + (int) (Math.random()*90);
		this.y = y + (int) (Math.random()*70);
	}
	
	public Explosion(int x, int y) {
		duration = 25;
		oX = x;
		oY = y;
		this.x = oX;
		this.y = oY;
	}
	
	public void move() {
		duration--;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getArrayNumber() {
		return arrayNumber;
	}
	
	public int getOX() {
		return oX;
	}
	
	public int getOY() {
		return oY;
	}
	
}
