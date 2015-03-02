package com.eec.ait;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EECSource extends JFrame {

	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel imageLabel;
	private JTextField jTextField1;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;

	private JButton jButton1;
	private JButton jButton3;

	private JPanel contentPane;
	public String Dest = "";

	public byte filebyte[] = new byte[10000000];
	public int filint[];
	public String filstr[];
	public String filmer[];
	public String filtfr[];
	public String filsep[][];
	public String filorg[];

	public char pakch[][];
	public char shufch[][];
	String EECLeveltmp = "";

	public int ch;
	public int flen;
	Socket st;
	int i, j, l;
	double s;
	int plen = 16;
	String EEC = " ";
	int z, n;

	public EECSource() {
		super();
		initializeComponent();
		this.setVisible(true);
	}

	private void initializeComponent() {

		jLabel2 = new JLabel();
		jLabel2.setFont(new Font("Cambria", Font.BOLD, 16));
		jLabel3 = new JLabel();
		jLabel3.setFont(new Font("Cambria", Font.BOLD, 16));
		jLabel4 = new JLabel();
		jLabel4.setFont(new Font("Cambria", Font.BOLD, 16));
		jTextField1 = new JTextField();
		jTextField1.setFont(new Font("Cambria", Font.BOLD, 16));
		jTextArea1 = new JTextArea();
		jTextArea1.setFont(new Font("Cambria", Font.BOLD, 16));
		jScrollPane1 = new JScrollPane();
		imageLabel = new JLabel();

		jButton1 = new JButton();
		jButton3 = new JButton();

		contentPane = (JPanel) this.getContentPane();

		jLabel2.setForeground(new Color(0, 0, 102));
		jLabel2.setText("Status Information");

		jLabel3.setForeground(new Color(0, 0, 102));
		jLabel3.setText("Open the Source File : ");

		jLabel4.setForeground(new Color(0, 0, 102));
		jLabel4.setText("");

		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextField1_actionPerformed(e);
			}

		});

		jScrollPane1.setViewportView(jTextArea1);

		jButton1.setForeground(new Color(0, 0, 102));
		jButton1.setText("Browse");
		jButton1.setHorizontalTextPosition(JButton.CENTER);
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e); // browse file
			}

		});

		jButton3.setForeground(new Color(0, 0, 102));
		jButton3.setText("Send Packets");
		jButton3.setHorizontalTextPosition(JButton.CENTER);
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3_actionPerformed(e);
			}

		});

		contentPane.setLayout(null);
		contentPane.setBackground(new Color(150, 150, 200));
		contentPane.setForeground(new Color(51, 51, 51));

		addComponent(contentPane, jLabel2, 27, 105, 184, 18);
		addComponent(contentPane, jLabel3, 27, 38, 240, 30);
		addComponent(contentPane, jLabel4, 27, 410, 200, 20);
		addComponent(contentPane, jTextField1, 27, 71, 600, 30);
		addComponent(contentPane, jScrollPane1, 27, 125, 600, 250);

		addComponent(contentPane, jButton1, 670, 70, 83, 32);

		addComponent(contentPane, jButton3, 506, 400, 120, 30);

		this.setTitle("Source Server ");
		this.setLocation(new Point(0, 300));
		this.setSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	private void jTextField1_actionPerformed(ActionEvent e) {
		System.out.println("\nBrowse For File");

	}

	FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);

	private void jButton1_actionPerformed(ActionEvent e) {

		System.out
				.println("\n*********************BROWSING FILE********************");

		try {

			fd.show();
			FileInputStream fin = new FileInputStream(fd.getDirectory()
					+ fd.getFile());

			jTextField1.setText(fd.getDirectory() + fd.getFile());

			File f = new File(fd.getDirectory() + fd.getFile());

			fin.read(filebyte);

			flen = (int) f.length();

			System.out.println("lenth" + flen);
			if (flen > 800000000) {
				String st = "CHOOSEN INVALID FILE";
				JOptionPane.showMessageDialog(null, st);
			}
			System.out
					.println("\n*********************File Loaded********************");
			jTextArea1.setText("\n   File Loaded");

			filint = new int[flen + 1000];

			filstr = new String[flen];
			filmer = new String[flen];
			filtfr = new String[flen];
			filsep = new String[flen][100];
			filorg = new String[flen];
			pakch = new char[flen + 25][100];
			shufch = new char[flen + 25][100];

			// jTextArea1.append("\n\n   File Loaded");

		} catch (Exception er) {
			er.printStackTrace();
			System.out.println(er);
		}
	}

	private void jButton3_actionPerformed(ActionEvent e) {

		jTextArea1.append("\n\n   Sending Packets to Destination");
		System.out.println("\nSending Packets Started");
		System.out
				.println("\n The packets have been sending from source to destination");

		try {
			Dest = "localhost";
			// Dest = "169.254.137.11";

			System.out.println("The Address of Destination : " + Dest);
			st = new Socket(Dest, 4500);
			DataOutputStream dos = new DataOutputStream(st.getOutputStream());

			dos.writeInt(flen);
			// dos.writeUTF("Dest");

			System.out
					.println("\n************************EEC Encoding Started****************");

			for (i = 0; i < flen; i++) {
				filint[i] = (int) filebyte[i];
				System.out.println("Int Value : [" + i + "] = " + filint[i]);
				jTextArea1.append("\nInt Value : [" + i + "] = " + filint[i]);

				filstr[i] = Integer.toBinaryString(filint[i]);

			}

			for (i = 0; i <= flen; i++) {

				System.out.println(filstr[i]);
				for (i = 0; i < flen; i++) {
					filtfr[i] = filstr[i];
					System.out.println("Packet [" + i + "] = " + filtfr[i]);
				}

			}

			for (i = 0; i < flen; i++) {
				if (filtfr[i] != null) {

					dos.writeUTF(filtfr[i]);
				}
			}

		} catch (Exception er) {
			er.printStackTrace();
			// System.out.println(er.getmessage();
		}

		jTextArea1.append("\n\n   Packets Sent to Destination");
		System.out.println("\nSending Packets Completed");

	}

	public static void main(String[] args) {
		new EECSource();
	}

}
