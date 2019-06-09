package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Library extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library frame = new Library();
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
	public Library() {
		setTitle("图书借阅系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar jmenuBar = new JMenuBar();
		setJMenuBar(jmenuBar);
		
		JMenu readerMangerJMenu = new JMenu("读者信息管理");
		readerMangerJMenu.setHorizontalAlignment(SwingConstants.CENTER);
		jmenuBar.add(readerMangerJMenu);
		
		JMenuItem readerAddJMI = new JMenuItem("读者信息添加");
		readerAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderAdd frame = new ReaderAdd();
				frame.setVisible(true);
			}
		});
		readerAddJMI.setSelected(true);
		readerMangerJMenu.add(readerAddJMI);
		
		JMenuItem readerSelUpdJMI = new JMenuItem("读者信息查询与修改");
		readerSelUpdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderSelectandUpdate frame = new ReaderSelectandUpdate();
				frame.setVisible(true);
			}
		});
		readerSelUpdJMI.setSelected(true);
		readerMangerJMenu.add(readerSelUpdJMI);
		
		JMenu bookMangerJMenu = new JMenu("图书信息管理");
		jmenuBar.add(bookMangerJMenu);
		
		JMenuItem bookAddJMI = new JMenuItem("图书信息添加");
		bookAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAdd frame = new BookAdd();
				frame.setVisible(true);
			}
		});
		bookMangerJMenu.add(bookAddJMI);
		
		JMenuItem bookSelUpdJMI = new JMenuItem("图书信息查询与修改");
		bookSelUpdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSelectUpdate frame =  new BookSelectUpdate();
				frame.setVisible(true);
			}
		});
		bookMangerJMenu.add(bookSelUpdJMI);
		
		JMenu bookBorrowJMenu = new JMenu("图书借阅管理");
		jmenuBar.add(bookBorrowJMenu);
		
		JMenuItem bookBorrowJMI = new JMenuItem("图书借阅");
		bookBorrowJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBorrow frame = new BookBorrow();
				frame.setVisible(true);
			}
		});
		bookBorrowJMenu.add(bookBorrowJMI);
		
		JMenuItem bookReturnJMI = new JMenuItem("图书归还");
		bookReturnJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookReturn frame = new BookReturn();
				frame.setVisible(true);
			}
		});
		bookBorrowJMenu.add(bookReturnJMI);
		
		JMenu baseInfoJMenu = new JMenu("基础信息维护");
		jmenuBar.add(baseInfoJMenu);
		
		JMenuItem bookTypeJMI = new JMenuItem("图书类别设置");
		bookTypeJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManager frame =  new BookTypeManager();
				frame.setVisible(true);
			}
		});
		baseInfoJMenu.add(bookTypeJMI);
		
		JMenuItem readerTypeJMI = new JMenuItem("读者类别设置");
		readerTypeJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderTypeManage frame = new ReaderTypeManage();
				frame.setVisible(true);
			}
		});
		baseInfoJMenu.add(readerTypeJMI);
		
		JMenuItem fineSetJMI = new JMenuItem("罚金设置");
		fineSetJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FineSet frame =  new FineSet();
				frame.setVisible(true);

			}
		});
		baseInfoJMenu.add(fineSetJMI);
		
		JMenu userMangerJMenu = new JMenu("用户管理");
		jmenuBar.add(userMangerJMenu);
		
		JMenuItem updPwdJMI = new JMenuItem("修改密码");
		updPwdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePWD frame = new updatePWD();
				frame.setVisible(true);

			}
		});
		userMangerJMenu.add(updPwdJMI);
		
		JMenuItem userAddJMI = new JMenuItem("用户添加");
		userAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd frame =  new UserAdd();
				frame.setVisible(true);
			}
		});
		userMangerJMenu.add(userAddJMI);
		
		JMenuItem userDelJMI = new JMenuItem("用户删除");
		userDelJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDel frame = new UserDel();
				frame.setVisible(true);
			}
		});
		userMangerJMenu.add(userDelJMI);
		contentPane = new JPanel();
		contentPane.setToolTipText("图书借阅系统");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
