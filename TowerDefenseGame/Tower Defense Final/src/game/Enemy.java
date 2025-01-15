package game;

import java.awt.Point;

import path.Path;

abstract public class Enemy extends GameObject
{
	protected double percentage;
	protected State state;
	protected Control control;
	FlyingSalt flyingSalt;
	
	
	abstract public Point getLocation();
	
	public double getDistanceTo(Point p, int x2, int y2)
	{
		
		double distance = Point.distance(p.getX(),p.getY(),x2,y2);
		return distance;
	}
	
	abstract public boolean changeExpired();
	
		
	
}
