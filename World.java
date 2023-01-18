import java.util.Scanner;

/*
    Author:  James Tam
    Version: Feb 18, 2021
    * New version of the starting code included.

    Version: October 12, 2015
    * Original program  
    **********************************************************************************************************************
    Editor: Michelle Cheung
    UCID: 30116197
    Class: CPSC 233 L01 T07
    Purpose: Creates a world for the Entity object to move in based on attacks and moves.
    Features:   Contains a simulated world in the form of a 2D array of references to Entity objects
                Starting point is given by the input file
                2 Worlds will be created. One as a main world, and one that stores information so all moves can be displayed at once
    Limiations: Requires an input .txt file to locate the Entities and is run through Simulator class only
    Version: March 12, 2021
*/

public class World
{
    public static final int SIZE = 10;
    public static final int ORCS_WIN = 0;
    public static final int ELVES_WIN = 1;
    public static final int DRAW = 2;
    public static final int CEASE_FIRE = 3;
    public static final int NOT_OVER = 4;
    public static final int MAX_ARRAY_INDEX = 9;
    public static final int CEASE_FIRE_ROUNDS = 10;

    private Entity [][] aWorld;
    private int gameContinues;  
    private int attackCounter;
    private int peaceCounter;
    private Location orcLocation;
    private Location elfLocation;
    private Location opponentLocation;
    private Entity [][] newWorld;   
    // Post-condition: the position of the characters in the
    // starting input file will determine the position of the
    // objects in the array. The type of character in the file
    // determines the appearance of the Entity(O or D) or if the
    // element is empty (spaces become null elements)
    public World()
    {
        aWorld = new Entity[SIZE][SIZE];
        int r;
        int c;
        for (r = 0; r < SIZE; r++)
        {
            for (c = 0; c < SIZE; c++)
            {
                aWorld[r][c] = null;
            }
        }
        newWorld = new Entity[SIZE][SIZE];  
        int newR;
        int newC;
        for (newR = 0; newR < SIZE; newR++)
        {
            for (newC = 0; newC < SIZE; newC++)
            {
                aWorld[newR][newC] = null;
            }
        }
        aWorld = FileInitialization.read();
        gameContinues = NOT_OVER;   //Setting original condition for the while loop
    }
    // Displays array elements on at a time one row per line
    // Each element is bound above, below, left and right by 
    // bounding lines
    public void display()
    {
        int r = -1;
        int c = -1;
        int b = -1;
        for (b=0; b<SIZE; b++)
            {System.out.print(" -");}
        System.out.println();
        for (r = 0; r < SIZE; r++)
        {
            for (c = 0; c < SIZE; c++)
            {
                System.out.print("|");
                if(aWorld[r][c] == null)
                    System.out.print(" ");
                else
                    System.out.print(aWorld[r][c].getAppearance());

            }
            System.out.println("|");
            for (c=0; c<SIZE; c++)
                {System.out.print(" -");}
            System.out.println();
        } 
    }

    public void debugToggle(String line)    //Debug toggle for user to decide to turn on or off
    {
        if (line != "")
        {
            char input;
            input = line.charAt(0);
            if ((input == 'D') || (input == 'd'))
            {
                if (GameStatus.debugModeOn == false)
                {
                    GameStatus.debugModeOn = true;
                }
                else
                {
                    GameStatus.debugModeOn = false;
                }
            }
        }
    }

    public void transferLocation()      //transfer the reference of Entities stored in the newWorld to aWorld to use
    {
        int r = -1;
        int c = -1;
        int b = -1;
        for (r = 0; r < SIZE; r++)
        {
            for (c = 0; c < SIZE; c++)
            {
                if(newWorld[r][c] != null)
                {
                    if (newWorld[r][c].isAnOrc(newWorld[r][c].getAppearance()) == true)
                    {
                        aWorld[r][c] = new Entity(Entity.ORC, Entity.ORC_HP, Entity.ORC_DAMAGE);
                    }
                    else if (newWorld[r][c].isAnElf(newWorld[r][c].getAppearance())==true)
                        aWorld[r][c] = new Entity(Entity.ELF, Entity.ELF_HP, Entity.ELF_DAMAGE);
                }
            }
        } 
    }

    public int winner()         //Determines winner
    {
        int r = -1;
        int c = -1;
        int b = -1;
        int orcCounter = 0;
        int elfCounter = 0;
        for (r = 0; r < SIZE; r++)
        {
            for (c = 0; c < SIZE; c++)
            {
                if(aWorld[r][c] != null)
                {
                    if (aWorld[r][c].isAnOrc(aWorld[r][c].getAppearance()) == true) 
                        orcCounter++;       //Counter to keep track of how many Orcs are left
                    else if (aWorld[r][c].isAnElf(aWorld[r][c].getAppearance())==true)
                        elfCounter++;       //Counter to keep track of how many Elves are left
                }
            }
        } 
        if (peaceCounter == CEASE_FIRE_ROUNDS)  
        {
            gameContinues = CEASE_FIRE;
            System.out.println("Cease Fire!");
            if (GameStatus.debugModeOn == true)
                GameStatus.checkCeaseFire(peaceCounter);
            return(gameContinues);
        }
        else if ((orcCounter == 0) && (elfCounter == 0))    //There's a draw when no Entities are left
        {
            gameContinues = DRAW;
            System.out.println("It's a Draw!");
            return (gameContinues);
        }
        else if (orcCounter == 0)     //Elves win when no Orcs are left
        {
            gameContinues = ELVES_WIN;
            System.out.println("The Elves Win!");
            return(gameContinues);
        }
        else if (elfCounter == 0)     //Orcs win when no Elves are left
        {
            gameContinues = ORCS_WIN;
            System.out.println("The Orcs Win!");
            return(gameContinues);
        }
        else if ((orcCounter > 0) && (elfCounter > 0))  //Game Continues 
        {
            gameContinues = NOT_OVER;
            return(gameContinues);
        }
        else
            return (200);       //This message should not appear. When it does, we know something wrong appeared.
    } 

    public void clear()     //Clears newWorld
    {
        int row;
        int column;
        for(row = 0; row < SIZE; row++)
        {
            for(column = 0; column < SIZE; column++)
            {
                newWorld[row][column] = null;   //clearing all elements to null
            }
        }
    }

    public Location middleSearching(int oldRow, int oldColumn)  //Searching goes in a circle from the top left then goes clockwise.
    {
        if (aWorld[oldRow-1][oldColumn -1] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn -1);
        else if (aWorld[oldRow - 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow -1][oldColumn + 1] !=null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn - 1);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location leftTopSearch(int oldRow, int oldColumn)    //Top left corner only needs to check for three cases 
    {                                                           //if there are other Entities for three cases
        if (aWorld[oldRow + 1][oldColumn] != null)              //starting right of Entity then clockwise until left edge
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }
    public Location rightTopSearch(int oldRow, int oldColumn)   //Top right corner only needs to check for three cases
    {                                                           //if there are other Entities for three cases
       if (aWorld[oldRow + 1][oldColumn] != null)               //starting below Entity then clockwise until top edge
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn - 1);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location leftBottomSearch(int oldRow, int oldColumn)     //Bottom left corner only needs to check for three cases
    {                                                               //if there are other Entities for three cases 
        if (aWorld[oldRow -1][oldColumn] != null)                   //starting above Entity then clockwise until bottom edge    
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow - 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location rightBottomSearch(int oldRow, int oldColumn)        //Bottom right corner only needs to check 
    {                                                                   //if there are other Entities for three cases 
        if (aWorld[oldRow - 1][oldColumn - 1] != null)                  //starting left and above Entity then clockwise
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn - 1);  //with skipping the non-existent spots under
        else if (aWorld[oldRow - 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location topRowSearch(int oldRow, int oldColumn)     //Entity is not in corner but in top edge
    {                                                           //Checks if there are other Entities
        if (aWorld[oldRow][oldColumn + 1] != null)              //starting right of Entity, then clockwise
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn - 1);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location bottomRowSearch(int oldRow, int oldColumn)          //Entity is not in corner but in bottom edge
    {                                                                   //Checks if there are other Entities
        if (aWorld[oldRow - 1][oldColumn - 1] != null)                  //starting top left, then clockwise
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn - 1);  //skips the non-existent spots under
        else if (aWorld[oldRow - 1][oldColumn] != null)                 //and goes clockwise from the left
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow - 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }
    public Location leftEdgeSearch(int oldRow, int oldColumn)       //Entity is not in corner but in left edge
    {                                                               //Checks if there are other Entities
        if (aWorld[oldRow - 1][oldColumn] != null)                  //starting above Entity, then clockwise
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow - 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn + 1);
        else if (aWorld[oldRow][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn + 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn + 1);
        else if (aWorld[oldRow + 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else
            opponentLocation = null;
        return (opponentLocation);
    }
    public Location rightEdgeSearch(int oldRow, int oldColumn)          //Entity is not in corner but in right edge
    {                                                                   //Checks if there are other Entities
        if (aWorld[oldRow -1][oldColumn - 1] != null)                   //starting left and above Entity
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn - 1);  //skips the non-existent spots right ofentity
        else if (aWorld[oldRow - 1][oldColumn] != null)                 //then continues searching clockwise from below Entity
            detectEnemy(oldRow, oldColumn, oldRow - 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn);
        else if (aWorld[oldRow + 1][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow + 1, oldColumn - 1);
        else if (aWorld[oldRow][oldColumn - 1] != null)
            detectEnemy(oldRow, oldColumn, oldRow, oldColumn - 1);
        else
            opponentLocation = null;
        return (opponentLocation);
    }

    public Location searching(int oldRow, int oldColumn)        //Combines all the different types of searches into one function
    {
        if ((oldRow == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX))
            return(leftTopSearch(oldRow, oldColumn));
        else if ((oldRow == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX) && (oldColumn < MAX_ARRAY_INDEX))
            return(topRowSearch(oldRow, oldColumn));
        else if ((oldRow == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX))
            return(rightTopSearch(oldRow, oldColumn));
        else if ((oldRow < MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX))  
            return(rightEdgeSearch(oldRow, oldColumn));  
        else if ((oldRow == MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX))
            return(rightBottomSearch(oldRow, oldColumn));
        else if ((oldRow == MAX_ARRAY_INDEX) && oldColumn > MAX_ARRAY_INDEX - MAX_ARRAY_INDEX)
            return(bottomRowSearch(oldRow, oldColumn));
        else if ((oldRow == MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX))
            return(leftBottomSearch(oldRow, oldColumn));
        else if ((oldRow < MAX_ARRAY_INDEX) && (oldColumn == MAX_ARRAY_INDEX - MAX_ARRAY_INDEX))
            return(leftEdgeSearch(oldRow, oldColumn));
        else 
            return(middleSearching(oldRow, oldColumn));
    }

    public Location detectEnemy(int myRow, int myColumn, int opponentRow, int opponentColumn)   
    {                                                                    //Detects if the adjacent Entity is an enemy of not
        if (aWorld[myRow][myColumn].getAppearance() != aWorld[opponentRow][opponentColumn].getAppearance())
            {   
                opponentLocation = new Location(opponentRow, opponentColumn);       
                return (opponentLocation);
            }
        else
        {
            opponentLocation = null;
            return(opponentLocation);
        }
    }

    public void startTurn(int row, int column)  //Used when an adjacent Entity exists
    {
        if (opponentLocation != null)   //If there adjacent Entity is an enemy, attack will occur
        {
            int opponentRow = opponentLocation.getRow();
            int opponentColumn = opponentLocation.getColumn();
            aWorld[row][column].attack(row, column, opponentRow, opponentColumn);
            int opponentHP = aWorld[opponentRow][opponentColumn].loseHP(opponentRow, opponentColumn);
            disappear(row,column, aWorld[row][column].getHitPoints());          //Unconscious Entity will disappear
            disappear(opponentRow,opponentColumn, aWorld[opponentRow][opponentColumn].getHitPoints());
            attackCounter++;    //Counts attacks to determine cease fire condition
        }
        else if (aWorld[row][column].isAnOrc(aWorld[row][column].getAppearance()) == true) //Occurs if adjacent Entity is of the same kind
        {
            orcLocation = aWorld[row][column].move(row, column);
            aWorld[row][column] = null;
            int r = orcLocation.getRow();
            int c = orcLocation.getColumn();
            orcNewTurn(r, c);   //Puts new moved entity into newWorld so it cannot be reused to determine adjacency in the same round
        }
        else if (aWorld[row][column].isAnElf(aWorld[row][column].getAppearance()) == true)
        {
            elfLocation = aWorld[row][column].move(row, column);
            aWorld[row][column] = null;
            int r = elfLocation.getRow();
            int c = elfLocation.getColumn();
            elfNewTurn(r, c);   //Puts new moved entity into newWorld so it cannot be reused to determine adjacency in the same round
        }
    }

    public void cycleMove() //Run down of each round
    {
        int r;
        int c;
        attackCounter = 0;
        clear();    //Clears the newWorld to prepare for new Entities
        for(r = 0; r < SIZE; r++)   //Runs through all elements in World
        {
            for(c = 0; c < SIZE; c++)
            {
                if ((aWorld[r][c] != null) && (aWorld[r][c].isAnOrc(aWorld[r][c].getAppearance()) == true))
                {                               
                    if (GameStatus.debugModeOn == true)
                        GameStatus.checkMove(r, c);
                    if (searching(r, c) != null)       //If an adjacent Entity exists
                    {
                        startTurn(r,c); 
                    }
                    else
                    {
                        orcLocation = aWorld[r][c].move(r, c);  //Moves if an adjacent Entity does not exist
                        aWorld[r][c] = null;    //Removes entity
                        int row = orcLocation.getRow();
                        int column = orcLocation.getColumn();
                        orcNewTurn(row, column);
                    }
                }
                else if((aWorld[r][c] != null) && (aWorld[r][c].isAnElf(aWorld[r][c].getAppearance()) == true))
                {
                    if (GameStatus.debugModeOn == true)
                        GameStatus.checkMove(r, c);
                    if (searching(r, c) != null)
                    {
                        startTurn(r, c);
                    }
                    else
                    {
                        elfLocation = aWorld[r][c].move(r, c);
                        aWorld[r][c] = null;
                        int row = elfLocation.getRow();
                        int column = elfLocation.getColumn();
                        elfNewTurn(row, column);
                    }
                }
            }
        }
        transferLocation(); //Transfers newWorld to aWorld after round
        display();      //Displays World after round
        if (attackCounter > 0)  //Updates peaceCounter
            peaceCounter = 0;
        else
            peaceCounter++;
    }

    public void orcNewTurn(int oRow, int oColumn)   //Moving Orc Entity into newWorld
    {                          
        newWorld[oRow][oColumn] = new Entity(Entity.ORC, Entity.ORC_HP, Entity.ORC_DAMAGE);
    }

    public void elfNewTurn(int eRow, int eColumn)   //Moving Elf Entity into newWorld
    {                          
        newWorld[eRow][eColumn] = new Entity(Entity.ELF, Entity.ELF_HP, Entity.ELF_DAMAGE);
    }

    public void disappear(int row, int column, int hp)
    {
        if (aWorld[row][column].unconscious(aWorld[row][column].getHitPoints()) == true)
            aWorld[row][column] = null;     //Entity disappears immediately after they are unconscious
        else
        {
            if (aWorld[row][column].getAppearance() == Entity.ORC)  //If they are conscious, they move into newWorld
                aWorld[row][column] = new Entity(Entity.ORC, hp, Entity.ORC_DAMAGE);
            else if (aWorld[row][column].getAppearance() == Entity.ELF) 
                aWorld[row][column] = new Entity(Entity.ELF, hp, Entity.ELF_DAMAGE);
            else
            System.out.println("This will not happen.");    //Message should not be displayed. If it does, something wrong has ocurred.
        }
    }

    public void start() //Starts the program off in Simulator class
    {        
        GameStatus.debugModeOn = false; //Starts program with debug mode off for user
        peaceCounter = 0;               
        while (gameContinues == NOT_OVER )  //While loop to keep game running until there is cease fire, draw, or one faction wins
        {   
            String line = "";
            System.out.print("Press enter to continue: ");  
            Scanner in = new Scanner(System.in);    //Allow for user to see each round
            line = in.nextLine();                   //and to determine if they want to turn on debug mode
            debugToggle(line);
            winner();                               //Gives conditions to if there is a winner or not
            if ((gameContinues == CEASE_FIRE) || (gameContinues == ELVES_WIN) ||(gameContinues == ORCS_WIN))
                break;  //Checks if there is a winner each round
            else
                cycleMove();      //A new round begins
        }     
    }
}