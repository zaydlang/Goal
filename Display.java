import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

import java.awt.geom.*;

import java.lang.Math.*;

import java.util.ArrayList;

public class Display extends JComponent implements ActionListener {
   private final Font font         = new Font("Roboto", Font.BOLD, 48);
   public static Level[] levels = new Level[Constants.NUMBER_OF_LEVELS];

   private Timer refreshTimer = new Timer(Constants.REFRESH_TIMER, this);
   private Timer updateTimer  = new Timer(Constants.UPDATE_TIMER, this);
   private Timer countTimer   = new Timer(Constants.COUNT_TIMER, this);

   static JLabel dummy = new JLabel();
   
   private static ArrayList<String> actionQueue = new ArrayList<String>();
   
   private long startTime;
   private int i = 0;
   private int time = 0;
   private int currentLevel = 0;
   private Player temp;
   private class ActionQueuer extends AbstractAction {
      String action;
      
      ActionQueuer(String id) {  
      System.out.println("I WAS CREATED :D " + id);   
         action = id;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
         System.out.println("I WAS PRESSED :D " + action);   
         actionQueue.add(action);
      }
   }
   
   public Display() {
      init();
      //run();
   }

   public void init() {
      Player p1 = new Player(0, 1200, 50, 50);
      Player p2 = new Player(0, 1200, 50, 50);
      Goal goal = new Goal(1100, 50, 50, 50);
      Element[][] level1Data = new Element[200][200];
      Element[][] level2Data = new Element[200][200];
      level1Data[0][0] = p1;
      level2Data[0][0] = p2;
      level1Data[5][0] = goal;
      level2Data[5][0] = goal;
      levels[0] = new Level(Constants.WIDTH, Constants.HEIGHT, level1Data);
      level2Data[2][0] = new Solid(600, 100, 100, 500);
      levels[1] = new Level(Constants.WIDTH, Constants.HEIGHT, level2Data);
      //data[2][1] = new Solid(800, 0, 100, 500);
      //data[2][2] = new Solid(0, 200, 400, 100);
      //data[2][2] = new Solid(700, 100, 100, 100);
      //data[2][3] = new Solid(400, 200, 100, 100);

      setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT)); 
      setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
      setLayout(null);
      
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("LEFT"), Constants.MOVE_LEFT);
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("RIGHT"), Constants.MOVE_RIGHT);
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("released LEFT"), Constants.MOVE_LEFT_RELEASED);
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("released RIGHT"), Constants.MOVE_RIGHT_RELEASED);
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("UP"), Constants.JUMP);
      dummy.getInputMap(Constants.IFW).put(KeyStroke.getKeyStroke("Z"), Constants.GRAPPLE);
      
      dummy.getActionMap().put(Constants.MOVE_LEFT, new ActionQueuer(Constants.MOVE_LEFT));
      dummy.getActionMap().put(Constants.MOVE_RIGHT, new ActionQueuer(Constants.MOVE_RIGHT));
      dummy.getActionMap().put(Constants.MOVE_LEFT_RELEASED, new ActionQueuer(Constants.MOVE_LEFT_RELEASED));
      dummy.getActionMap().put(Constants.MOVE_RIGHT_RELEASED, new ActionQueuer(Constants.MOVE_RIGHT_RELEASED));
      dummy.getActionMap().put(Constants.JUMP, new ActionQueuer(Constants.JUMP)); 
      dummy.getActionMap().put(Constants.GRAPPLE, new ActionQueuer(Constants.GRAPPLE)); 

      add(dummy);
      
      setVisible(true);
      refreshTimer.start();
      updateTimer.start();
      countTimer.start();
      startTime = System.currentTimeMillis();
   }
   
   public void paint(Graphics g) {
      i++;
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
      levels[currentLevel].drawLevel(g);
      
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString("Time: " + time, Constants.TIMER_X, Constants.TIMER_Y);

      if (System.currentTimeMillis() - startTime >= 1000) {
         System.out.println("FPS: " + i);
         startTime = System.currentTimeMillis();
         i = 0;
      }
   }

   @Override
   public synchronized void actionPerformed(ActionEvent e) {
      if (e.getSource() == refreshTimer) {
//System.out.println("REPAINT");
         repaint();
      }

      if (e.getSource() == updateTimer) {
//System.out.println("BUILDING");
      	 if (levels[currentLevel].buildLevel(actionQueue)) {
      	    if (++currentLevel == Constants.NUMBER_OF_LEVELS) {
      	        temp = new Player(0, 1200, 50, 50);
      	        System.exit(1); // this is a really bad way to say "you won", but whatever.
      	    } 
      	 }
      }

	  if (e.getSource() == countTimer) {
	     time++;
	  }
   }

   public static void main(String[] args) {
      Display disp = new Display();

      JFrame frame = new JFrame();
      frame.setTitle("Paintball");
      frame.add(disp);
      frame.pack();
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}
