package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.bbm.db.UserDao;
import com.bbm.model.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame
{

	private JPanel contentPane;
	private static JTextField usernameJTF;
	private static JPasswordField pwdJPF;
	private static Users user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		setResizable(false);
		setTitle("图书借阅系统登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel textJP = new JPanel();
		contentPane.add(textJP, BorderLayout.NORTH);

		JLabel textJL = new JLabel("图书借阅系统");
		textJL.setFont(new Font("黑体", Font.BOLD, 32));
		textJP.add(textJL);

		JPanel loginJP = new JPanel();
		contentPane.add(loginJP, BorderLayout.CENTER);
		loginJP.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel usernameJL = new JLabel("用户名：");
		usernameJL.setFont(new Font("宋体", Font.BOLD, 12));
		usernameJL.setHorizontalAlignment(SwingConstants.CENTER);
		loginJP.add(usernameJL);

		usernameJTF = new JTextField();
		loginJP.add(usernameJTF);
		usernameJTF.setColumns(15);

		JLabel passwordJL = new JLabel("密码：");
		passwordJL.setFont(new Font("宋体", Font.BOLD, 12));
		passwordJL.setHorizontalAlignment(SwingConstants.CENTER);
		loginJP.add(passwordJL);

		pwdJPF = new JPasswordField();
		pwdJPF.setColumns(15);
		loginJP.add(pwdJPF);

		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);

		JButton loginJB = new JButton("登录");
		loginJB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!"".equals(usernameJTF.getText())&&!"".equals(new String(pwdJPF.getPassword())))
				{
					user = UserDao.check(usernameJTF.getText(), new String(pwdJPF.getPassword()));
					if(user.getName()!=null)
					{
						try
						{
							Library frame = new Library();
							frame.setVisible(true);
							Login.this.setVisible(false);
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "您输入的用户名或密码错误，不能登录！");
						usernameJTF.setText("");
						pwdJPF.setText("");
					}
				}else{
						JOptionPane.showMessageDialog(null, "请输入用户名和密码！");
					}
										
			}
			
		});
		loginJB.setFont(new Font("宋体", Font.BOLD, 12));
		buttonJP.add(loginJB);

		JButton resetJB = new JButton("重置");
		resetJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameJTF.setText("");
				pwdJPF.setText("");
			}
		});
		resetJB.setFont(new Font("宋体", Font.BOLD, 12));
		buttonJP.add(resetJB);
	}
	public static String getusername()
	{
		 return usernameJTF.getText().trim();
	}
	public static char[] getuserpwd()
	{
		 return pwdJPF.getPassword();
	}

}
