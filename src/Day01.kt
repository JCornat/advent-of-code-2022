fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    val mutableList = mutableListOf<Int>();
    var weight = 0;
    for (a in input) {
        if (a == "") {
            mutableList.add(weight);
            weight = 0;
            continue;
        }

        weight += a.toInt();
    }

    mutableList.add(weight); // When loop is over, add the last elf

    mutableList.sortDescending();
    val totalCalories = mutableList.first();
    println("How many total Calories is that Elf carrying? $totalCalories")

    var top3 = 0;
    mutableList.slice(0 .. 2)
        .forEach {
            top3 += it;
        }

    println("How many Calories are those Elves carrying in total? $top3")
}
