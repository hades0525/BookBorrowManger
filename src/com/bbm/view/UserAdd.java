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

public class UserAdd extends JFrame {

	private JPanel contentPane;
	private JTextField PWDJTF;
	private JTextField usernameJTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAdd frame = new UserAdd();
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
	public UserAdd() {
		setTitle("添加用户");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton addJB = new JButton("添加");
		addJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameJTF.getText().trim();
				String userpwd = PWDJTF.getText().trim();
				int i =UserDao.insertUser(username, userpwd);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}
			}
			
		});
		buttonJP.add(addJB);
		
		JButton cancelJB = new JButton("取消");
		cancelJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(cancelJB);
		
		JPanel addUserJP = new JPanel();
		contentPane.add(addUserJP, BorderLayout.CENTER);
		addUserJP.setLayout(new GridLayout(2, 2, 2, 1));
		
		JLabel usernameJL = new JLabel("用户名：");
		usernameJL.setHorizontalAlignment(SwingConstants.CENTER);
		addUserJP.add(usernameJL);
		
		usernameJTF = new JTextField();
		addUserJP.add(usernameJTF);
		usernameJTF.setColumns(10);
		
		JLabel pwdJL = new JLabel("密码：");
		pwdJL.setHorizontalAlignment(SwingConstants.CENTER);
		addUserJP.add(pwdJL);
		
		PWDJTF = new JTextField();
		addUserJP.add(PWDJTF);
		PWDJTF.setColumns(10);
	}

}
