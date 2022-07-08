public abstract class Product {
    private String name,description,producer;
    private Department department;
    private int amountAvailable;
    private int pricePerUnit;
    private int valueOfProduct;

    public Product(String name, String description, String producer, Department department, int amountAvailable, int pricePerUnit) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.department = department;
        this.amountAvailable = amountAvailable;
        this.pricePerUnit = pricePerUnit;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String producer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Department department() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int amountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public int pricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int valueOfProduct() {
        return valueOfProduct;
    }

    public void setValueOfProduct() {
        this.valueOfProduct = amountAvailable*pricePerUnit;
    }


    public String toString() {
        return "Product: " + name + "\ndescription: " + description + "\nproducer: " + producer +
                "\ndepartment: " + department + "\namount on storage: " + amountAvailable
                + "\nprice per unit: " + pricePerUnit;
    }
}
