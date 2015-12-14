package com.lynxit.rc.parser

import org.scalatest.FunSpec

import com.lynxit.rc.ControlSystem._

class DefaultParserSpec extends FunSpec {

  describe("Default Praser") {

    it("should throw Exception if input is null") {
      intercept[IllegalArgumentException] {
        DefaultParser.parse(null)
      }
    }

    it("should throw Exception if input is empty string") {
      intercept[IllegalArgumentException] {
        DefaultParser.parse("")
      }
    }

    it("should be able to parse a command M1RM4L3M2") {
      val commandList = DefaultParser.parse("M1RM4L3M2")
      assert(commandList(0).equals(Move(1)))
      assert(commandList(1).equals(Right(1)))
      assert(commandList(2).equals(Move(4)))
      assert(commandList(3).equals(Left(3)))
      assert(commandList(4).equals(Move(2)))
    }

    it("should be able to parse a command M1") {
      val commandList = DefaultParser.parse("M1")
      assert(commandList(0).equals(Move(1)))

    }
    it("should be able to parse a command M") {
      val commandList = DefaultParser.parse("M")
      assert(commandList(0).equals(Move(1)))

    }

    it("should be able to parse a command R1") {
      val commandList = DefaultParser.parse("R1")
      assert(commandList(0).equals(Right(1)))

    }

    it("should be able to parse a command R") {
      val commandList = DefaultParser.parse("R")
      assert(commandList(0).equals(Right(1)))

    }

    it("should be able to parse a command L1") {
      val commandList = DefaultParser.parse("L1")
      assert(commandList(0).equals(Left(1)))
    }

    it("should be able to parse a command L") {
      val commandList = DefaultParser.parse("L")
      assert(commandList(0).equals(Left(1)))

    }

  }

}