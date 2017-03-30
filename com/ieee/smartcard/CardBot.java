package com.ieee.smartcard;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


class CardBot extends JFrame implements ActionListener {
	JButton submitButton;
	JPanel panel;
	JLabel lblCardInfo;
	final JTextField txtCardInfo;

	CardBot() {
		lblCardInfo = new JLabel();
		lblCardInfo.setText("Card information:");
		txtCardInfo = new JTextField(15);

		submitButton = new JButton("Submit");

		panel = new JPanel(new GridLayout(3, 1));
		panel.add(lblCardInfo);
		panel.add(txtCardInfo);
		panel.add(submitButton);
		add(panel, BorderLayout.CENTER);
		submitButton.addActionListener(this);
		setTitle("IEEE IoT Hackathon");
	}

	public void actionPerformed(ActionEvent ae) {
		String strCardInfo = txtCardInfo.getText();
		if (!strCardInfo.isEmpty()) {
			InformPage inform = new InformPage();
			inform.setVisible(true);
			CardSubmitter card = new CardSubmitter();
			try{
				card.submitCardInfo(strCardInfo);
			}catch(Exception e){
				JOptionPane.showMessageDialog(this,"Something wrong: " + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			JLabel label = new JLabel("Your submission is being processed. Thank you.");
			inform.getContentPane().add(label);
		} else {
			JOptionPane.showMessageDialog(this, "Please swipe your card.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
