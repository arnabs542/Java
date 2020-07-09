package jCheckBox_jRadioButton_jComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class MainWindow extends JFrame {

	protected JTextArea textArea = new JTextArea("Text here");
	protected CheckPane checkPane = new CheckPane(this);
	protected RadioPane radioPane = new RadioPane(this);
	protected ComboPane comboPane = new ComboPane(this);
	
	MainWindow() {
		super("JCheckBox and JRadioButtons");
		
		textArea.setPreferredSize(new Dimension(500, 200));
		textArea.setFont( new Font(Font.SERIF, Font.PLAIN, 20));
		
		setLayout(new BorderLayout() );
		
		add(textArea, BorderLayout.NORTH);
		add(checkPane, BorderLayout.WEST);
		add(radioPane, BorderLayout.CENTER);
		add(comboPane, BorderLayout.EAST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	public static void main(String[]args) {
		MainWindow mw = new MainWindow();
	}
	
}
