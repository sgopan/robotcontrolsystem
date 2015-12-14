package com.lynxit.rc

import com.lynxit.rc._
import com.lynxit.rc.parser.DefaultParser

/**
 * This object is responsible for interpreting the command objects and moving the robot in the grid.
 * @author sgopan
 */
object ControlSystem {
  
  /* Represents the parent for all commands */
  sealed trait Command {
    def description: String
    def execute(grid: Grid): Grid
  }
  
  /* Represents Move command for the robot */
  case class Move(steps: Int) extends Command {
    def description = "Move Forward"
    
    /**
     * This method will move the robot in x or y axis depending on its current direction
     */
    def execute(grid: Grid): Grid = {
      isStepCountWithinRange(steps,grid,"Move")
      val position = grid.position

      position.direction match {
        case North => Grid(Position(position.direction, position.x, moveNorthOrEast(grid)))

        case East  => Grid(Position(position.direction, moveNorthOrEast(grid), position.y))

        case South => Grid(Position(position.direction, position.x, moveSouthOrWest(grid)))

        case West  => Grid(Position(position.direction, moveSouthOrWest(grid), position.y))

      }
    }

    private def moveNorthOrEast(grid: Grid): Int = {
      val position = grid.position
      val coordinate = position.direction match {
        case North => position.y
        case East  => position.x
        case _ => throw new InvalidDirectionException("Invalid Direction - Direction can only be North or East")
      }
      //Value after which it will go past the grid coordinates
      val noOfMovesBeforeOverFlow = grid.maxCoordinate - coordinate
      if (noOfMovesBeforeOverFlow > steps) coordinate + steps
      else steps - noOfMovesBeforeOverFlow - 1 // NoofMovementsAfterOverflow - after max limit, one movement is used for reset zero , hence -1

    }

    private def moveSouthOrWest(grid: Grid): Int = {
      val position = grid.position
      val coordinate = position.direction match {
        case South => position.y
        case West  => position.x
        case _ => throw new InvalidDirectionException("Invalid Direction - Direction can only be South or West")
      }
      //Value after which it will go past the grid coordinates
      val noOfMovesBeforeOverFlow = coordinate - grid.minCoordinate
      if (noOfMovesBeforeOverFlow > steps) coordinate - steps
      else grid.maxCoordinate - (steps - noOfMovesBeforeOverFlow - 1) // NoofMovementsAfterOverflow - after max limit, one movement is used for reset zero , hence -1

    }

  }
  /* Represents Left command for the robot */
  case class Left(steps: Int) extends Command {
    def description = "Rotate 90 degree Left"
    
    /**
     * This method will turn the robot to Left
     */
    def execute(grid: Grid): Grid = {
      isStepCountWithinRange(steps,grid,"Left")
      val mod = steps % 4 // Since there only 4 directions
      var i = 0; var direction = grid.position.direction
      for (i <- 1 to mod) {
        direction match {
          case North => direction = West
          case South => direction = East
          case East  => direction = North
          case West  => direction = South
        }
      }
      Grid(Position(direction, grid.position.x, grid.position.y))
    }
  }
  
  /* Represents Right command for the robot */
  case class Right(steps: Int) extends Command {
    def description = "Move Forward 90 degree Right"

     /**
     * This method will turn the robot to Right
     */
    def execute(grid: Grid): Grid = {
      isStepCountWithinRange(steps,grid,"Right")
      val mod = steps % 4
      var i = 0; var direction = grid.position.direction
      for (i <- 1 to mod) {
        direction match {
          case North => direction = East
          case South => direction = West
          case East  => direction = South
          case West  => direction = North
        }
      }
      Grid(Position(direction, grid.position.x, grid.position.y))
    }

  }
  
  /**
   * This method will convert the command string to sequence of command objects and which will navigate the robot
   * in a particular direction along with altering its x or y coordinate
   * @param - grid - represents initial position on grid
   * @param - commandSequence command as a string
   * @return - Represents the new position of the robot on grid
   */
  def navigate(grid: Grid, commandSequence: String): Grid = {
    val commandList = DefaultParser.parse(commandSequence)
    var currentGrid = grid
    commandList.foreach { command =>
      val output = command match {
        case m: Move  => currentGrid = m.execute(currentGrid)
        case l: Left  => currentGrid = l.execute(currentGrid)
        case r: Right => currentGrid = r.execute(currentGrid)
        case _        => currentGrid = currentGrid
      }
    }
    currentGrid

  }
  
  private def isStepCountWithinRange(count:Int,grid:Grid,commandName:String) {
    if(count < grid.minCoordinate || count > grid.maxCoordinate)
      throw new IllegalArgumentException("No of steps for command '"+commandName+"' should be between "+grid.minCoordinate +"-"+"grid.maxCoordinate")
  }

}