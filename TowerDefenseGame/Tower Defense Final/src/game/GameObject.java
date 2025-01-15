/**
 * This abstract class is a superclass framework for all objects within the game.
 * It provides fields and methods for all objects that need to be used/animated within 
 * the game. It groups them all together to make working with them easier.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */

package game;

import java.awt.Graphics;

abstract public class GameObject 
{
	 protected boolean isVisible; 
	 protected boolean isExpired;
	 
	 public boolean isVisible() { return isVisible; }
	 public boolean isExpired() { return isExpired; }

	 abstract public void update (double elapsedTime);
	 abstract public void draw (Graphics g);
	 abstract public double getPercentage();
}
