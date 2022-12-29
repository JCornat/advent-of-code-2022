fun main() {
    fun translateLetterToShape(letter: Char): String {
        var res = ""
        when (letter) {
            'A', 'X' -> res = "Rock"
            'B', 'Y' -> res = "Paper"
            'C', 'Z' -> res = "Scissor"
        }

        return res
    }

    fun evaluate(opponent: String, you: String): String {
        var res = ""
        when (opponent) {
            you -> {
                res = "Draw"
            }
            "Rock" -> {
                when (you) {
                    "Paper" -> "Win"
                    "Scissor" -> "Lose"
                }
            }
            "Paper" -> {
                when (you) {
                    "Scissor" -> "Win"
                    "Rock" -> "Lose"
                }
            }
            "Scissor" -> {
                when (you) {
                    "Rock" -> "Win"
                    "Paper" -> "Lose"
                }
            }
        }

        return res;
    }

    fun calculateScore(shape: String, outcome: String): Int {
        var res = 0
        when (shape) {
            "Rock" -> res += 1
            "Paper" -> res += 2
            "Scissor" -> res += 3
        }

        when (outcome) {
            "Draw" -> res += 3
            "Win" -> res += 6
        }

        return res
    }

    fun parseShapes(line: String): (String, String) {
        line.split(" ")
    }

    fun part1(input: List<String>): Int {
        var score = 0
        for (line in input) {
            val opponentShape
        }

        return score
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

//    val input = readInput("Day02")
    val input = readInput("Day02_test")

    val score = part1(input)
    println("What would your total score be if everything goes exactly according to your strategy guide? $score")

    val score2 = part2(input)
    println("Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide? $score2")
}
