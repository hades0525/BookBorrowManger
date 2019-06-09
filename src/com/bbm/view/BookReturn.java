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
import java.util.Date;
import java.text.SimpleDateFormat;
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
import com.bbm.db.ReaderDao;
import com.bbm.db.UserDao;
import com.bbm.model.BorrowBook;
import com.bbm.model.Reader;
import com.bbm.model.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookReturn extends JFrame {


	private JPanel contentPane;
	private JTextField readeridJTF;
	private JTextField readernameJTF;
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
	private JTextField overdayJTF;
	private JTextField fineJTF;
	private JScrollPane jscrollPane;
	private double zfk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturn frame = new BookReturn();
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
	public BookReturn() {
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
		
		class TableListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e) {
				int selRow = jtable.getSelectedRow();
				ISBNJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
				nameJTF.setText(jtable.getValueAt(selRow, 1).toString().trim());
				typeJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getTypename());
				authorJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getAuthor());
				publishJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getPublish());
				dateJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getPublishdate().toString());
				printJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getPrinttime()+"");
				priceJTF.setText(BookDao.selectBookByISBN(ISBNJTF.getText().trim()).get(0).getUnitprice()+"");
				
				
				Date borrowday,returnday;
				borrowday = java.sql.Date.valueOf(jtable.getValueAt(selRow, 2).toString().trim());
				Date returndate =null;
				for(int i=0;i<BookBorrowDao.selectBookBorrowReaderId(readeridJTF.getText().trim()).size();i++)
				{
					returndate = BookBorrowDao.selectBookBorrowReaderId(readeridJTF.getText().trim()).get(i).getReturndate();
				}
				returnday = java.sql.Date.valueOf(returndate.toString());
				Long m_intervalday = returnday.getTime()-borrowday.getTime();
				Long borrowtime = m_intervalday/1000/60/60/24;
				List<Reader> list1 = ReaderDao.selectReaderById(readeridJTF.getText().trim());
				int limit;
				for(int i=0;i<list1.size();i++)
				{
					Reader reader = list1.get(i);
					limit=reader.getLimit();
					if(borrowtime>limit)
					{
						overdayJTF.setText(String.valueOf(borrowtime));
						double zfk = Double.valueOf(borrowtime)*FineSet.fine;
						fineJTF.setText(String.valueOf(zfk));
					}else
					{
						overdayJTF.setText("没有超过规定天数");
						fineJTF.setText("0");
					}
				}
			}
		}
		
		readeridJTF = new JTextField();
		readeridJTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readerid = readeridJTF.getText().trim();
				String readername = ReaderDao.selectReaderById(readerid).get(0).getName();
				readernameJTF.setText(readername);
				String readertype = ReaderDao.selectReaderById(readerid).get(0).getTypename();
				readertypeJTF.setText(readertype);
				Object[][] results =getSelect(BookBorrowDao.selectBookBorrowReaderId(readerid));	
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		readeridJTF.setBounds(73, 10, 129, 21);
		readerJP.add(readeridJTF);
		readeridJTF.setColumns(10);
		
		JLabel label = new JLabel("读者姓名：");
		label.setBounds(212, 13, 68, 20);
		readerJP.add(label);
		
		readernameJTF = new JTextField();
		readernameJTF.setColumns(10);
		readernameJTF.setBounds(274, 10, 129, 21);
		readerJP.add(readernameJTF);
		
		JLabel label_1 = new JLabel("读者类别：");
		label_1.setBounds(413, 13, 68, 20);
		readerJP.add(label_1);
		
		readertypeJTF = new JTextField();
		readertypeJTF.setColumns(10);
		readertypeJTF.setBounds(476, 10, 129, 21);
		readerJP.add(readertypeJTF);
		
		JPanel panel = new JPanel();
		readerBorrowJP.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
			
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(48, 10, 467, 129);
		panel.add(jscrollPane);
		
		jtable = new JTable();
		
		jscrollPane.setViewportView(jtable);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton returnJB = new JButton("归还");
		returnJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readerid = readeridJTF.getText().trim();
				String ISBN = ISBNJTF.getText().trim();
				Date returndate = new java.util.Date();
				int i= BookBorrowDao.returnBook(readerid, ISBN, returndate);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "归还图书成功");
				}
				
				
			}
		});
		buttonJP.add(returnJB);
		
		JButton closeJB = new JButton("关闭");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(closeJB);
		
		JPanel bookReturnJP = new JPanel();
		bookReturnJP.setBorder(new TitledBorder(null, "图书归还", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(bookReturnJP, BorderLayout.CENTER);
		bookReturnJP.setLayout(new GridLayout(6, 4, 1, 1));
		
		JLabel ISBNJL = new JLabel("ISBN");
		bookReturnJP.add(ISBNJL);
		
		ISBNJTF = new JTextField();
		bookReturnJP.add(ISBNJTF);
		ISBNJTF.setColumns(10);
		
		JLabel typeJL = new JLabel("类别：");
		bookReturnJP.add(typeJL);
		
		typeJTF = new JTextField();
		bookReturnJP.add(typeJTF);
		typeJTF.setColumns(10);
		
		JLabel nameJL = new JLabel("书名：");
		bookReturnJP.add(nameJL);
		
		nameJTF = new JTextField();
		bookReturnJP.add(nameJTF);
		nameJTF.setColumns(10);
		
		JLabel authorJL = new JLabel("作者：");
		bookReturnJP.add(authorJL);
		
		authorJTF = new JTextField();
		bookReturnJP.add(authorJTF);
		authorJTF.setColumns(10);
		
		JLabel publishJL = new JLabel("出版社：");
		bookReturnJP.add(publishJL);
		
		publishJTF = new JTextField();
		bookReturnJP.add(publishJTF);
		publishJTF.setColumns(10);
		
		JLabel dateJL = new JLabel("出版日期：");
		bookReturnJP.add(dateJL);
		
		dateJTF = new JTextField();
		bookReturnJP.add(dateJTF);
		dateJTF.setColumns(10);
		
		JLabel printJL = new JLabel("打印次数：");
		bookReturnJP.add(printJL);
		
		printJTF = new JTextField();
		bookReturnJP.add(printJTF);
		printJTF.setColumns(10);
		
		JLabel priceJL = new JLabel("单价：");
		bookReturnJP.add(priceJL);
		
		priceJTF = new JTextField();
		bookReturnJP.add(priceJTF);
		priceJTF.setColumns(10);
		
		JLabel regtimeJL = new JLabel("当前日期：");
		bookReturnJP.add(regtimeJL);
		
		regtimeJTF = new JTextField();
		bookReturnJP.add(regtimeJTF);
		regtimeJTF.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		
		JLabel overdayJL = new JLabel("超期天数：");
		bookReturnJP.add(overdayJL);
		
		overdayJTF = new JTextField();
		bookReturnJP.add(overdayJTF);
		overdayJTF.setColumns(10);
		
		JLabel fineJL = new JLabel("罚金：");
		bookReturnJP.add(fineJL);
		
		fineJTF = new JTextField();
		bookReturnJP.add(fineJTF);
		fineJTF.setColumns(10);
		
		JLabel operatorJL = new JLabel("操作人员：");
		bookReturnJP.add(operatorJL);
		
		JComboBox operatorJCB = new JComboBox();
		List<Users> list =UserDao.selectUser();
		for(int i=0;i<list.size();i++)
		{
			Users user = list.get(i);
			operatorJCB.addItem(user.getName());
		}
		bookReturnJP.add(operatorJCB);
	}
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
