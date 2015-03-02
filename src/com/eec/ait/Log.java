package com.eec.ait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Log extends JFrame {

	public static void main(String[] args) {
		Log frameTabel = new Log();
	}

	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	JLabel lab1 = new JLabel("Username");
	JLabel lab2 = new JLabel("Password");

	Log() {
		super("Login Autentification");
		setSize(300, 200);
		setLocation(500, 280);
		panel.setLayout(null);

		txuser.setBounds(90, 30, 150, 20);
		pass.setBounds(90, 65, 150, 20);
		blogin.setBounds(110, 100, 80, 20);
		lab1.setBounds(10, 30, 150, 20);
		lab2.setBounds(10, 65, 150, 20);
		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(lab1);
		panel.add(lab2);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = pass.getText();
				if (puname.equals("test") && ppaswd.equals("12345")) {
					EECSource regFace = new EECSource();
					regFace.setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null,
							"Wrong Password / Username");
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}

			}
		});
	}
}
