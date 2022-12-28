fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day03")
    val testInput = readInput("Day03_test")
    var sum = 0;
    for (item in input) {
        val sizePer2 = item.length / 2;
        var leftPart = item.slice(0..sizePer2 - 1);
        var rightPart = item.slice(sizePer2..item.length - 1);

        val x = leftPart.find { it.equals('J') }
        var mutableList = mutableListOf<Char>();
        for (char in leftPart) {
            if (!rightPart.contains(char)) {
                continue;
            }

            if (mutableList.contains(char)) {
                continue;
            }

            mutableList.add(char);
//            println("$char ${char.code}" )
            val charCode = char.code;
            var convertedInt = 0;
            if (charCode < 97) {
                convertedInt = charCode - 64 + 26; //
            } else {
                convertedInt = charCode - 96; //
            }

            sum += convertedInt;
        }
//        val y = leftPart.indexOf('J')
//        println("$item : $leftPart - $rightPart")
    }

    println("sum $sum")

//    val input = readInput("Day03")

}
