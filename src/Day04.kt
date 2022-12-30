fun main() {
    fun parseSections(line: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val sections = line.split(",");
        val leftSection = sections.first().split("-");
        val rightSection = sections.last().split("-");
        return Pair(
            Pair(leftSection.first().toInt(), leftSection.last().toInt()),
            Pair(rightSection.first().toInt(), rightSection.last().toInt())
        )
    }

    fun isContained(section: Pair<Int, Int>, value: Int): Boolean {
        if (value < section.first) {
            return false
        }

        if (value > section.second) {
            return false
        }

        return true
    }

    fun checkFullContain(leftSection: Pair<Int, Int>, rightSection: Pair<Int, Int>): Boolean {
        if (isContained(leftSection, rightSection.first) && isContained(leftSection, rightSection.second)) {
            return true
        }

        if (isContained(rightSection, leftSection.first) && isContained(rightSection, leftSection.second)) {
            return true
        }

        return false
    }

    fun checkOverlap(leftSection: Pair<Int, Int>, rightSection: Pair<Int, Int>): Boolean {
        if (isContained(leftSection, rightSection.first)) {
            return true
        }

        if (isContained(leftSection, rightSection.second)) {
            return true
        }

        if (isContained(rightSection, leftSection.first)) {
            return true
        }

        if (isContained(rightSection, leftSection.second)) {
            return true
        }

        return false
    }

    fun part1(input: List<String>): Int {
        var totalContains = 0
        for (line in input) {
            val (leftSection, rightSection) = parseSections(line)
            if (!checkFullContain(leftSection, rightSection)) {
                continue
            }

            totalContains++
        }

        return totalContains
    }

    fun part2(input: List<String>): Int {
        var totalContains = 0
        for (line in input) {
            val (leftSection, rightSection) = parseSections(line)
            if (!checkOverlap(leftSection, rightSection)) {
                continue
            }

            totalContains++
        }

        return totalContains
    }

    val input = readInput("Day04")
//    val input = readInput("Day04_test")

    val totalContains = part1(input)
    println("In how many assignment pairs does one range fully contain the other? $totalContains")

    val totalOverlaps = part2(input)
    println("In how many assignment pairs do the ranges overlap? $totalOverlaps")
}
