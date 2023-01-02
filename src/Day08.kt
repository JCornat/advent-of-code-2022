fun main() {
    fun parseMap(input: List<String>): List<List<Int>> {
        var map: MutableList<List<Int>> = mutableListOf()
        for (line in input) {
            var list = mutableListOf<Int>()
            for (item in line) {
                list.add(item.digitToInt())
            }

            map.add(list)
        }

        return map
    }

    fun isVisible(map: List<List<Int>>, x: Int, y: Int): Boolean {
        val line = map.get(y)
        if (y == 0 || y == map.size - 1) {
            return true
        }

        if (x == 0 || x == line.size - 1) {
            return true
        }

        val cell = line.get(x)
        val topLine = map.get(y - 1)
        val topCell = topLine.get(x)
        if (cell > topCell) {
            return true
        }

        val rightCell = line.get(x + 1)
        if (cell > rightCell) {
            return true
        }

        val leftCell = line.get(x - 1)
        if (cell > leftCell) {
            return true
        }

        val bottomLine = map.get(y + 1)
        val bottomCell = bottomLine.get(x)
        if (cell > bottomCell) {
            return true
        }

        return false
    }

    fun countTotalVisible(map: List<List<Int>>): Int {
        var res = 0
        for (y in 0 until map.size) {
            val line = map.get(y)

            for (x in 0 until line.size) {
                val cell = line.get(x)
                if (isVisible(map, x, y)) {
                    res++
                }
            }
        }

        return res
    }

    fun part1(input: List<String>): Int {
        val map = parseMap(input)
        val total = countTotalVisible(map)
        return total
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
        }

        return sum
    }

//    val input = readInput("Day08")
    val input = readInput("Day08_test")

    val totalVisible = part1(input)
    println("How many trees are visible from outside the grid? $totalVisible")

//    val sum2 = part2(input)
//    println("What is the sum of the priorities of those item types? $sum2")
}
