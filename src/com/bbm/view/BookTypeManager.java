package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.SwingConstants;

import com.bbm.db.BookTypeDao;
import com.bbm.model.BookType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManager extends JFrame {

	private JPanel contentPane;
	private JTextField bookTypeJTF;
	private JTable jtable;
	private JTextField nameJTF;
	private JTextField IDJTF;
	private JScrollPane jscrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManager frame = new BookTypeManager();
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
	public BookTypeManager() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel selectJP = new JPanel();
		contentPane.add(selectJP, BorderLayout.NORTH);
		selectJP.setPreferredSize(new Dimension(400, 125));
		selectJP.setLayout(new BorderLayout(0, 0));
		
		JPanel select_conditionJP = new JPanel();
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		
		JLabel bookTypeJL = new JLabel("ͼ������");
		select_conditionJP.add(bookTypeJL);
		
		bookTypeJTF = new JTextField();
		select_conditionJP.add(bookTypeJTF);
		bookTypeJTF.setColumns(22);
		
		class TableListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e) {
				int selRow = jtable.getSelectedRow();
				IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
				nameJTF.setText(jtable.getValueAt(selRow, 1).toString().trim());
				
			}
		}
		
		JButton searchJB = new JButton("��ѯ");
		searchJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = bookTypeJTF.getText().trim();
				Object[][] results =getSelect(BookTypeDao.selectBookType(condition));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		select_conditionJP.add(searchJB);
		
		JPanel select_resultJP = new JPanel();
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		select_resultJP.setLayout(null);
		
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(23, 10, 379, 72);
		select_resultJP.add(jscrollPane);
		
		Object[][] result = getSelect(BookTypeDao.selectBookType());
		//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
		jtable = new JTable(result,readersearch);
		jscrollPane.setViewportView(jtable);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtable.addMouseListener(new TableListener());
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton addJB = new JButton("���");
		addJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =Integer.parseInt(IDJTF.getText().trim());
				String typename = nameJTF.getText().trim();
				int i = BookTypeDao.insertBookType(id, typename);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				}
				

				Object[][] result = getSelect(BookTypeDao.selectBookType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(addJB);
		
		JButton updateJB = new JButton("�޸�");
		updateJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =Integer.parseInt(IDJTF.getText().trim());
				String typename = nameJTF.getText().trim();
				int i = BookTypeDao.updateBookType(id, typename);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				}
				
				Object[][] result = getSelect(BookTypeDao.selectBookType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(updateJB);
		
		JButton delJB = new JButton("ɾ��");
		delJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =Integer.parseInt(IDJTF.getText().trim());
				int i = BookTypeDao.deleteBookType(id);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				}
				

				Object[][] result = getSelect(BookTypeDao.selectBookType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(delJB);
		
		JButton exitJB = new JButton("�˳�");
		buttonJP.add(exitJB);
		
		JPanel readerTypeJP = new JPanel();
		contentPane.add(readerTypeJP, BorderLayout.CENTER);
		readerTypeJP.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel IDJL = new JLabel("ͼ�����ͱ�ţ�");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerTypeJP.add(IDJL);
		
		IDJTF = new JTextField();
		readerTypeJP.add(IDJTF);
		IDJTF.setColumns(10);
		
		JLabel nameJL = new JLabel("ͼ���������ƣ�");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerTypeJP.add(nameJL);
		
		nameJTF = new JTextField();
		readerTypeJP.add(nameJTF);
		nameJTF.setColumns(10);
	}
	String[] readersearch ={"ͼ�������","ͼ���������"};
	//���ص�result ��Ϊ�������
	Object[][] getSelect(List<BookType> list)
	{
		Object [][] result = new Object [list.size()][readersearch.length];
		for(int i=0;i<list.size();i++)
		{
			BookType booktype = list.get(i);
			result[i][0]=booktype.getTypeid();
			result[i][1]=booktype.getTypename();	
		}
		return result;
	}
}
