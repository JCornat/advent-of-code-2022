fun main() {
    data class Step (val count: Int, val source: Int, val target: Int)

    fun getStackWidth(input: String): Int {
        return (input.length + 1) / 4
    }

    fun formatStacks(stacks: MutableList<MutableList<String>>, columnCount: Int): MutableList<MutableList<String>> {
        var res = mutableListOf<MutableList<String>>()
        for (stack in stacks) {
            val difference = columnCount - stack.size
            if (difference == 0) {
                continue
            }

            check(difference > 0)

            var stackLine = (List(difference) { "" }).toMutableList()
            stack.addAll(stackLine)
        }

        return res
    }

    fun parseStackLine(input: String): MutableList<String> {
        var stackLine = mutableListOf<String>()
        var stackWidth = getStackWidth(input)
        for (i in 0..stackWidth - 1) {
            val startIndex = i * 4
            val character = input.slice(startIndex..startIndex + 2)
            val regex = """\[([A-Z])\]""".toRegex()
            val result = regex.find(character)
            if (result?.value == null) {
                stackLine.add("")
                continue
            }

            stackLine.add(result!!.groupValues.last())
        }

        return stackLine.toMutableList()
    }

    fun parseStepLine(input: String): Step {
        val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        val result = regex.find(input)
        if (result?.groupValues == null) {
            check(true == false)
        }

        val count = result!!.groupValues[1].toInt()
        val source = result!!.groupValues[2].toInt()
        val target = result!!.groupValues[3].toInt()
        return Step(count, source, target)
    }

    fun parseColumnCount(input: String): Int {
        val regex = """(\d+)""".toRegex()
        val matches = regex.findAll(input)
            .map { it.value }
            .toList()

        return matches.last().toInt()
    }

    fun getLineType(input: String): String {
        var res = ""
        if (input.startsWith("move ")) {
            res = "step"
        } else if (input.contains("[")) {
            res = "stack"
        } else if (input.contains(" 1 ")) {
            res = "row"
        }

        return res
    }

    fun parseInput(input: List<String>): Pair<MutableList<MutableList<String>>, MutableList<Step>> {
        var stacks = mutableListOf<MutableList<String>>()
        var steps = mutableListOf<Step>()
        for (line in input) {
            val lineType = getLineType(line)
            when (lineType) {
                "stack" -> {
                    val stackLine = parseStackLine(line)
                    stacks.add(stackLine)
                }
                "row" -> {
                    val columnCount = parseColumnCount(line)
                    formatStacks(stacks, columnCount)
                }
                "step" -> {
                    check(stacks.size > 0)

                    val step = parseStepLine(line)
                    steps.add(step)
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

    val topStack = part1(input)
    println("After the rearrangement procedure completes, what crate ends up on top of each stack? $topStack")

    val totalOverlaps = part2(input)
    println("In how many assignment pairs do the ranges overlap? $totalOverlaps")
}
