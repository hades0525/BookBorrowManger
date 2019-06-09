package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FineSet extends JFrame {

	private JPanel contentPane;
	private JTextField setJTF;
	public static double fine;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FineSet frame = new FineSet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FineSet() {
		setTitle("罚金设置");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 115);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel setJP = new JPanel();
		contentPane.add(setJP, BorderLayout.NORTH);
		
		JLabel setJL1 = new JLabel("罚金设置：");
		setJP.add(setJL1);
		
		setJTF = new JTextField();
		setJP.add(setJTF);
		setJTF.setColumns(15);
		
		JLabel setJL2 = new JLabel("元/天");
		setJP.add(setJL2);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.CENTER);
		
		JButton setJB = new JButton("设置");
		setJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 fine = Double.parseDouble(setJTF.getText());
				 
			}
		});
		buttonJP.add(setJB);
		
		JButton exitJB = new JButton("退出");
		exitJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(exitJB);
		
	}

}
