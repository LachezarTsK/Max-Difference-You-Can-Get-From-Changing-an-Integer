
import kotlin.math.pow
import kotlin.math.log10

class Solution {

    private companion object {
        const val NOT_FOUND = -1
        const val DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE = 0
        const val DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE = 1
        const val DIGIT_TO_INSERT_FOR_MAX_VALUE = 9
    }

    fun maxDiff(input: Int): Int {
        val numberOfDigits = 1 + log10(input.toDouble()).toInt()
        val minValue = createMinValueWithoutLeadingZerosAndGreaterThanZero(input, numberOfDigits)
        val maxValue = createMaxValue(input, numberOfDigits)

        return maxValue - minValue
    }

    private fun createMinValueWithoutLeadingZerosAndGreaterThanZero(input: Int, numberOfDigits: Int): Int {
        var input = input
        var extractorForLeftmostValue = (10.0).pow(numberOfDigits - 1).toInt()
        var leadingDigit = input / extractorForLeftmostValue

        var digitToReplace = NOT_FOUND
        var digitToInsertForExtremeValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE
        var minValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE

        if (leadingDigit > 1) {
            digitToInsertForExtremeValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE
            digitToReplace = leadingDigit
        }

        input %= extractorForLeftmostValue
        extractorForLeftmostValue /= 10

        while (extractorForLeftmostValue > 0) {
            var leftmostDigit = input / extractorForLeftmostValue

            if (leadingDigit != leftmostDigit && digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue) {
                digitToReplace = leftmostDigit
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = digitToInsertForExtremeValue
            }

            minValue = 10 * minValue + leftmostDigit
            input %= extractorForLeftmostValue
            extractorForLeftmostValue /= 10
        }
        return minValue
    }

    private fun createMaxValue(input: Int, numberOfDigits: Int): Int {
        var input = input
        var maxValue = 0
        var digitToReplace = NOT_FOUND
        var extractorForLeftmostValue = (10.0).pow(numberOfDigits - 1).toInt()

        while (extractorForLeftmostValue > 0) {
            var leftmostDigit = input / extractorForLeftmostValue

            if (digitToReplace == NOT_FOUND && leftmostDigit != DIGIT_TO_INSERT_FOR_MAX_VALUE) {
                digitToReplace = leftmostDigit
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = DIGIT_TO_INSERT_FOR_MAX_VALUE
            }

            maxValue = 10 * maxValue + leftmostDigit
            input %= extractorForLeftmostValue
            extractorForLeftmostValue /= 10
        }
        return maxValue
    }
}
