package ticTacToe;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
public class MenuPane extends JPanel implements ActionListener{

	private JButton mode = new JButton("<html>FREE<br />MODE<html>");
	private JButton reset = new JButton("RESET");
	private JSeparator jsept = new JSeparator( JSeparator.VERTICAL );
	private MainWindow parent;
	
	//--------------------------------------------------------------------------------------------------------------
	
	//Constructor
	MenuPane( MainWindow mw ) {
		super();
		parent = mw;
		
		mode.setPreferredSize( new Dimension(200,50) );
		reset.setPreferredSize( new Dimension(200,50) );
		jsept.setPreferredSize( new Dimension(1, 50));
		
		mode.addActionListener(this);
		reset.addActionListener(this);
		
		setLayout(new FlowLayout( FlowLayout.LEFT) );
		add(mode);
		add( Box.createHorizontalStrut(5) );
		add(jsept);
		add( Box.createHorizontalStrut(5) );
		add(reset);
		
	}		//end of constructor

	//-----------------------------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//If the reset button is pressed
		if (e.getSource() == reset) {
			parent.getGridPane().resetAllButton();
		}
		//If the game mode button is pressed
		else if (e.getSource() == mode) {
			if (!parent.isFreeMode) {
				parent.isFreeMode = true;
				parent.getGridPane().resetAllButton();
				mode.setText("<html>FREE<br />MODE<html>");
			}
			else {
				parent.isFreeMode = false;
				parent.getGridPane().resetAllButton();
				mode.setText("<html>Player <br /> vs<br />Comp");
			}
		}
	}
	
	
}		//end of class MenuPane
