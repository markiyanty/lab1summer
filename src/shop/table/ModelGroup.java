package shop.table;

import shop.entities.Group;
import shop.entities.Warehouse;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelGroup extends AbstractTableModel {
    List<Group> data = Warehouse.allGroups();

    String colNames[] = {"НАЗВА ГРУПИ", "ОПИС"};
    Class<?> colClasses[] = {String.class, String.class};

    public ModelGroup() {
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
            return data.get(rowIndex).getName();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getDescription();
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

            data.get(rowIndex).setName((String) aValue);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setDescription((String) aValue);
        }

    }

}