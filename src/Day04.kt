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
            return false;
        }

        if (value > section.second) {
            return false;
        }

        return true;
    }

    fun checkOverlap(leftSection: Pair<Int, Int>, rightSection: Pair<Int, Int>): Boolean {
        var lowerSection: Pair<Int, Int>
        var higherSection: Pair<Int, Int>
        if (leftSection.first < rightSection.first) {
            lowerSection = leftSection
            higherSection = rightSection
        } else {
            lowerSection = rightSection
            higherSection = leftSection
        }

        if (!isContained(lowerSection, higherSection.first)) {
            return false
        }

        if (!isContained(lowerSection, higherSection.second)) {
            return false
        }

        return true
    }

    fun part1(input: List<String>): Int {
        var overlaps = 0
        for (line in input) {
            val (leftSection, rightSection) = parseSections(line)
            if (!checkOverlap(leftSection, rightSection)) {
                continue
            }

            overlaps++
        }

        return overlaps
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day04")
//    val input = readInput("Day04_test")

    val assignments = part1(input)
    println("In how many assignment pairs does one range fully contain the other? $assignments")

//    val score2 = part2(input)
//    println("Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide? $score2")
}
