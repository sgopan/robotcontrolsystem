package com.lynxit.rc.parser

import com.lynxit.rc.ControlSystem.Command

/**
 * A trait representing a Parser for parsing command string. A parser can have
 * different implementations. The default implementation is a regular expression based.
 * 
 * @author sgopan
 */
trait CommandParser {
 /**
 * This method takes a string command and converts it a sequence of commands
 * @param - command The command as string. For example - 'M1RM4L3M2','M1L2'
 * @return - Seq[Command] returns a sequence of Command Objects
 */
  def parse(command: String): Seq[Command]

}