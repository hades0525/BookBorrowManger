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
		setTitle("ͼ�����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar jmenuBar = new JMenuBar();
		setJMenuBar(jmenuBar);
		
		JMenu readerMangerJMenu = new JMenu("������Ϣ����");
		readerMangerJMenu.setHorizontalAlignment(SwingConstants.CENTER);
		jmenuBar.add(readerMangerJMenu);
		
		JMenuItem readerAddJMI = new JMenuItem("������Ϣ���");
		readerAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderAdd frame = new ReaderAdd();
				frame.setVisible(true);
			}
		});
		readerAddJMI.setSelected(true);
		readerMangerJMenu.add(readerAddJMI);
		
		JMenuItem readerSelUpdJMI = new JMenuItem("������Ϣ��ѯ���޸�");
		readerSelUpdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderSelectandUpdate frame = new ReaderSelectandUpdate();
				frame.setVisible(true);
			}
		});
		readerSelUpdJMI.setSelected(true);
		readerMangerJMenu.add(readerSelUpdJMI);
		
		JMenu bookMangerJMenu = new JMenu("ͼ����Ϣ����");
		jmenuBar.add(bookMangerJMenu);
		
		JMenuItem bookAddJMI = new JMenuItem("ͼ����Ϣ���");
		bookAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAdd frame = new BookAdd();
				frame.setVisible(true);
			}
		});
		bookMangerJMenu.add(bookAddJMI);
		
		JMenuItem bookSelUpdJMI = new JMenuItem("ͼ����Ϣ��ѯ���޸�");
		bookSelUpdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSelectUpdate frame =  new BookSelectUpdate();
				frame.setVisible(true);
			}
		});
		bookMangerJMenu.add(bookSelUpdJMI);
		
		JMenu bookBorrowJMenu = new JMenu("ͼ����Ĺ���");
		jmenuBar.add(bookBorrowJMenu);
		
		JMenuItem bookBorrowJMI = new JMenuItem("ͼ�����");
		bookBorrowJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBorrow frame = new BookBorrow();
				frame.setVisible(true);
			}
		});
		bookBorrowJMenu.add(bookBorrowJMI);
		
		JMenuItem bookReturnJMI = new JMenuItem("ͼ��黹");
		bookReturnJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookReturn frame = new BookReturn();
				frame.setVisible(true);
			}
		});
		bookBorrowJMenu.add(bookReturnJMI);
		
		JMenu baseInfoJMenu = new JMenu("������Ϣά��");
		jmenuBar.add(baseInfoJMenu);
		
		JMenuItem bookTypeJMI = new JMenuItem("ͼ���������");
		bookTypeJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManager frame =  new BookTypeManager();
				frame.setVisible(true);
			}
		});
		baseInfoJMenu.add(bookTypeJMI);
		
		JMenuItem readerTypeJMI = new JMenuItem("�����������");
		readerTypeJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderTypeManage frame = new ReaderTypeManage();
				frame.setVisible(true);
			}
		});
		baseInfoJMenu.add(readerTypeJMI);
		
		JMenuItem fineSetJMI = new JMenuItem("��������");
		fineSetJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FineSet frame =  new FineSet();
				frame.setVisible(true);

			}
		});
		baseInfoJMenu.add(fineSetJMI);
		
		JMenu userMangerJMenu = new JMenu("�û�����");
		jmenuBar.add(userMangerJMenu);
		
		JMenuItem updPwdJMI = new JMenuItem("�޸�����");
		updPwdJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePWD frame = new updatePWD();
				frame.setVisible(true);

			}
		});
		userMangerJMenu.add(updPwdJMI);
		
		JMenuItem userAddJMI = new JMenuItem("�û����");
		userAddJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd frame =  new UserAdd();
				frame.setVisible(true);
			}
		});
		userMangerJMenu.add(userAddJMI);
		
		JMenuItem userDelJMI = new JMenuItem("�û�ɾ��");
		userDelJMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDel frame = new UserDel();
				frame.setVisible(true);
			}
		});
		userMangerJMenu.add(userDelJMI);
		contentPane = new JPanel();
		contentPane.setToolTipText("ͼ�����ϵͳ");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
