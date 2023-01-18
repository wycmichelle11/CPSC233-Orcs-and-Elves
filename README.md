# CPSC233-Orcs-and-Dwarves
Commandline text-based game written in Java for a class assignment.
The game starts off with a text-based world of a 10x10 square. There are Orcs and Dwarves who fight each other in the game if they are adjacent to each other. Each Orc and Dwarf attacks one adjacent opposite entity each round at random. This goes on each round until either there are only Dwarves left or only Orcs left. If there is no opposite entity adjacent to each other, Orcs will move down and right by one block and Dwarves will move up and left by one block.
 

To run:
```javac *.java to compile all files
   java Simulator.java```
   
The game would prompt the user to enter a starting position. this is obtained from one of the .txt files. 
