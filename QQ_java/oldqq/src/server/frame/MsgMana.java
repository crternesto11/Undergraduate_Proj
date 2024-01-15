package server.frame;


import java.awt.*;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.MyTools;
import server.common.PageService;
import server.dao.MsgDao;

import server.entity.Msg;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MsgMana extends JPanel {
	public JTable table;
	public DefaultTableModel model;
	private Vector<String> columnNames = new Vector<String>();
	private JLabel lblNum;
	public JLabel lblCurrent;
	public MsgActionListener msgActionListener;
	public MsgMana()
	{

		JScrollPane scrollPane = new JScrollPane();
		columnNames.add("消息ID");
		columnNames.add("消息内容");
		columnNames.add("消息发送者ID");
		columnNames.add("消息接收者ID");
		columnNames.add("消息发送时间");
		columnNames.add("消息备注");
		columnNames.add("消息类型");
		model = new DefaultTableModel(columnNames, 0);

		table = new JTable(model)
		{
			Boolean[] canEdit = new Boolean[] { false, true, true, true, true,
					true, true };

			@Override
			public boolean isCellEditable(int row, int column)
			{
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		scrollPane.setViewportView(table);

		lblNum = new JLabel("共0页/");

		lblCurrent = new JLabel("当前在第1页");

		JButton btnFirst = new JButton("首页");

		JButton btnPre = new JButton("上一页");

		JButton btnNext = new JButton("下一页");

		JButton btnLast = new JButton("尾页");

		JButton btnDelete = new JButton("删除");

		JButton btnReresh = new JButton("刷新");


		// 为每个按钮注册监听器
		msgActionListener = new MsgActionListener(this);
		btnFirst.addActionListener(msgActionListener);
		btnPre.addActionListener(msgActionListener);
		btnNext.addActionListener(msgActionListener);
		btnLast.addActionListener(msgActionListener);
		btnDelete.addActionListener(msgActionListener);
		btnReresh.addActionListener(msgActionListener);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(btnFirst, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnPre)
					.addGap(10)
					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnReresh, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(250)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNum, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
						.addGap(46)
						.addComponent(lblCurrent, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(295, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
//					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addComponent(lblNum)
//						.addComponent(lblCurrent))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnFirst))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnPre))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNext))
						.addComponent(btnLast)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnReresh)
							.addComponent(btnDelete))))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNum)
							.addComponent(lblCurrent))
					.addGap(10))
		);
		setLayout(groupLayout);

		// 将数据添加到表格中

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
	{// 先移出表格监听器
		// 得到总行数
		int sumRow = model.getRowCount();
		// 清空表格
		for (int i = 0; i < sumRow; i++)
		{
			model.removeRow(0);
		}

		MsgDao msgDao = new MsgDao();
		String sql = "select * from msg";
		List<Msg> list = msgDao.selectMsgs(sql);
		PageService service = new PageService(list);
		List listTemp = service.gotoFirst();
		lblNum.setText("共" + service.getTotalPage() + "页/");
		lblCurrent.setText("当前在第" + (service.getCurrentPage() + 1) + "页");
		for (int i = 0; i < listTemp.size(); i++)
		{
			Msg msg = (Msg) listTemp.get(i);
			Object[] rowData = new Object[] { msg.getMsgId(),
					msg.getMsgContent(), msg.getSendFrom(), msg.getSendTo(),
					msg.getSendTime(), msg.getRemark(), msg.getMsgTye() };
			model.addRow(rowData);
		}
		return list;
	}
}
