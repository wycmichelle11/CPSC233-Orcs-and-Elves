/*
	Author:  James Tam
	Version: October 14, 2015

	Starting execution point for the program.
	**********************************************************************************************************************
	Editor: Michelle Cheung
    UCID: 30116197
    Class: CPSC 233 L01 T07
    Purpose: The starting execution point
    Features:   Contains the main method to start Simulation
    Limiations: Refers to the World class only
    Version: March 12, 2021
*/

public class Simulator		
{
    public static void main(String [] args)
    {
        World aWorld = new World();	//Creating new object World
        aWorld.display();			//Displaying initial look of the World
        aWorld.start();				//Battle begins
    }
}