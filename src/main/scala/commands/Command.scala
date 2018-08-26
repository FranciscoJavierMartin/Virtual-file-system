package commands

import filesystem.State

trait Command {

  def apply(state:State):State
}

object Command{

  val MKDIR_NAME: String = "mkdir"
  val CD_NAME: String = "cd"
  val LS_NAME = "ls"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(s"$name: incomplete command")
  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if (input.isEmpty || tokens.isEmpty)
      emptyCommand
    else if (MKDIR_NAME.equals(tokens(0)))
      if (tokens.length < 2)
        incompleteCommand(MKDIR_NAME)
      else
        new Mkdir(tokens(1))
    else if (LS_NAME.equals(tokens(0)))
      new Ls
    else
      new UnkownCommand
  }
}