package guessTheCodeGame;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class RightListPane extends JPanel {

	private DefaultListModel<Integer> lm = new DefaultListModel<Integer>();
	private JList<Integer> list = new JList<Integer>();
	private MainWindow parent;
	
	RightListPane (MainWindow mw) {
		super();
		parent = mw;
		
		list.setModel(lm);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(7);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setFixedCellWidth(100);
		list.setFixedCellHeight(25);
		
		add(new JScrollPane(list) );
		
	}
	
	protected Object[] getSelection () {
		return list.getSelectedValuesList().toArray();
	}
	
	protected void addElements(Object[] addEle) {
		for (Object o: addEle)
			lm.addElement((Integer) o);
	}
	
	protected void removeElements(Object[] remove) {
		for (Object o: remove)
			lm.removeElement(o);
	}
	
	boolean is4Digit() {
		return lm.getSize() == 4;
	}
	
	protected Object[] getAll() {
		return lm.toArray();
	}
	
	protected void resetAll() {
		lm.removeAllElements();
	}
	
}
