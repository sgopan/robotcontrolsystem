package com.lynxit.rc

import util.control.Breaks._

/**
 * @author sgopan
 */
//Represents the four directions
sealed trait Direction
case object North extends Direction
case object South extends Direction
case object East extends Direction
case object West extends Direction

case class InvalidDirectionException(message:String) extends Exception(message)

//Represents a position which consists of direction and x and y coordinates
case class Position(direction: Direction, x: Int, y: Int) {

  override def toString() = {
    val str = direction match {
      case North => "N"
      case South => "S"
      case East  => "E"
      case West  => "W"
      case _     => throw new InvalidDirectionException("Cannot determine the direction")
    }
    str + " " + x + " " + y
  }

}

//Grid is a representation of Position combined with max and min value of x and y scoordinates.
case class Grid(position: Position,minCoordinate:Int=0, maxCoordinate: Int = 99)

/**
 * Represents a robot 
 */
class Robot {

  //Init the default position to N 0 0 and grid with 100
  var grid = Grid(Position(North, 0, 0))
  
  /**
   * This method accepts a command string and passes it control system for execution. Based on 
   * the command passed it will move the robot in the grid.
   * @param - command as a string. For example- 'M1LR1M3'
   */
  def acceptCommand(commandSequence:String) {
    grid = ControlSystem.navigate(grid, commandSequence)
    println(grid.position) 
  }  

}

/**
 * Helper class to run the robot program
 */
object RobotRunner {

  def main(args: Array[String]) {

    println("###############################")
    println("Welcome - Robot Control System")
    println("###############################")
    println("Type ''exit' to terminate the programme ...")

    var runStatus = true
    val robot = new Robot()
    val scanner = new java.util.Scanner(System.in)

    while (runStatus) {
      println("Please enter a command sequence to move the Robot ...")
      val commandSequence = scanner.next()
      breakable {
        if (null == commandSequence || commandSequence.trim().isEmpty()) {
          println("Command cannot be null or empty string")
          break
        }

        commandSequence match {
          case "exit" => runStatus = false
          case _      => robot.acceptCommand(commandSequence)
        }
      }

    }

  }
}