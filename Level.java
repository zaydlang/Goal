import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

import java.awt.geom.*;
import java.awt.Color;

import java.lang.Math.*;

import java.util.ArrayList;

public class Level {
    private int width;
    private int height;
    private Data data;
    
    public Level(int width, int height) {
    	this.width  = width;
    	this.height = height;
    	data = new Data(width, height);
    }

    public Flags buildLevel(ArrayList<String> actionQueue) {
        // updateLevel(actionQueue);
        data.update(actionQueue);
        return data.build();
        /*
        for (int i = 0; i < players.size(); i++) {
			for (int j = 0; data.get(i).get(j) != null; j++) {
                data.get(i).get(j).update();
			}
		}

		return ((Player)(data.get(0).get(0))).getNextLevelFlag();*/
    }
    
    public void drawLevel(Graphics g) {
    	data.draw(g);
    }

    public void updateLevel(ArrayList<String> actionQueue) {
    /*
        for (int i = 0; actionQueue.size() != 0; actionQueue.remove(0)) {
            String action = actionQueue.get(0);
            
            data = data[0][0].move(action, data);
            //data = data[3][0].move("", data);
        }
        
        data = data[0][0].move("", data);
        for (int i = 0; data[3][i] != null && i < data[3].length; i++) {
            data = data[3][i].move("", data);
            if (data == null) {
                resetLevel();
                return;
            }
        }*/
    }
    
    public void resetLevel() {
    }
    
    public Data getData() {
        return data;
    }

	public void addPlayer(Player p) {
		data.addPlayer(p);
	}

	public void addGoal(Goal g) {
		data.addGoal(g);
	}

	public void addSolid(Solid s) {
		data.addSolid(s);
	}
	
	public void addEnemy(Enemy e) {
		data.addEnemy(e);
	}

	public void addMovingSolid(MovingSolid ms) {
		data.addMovingSolid(ms);
	}
}
