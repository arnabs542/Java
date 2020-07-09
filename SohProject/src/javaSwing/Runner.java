package javaSwing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Runner {

	static private ArrayList<String> usernames = new ArrayList<String>();
	static private ArrayList<String> passwords = new ArrayList<String>();
	
	public static void main(String[]args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				MainWindow mw = new MainWindow();
				mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mw.setResizable(false);
				mw.setLocationRelativeTo(null);
				mw.pack();
				mw.setVisible(true);
				
			}
		});
	}		//end of main()
	
	static public boolean isUsernameTaken(String username) {
		return usernames.contains(username);
	}
	
	static public boolean matchUsernamePassword(String username, String password) {
		if (usernames.contains(username) ) {
			int index = usernames.indexOf(username);
			return passwords.get(index).equals(password);
		}
		else
			return false;
	}
	
	static public void addEntry (String username, String password) {
		usernames.add(username);
		passwords.add(password);
	}
	
	
}		//end of class
