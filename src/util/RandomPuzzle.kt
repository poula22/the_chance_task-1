package util

fun generateRandomIPv4() = List(4) {
    (0..255).random()
}.joinToString(".")

fun generateRandomPuzzle(boardRowSize: Int, boardColumnSize: Int) = List(boardRowSize) {
    generateSudokuRow(boardColumnSize)
}

private fun generateSudokuRow(boardColumnSize: Int) = List(boardColumnSize) {
    (1..boardColumnSize).random().toString()
}