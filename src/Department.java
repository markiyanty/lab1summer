import java.util.ArrayList;

public abstract class Department {
    private String name;
    int amountOfProducts, valueOfDepartment ;
    ArrayList<Product> products;



    public Department(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
