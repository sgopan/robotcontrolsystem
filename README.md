# Robot Control System
A control system for the robots that inhabit

our 100 by 100 grid world. The coordinates of our 

world are 0 based and the world wraps so grid 

coordinates 0 and 99 are adjacent.

Status 

Each robot starts with a simple compass heading N, S, 

E, W and a grid coordinate, X, Y. 

Example 

N 1 12

The Input 

The robot is controlled with a set of simple commands.

M - Move Forward

L - Rotate 90o Left

R - Rotate 90o Right

Each command may be followed by a number from 

1-100. Which indicates the number of times to perform 

the action. Absence of a number is equivalent to 1

Input should be read from standard in and is 

terminated with a new line.

Example 

Initial position: N 0 0

M1RM4L3M2

Final Position: S 4 99

The Output 

The controls system should print the final position of 

the robot.
