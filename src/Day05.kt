fun main() {
    data class Step (val count: Int, val source: Int, val target: Int)

    fun getStackWidth(input: String): Int {
        return (input.length + 1) / 4
    }

    fun formatStacks(stacks: MutableList<MutableList<String>>, columnCount: Int): MutableList<MutableList<String>> {
        var res = mutableListOf<MutableList<String>>()
        for (i in 0 until columnCount) {
            var line = mutableListOf<String>()
            for (j in stacks.size - 1 downTo 0) {
                var stack = stacks.getOrNull(j)
                if (stack == null) {
                    break
                }

                val value = stack.getOrNull(i)
                if (value == null || value == "") {
                    continue
                }

                line.add(value)
            }

            res.add(line)
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
        val source = result!!.groupValues[2].toInt() - 1
        val target = result!!.groupValues[3].toInt() - 1
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
                    stacks = formatStacks(stacks, columnCount)
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

    fun executeSteps(stacks: MutableList<MutableList<String>>, steps: MutableList<Step>) {
        for (step in steps) {
            for (i in 0 until step.count) {
                var source = stacks.get(step.source)
                var target = stacks.get(step.target)
                var item = source.removeLast()
                target.add(item)
            }
        }
    }

    fun executeSteps2(stacks: MutableList<MutableList<String>>, steps: MutableList<Step>) {
        for (step in steps) {
            var tmpList = mutableListOf<String>()
            for (i in 0 until step.count) {
                var source = stacks.get(step.source)
                var item = source.removeLast()
                tmpList.add(item)
            }

            for (i in 0 until step.count) {
                var target = stacks.get(step.target)
                var item = tmpList.removeLast()
                target.add(item)
            }
        }
    }

    fun getTopStack(stacks: MutableList<MutableList<String>>): MutableList<String> {
        var res = mutableListOf<String>()
        for (stack in stacks) {
            res.add(stack.last())
        }

        return res
    }

    fun part1(input: List<String>): MutableList<String> {
        val parsedInput = parseInput(input)
        var stacks = parsedInput.first
        var steps = parsedInput.second
        executeSteps(stacks, steps)
        return getTopStack(stacks)
    }

    fun part2(input: List<String>): MutableList<String> {
        val parsedInput = parseInput(input)
        var stacks = parsedInput.first
        var steps = parsedInput.second
        executeSteps2(stacks, steps)
        return getTopStack(stacks)
    }

    val input = readInput("Day05")
//    val input = readInput("Day05_test")

    val topStack = part1(input)
    println("After the rearrangement procedure completes, what crate ends up on top of each stack? ${topStack.joinToString("")}")

    val topStack2 = part2(input)
    println("After the rearrangement procedure completes, what crate ends up on top of each stack? ${topStack2.joinToString("")}")
}
