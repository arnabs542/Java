package PrimeNumGenSwing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {
	
	private MainWindow parent;
	private JButton test = new JButton("Test");
	private JButton list = new JButton("List");
	private JButton clear = new JButton("Clear");
	private Handler handler = new Handler();
	private boolean isRunning = false;
	private Thread thread;
	
	//Constructor
	ButtonPane(MainWindow mw) {
		super();
		parent = mw;
		
		setLayout(new GridLayout(10,1,50,0) );
		
		test.setPreferredSize( new Dimension(100,40 ) );
		
		test.addActionListener(handler);
		list.addActionListener(handler);
		clear.addActionListener(handler);
		
		add(test);
		add(list);
		add(clear);
		
	}
	
	
	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//If the test button is pressed
			if (e.getSource() == test) {
				if (parent.getInputPane().getInput().isEmpty() ) {
					JOptionPane.showMessageDialog(parent, "Please enter a number!");
				}
				else {
					try {
						parent.getDisplayPane().addTestText( Long.parseLong( parent.getInputPane().getInput() ) );
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(parent, "Invalid input! Must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
			//If the list button is pressed
			if (e.getSource() == list) {
				
				if (isRunning) {
					thread.stop();
					doFinishedJob();
				}
				else {
					long n = -1;
					try {
						n = Long.parseLong( parent.getInputPane().getInput() );
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(parent, "Invalid input! Must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if (n != -1) {
						thread = new Thread(new PrimeSys(n, parent) );
						thread.start();
						isRunning = true;
						list.setText("Stop");
					}
				}
			}
			
			//If the clear button is pressed
			if (e.getSource() == clear) {
				parent.getDisplayPane().clearText();
			}
			
		}
		
	}		//end of Handler class
	
	protected void doFinishedJob () {
		list.setText("List");
		isRunning = false;
		parent.getDisplayPane().addDivider();
		parent.getInputPane().updatePG(100, 0);
	}

}
