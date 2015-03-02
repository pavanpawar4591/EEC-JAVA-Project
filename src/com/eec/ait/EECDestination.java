package com.eec.ait;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EECDestination extends JFrame {

	private static final long serialVersionUID = 1L;
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JPanel contentPane;
	public int i, j;
	private JButton jButton1;
	private JButton jButton2;

	public int fillen;
	public int fillen1;
	public int intfec[];
	public int intval[];
	public char chfec[];
	public byte filebyte[] = new byte[10000000];
	public char pakch1[][];
	public char filreord[][];
	public String filtfr;
	public String filfec[];
	String Dest;
	public int ch;
	public ServerSocket ss;
	public FileWriter fw;
	public BufferedWriter bw;
	Socket so;

	public EECDestination() {
		super();
		initializeComponent();

		this.setVisible(true);

		try {
			ss = new ServerSocket(4500);
			File file = new File("c://filename.txt");

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			while (true) {
				so = ss.accept();

				System.out
						.println("**************Packets Are Arriving From The Source*********");

				jTextArea1.setText("\n   Packets Recieving Started");

				DataInputStream dis1 = new DataInputStream(so.getInputStream());
				fillen = dis1.readInt();

				jTextArea1.append("\n   Recieved File Length = " + fillen);

				dis1.read(filebyte);

				jTextArea1.append("\n   Address of the Target = "
						+ filebyte.toString());
				// filtfr=new String[fillen];
				System.out.println("File Length : " + fillen);
				Dest = "";

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				System.out.println("data is comming");

				i = 0;

				while (fillen > 0) {
					filtfr = dis1.readUTF();

					jTextArea1.append("\n   Packet[" + i++ + "] = " + filtfr);
					System.out.println("Packet[" + i++ + "] = " + filtfr);
					// dis2.writeUTF(filtfr);
					// jTextArea1.append("\n   Packets Sending = "+filtfr);
					bw.write(filtfr);
					fillen--;
				}

			}
		} catch (Exception erlll) {
			System.out.println("Socket : " + erlll);
			// er.printStackTrace();
		} finally {

			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void initializeComponent() {
		jLabel1 = new JLabel();
		jLabel1.setToolTipText("Hi this is be project");
		jLabel1.setFont(new Font("Cambria", Font.BOLD, 16));

		jLabel2 = new JLabel();
		jLabel2.setFont(new Font("Cambria", Font.BOLD, 16));

		jButton1 = new JButton();

		jButton2 = new JButton();

		jTextArea1 = new JTextArea();

		jTextArea1.setFont(new Font("Cambria", Font.BOLD, 16));

		jScrollPane1 = new JScrollPane();
		contentPane = (JPanel) this.getContentPane();

		//
		// jLabel1
		//
		jLabel1.setText("Destination");
		//
		// jButton1
		//
		// jButton1.setBackground(new Color(102, 102, 255));
		jButton1.setForeground(new Color(0, 0, 102));
		jButton1.setText("Exit");

		jButton2.setForeground(new Color(0, 0, 102));
		jButton2.setText("Clear");

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}

		});
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}

			private void jButton2_actionPerformed(ActionEvent e) {
				jTextArea1.setText("");
			}

		});
		//
		// jLabel2
		//
		jLabel2.setText("Data From Sources");
		//
		// jTextArea1
		//
		//
		// jScrollPane1
		//
		jScrollPane1.setViewportView(jTextArea1);
		//
		// contentPane
		//
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(150, 150, 125));
		addComponent(contentPane, jLabel1, 160, 14, 112, 14);
		addComponent(contentPane, jButton1, 243, 330, 83, 32);
		addComponent(contentPane, jButton2, 123, 330, 83, 32);
		addComponent(contentPane, jLabel2, 130, 65, 197, 30);
		addComponent(contentPane, jScrollPane1, 49, 103, 279, 214);

		//
		// EECQueue
		//
		this.setTitle("EECDestination");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(387, 400));
		this.setResizable(false);

	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */

	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	// exit from program
	private void jButton1_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// ============================= Testing ================================//
	// = =//
	// = The following main method is just for testing this class you built.=//
	// = After testing,you may simply delete it. =//
	// ======================================================================//
	public static void main(String[] args) throws ClassNotFoundException {

		EECDestination showForm = new EECDestination();

	}

}
