package commands

import filesystem.State

trait Command extends (State => State) {}

object Command {

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
    else tokens(0) match {
      case CommandsNames.MKDIR_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.MKDIR_COMMAND_NAME)
        else
          new Mkdir(tokens(1))
      case CommandsNames.LS_COMMAND_NAME =>
        new Ls
      case CommandsNames.PWD_COMMAND_NAME =>
        new Pwd
      case CommandsNames.TOUCH_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.TOUCH_COMMAND_NAME)
        else
          new Touch(tokens(1))
      case CommandsNames.CD_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.CD_COMMAND_NAME)
        else
          new Cd(tokens(1))
      case CommandsNames.RM_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.RM_COMMAND_NAME)
        else
          new Rm(tokens(1))
      case CommandsNames.ECHO_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.ECHO_COMMAND_NAME)
        else
          new Echo(tokens.tail)
      case CommandsNames.CAT_COMMAND_NAME =>
        if (tokens.length < 2)
          incompleteCommand(CommandsNames.CAT_COMMAND_NAME)
        else
          new Cat(tokens(1))
      case CommandsNames.COPY_COMMAND_NAME =>
        if (tokens.length < 3)
          incompleteCommand(CommandsNames.COPY_COMMAND_NAME)
        else
          new Copy(tokens(1), tokens(2))
      case CommandsNames.MOVE_COMMAND_NAME =>
        if (tokens.length < 3)
          incompleteCommand(CommandsNames.MOVE_COMMAND_NAME)
        else
          new Move(tokens(1), tokens(2))
      case CommandsNames.HELP_COMMAND_NAME =>
        if (tokens.length < 2)
          new Help("")
        else
          new Help(tokens(1))
      case _ => new UnkownCommand
    }
  }
}