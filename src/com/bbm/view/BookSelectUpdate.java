package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.bbm.db.BookDao;
import com.bbm.db.BookTypeDao;
import com.bbm.model.Book;
import com.bbm.model.BookType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookSelectUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField select_JTF;
	private JTable jtable;
	private JTextField ISBNJTF;
	private JTextField nameJTF;
	private JTextField authorJTF;
	private JTextField publishJTF;
	private JTextField dateJTF;
	private JTextField printJTF;
	private JTextField priceJTF;
	private JComboBox select_JCB;
	private JScrollPane jscrollPane;
	private JComboBox typeJCB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSelectUpdate frame = new BookSelectUpdate();
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
	public BookSelectUpdate() {
		setTitle("图书查询与修改");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 464, 361);
		contentPane.add(tabbedPane);
		
		JPanel selectJP = new JPanel();
		tabbedPane.addTab("图书信息查询", null, selectJP, null);
		selectJP.setLayout(new BorderLayout(0, 0));
		
		JPanel select_conditonJP = new JPanel();
		selectJP.add(select_conditonJP, BorderLayout.NORTH);
		
		select_JCB = new JComboBox();
		select_JCB.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "图书名称", "图书类别", "作者", "出版社"}));
		select_conditonJP.add(select_JCB);
		
		select_JTF = new JTextField();
		select_conditonJP.add(select_JTF);
		select_JTF.setColumns(25);
		
		JPanel buttonJP1 = new JPanel();
		selectJP.add(buttonJP1, BorderLayout.SOUTH);
		
		JButton searchJB = new JButton("查询");
		searchJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//存放用户选择的查询条件
				String condition =(String) select_JCB.getSelectedItem();
				//按读者编号查询
				if(condition.equals("ISBN"))
				{
					Object[][] results =getSelect(BookDao.selectBookByISBN(select_JTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
				
				}
				else if(condition.equals("图书名称"))
				{
					Object[][] results =getSelect(BookDao.selectBookByName(select_JTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
				}
				else if(condition.equals("图书类别"))
				{
					Object[][] results =getSelect(BookDao.selectBookByType(select_JTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
				}
				else if(condition.equals("作者"))
				{
					Object[][] results =getSelect(BookDao.selectBookByAuthor(select_JTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
				}
				else if(condition.equals("出版社"))
				{
					Object[][] results =getSelect(BookDao.selectBookByPublish(select_JTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
				}
			}
		});
		buttonJP1.add(searchJB);
		
		JButton exitJB = new JButton("退出");
		exitJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		buttonJP1.add(exitJB);
		
		JPanel select_resultJP = new JPanel();
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		select_resultJP.setLayout(null);
		
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 0, 439, 247);
		select_resultJP.add(jscrollPane);
		
		//读者信息放入二维对象数组,用了创建表格
		Object[][] result = getSelect(BookDao.selectBook());
		//表中放入读者信息，readersearch 作为表头，result 作为表的数据
		jtable = new JTable(result,readersearch);
		jscrollPane.setViewportView(jtable);
		
		JPanel updateJP = new JPanel();
		tabbedPane.addTab("图书信息修改", null, updateJP, null);
		updateJP.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonJP2 = new JPanel();
		updateJP.add(buttonJP2, BorderLayout.SOUTH);
		
		JButton updateJB = new JButton("修改");
		updateJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = ISBNJTF.getText().trim();
				String type = (String) typeJCB.getSelectedItem();
				String name = nameJTF.getText().trim();
 			    String author = authorJTF.getText().trim();
				String publish = publishJTF.getText().trim();
				String publishdate = dateJTF.getText().trim();
				int printtime = Integer.parseInt(printJTF.getText().trim());	
				double price =Double.parseDouble(priceJTF.getText().trim());
				int i = BookDao.updateBook(isbn, type, name, author, publish, publishdate, printtime, price);
				if(i==1)
				JOptionPane.showMessageDialog(null, "修改成功");
				
				Object[][] result = getSelect(BookDao.selectBook());
				//表中放入读者信息，readersearch 作为表头，result 作为表的数据
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
			}
		});
		buttonJP2.add(updateJB);
		
		JButton closeJB = new JButton("关闭");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP2.add(closeJB);
		
		JPanel bookJP = new JPanel();
		updateJP.add(bookJP, BorderLayout.CENTER);
		bookJP.setLayout(new GridLayout(8, 2, 2, 1));
		
		JLabel ISBNJL = new JLabel("ISBN：");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(ISBNJL);
		
		ISBNJTF = new JTextField();
		ISBNJTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = ISBNJTF.getText().trim();
				for(int i=0;i<BookDao.selectBook().size();i++)
				{			
					if( isbn.equals(BookDao.selectBook().get(i).getISBN()))
					{
						typeJCB.setSelectedItem(BookDao.selectBook().get(i).getTypename());
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
		bookJP.add(ISBNJTF);
		ISBNJTF.setColumns(10);
		
		JLabel typeJL = new JLabel("类别：");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(typeJL);
		
		typeJCB = new JComboBox();
		List<BookType> list =BookTypeDao.selectBookType();
		for(int i=0;i<list.size();i++)
		{
			BookType bt = list.get(i);
			typeJCB.addItem(bt.getTypename());
		}
		bookJP.add(typeJCB);
		
		JLabel nameJL = new JLabel("书名：");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(nameJL);
		
		nameJTF = new JTextField();
		bookJP.add(nameJTF);
		nameJTF.setColumns(10);
		
		JLabel authorJL = new JLabel("作者：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(authorJL);
		
		authorJTF = new JTextField();
		bookJP.add(authorJTF);
		authorJTF.setColumns(10);
		
		JLabel publishJL = new JLabel("出版社：");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(publishJL);
		
		publishJTF = new JTextField();
		bookJP.add(publishJTF);
		publishJTF.setColumns(10);
		
		JLabel dateJL = new JLabel("出版日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(dateJL);
		
		dateJTF = new JTextField();
		bookJP.add(dateJTF);
		dateJTF.setColumns(10);
		
		JLabel printJL = new JLabel("打印次数：");
		printJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(printJL);
		
		printJTF = new JTextField();
		bookJP.add(printJTF);
		printJTF.setColumns(10);
		
		JLabel priceJL = new JLabel("单价：");
		priceJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(priceJL);
		
		priceJTF = new JTextField();
		bookJP.add(priceJTF);
		priceJTF.setColumns(10);
	}
	
	   //readersearch 作为表头
		String[] readersearch ={"编号","类型","名称","作者","出版社","出版日期","出版次数","单价"};
		//返回的result 作为表的数据
		Object[][] getSelect(List<Book> list)
		{
			Object [][] result = new Object [list.size()][readersearch.length];
			for(int i=0;i<list.size();i++)
			{
				Book book = list.get(i);
				result[i][0]=book.getISBN();
				result[i][1]=book.getTypename();
				result[i][2]=book.getBookname();
				result[i][3]=book.getAuthor();
				result[i][4]=book.getPublish();
				result[i][5]=book.getPublishdate();
				result[i][6]=book.getPrinttime();
				result[i][7]=book.getUnitprice();
			}
			return result;
		}
}
