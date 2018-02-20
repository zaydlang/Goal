import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

import java.awt.geom.*;

import java.lang.Math.*;

import java.util.ArrayList;
import java.net.*;
import java.util.*;

public class Player extends Element {
    private double xVel = 0;
    private double yVel = 0;
    private double oldXVel;
	private double oldYVel;
	private double oldX;
	private double oldY;

    private boolean movingLeft  = false;
    private boolean movingRight = false;
    
    private boolean enablePhysics = true;
    private boolean enableJump    = true;
    private boolean enableGravity = true;
	private boolean onGround      = false;
	private boolean nextLevelFlag = false;
	
	private double dt = System.currentTimeMillis();
    public Player(double x, double y, double width, double height) {
    	super(x, y, width, height, Color.GREEN);   	
    	setUpdate(true);
    }
    
    public void update() {
    	updatePos(xVel, yVel);
    }
    
    public Flags move(String action, Data data, Flags flags) {
        dt = System.currentTimeMillis() - dt;
        oldXVel = xVel;
        oldYVel = yVel;
        oldX = getX();
        oldY = getY();
		if (action.equals("move left") || movingLeft) {
		    movingLeft = true;	
		    
            // Smooth Turning in mid-air
            /*
            if ((getY() != 0 && !enablePhysics) && xVel > 0) {
			    xVel -= Constants.PLAYER_ACC * dt;
		    }*/
		    
			xVel -= Constants.PLAYER_ACC * dt;
            if (-xVel > Constants.PLAYER_MOVE_SPEED) xVel = -Constants.PLAYER_MOVE_SPEED * dt; 
		}

        if (action.equals("move right") || movingRight) {	
            movingRight = true;
            
            // Smooth Turning in mid-air
            /*
            if (getY() != 0 && xVel < 0) {
			    xVel += Constants.PLAYER_ACC * dt;
		    }*/
		    
			xVel += Constants.PLAYER_ACC * dt;
            if (xVel > Constants.PLAYER_MOVE_SPEED) xVel = Constants.PLAYER_MOVE_SPEED * dt;
		}
		
        if (action.equals("jump") && (getY() == 0 || enableJump)) {
			//if (yVel < 0) yVel = 0;
         System.out.println("JUMP!");
			yVel = Constants.PLAYER_JUMP_SPEED;
            setY(getY() + 1);
            enableJump = false;
		}
		
		if (action.equals("move left released")) movingLeft = false;
		if (action.equals("move right released")) movingRight = false;
		/*
		if (action.equals("grapple")) {
		   data[1][0] = new Grapple(getX(), getY(), Constants.GRAPPLE_SIZE, Constants.GRAPPLE_SIZE, Constants.GRAPPLE_SPEED);
		}*/

		if (enablePhysics) {
	    	if (xVel > 0) {
		    	xVel += Constants.PLAYER_MASS * Constants.GRAVITY * Constants.FRIC * dt;
		        if (xVel < 0) xVel = 0;
		    }

		    if (xVel < 0) {
		  	 	xVel -= Constants.PLAYER_MASS * Constants.GRAVITY * Constants.FRIC * dt;
		        if (xVel > 0) xVel = 0;
		    }
	    }

        if (getY() <= 0) {
			setY(0);
			yVel = 0;      
	    } else if (enableGravity) {
    		yVel += Constants.GRAVITY * dt;
    	}

		onGround = false;
		ArrayList<Solid> solids = data.getSolids();
		for (int i = 0; i < solids.size(); i++) {
			boolean[] hitFlags = BoundingBox.getCollisions(this, solids.get(i));
			if (hitFlags[0] && hitFlags[1] || 
             hitFlags[1] && hitFlags[2] || 
             hitFlags[2] && hitFlags[3] ||
             hitFlags[3] && hitFlags[0]) {
             break;   
         }
         
			if (hitFlags[0]) {
				yVel = 0;
				setY(solids.get(i).getY() + solids.get(i).getHeight());
				enableJump = true;
				onGround = true;
			}
			
			if (hitFlags[1]) {
				setX(solids.get(i).getX() - this.getWidth());
				xVel = 0;
				yVel += Constants.GRAVITY;
				enableJump = true;
			}
			
			if (hitFlags[2]) {
				setX(solids.get(i).getX() + solids.get(i).getWidth());
				xVel = 0;
				yVel += Constants.GRAVITY;
				enableJump = true;
			}
			
			if (hitFlags[3]) {
				yVel = Constants.GRAVITY * dt; 
				updatePos(0, yVel);
				enableJump = false;
			}
			
			if (hitFlags[0] || hitFlags[1] || hitFlags[2] || hitFlags[3]) i = -1;
	    }
		
		ArrayList<Goal> goals = data.getGoals();
		for (int i = 0; i < goals.size(); i++) {
			boolean[] finishedLevel = BoundingBox.getCollisions(this, data.getGoals().get(i));
			if (finishedLevel[0] || finishedLevel[1] || finishedLevel[2] || finishedLevel[3]) {
				flags.setNextLevelFlag(true);
				break;
			}
		}
		
		/*
			if (BoundingBox.intersects(this, data[2][i]) || BoundingBox.intersects(data[2][i], this)) {
				if (BoundingBox.isAbove(this, data[2][i], Constants.BUFFER)) {
					yVel = 0;
					setY(data[2][i].getY() + data[2][i].getHeight());
					enableJump = true;
					//enablePhysics = true;
					onGround = true;
					i = -1;
					continue;
				} 
				
				if (BoundingBox.hitRoof(this, data[2][i], Constants.BUFFER)) {
				System.out.println("OUCH!");
					yVel = Constants.GRAVITY * dt; 
					updatePos(0, yVel);
					enableJump = false;
					i = -1;
					continue;
				} 
				
				if (BoundingBox.hitLeft(this, data[2][i], Constants.BUFFER)) {
					setX(data[2][i].getX() - this.getWidth());
					System.out.println("BEEP");
					//yVel += Constants.GRAVITY;
					enableJump = true;
					i = -1;
					continue;
				}
				
				if (BoundingBox.hitRight(this, data[2][i], Constants.BUFFER)) {
					setX(data[2][i].getX() + data[2][i].getWidth());
					//yVel += Constants.GRAVITY;
					enableJump = true;
					i = -1;
					continue;
				}

				//enablePhysics = false;
			}
		}*/

		dt = System.currentTimeMillis();
    	
		return flags;
	}
	
    public void updatePos(double xVel, double yVel) {
        //System.out.println(getX() + " " + getY() + " " + xVel + " " + yVel);
        setX(getX() + xVel);
        setY(getY() + yVel);
    }

	public void setNextLevelFlag(boolean newFlag) { nextLevelFlag = newFlag; }
	public boolean getNextLevelFlag() { return nextLevelFlag; }
}





