package newq;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class ModelData extends AbstractTableModel {
    List<Article> data = Warehouse.mergeAll();

    String colNames[] = {"СЕКЦІЯ", "ТОВАР", "ОПИС", "ВИРОБНИК", "КІЛЬКІСТЬ", "ЦІНА"};
    Class<?> colClasses[] = {String.class, String.class, String.class, String.class, Double.class, Double.class};
 
    ModelData() {
        super();

    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            String nameA = (String) getValueAt(rowIndex, 1);
            String nameG=Warehouse.getGroupByArticle(nameA).getName();
            return nameG;
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getName();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getDescription();
        }
        if (columnIndex == 3) {
            return data.get(rowIndex).getProducer();
        }
        if (columnIndex == 4) {
            return data.get(rowIndex).getAmount();
        }
        if (columnIndex == 5) {
            return data.get(rowIndex).getPrice();
        }
        return null;
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {

            data.get(rowIndex).setGroup((String) aValue);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setName((String) aValue);
        }
        if (columnIndex == 2) {
            data.get(rowIndex).setDescription((String) aValue);
        }
        if (columnIndex == 3) {
            data.get(rowIndex).setProducer((String) aValue);
        }
        if (columnIndex == 4) {
            data.get(rowIndex).setAmount((Integer) aValue);
        }
        if (columnIndex == 5) {
            data.get(rowIndex).setPrice((Double) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    public void fireTableStructureChanged(){};
}