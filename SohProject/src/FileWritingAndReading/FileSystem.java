package FileWritingAndReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileSystem {

	private File file;
	private MainWindow parent;
	private FileWriter fw;
	
	//Constructor
	FileSystem(MainWindow mw) {
		parent = mw;
	}
	//End of constructor
	
	//------------------------------------------------------------------------------------------------------------------
	
	//setFile is called when user selects a file from the select file button
	void setFile (File path) {
		try {
			file = new File(path.getPath() );
		}
		catch (NullPointerException e) {
			parent.mainLabelPane.setStatus("No file selected");
			parent.infoArr.clear();
			return;
		}
		
		if (file.isDirectory() ) {
			int choice = JOptionPane.showConfirmDialog( parent, "You've selected a directory. A new save file will be made. Are you sure?",
																"Make new File?", JOptionPane.YES_NO_OPTION);
			//User click yes
			if (choice == JOptionPane.YES_OPTION) {
				String newFileName = "";
				//Loop to obtain a valid, new text file name
				while (newFileName.isEmpty() || !newFileName.endsWith(".txt") ) {
					newFileName = JOptionPane.showInputDialog("Enter the new text file's name. It should end with \".txt\"");
					
					if (!newFileName.endsWith(".txt") )
						JOptionPane.showMessageDialog(parent, "The name is not valid! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				file = new File(file.getPath() + String.format("\\%s", newFileName) );
				parent.mainLabelPane.setStatus( file.getPath() );
				parent.infoArr.clear();
				parent.fieldPane.clearField();
			}
			//User click no
			else if (choice == JOptionPane.NO_OPTION) {
				parent.mainLabelPane.setStatus("No file selected");
				parent.infoArr.clear();
				parent.fieldPane.clearField();
			}
				
		}
		else if (file.getPath().endsWith(".txt") ) {
			parent.mainLabelPane.setStatus( file.getPath() );
			if (file.length() != 0) {
				parent.setInfoArray( readExistingFile(file) );
				parent.fieldPane.setIndexField();
			}
		}
		else {
			JOptionPane.showMessageDialog(parent, "An invalid file is choosen. You can only choose a txt file or a directory", "Error", JOptionPane.ERROR_MESSAGE);
			parent.mainLabelPane.setStatus("No file selected");
			parent.infoArr.clear();
			parent.fieldPane.clearField();
		}
	}		//end of setFile()
	
	//------------------------------------------------------------------------------------------------------------------------------
	
	void addEntry (String name, String age, String classStr) {
		try {
			fw = new FileWriter(file, true);
			fw.append( String.format("%s | %s | %s | \n", name, age, classStr) );
			parent.fieldPane.clearField();
			fw.close();
			parent.setInfoArray( readExistingFile(file) );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parent , "There is a problem with the selected file!");
			parent.mainLabelPane.setStatus("No file selected");
			return;
		}
		
	}		//end of addEntry()
	
	//-----------------------------------------------------------------------------------------------------------------------------
	
	ArrayList<String[]> readExistingFile(File file) {
		ArrayList< String[] > line = new ArrayList< String[] >();
		
		try {
			Scanner scan = new Scanner(file);
			
			while (scan.hasNext() ) {
				String[] info = new String[3];
				for (int i = 0; i < 3; i ++ ) {
					String thisStr = scan.next();
					String next = scan.next();
					while (!next.equals("|") ) {
						thisStr = thisStr.concat(" " + next);
						next = scan.next();
					}
					info[i] = thisStr;
				}
				line.add(info);
			}
			scan.close();
			return line;
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(parent , "Something went wrong with the reading of the file...");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}		//end of FileSystem class
