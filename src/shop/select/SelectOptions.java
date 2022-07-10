package shop.select;

import java.util.ArrayList;

public class SelectOptions {
    private ArrayList<Option> options = new ArrayList<>();

    public SelectOptions() {}

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
