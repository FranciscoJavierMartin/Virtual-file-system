package filesystem

import commands.Command
import files.Directory

object FileSystem extends App{

  val root=Directory.ROOT

  val initial = State(root, root).setMessage("Start to write")
  initial.show()

  io.Source.stdin.getLines().foldLeft(initial)((currentState, newLine) => {
    val state = Command.from(newLine).apply(currentState)
    state.show()
    state
  })
}
