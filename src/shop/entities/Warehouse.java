package shop.entities;

import java.util.ArrayList;
import java.util.Locale;

public class Warehouse {
    private static ArrayList<Group> groups = new ArrayList<>();

    public static ArrayList<Group> allGroups() {
        return groups;
    }

    public static Group getGroupByArticle(String name) {
        if (groups != null) for (Group group : groups) {
            if (group.getArticles() == null) {
                continue;
            }
            for (Article a : group.getArticles()) {
                if (a.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) return group;
            }
        }
        return null;
    }


    public static ArrayList<Article> mergeAll() {
        ArrayList<Article> result = new ArrayList<>();
        if (groups != null) {
            for (Group group : groups) {
                if (group.getArticles() != null) result.addAll(group.getArticles());
            }
        }
        return result;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                groups.remove(group);
                break;
            }
        }
    }

    public Article getArticle(String name) {
        for (Group group : groups) {
            if (group.getArticles() == null) {
                continue;
            }
            for (Article a : group.getArticles()) {
                if (a.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) return a;
            }
        }
        return null;
    }

    public void deleteArticle(String id) {
        for (Group group : groups) {
            group.removeArticle(id);
        }
    }

    public void addArticle(Group group, String name, String description, String producer, int amount, double price) {
        group.addArticle(name, description, producer, amount, price);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getArticleNames(String name) {
        String s = "";
        if (groups == null) {
            return "There are no articles in this warehouse";
        }
        for (Group g : groups) {
            if (g.getArticles() == null) {
                continue;
            }
            for (Article a : g.getArticles()) {
                if (a.getName().equals(name)) {
                    s += a.getName() + " " + a.getDescription() + " " + a.getAmount() + "\n";

                }
            }
        }
        return s;
    }

    public Group getGroupById(String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public Article getArticleById(String id) {
        for (Group group : groups) {
            for (Article article : group.getArticles()) {
                if (article.getId().equals(id)) {
                    return article;
                }
            }
        }
        return null;
    }

    public ArrayList<Article> getArticles() {
        ArrayList<Article> result = new ArrayList<>();

        for (Group group : groups) {
            result.addAll(group.getArticles());
        }

        return result;
    }

    @Override
    public String toString() {
        String s = "";
        if (groups == null || groups.size() == 0) {
            return "Warehouse is empty";
        }
        for (Group g : groups) {
            s += g.toString() + "\n";
        }
        return s;
    }
}

