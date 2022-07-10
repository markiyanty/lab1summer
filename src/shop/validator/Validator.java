package shop.validator;

public class Validator {
    public static StringValidator validate(String value) {
        return new StringValidator(value);
    }
}
