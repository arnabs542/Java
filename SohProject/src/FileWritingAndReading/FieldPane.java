package FileWritingAndReading;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FieldPane extends JPanel {

	private MainWindow parent;
	private JLabel nameLabel = new JLabel("Name: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel classLabel = new JLabel("Class: ");
	private JTextField nameField = new JTextField(50);
	private JTextField ageField = new JTextField(50);
	private JTextField classField = new JTextField(50);
	private GridBagConstraints gbc = new GridBagConstraints();
	
	//Constructor
	FieldPane (MainWindow mw) {
		super();
		parent = mw;
		
		setLayout(new GridBagLayout() );
		
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 0; gbc.gridy = 0;
		add(nameLabel, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		add(ageLabel, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		add(classLabel, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		add(nameField, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		add(ageField, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		add(classField, gbc);
		
	}		//end of constructor
	
	//------------------------------------------------------------------------------------------------------------------------
	
	protected boolean isAnyFieldEmpty() {
		return getNameField().isEmpty() || getAgeField().isEmpty() || getClassField().isEmpty();
	}
	
	protected void clearField() {
		nameField.setText("");
		ageField.setText("");
		classField.setText("");
	}
	
	protected void setIndexField() {
		if (parent.infoArr == null) {
			JOptionPane.showMessageDialog(parent, "You haven't selected any files!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (parent.index >= parent.infoArr.size() ){
			JOptionPane.showMessageDialog(parent, "You've reached the end.");
			parent.index --;
		}
		else if (parent.index < 0){
			JOptionPane.showMessageDialog(parent, "You've reached the start");
			parent.index ++;
		}
		else {
			String[] info = parent.infoArr.get( parent.index );
			setNameField( info[0] );
			setAgeField( info[1] );
			setClassField( info[2] );
		}
		
	}

	protected String getNameField() {
		return nameField.getText();
	}
	
	protected String getAgeField() {
		return ageField.getText();
	}
	
	protected String getClassField() {
		return classField.getText();
	}
	
	protected void setNameField(String text) {
		nameField.setText(text);
	}
	
	protected void setAgeField(String text) {
		ageField.setText(text);
	}
	
	protected void setClassField(String text) {
		classField.setText(text);
	}
	
	
}		//end of FieldPane class
