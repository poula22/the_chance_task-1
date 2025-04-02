package util

fun checkResult(
    description: String,
    actualResult: Boolean,
    expectedResult: Boolean
) {
    when {
        actualResult == expectedResult -> println("Success - $description")
        else -> println("Failed - $description")
    }
}