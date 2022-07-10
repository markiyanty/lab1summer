package newq;

import java.util.ArrayList;
import java.util.Locale;

public class Warehouse {
    private static ArrayList<Group> groups = new ArrayList<>();

    public static ArrayList<Group> allGroups() {
        return groups;
    }

    /**
     * Gets group by article name
     *
     * @param name
     * @return Group
     */
    public static Group getGroupByArticle(String name) {
        if (groups != null)
            for (Group group : groups) {
                if (group.getArticles() == null) {
                    continue;
                }
                for (Article a : group.getArticles()) {
                    if (a.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                        return group;
                }
                // if(group.getArticle(name)!=null)return group.getArticle(name);
            }
        return null;
    }

    public static ArrayList<String> getGroupNames() {
        ArrayList<String> names = null;
        for (Group g : groups) {
            names.add(g.getName());
        }
        return names;
    }

    /**
     * Merges all articles in a single arraylist
     *
     * @return ArrayList<Article>
     */
    public static ArrayList<Article> mergeAll() {
        ArrayList<Article> result = new ArrayList<>();
        if (groups != null) {
            for (Group group : groups) {
                if (group.getArticles() != null)
                    result.addAll(group.getArticles());
            }
        }
        return result;
    }

    /**
     * Adds group to ArrayList
     *
     * @param name
     * @param description
     * @param articles
     */
    public void addGroup(String name, String description, ArrayList<Article> articles) {
        if (groups == null) {
            groups = new ArrayList<>();
            groups.add(new Group(name, description, articles));
        } else {
            groups.add(new Group(name, description, articles));
        }
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Adds group to ArrayList
     *
     * @param name
     * @param description
     */
//    public void addGroup(String name, String description, ArrayList<Article> articles, String ID) {
//        if (groups == null)
//            groups = new ArrayList<>();
//        groups.add(new Group(name, description, articles, ID));
//    }

    /**
     * Removes group from ArrayList
     *
     * @param name
     */
    public void removeGroup(String name) {
        if (groups != null)
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                    groups.remove(groups.get(i));
            }
    }

    /**
     * Gets article by name
     *
     * @param name
     * @return Article
     */
    public Article getArticle(String name) {
        for (Group group : groups) {
            if (group.getArticles() == null) {
                continue;
            }
            for (Article a : group.getArticles()) {
                if (a.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                    return a;
            }
        }
        return null;
    }

    public void deleteArticle(String name) {
        Group group = getGroupByArticle(name);
        group.removeArticle(name);

    }

    public Article addArticle(Group group, String name, String description,
                              String producer, int amount, double price) {
        group.addArticle(name, description, producer, amount, price);
        return null;
    }

    /**
     * Gets ArrayList of groups
     *
     * @return ArrayList<Group>
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Gets group by name
     *
     * @param name
     * @return Group
     */
    public Group getGroup(String name) {
        if (groups != null) {
            for (Group group : groups) {
                if (group.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                    return group;
            }
        }
        return null;
    }

    /**
     * Gets list of all articles
     *
     * @return String
     */
    public String listArticles() {
        StringBuilder res = new StringBuilder();
        for (Article a : mergeAll()) {
            res.append("").append(a).append(";\n");
        }
        return "ALL ARTICLES LISTED: \n" + res;
    }

    /**
     * Gets article names
     *
     * @return String
     */
    public String getArticleNames(String name) {
        String s = "";
        if (groups == null) {
            return "There are no articles in this warehouse";
        }
        for (Group g : groups) {
            if (g.getArticles() == null) {
                //s+= "There are no articles in this group";
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

    /**
     * Gets all info about articles
     *
     * @return String
     */
    public String getArticleInfo() {
        String s = "";
        if (groups == null) {
            return "There are no articles in this warehouse";
        }
        for (Group g : groups) {
            if (g.getArticles() == null) {
                continue;
            }
            for (Article a : g.getArticles()) {
                s += a.toString() + "\n";
            }
        }
        return s;
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

