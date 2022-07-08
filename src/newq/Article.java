package newq;

/*
 * Author: Heorhii Sanchenko and Zahryvyi Oleh
 * File: Article.java
 * Task: 3rd depth part, Article
 */
public class Article {


	private String name;
	private String description; 
	private String producer;
	private int amount;
	private double price;
	private String group;

	Article(String name, String description) {
		this.name = name;
		this.description = description;
	}

	Article( String name, String description, String producer, int amount, double price) {

		this.name = name;
		this.description = description;
		this.producer = producer;
		this.amount = amount;
		this.price = price;
		if(name == null || name.isEmpty()) {
        	this.name = "Unknown";
        }
        if(description == null ||description.isEmpty()) {
        	this.description = "Unknown";
        }
        if(producer == null || producer.isEmpty()) {
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
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets amount
	 * @return int
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Gets description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets producer
	 * @return String
	 */
	public String getProducer() {
		return producer;
	}

	public void setGroup(String group){
		this.group=group;
	}

	public String getGroup() {
		return group;
	}

	/**
	 * Sets amount
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Sets description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Sets producer
	 * @param producer
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return name + ", description '" + description + '\'' + ", producer '" + producer + '\'' + ", amount = " + amount
				+ ", price = " + price;
	}
}
