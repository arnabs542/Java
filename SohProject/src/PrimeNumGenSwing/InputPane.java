package PrimeNumGenSwing;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class InputPane extends JPanel {
	
	private JLabel inputLabel = new JLabel("Input: ");
	private JTextField inputField = new JTextField(20);
	private JProgressBar bar = new JProgressBar();
	private JLabel foundLabel = new JLabel("Primes Found: ");
	private JTextField foundCount = new JTextField(10);
	private MainWindow parent;
	
	InputPane(MainWindow mw) {
		super();
		parent = mw;
		
		setLayout(new FlowLayout() );
		
		bar.setStringPainted(true);
		foundCount.setEditable(false);
		foundCount.setText("0");
		
		add(inputLabel);
		add(inputField);
		add(bar);
		add(foundLabel);
		add(foundCount);
		
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	protected String getInput() {
		return inputField.getText();
	}
	
	protected void setCounter(long n) {
		foundCount.setText( String.format("%d", n));
	}
	
	protected void updatePG (long limit, long current) {
		int percent = (int)(current * 100.0 / limit);
		bar.setValue(percent);
	}

}
