public class LevelFactory {
	public static Level getLevel(int id) {
		switch (id) {
			case 0: 
				return createLevelOne();
			case 1:
				return createLevelTwo();
			case 2:
				return createLevelThree();
			case 3:
				return createLevelFour();
			case 4:
				return createLevelFive();
		    case 5:
		        return createLevelSix();
		}
		
		return null;
	}
      
	
	public static Level createLevelOne() {
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1100, 50, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
      
        levelData[5][0] = goal;
      
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
	
	public static Level createLevelTwo() {
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1100, 50, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
      
        levelData[2][0] = new Solid(600, 0, 100, 500);
        
        levelData[5][0] = goal;
      
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
	
	public static Level createLevelThree() {
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1125, 550, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
        
        levelData[2][1] = new Solid(1100, 400, 100, 100);
        levelData[2][0] = new Solid(600, 0, 100, 500);
      
        levelData[5][0] = goal;
      
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
	
	public static Level createLevelFour() {
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1125, 550, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
        
        levelData[2][0] = new Solid(750, 400, 575, 100);
        levelData[2][1] = new Solid(500, 100, 100, 1000);
        levelData[2][2] = new Solid(750, 0, 100, 400);
      
        levelData[5][0] = goal;
      
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
	
	public static Level createLevelFive() {
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1125, 550, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
        
        levelData[2][0] = new Solid(0, 0, 100, 500);
        levelData[2][1] = new Solid(1100, 0, 100, 500);
      
        levelData[3][0] = new Enemy(500, 0, 50, 50);
        
        levelData[5][0] = goal;
        
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
	
	public static Level createLevelSix() {
	    Player p = new Player(800, 801, 50, 50);
        Goal goal = new Goal(125, 25, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
        
        levelData[2][0] = new Solid(0, 600, 950, 100);
        levelData[2][1] = new Solid(250, 400, 950, 100); 
        levelData[2][2] = new Solid(0, 200, 950, 100); 
        levelData[2][3] = new Solid(1100, 0, 100, 800);
        levelData[2][4] = new Solid(0, 0, 100, 800);
      
	    levelData[3][0] = new Enemy(100, 801, 50, 50);
        
        levelData[5][0] = goal;
        
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);
	}
}
