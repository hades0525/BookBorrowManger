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
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton yesJB = new JButton("确认");
		yesJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = String.valueOf(Login.getuserpwd());
				String pwd1 = originPWDJTF.getText().trim();
				String pwd2 =newPWDJTF.getText().trim();
				String pwd3 =againPWDJTF.getText().trim();
				if(! pwd1.equals(pwd))
			{
					JOptionPane.showMessageDialog(null, "原密码输入错误");
					originPWDJTF.setText(null);
			}else{
				if(pwd2.equals(pwd3))
				{
					pwd2 =String.valueOf(newPWDJTF.getText());
					pwd3 =String.valueOf(againPWDJTF.getText());
					int i= UserDao.updateUser(Login.getusername(),pwd3);
					if(i==1)
					{
						JOptionPane.showMessageDialog(null, "修改成功");
						updatePWD.this.setVisible(false);
					}
				}else
				{
					JOptionPane.showMessageDialog(null, "新密码与确认密码不一致，请重新输入！");
					newPWDJTF.setText(null);
					againPWDJTF.setText(null);
				}
			}
				
			}
		});
		buttonJP.add(yesJB);
		
		JButton cancelJB = new JButton("取消");
		cancelJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(cancelJB);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 2, 1));
		
		JLabel usernameJL = new JLabel("用户名：");
		usernameJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(usernameJL);
		
		usernameJTF = new JTextField();
		
		usernameJTF.setText(Login.getusername());
		usernameJTF.setEditable(false);
		
		panel.add(usernameJTF);
		usernameJTF.setColumns(10);
		
		JLabel originPWDJL = new JLabel("原密码：");
		originPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(originPWDJL);
		
		originPWDJTF = new JTextField();
		panel.add(originPWDJTF);
		originPWDJTF.setColumns(10);
		
		JLabel newPWDJL = new JLabel("新密码：");
		newPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(newPWDJL);
		
		newPWDJTF = new JTextField();
		panel.add(newPWDJTF);
		newPWDJTF.setColumns(10);
		
		JLabel againPWDJL = new JLabel("确认新密码：");
		againPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(againPWDJL);
		
		againPWDJTF = new JTextField();
		panel.add(againPWDJTF);
		againPWDJTF.setColumns(10);
	}

}
