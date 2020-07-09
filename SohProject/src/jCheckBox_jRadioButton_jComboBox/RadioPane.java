package jCheckBox_jRadioButton_jComboBox;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioPane extends JPanel {

	private JRadioButton plain = new JRadioButton("Plain", true);
	private JRadioButton bold = new JRadioButton("Bold", false);
	private JRadioButton italic = new JRadioButton("Italic", false);
	private JRadioButton boldItalic = new JRadioButton("Bold and Italic", false);
	private ButtonGroup bg = new ButtonGroup();
	private MainWindow parent;
	
	public RadioPane(MainWindow mw) {
		super();
		parent = mw;
		
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		
		bg.add(plain);
		bg.add(bold);
		bg.add(italic);
		bg.add(boldItalic);
		
		plain.addItemListener(new RadioButtonClicked(0 ) );
		bold.addItemListener( new RadioButtonClicked(1 ) );
		italic.addItemListener( new RadioButtonClicked(2 ) );
		boldItalic.addItemListener( new RadioButtonClicked(3 ) );
		
		add(plain);
		add(bold);
		add(italic);
		add(boldItalic);
	
	}
	
	void changeState(int choice) {
		switch (choice) {
			case 0: plain.setSelected(true);
					break;
			case 1: bold.setSelected(true);
					break;
			case 2: italic.setSelected(true);
					break;
			case 3: boldItalic.setSelected(true);
					break;
		}
	}

	private class RadioButtonClicked implements ItemListener {
		
		private int choice;
		
		RadioButtonClicked(int choice) {
			this.choice = choice;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			parent.checkPane.changeState(choice);
			
		}
	}
	
	
}
