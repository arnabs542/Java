package javaSwing;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class MainWindow extends JFrame {

	private JPanel mainPane = new JPanel();
	private IOPane ioPane = new IOPane();
	private ButtonPane buttonPane = new ButtonPane();
	
	public MainWindow() {
		super("Login to Jun Wei System");
		
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
		mainPane.add(new TitlePane());
		mainPane.add(ioPane);
		mainPane.add(buttonPane);
		
		setContentPane( mainPane);
		
	}
	
	
	//------------------------------------------------------------------------------------------------------
	
	//Main title Nested class
	class TitlePane extends JPanel {
		private JLabel title = new JLabel("Jun Wei System");
		private JLabel version = new JLabel("v 1.0");
		
		public TitlePane() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
			title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60) );
			version.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
			title.setAlignmentX(CENTER_ALIGNMENT);
			version.setAlignmentX(CENTER_ALIGNMENT);
			
			add(title);
			add(version);
		}
		
	}		//end of Main title Panel class
	
	//------------------------------------------------------------------------------------------------
	
	//Information input Panel nested class
	class IOPane extends JPanel{
		private JLabel userLabel = new JLabel("Username: ");
		private JLabel passLabel = new JLabel("Password: ");
		private JTextField userField = new JTextField(30);
		private JPasswordField passField = new JPasswordField(30);
		private GridBagConstraints gbc = new GridBagConstraints();
		
		public IOPane() {
			setLayout(new GridBagLayout() );
			
			Font fontstyle = new Font(Font.SERIF, Font.BOLD, 20);
			userLabel.setFont(fontstyle);
			passLabel.setFont(fontstyle);
			
			gbc.anchor = GridBagConstraints.LINE_END;
			add(userLabel, gbc);
			
			gbc.gridx = 0; gbc.gridy = 1;
			add(passLabel, gbc);
			
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 1; gbc.gridy = 0;
			add(userField ,gbc);
			
			gbc.gridx = 1; gbc.gridy = 1;
			add(passField, gbc);
		}
		
		public String getUserName() {
			return userField.getText();
		}
		
		public String getPassWord() {
			return passField.getText();
		}
		
		public void resetField() {
			userField.setText("");
			passField.setText("");
		}
		
		
	}		//End of information input panel class
	
	//----------------------------------------------------------------------------------------------------
	
	//Button panel nested class
	class ButtonPane extends JPanel implements ActionListener{
		private JButton register = new JButton("Register");
		private JButton login = new JButton("Login");
		private JButton reset = new JButton("Reset");
		private JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
		
		public ButtonPane () {
			Dimension d = new Dimension(150,50);
			register.setPreferredSize(d);
			login.setPreferredSize(d);
			reset.setPreferredSize(d);
			setLayout(new FlowLayout());
			setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			sep.setPreferredSize(new Dimension(1, 50));
			
			register.addActionListener(this);
			login.addActionListener(this);
			reset.addActionListener(this);
			
			add(register);
			add(sep);
			add(login);
			add(reset);
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			//If it's the login button pressed
			if (ae.getSource() == buttonPane.login) {
				if (Runner.matchUsernamePassword( ioPane.getUserName() , ioPane.getPassWord() )) {
					JOptionPane.showMessageDialog(this, "LOGIN SUCCESSFUL!!!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Incorrect username/ password. Please Try Again");
				}
			}
			else if (ae.getSource() == buttonPane.register) {
				Registration rg = new Registration();
				rg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				rg.setResizable(false);
				rg.setLocationRelativeTo(null);
				rg.pack();
				rg.setVisible(true);
			}
			else if (ae.getSource() == buttonPane.reset) {
				ioPane.resetField();
			}
		}
		
	}		//end of button panel class

	//--------------------------------------------------------------------------------------------
	
	
	//------------------------------------------------------------------------------------------------
	
	
}		//end of MainWindow.java


