package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.bbm.db.UserDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class updatePWD extends JFrame {

	private JPanel contentPane;
	private JTextField originPWDJTF;
	private JTextField newPWDJTF;
	private JTextField usernameJTF;
	private JTextField againPWDJTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updatePWD frame = new updatePWD();
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
	public updatePWD() {
		setTitle("�޸�����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton yesJB = new JButton("ȷ��");
		yesJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = String.valueOf(Login.getuserpwd());
				String pwd1 = originPWDJTF.getText().trim();
				String pwd2 =newPWDJTF.getText().trim();
				String pwd3 =againPWDJTF.getText().trim();
				if(! pwd1.equals(pwd))
			{
					JOptionPane.showMessageDialog(null, "ԭ�����������");
					originPWDJTF.setText(null);
			}else{
				if(pwd2.equals(pwd3))
				{
					pwd2 =String.valueOf(newPWDJTF.getText());
					pwd3 =String.valueOf(againPWDJTF.getText());
					int i= UserDao.updateUser(Login.getusername(),pwd3);
					if(i==1)
					{
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
						updatePWD.this.setVisible(false);
					}
				}else
				{
					JOptionPane.showMessageDialog(null, "��������ȷ�����벻һ�£����������룡");
					newPWDJTF.setText(null);
					againPWDJTF.setText(null);
				}
			}
				
			}
		});
		buttonJP.add(yesJB);
		
		JButton cancelJB = new JButton("ȡ��");
		cancelJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(cancelJB);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 2, 1));
		
		JLabel usernameJL = new JLabel("�û�����");
		usernameJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(usernameJL);
		
		usernameJTF = new JTextField();
		
		usernameJTF.setText(Login.getusername());
		usernameJTF.setEditable(false);
		
		panel.add(usernameJTF);
		usernameJTF.setColumns(10);
		
		JLabel originPWDJL = new JLabel("ԭ���룺");
		originPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(originPWDJL);
		
		originPWDJTF = new JTextField();
		panel.add(originPWDJTF);
		originPWDJTF.setColumns(10);
		
		JLabel newPWDJL = new JLabel("�����룺");
		newPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(newPWDJL);
		
		newPWDJTF = new JTextField();
		panel.add(newPWDJTF);
		newPWDJTF.setColumns(10);
		
		JLabel againPWDJL = new JLabel("ȷ�������룺");
		againPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(againPWDJL);
		
		againPWDJTF = new JTextField();
		panel.add(againPWDJTF);
		againPWDJTF.setColumns(10);
	}

}
