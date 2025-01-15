package path;

/**
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 7,2022
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Path 
{
	
	private ArrayList<Point> pointsArray; //Private ArrayList field to hold points.
	int pointCount; //Will hold the size of the ArrayList.
	int totalLength;
	
	/**
	 * Creates Blank path Object which has no points in the list
	 */
	public Path()
	{
		pointsArray =  new ArrayList<Point>(); //Initialize a new blank Path with nothing in the ArrayList.
	}
	
	/**
	 * Adds the points from the list to create a path
	 *  
	 * @param in a Scanner to read the .txt file
	 */
	public Path(Scanner in) //New path with a running scanner as a paramter.
	{
		pointsArray = new ArrayList<Point>(); //Create a new ArrayList to hold points.
		pointCount = in.nextInt(); //The amount of points is the first int in the file.
		for(int i=0;i<pointCount;i++)
		{	
			int newX = in.nextInt(); //The first int in the pair is the x coordinate of the next point.
			int newY = in.nextInt(); //The second int in the pair is the y coordinate of the next point.
			Point newPoint = new Point(newX,newY); //Create a new point with the x and y coordinate.
			
			pointsArray.add(newPoint); //Add the point to the ArrayList.
			
		}
		
	}
	/**
	 * Get the amount of points in the list
	 * 
	 * @return the size of the list
	 */
	public int getPointCount()
	{
		return pointCount;  //Returns the size of the ArrayList.
	}
	
	/**
	 * get the X coordinate at the specific location n in the list
	 * 
	 * @param n the specific location in the list
	 * @return the x coordinate
	 */
	public int getX(int n)
	{
		return (int)pointsArray.get(n).getX(); //Returns the x coordinate of a specific point in the ArrayList.
	}
	
	/**
	 * get the Y coordinate at the specific location n in the list
	 * @param n the specific location in the list
	 * @return the y coordinate
	 */
	public int getY(int n)
	{
		return (int)pointsArray.get(n).getY(); //Returns the y coordinate of a specific point in the ArrayList.
	}
	
	/**
	 * add the points x and y to the list and increase the size by 1
	 * 
	 * @param x a x coordinate
	 * @param y a y coordinate
	 */
	public void add(int x, int y)
	{
		Point newPoint = new Point(x,y); //Create a new point to be added given the parameter x and y coordinates.
		pointsArray.add(newPoint); //Add the new point to the ArrayList.
		pointCount++; //Increment the amount of points in the Array by one so they can be drawn.
	}
	
	/**
	 * Helper method to calculate the length of a segment
	 * 
	 * @param point1 a point on the path in the pointsArray
	 * @param point2 another point one index further in the pointsArray
	 * @return the length between the two points
	 */
	public double calculateLength(Point point1, Point point2)
	{
		double length;
		length = Math.sqrt(Math.pow(point2.getX()-point1.getX(),2)+Math.pow(point2.getY()-point1.getY(), 2));
		return length;
	}
	
	/**
	 * Turns the points list into a version that a scanner could read
	 * 
	 * @return the string representation of the points array
	 */
	public String toString()
	{
		String result = pointsArray.size()+" "; //The start of the string representation is the amount of points.
		for(int i=0;i<pointsArray.size();i++)
		{
			result = result+(int) pointsArray.get(i).getX()+" "+(int) pointsArray.get(i).getY()+" "; //Create a string represenation of the x and y coordinates separated by spaces.
		}
		return result; //return the string representation of the pointsArray ArrayList separated by spaces to be read by the scanner.
	}
	
	/**
	 * Draw all the lines in the list and then draw lines between them
	 * 
	 * @param g a graphics object
	 */
	public void draw(Graphics g)
	{
	
		
		//Loop draw all the points up to the amount of points in the Path arraylist.
		for(int i =0;i<pointCount-1;i++)
		{
			//Make line red.
			g.setColor(Color.RED);
			//Draw a line from a point to the next point.
			g.drawLine((int)getX(i), (int)getY(i),(int)getX(i+1), (int)getY(i+1));
		}
	}

	
	
	
	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned
	 * point object (or the caller could be changing the path).
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point convertToCoordinates(double percentage) 
	{
		double percentageAcross =0;
		double totalLength =0;
		double pixelsThroughSegment=0;
		double segmentLength=0;
		int firstPoint=0;
		int segment=0;
		
		
	for(int i=0;i<pointCount-1;i++)
		{
			totalLength+=calculateLength(pointsArray.get(i+1),pointsArray.get(i)); //Loop through each segment and add up the distances.
		}
		
		double pixelsTraveled = totalLength*percentage; //This is the amount of pixels we have traveled on our path.
		
		for(int i=0;i<pointCount-1;i++)
		{
			segmentLength = calculateLength(pointsArray.get(i+1),pointsArray.get(i)); //The current segments length
			pixelsTraveled-=segmentLength; //Subtract the current length from distance traveled until it is negative to find what segment we are on.
		
		if(pixelsTraveled<0)
		{
			 pixelsThroughSegment = pixelsTraveled+segmentLength; //If the value becomes negative, we add back on the current segment length to get how many pixels we have traveled through the proper segment.
			 firstPoint = i; //The first point we will use to calculate our new point is the first point of the current segment.
			 break; 
		}
		}
		
		percentageAcross = (pixelsThroughSegment)/segmentLength; //Percentage through segment
		int xResult = (int)((1-percentageAcross)*pointsArray.get(firstPoint).getX()+percentageAcross*pointsArray.get(firstPoint+1).getX()); //Using formula Dr. Jensen gave us to calculate new x and y.
		int yResult = (int)((1-percentageAcross)*pointsArray.get(firstPoint).getY()+percentageAcross*pointsArray.get(firstPoint+1).getY());
		Point newPoint = new Point(xResult,yResult);
		return newPoint;
		
	}
	public double distanceToPath(int x, int y)
	{
		Point xy = new Point(x,y);
		Point first = pointsArray.get(0);
		
		double smallestDist = xy.distance(first);
		
		for(double percent = 0.0; percent<=1.0; percent+=.001)
		{
			Point next = convertToCoordinates(percent);
			double dist = next.distance(xy);
			if(dist < smallestDist)
			{
				smallestDist = dist;
			}			
		}
		return smallestDist;
	}
		
}
	
	
	

	
	


