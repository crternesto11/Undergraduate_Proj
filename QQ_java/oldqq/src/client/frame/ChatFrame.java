package client.frame;

import java.awt.Color;
import javax.swing.UIManager;
import client.common.MyLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


public class ChatFrame extends javax.swing.JFrame
{

	//创建新的聊天窗体
	public ChatFrame()
	{
		initComponents();
	}

	private void initComponents()
	{

		chat_rightPanel = new javax.swing.JPanel();
		chat_qqshowU = new javax.swing.JLabel();
		chat_qqshowM = new javax.swing.JLabel();
		chat_leftPanel = new javax.swing.JPanel();
		chat_topPanel = new javax.swing.JPanel();
		chat_headImage = new javax.swing.JLabel();
		chat_qqZone = new javax.swing.JLabel();
		chat_friend = new javax.swing.JLabel();
		chat_sendFile = new javax.swing.JLabel();
		chat_chatPanel = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		chat_recPane = new javax.swing.JTextPane();
		chat_sendPanel = new javax.swing.JPanel();
		chat_send = new javax.swing.JButton();
		chat_close = new javax.swing.JButton();
		javax.swing.JPanel chat_toolPanel = new javax.swing.JPanel();//工具面板
		typeface= new javax.swing.JLabel();
		chat_face = new javax.swing.JLabel();
		chat_face.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectFace();
			}
		});
		chat_picture = new javax.swing.JLabel();
		chat_picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				sendImg();
			}
		});
		chat_cut = new javax.swing.JLabel();
		chat_cut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				screenFram();
			}
		});
		chat_log = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		chat_sendPane = new javax.swing.JTextPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("聊天");
		setBackground(new java.awt.Color(204, 255, 255));
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				formWindowClosing(evt);
			}
		});

		chat_rightPanel.setBackground(new java.awt.Color(204, 255, 255));

		chat_qqshowU.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/qqshow/qqshow_girl_02.jpg"))); // NOI18N

		chat_qqshowM.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/qqshow/qqshow_boy_01.jpg"))); // NOI18N

		javax.swing.GroupLayout chat_rightPanelLayout = new javax.swing.GroupLayout(
				chat_rightPanel);
		chat_rightPanel.setLayout(chat_rightPanelLayout);
		chat_rightPanelLayout.setHorizontalGroup(chat_rightPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chat_qqshowU).addComponent(chat_qqshowM));
		chat_rightPanelLayout.setVerticalGroup(chat_rightPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				chat_rightPanelLayout
						.createSequentialGroup()
						.addComponent(chat_qqshowU)
						.addGap(18, 18, 18)
						.addComponent(chat_qqshowM,
								javax.swing.GroupLayout.PREFERRED_SIZE, 251,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		chat_leftPanel.setBackground(new java.awt.Color(204, 255, 255));

		chat_topPanel.setBackground(new java.awt.Color(204, 255, 255));

		chat_headImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/QQ_64.png"))); // NOI18N



		chat_friend.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_myfeeds_54.png"))); // NOI18N


		chat_sendFile.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_sendfile_54.png"))); // NOI18N
		chat_sendFile.setToolTipText("发送文件");
		chat_sendFile.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				chat_sendFileMouseClicked(evt);
			}
		});

		chat_qqZone.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_qzone_54.png"))); // NOI18N
		chat_qqZone.addMouseListener (new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {//仅当鼠标单击时响应
				chat_enterMouseClicked(evt);
			}

		});

		javax.swing.GroupLayout chat_topPanelLayout = new javax.swing.GroupLayout(
				chat_topPanel);
		chat_topPanel.setLayout(chat_topPanelLayout);
		chat_topPanelLayout
				.setHorizontalGroup(chat_topPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								chat_topPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(chat_headImage)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(chat_sendFile)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(chat_qqZone)
										.addGap(13, 13, 13)
										.addComponent(chat_friend)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												23, Short.MAX_VALUE)
										.addContainerGap()));
		chat_topPanelLayout
				.setVerticalGroup(chat_topPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								chat_topPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												chat_topPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(chat_headImage)
														.addGroup(
																chat_topPanelLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				chat_sendFile)
																		.addComponent(
																				chat_qqZone)
																		.addComponent(
																				chat_friend)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		chat_chatPanel.setLayout(new java.awt.BorderLayout());

		chat_recPane.setEditable(false);
		jScrollPane1.setViewportView(chat_recPane);

		chat_chatPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		chat_sendPanel.setBackground(new java.awt.Color(204, 255, 255));

		chat_send.setText("发送");
		chat_send.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				chat_sendActionPerformed(evt);
			}
		});

		chat_close.setText("关闭");

		chat_toolPanel.setBackground(new java.awt.Color(204, 255, 255));

		typeface.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_font_32.png"))); // NOI18N
		typeface.setToolTipText("字体");
		typeface.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				new java.awt.Color(204, 255, 255), new java.awt.Color(204, 255,
						255)));

		chat_face.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_face_32.png"))); // NOI18N
		chat_face.setToolTipText("表情");

		chat_picture.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_picture_32.png"))); // NOI18N
		chat_picture.setToolTipText("发送图片");
		chat_cut.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_snap_32.png"))); // NOI18N
		chat_cut.setToolTipText("截图");

		chat_log.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/client/img/chat/fun_message_history_32.png"))); // NOI18N
		chat_log.setText("聊天记录");
		chat_log
				.setToolTipText("打开我的聊天记录");

		javax.swing.GroupLayout chat_toolPanelLayout = new javax.swing.GroupLayout(
				chat_toolPanel);
		chat_toolPanel.setLayout(chat_toolPanelLayout);
		chat_toolPanelLayout
				.setHorizontalGroup(chat_toolPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								chat_toolPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												typeface,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(chat_face)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(chat_picture)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(chat_cut)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												193, Short.MAX_VALUE)
										.addComponent(chat_log)));
		chat_toolPanelLayout
				.setVerticalGroup(chat_toolPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								chat_toolPanelLayout
										.createSequentialGroup()
										.addGroup(
												chat_toolPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(typeface)
														.addComponent(chat_face)
														.addComponent(chat_picture)
														.addComponent(chat_cut)
														.addComponent(
																chat_log))
										.addContainerGap()));

		jScrollPane3.setViewportView(chat_sendPane);

		javax.swing.GroupLayout chat_sendPanelLayout = new javax.swing.GroupLayout(
				chat_sendPanel);
		chat_sendPanel.setLayout(chat_sendPanelLayout);
		chat_sendPanelLayout
				.setHorizontalGroup(chat_sendPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(chat_toolPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								chat_sendPanelLayout
										.createSequentialGroup()
										.addContainerGap(321, Short.MAX_VALUE)
										.addComponent(
												chat_close,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												chat_send,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												77,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5))
						.addComponent(jScrollPane3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 486,
								Short.MAX_VALUE));
		chat_sendPanelLayout
				.setVerticalGroup(chat_sendPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								chat_sendPanelLayout
										.createSequentialGroup()
										.addComponent(
												chat_toolPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												96,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)
										.addGroup(
												chat_sendPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																chat_send,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE)
														.addComponent(
																chat_close,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE))
										.addContainerGap()));

		javax.swing.GroupLayout chat_leftPanelLayout = new javax.swing.GroupLayout(
				chat_leftPanel);
		chat_leftPanel.setLayout(chat_leftPanelLayout);
		chat_leftPanelLayout.setHorizontalGroup(chat_leftPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chat_topPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chat_sendPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chat_chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						486, Short.MAX_VALUE));
		chat_leftPanelLayout
				.setVerticalGroup(chat_leftPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								chat_leftPanelLayout
										.createSequentialGroup()
										.addComponent(
												chat_topPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												89,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												chat_chatPanel,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												260, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												chat_sendPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(chat_leftPanel,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(chat_rightPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chat_rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chat_leftPanel,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}


	private void formWindowClosing(java.awt.event.WindowEvent evt)
	{
		// TODO add your handling code here:
		beforeClose();
	}

	private void chat_sendFileMouseClicked(java.awt.event.MouseEvent evt)
	{
		// TODO add your handling code here:
		sendFile();
	}
	private void chat_enterMouseClicked(java.awt.event.MouseEvent evt)
	{
		// TODO add your handling code here:
		enterSpace();
	}

	private void chat_sendActionPerformed(java.awt.event.ActionEvent evt)
	{
		// TODO add your handling code here:
		sendMessage();
	}


	private javax.swing.JButton chat_close;//关闭按钮
	public javax.swing.JButton chat_send;//发送按钮
	private javax.swing.JLabel chat_qqshowU;//对方qq秀
	private javax.swing.JLabel chat_qqshowM;//自己qq秀
	public javax.swing.JLabel chat_qqZone;//qq空间
	public javax.swing.JLabel chat_sendFile;//发送文件
	public javax.swing.JLabel chat_picture;//发送图片
	private javax.swing.JLabel chat_headImage;//头像
	public javax.swing.JLabel typeface;//字体
	public javax.swing.JLabel chat_friend;//添加好友
	public javax.swing.JLabel chat_cut;//截图
	public javax.swing.JLabel chat_log;//聊天记录
	public javax.swing.JLabel chat_face;//表情
	private javax.swing.JPanel chat_sendPanel;//发送面板
	private javax.swing.JPanel chat_rightPanel;//右侧面板
	private javax.swing.JPanel chat_leftPanel;//左侧面板
	private javax.swing.JPanel chat_chatPanel;//聊天面板
	private javax.swing.JPanel chat_topPanel;//顶部面板
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	public javax.swing.JTextPane chat_sendPane;//发送框
	public javax.swing.JTextPane chat_recPane;//接收框

	public javax.swing.JTextPane getChatLog()
	{
		return chat_recPane;
	}

	public void setchatLogPane(javax.swing.JTextPane chatLogPane)
	{
		this.chat_recPane = chatLogPane;
	}

	/**
	 * 发送消息，空的方法，由子类实现
	 */
	public void sendMessage()
	{
	}

	public void beforeClose(){}
	/**
	 * 选择表情
	 */
	public void selectFace(){}
	/**
	 * 处理消息中的表情
	 */
	public void dealIcon(String str)
	{
		
	}
	/**
	 * 发送文件
	 */
	public void sendFile()
	{
		
	}
	public void enterSpace()
	{
		System.out.println("你好，点击跳转");
		try {
			Runtime.getRuntime().exec("cmd /c start http://localhost:8080");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送图片
	 */
	public void sendImg()
	{
		
	}


	
	public void screenFram()
	{
		
	}

}