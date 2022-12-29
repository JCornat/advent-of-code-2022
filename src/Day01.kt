fun main() {
    fun part1(elfList: MutableList<Int>, input: List<String>): Int {
        var weight = 0
        for (line in input) {
            if (line == "") {
                elfList.add(weight)
                weight = 0
                continue
            }

            weight += line.toInt()
        }

        elfList.add(weight) // When loop is over, add the last elf

        elfList.sortDescending()
        return elfList.first()
    }

    fun part2(elfList: MutableList<Int>): Int {
        var res = 0
        elfList.slice(0 .. 2)
            .forEach {
                res += it
            }

        return res
    }

    val input = readInput("Day01")
//    val input = readInput("Day01_test")

    val elfList = mutableListOf<Int>()
    val totalCalories = part1(elfList, input)
    println("How many total Calories is that Elf carrying? $totalCalories")

    val top3Calories = part2(elfList)
    println("How many Calories are those Elves carrying in total? $top3Calories")
}
