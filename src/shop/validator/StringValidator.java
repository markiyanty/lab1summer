package shop.validator;

import java.util.List;

public class StringValidator {
    private String value;

    public StringValidator(String value) {
        this.value = value;
    }

    public StringValidator notOneOf(List<String> list) throws ValidationException {
        for (String s : list) {
            if (s.equals(value)) {
                throw new ValidationException("Input value shouldn't be in list!");
            }
        }
        return this;
    }

    public StringValidator notEmpty() throws ValidationException {
        if (!value.isEmpty()) {
            return this;
        }
        throw new ValidationException("Input value shouldn't be empty!");
    }

    public IntValidator toInt() throws ValidationException {
        try {
            return new IntValidator(Integer.parseInt(value));
        } catch (NumberFormatException ex) {
            throw new ValidationException("Input value should be type of integer!");
        }
    }

    public DoubleValidator toDouble() throws ValidationException {
        try {
            return new DoubleValidator(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
            throw new ValidationException("Input value should be type of double!");
        }
    }

    public String end() {
        return value;
    }
}
