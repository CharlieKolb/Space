package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseMotionListener implements MouseMotionListener {

	private int leftOfShip, rightOfShip;
	private int mouseX;
	
	public void move(int playerXLeft, int playerXRight) {
		leftOfShip = playerXLeft + 5;
		rightOfShip = playerXRight - 5;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	}
	
	public boolean mouseLeftOfShip() {
		return mouseX < leftOfShip;
	}
	
	public boolean mouseRightOfShip() {
		return mouseX > rightOfShip;
	}

}
