package files

import filesystem.FileSystemExeption

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory =
    throw new FileSystemExeption("A file cannot be converted")

  override def getType: String = "File"

  override def asFile: File = this

  override def isDirectory: Boolean = false

  override def isFile: Boolean = true
}

object File {

  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}