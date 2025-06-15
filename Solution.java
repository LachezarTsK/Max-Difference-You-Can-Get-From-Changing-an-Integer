
public class Solution {

    private static final int NOT_FOUND = -1;
    private static final int DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE = 0;
    private static final int DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE = 1;
    private static final int DIGIT_TO_INSERT_FOR_MAX_VALUE = 9;

    public int maxDiff(int input) {
        int numberOfDigits = 1 + (int) Math.log10(input);
        int minValue = createMinValueWithoutLeadingZerosAndGreaterThanZero(input, numberOfDigits);
        int maxValue = createMaxValue(input, numberOfDigits);

        return maxValue - minValue;
    }

    private int createMinValueWithoutLeadingZerosAndGreaterThanZero(int input, int numberOfDigits) {
        int extractorForLeftmostValue = (int) Math.pow(10, numberOfDigits - 1);
        int leadingDigit = input / extractorForLeftmostValue;

        int digitToReplace = NOT_FOUND;
        int digitToInsertForExtremeValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE;
        int minValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE;

        if (leadingDigit > 1) {
            digitToInsertForExtremeValue = DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE;
            digitToReplace = leadingDigit;
        }

        input %= extractorForLeftmostValue;
        extractorForLeftmostValue /= 10;

        while (extractorForLeftmostValue > 0) {
            int leftmostDigit = input / extractorForLeftmostValue;

            if (leadingDigit != leftmostDigit && digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue) {
                digitToReplace = leftmostDigit;
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = digitToInsertForExtremeValue;
            }

            minValue = 10 * minValue + leftmostDigit;
            input %= extractorForLeftmostValue;
            extractorForLeftmostValue /= 10;
        }
        return minValue;
    }

    private int createMaxValue(int input, int numberOfDigits) {
        int maxValue = 0;
        int digitToReplace = NOT_FOUND;
        int extractorForLeftmostValue = (int) Math.pow(10, numberOfDigits - 1);

        while (extractorForLeftmostValue > 0) {
            int leftmostDigit = input / extractorForLeftmostValue;

            if (digitToReplace == NOT_FOUND && leftmostDigit != DIGIT_TO_INSERT_FOR_MAX_VALUE) {
                digitToReplace = leftmostDigit;
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = DIGIT_TO_INSERT_FOR_MAX_VALUE;
            }

            maxValue = 10 * maxValue + leftmostDigit;
            input %= extractorForLeftmostValue;
            extractorForLeftmostValue /= 10;
        }
        return maxValue;
    }
}
