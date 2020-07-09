package MouseEvents;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Imagine having to implement all of the 5 or even more mouse events in the MouseListener interface although you don't need all!
 * Introducing adapter classes which let you only overwrite certain needed mouse methods!
 */

public class AdapterClass extends JFrame {

	private String status;
	private JLabel label = new JLabel("The default label");

	AdapterClass() {
		super("Adapter class tester Program");
	
		setLayout(new BorderLayout());
		this.addMouseListener(new mouseHandler());
	
		add(label, BorderLayout.SOUTH);
	
	}

	// THe private handler class to handle the mouse events but with inheritance of
	// adapter class
	private class mouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// The getClickCount() will keep increase if you click at same spot;
			// If you moved the mouse, the mouseMoved event fires and basically the mouse
			// event is reset
			status = String.format("You've clicked %d", e.getClickCount());

			// Scroll wheel click
			if (e.isAltDown()) {
				status = status.concat(" times using your Scroll Wheel");
			}
			// Right mouse click
			else if (e.isMetaDown()) {
				status = status.concat(" times using your right mouse button");
			}
			// If none of the above, is Left mouse click
			else {
				status = status.concat(" times using your left mouse button");
			}

			// With control key hold?
			if (e.isControlDown()) {
				status = status.concat(" with control key");
			}
			// With shift key hold?
			else if (e.isShiftDown()) {
				status = status.concat(" with shift key");
			}

			label.setText(status);
		}
	}

	public static void main(String[] args) {
		AdapterClass ac = new AdapterClass();
		ac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ac.setSize(400, 400);
		ac.setLocationRelativeTo(null);
		ac.setVisible(true);
	}
}
