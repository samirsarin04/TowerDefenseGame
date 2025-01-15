/**
 * This application creates a SCargo enemy object that goes down the path.
 * Once the snail makes its way down the path it expires and creates a new SCargo enemy.
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */


package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class SCargo extends Enemy
{
	public double percentage;
	private State state;
	private Control control;
	
	/**
	 * Creates a SCargo Object
	 * 
	 * @param control a control object
	 * @param state a state object
	 */
	public SCargo(Control control, State state)
	{
		this.control = control;
		this.state =state;
		percentage = .001;
		
	    isVisible = true;
	    isExpired = false;	
	}
	
	
	@Override
	/**
	 * Updates the percentage that SCargo has traveled in relation to a tick of the timer
	 * 
	 * @param elapsedTime which is just the time that has gone by
	 * 
	 */
	public void update(double elapsedTime) 
	{
		
		percentage += .05*state.getElapsedTime()/1000.0;
		if(percentage>=1)
		{
			isExpired = true;
			state.setLives(-5);
			
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
		BufferedImage sCargo = control.getImage("s-cargo.png");
		g.drawImage(sCargo, loc.x - sCargo.getWidth()/2, loc.y - 
		sCargo.getHeight()/2, null);
		
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
		return loc;
	}


	@Override
	public boolean changeExpired() 
	{
			isExpired = !isExpired;
			return isExpired;
	}

}
