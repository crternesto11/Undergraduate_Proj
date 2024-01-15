package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import client.common.MyLabel;
import client.control.Chat;
import javax.swing.JScrollPane;

public class PublicMessageFrame extends JDialog
{
	private JPanel contentPane;
	public JTextArea textAreaPublic;//公告
	public JLabel lbl_close;//关闭
	private JLabel lbl_mini;//最小化
	public JButton btn_open ;//打开
	public Chat chat=null;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		new PublicMessageFrame("您有未读消息","欢迎欢迎！",null);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PublicMessageFrame(String title,String publicMessage)
	{
		init(title, publicMessage);
	}
	/**
	 * Create the frame.
	 */
	public PublicMessageFrame(String title,String publicMessage,Chat chat)
	{
		this.chat=chat;
		init(title, publicMessage);
	}
	public void init(String title,String publicMessage)
	{
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 352, 267);
		contentPane = new MyPanel("../img/QQ2013_Login.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl标题 = new JLabel(title);
		lbl标题.setFont(new Font("宋体", Font.BOLD, 16));
		lbl标题.setBounds(15, 5, 237, 21);
		contentPane.add(lbl标题);
		
		JButton btn关闭 = new JButton("关闭(C)");
		btn关闭.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btn关闭.setBounds(247, 234, 83, 27);
		contentPane.add(btn关闭);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 107, 345, 126);
		contentPane.add(scrollPane);
		
		textAreaPublic = new JTextArea();
		scrollPane.setViewportView(textAreaPublic);
		textAreaPublic.setLineWrap(true);
		textAreaPublic.setFont(new Font("微软雅黑", Font.BOLD, 18));
		textAreaPublic.setBackground(new Color(255, 250, 250));
		textAreaPublic.setEditable(false);
		textAreaPublic.setText(publicMessage);
		
		lbl_close = new JLabel("");
		lbl_close.setIcon(new ImageIcon(PublicMessageFrame.class.getResource("/client/img/button/login_exit_1.png")));
		lbl_close.setBounds(312, 0, 38, 18);
		contentPane.add(lbl_close);
		
		lbl_mini = new JLabel("");
		lbl_mini.setIcon(new ImageIcon(PublicMessageFrame.class.getResource("/client/img/button/login_minsize_1.png")));
		lbl_mini.setBounds(282, 0, 29, 18);
		contentPane.add(lbl_mini);
		
		btn_open = new JButton("\u6253\u5F00");
		btn_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showChat();
			}
		});
		btn_open.setBounds(165, 234, 83, 27);
		contentPane.add(btn_open);
		
		addEvent();
		showFrame();
	}
	
	/**
	 * 添加事件
	 */
	public void addEvent()
	{
		new MyLabel(lbl_mini, "../img/button/login_minsize", "png").addEvent();
		new MyLabel(lbl_close, "../img/button/login_exit", "png").addEvent();
		lbl_close.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				dispose();
			}
		});
	}
	/**
	 * 显示窗体
	 */
	public void showFrame()
	{
		this.setAlwaysOnTop(true);
		if(chat==null)
			btn_open.setVisible(false);
		int width=350;
		int height=266;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width-width-3,screenSize.height,width,height);
		setVisible(true);
		Timer timer=new Timer(10, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setLocation(getLocation().x, getLocation().y-10);
			}
		});
		timer.start();
		while(true)
		{
			System.out.println("当前高度："+getLocation().y+"，屏幕高度："+screenSize.height);
			if(getLocation().y+height<screenSize.height-25)
			{
				timer.stop();
				break;
			}
		}
	}
	/**
	 * 显示聊天窗体
	 */
	public void showChat()
	{
		if(chat!=null)
		{
			chat.setVisible(true);
			this.dispose();
		}
	}
}
