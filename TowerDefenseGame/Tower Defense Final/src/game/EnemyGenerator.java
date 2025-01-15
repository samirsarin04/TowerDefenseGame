/**
 * This application creates an EnemyGenerator object which is used to create the rules 
 * for creating new enemies in different waves.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 28, 2022
 */

package game;

import java.awt.Graphics;

public class EnemyGenerator extends GameObject
{
	private Control control;
	private State state;
	double nextGenTimeSCargo = 5.0; //The initial time it takes before another SCargo is spawned.
	double nextGenTimeSnail = 1.0; //The initial time it takes before another Snail is spawned.
	double SCargoSpawnSpeed = 5.0; //Initial spawn speed, to be modified as time goes on.
	double SnailSpawnSpeed = 1.25; //Initial spawn speed, to be modified as time goes on.
	int enemyCounter =1; //1 enemy initially from the creation in the control class, counts how many enemies on screen.
	int enemyLimit= 10; //The limit for the first wave of enemies.
	
/**
 * Create a new EnemyGenerator object.
 * 
 * @param control control object
 * @param state state object
 */
	public EnemyGenerator(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	
	
	
	
	@Override
	public void update(double elapsedTime) 
	{
		
		
		if(enemyCounter == enemyLimit) //Check to see if the max amount of enemies for a certain wave are on the screen.
		{
			nextGenTimeSCargo=(long)(state.getTotalTime()/1000.0+20); //Wait 20 seconds after the last enemy is spawned before starting the next wave.
			nextGenTimeSnail =(long)(state.getTotalTime()/1000.0+20);
			enemyLimit +=10; //Increment the limit by 10 every wave.
			enemyCounter = 0; //reset the enemies on screen counter.
		}	
		
		
		if(nextGenTimeSCargo<state.getTotalTime()/1000.0)
		{
			state.addGameObject(new SCargo(control,state)); //Spawn a new SCargo periodically when the timer ticks a certain amount of time.
			enemyCounter+=1;
			nextGenTimeSCargo+=SCargoSpawnSpeed;	
		}
		if(nextGenTimeSnail<state.getTotalTime()/1000.0) //Same with snail.
		{
			state.addGameObject(new Snail(control,state));
			enemyCounter+=1;
			nextGenTimeSnail+=SnailSpawnSpeed;
		}
	}
		
	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		
	}
/**
 * Mutator for the enemyCounter, change how many enemies are counted on the path.
 * @param change
 */
	public void setEnemyCounter(int change)
	{
		enemyCounter += change;
	}

	


@Override
public double getPercentage() {
	// TODO Auto-generated method stub
	return 0;
}
	
	
	
}
