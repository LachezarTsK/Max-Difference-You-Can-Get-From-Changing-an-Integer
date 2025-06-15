
package main
import "math"

const NOT_FOUND = -1
const DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE = 0
const DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE = 1
const DIGIT_TO_INSERT_FOR_MAX_VALUE = 9

func maxDiff(input int) int {
    numberOfDigits := 1 + int(math.Log10(float64(input)))
    minValue := createMinValueWithoutLeadingZerosAndGreaterThanZero(input, numberOfDigits)
    maxValue := createMaxValue(input, numberOfDigits)

    return maxValue - minValue
}

func createMinValueWithoutLeadingZerosAndGreaterThanZero(input int, numberOfDigits int) int {
    extractorForLeftmostValue := int(math.Pow(10.0, float64(numberOfDigits - 1)))
    leadingDigit := input / extractorForLeftmostValue

    digitToReplace := NOT_FOUND
    digitToInsertForExtremeValue := DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE
    minValue := DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE

    if leadingDigit > 1 {
        digitToInsertForExtremeValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE
        digitToReplace = leadingDigit
    }

    input %= extractorForLeftmostValue
    extractorForLeftmostValue /= 10

    for extractorForLeftmostValue > 0 {
        var leftmostDigit = input / extractorForLeftmostValue

        if leadingDigit != leftmostDigit && digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue {
            digitToReplace = leftmostDigit
        }
        if leftmostDigit == digitToReplace {
            leftmostDigit = digitToInsertForExtremeValue
        }

        minValue = 10 * minValue + leftmostDigit
        input %= extractorForLeftmostValue
        extractorForLeftmostValue /= 10
    }
    return minValue
}

func createMaxValue(input int, numberOfDigits int) int {
    maxValue := 0
    digitToReplace := NOT_FOUND
    extractorForLeftmostValue := int(math.Pow(10.0, float64(numberOfDigits - 1)))

    for extractorForLeftmostValue > 0 {
        leftmostDigit := input / extractorForLeftmostValue

        if digitToReplace == NOT_FOUND && leftmostDigit != DIGIT_TO_INSERT_FOR_MAX_VALUE {
            digitToReplace = leftmostDigit
        }
        if leftmostDigit == digitToReplace {
            leftmostDigit = DIGIT_TO_INSERT_FOR_MAX_VALUE
        }

        maxValue = 10 * maxValue + leftmostDigit
        input %= extractorForLeftmostValue
        extractorForLeftmostValue /= 10
    }
    return maxValue
}
