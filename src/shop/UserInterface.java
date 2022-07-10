package shop;

import shop.entities.Article;
import shop.entities.Group;
import shop.entities.Warehouse;
import shop.select.Option;
import shop.select.SelectOptions;
import shop.data_saver.DataReader;
import shop.data_saver.DataWriter;
import shop.table.ModelData;
import shop.table.ModelGroup;
import shop.validator.ValidationException;
import shop.validator.Validator;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

public class UserInterface extends JFrame {
    @Serial
    private static final long serialVersionUID = 2768304222530285489L;
    JFileChooser fileChooser;

    public UserInterface() throws IOException {
        super("FileChooserApp");
        initialize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400, 200);
    }

    public void initialize() throws IOException {
        JFrame f = new JFrame("Warehouse");

        f.setLayout(new FlowLayout());
        f.setBounds(350, 20, 750, 750);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else return false;
            }

            @Override
            public String getDescription() {
                return "Directories";
            }
        };
        fileChooser.setFileFilter(filter);

        Warehouse warehouse = DataReader.getDataFromJSON();

        // SAVE DATA ON CLOSE
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    DataWriter.writeDataToJSON(warehouse);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //TABLES
        JTable table = new JTable(new ModelData());
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(700, 400));

        JTable tableOfGroups = new JTable(new ModelGroup());
        JScrollPane scrollPaneG = new JScrollPane(tableOfGroups, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneG.setPreferredSize(new Dimension(300, 100));
        f.add(scrollPaneG);

        // ARTICLES
        // EDITING
        JButton editArticle = new JButton("EDIT ARTICLE");
        editArticle.addActionListener(e -> {
            SelectOptions options = new SelectOptions();
            for (Article article : warehouse.getArticles()) {
                options.addOption(article.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of article you want to edit: ",
                    "Edit Article", JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            Article article = warehouse.getArticleById(s.getKey());

            String newName;
            do {
                newName = JOptionPane.showInputDialog("New name:");
                if (newName == null) {
                    return;
                }
                try {
                    Validator.validate(newName).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            String newInfo;
            do {
                newInfo = JOptionPane.showInputDialog("New description:");
                if (newInfo == null) {
                    return;
                }
                try {
                    Validator.validate(newInfo).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            String producer;
            do {
                producer = JOptionPane.showInputDialog("New producer:");
                if (producer == null) {
                    return;
                }
                try {
                    Validator.validate(producer).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            String amount;
            int newAmount;
            do {
                amount = JOptionPane.showInputDialog("New amount:");
                if (amount == null) {
                    return;
                }
                try {
                    newAmount = Validator.validate(amount).notEmpty().toInt().greaterThan(0).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            String price;
            double newPrice;
            do {
                price = JOptionPane.showInputDialog("New price:");
                if (price == null) {
                    return;
                }
                try {
                    newPrice = Validator.validate(price).notEmpty().toDouble().greaterThan(0).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            article.setName(newName);
            article.setDescription(newInfo);
            article.setProducer(producer);
            article.setAmount(newAmount);
            article.setPrice(newPrice);

            table.setModel(new ModelData());
            JOptionPane.showInternalMessageDialog(null, "The article was edited.");
        });

        // ADDING
        JButton addArticle = new JButton("ADD ARTICLE");
        addArticle.addActionListener(e -> {
            String artName;
            do {
                artName = JOptionPane.showInputDialog("Enter the name of the article:");
                if (artName == null) {
                    return;
                }
                try {
                    Validator.validate(artName).notEmpty().notOneOf(warehouse.getArticles().stream().map(Article::getName).toList());
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            SelectOptions options = new SelectOptions();
            for (Group group : warehouse.getGroups()) {
                options.addOption(group.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of group: ", "Add article",
                    JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            Group group = warehouse.getGroupById(s.getKey());

            String info;
            do {
                info = JOptionPane.showInputDialog("Description:");
                if (info == null) {
                    return;
                }
                try {
                    Validator.validate(info).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            String producer;
            do {
                producer = JOptionPane.showInputDialog("Producer:");
                if (producer == null) {
                    return;
                }
                try {
                    Validator.validate(producer).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            String amount;
            int newAmount;
            do {
                amount = JOptionPane.showInputDialog("Amount:");
                if (amount == null) {
                    return;
                }
                try {
                    newAmount = Validator.validate(amount).notEmpty().toInt().greaterThan(0).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            String price;
            double newPrice;
            do {
                price = JOptionPane.showInputDialog("Price:");
                if (price == null) {
                    return;
                }
                try {
                    newPrice = Validator.validate(price).notEmpty().toDouble().greaterThan(0).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            try {
                warehouse.addArticle(group, artName, info, producer, newAmount, newPrice);
                table.setModel(new ModelData());
                JOptionPane.showInternalMessageDialog(null, "The article was added.");
            } catch (Exception ex) {
                JOptionPane.showInternalMessageDialog(null, ex.getMessage());
            }
        });

        // DELETING
        JButton deleteArticle = new JButton("DELETE ARTICLE");
        deleteArticle.addActionListener(e -> {
            SelectOptions options = new SelectOptions();
            for (Article article : warehouse.getArticles()) {
                options.addOption(article.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of article you want to delete: ",
                    "Delete Article", JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            warehouse.deleteArticle(s.getKey());
            table.setModel(new ModelData());
            JOptionPane.showInternalMessageDialog(null, "The article was deleted");
        });


        // GROUP
        // DELETE
        JButton deleteGroup = new JButton("DELETE GROUP");
        deleteGroup.addActionListener(e -> {

            SelectOptions options = new SelectOptions();
            for (Group group : warehouse.getGroups()) {
                options.addOption(group.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of group: ", "Delete group",
                    JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            warehouse.removeGroup(s.getKey());
            table.setModel(new ModelData());
            tableOfGroups.setModel(new ModelGroup());
            JOptionPane.showInternalMessageDialog(null, "The group was deleted.");
        });

        // EDIT
        JButton editGroup = new JButton("EDIT GROUP");
        editGroup.addActionListener(e -> {
            SelectOptions options = new SelectOptions();
            for (Group group : warehouse.getGroups()) {
                options.addOption(group.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of group: ", "Edit group",
                    JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            Group group = warehouse.getGroupById(s.getKey());

            String newName;
            do {
                newName = JOptionPane.showInputDialog("Enter a new name:");
                if (newName == null) {
                    return;
                }
                try {
                    Validator.validate(newName).notEmpty().notOneOf(
                            warehouse.getGroups().stream().map(Group::getName).toList());
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            String newDesc;
            do {
                newDesc = JOptionPane.showInputDialog("Enter a new description:");
                if (newDesc == null) {
                    return;
                }
                try {
                    Validator.validate(newDesc).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);


            group.setName(newName);
            group.setDescription(newDesc);
            table.setModel(new ModelData());
            tableOfGroups.setModel(new ModelGroup());
            JOptionPane.showInternalMessageDialog(null, "The group was edited.");

        });

        // ADD
        JButton addGroup = new JButton("ADD GROUP");
        addGroup.addActionListener(e -> {
            String newName;
            do {
                newName = JOptionPane.showInputDialog("Enter the name of group you want to add:");
                if (newName == null) {
                    return;
                }
                try {
                    Validator.validate(newName).notEmpty().notOneOf(
                            warehouse.getGroups().stream().map(Group::getName).toList());
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            String groupDesc;
            do {
                groupDesc = JOptionPane.showInputDialog("Enter the name of group you want to add:");
                if (groupDesc == null) {
                    return;
                }
                try {
                    Validator.validate(groupDesc).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            warehouse.addGroup(new Group(newName, groupDesc));
            JOptionPane.showInternalMessageDialog(null, "The group was added.");
        });


        // PURCHASES
        // DELIVERY
        JButton deliveryArticle = new JButton("DELIVERY");
        deliveryArticle.addActionListener(e -> {
            SelectOptions options = new SelectOptions();
            for (Article article : warehouse.getArticles()) {
                options.addOption(article.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of article that was delivered: ",
                    "Delivery", JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            Article art = warehouse.getArticleById(s.getKey());

            String amount;
            int newAmount;
            do {
                amount = JOptionPane.showInputDialog("Delivered amount:");
                if (amount == null) {
                    return;
                }
                try {
                    newAmount = Validator.validate(amount).notEmpty().toInt().greaterThan(0).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            art.setAmount(art.getAmount() + newAmount);
            table.setModel(new ModelData());
            JOptionPane.showInternalMessageDialog(null, "Delivery was accepted.");
        });

        // SELL
        JButton soldArticle = new JButton("SELL");
        soldArticle.addActionListener(e -> {
            SelectOptions options = new SelectOptions();
            for (Article article : warehouse.getArticles()) {
                options.addOption(article.convertToOption());
            }
            Object[] optionsData = options.getOptions().toArray();
            Option s = (Option) JOptionPane.showInputDialog(this, "Enter the name of article you sold: ", "Delivery",
                    JOptionPane.QUESTION_MESSAGE, null, optionsData, optionsData[0]);
            if (s == null) {
                return;
            }

            Article art = warehouse.getArticleById(s.getKey());
            int currentAmount = art.getAmount();

            String amount;
            int newAmount;
            do {
                amount = JOptionPane.showInputDialog("Sold amount:");
                if (amount == null) {
                    return;
                }
                try {
                    newAmount = Validator.validate(amount).notEmpty().toInt().lessThanOrEquals(currentAmount).end();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            art.setAmount(currentAmount - newAmount);
            table.setModel(new ModelData());
            JOptionPane.showInternalMessageDialog(null, "Product was sold.");
        });

        // STATISTICS
        // GENERAL
        JButton showGeneralStatistics = new JButton("GENERAL STATISTICS");
        showGeneralStatistics.addActionListener(e -> {

            ArrayList<String> groupsStatistics = new ArrayList<>();
            int totalArtAmount = 0;
            int totalGroupsValue = 0;

            for (int group = 0; group < warehouse.getGroups().size(); group++) {
                int groupValue = 0;
                int groupArtAmount = 0;
                StringBuilder groupStat = new StringBuilder("");
                Group g = warehouse.getGroups().get(group);
                groupArtAmount = g.getArticles().size();
                for (int i = 0; i < g.getArticles().size(); i++) {
                    groupValue += (int) (g.getArticles().get(i).getAmount() * g.getArticles().get(i).getPrice());
                }
                totalArtAmount += groupArtAmount;
                totalGroupsValue += groupValue;


            }
            String totalAmount = "\nTotal amount of articles: " + totalArtAmount;
            String totalValue = "\nTotal value of articles: " + totalGroupsValue;
            System.out.println(totalAmount);
            System.out.println(totalValue);
            JOptionPane.showInternalMessageDialog(null,
                    "\nTotal amount of articles: " + totalArtAmount + "\nTotal value of articles: " + totalGroupsValue);

        });

        // BY GROUP
        JButton showStatisticsByGroups = new JButton("STATISTICS BY GROUP");
        showStatisticsByGroups.addActionListener(e -> {
            ArrayList<String> groupsStatistics = new ArrayList<>();

            for (int group = 0; group < warehouse.getGroups().size(); group++) {
                int groupValue = 0;
                int groupArtAmount = 0;
                StringBuilder groupStat = new StringBuilder("");
                Group g = warehouse.getGroups().get(group);
                groupArtAmount = g.getArticles().size();
                for (int i = 0; i < g.getArticles().size(); i++) {
                    groupValue += (int) (g.getArticles().get(i).getAmount() * g.getArticles().get(i).getPrice());
                }
                groupStat.append("\n").append(g.getName()).append(": amount of articles - ").append(
                        groupArtAmount).append(", total value - ").append(groupValue);
                groupsStatistics.add(String.valueOf(groupStat));

            }
            System.out.println(groupsStatistics.stream().toList());
            JOptionPane.showInternalMessageDialog(null, "Statistics: " + groupsStatistics.stream().toList());
        });

        // FIND ARTICLES
        JButton searchArticle = new JButton("FIND ARTICLE");
        searchArticle.addActionListener(e -> {
            String artName;
            do {
                artName = JOptionPane.showInputDialog("Enter the name of article you want to find::");
                if (artName == null) {
                    return;
                }
                try {
                    Validator.validate(artName).notEmpty();
                    break;
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } while (true);

            Article a = warehouse.getArticle(artName);
            if (a == null) {
                JOptionPane.showInternalMessageDialog(null, "There is no such article found!",
                        "Error: no article found", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String output = warehouse.getArticleNames(a.getName());
            JOptionPane.showInternalMessageDialog(null, "The \n" + output + "was found.");
        });


        f.add(scrollPane);
        f.add(addArticle);
        f.add(editArticle);
        f.add(deleteArticle);
        f.add(addGroup);
        f.add(editGroup);
        f.add(deleteGroup);
        f.add(deliveryArticle);
        f.add(soldArticle);
        f.add(showGeneralStatistics);
        f.add(showStatisticsByGroups);
        f.add(searchArticle);

        f.setVisible(true);
    }
}
