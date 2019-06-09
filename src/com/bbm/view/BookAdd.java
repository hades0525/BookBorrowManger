package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.bbm.db.BookDao;
import com.bbm.db.BookTypeDao;
import com.bbm.model.BookType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class BookAdd extends JFrame {

	private JPanel contentPane;
	private JTextField ISBNJTF;
	private JTextField publishJTF;
	private JTextField pubDateJTF;
	private JTextField nameJTF;
	private JTextField printJTF;
	private JTextField priceJTF;
	private JTextField authorJTF;
	private JComboBox typeJCB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAdd frame = new BookAdd();
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
	public BookAdd() {
		setTitle("图书信息添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel readerAddJP = new JPanel();
		contentPane.add(readerAddJP, BorderLayout.CENTER);
		readerAddJP.setLayout(new GridLayout(4, 4, 2, 1));
		
		JLabel ISBNJL = new JLabel("ISBN：");
		readerAddJP.add(ISBNJL);
		
		ISBNJTF = new JTextField();
		ISBNJTF.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				String ISBN = ISBNJTF.getText();
				for(int i=0;i<BookDao.selectBook().size();i++)
			{			
				if( ISBN.equals(BookDao.selectBook().get(i).getISBN()))
				{
					JOptionPane.showMessageDialog(null, "添加书号重复！");
				}
			}
			}
		});
		readerAddJP.add(ISBNJTF);
		ISBNJTF.setColumns(10);
		
		JLabel typeJL = new JLabel("类别：");
		readerAddJP.add(typeJL);
		
		typeJCB = new JComboBox();
		List<BookType> list =BookTypeDao.selectBookType();
		for(int i=0;i<list.size();i++)
		{
			BookType bt = list.get(i);
			typeJCB.addItem(bt.getTypename());
		}
		readerAddJP.add(typeJCB);
		
		JLabel nameJL = new JLabel("书名：");
		readerAddJP.add(nameJL);
		
		nameJTF = new JTextField();
		readerAddJP.add(nameJTF);
		nameJTF.setColumns(10);
		
		JLabel authorJL = new JLabel("作者：");
		readerAddJP.add(authorJL);
		
		authorJTF = new JTextField();
		readerAddJP.add(authorJTF);
		authorJTF.setColumns(10);
		
		
		
		JLabel publishJL = new JLabel("出版社：");
		readerAddJP.add(publishJL);
		
		publishJTF = new JTextField();
		readerAddJP.add(publishJTF);
		publishJTF.setColumns(10);
		
		JLabel pubDateJL = new JLabel("出版日期：");
		readerAddJP.add(pubDateJL);
		
		pubDateJTF = new JTextField();
		readerAddJP.add(pubDateJTF);
		pubDateJTF.setColumns(10);
		
		JLabel printJL = new JLabel("印刷次数：");
		readerAddJP.add(printJL);
		
		printJTF = new JTextField();
		readerAddJP.add(printJTF);
		printJTF.setColumns(10);
		
		JLabel priceJL = new JLabel("单价：");
		readerAddJP.add(priceJL);
		
		
		priceJTF = new JTextField();
		readerAddJP.add(priceJTF);
		priceJTF.setColumns(10);

		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton addJB = new JButton("添加");
		addJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ISBNJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ISBN不可以为空！");
					return;
				}
				if(ISBNJTF.getText().length() != 10)
				{
					JOptionPane.showMessageDialog(null, "ISBN长度为10位！");
					return;
				}
				if(BookDao.selectBookByISBN(ISBNJTF.getText()) != null)
				{
					JOptionPane.showMessageDialog(null, "添加书号重复！");
					return;
				}
				if(nameJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "书名不可以为空！");
					return;
				}
				if(authorJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "作者不可以为空！");
					return;
				}	
				if(publishJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "出版社不可以为空！");
					return;
				}
				if(pubDateJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "出版日期不可以为空！");
					return;
				}
				if(printJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "打印次数不可以为空！");
					return;
				}
				if(priceJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "单价不可以为空！");
					return;
				}
				
				String isbn = ISBNJTF.getText().trim();
				String booktype =(String) typeJCB.getSelectedItem();
				String name = nameJTF.getText().trim();
				String author = authorJTF.getText().trim();
				String publish = publishJTF.getText().trim();
				String publishdate = pubDateJTF.getText().trim();
				int printtime =Integer.parseInt(printJTF.getText().trim());
				double price =Double.parseDouble(priceJTF.getText().trim());
				int i = BookDao.insertBook(isbn, booktype, name, author, publish, publishdate, printtime, price);
				if(i == 1)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
					BookAdd.this.setVisible(false);
				}
			}
		});
		buttonJP.add(addJB);
		
		JButton restJB = new JButton("重置");
		restJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ISBNJTF.setText("");
				nameJTF.setText("");
				authorJTF.setText("");
				publishJTF.setText("");
				pubDateJTF.setText("");
				printJTF.setText("");
				priceJTF.setText("");
			}
		});
		buttonJP.add(restJB);
		
		JButton closeJB = new JButton("关闭");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(closeJB);
	}

}
