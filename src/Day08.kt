fun main() {
    data class Cell(val height: Int, var visible: Boolean)

    fun parseMap(input: List<String>): List<List<Cell>> {
        var map: MutableList<List<Cell>> = mutableListOf()
        for (line in input) {
            var list = mutableListOf<Cell>()
            for (item in line) {
                val height = item.digitToInt()
                val cell = Cell(height, false)
                list.add(cell)
            }

            map.add(list)
        }

        return map
    }

    fun detectVisibleOnLine(list: List<Cell>) {
        var maxHeight = -1
        for (cell in list) {
            if (cell.height > maxHeight) {
                maxHeight = cell.height
                cell.visible = true
            }
        }
    }

    fun detectMap(map: List<List<Cell>>) {
        for (line in map) {
            detectVisibleOnLine(line)
            detectVisibleOnLine(line.reversed())
        }

        for (x in 0 until map.first().size) {
            var column = mutableListOf<Cell>()
            for (y in 0 until map.size) {
                val cell = map.get(y).get(x)
                column.add(cell)
            }

            detectVisibleOnLine(column)
            detectVisibleOnLine(column.reversed())
        }
    }

    fun countTotalVisible(map: List<List<Cell>>): Int {
        var res = 0
        for (y in 0 until map.size) {
            for (x in 0 until map.first().size) {
                val cell = map.get(y).get(x)
                if (cell.visible) {
                    res++
                }
            }
        }

        return res
    }

    fun part1(input: List<String>): Int {
        val map = parseMap(input)
        detectMap(map)
        val total = countTotalVisible(map)
        return total
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
        }

        return sum
    }

    val input = readInput("Day08")
//    val input = readInput("Day08_test")

    val totalVisible = part1(input)
    println("How many trees are visible from outside the grid? $totalVisible")

//    val sum2 = part2(input)
//    println("What is the sum of the priorities of those item types? $sum2")
}
