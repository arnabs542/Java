package guessTheCodeGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonsPane extends JPanel {

	private JButton startButton = new JButton("Start");
	private JButton addButton = new JButton("Add >>>");
	private JButton removeButton = new JButton("<<< Remove");
	private JButton checkButton = new JButton("Check");
	
	private ButtonClickedClass bcc = new ButtonClickedClass();
	
	private MainWindow parent;
	
	ButtonsPane(MainWindow mw) {
		super();
		parent = mw;

		setLayout(new GridLayout(4,1,4,4) );
		
		startButton.addActionListener(bcc);
		addButton.addActionListener(bcc);
		removeButton.addActionListener(bcc);
		checkButton.addActionListener(bcc);
		
		add(startButton);
		add(addButton);
		add(removeButton);
		add(checkButton);
		
		addButton.setEnabled(false);
		removeButton.setEnabled(false);
		checkButton.setEnabled(false);
	}
	
	private class ButtonClickedClass implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//The start Button is pressed
			if (e.getSource() == startButton) {
				parent.generateCode();
				addButton.setEnabled(true);
				removeButton.setEnabled(true);
				checkButton.setEnabled(true);
				startButton.setEnabled(false);
				parent.addTextToField("Game Start");
			}
			
			//The Add Button is pressed
			else if (e.getSource() == addButton) {
				Object[] selection = parent.leftListPane.getSelection();
				parent.leftListPane.removeElements(selection);
				parent.rightListPane.addElements(selection);
			}
			
			//The Remove Button is pressed
			else if (e.getSource() == removeButton) {
				Object[] selection = parent.rightListPane.getSelection();
				parent.leftListPane.addElements(selection);
				parent.rightListPane.removeElements(selection);
			}
			
			//The Check Button is pressed
			else if (e.getSource() == checkButton) {
				if (!parent.rightListPane.is4Digit())
					JOptionPane.showMessageDialog(parent, "The code must consist of exactly 4 digits only!", "Invalid code", JOptionPane.ERROR_MESSAGE);
				else {
					int correct = parent.getCorrectPlaces( parent.rightListPane.getAll() );
					int incorrect = parent.getIncorrectPlaces( parent.rightListPane.getAll() ) - correct;
					
					if (correct == 4) {
						JOptionPane.showMessageDialog(parent, "Congratulations! You've guessed it!", "Correct answer", JOptionPane.INFORMATION_MESSAGE);
						parent.addTextToField("-----------------------------------------------------------------------------------");
						parent.rightListPane.resetAll();
						parent.leftListPane.resetAll();
						startButton.setEnabled(true);
						addButton.setEnabled(false);
						removeButton.setEnabled(false);
						checkButton.setEnabled(false);
					}
					else
						parent.addTextToField( String.format("%d of the digits are correct and in right place; %d of the digits are correct but in incorrect place"
								, correct, incorrect) );
					
				}
			}
			
		}
		
	}		//end of ButtonClickedClass
	
	
}
