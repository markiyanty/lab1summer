package Structure;

import java.util.ArrayList;

public class Shop {
    int amountOfProducts, valueOfDepartment;
    ArrayList<Product> products;
    ArrayList<Department> departments;

    public Shop() {


    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }


    public void setDepartments(ArrayList<Department> departments) {
        departments.add(new Department("Department of Meat"));
        departments.add(new Department("Department of Fish"));
        departments.add(new Department("Department of Dairy Products"));
        departments.add(new Department("Department of Wheat and Pasta"));
        departments.add(new Department("Department of Fruits"));
        departments.add(new Department("Department of Veggies"));
        departments.add(new Department("Department of Snacks"));
        departments.add(new Department("Department of Sweats"));
        departments.add(new Department("Department of Bakery"));
        departments.add(new Department("Department of Frozen Food"));
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = products.size();
    }

    public int getValueOfDepartment() {
        return valueOfDepartment;
    }

    public void setValueOfDepartment() {
        for (int i = 0; i < products.size(); i++) {
            this.valueOfDepartment += products.get(1).valueOfProduct();
        }
    }
}
