package main;

public class Background {
    
    private int y;
    private int dy;
    
    private int image;
    
    public Background(int Counter, int level, boolean tutorial) {
        y = 200*Counter;
        switch(level) {
            case 1:
                dy = 1;
                image = 1;
                break;
            case 2: 
                dy = 2;
                image = 2;
                break;
            case 3:
                dy = 3;
                image = 3;
                break;
            case 4:
                dy = 5;
                image = 4;
                break;          
        }
        if(tutorial && level == 1) {
	        	if(Counter == 0) image = 5;
	        	else if(Counter == 1) image = 6;
	        	else if(Counter == 2) image = 7;
        }
    }
    
    public void move() {
        y = y + dy;
        if(y >= 1000) {
        	y = 0;
        	if(image > 4) image = 1;
        }
    }
    
    public int getImage() {
    	return image;
    }
    
    public int getY() {
        return y;
    }
}