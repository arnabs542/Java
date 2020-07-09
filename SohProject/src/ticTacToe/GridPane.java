package ticTacToe;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GridPane extends JPanel {

	private XOButton[][] buttons = new XOButton[3][3];
	protected MainWindow parent;
	
	//Constructor
	GridPane ( MainWindow mw ) {
		super();
		parent = mw;
		
		setLayout(new GridLayout(3,3));
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				buttons[i][j] = new XOButton(this);
				add( buttons[i][j] );
			}
		}
		
	}		//end of constructor
	
	//------------------------------------------------------------------------------------------------------------

	void resetAllButton() {
		for (XOButton b[]: buttons) 
			for (XOButton b2: b) {
				b2.changeIcon((byte) 0);
				b2.setEnabled(true);
			}
	}
	
	//A method to check if the TicTacToe had reached win condition:
	//Returns a 0 if the win condition is not met
	//Returns a 1 if the X wins
	//Returns a 2 if the O wins
	byte checkWin() {
		//Horizontal Check
		for (XOButton b[]: buttons) {
			if ( b[0].getState() == b[1].getState() && b[1].getState() == b[2].getState() && b[0].getState() != 0)
				return b[0].getState();
		}
		
		//Vertical Check
		for (int col = 0; col < 3; col ++) {
			if ( buttons[0][col].getState() == buttons[1][col].getState() && buttons[1][col].getState() == buttons[2][col].getState()
				 && buttons[0][col].getState() != 0)
				return buttons[0][col].getState();
		}
		
		//X Check
		if ( buttons[0][0].getState() == buttons[1][1].getState() && buttons[1][1].getState() == buttons[2][2].getState()
			&& buttons[0][0].getState() != 0)
			return buttons[0][0].getState();
		
		if ( buttons[2][0].getState() == buttons[1][1].getState() && buttons[1][1].getState() == buttons[0][2].getState()
			 && buttons[1][1].getState() != 0)
			return buttons[1][1].getState();
		
		//No Result
		return 0;
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	void cPUMoves () {
		int row, col;
		Random rand = new Random();
		
		do {
			row = rand.nextInt(3);
			col = rand.nextInt(3);
		} while (buttons[row][col].getState() != (byte)0 );
		
		buttons[row][col].changeIcon((byte)2);
		buttons[row][col].setEnabled(false);
	}
	
	boolean isFull() {
		boolean isFull = true;
		for (XOButton[] b: buttons)
			for (XOButton b2: b)
				if (b2.getState() == (byte)0)
					isFull = false;
		return isFull;
	}
	
	void showIsWin (byte winner) {
		switch (winner) {
			case 1: JOptionPane.showMessageDialog(parent, "Congratulations. You've win!!!");
					resetAllButton();
					break;
			case 2: JOptionPane.showMessageDialog(parent, "Oops! You've lost to a randomized, unintelligent CPU. Boo!!!");	
					resetAllButton();
					break;
		}
	}
}
