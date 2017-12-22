/*
 * Name: Shamoun Syed
 * Student Number: 140313701
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CirclesGUI {
	JFrame frame;
	CircleDrawPanel drawPanel;
	private Color color = Color.red;
	private int X; private int Y;
	private int diameter = 100;
    private Random generator;


	public static void main (String[] args){
		CirclesGUI gui = new CirclesGUI();
		gui.go();
	}

	//this method sets up the JFrame, including adding any components and listeners.
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generator = new Random();

		drawPanel = new  CircleDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		drawPanel.addMouseListener(new CircleListener());

        JButton resizeButton = new JButton("click to resize the circle");
        frame.getContentPane().add(BorderLayout.NORTH, resizeButton);
        resizeButton.addActionListener(new DiameterListener());

        JButton colorButton = new JButton("click to change the color of the circle");
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        colorButton.addActionListener(new RandomColourListener());

        X = 300; // Set initial position to be near the center of the screen,
        Y = 300; // for immediate visibility upon running the program
		frame.setSize(600,600);
		frame.setVisible(true);
	}


	//the placing of the circle depends on the X and Y instance variables. CircleDrawPanel can access the X and Y variables, as inner classes have unrestricted access to their containing classes instance variables.
	class CircleDrawPanel extends JPanel{
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			g2.setColor(color);
			g2.fillOval(X - diameter/2, Y - diameter/2, diameter, diameter);
		}
	}

    class CircleListener implements MouseListener {
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
    }

    class DiameterListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            diameter = generator.nextInt(600);
            drawPanel.repaint();
        }
    }

    class RandomColourListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Color c = new Color(
                    generator.nextInt(255),
                    generator.nextInt(255),
                    generator.nextInt(255));

            color = c;
            drawPanel.repaint();
        }
    }
}
