package shop.entities;
import shop.entities.Article;
import shop.select.IOptionConverter;
import shop.select.Option;
import shop.data_saver.JSONImage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class Group implements JSONImage, IOptionConverter {
    private String name;
    private String description;
    private ArrayList<Article> articles = new ArrayList<>();
    private String id;

    public Group(String name, String description, ArrayList<Article> articles) {
        this.name = name;
        this.description = description;
        if (name == null) {
            this.name = "Unknown";
        }
        if (description == null) {
            this.description = "Unknown";
        }
        this.articles = new ArrayList<>();
        this.articles.addAll(articles);
        this.id = UUID.randomUUID().toString();
    }

    public Group(String name, String description, ArrayList<Article> articles, String ID) {
        this(name, description, articles);
        this.id = ID;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void addArticle(String name, String description, String producer, int amount, double price) {
        if (articles == null) articles = new ArrayList<>();
        articles.add(new Article(name, description, producer, amount, price));
    }

    public void removeArticle(String id) {
        Article article = null;
        for (Article _article : articles) {
            if (_article.getId().equals(id)) {
                article = _article;
                break;
            }
        }
        articles.remove(article);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }


    @Override
    public String toString() {
        String artString = "";
        if (articles == null) {
            return "Group:\n" + "name='" + name + "'" + ", \ndescription='" + description + "'" + ", \nThere are no articles in this group";
        }
        for (Article article : articles) {
            artString += article.toString() + "; \n";
        }
        return "Group:\n" + "name='" + name + "'" + ", \ndescription='" + description + "'" + ", \n" + artString;
    }

    @Override
    public JSONObject toJSON() {
        JSONArray jsonArticles = new JSONArray();
        for (Article article : articles) {
            jsonArticles.put(article.toJSON());
        }
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        result.put("articles", jsonArticles);

        return result;
    }

    @Override
    public Option convertToOption() {
        return new Option(id, name);
    }
}
