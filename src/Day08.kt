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

    fun getScoreInList(list: List<Cell>, index: Int): Int {
        var score = 0
        val cell = list.get(index)
        for (i in index + 1 until list.size) {
            score++

            val nextCell = list.get(i)
            if (cell.height <= nextCell.height) {
                break
            }
        }

        return score
    }

    fun scoreCell(row: List<Cell>, column: List<Cell>, x: Int, y: Int): Int {
        if (x == 0 || y == 0 || x == row.size - 1 || y == column.size - 1) {
            return 0
        }

        val top = getScoreInList(column.reversed(), column.size - 1 - y)
        val right = getScoreInList(row, x)
        val bottom = getScoreInList(column, y)
        val left = getScoreInList(row.reversed(), row.size - 1 - x)

        return top * right * bottom * left
    }

    fun scoreMap(map: List<List<Cell>>): Int {
        var res = 0
        for (y in 0 until map.size) {
            val row = map.get(y)

            for (x in 0 until map.first().size) {
                var column = mutableListOf<Cell>()
                for (y in 0 until map.size) {
                    val cell = map.get(y).get(x)
                    column.add(cell)
                }

                val score = scoreCell(row, column, x, y)
                if (score > res) {
                    res = score
                }
            }
        }

        return res
    }

    fun part2(input: List<String>): Int {
        val map = parseMap(input)
        detectMap(map)

        return scoreMap(map)
    }

    val input = readInput("Day08")
//    val input = readInput("Day08_test")

    val totalVisible = part1(input)
    println("How many trees are visible from outside the grid? $totalVisible")

    val score = part2(input)
    println("What is the highest scenic score possible for any tree? $score")
}
