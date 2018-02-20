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
		    case 6:
		        return createLevelSeven();
		    case 7:
		    	return createLevelEight();
		}
		
		return null;
	}
      
	
	public static Level createLevelOne() {
		Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
        level.addGoal(new Goal(1100, 50, 50, 50));
      
        return level;
	}
	
	public static Level createLevelTwo() {
		Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(550, 0, 100, 200));
	    
        level.addGoal(new Goal(1100, 50, 50, 50));
      
        return level;
	}
	
	public static Level createLevelThree() {
		Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(800, 0, 150, 100));
	    level.addSolid(new Solid(950, 0, 150, 250));
	    level.addSolid(new Solid(1100, 0, 100, 400));
	    
        level.addGoal(new Goal(1125, 425, 50, 50));
      
        return level;
	}
	
	public static Level createLevelFour() {
		Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(700, 0, 100, 100));
	    level.addSolid(new Solid(900, 0, 100, 250));
	    level.addSolid(new Solid(1100, 0, 100, 400));
	    
        level.addGoal(new Goal(1125, 425, 50, 50));
      
        return level;
	}
	
	public static Level createLevelFive() {
        Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(1100, 400, 100, 100));
	    level.addSolid(new Solid(600, 0, 100, 500));
	    
        level.addGoal(new Goal(1125, 550, 50, 50));
      
        return level;
	}
	
	public static Level createLevelSix() {
        Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(750, 400, 575, 100));
	    level.addSolid(new Solid(500, 100, 100, 1000));
	    level.addSolid(new Solid(750, 0, 100, 400));
	    
        level.addGoal(new Goal(1125, 550, 50, 50));
      
        return level;
	}
	
	public static Level createLevelSeven() {
        Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(0, 1200, 50, 50));
	    
	    level.addSolid(new Solid(0, 0, 100, 500));
	    level.addSolid(new Solid(1100, 0, 100, 500));
	    
	    level.addEnemy(new Enemy(500, 0, 50, 50));
        level.addGoal(new Goal(1125, 550, 50, 50));
      
        return level;
	}
	
	public static Level createLevelEight() {
        Level level = new Level(Constants.WIDTH, Constants.HEIGHT);
	    
	    level.addPlayer(new Player(800, 800, 50, 50));
	    
	    level.addSolid(new Solid(0, 600, 950, 100));
	    level.addSolid(new Solid(250, 400, 950, 100));
	    level.addSolid(new Solid(0, 200, 950, 100));
	    level.addSolid(new Solid(1100, 0, 100, 800));
	    level.addSolid(new Solid(0, 0, 100, 800));
	    
	    level.addEnemy(new Enemy(100, 701, 50, 50));
        level.addGoal(new Goal(125, 25, 50, 50));
      
        return level;
	}
	
	public static Level createLevelNine() {
        return null;
	}

	public static Level createLevelTen() {/*
	    Player p = new Player(0, 1200, 50, 50);
        Goal goal = new Goal(1125, 550, 50, 50);
      
        Element[][] levelData = new Element[200][200];
      
        levelData[0][0] = p;
        
        levelData[2][0] = new Solid(0, 600, 350, 100);
        levelData[2][1] = new Solid(550, 600, 350, 100);
      	levelData[4][2] = new MovingSolid(450, 600
        
        levelData[5][0] = goal;
        
        return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);*/
        return null;
	}
}
