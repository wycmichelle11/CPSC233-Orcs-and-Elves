# CPSC233-Orcs-and-Elves
Commandline text-based game written in Java for a class assignment.

The game starts off with a text-based world of a 10x10 square. There are Orcs and Elves who fight each other in the game if they are adjacent to each other. Each Orc ('O') and Elf ('E') attacks one adjacent opposite entity each round at random. This goes on each round until either there are only Elves left or only Orcs left. If there is no opposite entity adjacent to each other, Orcs will move down and right by one block and Elves will move up and left by one block until they reach the edge of the world or encounter another of the same entity. Orcs start off with 10 Healthpoints (HP) and Elves start off with 15HP. Each attack, Orcs damage the Elves HP by 3 and Elves damage the Orcs HP by 7.
 

To run:
```
javac *.java to compile all files
java Simulator.java
 ```
   
The game would prompt the user to enter a starting position. This is obtained from one of the .txt files, for example:
```
ragnarok.txt
```
<img width="72" alt="image" src="https://user-images.githubusercontent.com/79016649/213530967-c4e8a536-0cf0-4a12-bf52-e459b323a856.png"> \
The image above is the starting positions in ragnarok.txt and the spaces in the file are open spaces.
In the example, I used data.txt, and the game shows you the initial world. Each round requires the user to press enter, so it gives time for the user to process the current situation.\
<img width="334" alt="image" src="https://user-images.githubusercontent.com/79016649/213530264-c548882c-d269-4e6c-b191-82a1e9803628.png"> \
After each round there is an option to turn on debug mode with 'D' or 'd' and that will show the status of the game and where each entity will move to next and the HP, HP damage of both the attacking and attacked entity. If debug mode is off, it will proceed with the entities moving and prompt you to press enter again. \
<img width="336" alt="image" src="https://user-images.githubusercontent.com/79016649/213528254-7db6f830-59e9-450e-a69f-19bccc859b68.png">
<img width="384" alt="image" src="https://user-images.githubusercontent.com/79016649/213529195-f1e08fd4-b0f1-4a0e-b426-ef62f0049c56.png">
<img width="134" alt="image" src="https://user-images.githubusercontent.com/79016649/213528391-c420cdef-084a-4a72-9393-7c4243ca61f6.png">\
When there is no attacks for 10 rounds in a row, then the game ends with a cease fire.\
<img width="135" alt="image" src="https://user-images.githubusercontent.com/79016649/213530070-522f6319-d5d9-46eb-a7e4-cda5b9c808a4.png">



