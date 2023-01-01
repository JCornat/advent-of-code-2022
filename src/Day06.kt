fun main() {
    fun hasRepetition(list: MutableList<Char>): Boolean {
        for (i in 0..list.size - 2) {
            var currentChar = list.get(i)

            for (j in i + 1..list.size - 1) {
                val char = list.get(j)
                if (currentChar == char) {
                    return true
                }
            }
        }

        return false
    }

    fun getStartPacketPosition(line: String): Int {
        val limit = 4
        var position = 0
        var markerList = mutableListOf<Char>()
        for (i in 0..line.length - 1) {
            val character: Char = line.get(i)
            if (markerList.size < limit - 1) {
                markerList.add(character)
                continue
            }

            markerList.add(character)
            if (!hasRepetition(markerList)) {
                position = i + 1
                break
            }

            markerList.removeFirst()
        }

        if (position == 0) {
            return -1
        }

        return position
    }

    fun getStartMessagePosition(line: String): Int {
        val limit = 14
        var position = 0
        var markerList = mutableListOf<Char>()
        for (i in 0..line.length - 1) {
            val character: Char = line.get(i)
            if (markerList.size < limit - 1) {
                markerList.add(character)
                continue
            }

            markerList.add(character)
            if (!hasRepetition(markerList)) {
                position = i + 1
                break
            }

            markerList.removeFirst()
        }

        if (position == 0) {
            return -1
        }

        return position
    }

    fun part1(input: List<String>): Int {
        var count = 0
        for (line in input) {
            count = getStartPacketPosition(line)
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (line in input) {
            count = getStartMessagePosition(line)
            println(count)
        }

        return count
    }

    val input = readInput("Day06")
//    val input = readInput("Day06_test")
//    val input = readInput("Day06_test2")

    val count = part1(input)
    println("How many characters need to be processed before the first start-of-packet marker is detected? $count")

    val sum2 = part2(input)
    println("How many characters need to be processed before the first start-of-message marker is detected? $sum2")
}
