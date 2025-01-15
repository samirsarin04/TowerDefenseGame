package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FlyingSalt extends GameObject 
{
	private int x;
	private int y;
	private int vx;
	private int vy;
	private Control control;
	private State state;
	private double percentage;
	public FlyingSalt(Control control, State state,int x,int y, int vx, int vy)
	{
		percentage = 0;
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	
	
	@Override
	public void update(double elapsedTime) 
	{
	percentage += .95*state.getElapsedTime()/1000.0;
		x += vx*(state.getElapsedTime()/1000.0);
		y +=  vy*(state.getElapsedTime()/1000.0);
		if(percentage>=1)
		{
			isExpired = true;
		}
		Enemy e = state.findNearestEnemy(x, y);
		if(e==null)
		{
			return;
		}
		if(e.getDistanceTo(e.getLocation(),x+20, y+20)<=30)
		{
			if(e instanceof SCargo)
			{
				state.setMoney(20);
				e.changeExpired();
				isExpired = !isExpired;
			}
			if(e instanceof Snail)
			{
				state.setMoney(10);
				e.changeExpired();
				isExpired = !isExpired;
			}
		}
				
			
			
			
		
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	@Override
	public void draw(Graphics g) 
	{
		BufferedImage flyingSalt = control.getImage("salt_crystals.png");
		g.drawImage(flyingSalt,x,y,flyingSalt.getWidth(),flyingSalt.getHeight(),null);
		
	}
	
	@Override
	public double getPercentage() 
	{
		
		return percentage;
	}

}
