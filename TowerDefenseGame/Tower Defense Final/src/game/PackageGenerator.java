package game;

import java.awt.Graphics;

public class PackageGenerator extends GameObject
{
	private Control control;
	private State state;
	private double cooldownTime = 30;
	public PackageGenerator(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	@Override
	public void update(double elapsedTime) 
	{
		if(cooldownTime>=0)
		{
		cooldownTime -= elapsedTime/1000.0;
		}
		if(cooldownTime<=0)
		{
			state.addGameObject(new CarePackage(control,state));
			cooldownTime =30.2;
		}	
		
	}

	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPercentage() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
