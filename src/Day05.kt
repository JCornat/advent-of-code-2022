fun main() {
    fun getStackWidth(input: String): Int {
        return (input.length + 1) / 4
    }

    fun parseStackLine(input: String, size: Int): MutableList<String> {
        var stackLine = (List(size) { "" }).toMutableList()
        for (i in 0..size - 1) {
            val startIndex = i * 4
            val x = input.slice(startIndex..startIndex + 2)
            val regex = """\[([A-Z])\]""".toRegex()
            val result = regex.find(x)
            if (result?.value == null) {
                continue
            }

            stackLine.set(i, result!!.groupValues.last())
        }

        return stackLine.toMutableList()
    }

    fun parseStepLine(input: String): MutableList<Pair<Int, Int>> {
        val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        val result = regex.find(input)
        if (result?.groupValues == null) {
            check(true == false)
        }

        var steps = mutableListOf<Pair<Int, Int>>()
        val iterations = result!!.groupValues[1].toInt()
        for (i in 0..iterations) {
            val pair = Pair(result!!.groupValues[2].toInt(), result!!.groupValues[3].toInt())
            steps.add(pair)
        }

        return steps
    }

    fun getLineType(input: String): String {
        var res = ""
        if (input.startsWith("move ")) {
            res = "step"
        } else if (input.contains("[")) {
            res = "stack"
        }

        return res
    }

    fun parseInput(input: List<String>): Pair<MutableList<MutableList<String>>, MutableList<Pair<Int, Int>>> {
        val stackWidth = getStackWidth(input.first())
        var stacks = mutableListOf<MutableList<String>>()
        var steps = mutableListOf<Pair<Int, Int>>()
        for (line in input) {
            val lineType = getLineType(line)
            when (lineType) {
                "stack" -> {
                    val stackLine = parseStackLine(line, stackWidth)
                    stacks.add(stackLine)
                }
                "step" -> {
                    val stepLines = parseStepLine(line)
                    for (stepLine in stepLines) {
                        steps.add(stepLine)
                    }
                }
            }
        }

        return Pair(stacks, steps)
    }

    fun part1(input: List<String>): List<String> {
        parseInput(input)
        var topCrates = mutableListOf<String>()

        return topCrates.toList()
    }

    fun part2(input: List<String>): List<String> {
        var topCrates = mutableListOf<String>()
        for (line in input) {

        }

        return topCrates.toList()
    }

//    val input = readInput("Day05")
    val input = readInput("Day05_test")

    val totalContains = part1(input)
    println("In how many assignment pairs does one range fully contain the other? $totalContains")

    val totalOverlaps = part2(input)
    println("In how many assignment pairs do the ranges overlap? $totalOverlaps")
}
