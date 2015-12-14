package com.lynxit.rc

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import com.lynxit.rc.ControlSystem.Move

class MoveSpec extends FunSpec {

  describe("Move Command") {

    it("should move 2 steps forward on Yaxis with inital position as N 0 0 and steps 2") {
      val grid = Grid(Position(North, 0, 0))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 2)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(North))
    }

    it("should wrap Y coordinate to 0 if it get past maxcoordinates with inital position as N 0 99 and steps 1") {
      val grid = Grid(Position(North, 0, 99))
      val move = Move(1)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(North))
    }

    it("should mov 2 steps forward on X axis with initial position as E 0 0 and steps 2") {
      val grid = Grid(Position(East, 0, 0))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 2)
      assert(newGrid.position.direction.equals(East))
    }

    it("should wrap X coordinate to 0 if it get past maxcoordinates with inital position as E 99 0 and steps 1") {
      val grid = Grid(Position(East, 99, 0))
      val move = Move(1)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(East))
    }

    it("should move 2 steps backward on Yaxis with inital position as S 0 10 and steps 2") {
      val grid = Grid(Position(South, 0, 10))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 8)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(South))
    }

    it("should wrap Y coordinate to 99 if it get past mincoordinates with inital position as S 0 1 and steps 2") {
      val grid = Grid(Position(South, 0, 1))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 99)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction == South)
    }

    it("should mov 2 steps back on X axis with initial position as W 10 0 and steps 2") {
      val grid = Grid(Position(West, 10, 0))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 8)
      assert(newGrid.position.direction == West)
    }

    it("should wrap X coordinate to 99 if it get past mincoordinates with inital position as W 1 0 and steps 2") {
      val grid = Grid(Position(West, 1, 0))
      val move = Move(2)
      val newGrid = move.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 99)
      assert(newGrid.position.direction == West)
    }

    it("should throw an exception for steps -1 with inital position as N 0 0") {
      intercept[IllegalArgumentException] {
        val grid = Grid(Position(North, 0, 0))
        val move = Move(-1)
        val newGrid = move.execute(grid)
      }
    }

    it("should throw an exception for steps 102 with inital position as N 0 0") {
      intercept[IllegalArgumentException] {
        val grid = Grid(Position(North, 0, 0))
        val move = Move(102)
        val newGrid = move.execute(grid)
      }
    }

  }

}