package problem

import kotlin.math.sqrt

/**
 * Time complexity: O(n2)
 * */
fun isValidSudoku(board: List<List<String>>): Boolean {
    if (board.isEmpty()) return false
    val columnSize = board[0].size
    val rowSize = board.size

    if (columnSize != rowSize) return false
    if (columnSize < 4) return false

    val divisionFactor = sqrt(columnSize.toDouble())
    if ((divisionFactor - divisionFactor.toInt()) != 0.0) return false

    if (!areSudokuNumbersValid(board, columnSize, divisionFactor.toInt())) return false
    if (!areSudokuColumnsValid(board, columnSize)) return false
    if (!areSudokuRowsValid(board, columnSize)) return false

    return areSudokuGridsValid(board, divisionFactor.toInt())
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuRowsValid(board: List<List<String>>, size: Int): Boolean {
    for (row in 0..<size) {
        val numbersSet = mutableSetOf<String>()
        for (column in 0..<size) {
            if (board[row][column] == "-") continue
            if (!numbersSet.add(board[row][column])) return false
        }
    }
    return true
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuColumnsValid(board: List<List<String>>, size: Int): Boolean {
    for (column in 0..<size) {
        val numbersSet = mutableSetOf<String>()
        for (row in 0..<size) {
            if (board[row][column] == "-") continue
            if (!numbersSet.add(board[row][column])) return false
        }
    }
    return true
}

private fun areSudokuGridsValid(board: List<List<String>>, divisionFactor: Int): Boolean {
    for (boxRow in 0..<divisionFactor) {
        for (boxCol in 0..<divisionFactor) {
            val numbersSet = mutableSetOf<String>()
            for (row in 0..<divisionFactor) {
                for (col in 0..<divisionFactor) {
                    val element = board[boxRow * divisionFactor + row][boxCol * divisionFactor + col]
                    if (element == "-") continue
                    if (!numbersSet.add(element)) return false
                }
            }
        }
    }
    return true
}

private fun areSudokuNumbersValid(
    board: List<List<String>>,
    size: Int,
    divisionFactor: Int
): Boolean {
    for (row in 0..<size) {
        for (column in 0..<size) {
            if (board[row][column] == "-") continue
            val element = board[row][column].toIntOrNull() ?: return false
            if (element < 1 || element > (divisionFactor * divisionFactor)) return false
        }
    }
    return true
}