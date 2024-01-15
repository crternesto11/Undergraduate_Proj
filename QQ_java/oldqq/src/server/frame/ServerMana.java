package server.frame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import server.QQServer;

import client.frame.MyPanel;

import com.MyTools;

import java.awt.*;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;


public class ServerMana extends JPanel implements Runnable
{
	QQServer qqServer=null;//QQ服务器
	
	
	JButton btnStart;
	JLabel lblTime;
	Calendar calendar=Calendar.getInstance();
	private JLabel currentTime;
	private JLabel label_1;
	private JLabel lblRun;
	private JLabel lblStateShow;
	public JLabel lblState;
	public int allTime=0;
	public long startTime=0;
	public long endTime=0;
	public boolean isRun=false;
	public JTextArea textArea公告;
	private JPanel panel;
	private JTextArea textArea弹窗;
	private JButton btn弹窗;
	
	public ServerMana() 
	{
		setLayout(null);
		btnStart = new JButton("启动服务器");
		btnStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				startOrCloseServer();
			}
		});
		btnStart.setFont(new Font("微软雅黑", Font.BOLD, 24));
		btnStart.setBounds(420, 40, 150, 60);
		btnStart.setBackground(Color.lightGray);
		add(btnStart);


		JLabel label = new JLabel("当前服务器时间：");
		label.setFont(new Font("宋体",Font.BOLD,14));
		label.setBounds(29, 100, 130, 15);
		add(label);
		
		currentTime = new JLabel("");
		currentTime.setBounds(150, 100, 192, 15);
		currentTime.setFont(new Font("宋体",Font.BOLD,14));
		add(currentTime);
		
		label_1 = new JLabel("服务器运行时间：");
		label_1.setBounds(29, 60, 130, 15);
		label_1.setFont(new Font("宋体",Font.BOLD,14));
		add(label_1);
		
		lblRun = new JLabel("");
		lblRun.setBounds(150, 60, 192, 15);
		lblRun.setFont(new Font("宋体",Font.BOLD,14));
		add(lblRun);
		
		lblStateShow = new JLabel("当前服务器状态：");
		lblStateShow.setBounds(28, 20, 130, 15);
		lblStateShow.setFont(new Font("宋体",Font.BOLD,14));
		add(lblStateShow);
		
		lblState = new JLabel("服务器未运行");
		lblState.setBounds(150, 20, 135, 15);
		lblState.setFont(new Font("宋体",Font.BOLD,14));
		add(lblState);
		
		JPanel panel公告 = new JPanel();
		panel公告.setBorder(new TitledBorder(null, "发布聊天室公告", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel公告.setBounds(29, 231, 289, 240);
//		add(panel公告);
		
		textArea公告 = new JTextArea();
		
		JButton btn发布公告 = new JButton("发布聊天室公告");
		btn发布公告.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sendPublicMessage();
			}
		});
		GroupLayout gl_panel公告 = new GroupLayout(panel公告);
		gl_panel公告.setHorizontalGroup(
			gl_panel公告.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea公告, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel公告.createSequentialGroup()
					.addContainerGap(63, Short.MAX_VALUE)
					.addComponent(btn发布公告, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		gl_panel公告.setVerticalGroup(
			gl_panel公告.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel公告.createSequentialGroup()
					.addComponent(textArea公告, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn发布公告, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		//聊天室窗口
//		panel公告.setLayout(gl_panel公告);

		//弹窗公告Start
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "发布弹窗公告", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 160, 550, 300);
		panel.setBackground(new Color(206, 243, 240,50));
		add(panel);
		panel.setLayout(null);
		
		textArea弹窗 = new JTextArea();
		textArea弹窗.setBounds(18, 33, 515, 200);
		textArea弹窗.setBackground(new Color(227, 231, 238, 255));
		panel.add(textArea弹窗);
		
		btn弹窗 = new JButton("发布弹窗公告");
		btn弹窗.setFont(new Font("宋体",Font.BOLD,18));
		btn弹窗.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showWindow();
			}
		});
		btn弹窗.setBounds(210, 240, 160, 46);
		panel.add(btn弹窗);
		Thread thread=new Thread(this);
		thread.start();
		
	}
	//设置窗体的背景图片
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);//这句话有时候必须写，有时候可以不写
		g.drawImage(MyTools.getIcon("../img/registerBGimg3.jpg").getImage(), 0, 0, null);
	}


	public void run()
	{
		while(true)
		{
			showTime();
			if(isRun)
			{
				endTime=System.currentTimeMillis();
				showRunTime();
			}
			else 
			{
				showRunTime();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void showTime()
	{
		Calendar calendar=Calendar.getInstance();
		String time=calendar.get(Calendar.YEAR)+"年"
				+(calendar.get(Calendar.MONTH)+1)+"月"
				+calendar.get(Calendar.DATE)+"日"
				+" "
				+calendar.get(Calendar.HOUR_OF_DAY)+":"
				+calendar.get(Calendar.MINUTE)+":"
				+calendar.get(Calendar.SECOND);
		currentTime.setText(time);
	}
	public void showRunTime()
	{
		allTime=(int)(endTime-startTime)/1000;
		lblRun.setText(String.valueOf(allTime));
	}
	public void startOrCloseServer()
	{
		if(btnStart.getText().equals("启动服务器"))
		{
			qqServer=new QQServer(MyTools.QQServerPort);
			allTime=0;
			startTime=System.currentTimeMillis();
			isRun=true;
			lblState.setText("服务器正在运行中...");
			btnStart.setText("停止服务器");
		}
		else if(btnStart.getText().equals("停止服务器"))
		{
			if(qqServer!=null)
			{
				qqServer.closeServer();
				isRun=false;
				lblState.setText("服务器已关闭。");
				JOptionPane.showMessageDialog(null,"本次服务器总共运行"+allTime+"秒");
				btnStart.setText("启动服务器");
			}
		}
	}
	/**
	 * 发布聊天室公告
	 */
	public void sendPublicMessage()
	{
		if(btnStart.getText().equals("停止服务器"))
		{
    		if(textArea公告.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "公告内容不能为空！");
    		else
    			qqServer.sendPublicMessage(textArea公告.getText());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "您还未启动MyQQ服务器，不能发布公告！");
		}
	}
	/**
	 * 弹窗
	 */
	public void showWindow()
	{
		if(btnStart.getText().equals("停止服务器"))
		{
    		if(textArea弹窗.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "弹窗内容不能为空！");
    		else
    			qqServer.sendShowWindow(textArea弹窗.getText());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "您还未启动服务器，不能发布弹窗公告！");
		}
	}
}
