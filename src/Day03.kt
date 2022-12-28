fun main() {
    fun charToInt(char: Char): Int {
        val charCode = char.code
        var convertedInt = 0
        if (charCode < 97) {
            convertedInt = charCode - 64 + 26 //
        } else {
            convertedInt = charCode - 96 //
        }

        return convertedInt
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val sizePer2 = line.length / 2
            var leftPart = line.slice(0..sizePer2 - 1)
            var rightPart = line.slice(sizePer2..line.length - 1)

            var mutableList = mutableListOf<Char>()
            for (char in leftPart) {
                if (!rightPart.contains(char)) {
                    continue
                }

                if (mutableList.contains(char)) {
                    continue
                }

                mutableList.add(char)
                sum += charToInt(char);
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var matchingList = mutableListOf<Char>()
        for (i in input.indices) {
            val isFirstLine = (i % 3) == 0;
            if (!isFirstLine) {
                continue
            }

            val firstLine = input[i];
            val secondLine = input[i + 1];
            val thirdLine = input[i + 2]

            for (char in firstLine) {
                if (secondLine.contains(char) && thirdLine.contains(char)) {
                    matchingList.add(char)
                    break
                }
            }
        }

        var sum = 0
        for (char in matchingList) {
            sum += charToInt(char)
        }

        return sum
    }

    val input = readInput("Day03")
//    val input = readInput("Day03_test")

    val sum = part1(input)
    println("What is the sum of the priorities of those item types? $sum")

    val sum2 = part2(input)
    println("What is the sum of the priorities of those item types? $sum2")
}
