/*
    Author:  James Tam

    Version Feb 17A, 2021
    * Added method so an Entity can damage another Entity.

    Version: Feb 12A, 2021
    * Added attribute to track if Entity has been moved or not.

    Version: Feb 11B, 2021
    * Changed references from dwarves to elves.
    * Added a new damage attribute.

    Version: October 12, 2015
    * Original program
    **********************************************************************************************************************
    
    Editor: Michelle Cheung
    Class: CPSC 233 L01 T07
    Purpose: The characters or soldiers in the simulation.
    Features:   An entity can either be an orc or an elf
                Orcs have appearance 'O'and Elves have appearance 'E'
                There are default values of damage and hit points set for both Entities
    Limiations: Is run through Simulator class only
    Version: March 12, 2021
*/

public class Entity
{
    public static final char DEFAULT_APPEARANCE = 'X';
    public static final char ELF = 'E';
    public static final char EMPTY = ' ';
    public static final char ORC = 'O';
    public static final int DEFAULT_HP = 1;
    public static final int ORC_DAMAGE = 3;
    public static final int ELF_DAMAGE = 7;
    public static final int ORC_HP = 10;
    public static final int ELF_HP = 15;

    private char appearance;
    private int hitPoints;
    private int damage;
    private Location aLocation;

    public Entity()
    {
        setAppearance(DEFAULT_APPEARANCE);
        setHitPoints(DEFAULT_HP);
    }

    public Entity(char newAppearance)
    {
        appearance = newAppearance;
        hitPoints = DEFAULT_HP;
        damage = ORC_DAMAGE;
    }

    public Entity(char newAppearance, int newHitPoints, int newDamage)
    {
        setAppearance(newAppearance);
        setDamage(newDamage);
        setHitPoints(newHitPoints);
    }

    public boolean moveBoundaryCheck(int oldRow, int oldColumn, int newRow, int newColumn)  
    {                   //Checking if destination move is out of bounds. Part of entity class because entity is responsible for checking before moving
        if ((newRow > World.MAX_ARRAY_INDEX)|| (newRow < World.MAX_ARRAY_INDEX - World.MAX_ARRAY_INDEX))      
            return (false);
        if ((newColumn > World.MAX_ARRAY_INDEX) || (newColumn < World.MAX_ARRAY_INDEX - World.MAX_ARRAY_INDEX))
            return (false);
        else
            return (true);
    }
        
    public Location move(int row, int column)       //Moving Entity to new location
    {
        int newRow = -2;        //Variables initialized to -2 now, but it will not appear
        int newColumn = -2;
        if (getAppearance() == ORC) //Moves right and down when possible
        {
            newRow = row + 1;           
            newColumn = column + 1;
        }
        else if (getAppearance() == ELF)    //Moves left and up when possible
        {
            newRow = row - 1;
            newColumn = column - 1;
        }
        if (moveBoundaryCheck(row, column, newRow, newColumn) == false) //if new location is out of bounds, use the old coordinates
        {
            newRow = row;
            newColumn = column;
        }
        else
        {
            if (GameStatus.debugModeOn == true)         //Display GameStatus when debug mode is on
            GameStatus.confirmMove(row, column, newRow, newColumn);
        }
        aLocation = new Location(newRow, newColumn);
        return (aLocation);
    }

    public void attack(int row, int column, int opponentRow, int opponentColumn)    //Entity is being attacked
    {
        if (getAppearance() == ORC)     
        {
            int orcHP = ORC_HP;  //An Orc is being attacked by an Elf
            orcHP = getHitPoints();
            int newOrcHP = orcHP;
            if (GameStatus.debugModeOn == true)         //Display GameStatus when debug mode is on
                GameStatus.checkAttackerHP(ORC,row, column, ORC_DAMAGE); 
        }
        else if (getAppearance() == ELF)
        {
            int elfHP = ELF_HP;      //An Elf is being attacked by an Orc
            elfHP = getHitPoints();
            int newElfHP = elfHP; 
            if (GameStatus.debugModeOn == true)         //Display GameStatus when debug mode is on
                GameStatus.checkAttackerHP(ELF,row, column, ELF_DAMAGE); 
        }
    }

    public int loseHP(int opponentRow, int opponentColumn)  //Opponent loses HP
    {
        if (getAppearance() == ORC)
        {
            int orcHP = ORC_HP;
            orcHP = getHitPoints();
            int newOrcHP = orcHP - ELF_DAMAGE;  //HP - attacker's damage is the new HP
            setHitPoints(newOrcHP);
            if (GameStatus.debugModeOn == true)
                GameStatus.checkOpponentHP(ORC, opponentRow, opponentColumn, ELF_DAMAGE, ORC_HP, newOrcHP); 
            return(newOrcHP);
        }
        else if (getAppearance() == ELF)
        {
            int elfHP = ELF_HP;
            elfHP = getHitPoints();
            int newElfHP = elfHP - ORC_DAMAGE; 
            setHitPoints(newElfHP);
            if (GameStatus.debugModeOn == true)
                GameStatus.checkOpponentHP(ELF, opponentRow, opponentColumn, ORC_DAMAGE, ELF_HP, newElfHP);
            return(newElfHP);
        }
        else                            
            return(5000);       //This message should not appear. When it does, we know something wrong appeared.
    }

    public boolean unconscious(int hP)  //Check if an Entity is unconscious or not
    {
        if (hP <= 0)
            return(true);
        else
            return (false);
    }
    public boolean isAnOrc(char letter)     //Checks if an Entity is an Orc or not
    {
        if  (letter == ORC)
            return(true);
        else
            return(false);
    }

    public boolean isAnElf(char letter)     //Checks if an Entity is an Elf or not
    {
        if  (letter == ELF)
            return(true);
        else
            return(false);
    }
    public char getAppearance()
    {
        return(appearance);
    }

    public int getDamage()
    {
        return(damage);
    }

    public int getHitPoints()
    {
        return(hitPoints);
    }

    private void setAppearance(char newAppearance)
    {
        appearance = newAppearance;
    }

    private void setDamage(int newDamage)
    {
        if (newDamage < 1) 
        {
            System.out.println("Damage must be 1 or greater");
        }
        else
        {
            damage = newDamage;
        }        
    }

    private void setHitPoints(int newHitPoints)
    {
        hitPoints = newHitPoints;
    }
}