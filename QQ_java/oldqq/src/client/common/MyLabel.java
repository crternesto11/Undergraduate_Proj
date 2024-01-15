package client.common;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import com.MyTools;

public class MyLabel
{
	//规定：常规状态图片索引为1，鼠标进入与释放为2，鼠标按下为3
	JLabel jLabel=null;
	String fileName="";
	String extension="";
	int mode=1;//模式为1表示用图片，模式为2表示用Boder
	Color backColor=null;//Label的父容器的背景色
//相对路径使用
	public MyLabel(JLabel jLabel,String fileName,String extension)
	{
		this.jLabel=jLabel;
		this.fileName=fileName;
		this.extension=extension;
	}
	public MyLabel(JLabel jLabel)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=this.jLabel.getParent().getBackground();
		setEtchedBorder(backColor);
	}
	public MyLabel(JLabel jLabel,Color color)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=color;
		setEtchedBorder(backColor);
	}

	public void setEtchedBorder(Color color)
	{
		jLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(color, color));
	}

	 //给jLabel添加事件
	public void addEvent()
	{
		jLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else
					setEtchedBorder(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_1."+extension));
				else 
					setEtchedBorder(backColor);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_3."+extension));
				else 
					jLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(
							javax.swing.border.BevelBorder.LOWERED));
			}

		});
	}
}
