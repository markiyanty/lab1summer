package shop.validator;

public class DoubleValidator {
    private double value;

    public DoubleValidator(double value) {
        this.value = value;
    }

    public DoubleValidator greaterThan(double comparator) throws ValidationException {
        if (value > comparator) {
            return this;
        }
        throw new ValidationException("Input value should be greater than " + comparator + "!");
    }

    public double end() {
        return value;
    }
}
