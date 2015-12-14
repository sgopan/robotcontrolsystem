package com.lynxit.rc

import org.scalatest.FunSpec

import com.lynxit.rc.ControlSystem.Right

class RightSpec extends FunSpec {

  describe("Right Command") {

    it("should make the robot point to E 0 0 with inital position as N 0 0 and steps 1") {
      val grid = Grid(Position(North, 0, 0))
      val right = Right(1)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(East))
    }

    it("should make the robot point to N 0 0 with inital position as W 0 0 and steps 1") {
      val grid = Grid(Position(West, 0, 0))
      val right = Right(1)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(North))
    }

    it("should make the robot point to W 0 0 with inital position as S 0 0 and steps 1") {
      val grid = Grid(Position(South, 0, 0))
      val right = Right(1)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(West))
    }

    it("should make the robot point to S 0 0 with inital position as E 0 0 and steps 1") {
      val grid = Grid(Position(East, 0, 0))
      val right = Right(1)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(South))
    }

    it("should make the robot point to S 0 0 with inital position as N 0 0 and steps 2") {
      val grid = Grid(Position(North, 0, 0))
      val right = Right(2)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction == South)
    }

    it("should make the robot point to W 0 0 with inital position as N 0 0 and steps 3") {
      val grid = Grid(Position(North, 0, 0))
      val right = Right(3)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(West))
    }

    it("should make the robot point to S 0 0 with inital position as N 0 0 and steps 6") {
      val grid = Grid(Position(North, 0, 0))
      val right = Right(6)
      val newGrid = right.execute(grid)
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(South))
    }

    it("should throw an exception for steps -1 with inital position as N 0 0") {
      intercept[IllegalArgumentException] {
        val grid = Grid(Position(North, 0, 0))
        val right = Right(-1)
        val newGrid = right.execute(grid)
      }
    }

    it("should throw an exception for steps 102 with inital position as N 0 0") {
      intercept[IllegalArgumentException] {
        val grid = Grid(Position(North, 0, 0))
        val right = Right(102)
        val newGrid = right.execute(grid)
      }
    }
  }

}