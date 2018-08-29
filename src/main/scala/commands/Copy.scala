package commands

import files.DirEntry
import filesystem.State

class Copy(originFile: String, destinationFile: String) extends CreateEntry(destinationFile) {
  override def createSpecificEntry(state: State): DirEntry = ???
}
