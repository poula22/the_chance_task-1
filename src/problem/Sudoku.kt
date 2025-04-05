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
    val divisionFactor = sqrt(rowSize.toDouble()).toInt()
    if (columnSize != divisionFactor * divisionFactor) return false
    return isBoardValid(board, divisionFactor)
}

private fun isBoardValid(board: List<List<String>>, divisionFactor: Int): Boolean {
    return areSudokuNumbersValid(board)
            && areSudokuColumnsValid(board)
            && areSudokuRowsValid(board)
            && areSudokuGridsValid(board, divisionFactor)
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuNumbersValid(
    board: List<List<String>>
): Boolean {
    for (row in board.indices) {
        for (column in board.indices) {
            val element = board[row][column]
            if (element == "-") continue
            if (element.toIntOrNull() !in 1..board.size) return false
        }
    }
    return true
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuRowsValid(board: List<List<String>>): Boolean {
    for (row in board.indices) {
        val numbersSet = mutableSetOf<String>()
        for (column in board.indices) {
            val element = board[row][column]
            if (element == "-") continue
            if (!numbersSet.add(element)) return false
        }
    }
    return true
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuColumnsValid(board: List<List<String>>): Boolean {
    for (column in board.indices) {
        val numbersSet = mutableSetOf<String>()
        for (row in board.indices) {
            if (board[row][column] == "-") continue
            if (!numbersSet.add(board[row][column])) return false
        }
    }
    return true
}

private fun areSudokuGridsValid(board: List<List<String>>, divisionFactor: Int): Boolean {
    val grids = Array(board.size) { mutableSetOf<String>() }
    for (rowIndex in board.indices) {
        val gridRowFactor = (rowIndex / divisionFactor) * divisionFactor
        for (columnIndex in board.indices) {
            val element = board[rowIndex][columnIndex]
            if (element == "-") continue
            val gridColumnFactor = columnIndex / divisionFactor
            val gridIndex = gridRowFactor + gridColumnFactor
            if (!grids[gridIndex].add(element)) return false
        }
    }
    return true
}