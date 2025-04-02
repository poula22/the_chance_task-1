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

    if (!areSudokuColumnsValid(board, rowSize, columnSize)) return false
    if (!areSudokuRowsValid(board, rowSize, columnSize)) return false

    return areSudokuGridsValid(board, divisionFactor.toInt())
}


/**
 * Time complexity: O(n2)
* */
private fun areSudokuRowsValid(board: List<List<String>>, rowSize: Int, columnSize: Int): Boolean {
    for (row in 0 until rowSize) {
        val numbersSet = mutableSetOf<String>()
        var dashCounter = 0
        for (column in 0 until columnSize) {
            if (board[row][column] == "-") dashCounter++
            else numbersSet.add(board[row][column])
        }
        if ((numbersSet.size + dashCounter) != rowSize) return false
    }
    return true
}

/**
 * Time complexity: O(n2)
 * */
private fun areSudokuColumnsValid(board: List<List<String>>, rowSize: Int, columnSize: Int): Boolean {
    for (column in 0 until columnSize) {
        val numbersSet = mutableSetOf<String>()
        var dashCounter = 0
        for (row in 0 until rowSize) {
            if (board[row][column] == "-") dashCounter++
            else numbersSet.add(board[row][column])
        }
        if ((numbersSet.size + dashCounter) != columnSize) return false
    }
    return true
}

/**
 * based on binary we have four grids each one will be labeled starting from top left with 00,01,10,11.
 * The first digit will help us to calculate row start index. (digit * divisionFactor)
 * The second digit will help us to calculate column start index. (digit * divisionFactor)
 * Time complexity: O(n2) - we will neglect constant of 4 in (4 * n2)
* */
private fun areSudokuGridsValid(board: List<List<String>>, divisionFactor: Int): Boolean {
    for (grid in 0..< 4) {
        val rowStartIndex = (grid / 2) * divisionFactor
        val colStartIndex = (grid % 2) * divisionFactor
        val rowLastIndex = rowStartIndex + divisionFactor
        val colLastIndex = colStartIndex + divisionFactor
        val list = MutableList(divisionFactor * divisionFactor) { (it + 1).toString() }

        for (row in rowStartIndex ..< rowLastIndex) {
            for (col in colStartIndex ..< colLastIndex) {
                val element = board[row][col]
                if (element != "-") {
                    if (element in list) {
                        list.remove(element)
                    } else {
                        return false
                    }
                }
            }
        }
    }
    return true
}