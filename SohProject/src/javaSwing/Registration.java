package javaSwing;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javaSwing.MainWindow.ButtonPane;

import javax.swing.JOptionPane;

import java.awt.Color;

public class Registration extends JFrame implements ActionListener {

	private JPanel mainPane = new JPanel();
	private JLabel instruction = new JLabel("<html><center>The password must be of<br /> length greater than or equal to 6</center></html>");
	private JButton regisButton = new JButton("Register");
	private InputPane inputPane = new InputPane();
	
	//Constructor
	public Registration() {
		super("Registration to Jun Wei System");
		
		mainPane.setBorder(BorderFactory.createTitledBorder("Registration"));
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
		regisButton.addActionListener(this);
		
		instruction.setFont(new Font(Font.DIALOG, Font.PLAIN, 20) );
		instruction.setForeground(Color.RED);
		mainPane.add(instruction, Box.CENTER_ALIGNMENT);
		
		mainPane.add(inputPane, Box.CENTER_ALIGNMENT);
		
		mainPane.add(regisButton, Box.CENTER_ALIGNMENT);
		
		add(mainPane);
	}
	
	//---------------------------------------------------------------------------------------
	
	//Information input nested class
	static class InputPane extends JPanel {
		private JLabel userLabel = new JLabel("Username: ");
		private JLabel passLabel = new JLabel("Password: ");
		private JLabel confirmLabel = new JLabel("<html>Confirm: <br /> password </html>");
		private JTextField userField = new JTextField(30);
		private JPasswordField passField = new JPasswordField(30);
		private JPasswordField confirmField = new JPasswordField(30);
		
		private GridBagConstraints gbc = new GridBagConstraints();
		
		public InputPane() {
			setLayout(new GridBagLayout());
			
			gbc.anchor = GridBagConstraints.LINE_END;
			
			add(userLabel, gbc);
			
			gbc.gridx = 0; gbc.gridy = 1;
			add(passLabel, gbc);
			
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 0; gbc.gridy = 2;
			add(confirmLabel, gbc);
			
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 1; gbc.gridy = 0;
			add(userField, gbc);
			
			gbc.gridx = 1; gbc.gridy = 1;
			add(passField, gbc);
			
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc.gridx = 1; gbc.gridy = 2;
			add(confirmField, gbc);
		}
		
		private String getUsername() {
			return userField.getText();
		}
		
		private String getPassword() {
			return passField.getText();
		}
		
		private String getConfirmPW() {
			return confirmField.getText();
		}
		
	}

	//---------------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String username = inputPane.getUsername();
		String password = inputPane.getPassword();
		String confirm = inputPane.getConfirmPW();
		
		//If all of the fields is filled properly
		if (! (username.isEmpty() || password.isEmpty() || confirm.isEmpty() ) ) {
			//If the user name is not taken
			if (!Runner.isUsernameTaken( username ) ) {
				//If both password and confirm password is equal
				if (password.equals( confirm) ) {
					//If only the password is over length 6
					if (password.length() >= 6) {
						Runner.addEntry(username, password);
						JOptionPane.showMessageDialog(this, "Registration Successful!");
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(this, "Password is too short! It must be at least 6 characters long!");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Password and Confirm password does not match!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "The username \"" + inputPane.getUsername() + " is taken! Try another username");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Please ensure you've filled in all the fields required!");
		}
	}		//end of ActionPerformed
	
	
}		//end of Registration class

