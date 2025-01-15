/**
 * This application creates a view object which is used to do the panel
 * setup and painting throughout the game. The view object creates a JFrame and does
 * the animation for the Game Objects in the game using their own draw methods. 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */


package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel
{
	private Control control;
	private State state;
	
	/**
	 * Build the GUI by creating a JFrame object and setting the size of the frame.
	 * Make the Frame Visible as well.
	 * 
	 * @param control
	 * @param state
	 */
	public View(Control control, State state)
	{
		this.control = control;
		this.state = state;
		
		JFrame frame = new JFrame("Tower Defense");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		
		this.setPreferredSize(new Dimension(800,600));
		this.setMaximumSize(new Dimension(800,600));
		frame.setContentPane(this);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * draw the background first and then check if all the items in FrameObjects are GameObjects
	 * and that they are visible and not expired
	 * 
	 * @param g
	 */
	public void paint(Graphics g)
	{
		
		g.drawImage(control.getImage("path_2.jpg"), 0, 0, null);
		
		for (GameObject go : state.getFrameObjects())
			if (go.isVisible() && !go.isExpired())
				go.draw(g);
	}
	
}

