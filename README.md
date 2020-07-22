# Distributed Scrabble Game  

Scrabble is a very popular crossword puzzle, multiple players place letters on one board to form a word to earn points, and the player with the highest total score will win the game. The purpose of this project is to develop a multiplayer online Scrabble game include the server and client by using Java programming language. The player opens the client to connect server and plays the game with other online players. Our design allowed multiplayer play multi-game at the same time. The system will automatically handle the player's actions and push the game's progress.
The application was developed by using the concept of sockets for communication between the different clients and the game server. TCP Sockets are used for communication. The concurrency in the game is achieved through multi-threading. The client has two thread and the server has multi-thread which is the thread-per-connection structure. Threads help in executing one or more tasks concurrently in a java program. The message passing between different clients and the game server is IO objects stream, implemented by sending objects through sockets using the concept of serialization. The error handling is implemented through Exception handling in Java. And the Graphical User Interface is implemented using JavaFX. <br /><br />

## Dependencies

JDK required: 1.8

## Installation & Set-up

#### IntelliJ IDEA
```shell
open project structure -> set jdk to 1.8 and module as 8(Lambda)
open Preference -> compiler -> java compiler -> set target bytecode version to 1.8 or 8
```

#### Eclipse 
``` shell
import existing maven projects -> open the directory you downloaded -> finish
```

## Instructions to Run

``` shell
java -jar Server.jar
java -jar Client.jar
```





















