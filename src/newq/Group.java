package newq;/*
 * Author: Heorhii Sanchenko and Zahryvyi Oleh
 * File: Group.java
 * Task: 2nd depth part, Group
 */
import java.util.ArrayList;
import java.util.Locale;

public class Group {
    private String name;
    private String description;
    private  ArrayList<Article> articles;

    public Group() {
    } 

    public Group(String name, String description, ArrayList<Article> articles) {
        this.name = name;
        this.description = description;
        if(name == null) {
        	this.name = "Unknown";
        }
        if(description == null) {
        	this.description = "Unknown";
        }
        this.articles = new ArrayList<>();
        this.articles.addAll(articles);
    }


    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        if(name == null) {
        	this.name = "Unknown";
        }
        if(description == null) {
        	this.description = "Unknown";
        }
    }

    /**
     * Adds article to arraylist
     * @param name
     * @param description
     * @param producer
     * @param amount
     * @param price
     */
    public void addArticle(String name, String description,
                           String producer, int amount, double price) {
        if (articles == null) articles = new ArrayList<>();
        articles.add(new Article(name, description, producer, amount, price));
    }


    /**
     * Removes article from arraylist
     * @param name
     */
    public void removeArticle(String name) {
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getName().equals(name)) articles.remove(articles.get(i));
        }
    }

    /**
     * Gets name
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Gets description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets articles arraylist
     * @return ArrayList<Article>
     */
    public  ArrayList<Article> getArticles() {
        return articles;
    }

    /**
     * Gets article by name
     * @param name
     * @return Article
     */
    public Article getArticle(String name) {
    	if(articles == null) {
    		return null;
    	}
        for (Article a : articles) {
            if (a.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) return a;
        }
        return null;
    }
    
    /**
     * Sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets articles
     * @param articles
     */
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /**
     * Gets total price of a group
     * @return double
     */
    public double getTotalPrice() {
        double result = 0;
        if(articles == null) {
        	return 0;
        }
        for (Article a : articles) {
            result +=  a.getPrice() *((double) a.getAmount());
        }
        return result;
    }

    @Override
    public String toString() {
        String artString = "";
        if(articles == null) {
        	return "Group:\n" +
                    "name='" + name + "'" +
                    ", \ndescription='" + description + "'" +
                    ", \nThere are no articles in this group";
        }
        for (Article article : articles) {
            artString += article.toString() + "; \n";
        }
        return "Group:\n" +
                "name='" + name + "'" +
                ", \ndescription='" + description + "'" +
                ", \n" + artString;
    }
}
