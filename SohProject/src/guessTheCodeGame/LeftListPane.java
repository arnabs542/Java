package guessTheCodeGame;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LeftListPane extends JPanel {

	
	private JList<Integer> list = new JList<Integer>();
	private DefaultListModel<Integer> lm = new DefaultListModel<Integer>();
	private MainWindow parent;
	private Integer[] numbers = {1,2,3,4,5,6,7,8,9,0};
	
	LeftListPane(MainWindow mw) {
		super();
		parent = mw;
		
		for (int i = 0; i < 10; i ++)
			lm.addElement(numbers[i]);
		
		list.setModel(lm);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(7);
		list.setFixedCellWidth(100);
		list.setFixedCellHeight(25);
		
		add(new JScrollPane(list) );
	}
	
	protected Object[] getSelection () {
		return list.getSelectedValuesList().toArray();
	}
	
	protected void removeElements(Object[] remove) {
		for (Object o: remove)
			lm.removeElement(o);
	}
	
	protected void addElements(Object[] addEle) {
		for (Object o: addEle)
			lm.addElement((Integer) o);
	}
	
	protected void resetAll() {
		lm.removeAllElements();
		for (int i = 0; i < 10; i ++)
			lm.addElement(numbers[i]);
	}
}
