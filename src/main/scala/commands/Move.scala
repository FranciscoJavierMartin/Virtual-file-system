package commands

import files.DirEntry
import filesystem.State

class Move(originFile: String, destinationFile: String) extends CreateEntry(destinationFile) {
  override def createSpecificEntry(state: State): DirEntry = ???
}
