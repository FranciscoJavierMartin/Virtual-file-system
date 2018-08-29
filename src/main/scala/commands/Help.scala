package commands

import filesystem.State

class Help(command: String) extends Command {
  override def apply(state: State): State = {

    state.setMessage(command match {
      case CommandsNames.MKDIR_COMMAND_NAME =>
        "mkdir [directoryName]"
      case CommandsNames.LS_COMMAND_NAME =>
        "ls"
      case CommandsNames.PWD_COMMAND_NAME =>
        "pwd"
      case CommandsNames.TOUCH_COMMAND_NAME =>
        "touch [filename]"
      case CommandsNames.CD_COMMAND_NAME =>
        "cd [directory]"
      case CommandsNames.RM_COMMAND_NAME =>
        "rm [filename]"
      case CommandsNames.ECHO_COMMAND_NAME =>
        "echo [content]"
      case CommandsNames.CAT_COMMAND_NAME =>
        "cat [filename]"
      case CommandsNames.COPY_COMMAND_NAME =>
        "copy [originfile] [destinyfile]"
      case CommandsNames.MOVE_COMMAND_NAME =>
        "move [originfile] [destinyfile]"
      case CommandsNames.HELP_COMMAND_NAME =>
        "help [command]"
      case _ => "Unknown command"
    })
  }
}
