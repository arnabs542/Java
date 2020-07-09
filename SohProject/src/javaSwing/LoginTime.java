package javaSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginTime implements ActionListener {

	JFrame frame;
	JPanel panel;
	JLabel userNameMsg;
	JLabel passWordMsg;
	JLabel status;
	JTextField username;
	JPasswordField password;
	JButton login;
	JButton register;
	Font myFont = new Font(null, 0, 22);
	GridBagConstraints gbc = new GridBagConstraints();
	
	ArrayList<String> usernames = new ArrayList<String>();
	ArrayList<String> passwords = new ArrayList<String>();
	
	public LoginTime() {
		
		userNameMsg = new JLabel("Username: ");
		userNameMsg.setFont(myFont);
		passWordMsg = new JLabel("Password: ");
		passWordMsg.setFont(myFont);
		
		username = new JTextField(20);
		username.setSize(100, 100);
		password = new JPasswordField(20);
		password.setSize(50, 30);
		
		login = new JButton("Login");
		login.addActionListener(this);
		
		register = new JButton("Register");
		register.addActionListener(this);
		
		status = new JLabel();
		status.setFont(myFont);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder( BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK) );
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1; gbc.gridy = 1;
		panel.add(userNameMsg, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		panel.add(passWordMsg, gbc);
		gbc.gridx = 2; gbc.gridy = 1;
		panel.add(username, gbc);
		gbc.gridx = 2; gbc.gridy = 2;
		panel.add(password, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		panel.add(login, gbc);
		gbc.gridx = 2; gbc.gridy = 3;
		panel.add(register, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 2; gbc.gridy = 4;
		panel.add(status, gbc);
		
		frame = new JFrame("Login");
		frame.add(panel);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args) {	
		new LoginTime();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(login)) {
			boolean successful = false;
			
			String thisUsername = username.getText();
			String thisPassword = password.getText();
			
			if (usernames.contains(thisUsername) ) {
				int index = usernames.indexOf(thisUsername);
				successful = passwords.get(index).equals(thisPassword);
			}
			
			if (successful) {
				status.setForeground(Color.GREEN);
				status.setText("Successful Login"); 
			}
			else {
				status.setForeground(Color.RED);
				status.setText("Failed Login");
			}
		}
		
		else if (e.getSource().equals(register)) {
			RegisterWindow register = new RegisterWindow(this);
		}
		
	}		//end of actionPerformed()

}
