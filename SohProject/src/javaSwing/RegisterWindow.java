package javaSwing;

import java.awt.Dialog.ModalExclusionType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class RegisterWindow implements ActionListener{

	JDialog frame;
	JPanel panel;
	JLabel newUserMsg, newPWMsg, confirmPWMsg;
	JTextField newUserName;
	JPasswordField newPassword, confirmPassword;
	JButton register;
	GridBagConstraints gbc;
	LoginTime parent;
	
	public RegisterWindow(LoginTime parent) {
		this.parent = parent;
		
		newUserMsg = new JLabel("Username:");
		newPWMsg = new JLabel("New Password:");
		confirmPWMsg = new JLabel("Confirm Password:");

		newUserName = new JTextField(30);
		newPassword = new JPasswordField(30);
		confirmPassword = new JPasswordField(30);
		
		register = new JButton("Register");
		register.addActionListener(this);
		
		panel = new JPanel();
		panel.setLayout( new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createEmptyBorder());
		
		gbc.gridx = 1; gbc.gridy = 1;
		panel.add(newUserMsg, gbc);
		gbc.gridx = 2; gbc.gridy = 1;
		panel.add(newUserName, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		panel.add(newPWMsg, gbc);
		gbc.gridx = 2; gbc.gridy = 2;
		panel.add(newPassword, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		panel.add(confirmPWMsg, gbc);
		gbc.gridx = 2; gbc.gridy = 3;
		panel.add(confirmPassword, gbc);
		gbc.gridx = 1; gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(register, gbc);	
		
		frame = new JDialog(parent.frame, "Register New User", true);
		frame.setContentPane(panel);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(1);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		
	}		//end of main()

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String userN = newUserName.getText();
		String passW = newPassword.getText();
		String confirmPW = confirmPassword.getText();
		
		if (parent.usernames.contains(userN)) {
			System.out.println("Repeat!!!");
		}
		else if (!passW.equals(confirmPW)) {
			System.out.println("Password error");
		}
		else {
			parent.usernames.add(userN);
			parent.passwords.add(passW);
			System.out.println("Operation Successful");
			frame.dispose();
		}
	}

}		//end of class
