/**
 * This application creates a snail enemy object that goes down the path.
 * Once the snail makes its way down the path it expires and creates a new snail enemy.
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */


package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class Snail extends Enemy
{
	public double percentage;
	private State state;
	private Control control;
	
	/**
	 * Creates a Snail Object
	 * 
	 * @param control a control object
	 * @param state a state object
	 */
	public Snail(Control control, State state)
	{
		this.control = control;
		this.state =state;
		percentage = .001;
		
	    isVisible = true;
	    isExpired = false;	
	
	}
	
	
	@Override
	/**
	 * Updates the percentage that snail has traveled in relation to a tick of the timer
	 * 
	 * @param elapsedTime which is just the time that has gone by
	 * 
	 */
	public void update(double elapsedTime) 
	{
		
		percentage += .15*(state.getElapsedTime()/1000.0);
		if(percentage>=1)
		{
			isExpired = true;
//			state.nextFrameGameObjects.add(new Snail(control,state));
			state.setLives(-1);
			
			
		}
		
	   
	}
	
	/**
	 * Draw the snail and convert the percentage that the snail has traveled to a coordinate
	 * 
	 * @param g
	 */
	public void draw(Graphics g) 
	{
		Path p = control.getPath();
		Point loc = p.convertToCoordinates(percentage);
		BufferedImage snail = control.getImage("snail.png");
		g.drawImage(snail, loc.x - snail.getWidth()/2, loc.y - 
		snail.getHeight()/2, null);
		
	}

	public double getPercentage()
	{
		return percentage;
	}
	@Override
	public Point getLocation() 
	{
		Path p = control.getPath();
		Point loc = p.convertToCoordinates(percentage);
		// TODO Auto-generated method stub
		return loc;
	}


	@Override
	public boolean changeExpired() 
	{
			isExpired = !isExpired;
			return isExpired;
	}

}
