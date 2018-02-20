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


public class Data {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Goal> goals = new ArrayList<Goal>();
    private ArrayList<Solid> solids = new ArrayList<Solid>();
    private ArrayList<MovingSolid> movingSolids = new ArrayList<MovingSolid>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    private double width;
    private double height;
    
    private Flags flags;
    
	public Data(double width, double height) {
		this.width = width;
		this.height = height;
		
		flags = new Flags();
	}
	
	public void update(ArrayList<String> actionQueue) {
		for (int i = 0; actionQueue.size() != 0; actionQueue.remove(0)) {
            String action = actionQueue.get(0);
            
            for (int j = 0; j < players.size(); j++) {
            	flags = players.get(j).move(action, this, flags);
            }
            //data = data[3][0].move("", data);
        }
        
        for (int j = 0; j < players.size(); j++) {
          	flags = players.get(j).move("", this, flags);
        }
           
        for (int i = 0; i < enemies.size(); i++) {
            flags = enemies.get(i).move("", this, flags);
            /* TODO: better handling, use Flags class
            if (data == null) {
                resetLevel();
                return;
            }*/
        }
	}
	
	public Flags build() {
	    for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
		}
		
	    for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
		}

		// TODO: better handling, use Flags class
		return flags;
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < players.size(); i++) {
			Element temp = players.get(i);
			g.setColor(temp.getColor());
			g.fillRect((int)(temp.getX()), (int)(height - temp.getY() - temp.getHeight()), (int)(temp.getWidth()), (int)(temp.getHeight()));
		}
		
		for (int i = 0; i < solids.size(); i++) {
			Element temp = solids.get(i);
			g.setColor(temp.getColor());
			g.fillRect((int)(temp.getX()), (int)(height - temp.getY() - temp.getHeight()), (int)(temp.getWidth()), (int)(temp.getHeight()));
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			Element temp = enemies.get(i);
			g.setColor(temp.getColor());
			g.fillRect((int)(temp.getX()), (int)(height - temp.getY() - temp.getHeight()), (int)(temp.getWidth()), (int)(temp.getHeight()));
		}
		
		for (int i = 0; i < goals.size(); i++) {
			goals.get(i).draw(g, width, height);
		}
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void addGoal(Goal g) {
		goals.add(g);
	}

	public void addSolid(Solid s) {
		solids.add(s);
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public void addMovingSolid(MovingSolid ms) {
		movingSolids.add(ms);
		solids.add(ms);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Goal> getGoals() {
		return goals;
	}
	
	public ArrayList<Solid> getSolids() {
		return solids;
	}
}
