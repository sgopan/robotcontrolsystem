package com.lynxit.rc.parser

import com.lynxit.rc.ControlSystem._

/**
 * Default Parser parses  a command sequence of the form 'M1RM4L3M2' to a list of
 * command objects using regular expression
 * 
 * @author sgopan
 */
object DefaultParser extends CommandParser {

/**
 * This method takes a string command and converts it a sequence of commands
 * @param - command The command as string. For example - 'M1RM4L3M2','M1L2'
 * @return - Seq[Command] returns a sequence of Command Objects
 */
  def parse(command: String): Seq[Command] = {
    if(null == command || "".equals(command.trim()))
      throw new IllegalArgumentException("Command cannot be null or empty string")
    
    val pattern = "[MLR]([1-9]\\d?)?".r
    val commands = (pattern findAllIn command)
    var commandList: Seq[Command] = Seq()
    commands.foreach { commandStr =>
      commandList = commandList :+ buildCommandObject(commandStr)
    }
    commandList
  }

  private def buildCommandObject(commandStr: String): Command = {
    val command = commandStr take 1 //Extract the 1st Command character - M,L or R
    val count = commandStr drop 1 //Extract the number
    val stepsToMove = if (count.isEmpty()) 1 else count.toInt
    command match {
      case "L"     => Left(stepsToMove)
      case "R"     => Right(stepsToMove)
      case "M"     => Move(stepsToMove)
      case unknown => throw new IllegalArgumentException("Invalid Command character - Command not supported - '" + command + "'")
    }

  }

}