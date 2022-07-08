package newq;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UserInterface extends JFrame {
	private static final long serialVersionUID = 2768304222530285489L;

	private JButton findArticle;
	private JButton manageArticles;
	private JButton getStatistics;
	private JButton saveToFile;

	JFileChooser fileChooser;

	public UserInterface() {
		super("FileChooserApp");
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400, 200);
	}

	public void initialize() {
		JFrame f = new JFrame("Warehouse");

		f.setLayout(new FlowLayout());
		f.setBounds(350,20,750, 750);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else
					return false;
			}

			@Override
			public String getDescription() {
				return "Directories";
			}
		};
		fileChooser.setFileFilter(filter);

		Warehouse warehouse = new Warehouse();
		// FRUIT
		warehouse.addGroup("Фруктова лавка", "фрукти з трансельванії");
		ArrayList<Article> articles1 = new ArrayList<>();
		Collections.addAll(articles1,
				new Article("Яблука", "Семеренко", "Україна", 800, 20),
				new Article("Банан", "Жовті", "Африка", 300, 29),
				new Article("Апельсин", "Кнопочки", "Бразилія", 450, 29));
		warehouse.getGroup("Фруктова лавка").setArticles(articles1);

		// BERRIES
		warehouse.addGroup("Ягідна лавка", "ягоди боба");
		ArrayList<Article> articles2 = new ArrayList<>();
		Collections.addAll(articles2,
				new Article("Черешня", "Солодка", "Україна", 150, 45),
				new Article("Полуниця", "Солона", "Польща", 200, 60));
		warehouse.getGroup("Ягідна лавка").setArticles(articles2);

		// VEGETABLES
		warehouse.addGroup("Овочева лавка", "овочі геркулеса");
		ArrayList<Article> articles3 = new ArrayList<>();
		Collections.addAll(articles3,
				new Article("Помідор", "Рожевий", "Україна", 600, 34),
				new Article("Картопля", "Жовта", "Україна", 800, 17));
		warehouse.getGroup("Овочева лавка").setArticles(articles3);

		// DIARY
		warehouse.addGroup("Молочна лавка", "від коровки Мілки");
		ArrayList<Article> articles4 = new ArrayList<>();
		Collections.addAll(articles4,
				new Article("Молоко", "2,5%", "Україна", 800, 30),
				new Article("Пармезан", "Італія", "Україна",130, 230));
		warehouse.getGroup("Молочна лавка").setArticles(articles4);

		// BAKERY
		warehouse.addGroup("Солодка лавка", "випічка паризька");
		ArrayList<Article> articles5 = new ArrayList<>();
		Collections.addAll(articles5,
				new Article("Шоколад", "Рошен","Україна", 800, 25),
				new Article("Печиво", "Езмеральда", "Україна", 500, 27));
		warehouse.getGroup("Солодка лавка").setArticles(articles5);


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
		editArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String artName = "";
				Group g;
				Article a;

				artName = JOptionPane.showInputDialog("Enter the name of article you want to edit: ");
				if (artName == null) {
					JOptionPane.showInternalMessageDialog(null, "No article entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				g = warehouse.getGroupByArticle(artName);
				if (g == null) {
					JOptionPane.showInternalMessageDialog(null, "The article does not exist!",
							"Error: non existing article", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					Article art =g.getArticle(artName);
					String n_n = JOptionPane.showInputDialog("New name:");
					if(n_n != null &&!n_n.isEmpty()){
						art.setName(n_n);
					}
					String info = JOptionPane.showInputDialog("New description:");
					if(info != null &&!info.isEmpty()){
						art.setDescription(info);
					}
					String producer = JOptionPane.showInputDialog("New producer:");
					if(producer != null &&!producer.isEmpty()){
						art.setProducer(producer);
					}
					String amount = JOptionPane.showInputDialog("New amount:");
					if(amount != null &&!amount.isEmpty()){

						while (!isNumber(amount)){
							JOptionPane.showInternalMessageDialog(null, "You can enter only positive numbers!",
									"Error: not a number", JOptionPane.ERROR_MESSAGE);
							String amount1 = JOptionPane.showInputDialog("New amount:");
							amount=amount1;
						}
						if(isNumber(amount)) {
							int b = Integer.parseInt(amount);
							art.setAmount(b);
						}

					}
					String price = JOptionPane.showInputDialog("New price:");
					if(price != null &&!price.isEmpty()){
						while (!isNumber(price)){
							JOptionPane.showInternalMessageDialog(null, "You can enter only numbers!",
									"Error: not a number", JOptionPane.ERROR_MESSAGE);
							String pr = JOptionPane.showInputDialog("New amount:");
							price=pr;
						}
						if(isNumber(price)) {
							double p = Double.parseDouble(price);
							art.setPrice(p);
						}
					}

					table.setModel(new ModelData());
					JOptionPane.showInternalMessageDialog(null, "The article was edited.");
					return;
					//}
				}

			}

		});

		// ADDING
		JButton addArticle = new JButton("ADD ARTICLE");
		addArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String artName = "";
				Group g;
				Article a;

				artName = JOptionPane.showInputDialog("Enter the name of article you want to add: ");
				while (artName == null) {
					JOptionPane.showInternalMessageDialog(null, "No article entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					String artName1 = JOptionPane.showInputDialog("Enter the name of article you want to add: ");
					artName= artName1;
				}
				g = warehouse.getGroupByArticle(artName);
				if (g == null) {
					// adding article
					String g_name = JOptionPane.showInputDialog("Group:");
					// ПЕРЕВІРКА НА ІСНУВАННЯ ГРУПИ if (groupName)
					if (warehouse.getGroup(g_name)==null) {
						JOptionPane.showInternalMessageDialog(null, "No group found, try again", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						Group group = warehouse.getGroup(g_name);
						String info = JOptionPane.showInputDialog("Description:");
						String producer = JOptionPane.showInputDialog("Producer:");
						String amount = JOptionPane.showInputDialog("New amount:");
						int b;
						if(amount != null &&!amount.isEmpty()){

							while (!isNumber(amount)){
								JOptionPane.showInternalMessageDialog(null, "You can enter only positive numbers!",
										"Error: not a number", JOptionPane.ERROR_MESSAGE);
								String amount1 = JOptionPane.showInputDialog("New amount:");
								amount=amount1;
							}
							if(isNumber(amount)) {
								 b = Integer.parseInt(amount);
								String price = JOptionPane.showInputDialog("New price:");
								if(price != null &&!price.isEmpty()){
									while (!isNumber(price)){
										JOptionPane.showInternalMessageDialog(null, "You can enter only numbers!",
												"Error: not a number", JOptionPane.ERROR_MESSAGE);
										String pr = JOptionPane.showInputDialog("New price:");
										price=pr;
									}
									if(isNumber(price)) {
										double p = Double.parseDouble(price);
										warehouse.addArticle(group, artName, info, producer, b, p);
										table.setModel(new ModelData());
										JOptionPane.showInternalMessageDialog(null, "The article was added.");
										return;

							}

						}else {
									warehouse.addArticle(group, artName, info, producer, b, 0.0);
									table.setModel(new ModelData());
									JOptionPane.showInternalMessageDialog(null, "The article was added.");
									return;

								}
							}
						}
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "The article already exists!",
							"Error: existing article", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		});

		// DELETING
		JButton deleteArticle = new JButton("DELETE ARTICLE");
		deleteArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String artName = "";
				Group g;
				Article a;

				artName = JOptionPane.showInputDialog("Enter the name of article you want to delete: ");
				if (artName == null) {
					JOptionPane.showInternalMessageDialog(null, "No article entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					a = warehouse.getArticle(artName);
					if (a == null) {
						JOptionPane.showInternalMessageDialog(null, "There is no such article found!",
								"Error: no article found", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						warehouse.deleteArticle(artName);
						table.setModel(new ModelData());
						JOptionPane.showInternalMessageDialog(null, "The " + artName + " was deleted.");
						return;
					}
				}
			}
		});




// GROUP
		// DELETE
		JButton deleteGroup = new JButton("DELETE GROUP");
		deleteGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String groupName = "";
				Group g;
				Article a;

				groupName = JOptionPane.showInputDialog("Enter the name of group you want to delete: ");
				if (groupName == null) {
					JOptionPane.showInternalMessageDialog(null, "No group entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// ПЕРЕВІРКА НА ІСНУВАННЯ ГРУПИ if (groupName)
				if (warehouse.getGroup(groupName)==null) {
					JOptionPane.showInternalMessageDialog(null, "No group found", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					warehouse.removeGroup(groupName);
					table.setModel(new ModelData());
					tableOfGroups.setModel(new ModelGroup());
					JOptionPane.showInternalMessageDialog(null, "The " +  groupName +" group was deleted.");
					return;
				}
			}

		});

		// EDIT
		JButton editGroup = new JButton("EDIT GROUP");
		editGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String groupName = "";
				groupName = JOptionPane.showInputDialog("Enter the name of group you want to edit: ");
				if (groupName == null) {
					JOptionPane.showInternalMessageDialog(null, "No group entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (warehouse.getGroup(groupName)==null) {
					JOptionPane.showInternalMessageDialog(null, "The group does not exist", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					Group g =warehouse.getGroup(groupName);
					String group_n = JOptionPane.showInputDialog("Enter a new name: ");
					if(group_n!= null &&!group_n.isEmpty()){
						g.setName(group_n);
					}
					String group_desc = JOptionPane.showInputDialog("Enter a new description: ");
					if(group_desc!= null &&!group_desc.isEmpty()){
						g.setDescription(group_desc);
					}


					table.setModel(new ModelData());
					tableOfGroups.setModel(new ModelGroup());
					JOptionPane.showInternalMessageDialog(null, "The group was edited.");
					return;
				}
			}

		});

		// ADD
		JButton addGroup = new JButton("ADD GROUP");
		addGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String group_desc = "";
				String groupName = "";

				groupName = JOptionPane.showInputDialog("Enter the name of group you want to add: ");
				if (groupName == null) {
					JOptionPane.showInternalMessageDialog(null, "No group entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// перевірка на неіснування групи
				if (warehouse.getGroup(groupName)!=null) {
					JOptionPane.showInternalMessageDialog(null, "The group exists already", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					group_desc = JOptionPane.showInputDialog("Enter the description of group you want to add: ");
					if(group_desc!= null &&!group_desc.isEmpty()){
						warehouse.addGroup(groupName, group_desc);
					}else
						warehouse.addGroup(groupName, "Unknown");

					table.setModel(new ModelData());
					tableOfGroups.setModel(new ModelGroup());
					JOptionPane.showInternalMessageDialog(null, "The " +  groupName +" group was added.");
					return;
				}

			}

		});
		JButton deliveryArticle = new JButton("DELIVERY");
		deliveryArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String artName = "";
				Group g;
				Article a;
				int currentAmount = 0;

				artName = JOptionPane.showInputDialog("Enter the name of article that was delivered: ");
				if (artName == null) {
					JOptionPane.showInternalMessageDialog(null, "No article entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				g = warehouse.getGroupByArticle(artName);
				if (g == null) {
					JOptionPane.showInternalMessageDialog(null, "The article does not exist!",
							"Error: non existing article", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					Article art =g.getArticle(artName);
					currentAmount = art.getAmount();
					String amount = JOptionPane.showInputDialog("Delivered amount:");
					if(amount != null && !amount.isEmpty()){

						while (!isNumber(amount)){
							JOptionPane.showInternalMessageDialog(null, "You can enter only positive numbers!",
									"Error: not a number", JOptionPane.ERROR_MESSAGE);
							String amount1 = JOptionPane.showInputDialog("Delivered amount:");
							amount = String.valueOf(Integer.parseInt(amount1) + currentAmount);
						}
						if(isNumber(amount)) {
							int b = Integer.parseInt(amount);
							art.setAmount(Integer.parseInt(amount)+currentAmount) ;
						}

					}
					table.setModel(new ModelData());
					JOptionPane.showInternalMessageDialog(null, "Delivery was accepted.");
					return;
					//}
				}

			}

		});
		JButton soldArticle = new JButton("SELL");
		soldArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String artName = "";
				Group g;
				Article a;
				int currentAmount = 0;

				artName = JOptionPane.showInputDialog("Enter the name of article you sold: ");
				if (artName == null) {
					JOptionPane.showInternalMessageDialog(null, "No article entered", "Error: no input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				g = warehouse.getGroupByArticle(artName);
				if (g == null) {
					JOptionPane.showInternalMessageDialog(null, "The article does not exist!",
							"Error: non existing article", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					Article art =g.getArticle(artName);
					currentAmount = art.getAmount();
					String amount = JOptionPane.showInputDialog("Sold amount:");
					if(amount != null && !amount.isEmpty()){

						while (!isNumber(amount)){
							JOptionPane.showInternalMessageDialog(null, "You can enter only positive numbers!",
									"Error: not a number", JOptionPane.ERROR_MESSAGE);
							String amount1 = JOptionPane.showInputDialog("Sold amount:");
							amount = String.valueOf(currentAmount - Integer.parseInt(amount1));
						}
						if(isNumber(amount)) {
							int b = Integer.parseInt(amount);
							art.setAmount(currentAmount-Integer.parseInt(amount));
						}

					}
					table.setModel(new ModelData());
					JOptionPane.showInternalMessageDialog(null, "Product was sold.");
					return;
					//}
				}

			}

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



		f.setVisible(true);
	}
	public boolean isNumber(String str) {
		if (str == null || str.isEmpty()) return false;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) return false;
		}
		return true;
	}
}
