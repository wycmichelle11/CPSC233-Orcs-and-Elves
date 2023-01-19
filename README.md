# CPSC233-Orcs-and-Elves
Commandline text-based game written in Java for a class assignment.

The game starts off with a text-based world of a 10x10 square. There are Orcs and Elves who fight each other in the game if they are adjacent to each other. Each Orc ('O') and Elf ('E') attacks one adjacent opposite entity each round at random. This goes on each round until either there are only Elves left or only Orcs left. If there is no opposite entity adjacent to each other, Orcs will move down and right by one block and Elves will move up and left by one block until they reach the edge of the world or encounter another of the same entity. Orcs start off with 10 Healthpoints (HP) and Elves start off with 15HP. Each attack, Orcs damage the Elves HP by 3 and Elves damage the Orcs HP by 7.
 

To run:
```
javac *.java to compile all files
java Simulator.java
   ```
   
The game would prompt the user to enter a starting position. this is obtained from one of the .txt files. \
<img width="120" alt="image" src="https://user-images.githubusercontent.com/79016649/213525220-2267f9ec-a646-4cc3-83fb-e289e3cdd1ac.png"> \
The image above is the starting positions in data.txt and the spaces in the file are open spaces.
In the example, I used data.txt, and the game shows you the initial world. Each round requires the user to press enter, so it gives time for the user to process the current situation.\
<img width="293" alt="image" src="https://user-images.githubusercontent.com/79016649/213527172-0e4cc98e-4e6c-4584-aea4-65a726e79251.png"> \
After each round there is an option to turn on debug mode with 'D' or 'd' and that will show the status of the game and where each entity will move to next. If debug mode is off, it will proceed with the entities moving. \
<img width="336" alt="image" src="https://user-images.githubusercontent.com/79016649/213528254-7db6f830-59e9-450e-a69f-19bccc859b68.png">
<img width="134" alt="image" src="https://user-images.githubusercontent.com/79016649/213528391-c420cdef-084a-4a72-9393-7c4243ca61f6.png">



