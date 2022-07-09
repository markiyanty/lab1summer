package newq;/*
 * Author: Heorhii Sanchenko and Zahryvyi Oleh
 * File: Tester.java
 * Task: Tester of userinterface
 */
import javax.swing.SwingUtilities;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
    	
    	Runnable FileChooserThread = new Runnable() {
			public void run() {
				try {
					new UserInterface();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
 
		SwingUtilities.invokeLater(FileChooserThread);
    	
    }
}
