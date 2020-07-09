package FileWritingAndReading;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

public class MainWindow extends JFrame {

	protected MainLabel mainLabelPane = new MainLabel();
	protected FieldPane fieldPane = new FieldPane(this);
	protected ButtonPane buttonPane = new ButtonPane(this);
	protected FileSystem fileSystem = new FileSystem(this);

	private JLabel status = new JLabel("No file selected");
	
	protected ArrayList<String[]> infoArr = new ArrayList<String[]>();
	protected int index = 0;
	
	//Constructor
	MainWindow() {
		super("File Reading and File Writing");
		
		setLayout(new BorderLayout() );
		
		add(mainLabelPane, BorderLayout.NORTH);
		add(fieldPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.SOUTH); 
		
	}
	//end of constructor
	
	//Set the infoArray which stores all the information stored in the file
	void setInfoArray ( ArrayList<String[]> arr ) {
		infoArr = arr;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	//An inner class for JPanel (functions to display title and JLabel indicating directory path)
	class MainLabel extends JPanel {
		private JLabel bigTitle = new JLabel("File Manipulation", JLabel.CENTER);
		private JLabel version = new JLabel("v 1.0", JLabel.CENTER);

		private JLabel status = new JLabel("No file selected", JLabel.CENTER);
		
		MainLabel () {
			super();
			setLayout(new BorderLayout() );
			
			bigTitle.setFont( new Font(Font.SERIF, Font.BOLD , 50));
			version.setFont( new Font(Font.SERIF, Font.ITALIC, 20));
			status.setForeground(Color.GRAY);
			
			add(bigTitle, BorderLayout.NORTH);
			add(version, BorderLayout.CENTER);
			add(status, BorderLayout.SOUTH);
		}
		
		//To set the label text for directory of the file
		void setStatus (String text) {
			status.setText(text);
		}
		
	}
	//end of JPanel class for MainLabel
	
	//-----------------------------------------------------------------------------------------------------------------
	
	//main() method
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		mw.setSize(700, 500);
		mw.setLocationRelativeTo(null);
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.setVisible(true);
		mw.setResizable(false);
		
	}
	
	

}
