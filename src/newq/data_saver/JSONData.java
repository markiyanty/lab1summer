package newq.data_saver;

import newq.Article;
import newq.Group;

import java.util.ArrayList;

public class JSONData {
    private ArrayList<Article> articles;
    private ArrayList<Group> groups;

    public JSONData(ArrayList<Article> articles, ArrayList<Group> groups) {
        this.articles = articles;
        this.groups = groups;
    }
}
