package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*  Gets Input from the Player
 *  Sends Input to InputHandler
 * 
 */
public class Input extends KeyAdapter {

    private boolean vk_up;
    private boolean vk_left;
    private boolean vk_right;
    private boolean vk_down;
    private boolean vk_enter;
    private boolean vk_escape;
    
    private MyMouseMotionListener myMouseMotionListener;
    private MyMouseListener myMouseListener;
    
    private boolean useMouse;
    
    public Input() {
    	myMouseMotionListener = new MyMouseMotionListener();
    	myMouseListener = new MyMouseListener();
    }
    
    /*
     *  Input-Table:
     *  0: No Input
     *  1: Left
     *  2: Right
     *  3: Up
     *  4: Right & Up
     *  5: Left & Up
     *  6: Down
     *  7: Down & Up
     *  8: Enter
     *  9: Escape
     */
    public int getInput() {       
        if(vk_left && !vk_up) return 1;
        else if(vk_right && !vk_up) return 2;
        else if(vk_up && !vk_right && !vk_left) return 3;
        else if(vk_right && vk_up) return 4;
        else if(vk_left && vk_up) return 5;
        else if(vk_down && !vk_up) return 6;
        else if(vk_down && vk_up) return 7;
        else if(vk_enter) return 8;
        else if(vk_escape) return 9;
        
        return 0;
    }
    
    public void move(int playerXLeft, int playerXRight) {
	    if(useMouse) {
    		myMouseMotionListener.move(playerXLeft, playerXRight);
	    	
	    	if(myMouseMotionListener.mouseLeftOfShip()) {
	    		vk_left = true;
	    		if(vk_right) vk_right = false;
	    	}
	    	else {
	    		vk_left = false;
	    	}
	    	
	    	if(myMouseMotionListener.mouseRightOfShip()) {
	    		vk_right = true;
	    		if(vk_left) vk_left = false;
	    	}
	    	else {
	    		vk_right = false;
	    	}
	    	
	    	if(myMouseListener.isShooting()) {
	    		vk_up = true;
	    	} else vk_up = false;
	    }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {   
            case KeyEvent.VK_UP:
                vk_up = true;
                break;
            case KeyEvent.VK_LEFT:
                if(vk_right) vk_right = false;
                vk_left = true;
                break;
            case KeyEvent.VK_RIGHT:
                if(vk_left) vk_left = false;
                vk_right = true;
                break;
            case KeyEvent.VK_DOWN:
            	vk_down = true;
            	break;
            case KeyEvent.VK_ENTER:
            	vk_enter = true;
            	break;
            case KeyEvent.VK_ESCAPE:
            	vk_escape = true;
            	break;
            case KeyEvent.VK_M:
            	if(useMouse) {
            		useMouse = false;
            		vk_right = false;
            		vk_left = false;
            	}
            	else {
            		useMouse = true;
	            	vk_right = false;
	        		vk_left = false;
	        	}
            	break;
            	
        }       
    }
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
       
        switch(key) {
            case KeyEvent.VK_UP:
                vk_up = false;
                break;
            case KeyEvent.VK_LEFT:
                vk_left = false;
                break;
            case KeyEvent.VK_RIGHT:
                vk_right = false;
                break;
            case KeyEvent.VK_DOWN:
            	vk_down = false;
            	break;
            case KeyEvent.VK_ENTER:
            	vk_enter = false;
            	break;
            case KeyEvent.VK_ESCAPE:
            	vk_escape = false;
            	break;
        }       
    }
    
    public void disableMouse() {
    	useMouse = false;
    }
    
    public void enableMouse() {
    	useMouse = true;
    }
    
    public boolean useMouse() {
    	return useMouse;
    }
    
    public MouseMotionListener getMouseMotionListener() {
    	return myMouseMotionListener;
    }
    
    public MouseListener getMouseListener() {
    	return myMouseListener;
    }
    
}