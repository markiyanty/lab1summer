package shop.data_saver;

import shop.entities.Group;
import shop.entities.Warehouse;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataWriter {
    public static void writeDataToJSON(Warehouse warehouse) throws IOException {
        JSONArray result = new JSONArray();
        ArrayList<Group> groups = warehouse.getGroups();

        for (Group group : groups) {
            result.put(group.toJSON());
        }

        String outputData = result.toString();
        FileWriter fr = new FileWriter("data.json");
        fr.write(outputData);
        fr.close();
    }
}
