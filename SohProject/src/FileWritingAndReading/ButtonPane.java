package FileWritingAndReading;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPane extends JPanel {

	private MainWindow parent;
	private JButton selectButton = new JButton("<html>Select<br />Files...<html>");
	private JButton addButton = new JButton("Add");
	private JButton nextButton = new JButton("Next");
	private JButton prevButton = new JButton("Previous");
	private JButton clearButton = new JButton("Clear");
	
	final private Handler handler = new Handler();
	
	ButtonPane (MainWindow mw) {
		super();
		parent = mw;
		
		setLayout(new FlowLayout() );
		
		selectButton.setPreferredSize( new Dimension(100,70));
		addButton.setPreferredSize( new Dimension(100,70));
		nextButton.setPreferredSize( new Dimension(100,70));
		prevButton.setPreferredSize( new Dimension(100,70));
		clearButton.setPreferredSize( new Dimension(100,70));
		
		selectButton.addActionListener(handler);
		addButton.addActionListener(handler);
		nextButton.addActionListener(handler);
		prevButton.addActionListener(handler);
		clearButton.addActionListener(handler);
		
		add(selectButton);
		add(addButton);
		add(prevButton);
		add(nextButton);
		add(clearButton);
	}
	
	//Inner class as ActionHandler class
	private class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Select button is pressed
			if (e.getSource() == selectButton) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int selection = fc.showOpenDialog(parent);
				if (selection == JFileChooser.APPROVE_OPTION) {
					parent.fileSystem.setFile( fc.getSelectedFile() );
				}
			}
			
			//Add button is pressed (Time to write something into file!)
			if (e.getSource() == addButton) {
				if (parent.fieldPane.isAnyFieldEmpty() ) {
					JOptionPane.showMessageDialog(parent, "One or more field(s) is empty! Ensure you filled them up!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					parent.fileSystem.addEntry( parent.fieldPane.getNameField(), parent.fieldPane.getAgeField(), parent.fieldPane.getClassField() );
				}
			}
			
			//Next button is pressed
			if (e.getSource() == nextButton) {
				parent.index ++;
				parent.fieldPane.setIndexField();
			}
			
			//Previous Button is pressed
			if (e.getSource() == prevButton) {
				parent.index --;
				parent.fieldPane.setIndexField();
			}
			
			//Clear Button is pressed
			if (e.getSource() == clearButton) {
				parent.fieldPane.clearField();
			}
			
		}		//end of actionPerformed()
		
	}		//end of ActionHandler class
	
	
	
}		//end of ButtonPane class
