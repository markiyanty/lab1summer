package newq;/*
 * Author: Heorhii Sanchenko and Zahryvyi Oleh
 * File: Tester.java
 * Task: Tester of userinterface
 */
import javax.swing.SwingUtilities;

public class Tester {
    public static void main(String[] args) {
    	
    	Runnable FileChooserThread = new Runnable() {
			public void run() {
				new UserInterface();
			}
		};
 
		SwingUtilities.invokeLater(FileChooserThread);
    	
    }
}
