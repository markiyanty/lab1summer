package DataCheck;

import DataCheck.Exceptions.InvalidDepartmentNameException;

import javax.swing.*;


public class DepartmentName {
    String name;

    /**
     * Назва department
     *
     * @param name
     * @throws InvalidDepartmentNameException
     */
    public DepartmentName(String name) throws InvalidDepartmentNameException {
        if (name.split(" ")[0].equals("Секція")) {
            this.name = name;
            fixString(this.name);
        } else {
            JOptionPane.showInternalMessageDialog(null, "The name of a department must begin with \"Секція\".", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new InvalidDepartmentNameException("The name of a department must begin with \"Секція\".\n" +
                    name + " is not valid.");
        }
    }

    /**
     * корегує назву кафедри з урахуванням входження під час написання слова «department»
     *
     * @param name
     */
    private void fixString(String name) {
        char[] data = name.toCharArray();

        for (int i = 7; i < data.length - 1; i++) {
            if (data[i] == ' ')
                data[i + 1] = Character.toLowerCase(data[i + 1]);
        }

        this.name = String.valueOf(data);
    }

    @Override
    public String toString() {
        return "Секція{" +
                "name='" + name + " of " + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
