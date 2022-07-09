package newq.data_saver;

import newq.Article;
import newq.Group;
import newq.Warehouse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    public static Warehouse getDataFromJSON() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data.json"));
        StringBuilder jsonData = new StringBuilder();
        String str;
        do {
            str = br.readLine();
            jsonData.append(str);
        } while (str != null);
        br.close();

        JSONArray data = new JSONArray(jsonData.toString());
        Warehouse warehouse = new Warehouse();

        for (int i = 0; i < data.length(); i++) {
            JSONObject group = data.getJSONObject(i);

            String groupName = group.getString("name");
            String groupDesc = group.getString("description");
            String groupID = group.getString("id");

            JSONArray jsonArticles = group.getJSONArray("articles");
            ArrayList<Article> groupArticles = new ArrayList<>();

            for (int j = 0; j < jsonArticles.length(); j++) {
                JSONObject article = jsonArticles.getJSONObject(j);

                String articleId = article.getString("id");
                String articleName = article.getString("name");
                String articleDesc = article.getString("description");
                String articleProducer = article.getString("producer");
                int articleAmount = article.getInt("amount");
                int articlePrice = article.getInt("price");

                groupArticles.add(new Article(articleName, articleDesc, articleProducer, articleAmount, articlePrice, articleId));
            }

            warehouse.addGroup(new Group(groupName, groupDesc, groupArticles, groupID));
        }

        return warehouse;
    }
}
