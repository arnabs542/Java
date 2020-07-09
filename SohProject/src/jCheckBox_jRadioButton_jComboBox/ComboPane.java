package jCheckBox_jRadioButton_jComboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboPane extends JPanel implements ActionListener{

	private MainWindow parent;
	private JComboBox<Integer> comboBox;
	private Integer[] sizeArray = new Integer[100];
	
	ComboPane(MainWindow mw) {
		super();
		parent = mw;
		
		for(int i = 0; i < 100; i ++)
			sizeArray[i] = i+1;
		comboBox = new JComboBox<Integer>(sizeArray);
		comboBox.setPreferredSize(new Dimension(100,50) );
		comboBox.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		
		comboBox.addActionListener(this);
		comboBox.setSelectedItem(20);
		
		add(comboBox);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Font beforeFont = parent.textArea.getFont();
		
		parent.textArea.setFont( new Font( beforeFont.getFontName(), beforeFont.getStyle(), (int) comboBox.getSelectedItem()));
	}
	
	
	
}
