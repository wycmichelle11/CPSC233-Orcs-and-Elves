/*
    Author:  James Tam
    Version: February 11, 2021
    **********************************************************************************************************************
    User: Michelle Cheung
    Class: CPSC 233 L01 T07
    Purpose: Stores the location of Entities in (Row/Column) form
    Features:   A wrapper class
    Limiations: Is run through the Simulator class only
    Version: March 12, 2021
*/

public class Location
{
    private int row;
    private int column;

    public Location(int newRow, int newColumn)
    {
        row = newRow;
        column = newColumn;
    }

    public int getColumn()
    {
        return(column);
    }

    public int getRow()
    {
        return(row);
    }

    public void setColumn(int newColumn)
    {
        column = newColumn;
    }

    public void setRow(int newRow)
    {
        row = newRow;
    }
}