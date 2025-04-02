package checker

import util.checkResult
import problem.isValidSudoku

fun runSudokuChecks() {
    runEdgeCases()
    runValidationTestCases()
    testCorrectAnswers()
}

/** this function checks bounds */
private fun runEdgeCases() {
    //empty board
    checkResult(
        description = "when sudoku board is empty then return false",
        actualResult = isValidSudoku(
            listOf()
        ),
        expectedResult = false
    )

    //size 3X3
    checkResult(
        description = "when sudoku board less than min (size = 3X3) then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("-", "-", "-"),
                listOf("-", "-", "-"),
                listOf("-", "-", "-")
            )
        ),
        expectedResult = false
    )

    // not a square matrix 4X5
    checkResult(
        description = "when board is not a square matrix (size = 4X5) then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )

    //min 4X4 (sub grids 2X2)
    checkResult(
        description = "when sudoku board is of min size (size = 4X4) then return true",
        actualResult = isValidSudoku(
            listOf(
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-")
            )
        ),
        expectedResult = true
    )

    // size 6X6
    checkResult(
        description = "when sudoku board size is larger than min but not square seperated (size = 6X6) then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("-", "-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-", "-"),
                listOf("-", "-", "-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )
}

private fun runValidationTestCases() {
    // duplication in one row
    checkResult(
        description = "when 2 duplicate values found in a row then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("2", "-", "-", "2"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )
    // duplication in one column
    checkResult(
        description = "when 2 duplicate values found in a column then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("2", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("2", "-", "-", "-")
            )
        ),
        expectedResult = false
    )

    // duplication in one Grid
    checkResult(
        description = "when 2 duplicate values found in a grid then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("2", "-", "-", "-"),
                listOf("-", "2", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )

    checkResult(
        description = "when input is more than upper bound of range(1 to 4) for 4X4 board then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("7", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )

    checkResult(
        description = "when input is less than lower bound of range(1 to 4) for 4X4 board then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("-2", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-"),
                listOf("-", "-", "-", "-")
            )
        ),
        expectedResult = false
    )

    // unknown input
    checkResult(
        description = "when input is characters or wrong input then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("a", "B", "~", "!")
            )
        ),
        expectedResult = false
    )

    // empty string input
    checkResult(
        description = "when input is empty string then return false",
        actualResult = isValidSudoku(
            listOf(
                listOf("")
            )
        ),
        expectedResult = false
    )
}


fun testCorrectAnswers() {
    //test valid solution 4x4
    checkResult(
        description = "Test valid solution for 4x4, return true",
        actualResult = isValidSudoku(
            listOf(
                listOf("1", "2", "3", "4"),
                listOf("4", "3", "2", "1"),
                listOf("3", "4", "1", "2"),
                listOf("2", "1", "4", "3")
            )
        ),
        expectedResult = true
    )

    //test valid solution 9x9
    checkResult(
        description = "Test valid solution for 9x9 then return true",
        actualResult = isValidSudoku(
            listOf(
                listOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
                listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
                listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),

                listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
                listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
                listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),

                listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
                listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
                listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
            )
        ),
        expectedResult = true
    )

    //test valid solution 16x16
    checkResult(
        description = "Test valid solution for 16x16 then return true",
        actualResult = isValidSudoku(
            listOf(
                listOf("10", "11", "15", "4", "16", "8", "3", "9", "6", "13", "5", "12", "1", "2", "7", "14"),
                listOf("5", "16", "9", "3", "12", "1", "14", "6", "7", "4", "2", "11", "10", "15", "8", "13"),
                listOf("1", "13", "12", "14", "10", "5", "2", "7", "3", "15", "16", "8", "6", "4", "9", "11"),
                listOf("8", "7", "2", "6", "13", "4", "15", "11", "1", "9", "14", "10", "16", "12", "3", "5"),

                listOf("2", "9", "1", "12", "11", "3", "7", "10", "15", "5", "4", "6", "13", "16", "14", "8"),
                listOf("4", "8", "14", "13", "9", "15", "6", "12", "16", "1", "11", "7", "3", "5", "2", "10"),
                listOf("15", "10", "6", "5", "1", "16", "13", "4", "2", "3", "8", "14", "11", "7", "12", "9"),
                listOf("11", "3", "16", "7", "2", "14", "8", "5", "10", "12", "9", "13", "15", "1", "4", "6"),

                listOf("12", "15", "8", "10", "4", "6", "16", "3", "5", "7", "13", "9", "14", "11", "1", "2"),
                listOf("16", "1", "4", "9", "5", "12", "10", "15", "14", "11", "6", "2", "8", "3", "13", "7"),
                listOf("13", "6", "7", "2", "14", "9", "11", "8", "12", "16", "1", "3", "5", "10", "15", "4"),
                listOf("14", "5", "3", "11", "7", "2", "1", "13", "4", "8", "10", "15", "9", "6", "16", "12"),

                listOf("3", "14", "11", "15", "6", "10", "9", "1", "13", "2", "7", "4", "12", "8", "5", "16"),
                listOf("6", "2", "5", "1", "8", "7", "12", "14", "9", "10", "3", "16", "4", "13", "11", "15"),
                listOf("7", "12", "10", "8", "3", "13", "4", "16", "11", "14", "15", "5", "2", "9", "6", "1"),
                listOf("9", "4", "13", "16", "15", "11", "5", "2", "8", "6", "12", "1", "7", "14", "10", "3")
            )
        ),
        expectedResult = true
    )
}