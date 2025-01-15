package game;

import java.awt.Graphics;

public class GameOver extends GameObject
{

	private Control control;
	private State state;
	public GameOver(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
	    isExpired = false;
	}
	
	
	
	
	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("game_over.png"),0,0,null);
	}




	@Override
	public double getPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
