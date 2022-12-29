fun main() {
    fun translateLetterToShape(letter: String): String {
        var res = ""
        when (letter) {
            "A", "X" -> res = "Rock"
            "B", "Y" -> res = "Paper"
            "C", "Z" -> res = "Scissor"
        }

        check(res != "")

        return res
    }

    fun translateLetterToOutcome(letter: String): String {
        var res = ""
        when (letter) {
            "X" -> res = "Lose"
            "Y" -> res = "Draw"
            "Z" -> res = "Win"
        }

        check(res != "")

        return res
    }

    fun evaluateOutcome(opponentShape: String, playerShape: String): String {
        var res = ""
        when (opponentShape) {
            playerShape -> {
                res = "Draw"
            }
            "Rock" -> {
                when (playerShape) {
                    "Paper" -> res = "Win"
                    "Scissor" -> res = "Lose"
                }
            }
            "Paper" -> {
                when (playerShape) {
                    "Scissor" -> res = "Win"
                    "Rock" -> res = "Lose"
                }
            }
            "Scissor" -> {
                when (playerShape) {
                    "Rock" -> res = "Win"
                    "Paper" -> res = "Lose"
                }
            }
        }

        check(res != "")

        return res;
    }

    fun evaluateShapeFromOutcome(opponentShape: String, outcome: String): String {
        var res = ""
        when (outcome) {
            "Draw" -> {
                res = opponentShape
            }
            "Lose" -> {
                when (opponentShape) {
                    "Rock" -> res = "Scissor"
                    "Paper" -> res = "Rock"
                    "Scissor" -> res = "Paper"
                }
            }
            "Win" -> {
                when (opponentShape) {
                    "Rock" -> res = "Paper"
                    "Paper" -> res = "Scissor"
                    "Scissor" -> res = "Rock"
                }
            }
        }

        check(res != "")

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

    fun parseShapes(line: String): Pair<String, String> {
        val list = line.split(" ")
        val leftLetter = list.first()
        val rightLetter = list.last()
        val opponentShape = translateLetterToShape(leftLetter)
        val playerShape = translateLetterToShape(rightLetter)
        return Pair(opponentShape, playerShape)
    }

    fun parseShapes2(line: String): Pair<String, String> {
        val list = line.split(" ")
        val leftLetter = list.first()
        val rightLetter = list.last()
        val opponentShape = translateLetterToShape(leftLetter)
        val outcome = translateLetterToOutcome(rightLetter)
        val playerShape = evaluateShapeFromOutcome(opponentShape, outcome)
        return Pair(playerShape, outcome)
    }

    fun part1(input: List<String>): Int {
        var score = 0
        for (line in input) {
            val (opponentShape, playerShape) = parseShapes(line)

            val outcome = evaluateOutcome(opponentShape, playerShape)
            score += calculateScore(playerShape, outcome)
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (line in input) {
            val (playerShape, outcome) = parseShapes2(line)

            score += calculateScore(playerShape, outcome)
        }

        return score
    }

    val input = readInput("Day02")
//    val input = readInput("Day02_test")

    val score = part1(input)
    println("What would your total score be if everything goes exactly according to your strategy guide? $score")

    val score2 = part2(input)
    println("Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide? $score2")
}
