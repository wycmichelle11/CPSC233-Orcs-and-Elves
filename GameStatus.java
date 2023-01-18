/*********************************************************************************
 Author:  James Tam
 Version: March 9, 2004

 The sole purpose of this class to store global flags that indicate the current
 status of the game.
********************************************************************************/
/*
	Editor: Michelle Cheung
    UCID: 30116197
    Class: CPSC 233 L01 T07
    Purpose: Tracks the state of the simulation (in both debugging mode and out of debugging mode)
    Features:   Debug mode will stay on until otherwise told
    			There is an option to turn on or off debug mode after every round
    Limiations: Debug mode is turned on in World class
    			Is run by the Simulation class only
    Version: March 12, 2021
*/
public class GameStatus
{
    public static boolean debugModeOn = false;

    public static void checkMove(int fromRow, int fromColumn)		//Displays that Entity is checking their destination move
    {
		System.out.println("World.searching()");
		System.out.println("\t Checking move for Entity in (Row/Column): ("+fromRow+"/"+fromColumn+")...");
    }
    public static void confirmMove(int fromRow, int fromColumn, int toRow, int toColumn)	//Displays when Entity is able to move to destination
    {
    	System.out.println("World.move()");
		System.out.println("\tSource (Row/Column): ("+fromRow+"/"+fromColumn+")");	//Source of Entity
		System.out.println("\tDestination (Row/Column): ("+toRow+"/"+toColumn+")");	//Destination of Entity
		System.out.println("\tEntity at location (Row/Column): ("+fromRow+"/"+fromColumn+")"); 
		System.out.println("\twill move to location (Row/Column): ("+toRow+"/"+toColumn+")");
    }

    public static void checkAttackerHP(char attacker, int row, int column, int damage)	//Displays when an Entity is attacking and lists it's attack damage
    {
    	System.out.println("Entity "+attacker+" at (Row/Column) ("+row+"/"+column+") attacking for Damage "+damage);
    }
    public static void checkOpponentHP(char opponent, int row, int column, int damage, int oldHp, int newHp) //Displays when an Entity is being attacked
    {																	//Lists original HP, how much damage is made (drop in hp), final HP
 	   System.out.println("Opponent "+opponent+" with "+oldHp+" HP at (Row/Column) ("+row+"/"+column+") with damage: "+damage+" New HP: "+newHp);
    }																		
    public static void checkCeaseFire(int counter)				//Displays the number of rounds for a cease fire to happen
    {
    	System.out.println("There have been "+counter+" rounds before this cease fire.");
    }
}
