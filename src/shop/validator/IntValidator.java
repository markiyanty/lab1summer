package shop.validator;

public class IntValidator {
    private int value;

    public IntValidator(int value) {
        this.value = value;
    }

    public IntValidator greaterThan(int comparator) throws ValidationException {
        if (value > comparator) {
            return this;
        }
        throw new ValidationException("Input value should be greater than " + comparator + "!");
    }

    public IntValidator lessThanOrEquals(int comparator) throws ValidationException {
        if (value <= comparator) {
            return this;
        }
        throw new ValidationException("Input value should be less than or equal to " + comparator + "!");
    }

    public int end() {
        return value;
    }
}
