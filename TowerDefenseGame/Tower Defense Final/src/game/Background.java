/**
 * This application creates a new background object. The Background object
 * draws a new background which in the case of our game is the path 
 * image. The background goes behind everything else so everything else can be drawn on top.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */
package game;

import java.awt.Graphics;

public class Background extends GameObject
{
	private Control control;
	private State state;
	/**
	 * Create a new Background Object and make it visible and not expired
	 * @param control a control object
	 */
	public Background(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
	    isExpired = false;
	}


	@Override
	/**
	 * @param elapsedTime the amount of time gone by
	 */
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Draw the background object
	 * 
	 * @param g a graphics object
	 */
	public void draw(Graphics g) 
	{
		  g.drawImage(control.getImage("path_2.jpg"), 0, 0, null);
	}


	@Override
	public double getPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
