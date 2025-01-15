/**
 * This Interface groups together objects that should be "Clickable"
 * and provides them with one abstract method that is consumeClick().
 * The interface is used to group GameObjects that need to be clicked for use.
 * 
 * 
 * @author Atticus Benson and Samir Sarin
 * @version November 21, 2022
 */
package game;

public interface Clickable 
{
	boolean consumeClick();
}
