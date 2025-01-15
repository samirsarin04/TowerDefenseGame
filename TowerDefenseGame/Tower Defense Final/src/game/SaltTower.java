/**
 * This application creates the SaltTower object. This SaltTower is
 * Clickable and when clicked follows the cursor around the screen until
 * it is clicked again to be dropped on the screen in place for use in the game.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SaltTower extends GameObject implements Clickable
{
	
	private State state;
	private Control control;
	private boolean isMoving; //Field to check if the object is moving
	private int x = 695;
	private int y = 390;
	private double cooldownTime = 1.0; //time that we're not allowed to fire.
	
	/**
	 * SaltTower Constructor, create a new SaltTower Object.
	 * 
	 * @param control a control object
	 * @param state a state object
	 */
	public SaltTower(Control control, State state)
	{
		this.state = state;
		this.control = control;
		isVisible = true; 
		isExpired = false;
		isMoving = true;	
	}
	
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * Every time a specific amount of time passes, if the Salt Tower object is moving, 
	 * sets its location equal to the location of the mouse pointer. 
	 * 
	 * @param elapsedTime a double amount of time between each update.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		if(isMoving)
		{
			x = control.getMouseX();
			y = control.getMouseY();
		}
		else
		{
			if(cooldownTime>=0)
			{
			cooldownTime -= elapsedTime/1000.0;
			}
			if(cooldownTime<=0)
			{
				Enemy e = state.findNearestEnemy(x, y);
				if(e == null)
				{
					return;
				}
				if(e.getDistanceTo(e.getLocation(),x,y)<100)
				{
					int vx = 3*(int)(e.getLocation().getX()-x);
					int vy = 3*(int)(e.getLocation().getY()-(y-20));
					state.addGameObject(new FlyingSalt(control,state,x,y,vx,vy));
					cooldownTime += 1.2;
				}
				
			}
		}
		
		
		
	}
/**
 * Draws a salt tower at the specified x and y coordinates (mouse location).
 * 
 * @param g a graphics object.
 */
	@Override
	public void draw(Graphics g) 
	{
		BufferedImage saltTower = control.getImage("salt.png");
		g.drawImage(saltTower,x,y,saltTower.getWidth(),saltTower.getHeight(),null);
	}
/**
 * Tells the SaltTower Object what to do when there is a mouse click.
 */
	@Override
	public boolean consumeClick() 
	{
		
		if(isMoving) //Check to see if the tower is moving.
		{
			if(x>0&&x<600&&y>0&&y<600) //Check to see if the tower is being placed in a legal spot.
			{
				if(control.getPath().distanceToPath(x, y)<40)
				{
					return false;
				}
				
				isMoving = !isMoving; //Change the state of the tower.
				
			}
			else
			{
				isExpired = true; //If not in legal coordinates, expire.
			}
			
		return true; // Click has been consumed.
		}
		
		return false; //Click wasn't consumed.
	}


@Override
public double getPercentage() 
{
	// TODO Auto-generated method stub
	return 0;
}

}
