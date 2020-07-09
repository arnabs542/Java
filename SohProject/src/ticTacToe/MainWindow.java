package ticTacToe;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MainWindow extends JFrame{

	private GridPane gridPane = new GridPane(this);
	private MenuPane menuPane = new MenuPane(this);
	
	boolean isFreeMode = true;
	
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
	}

	//----------------------------------------------------------------------------------------------------
	
	//Constructor
	MainWindow() {
		super("Tic Tac Toe");
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout( new BoxLayout( mainPane, BoxLayout.Y_AXIS ) );
		
		mainPane.add(gridPane);
		mainPane.add(menuPane);
		
		add(mainPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,880);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}		//end of constructor
	
	//----------------------------------------------------------------------------------------------------------
	
	MenuPane getMenuPane() {
		return menuPane;
	}
	
	GridPane getGridPane() {
		return gridPane;
	}
	
	
}		//end of MainWindow
