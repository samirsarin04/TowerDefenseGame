/**
 * This application creates a control object which holds all of the key parts of the tower 
 * defense game. When the control object is called in the main method, it will run the game.
 * Control will act as the "center point" for all the other classes and tell them what to do.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */


package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener
{
	private Path path;
	private State state;
	private View view;
	private int mouseX;
	private int mouseY;
	private  Map<String , BufferedImage> imageCache;
	
	
	/**
	 * Create a Control Object and make the GUI Thread do the rest of the setup
	 */
	public Control()
	{
		SwingUtilities.invokeLater(this);
	}
	
	
	@Override
	/**
	 * Create the next frame and add a background object and two snails to the gameObject list and then finish the frame
	 * 
	 */
	public void run() 
	{
		imageCache = new TreeMap<String, BufferedImage>();
		
		System.out.println("GUI Thread");
		state = new State();
		view = new View(this,state);
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
		ClassLoader myLoader = this.getClass().getClassLoader();
	    InputStream pathStream = myLoader.getResourceAsStream("resources/path1.txt");
	    Scanner pathScanner = new Scanner(pathStream);
	    path = new Path(pathScanner);
		
		state.startFrame();  // Prepares the creation of the 'next' frame
		state.addGameObject(new Background(this,state));				// Add one background object to our list
        state.addGameObject(new Menu(this,state)); //Add one menu object to our list.
        state.addGameObject(new SaltButton(this,state)); //Add one SaltButton object to our list.
        state.addGameObject(new BeerButton(this,state));
        state.addGameObject(new Snail(this,state)); //Add one Snail object to our list.
        state.addGameObject(new SCargo(this,state)); //Add one SCargo object to our list.
        state.addGameObject(new EnemyGenerator(this,state));
        state.addGameObject((new PackageGenerator(this,state)));
        state.finishFrame();    // Mark the next frame as ready
       	
       
		view.repaint();
		
        Timer t = new Timer(16,this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();  
	}
	
		
	 

	/**
	 * Get the image given a filename. Get the image from the resources package
	 * 
	 * @param filename
	 * @return the image found in the file
	 */
	public BufferedImage getImage (String filename)
	    {
			if(imageCache.containsKey(filename))
			{
				return imageCache.get(filename);
			}
	        try
	        {
	        	System.out.println("loading"+filename);
	            ClassLoader myLoader = this.getClass().getClassLoader();
	            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
	            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
	            imageCache.put(filename, image);
	            return image;
	            
	        }
	        catch (IOException e)
	        {
	            System.out.println("Could not find or load resources/" + filename);
	            System.exit(0);  // Close the frame, bail out.
	            return null;  // Does not happen, the application has exited.
	        }
	        
	    }
	
	/**
	 * Get the path
	 * 
	 * @return the path
	 */
	public Path getPath()
	{
		return path;
	}
	


	@Override
	/**
	 * Every time an action is performed, in the case of this program, a timer ticks, 
	 * each GameObject is updated and stored into a new frame.
	 * 
	 * @param e a tick of the timer
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		state.startFrame();
		
		if(state.run == false)
        {
        	state.addGameObject(new GameOver(this,state));
        }
		else
		{
        for (GameObject go : state.getFrameObjects())
        {
        	go.update(state.getElapsedTime());   
        }
		}	
		state.finishFrame();
        view.repaint();
	}


	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * When the mouse moves, set the field mouseX and mouseY values equal to the cursor x and y values.
	 */
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		
		mouseY = e.getY();
		
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * When the mouse is released, see if an object is clickable and if it is, use the consumeClick method on that 
	 * clickable object, then break, so we don't use another incorrect objects consumeClick.
	 */
	@Override
	public void mouseReleased(MouseEvent e) 
	{
	List<GameObject> list = state.getFrameObjects(); //A list of current Game Objects.
		
		for(GameObject go : list)
		{
			if(go instanceof Clickable) //If the game Object is clickable.
			{
				Clickable c = (Clickable) go; //Type cast the game object into a clickable type object.
				if(c.consumeClick()) // If the click is consumed break the loop.
				{
					break;
				}
			}
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	/**
	 * Returns the current Mouse X coordinate.
	 * @return MouseX the int value of the current mouse X value.
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	/**
	 * Returns the current Mouse Y coordinate.
	 * @return MouseY the int value of the current mouse Y value.
	 */
	public int getMouseY()
	{
		return mouseY;
	}
}
