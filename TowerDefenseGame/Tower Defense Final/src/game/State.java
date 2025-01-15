/**
 * This application creates a State object. The State object holds the current "state"
 * of the game. State keeps track of the current Game Objects which are supposed to be
 * being animated or remain on the screen and continues to keep track of their current conditions.
 * State is used by view to draw what is currently supposed to be on screen.
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */



package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import path.Path;

public class State 
{
	 List<GameObject> currentFrameGameObjects;
	 List<GameObject> nextFrameGameObjects;
	 private int money = 100;
	 private int lives = 100;
	 boolean run = true;
	 private double elapsedTime;
	 private double totalTime;
	 private double startTime;
	 private int saltCost = 100;
	 private int beerCost = 400;
	
	
	 /**
	  * Creates the currentFrameGameObjects list  
	  */
	 public State()
	 {
		 currentFrameGameObjects = new ArrayList<GameObject>();
		 totalTime = 0;
		 startTime = System.currentTimeMillis();
		 elapsedTime =0;
	 }
	 
	 /**
	  * get the currentFrameObjects 
	  * 
	  * @return return the current FrameGameObjects
	  */
	 public List<GameObject> getFrameObjects ()
	  {
	      return currentFrameGameObjects;
	  }
	 
	 /**
	  * starts the Frame by adding all the frame objects to the new list
	  */
	 public void startFrame ()
	 {
		 nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
	     nextFrameGameObjects.addAll(currentFrameGameObjects);	// Add all the current ones to the new list.  This is more clear
	    
	     elapsedTime = System.currentTimeMillis() - startTime;
	     totalTime+=elapsedTime;
	     startTime = System.currentTimeMillis();
	 }
	 
	 public void setMoney(int newMoney)
	 {
		 this.money += newMoney;
	 }
	 public int getMoney()
	 {
		 return money;
	 }
	 public void setLives(int newLives)
	 {
		 this.lives += newLives;
	 }
	
	 public int getLives()
	 {
		 return lives;
	 }
//	 public void changeSaltCost()
//	 {
//		 saltCost+=50;
//	 }
	 
	 public int getSaltCost()
	 {
		 return saltCost;
	 }
	 public int getBeerCost()
	 {
		 return beerCost;
	 }
	 
	 
	 /**
	  * Finish the frame and go to the next GameObject in the list
	  */
	 public void finishFrame ()
	 {
		if(lives <=0)
		{
			run = false;
		}
		
		for(GameObject go: currentFrameGameObjects)
		{
			if (go.isExpired())
			{
				nextFrameGameObjects.remove(go);
			}
		}
		 
		 currentFrameGameObjects = nextFrameGameObjects;
	     nextFrameGameObjects = null; // I added this -- it makes it clear there is only a current list now.
	     
	 }
	 
	 /**
	  * Get the amount of time elapsed during an interval.
	  * 
	  * @return return the amount of time elapsed.
	  */
	 public double getElapsedTime()
	 {
		 return elapsedTime;
	 }
	 
	 /**
	  * Get the total time the game has been running.
	  * 
	  * @return return the total game time.
	  */
	 public double getTotalTime()
	 {
		 return totalTime;
	 }
	 
	 /**
	  * Add a GameObject to the next GameObject list
	  * @param go a gameObject
	  */
	 public void addGameObject (GameObject go)
	 {
		 nextFrameGameObjects.add(go);
	 }

	 public Enemy findNearestEnemy(int x, int y)
	 {
		Path newPath = new Path();
		Point towerPoint = new Point(x,y);
		Enemy bestEnemySoFar = null;
		double bestLengthSoFar = 0;
		int count =0;
		for(GameObject go: currentFrameGameObjects)
			if(go instanceof Enemy)
			{
				Point p = ((Enemy) go).getLocation();
				double length =newPath.calculateLength(towerPoint, p);
				if(length<bestLengthSoFar||count == 0)
				{
					bestLengthSoFar = length;
					bestEnemySoFar = (Enemy) go;
				}
				count++;
			}
		 
		 
		 return bestEnemySoFar;
				 
				 
	 }
	 
	 
	
}
