package client.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 */
public class MainFrame extends JFrame
{
	public JLabel main_set;//设置
	public JLabel main_head;//头像
	public JComboBox main_state;//状态
	public JLabel main_username;//用户名
	public JLabel main_slogan;//个性签名
	public JTree tree;
	private JTabbedPane tabbedPane;
	private JPanel main_friendList;//好友面板
	private JPanel main_group;//群
	private JScrollPane main_friend;//好友
	private JPanel main_userInfo;//用户信息
	private JPanel main_friendPanel;//好友面板
	private JPanel main_setPanel;//设置面板
	private JPopupMenu main_friendMenu;//好友列表
	private JMenuItem main_sendSoon;//发送即时消息
	private JMenuItem main_checkinfo;//查看好友信息
	private JMenuItem main_delFriend;//删除用户


	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/client/img/QQ_64.png")));
		setTitle("QQ2013");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 600);
		
		main_userInfo = new JPanel();
		main_userInfo.setBackground(new Color(22, 176, 239));
		
		main_friendPanel = new JPanel();
		
		main_setPanel = new JPanel();
		main_setPanel.setBackground(new Color(22, 176, 239));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(main_userInfo, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
				.addComponent(main_setPanel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
				.addComponent(main_friendPanel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(main_userInfo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(main_friendPanel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(main_setPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		);
		main_friendPanel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		main_friendPanel.add(tabbedPane, BorderLayout.CENTER);
		
		main_friendList = new JPanel();
		tabbedPane.addTab("好友", new ImageIcon(MainFrame.class.getResource("/client/img/friend_list.png")), main_friendList, null);
		main_friendList.setLayout(new BorderLayout(0, 0));
		
		main_friend = new JScrollPane();
		main_friendList.add(main_friend);
		
		tree = new JTree();
		main_friend.setViewportView(tree);
		
		main_friendMenu = new JPopupMenu();
		addPopup(tree, main_friendMenu);
		
		main_sendSoon = new JMenuItem("发送即时消息");
		main_sendSoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				startChat(e);
			}
		});
		main_sendSoon.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/QQ_16.png")));
		main_friendMenu.add(main_sendSoon);
		
//		menuItem发送文件 = new JMenuItem("发送文件");
//		menuItem发送文件.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				sendFile(e);
//			}
//		});
//		main_friendMenu.add(menuItem发送文件);
		
		main_checkinfo = new JMenuItem("查看好友资料");
		main_checkinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				getFriendInfo(e);
			}
		});
		main_friendMenu.add(main_checkinfo);
		
		main_delFriend = new JMenuItem("删除好友");
		main_delFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				deleteFriend(e);
			}
		});
		main_friendMenu.add(main_delFriend);

		main_group = new JPanel();
		tabbedPane.addTab("群组", new ImageIcon(MainFrame.class.getResource("/client/img/friend_qun.png")),main_group, null);
		JButton btn官方聊天室 = new JButton("进入群聊");
		ImageIcon chat_icon = new ImageIcon(MainFrame.class.getResource("/client/img/face/f109.png"));
		btn官方聊天室.setIcon(chat_icon);
		btn官方聊天室.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				gotoChatRoom();
			}
		});
		btn官方聊天室.setFont(new Font("微软雅黑", Font.BOLD, 28));

		GroupLayout gl_main_group = new GroupLayout(main_group);
		gl_main_group.setHorizontalGroup(
			gl_main_group.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_main_group.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_main_group.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn官方聊天室, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_main_group.setVerticalGroup(
			gl_main_group.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_group.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn官方聊天室, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		main_group.setLayout(gl_main_group);
		
		main_set = new JLabel("");
		main_set.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/button/QQ_settings_1.png")));
		main_set.setBounds(6, 6, 64, 64);
		main_setPanel.add(main_set);
		main_userInfo.setLayout(null);
		
		main_head = new JLabel("");
		main_head.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/headImage/head_boy_01_64.jpg")));
		main_head.setBounds(15, 15, 64, 64);
		main_userInfo.add(main_head);
		
		main_state = new JComboBox();
		main_state.setBounds(91, 15, 58, 28);
		main_userInfo.add(main_state);
		
		main_username = new JLabel("胡图图");
		main_username.setFont(new Font("黑体", Font.BOLD, 18));
		main_username.setBounds(159, 18, 90, 25);
		main_userInfo.add(main_username);
		
		main_slogan = new JLabel("大家好，我叫胡图图");
		main_slogan.setBounds(91, 55, 210, 18);
		main_userInfo.add(main_slogan);
		getContentPane().setLayout(groupLayout);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	/**
	 * 开始聊天
	 */
	public void startChat(ActionEvent e)
	{
	}
	/**
	 * 获取好友资料
	 * @param e
	 */
	public void getFriendInfo(ActionEvent e)
	{
		
	}
//	/**
//	 * 发送文件
//	 * @param e
//	 */
//	public void sendFile(ActionEvent e)
//	{
//
//	}
	/**
	 * 删除好友
	 * @param e
	 */
	public void deleteFriend(ActionEvent e)
	{
		
	}
	/**
	 * 进入聊天室
	 */
	public void gotoChatRoom()
	{}
	
	/**
	 * 新建聊天室
	 */
	public void buildNewChatRoom()
	{
		
	}
}
