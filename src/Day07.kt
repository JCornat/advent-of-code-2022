data class File(val name: String, val size: Int)
class Folder(val name: String, val parent: Folder? = null) {
    var folders = mutableListOf<Folder>()
    var files = mutableListOf<File>()
    var size = 0

    init {
        if (parent != null) {
            parent.addFolder(this)
        }
    }

    fun addFile(name: String, size: Int) {
        val file = File(name, size)
        files.add(file)

        addSize(file.size)
    }

    fun addFolder(folder: Folder) {
        folders.add(folder)
    }

    fun getFolder(name: String): Folder? {
        var res: Folder? = null
        for (folder in folders) {
            if (folder.name == name) {
                res = folder
                break
            }
        }

        return res
    }

    protected fun addSize(value: Int) {
        size += value

        if (parent != null) {
            parent.addSize(value)
        }
    }
}

fun main() {
    lateinit var rootFolder: Folder
    lateinit var currentFolder: Folder

    fun parseCommand(value: String) {
        val line = value.substringAfter("$ ")
        val split = line.split(" ")
        val command = split.first()
        when (command) {
            "cd" -> {
                val path = split.get(1)
                check(path != null)

                when (path) {
                    ".." -> {
                        if (currentFolder.parent == null) {
                            throw Error("Folder without parent")
                        }

                        currentFolder = currentFolder.parent!!
                    }
                    "/" -> {
                        currentFolder = rootFolder
                    }
                    else -> {
                        val folder = currentFolder.getFolder(path)
                        check(folder != null)

                        currentFolder = folder
                    }
                }
            }
            "ls" -> {
                //
            }
            else -> {
                throw Error("Command $command not supported")
            }
        }
    }

    fun parseOutput(line: String) {
        val split = line.split(" ")
        val value = split.first()
        val name = split.last()
        when (value) {
            "dir" -> {
                Folder(name, currentFolder)
            }
            else -> {
                currentFolder.addFile(name, value.toInt())
            }
        }
    }

    fun parseLine(value: String) {
        if (value.startsWith("$")) {
            parseCommand(value)
        } else {
            parseOutput(value)
        }
    }

    var totalAtMost = 0
    fun browseFolder(folder: Folder, maxSize: Int) {
        var totalSize = folder.size
        for (childFolder in folder.folders) {
            browseFolder(childFolder, maxSize)
        }

        if (totalSize <= maxSize) {
            totalAtMost += totalSize
        }
    }

    var smallestFolder = 70000000
    fun browseFolder2(folder: Folder, minSize: Int) {
        for (childFolder in folder.folders) {
            if (childFolder.size < minSize) {
                continue
            }

            browseFolder2(childFolder, minSize)
        }

        if (folder.size >= minSize && folder.size < smallestFolder) {
            smallestFolder = folder.size
        }
    }

    fun part1(input: List<String>): Int {
        rootFolder = Folder("/")
        for (line in input) {
            parseLine(line)
        }

        browseFolder(rootFolder, 100000)
        return totalAtMost
    }

    fun part2(input: List<String>): Int {
        rootFolder = Folder("/")
        for (line in input) {
            parseLine(line)
        }

        val totalSpace = 70000000
        val minSpace = 30000000
        val takenSpace = rootFolder.size
        val freeSpace = totalSpace - takenSpace
        val spaceNeeded = minSpace - freeSpace

        browseFolder2(rootFolder, spaceNeeded)
        return smallestFolder
    }

    val input = readInput("Day07")
//    val input = readInput("Day07_test")

    val sum = part1(input)
    println("What is the sum of the total sizes of those directories? $sum")

    val totalSize = part2(input)
    println("What is the total size of that directory? $totalSize")
}
