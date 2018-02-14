import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

import java.awt.geom.*;

import java.lang.Math.*;

import java.util.ArrayList;

public class Goal extends Element {
	private int degrees = 0;

    public Goal(double x, double y, double width, double height) {
    	super(x, y, width, height, Color.YELLOW);   	
    	setUpdate(false);
    }

	public void draw(Graphics g, double width, double height) {
        /*
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		degrees += Constants.DEGREES_PER_SECOND;
		g2d.rotate(Math.toRadians(degrees));
		g2d.setColor(getColor());
		g.fillRect((int)(getX()), (int)(height - getY() - getHeight()), (int)(getWidth()), (int)(getHeight()));
		g2d.setTransform(old);*/

		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getColor());
		AffineTransform original = g2d.getTransform();
		// got this bit of code from https://stackoverflow.com/questions/8807717/java-rotate-rectangle-around-the-center
		// i modified it to suit my needs
        AffineTransform transform = new AffineTransform();
		degrees += Constants.DEGREES_PER_SECOND;
        Rectangle rectangle = new Rectangle((int)getX(), (int)height - (int)getY() - (int)getHeight(), (int)getWidth(), (int)getHeight());
        transform.rotate(Math.toRadians(degrees), rectangle.getX() + rectangle.width/2, rectangle.getY() + rectangle.height/2);
        Shape transformed = transform.createTransformedShape(rectangle);
        g2d.fill(transformed);
        g2d.setTransform(original);
	}
}
