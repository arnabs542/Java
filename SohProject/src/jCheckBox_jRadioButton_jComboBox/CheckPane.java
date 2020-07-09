package jCheckBox_jRadioButton_jComboBox;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckPane extends JPanel {

	private JCheckBox bold = new JCheckBox("Bold");
	private JCheckBox italic = new JCheckBox("Italic");
	private MainWindow parent;
	private CheckBoxClick cbc = new CheckBoxClick();
	
	CheckPane (MainWindow mw) {
		super();
		parent = mw;
		
		setLayout(new BoxLayout( this, BoxLayout.Y_AXIS) );
		
		bold.addItemListener(cbc);
		italic.addItemListener(cbc);
		
		add(bold);
		add(italic);
	
	}
	
	private class CheckBoxClick implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent ie) {
			
			int size = parent.textArea.getFont().getSize();
			
			if (bold.isSelected() && italic.isSelected() ) {
				parent.textArea.setFont( new Font(Font.SERIF, Font.BOLD + Font.ITALIC, size) );
				parent.radioPane.changeState(3);
			}
			else if (bold.isSelected() ) {
				parent.textArea.setFont( new Font(Font.SERIF, Font.BOLD, size) );
				parent.radioPane.changeState(1);
			}
			else if (italic.isSelected() ) {
				parent.textArea.setFont( new Font(Font.SERIF, Font.ITALIC, size) );
				parent.radioPane.changeState(2);
			}
			else {
				parent.textArea.setFont( new Font(Font.SERIF, Font.PLAIN, size) );
				parent.radioPane.changeState(0);
			}
		}
	}

	void changeState(int choice) {
		switch (choice) {
			case 0: bold.setSelected(false);
					italic.setSelected(false);
					break;
			case 1: bold.setSelected(true);
					italic.setSelected(false);
					break;
			case 2: bold.setSelected(false);
					italic.setSelected(true);
					break;
			case 3: bold.setSelected(true);
					italic.setSelected(true);
					break;
		}
	}
		
	
}
