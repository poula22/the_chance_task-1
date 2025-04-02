package checker

import util.checkResult
import problem.isIPV4Valid
import util.generateRandomIPv4

fun runIpv4Checks() {
    runEdgeCases()
    runValidationTestCases()
    runCorrectAnswersTestCases()
}

private fun runEdgeCases() {
    checkResult(
        description = "When ipv4 has empty string then return false",
        actualResult = isIPV4Valid(""),
        expectedResult = false
    )
    checkResult(
        description = "when ipv4 has segment more than 255 then return false",
        actualResult = isIPV4Valid("256.1.1.1"),
        expectedResult = false
    )

    checkResult(
        description = "when ipv4 segment less than 0 then return false",
        actualResult = isIPV4Valid("-1.233.233.233"),
        expectedResult = false
    )

    checkResult(
        description = "when ipv4 segments are comma seperated then return false",
        actualResult = isIPV4Valid("1,2,3,4"),
        expectedResult = false
    )

    checkResult(
        description = "when ipv4 contains 5 segments then return false",
        actualResult = isIPV4Valid("1.1.1.1.1"),
        expectedResult = false
    )
}

private fun runValidationTestCases() {
    checkResult(
        description = "when ipv4 contains 01 or 001 then return false",
        actualResult = isIPV4Valid("01.001.1.1"),
        expectedResult = false
    )

    checkResult(
        description = "when ipv4 is empty seperated with dots then return false",
        actualResult = isIPV4Valid("..."),
        expectedResult = false
    )

    checkResult(
        description = "when ipv4 contains invalid characters then return false",
        actualResult = isIPV4Valid("a.b.b.!"),
        expectedResult = false
    )
}

private fun runCorrectAnswersTestCases() {
    checkResult(
        description = "when ipv4 is 1.1.1.1 then return true",
        actualResult = isIPV4Valid("1.1.1.1"),
        expectedResult = true
    )

    checkResult(
        description = "when ipv4 is 255.255.255.255 then return true",
        actualResult = isIPV4Valid("255.255.255.255"),
        expectedResult = true
    )

    checkResult(
        description = "when ipv4 is random valid then return true",
        actualResult = isIPV4Valid(generateRandomIPv4()),
        expectedResult = true
    )
}