package com.bbm.view;
//package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm.db.ReaderTypeDao;
import com.bbm.model.BookType;
import com.bbm.model.ReaderType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ReaderTypeManage extends JFrame {

	private JPanel contentPane;
	private JTextField typeJTF;
	private JTable jtable;
	private JTextField limitJTF;
	private JTextField IDJTF;
	private JTextField borrownumJTF;
	private JTextField typenameJTF;
	private JScrollPane jscrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderTypeManage frame = new ReaderTypeManage();
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
	public ReaderTypeManage() {
		setTitle("�������͹���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel selectJP = new JPanel();
		contentPane.add(selectJP, BorderLayout.NORTH);
		selectJP.setPreferredSize(new Dimension(400,150));
		selectJP.setLayout(new BorderLayout(0, 0));
		
		JPanel select_conditonJP = new JPanel();
		selectJP.add(select_conditonJP, BorderLayout.NORTH);
		
		JLabel typeJL = new JLabel("��������");
		select_conditonJP.add(typeJL);
		
		typeJTF = new JTextField();
		select_conditonJP.add(typeJTF);
		typeJTF.setColumns(16);
		
		class TableListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e) {
				int selRow = jtable.getSelectedRow();
				IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
				typenameJTF.setText(jtable.getValueAt(selRow, 1).toString().trim());
				borrownumJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
				limitJTF.setText(jtable.getValueAt(selRow, 3).toString().trim());
			}
		}
		
		JButton searchJB = new JButton("��ѯ");
		searchJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = typeJTF.getText().trim();
				Object[][] results =getSelect(ReaderTypeDao.selectReaderType(condition));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		select_conditonJP.add(searchJB);
		
		JPanel select_resultJP = new JPanel();
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		select_resultJP.setLayout(null);
		
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 10, 404, 97);
		select_resultJP.add(jscrollPane);
		
		//������Ϣ�����ά��������,���˴������
		Object[][] result = getSelect(ReaderTypeDao.selectReaderType());
		//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
		jtable = new JTable(result,readersearch);
		jscrollPane.setViewportView(jtable);
		jtable.addMouseListener(new TableListener());
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton addJB = new JButton("���");
		addJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int readerid =Integer.parseInt(IDJTF.getText().trim());
				String typename = typenameJTF.getText().trim();
				int borrownum =Integer.parseInt(borrownumJTF.getText().trim());
				int  limit=Integer.parseInt(limitJTF.getText().trim());
				int i = ReaderTypeDao.insertReaderType(readerid, typename, borrownum, limit);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				}
				
				Object[][] result = getSelect(ReaderTypeDao.selectReaderType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(addJB);
		
		JButton updateJB = new JButton("�޸�");
		updateJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int readerid =Integer.parseInt(IDJTF.getText().trim());
				String typename = typenameJTF.getText().trim();
				int borrownum =Integer.parseInt(borrownumJTF.getText().trim());
				int  limit=Integer.parseInt(limitJTF.getText().trim());
				int i =ReaderTypeDao.updateReaderType(readerid, typename, borrownum, limit);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				}
				
				Object[][] result = getSelect(ReaderTypeDao.selectReaderType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(updateJB);
		
		JButton delJB = new JButton("ɾ��");
		delJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int readerid =Integer.parseInt(IDJTF.getText().trim());
				int i = ReaderTypeDao.deleteReaderType(readerid);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				}
				
				Object[][] result = getSelect(ReaderTypeDao.selectReaderType());
				//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(delJB);
		
		JButton exitJB = new JButton("�˳�");
		exitJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(exitJB);
		
		JPanel infoJP = new JPanel();
		contentPane.add(infoJP, BorderLayout.CENTER);
		infoJP.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel IDJL = new JLabel("�������ͱ�ţ�");
		infoJP.add(IDJL);
		
		IDJTF = new JTextField();
		infoJP.add(IDJTF);
		IDJTF.setColumns(10);
		
		JLabel nameJL = new JLabel("�����������ƣ�");
		infoJP.add(nameJL);
		
		typenameJTF = new JTextField();
		infoJP.add(typenameJTF);
		typenameJTF.setColumns(10);
		
		JLabel amoutJL = new JLabel("�ɽ�ͼ��������");
		infoJP.add(amoutJL);
		
		borrownumJTF = new JTextField();
		infoJP.add(borrownumJTF);
		borrownumJTF.setColumns(10);
		
		JLabel intimeJL = new JLabel("�ɽ�ͼ�����ޣ�");
		infoJP.add(intimeJL);
		
		limitJTF = new JTextField();
		infoJP.add(limitJTF);
		limitJTF.setColumns(10);
	}
	String[] readersearch ={"�������͵ı��","������������","�ɽ�ͼ������","�ɽ�ͼ������"};
	//���ص�result ��Ϊ�������
	Object[][] getSelect(List<ReaderType> list)
	{
		Object [][] result = new Object [list.size()][readersearch.length];
		for(int i=0;i<list.size();i++)
		{
			ReaderType readertype = list.get(i);
			result[i][0]=readertype.getId();
			result[i][1]=readertype.getName();
			result[i][2]=readertype.getMaxborrownum();
			result[i][3]=readertype.getLimit();
			
		}
		return result;
	}
}
