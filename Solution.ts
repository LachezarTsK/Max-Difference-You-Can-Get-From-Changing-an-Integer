
function maxDiff(input: number): number {
    const numberOfDigits = 1 + Math.floor(Math.log10(input));
    const minValue = createMinValueWithoutLeadingZerosAndGreaterThanZero(input, numberOfDigits);
    const maxValue = createMaxValue(input, numberOfDigits);

    return maxValue - minValue;
};

class Util {
    static NOT_FOUND = -1;
    static DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE = 0;
    static DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE = 1;
    static DIGIT_TO_INSERT_FOR_MAX_VALUE = 9;
}

function createMinValueWithoutLeadingZerosAndGreaterThanZero(input: number, numberOfDigits: number): number {
    let extractorForLeftmostValue = Math.pow(10, numberOfDigits - 1);
    const leadingDigit = Math.floor(input / extractorForLeftmostValue);

    let digitToReplace = Util.NOT_FOUND;
    let digitToInsertForExtremeValue = Util.DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_EQUAL_TO_ONE;
    let minValue = Util.DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE;

    if (leadingDigit > 1) {
        digitToInsertForExtremeValue = Util.DIGIT_TO_INSERT_FOR_MIN_VALUE_WHEN_LEADING_DIGIT_IS_GREATER_THAN_ONE;
        digitToReplace = leadingDigit;
    }

    input %= extractorForLeftmostValue;
    extractorForLeftmostValue = Math.floor(extractorForLeftmostValue / 10);

    while (extractorForLeftmostValue > 0) {
        let leftmostDigit = Math.floor(input / extractorForLeftmostValue);

        if (leadingDigit !== leftmostDigit && digitToReplace === Util.NOT_FOUND && leftmostDigit !== digitToInsertForExtremeValue) {
            digitToReplace = leftmostDigit;
        }
        if (leftmostDigit === digitToReplace) {
            leftmostDigit = digitToInsertForExtremeValue;
        }

        minValue = 10 * minValue + leftmostDigit;
        input %= extractorForLeftmostValue;
        extractorForLeftmostValue = Math.floor(extractorForLeftmostValue / 10);
    }
    return minValue;
}

function createMaxValue(input: number, numberOfDigits: number): number {
    let maxValue = 0;
    let digitToReplace = Util.NOT_FOUND;
    let extractorForLeftmostValue = Math.pow(10, numberOfDigits - 1);

    while (extractorForLeftmostValue > 0) {
        let leftmostDigit = Math.floor(input / extractorForLeftmostValue);

        if (digitToReplace === Util.NOT_FOUND && leftmostDigit !== Util.DIGIT_TO_INSERT_FOR_MAX_VALUE) {
            digitToReplace = leftmostDigit;
        }
        if (leftmostDigit === digitToReplace) {
            leftmostDigit = Util.DIGIT_TO_INSERT_FOR_MAX_VALUE;
        }

        maxValue = 10 * maxValue + leftmostDigit;
        input %= extractorForLeftmostValue;
        extractorForLeftmostValue = Math.floor(extractorForLeftmostValue / 10);
    }
    return maxValue;
}
