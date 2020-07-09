package PrimeNumGenSwing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private ButtonPane buttonPane = new ButtonPane(this);
	private DisplayPane displayPane = new DisplayPane(this);
	private InputPane inputPane = new InputPane(this);
	
	//Constructor
	MainWindow() {
		super("Prime Number Generator");
		
		setLayout(new BorderLayout() );
		
		add(buttonPane, BorderLayout.WEST);
		add(displayPane, BorderLayout.CENTER);
		add(inputPane, BorderLayout.SOUTH);
	}
	
	
	
	//--------------------------------------------------------------------------------------------------
	
	protected ButtonPane getButtonPane() {
		return buttonPane;
	}
	
	protected DisplayPane getDisplayPane() {
		return displayPane;
	}
	
	protected InputPane getInputPane() {
		return inputPane;
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	//main Method
	public static void main(String[]args) {
		MainWindow mw = new MainWindow();
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.pack();
		mw.setLocationRelativeTo(null);
		mw.setVisible(true);
	}
	
}
