# CPSC233-Orcs-and-Dwarves
Commandline text-based game written in Java for a class assignment.

The game starts off with a text-based world of a 10x10 square. There are Orcs and Dwarves who fight each other in the game if they are adjacent to each other. Each Orc ('O') and Dwarf ('D') attacks one adjacent opposite entity each round at random. This goes on each round until either there are only Dwarves left or only Orcs left. If there is no opposite entity adjacent to each other, Orcs will move down and right by one block and Dwarves will move up and left by one block until they reach the edge of the world or encounter another of the same entity. Orcs start off with 10 Healthpoints (HP) and Dwarves start off with 15HP. Each attack, Orcs damage the Dwarves HP by 3 and Dwarves damage the Orcs HP by 7.
 

To run:
```
javac *.java to compile all files
java Simulator.java
   ```
   
The game would prompt the user to enter a starting position. this is obtained from one of the .txt files. 
<img width="120" alt="image" src="https://user-images.githubusercontent.com/79016649/213525220-2267f9ec-a646-4cc3-83fb-e289e3cdd1ac.png">
The image above is the starting positions in data.txt and the spaces in the file are open spaces.
