package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.bbm.db.BookBorrowDao;
import com.bbm.db.BookDao;
import com.bbm.db.UserDao;
import com.bbm.model.Book;
import com.bbm.model.BorrowBook;
import com.bbm.model.Users;
import com.bbm.db.ReaderDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BookBorrow extends JFrame {

	private JPanel contentPane;
	private JTextField readeridJTF;
	private JTextField reeadernameJTF;
	private JTextField readertypeJTF;
	private JTable jtable;
	private JTextField nameJTF;
	private JTextField authorJTF;
	private JTextField priceJTF;
	private JTextField regtimeJTF;
	private JTextField typeJTF;
	private JTextField publishJTF;
	private JTextField printJTF;
	private JTextField ISBNJTF;
	private JTextField dateJTF;
	private JScrollPane jscrollPane;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBorrow frame = new BookBorrow();
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
	public BookBorrow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel readerBorrowJP = new JPanel();
		readerBorrowJP.setBorder(new TitledBorder(null, "读者借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(readerBorrowJP, BorderLayout.NORTH);
		readerBorrowJP.setPreferredSize(new Dimension(450, 215));
		readerBorrowJP.setLayout(new BorderLayout(0, 0));
		
		JPanel readerJP = new JPanel();
		readerBorrowJP.add(readerJP, BorderLayout.NORTH);
		readerJP.setLayout(null);
		readerJP.setPreferredSize(new Dimension(450, 40));
		
		JLabel lblNewLabel = new JLabel("读者编号：");
		lblNewLabel.setBounds(10, 10, 68, 20);
		readerJP.add(lblNewLabel);
		
		panel = new JPanel();
		readerBorrowJP.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(88, 10, 427, 129);
		panel.add(jscrollPane);
		
		jtable = new JTable();
		jscrollPane.setViewportView(jtable);
		
		readeridJTF = new JTextField();
		readeridJTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readerid = readeridJTF.getText().trim();
				String readername = ReaderDao.selectReaderById(readerid).get(0).getName();
				reeadernameJTF.setText(readername);
				String readertype = ReaderDao.selectReaderById(readerid).get(0).getTypename();
				readertypeJTF.setText(readertype);
				
				Object[][] results =getSelect(BookBorrowDao.selectBookBorrowReaderId(readerid));	
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			}
		});
		readeridJTF.setBounds(73, 10, 129, 21);
		readerJP.add(readeridJTF);
		readeridJTF.setColumns(10);
		
		JLabel label = new JLabel("读者姓名：");
		label.setBounds(212, 13, 68, 20);
		readerJP.add(label);
		
		reeadernameJTF = new JTextField();
		reeadernameJTF.setEditable(false);
		reeadernameJTF.setColumns(10);
		reeadernameJTF.setBounds(274, 10, 129, 21);
		readerJP.add(reeadernameJTF);
		
		JLabel label_1 = new JLabel("读者类别：");
		label_1.setBounds(413, 13, 68, 20);
		readerJP.add(label_1);
		
		readertypeJTF = new JTextField();
		readertypeJTF.setEditable(false);
		readertypeJTF.setColumns(10);
		readertypeJTF.setBounds(476, 10, 129, 21);
		readerJP.add(readertypeJTF);
		
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton borrowJB = new JButton("借阅");
		borrowJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readerid = readeridJTF.getText().trim();
				String ISBN = ISBNJTF.getText().trim();
				
				String regtime = regtimeJTF.getText().trim();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try
				{
					date = sdf.parse(regtime);
				} catch (ParseException e1)
				{
					e1.printStackTrace();
				}
				
				int i = BookBorrowDao.borrowBook(readerid, ISBN, date);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "图书借阅成功");
				}
			}
		});
		buttonJP.add(borrowJB);
		
		JButton closeJB = new JButton("关闭");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(closeJB);
		
		JPanel bookBorrowJP = new JPanel();
		bookBorrowJP.setBorder(new TitledBorder(null, "图书借阅", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(bookBorrowJP, BorderLayout.CENTER);
		bookBorrowJP.setLayout(new GridLayout(5, 4, 1, 1));
		
		JLabel ISBNJL = new JLabel("ISBN");
		bookBorrowJP.add(ISBNJL);
		
		ISBNJTF = new JTextField();
		ISBNJTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN = ISBNJTF.getText().trim();
				for(int i=0;i<BookDao.selectBook().size();i++)
				{			
					if( ISBN.equals(BookDao.selectBook().get(i).getISBN()))
					{
						typeJTF.setText(BookDao.selectBook().get(i).getTypename());
						nameJTF.setText(BookDao.selectBook().get(i).getBookname());
						authorJTF.setText(BookDao.selectBook().get(i).getAuthor());
						publishJTF.setText(BookDao.selectBook().get(i).getPublish());
						dateJTF.setText(BookDao.selectBook().get(i).getPublishdate().toString());
						printJTF.setText(BookDao.selectBook().get(i).getPrinttime()+"");
						priceJTF.setText(BookDao.selectBook().get(i).getUnitprice()+"");
					}
				}
			}
		});
		bookBorrowJP.add(ISBNJTF);
		ISBNJTF.setColumns(10);
		
		JLabel typeJL = new JLabel("类别：");
		bookBorrowJP.add(typeJL);
		
		typeJTF = new JTextField();
		bookBorrowJP.add(typeJTF);
		typeJTF.setColumns(10);
		
		JLabel nameJL = new JLabel("书名：");
		bookBorrowJP.add(nameJL);
		
		nameJTF = new JTextField();
		bookBorrowJP.add(nameJTF);
		nameJTF.setColumns(10);
		
		JLabel authorJL = new JLabel("作者：");
		bookBorrowJP.add(authorJL);
		
		authorJTF = new JTextField();
		bookBorrowJP.add(authorJTF);
		authorJTF.setColumns(10);
		
		JLabel publishJL = new JLabel("出版社：");
		bookBorrowJP.add(publishJL);
		
		publishJTF = new JTextField();
		bookBorrowJP.add(publishJTF);
		publishJTF.setColumns(10);
		
		JLabel dateJL = new JLabel("出版日期：");
		bookBorrowJP.add(dateJL);
		
		dateJTF = new JTextField();
		bookBorrowJP.add(dateJTF);
		dateJTF.setColumns(10);
		
		JLabel printJL = new JLabel("打印次数：");
		bookBorrowJP.add(printJL);
		
		printJTF = new JTextField();
		bookBorrowJP.add(printJTF);
		printJTF.setColumns(10);
		
		JLabel priceJL = new JLabel("单价：");
		bookBorrowJP.add(priceJL);
		
		priceJTF = new JTextField();
		bookBorrowJP.add(priceJTF);
		priceJTF.setColumns(10);
		
		JLabel regtimeJL = new JLabel("当前日期：");
		bookBorrowJP.add(regtimeJL);
		
		regtimeJTF = new JTextField();
		regtimeJTF.setEditable(false);
		bookBorrowJP.add(regtimeJTF);
		regtimeJTF.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		
		JLabel operatorJL = new JLabel("操作人员：");
		bookBorrowJP.add(operatorJL);
		
		JComboBox operatorJCB = new JComboBox();
		List<Users> list =UserDao.selectUser();
		for(int i=0;i<list.size();i++)
		{
			Users user = list.get(i);
			operatorJCB.addItem(user.getName());
		}
		bookBorrowJP.add(operatorJCB);
	}
	 //readersearch 作为表头
	String[] readersearch ={"图书编号","图书名称","借书日期"};
	//返回的result 作为表的数据
	Object[][] getSelect(List<BorrowBook> list)
	{
		Object [][] result = new Object [list.size()][readersearch.length];
		for(int i=0;i<list.size();i++)
		{
			BorrowBook borrowbook = list.get(i);
			result[i][0]=borrowbook.getISBN();
			result[i][1]=BookDao.selectBookByISBN(borrowbook.getISBN()).get(0).getBookname();
			result[i][2]=borrowbook.getBorrowdate();
		}
		return result;
	}
}
