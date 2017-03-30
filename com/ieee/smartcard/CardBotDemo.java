package com.ieee.smartcard;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CardBotDemo {
    
	public static void main(String arg[]) {
		try {
			CardBot frame = new CardBot();

	        Dimension windowSize = frame.getSize();
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        Point centerPoint = ge.getCenterPoint();

	        int dx = centerPoint.x - windowSize.width / 2;
	        int dy = centerPoint.y - windowSize.height / 2;	      
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(dx, dy);
			frame.setSize(500, 200);
			frame.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}