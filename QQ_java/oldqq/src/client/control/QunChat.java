package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.tree.TreeModel;

import com.MyTools;
import com.MyTools.Flag;

import client.common.MyTextPane;
import client.common.MyTreeIcon;
import client.frame.QunChatFrame;
import client.socket.CS_TCP;

public class QunChat extends QunChatFrame
{
	CS_TCP cs_TCP=null;
	Main main=null;
	
	public QunChat(Main main)
	{
		this.main=main;
		this.tree.setModel(main.tree.getModel());
		this.tree.setCellRenderer(main.tree.getCellRenderer());
		this.cs_TCP=main.cs_TCP;
		MyTools.setWindowsMiddleShow(this);
		this.setTitle("童年大排档之"+main.main_username.getText()+"版");
		addEvent();
		this.setVisible(true);
	}
	public void addEvent()
	{
		qun_sendBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nowTime= DateFormat.getTimeInstance().format(new Date());
				cs_TCP.sendMessage(Flag.QUN_CHAT+MyTools.FLAGEND+main.main_username.getText()+MyTools.SPLIT1+nowTime+MyTools.SPLIT1+qun_sendPane.getText());
				qun_sendPane.setText("");
			}
		});
		qun_closeBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();//关闭聊天室
			}
		});
	}
	public void receiveMessage(String message)
	{
		String[] temp=message.split(MyTools.SPLIT1);
		if(temp[0].equals(main.main_username.getText()))
		{
			new MyTextPane(qun_recPane).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(qun_recPane).addText(temp[2]+"\n", MyTextPane.getMyAttribute());
		}
		else
		{
			new MyTextPane(qun_recPane).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(qun_recPane).addText(temp[2]+"\n", MyTextPane.getFriendAttribute());
		}
	}
	public void showPublicMessage(String message)
	{
		qun_public.setText(message);
	}
}
