package com.bbm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm.db.UserDao;
import com.bbm.model.Users;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserDel extends JFrame {

	private JPanel contentPane;
	private JTable jtable;
	private JScrollPane jscrollPane;
	private String name;
	private String password ;
	private int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDel frame = new UserDel();
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
	public UserDel() {
		setTitle("删除用户");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonJP = new JPanel();
		contentPane.add(buttonJP, BorderLayout.SOUTH);
		
		class TableListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e) {
				int selRow = jtable.getSelectedRow();
				id = Integer.parseInt(jtable.getValueAt(selRow, 0).toString().trim());
				name =jtable.getValueAt(selRow, 1).toString().trim();
				password = jtable.getValueAt(selRow, 2).toString().trim();
			}
		}
		
		JButton delJB = new JButton("删除");
		delJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = jtable.getSelectedRow();
			
				id = Integer.parseInt(jtable.getValueAt(selRow, 0).toString().trim());
				int i = UserDao.deleteUser(id);
				if(i==1)
				{
					JOptionPane.showMessageDialog(null, "删除成功");
				}
				
				Object[][] result = getSelect(UserDao.selectUser());
				//表中放入读者信息，readersearch 作为表头，result 作为表的数据
				jtable = new JTable(result,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new TableListener());
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			}
		});
		buttonJP.add(delJB);
		
		JButton exitJB = new JButton("退出");
		exitJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonJP.add(exitJB);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		
		jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 10, 454, 148);
		panel.add(jscrollPane);
		
		Object[][] result = getSelect(UserDao.selectUser());
		//表中放入读者信息，readersearch 作为表头，result 作为表的数据
		jtable = new JTable(result,readersearch);
		jscrollPane.setViewportView(jtable);
		jtable.addMouseListener(new TableListener());
	}
	String[] readersearch ={"用户编号","用户名","密码"};
	//返回的result 作为表的数据
	Object[][] getSelect(List<Users> list)
	{
		Object [][] result = new Object [list.size()][readersearch.length];
		for(int i=0;i<list.size();i++)
		{
			Users users = list.get(i);
			result[i][0]=users.getId();
			result[i][1]=users.getName();
			result[i][2]=users.getPassword();	
		}
		return result;
	}
}
