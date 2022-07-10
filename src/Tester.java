import shop.UserInterface;

import javax.swing.*;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) {

        Runnable FileChooserThread = () -> {
            try {
                new UserInterface();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        SwingUtilities.invokeLater(FileChooserThread);

    }
}
