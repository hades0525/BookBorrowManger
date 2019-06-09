package com.bbm.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import com.bbm.db.ReaderDao;
import com.bbm.db.ReaderTypeDao;
import com.bbm.model.Reader;
import com.bbm.model.ReaderType;

import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ReaderSelectandUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField conditionJTF;
	private JTextField ageJTF;
	private JTextField phoneJTF;
	private JTextField IDJTF;
	private JTextField nameJTF;
	private JTextField deptJTF;
	private JTextField regtimeJTF;
	private JScrollPane jscrollPane;
	private JComboBox conditionJCB;
	private JComboBox typeJCB;
	private JRadioButton JRB1;
	private JRadioButton JRB2;
	
	/**
	 * @wbp.nonvisual location=-22,417
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderSelectandUpdate frame = new ReaderSelectandUpdate();
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
	public ReaderSelectandUpdate() {
		setTitle("������Ϣ��ѯ���޸�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel updateJP = new JPanel();
		contentPane.add(updateJP, BorderLayout.CENTER);
		updateJP.setPreferredSize(new Dimension(0, 100));
		updateJP.setLayout(new GridLayout(4, 4, 2, 1));
		
		JLabel IDJL = new JLabel("��ţ�");
		updateJP.add(IDJL);
		
		IDJTF = new JTextField();
		updateJP.add(IDJTF);
		IDJTF.setColumns(10);
		
		JLabel nameJL = new JLabel("������");
		updateJP.add(nameJL);
		
		nameJTF = new JTextField();
		updateJP.add(nameJTF);
		nameJTF.setColumns(10);
		
		JLabel typeJL = new JLabel("���");
		updateJP.add(typeJL);
		
		 typeJCB = new JComboBox();
		//�����ݿ�ȡ���������ͼ��������б�
				List<ReaderType> list =ReaderTypeDao.selectReaderType();
				for(int i=0;i<list.size();i++)
				{
					ReaderType rt = list.get(i);
					typeJCB.addItem(rt.getName());
				}
		updateJP.add(typeJCB);
		
		JLabel sexJL = new JLabel("�Ա�");
		updateJP.add(sexJL);
		
		JPanel sexJP = new JPanel();
		updateJP.add(sexJP);
		sexJP.setLayout(new GridLayout(1, 2, 0, 0));
		
		JRB1 = new JRadioButton("��");
		sexJP.add(JRB1);
		
		JRB2 = new JRadioButton("Ů");
		sexJP.add(JRB2);
		buttonGroup.add(JRB1);
		buttonGroup.add(JRB2);
		
		JLabel ageJL = new JLabel("���䣺");
		updateJP.add(ageJL);
		
		ageJTF = new JTextField();
		updateJP.add(ageJTF);
		ageJTF.setColumns(10);
		
		JLabel phoneJL = new JLabel("�绰��");
		updateJP.add(phoneJL);
		
		phoneJTF = new JTextField();
		updateJP.add(phoneJTF);
		phoneJTF.setColumns(10);
		
		JLabel deptJL = new JLabel("���ڲ��ţ�");
		updateJP.add(deptJL);
		
		deptJTF = new JTextField();
		updateJP.add(deptJTF);
		deptJTF.setColumns(10);
		
		JLabel regtimeJL = new JLabel("ע�����ڣ�");
		updateJP.add(regtimeJL);
		
		regtimeJTF = new JTextField();
		updateJP.add(regtimeJTF);
		regtimeJTF.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		
		JPanel selectJP = new JPanel();
		contentPane.add(selectJP, BorderLayout.NORTH);
		selectJP.setPreferredSize(new Dimension(0, 250));
		selectJP.setLayout(new BorderLayout(0, 0));
		
		JPanel seclct_conditionJP = new JPanel();
		selectJP.add(seclct_conditionJP, BorderLayout.NORTH);
		
		conditionJCB = new JComboBox();
		conditionJCB.setModel(new DefaultComboBoxModel(new String[] {"���߱��", "����", "����", "ϵ��"}));
		seclct_conditionJP.add(conditionJCB);
		
		conditionJTF = new JTextField();
		seclct_conditionJP.add(conditionJTF);
		conditionJTF.setColumns(20);		
		
		JPanel select_resultJP = new JPanel();
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		select_resultJP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		jscrollPane = new JScrollPane();
		select_resultJP.add(jscrollPane);
		jscrollPane.setPreferredSize(new Dimension(400, 205));
		
		//������Ϣ�����ά��������,���˴������
		Object[][] result = getSelect(ReaderDao.selectReader());
		//���з��������Ϣ��readersearch ��Ϊ��ͷ��result ��Ϊ�������
		jtable = new JTable(result,readersearch);
		class TableListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e) {
				int selRow = jtable.getSelectedRow();
				IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
				typeJCB.setSelectedItem(jtable.getValueAt(selRow, 1).toString().trim());
				nameJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
				ageJTF.setText(jtable.getValueAt(selRow, 3).toString().trim());
				if(jtable.getValueAt(selRow, 4).toString().trim().equals("��"))
					JRB1.setSelected(true);
				else
					JRB2.setSelected(true);
				phoneJTF.setText(jtable.getValueAt(selRow, 5).toString().trim());
				deptJTF.setText(jtable.getValueAt(selRow, 6).toString().trim());
				regtimeJTF.setText(jtable.getValueAt(selRow, 7).toString().trim());
			}
		}
		jtable.addMouseListener(new TableListener());
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);
		
		JButton selectJB = new JButton("��ѯ");
		selectJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����û�ѡ��Ĳ�ѯ����
				String condition =(String) conditionJCB.getSelectedItem();
				//�����߱�Ų�ѯ
				if(condition.equals("���߱��"))
				{
					Object[][] results =getSelect(ReaderDao.selectReaderById(conditionJTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
					jtable.addMouseListener(new TableListener());
				}
				else if(condition.equals("����"))
				{
					Object[][] results =getSelect(ReaderDao.selectReaderByName(conditionJTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtable.addMouseListener(new TableListener());
				}
				else if(condition.equals("����"))
				{
					Object[][] results =getSelect(ReaderDao.selectReaderByType(conditionJTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtable.addMouseListener(new TableListener());
				}
				else if(condition.equals("ϵ��"))
				{
					Object[][] results =getSelect(ReaderDao.selectReaderByDept(conditionJTF.getText().trim()));	
					jtable = new JTable(results,readersearch);
					jscrollPane.setViewportView(jtable);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtable.addMouseListener(new TableListener());
				}
			}
		});
		seclct_conditionJP.add(selectJB);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton updateJB = new JButton("�޸�");
		updateJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IDJTF.getText().trim();
				String type = (String) typeJCB.getSelectedItem();
				String name = nameJTF.getText().trim();
				int age = Integer.parseInt(ageJTF.getText().trim());
				String sex = "��";
				if(!JRB1.isSelected())
					sex = "Ů";
				String phone = phoneJTF.getText().trim();
				String dept = deptJTF.getText().trim();
				String regdate = regtimeJTF.getText().trim();
				int i =ReaderDao.updateReader(id, type, name, age, sex, phone, dept, regdate);
				if(i==1)
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				
				Object[][] results = getSelect(ReaderDao.selectReader());
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		});
		buttonJP.add(updateJB);
		
		JButton closeJB = new JButton("�ر�");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(closeJB);
	}

	//readersearch ��Ϊ��ͷ
	String[] readersearch ={"���","����","����","����","�Ա�","�绰","ϵ��","ע������"};
	//���ص�result ��Ϊ�������
	Object[][] getSelect(List<Reader> list)
	{
		Object [][] result = new Object [list.size()][readersearch.length];
		for(int i=0;i<list.size();i++)
		{
			Reader reader = list.get(i);
			result[i][0]=reader.getReaderid();
			result[i][1]=reader.getTypename();
			result[i][2]=reader.getName();
			result[i][3]=reader.getAge();
			result[i][4]=reader.getSex();
			result[i][5]=reader.getPhone();
			result[i][6]=reader.getDept();
			result[i][7]=reader.getRegdate();
		}
		return result;
	}
}
