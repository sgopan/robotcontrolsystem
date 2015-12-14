package com.lynxit.rc

import org.scalatest.FunSpec

import com.lynxit.rc.ControlSystem._

class ControlSystemSpec extends FunSpec {
  
  describe("Control Sytem Spec") {
    
    it("should move the robot to N 0 5 for command M5 with initial position N 0 0") {
      val grid = Grid(Position(North, 0, 0))
      val newGrid = ControlSystem.navigate(grid, "M5")
      assert(newGrid.position.y == 5)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(North))
    }

    it("should move the robot to W 0 0 for command L with initial position N 0 0") {
      val grid = Grid(Position(North, 0, 0))
      val newGrid = ControlSystem.navigate(grid, "L")
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(West))
    }
    
    it("should move the robot to E 0 0 for command R with initial position N 0 0") {
      val grid = Grid(Position(North, 0, 0))
      val newGrid = ControlSystem.navigate(grid, "R")
      assert(newGrid.position.y == 0)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(East))
    }

    it("should move the robot to N 0 1 for command M2 with initial position N 0 99") {
      val grid = Grid(Position(North, 0, 99))
      val newGrid = ControlSystem.navigate(grid, "M2")
      assert(newGrid.position.y == 1)
      assert(newGrid.position.x == 0)
      assert(newGrid.position.direction.equals(North))
    }

    it("should move the robot to S 4 99 for command M1RM4L3M2 with initial position N 0 0") {
      val grid = Grid(Position(North, 0, 0))
      val newGrid = ControlSystem.navigate(grid, "M1RM4L3M2")
      assert(newGrid.position.y == 99)
      assert(newGrid.position.x == 4)
      assert(newGrid.position.direction.equals(South))
    }  

    it("should move the robot to W 4 99 for command M1RM1L2M2 with initial position N 0 0") {
      val grid = Grid(Position(North, 0, 0))
      val newGrid = ControlSystem.navigate(grid, "M1RM1L2M2")
      assert(newGrid.position.y == 1)
      assert(newGrid.position.x == 99)
      assert(newGrid.position.direction.equals(West))
    } 
  
    
  }
  
}