Goal - A Game by Zayd Qumsieh

===== ABOUT =====
This is a game made in Java. It uses a game
engine that I created. Levels are all created
in the LevelFactory class. It's pretty easy to
figure out how level creation works, so it's easy
to create your own levels. See the LEVEL CREATION
section for more info.
If you're using Mac or Linux, you'll need to wait
somewhere between 10-25 seconds after the main display
shows up in order to start the game. I don't know
why, but it lags a bit.

===== HOW TO PLAY =====
To run the game, open a command prompt and type: "java Display".
Left / Right Arrow Keys to Move
Up Arrow Key to Jump
You can climb walls using the jump key.
You can use the above technique to wall jump as well.
Get to the yellow spinning cube to win.
There are 7 levels. Good luck!

===== LEVEL CREATION =====
Here's an example level, level 5.

	Player p = new Player(0, 1200, 50, 50);
    Goal goal = new Goal(1125, 550, 50, 50);
      
    Element[][] levelData = new Element[200][200];
      
    levelData[0][0] = p;
        
    levelData[2][0] = new Solid(0, 0, 100, 500);
    levelData[2][1] = new Solid(1100, 0, 100, 500);
      
    levelData[3][0] = new Enemy(500, 0, 50, 50);
      
    levelData[5][0] = goal;
        
    return new Level(Constants.WIDTH, Constants.HEIGHT, levelData);

Each Element takes 4 arguments. The first two are the x and y coordinates,
while the last two are the width and height, respectively. The elements
must be stored in the correct spots in levelData for the level to work. Here's
a key:

	level[0] contains players
	level[2] contains solids (walls / floor)
	level[3] contains enemies
	level[5] contains goals (note that only one goal must be collected in order to finish a level.

Once you've made your own level, you'll have to edit the switch case earlier in the 
LevelFactory.java file.

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
	}
		
	return null;

Add an extra case for your new level, createLevelEight(); Finally, go
to the Constants.java file and change NUMBER_OF_LEVELS to 8. Everything
else should work from there.

===== BUGS =====
In Level 4, it is possible to get out of bounds and get into the bottom left
corner. Once you're there, you're stuck there. This is because I have yet to
add a barrier on the edges of the screen.

Sometimes the Level 6 is skipped, leading you to a broken Level 7 where the
player doesn't show up. So far, this problem only appears on Linux computers.

I implemented walljumping by setting a variable enableJump to true whenever
you are touching a wall to the left or to the right. enableJump controlls
whether or not you can jump. However, I never set it to false if you leave
the wall, only after you jump. So, you could climb a wall, move off of it
without jumping, and then jump in midair.

Collision Detection near corners isn't the best. 
