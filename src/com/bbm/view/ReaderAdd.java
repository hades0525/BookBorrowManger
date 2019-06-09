package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.ButtonGroup;

import com.bbm.db.ReaderDao;
import com.bbm.db.ReaderTypeDao;
import com.bbm.model.ReaderType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ReaderAdd extends JFrame {

	private JPanel contentPane;
	private JTextField IDJTF;
	private JTextField nameJTF;
	private JTextField ageJTF;
	private JTextField phoneJTF;
	private JTextField deptJTF;
	private JTextField regtimeJTF;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1;
	private JRadioButton JRB2;
	private JComboBox typeJCB;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderAdd frame = new ReaderAdd();
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
	public ReaderAdd() {
		setResizable(false);
		setTitle("������Ϣ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel readerAddJP = new JPanel();
		contentPane.add(readerAddJP, BorderLayout.CENTER);
		readerAddJP.setLayout(new GridLayout(4, 4, 2, 1));
		
		JLabel IDJL = new JLabel("��ţ�");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		IDJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(IDJL);
		
		IDJTF = new JTextField();
		IDJTF.setColumns(15);
		readerAddJP.add(IDJTF);
		
		JLabel nameJL = new JLabel("������");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(nameJL);
		
		nameJTF = new JTextField();
		nameJTF.setColumns(10);
		readerAddJP.add(nameJTF);
		
		JLabel typeJL = new JLabel("���");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		typeJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(typeJL);
		
		typeJCB = new JComboBox();
		//�����ݿ�ȡ���������ͼ��������б�
		List<ReaderType> list =ReaderTypeDao.selectReaderType();
		for(int i=0;i<list.size();i++)
		{
			ReaderType rt = list.get(i);
			typeJCB.addItem(rt.getName());
		}
		readerAddJP.add(typeJCB);
		
		JLabel sexJL = new JLabel("�Ա�");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		sexJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(sexJL);
		
		JPanel sexJP = new JPanel();
		readerAddJP.add(sexJP);
		sexJP.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRB1 = new JRadioButton("��");
		sexJP.add(JRB1);
		
		JRB2 = new JRadioButton("Ů");
		sexJP.add(JRB2);
		buttonGroup.add(JRB1);
		buttonGroup.add(JRB2);
		
		
		
				
		JLabel ageJL = new JLabel("���䣺");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		ageJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(ageJL);
		
		ageJTF = new JTextField();
		ageJTF.setColumns(10);
		readerAddJP.add(ageJTF);
		
		JLabel phoneJL = new JLabel("�绰��");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		phoneJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(phoneJL);
		
		phoneJTF = new JTextField();
		phoneJTF.setText("");
		phoneJTF.setColumns(15);
		readerAddJP.add(phoneJTF);
		
		JLabel deptJL = new JLabel("���ڲ��ţ�");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		deptJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(deptJL);
		
		deptJTF = new JTextField();
		deptJTF.setColumns(15);
		readerAddJP.add(deptJTF);
		
		JLabel regtimeJL = new JLabel("ע�����ڣ�");
		regtimeJL.setHorizontalAlignment(SwingConstants.CENTER);
		regtimeJL.setFont(new Font("����", Font.BOLD, 12));
		readerAddJP.add(regtimeJL);
		
		regtimeJTF = new JTextField();
		regtimeJTF.setEditable(false);
		regtimeJTF.setColumns(10);
		readerAddJP.add(regtimeJTF);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		JButton addJB = new JButton("���");
		addJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IDJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "���߱�Ų�����Ϊ��");
					return;
				}
				if(IDJTF.getText().length() != 8)
				{
					JOptionPane.showMessageDialog(null, "���߱��λ��Ϊ8λ");
					return;
				}
				if(nameJTF.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "��������������Ϊ��");
					return;
				}
				String ID = IDJTF.getText().trim();
				String readertype =(String) typeJCB.getSelectedItem();
				String name = nameJTF.getText().trim();
				//age string תΪint
				int age = Integer.parseInt(ageJTF.getText().trim());
				String sex = "Ů";
				if(JRB1.isSelected())
				{
					sex = "��";
				}
				
				String phone = phoneJTF.getText().trim();
				String dept = deptJTF.getText().trim();
				String regdate = regtimeJTF.getText().trim();
				int i =ReaderDao.insertReader(ID, readertype, name,age, sex, phone, dept, regdate);
				if(i == 1)
				{
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					ReaderAdd.this.setVisible(false);
				}
			}
		});
		buttonJP.add(addJB);
		
		JButton closeJB = new JButton("�ر�");
		closeJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(closeJB);
	}

}
