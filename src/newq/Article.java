package newq;

import org.json.JSONObject;

import java.util.UUID;

/*
 * Author: Heorhii Sanchenko and Zahryvyi Oleh
 * File: Article.java
 * Task: 3rd depth part, Article
 */
public class Article implements JSONImage{
    private String name;
    private String description;
    private String producer;
    private int amount;
    private double price;
    private String group;
    private String id;

    public Article(String name, String description, String producer, int amount, double price, String ID) {
        this(name, description, producer, amount, price);
		this.id = ID;
    }

    Article(String name, String description, String producer, int amount, double price) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
        this.id = UUID.randomUUID().toString();
        if (name == null || name.isEmpty()) {
            this.name = "Unknown";
        }
        if (description == null || description.isEmpty()) {
            this.description = "Unknown";
        }
        if (producer == null || producer.isEmpty()) {
            this.producer = "Unknown";
        }
    }

    Article(String name, String description, int amount, double price) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.producer = "Unknown";
    }

    /**
     * Gets price
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets amount
     *
     * @return int
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets description
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets producer
     *
     * @return String
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Sets producer
     *
     * @param producer
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + ", description '" + description + '\'' + ", producer '" + producer + '\'' + ", amount = " + amount + ", price = " + price;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject result = new JSONObject();

        result.put("name", name);
        result.put("description", description);
        result.put("producer", producer);
        result.put("amount", amount);
        result.put("price", price);
        result.put("id", id);

        return result;
    }
}
