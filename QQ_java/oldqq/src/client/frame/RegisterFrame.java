package client.frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

import client.control.Login;
import client.control.Register;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterFrame extends JFrame {

	public JPanel contentPane;
	public JTextField txtName;
	public JPasswordField pwd;
	public JPasswordField pwdRe;
	public JTextField txtEmail;
	public JTextField txtbirthday;
	public JComboBox comGender;
	public JTextArea txtSignat;//签名
	public JComboBox comboBoxHeadImage;//头像
	public JButton btnRegister;
	/**
	 * Create the frame.
	 */
	public RegisterFrame()
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				beforeClose();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/client/img/QQ_64.png")));
		setTitle("QQ2013\u6CE8\u518C");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(592, 553);
		
		contentPane = new MyPanel("../img/registerBGimg.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUserName = new JLabel("用户昵称");
		lbUserName.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbUserName.setBounds(22, 45, 79, 35);
		contentPane.add(lbUserName);
		
		JLabel lbPwd = new JLabel("密码");
		lbPwd.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbPwd.setBounds(38, 96, 54, 30);
		contentPane.add(lbPwd);
		
		JLabel lbPwdre = new JLabel("重复密码");
		lbPwdre.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbPwdre.setBounds(22, 138, 79, 35);
		contentPane.add(lbPwdre);
		
		JLabel lbEmail = new JLabel("E-mail");
		lbEmail.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbEmail.setBounds(33, 234, 59, 28);
		contentPane.add(lbEmail);
		
		JLabel lbBirthday = new JLabel("生日");
		lbBirthday.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbBirthday.setBounds(38, 285, 46, 24);
		contentPane.add(lbBirthday);
		
		JLabel lbSignature = new JLabel("个性签名");
		lbSignature.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbSignature.setBounds(22, 332, 79, 28);
		contentPane.add(lbSignature);
		
		btnRegister = new JButton("立即注册");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 100, 0));
		btnRegister.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnRegister.setBounds(57, 431, 161, 60);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.setForeground(new Color(255, 250, 250));
		btnCancel.setBackground(new Color(0, 100, 0));
		btnCancel.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cancle();
			}
		});
		btnCancel.setBounds(248, 431, 100, 60);
		contentPane.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setBounds(111, 40, 217, 35);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(111, 92, 217, 35);
		contentPane.add(pwd);
		
		pwdRe = new JPasswordField();
		pwdRe.setBounds(113, 138, 215, 35);
		contentPane.add(pwdRe);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(111, 231, 217, 35);
		contentPane.add(txtEmail);
		
		comGender = new JComboBox();
		comGender.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		comGender.setBounds(111, 185, 59, 35);
		contentPane.add(comGender);
		
		JLabel lbGender = new JLabel("性别");
		lbGender.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lbGender.setBounds(38, 190, 46, 24);
		contentPane.add(lbGender);
		
		txtSignat = new JTextArea();
		txtSignat.setBounds(111, 328, 217, 75);
		contentPane.add(txtSignat);
		
		txtbirthday = new JTextField();
		txtbirthday.setBounds(111, 281, 217, 35);
		contentPane.add(txtbirthday);
		txtbirthday.setColumns(10);
		
		comboBoxHeadImage = new JComboBox();
		comboBoxHeadImage.setBounds(375, 100, 138, 117);
		contentPane.add(comboBoxHeadImage);
		
		JLabel lblHead = new JLabel("头像选择");
		lblHead.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblHead.setBounds(375, 60, 100, 32);
		contentPane.add(lblHead);
	}
	
	/**
	 * 在窗体关闭之前需要做的事
	 */
	public void beforeClose()
	{}
	/**
	 * 取消按钮事件
	 */
	public void cancle()
	{}
}
