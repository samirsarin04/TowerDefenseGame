package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CarePackage extends GameObject implements Clickable
{
	private State state;
	private Control control;
	private double expireTimer = 5.0;
	private double spawnTimer = 30.0;
	private int x;
	private int y;
	private double cooldownTime = 5.0;
	
	public CarePackage(Control control, State state)
	{
		this.state = state;
		this.control = control;
		isVisible = true;
		isExpired = false;
		x = (int)(Math.random()*600);
		y = (int) (Math.random()*600);
		
	}
	@Override
	public void update(double elapsedTime) 
	{
		
	}
		
	

	@Override
	public void draw(Graphics g) 
	{
		BufferedImage carePackage = control.getImage("box.png");
		g.drawImage(carePackage,x,y,50,50,null);
	}

	@Override
	public double getPercentage() 
	{
		
		return 0;
	}

	@Override
	public boolean consumeClick() 
	{
		if(control.getMouseX()>x&&control.getMouseX()<x+50&&control.getMouseY()>y&&control.getMouseY()<y+50)
		{
			state.setMoney(100);
			isExpired = !isExpired;
			return true;
			
		}
		return false;
	}

}
