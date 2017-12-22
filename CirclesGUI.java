import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CirclesGUI implements MouseListener{
	JFrame frame;
	CircleDrawPanel drawPanel;
	private Color color = Color.red;
	private int X; private int Y;
	private int diameter = 100;


	public static void main (String[] args){
		CirclesGUI gui = new CirclesGUI();
		gui.go();
	}

	//this method sets up the JFrame, including adding any components and listeners.
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new  CircleDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		drawPanel.addMouseListener(this);

		frame.setSize(600,600);
		frame.setVisible(true);
	}

	    public void mousePressed(MouseEvent e) {}
	    public void mouseReleased(MouseEvent e) {}
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e) {}
	    //all interface methods must be implemented

	    public void mouseClicked(MouseEvent e) {
	      X = e.getX();
	      Y = e.getY();
	      drawPanel.repaint();
		}


	//the placing of the circle depends on the X and Y instance variables. CircleDrawPanel can access the X and Y variables, as inner classes have unrestricted access to their containing classes instance variables.
	class CircleDrawPanel extends JPanel{
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			g2.setColor(color);
			g2.fillRect(X,Y,diameter,diameter);
		}
	}
}
