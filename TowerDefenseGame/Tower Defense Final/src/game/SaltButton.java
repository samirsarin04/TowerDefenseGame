/**
 * This application creates a button for the Salt tower within the menu bounds.
 * The button is a specific rectangle containing a picture of the salt tower that when clicked 
 * within its bounds, adds a new tower object to the game.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */




package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SaltButton extends GameObject implements Clickable
{
	private Control control;
	private State state;
	private int x,y,width,height;
	
	public SaltButton(Control control, State state)
	{
		isVisible = true;
		isExpired = false;
		this.control = control;
		this.state = state;
		this.x = 625;
		this.y = 400;
		this.width = 70;
		this.height = 77;
				
	}

	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(Color.BLACK); //Draw the button
		g.fillRoundRect(x,y,width,height,10,10);
		if(state.getMoney() >= state.getSaltCost())
		{
		g.setColor(Color.LIGHT_GRAY);
		}
		else
		{
			g.setColor(Color.GRAY);
		}
		
		g.fillRoundRect(x+2,y+2,width-3,height - 3,8,8);
		g.drawImage(control.getImage("salt.png"),x+10,y+10,width - 20, height - 20,null); //Draw a salt image on the button.
		if(state.getMoney()>=state.getSaltCost())
		{
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.RED.darker());
		}
		g.setFont(new Font ("Arial", Font.BOLD,24));
		g.drawString("100", x+4, y+height-4);
	
	}

	/**
	 * Tells the Salt Button Object what to do if there is a click.
	 */
	@Override
	public boolean consumeClick() 
	{
	if(control.getMouseX()>=x&control.getMouseX()<=x+width&&control.getMouseY()>y&&control.getMouseY()<=y+height) //Check to see if the click is in the SaltButton area.
	{
		if(state.getMoney()<state.getSaltCost())
		{
			return false;
		}
		state.currentFrameGameObjects.add(new SaltTower(control,state));//Add a new salt tower to the current game object list.
		state.setMoney(-state.getSaltCost());
		
		return true; //Click was consumed.
	}
		return false; //Click wasn't consumed.
	}

	@Override
	public double getPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
