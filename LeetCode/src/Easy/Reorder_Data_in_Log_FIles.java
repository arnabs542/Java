package Easy;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Reorder_Data_in_Log_FIles {
	
	
	
	public String[] reorderLogFiles(String[] logs) {
		LinkedList<Integer> indexDig = new LinkedList<>();
		LinkedList<String> strLogs = new LinkedList<>();
		
		for (int i = 0; i < logs.length; i ++ ) {
			if (Character.isDigit(logs[i].charAt(logs[i].indexOf(' ') + 1) ) ) {
				indexDig.add(i);
			}
			else {
				strLogs.add(logs[i]);
			}
		}
		strLogs.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int comp = (o1.substring(o1.indexOf(' ') + 1).compareTo(o2.substring(o2.indexOf(' ') + 1) ) );
				return (comp == 0)? o1.compareTo(o2): comp;
			}
		});
		
		String[] sol = new String[logs.length];
		Iterator<String> it = strLogs.iterator();
		Iterator<Integer> itD = indexDig.iterator();
		
		
		for (int i = 0; i < sol.length; i ++ ) {
			if (it.hasNext() ) 
				sol[i] = it.next();
			else 
				sol[i] = logs[itD.next()];
		}
		return sol;
    }
}
