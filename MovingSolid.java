import java.awt.Color;

public class MovingSolid extends Solid {
    private double[] x;
    private double[] y;
    private double speed;
    private double waitTime;
    private double xVel;
    private double yVel;
    
    private int index = 0;
	private double dt = System.currentTimeMillis();
	private double wait;
    public MovingSolid(double[] x, double[] y, double speed, double waitTime, double width, double height) {
    	super(x[0], y[0], width, height);
        
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.waitTime = waitTime;
        this.wait = System.currentTimeMillis();
        this.dt = System.currentTimeMillis();
    	setUpdate(true);
    }
    
    public Data move(String action, Data data) {
        if (System.currentTimeMillis() - wait < waitTime) return data;
        dt = System.currentTimeMillis() - dt;
        
        if (getX() != x[index] || getY() != y[index]) {
            double theta = Math.atan(x[index] - getX() / y[index] - getY()); // radians
            xVel = Math.cos(theta) * speed * dt;
            yVel = Math.cos(theta) * speed * dt;
            updatePos(xVel, yVel);
            
            double newTheta = Math.atan(x[index] - getX() / y[index] - getY()); // radians
            if (newTheta != theta) { // we've overshot
                index++;
                setX(x[index]);
                setY(y[index]);
            }
        }
        
        return data;
    }
    
    public void update() {
    	updatePos(xVel, yVel);
    }
	
    public void updatePos(double xVel, double yVel) {
        //System.out.println(getX() + " " + getY() + " " + xVel + " " + yVel);
        setX(getX() + xVel);
        setY(getY() + yVel);
    }
}
