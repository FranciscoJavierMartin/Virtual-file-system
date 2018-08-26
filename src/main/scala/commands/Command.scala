package commands

import filesystem.State

trait Command {

  def apply(state:State):State
}

object Command{

  val MKDIR_NAME: String = "mkdir"
  val CD_NAME: String = "cd"
  val LS_NAME = "ls"
  val PWD_NAME = "pwd"
  val TOUCH_NAME = "touch"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(s"$name: incomplete command")
  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")
    val command_name = tokens(0)

    if (input.isEmpty || tokens.isEmpty)
      emptyCommand
    else if (MKDIR_NAME.equals(command_name))
      if (tokens.length < 2)
        incompleteCommand(MKDIR_NAME)
      else
        new Mkdir(tokens(1))
    else if (LS_NAME.equals(command_name))
      new Ls
    else if (PWD_NAME.equals(command_name))
      new Pwd
    else if (TOUCH_NAME.equals(command_name))
      if (tokens.length < 2)
        incompleteCommand(TOUCH_NAME)
      else
        new Touch(tokens(1))
    else if (CD_NAME.equals(command_name))
      if (tokens.length < 2)
        incompleteCommand(CD_NAME)
      else
        new Cd(tokens(1))
    else
      new UnkownCommand
  }
}