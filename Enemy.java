import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

import java.awt.geom.*;

import java.lang.Math.*;

public class Enemy extends Element {
    private double xVel = Constants.ENEMY_MOVE_SPEED;
    private double yVel = 0;
    
    private boolean movingLeft  = false;
    private boolean movingRight = false;
    
    private boolean enablePhysics = true;
    private boolean enableGravity = true;

    private double dt = System.currentTimeMillis();
    
    public Enemy(double x, double y, double width, double height) {
    	super(x, y, width, height, Color.RED);   	
    	setUpdate(true);
    }
    
    public void update() {
        if (getY() <= 0) {
			setY(0);
			yVel = 0;
	    } else if (enableGravity) {
    		yVel += Constants.GRAVITY;
    	}
       
    	updatePos(xVel, yVel);
    }

    public Element[][] move(String action, Element[][] data) {
        dt = System.currentTimeMillis() - dt;
        
        double oldXVel = xVel;
        double oldYVel = yVel;
        double oldX = getX();
        double oldY = getY();

		updatePos(xVel, yVel);
		for (int i = 0; data[2][i] != null; i++) {
			boolean[] hitFlags = BoundingBox.getCollisions(this, data[2][i]);
			if (hitFlags[0] && hitFlags[1] || 
             hitFlags[1] && hitFlags[2] || 
             hitFlags[2] && hitFlags[3] ||
             hitFlags[3] && hitFlags[0]) {
             break;   
         }
         
			if (hitFlags[0]) {
				yVel = 0;
				setY(data[2][i].getY() + data[2][i].getHeight());
			}
			
			if (hitFlags[1]) {
				setX(data[2][i].getX() - this.getWidth());
				xVel = -Constants.ENEMY_MOVE_SPEED;
				yVel += Constants.GRAVITY;
			}
			
			if (hitFlags[2]) {
				setX(data[2][i].getX() + data[2][i].getWidth());
				xVel = Constants.ENEMY_MOVE_SPEED;
				yVel += Constants.GRAVITY;
			}
			
			if (hitFlags[3]) {
				yVel = Constants.GRAVITY * dt; 
				updatePos(0, yVel);
			}
			
			if (hitFlags[0] || hitFlags[1] || hitFlags[2] || hitFlags[3]) i = -1;
	    }		
       
       boolean[] resetFlags = BoundingBox.getCollisions(this, data[0][0]);
       System.out.println(resetFlags);
       if (resetFlags[0] || resetFlags[1] || resetFlags[2] || resetFlags[3]) {
           System.out.println("NU");
           return null;
       }
       
       dt = System.currentTimeMillis();
       return data;
	}
	
    public void updatePos(double xVel, double yVel) {
        setX(getX() + xVel);
        setY(getY() + yVel);
    }
}



