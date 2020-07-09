package guessTheCodeGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.util.Random;

public class MainWindow extends JFrame {

	protected LeftListPane leftListPane = new LeftListPane(this);
	protected ButtonsPane buttonsPane = new ButtonsPane(this);
	protected RightListPane rightListPane = new RightListPane(this);
	
	protected JTextArea textArea = new JTextArea();
	
	private Integer[] code = new Integer[4];
	
	MainWindow() {
		super("Guess The Code Game");
		
		setLayout(new BorderLayout() );
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,350);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension (100,100));
		
		add(leftListPane, BorderLayout.WEST);
		add(buttonsPane, BorderLayout.CENTER);
		add(rightListPane, BorderLayout.EAST);
		add( scroll, BorderLayout.SOUTH);
	}
	
	protected void generateCode () {
		Random randGen = new Random();
		boolean[] isTaken = new boolean[10];
		int rand = randGen.nextInt(10);
		
		for (int i = 0; i < 4; i ++ ) {
			while( isTaken[rand] )
				rand = randGen.nextInt(10);
			
			code[i] = rand;
			System.out.println(rand);
			isTaken[rand] = true;
		}
	}
	
	protected Integer[] getCode() {
		return code;
	}
	
	protected int getIncorrectPlaces(Object[] arr) {
		boolean[] exists = new boolean[10];
		int counter = 0;
		for (int e: code)
			exists[e] = true;
		
		for (Object o: arr) {
			if (exists[(int) o])
				counter ++;
		}
		return counter;
	}
	
	protected int getCorrectPlaces(Object[] arr) {
		int counter = 0;
		for (int i = 0; i < 4; i ++ ) {
			if ( (int)arr[i] == code[i] )
				counter ++;
		}
		return counter;
	}
	
	protected void addTextToField(String str) {
		textArea.setText( textArea.getText() + str + "\n");
	}
	
	
	public static void main(String[]args) {
		MainWindow mw = new MainWindow();
	}
	
}
