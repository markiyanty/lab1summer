package shop.entities;

import shop.select.IOptionConverter;
import shop.select.Option;
import shop.data_saver.JSONImage;
import org.json.JSONObject;

import java.util.UUID;

public class Article implements JSONImage, IOptionConverter {
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

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

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

    @Override
    public Option convertToOption() {
        return new Option(id, name);
    }
}
