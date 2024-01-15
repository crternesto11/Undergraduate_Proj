package client.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import client.frame.MyPanel;
import javax.swing.JLabel;
//import com.sun.awt.AWTUtilities;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;//内容
	public JPasswordField user_pwd;//密码
	private JLabel lblQQ2013;
	public JLabel user_head;//头像
	public JCheckBox user_rem;//记住密码
	public JCheckBox user_autoLogin;//自动登录
	public JLabel login;//登录
	public JTextField userName;//用户名
	public JLabel user_register;//注册
	public JLabel user_forgetPwd;//忘记密码
	public JLabel user_exit;//退出
	public JComboBox user_state;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setTitle("QQ2013");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/client/img/QQ_64.png")));
		setUndecorated(true);//设置窗体没有边框
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 272);
		
		contentPane = new MyPanel("../img/QQ2013_Login.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user_pwd = new JPasswordField();
		user_pwd.setText("123");
		user_pwd.setEchoChar('●');
		user_pwd.setBounds(104, 163, 154, 26);
		contentPane.add(user_pwd);
		
		lblQQ2013 = new JLabel("QQ2013");
		lblQQ2013.setForeground(new Color(0, 0, 51));
		lblQQ2013.setFont(new Font("宋体", Font.BOLD, 16));
		lblQQ2013.setBounds(14, 6, 55, 18);
		contentPane.add(lblQQ2013);
		
		user_head = new JLabel("");
		user_head.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/headImage/head_boy_01_64.jpg")));
		user_head.setBounds(18, 127, 64, 64);
		contentPane.add(user_head);
		
		user_rem = new JCheckBox("记住密码");
		user_rem.setBounds(156, 198, 76, 18);
		contentPane.add(user_rem);
		
		user_autoLogin = new JCheckBox("自动登录");
		user_autoLogin.setBounds(237, 198, 76, 18);
		contentPane.add(user_autoLogin);
		
		login = new JLabel("");
		login.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/button_login_1.png")));
		login.setBounds(150, 237, 120, 21);
		contentPane.add(login);
		
		userName = new JTextField();
		userName.setText("胡图图");
		userName.setBounds(104, 128, 154, 26);
		contentPane.add(userName);
		userName.setColumns(10);
		
		user_register = new JLabel("注册账号");
		user_register.setFont(new Font("SansSerif", Font.PLAIN, 13));
		user_register.setForeground(new Color(21, 21, 23));
		user_register.setBounds(288, 132, 58, 18);
		contentPane.add(user_register);
		
		user_forgetPwd = new JLabel("忘记密码");
		user_forgetPwd.setFont(new Font("SansSerif", Font.PLAIN, 13));
		user_forgetPwd.setForeground(new Color(21, 21, 23));
		user_forgetPwd.setBounds(288, 167, 55, 18);
		contentPane.add(user_forgetPwd);
		

		
		user_exit = new JLabel("");
		user_exit.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/login_exit_1.png")));
		user_exit.setBounds(312, -1, 37, 20);
		contentPane.add(user_exit);

		
		user_state = new JComboBox();
		user_state.setBounds(104, 195, 40, 24);
		contentPane.add(user_state);
	}
}
