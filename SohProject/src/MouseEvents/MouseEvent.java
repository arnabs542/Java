
package MouseEvents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseEvent extends JFrame {

	private JPanel pane = new JPanel();
	private JLabel lab = new JLabel("Default");
	
	MouseEvent() {
		super("Mouse event");
		
		setLayout(new BorderLayout() );
		pane.setSize(new Dimension(300, 300));
		lab.setFont(new Font(Font.SERIF, Font.BOLD, 20) );
	
		pane.addMouseListener(new Handler() );
		pane.addMouseMotionListener(new Handler2() );
		
		add(pane, BorderLayout.CENTER);
		add(lab, BorderLayout.SOUTH);
		
	}
	
	private class Handler implements MouseListener {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			lab.setText( String.format("Mouse clicked at %d, %d", e.getX(), e.getY() ));
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {
			lab.setText("Mouse entered the game");
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {
			lab.setText("Mouse Quitter");
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			lab.setText( String.format("Mouse press at %d, %d", e.getX(), e.getY() ));
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			lab.setText( String.format("Mouse release at %d, %d" , e.getX(), e.getY() ));
		}
	}
	
	private class Handler2 implements MouseMotionListener {

		@Override
		public void mouseDragged(java.awt.event.MouseEvent arg0) {
			lab.setText("Where are u dragging to?");
		}

		@Override
		public void mouseMoved(java.awt.event.MouseEvent arg0) {
			lab.setText("Where are u moving to????");
			
		}
		
	}
	
	public static void main(String[]args) {
		MouseEvent me = new MouseEvent();
		me.setDefaultCloseOperation(EXIT_ON_CLOSE);
		me.setSize(500,500);
		me.setResizable(false);
		me.setLocationRelativeTo(null);
		me.setVisible(true);
	}
	
}
