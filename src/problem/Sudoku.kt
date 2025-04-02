package problem

import kotlin.math.sqrt

fun isValidSudoku(board: List<List<String>>): Boolean {
    if (board.isEmpty()) return false
    val columnSize = board[0].size
    val rowSize = board.size

    if (columnSize != rowSize) return false
    if (columnSize < 4) return false

    val divisionFactor = sqrt(columnSize.toDouble())
    if ((divisionFactor - divisionFactor.toInt()) !=  0.0) return false

    if (!areSudokuColumnsValid(board, rowSize, columnSize)) return false
    if (!areSudokuRowsValid(board, rowSize, columnSize)) return false

    val sudokuGrids = initSudokuGrids(board, rowSize, columnSize, divisionFactor.toInt())
    return isSudokuGridsValid(sudokuGrids)

}

private fun initSudokuGrids(
    board: List<List<String>>,
    rowSize: Int,
    columnSize: Int,
    divisionFactor: Int
): List<List<List<String>>> {
    val grids: MutableList<MutableList<MutableList<String>>> = mutableListOf()
    val nOfGrids = (rowSize / divisionFactor) * (columnSize / divisionFactor)

    for (gridIndex in 0 until nOfGrids) {
        val grid = mutableListOf<MutableList<String>>()
        for (row in 0 until divisionFactor) {
            val list = mutableListOf<String>()
            for (column in 0 until divisionFactor) {
                list.add("-")
            }
            grid.add(list)
        }
        grids.add(grid)
    }

    var startRowIndex = 0
    var startColumnIndex = -divisionFactor

    for (gridIndex in 0 until nOfGrids) {
        startColumnIndex += divisionFactor
        if (startColumnIndex > columnSize - 1) {
            startColumnIndex = 0
            startRowIndex += divisionFactor
        }
        for (gridRow in 0 until divisionFactor) {
            for (gridColumn in 0 until divisionFactor) {
                grids[gridIndex][gridRow][gridColumn] = board[startRowIndex + gridRow][startColumnIndex + gridColumn]
            }
        }
    }
    return grids
}

//o(n2)
private fun areSudokuRowsValid(board: List<List<String>>, rowSize: Int, columnSize: Int): Boolean {
    val sortedBoard = board.map { it.sorted() }

    for (row in 0 until rowSize) {
        for (column in 0 until columnSize - 1) {
            if (sortedBoard[row][column] == "-") continue
            if (sortedBoard[row][column] == sortedBoard[row][column + 1]) return false
        }
    }
    return true
}

private fun areSudokuColumnsValid(board: List<List<String>>, rowSize: Int, columnSize: Int): Boolean {
    for (colum in 0 until columnSize) {
        for (row in 0 until rowSize - 1) {
            if (board[row][colum] == "-") continue

            for (nextRow in (row + 1) until rowSize) {
                if (board[row][colum] == board[nextRow][colum]) return false
            }
        }
    }
    return true
}

private fun isSudokuGridsValid(sudokuGrids: List<List<List<String>>>): Boolean {
    if (sudokuGrids.isEmpty()) return false
    val gridSize = sudokuGrids[0].size

    for (grid in sudokuGrids) {
        val list = MutableList(gridSize * gridSize) { (it+1).toString() }

        for (row in grid) {
            for (element in row) {
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