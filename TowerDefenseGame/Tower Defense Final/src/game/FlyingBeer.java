package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FlyingBeer extends GameObject 
{
	private int x;
	private int y;
	private int vx;
	private int vy;
	private Control control;
	private State state;
	private double percentage;
	public FlyingBeer(Control control, State state,int x,int y, int vx, int vy)
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
		x += vx/2*(state.getElapsedTime()/1000.0);
		y +=  vy/2*(state.getElapsedTime()/1000.0);
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
		BufferedImage flyingBeer = control.getImage("beer.png");
		g.drawImage(flyingBeer,x,y,flyingBeer.getWidth()/2,flyingBeer.getHeight()/2,null);
		
	}
	
	@Override
	public double getPercentage() 
	{
		
		return percentage;
	}

}
