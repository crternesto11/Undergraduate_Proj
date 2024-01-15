package server.frame;

import java.awt.*;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.MyTools;
import server.common.PageService;
import server.dao.UserDao;

import server.entity.Users;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserMana extends JPanel {
	JScrollPane scrollpane = new JScrollPane();
	Vector<String> columnNames = new Vector<String>();
	public DefaultTableModel model;
	public JTable table;
	public JLabel lblNum;
	public JLabel lblCurrent;
	
	public UserTableCahnged userTableCahnged;

	JButton btnFirst = new JButton("首页");
	JButton btnNext = new JButton("下一页");
	JButton btnPre = new JButton("上一页");
	JButton btnLast = new JButton("尾页");
	JButton btnDelete = new JButton("删除");
	private final JButton btnUpdate = new JButton("刷新");
	public JTextField txtId;
	public JTextField txtName;
	public UserMana()
	{
		columnNames.add("用户ID");
		columnNames.add("用户名");
		columnNames.add("密码");
		columnNames.add("IP");
		columnNames.add("状态");
		columnNames.add("性别");
		columnNames.add("Email");
		columnNames.add("最近上线时间");
		columnNames.add("最后下线时间");
		columnNames.add("生日");
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model)
		{
			boolean[] canEdit = new boolean[] { false, true, true, false, true,
					true, true, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}

		};
		table.getAutoscrolls();
		scrollpane.setViewportView(table);
		
		UserActionListen userActionListen=new UserActionListen(this);
		userTableCahnged=new UserTableCahnged(table);
		
		model.addTableModelListener(userTableCahnged);
		btnFirst.addActionListener(userActionListen);
		btnPre.addActionListener(userActionListen);
		btnNext.addActionListener(userActionListen);
		btnLast.addActionListener(userActionListen);
		lblNum=new JLabel();
		lblCurrent=new JLabel();
		btnDelete.addActionListener(userActionListen);
		btnUpdate.addActionListener(userActionListen);
		
		JLabel lblid = new JLabel("按用户ID查找：");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		JLabel label = new JLabel("按用户名查找：");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JButton btnSearch1 = new JButton("查找一");
		JButton btnSearch2 = new JButton("查找二");
		btnSearch1.addActionListener(userActionListen);
		btnSearch2.addActionListener(userActionListen);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
					//按ID查找+查找一按钮
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblid)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(40)
					.addComponent(btnSearch1)
				)
				//按名字查找+查找二按钮
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(23)
						.addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addGap(40)
						.addComponent(btnSearch2))
				//中间部分滚动框
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
					.addGap(20)
				)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(260, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblCurrent, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblNum, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(260))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(btnFirst)
					.addGap(5)
					.addComponent(btnPre)
					.addGap(5)
					.addComponent(btnNext)
					.addGap(5)
					.addComponent(btnLast)
					.addGap(160)
					.addComponent(btnDelete)
					.addGap(5)
					.addComponent(btnUpdate)
					.addContainerGap(99, Short.MAX_VALUE))

		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					//距离最顶部位置
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch1)
//						.addComponent(label)
//						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch2))
					.addGap(25)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFirst)
						.addComponent(btnPre)
						.addComponent(btnNext)
						.addComponent(btnLast)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnUpdate)
							.addComponent(btnDelete)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblCurrent)
							.addComponent(lblNum))
					.addGap(20))
		);
		setLayout(groupLayout);
		refresh();
	}

	//设置窗体的背景图片
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);//这句话有时候必须写，有时候可以不写
		g.drawImage(MyTools.getIcon("../img/registerBGimg3.jpg").getImage(), 0, 0, null);
	}

	public List refresh()
	{
		model.removeTableModelListener(userTableCahnged);
		int rowCount = model.getRowCount(); // 将表格清空
		for (int i = 0; i < rowCount; i++)
			model.removeRow(0);
		UserDao userDao=new UserDao();
		List<Users> list=userDao.queryAll();
		PageService pageService=new PageService(list);
		List<Users> listTemp=pageService.gotoFirst();
		lblNum.setText("共"+pageService.getTotalPage()+"页/");
		lblCurrent.setText("当前在第"+(pageService.getCurrentPage()+1)+"页");
		for (Users user : listTemp)
		{
			Object[] rowData =
			{ user.getId(), user.getName(), user.getPwd(),user.getIp(),user.getState(),user.getGender(), user.getEmail(),
					user.getLastLogin(),user.getLastExit(), user.getBirthday()};
			model.addRow(rowData);
		}	
		model.addTableModelListener(userTableCahnged);
		return list;
	}
}
