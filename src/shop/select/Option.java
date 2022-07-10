package shop.select;

public class Option {
    private String key;
    private String value;

    public Option(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return value;
    }
}
