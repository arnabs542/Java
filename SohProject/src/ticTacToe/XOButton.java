package ticTacToe;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class XOButton extends JButton{	

	private ImageIcon crossIcon = createImgIcon("cross.png");
	private ImageIcon roundIcon = createImgIcon("round.png");
	private byte state = 0;
	private GridPane parent;
	
	//------------------------------------------------------------------------------------------------------------------
	
	//Constructor
	XOButton(GridPane gridPane) {
		super();
		
		parent = gridPane;
		
		setPreferredSize(new Dimension(200,200));
//		addActionListener(this);
		this.addMouseListener(new Adapter());
	}
	//End of constructor

	//------------------------------------------------------------------------------------------------------------------
	
	//Changes the icon of this button with respect to the argument passed (0,1 or 2)
	void changeIcon(byte state) {
		this.state = state;
		switch (state) {
		case 0: setIcon(null);
				break;
		case 1: setIcon(crossIcon);
				break;
		case 2: setIcon(roundIcon);
				break;
		}
	}
	
	byte getState () {
		return state;
	}
	
	//Method to obtain the image file of cross and round O
	private ImageIcon createImgIcon(String img) {
		URL imgURL = this.getClass().getResource(img);
		if (imgURL == null) {
			System.err.println("File is not found");
			return null;
		}
		return new ImageIcon(imgURL);
	}
	
	//-------------------------------------------------------------------------------------------
	
	private class Adapter extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			//If the game is currently in Free Mode
			if (parent.parent.isFreeMode) {
				//if Right click registered
				if (e.isMetaDown() ) {
					if (state != 2) changeIcon((byte) 2);
					else changeIcon((byte)0);
				}
				//else is Left click registered
				else {
					if (state != 1) changeIcon((byte) 1);
					else changeIcon((byte)0);
				}
			}
			//Else the game is in CPU vs Player Mode
			else {
				changeIcon((byte) 1);
				setEnabled(false);
				parent.showIsWin( parent.checkWin() );
				
				if (parent.isFull()) {
					JOptionPane.showMessageDialog(parent.parent, "The game ends in a draw, Resetting...", "Draw", JOptionPane.INFORMATION_MESSAGE);
					parent.resetAllButton();
				}
				else {
					parent.cPUMoves();
					parent.showIsWin( parent.checkWin() );
					if (parent.isFull()) {
						JOptionPane.showMessageDialog(parent.parent, "The game ends in a draw, Resetting...", "Draw", JOptionPane.INFORMATION_MESSAGE);
						parent.resetAllButton();
					}
				}
			}
		}
	}
	
	
	
}		//end of XOButton class
