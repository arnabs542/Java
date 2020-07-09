package PrimeNumGenSwing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DisplayPane extends JPanel {

	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private MainWindow parent;
	
	DisplayPane (MainWindow mw) {
		super();
		parent = mw;
		
		scroll.setPreferredSize(new Dimension(600, 400));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		add(scroll);
	}
	
	protected void addTestText(long n) {
		if (PrimeSys.isPrime(n) ) {
			textArea.append(n + " is a prime number");
			addDivider();
			parent.getInputPane().setCounter(1);
		}
		else {
			textArea.append(n + " is not a prime number");
			addDivider();
			parent.getInputPane().setCounter(0);
		}
	}
	
	protected void addListText(long n ) {
		textArea.append(n + "\t");
	}
	
	protected void addDivider() {
		textArea.append("\n--------------------------------------------------------------------------------------------------------------------\n");
	}
	
	
	protected void clearText() {
		textArea.setText("");
		parent.getInputPane().setCounter(0);
	}
}
