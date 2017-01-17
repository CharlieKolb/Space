package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

	private boolean isShooting;
	
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isShooting = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isShooting = false;
		
	}
	
	public boolean isShooting() {
		return isShooting;
	}

}
