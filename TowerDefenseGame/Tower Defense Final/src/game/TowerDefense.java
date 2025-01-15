/**
 * This application runs the Tower Defense game and holds the main method for doing so.
 * It calls the control object which runs the entire game on the GUI thread. This is the main thread.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */
package game;

public class TowerDefense 
{

	public static void main (String[] args)
	{
		 new Control (); // The Control constructor will do the rest.
		
	}
	
	
}
