/**
 * This application creates a Menu object. This just creates an area on 
 * the side of the screen from where the main game is animated for things 
 * such as towers, lives, money, and any other relative game components can be held.
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */



package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu extends GameObject 
{

	private Control control;
	private State state;
	
	public Menu(Control control, State state)
	{
		isVisible = true;
		isExpired = false;
		this.control = control;
		this.state = state;	
	}
	
	
	
	
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) 
	{
		g.setColor(Color.WHITE); //Draw a menu area white rectangle.
		g.fillRect(600, 0, 200, 600); 
		g.setColor(Color.BLACK);
		g.drawString("Lives: "+state.getLives(), 610, 60); //Draw the Lives Counter.
		g.drawString("Money: "+state.getMoney(),610,80); //Draw the Money Counter.
		
	}




	@Override
	public double getPercentage() 
	{
		// TODO Auto-generated method stub
		return 0;
	}




	
	

}
