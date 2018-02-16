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
    private Element[][] data;
    private Element[][] originalData = new Element[200][200];
    
    public Level(int width, int height, Element[][] data) {
    	this.width  = width;
    	this.height = height;
    	this.data   = data;
      
      originalData = data;
    }

    public boolean buildLevel(ArrayList<String> actionQueue) throws Exception {
        updateLevel(actionQueue);
        
        for (int i = 0; i < data.length; i++) {
			for (int j = 0; data[i][j] != null; j++) {
                Element temp = data[i][j];
                temp.update();
			}
		}

		return ((Player)(data[0][0])).getNextLevelFlag();
    }
    
    public void drawLevel(Graphics g) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; data[i][j] != null; j++) {
				if (i == 5) continue;
                Element temp = data[i][j];
                g.setColor(temp.getColor());
				g.fillRect((int)(temp.getX()), (int)(height - temp.getY() - temp.getHeight()), (int)(temp.getWidth()), (int)(temp.getHeight()));
			}
		}

		((Goal)(data[5][0])).draw(g, width, height);
    }

    public void updateLevel(ArrayList<String> actionQueue) throws Exception {
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
        }
    }
    
    public void resetLevel() {
        System.out.println(originalData == null);
        data = originalData;
    }
    
    public Element[][] getData() {
        return data;
    }
}
