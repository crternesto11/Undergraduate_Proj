package client.common;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer
{
	private Font uhOhFont;
	private ImageIcon[] images=null;
	private String[] imageNames=null;
	public ComboBoxRenderer(ImageIcon[] images,String[] imageNames)
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		this.imageNames=imageNames;
		this.images=images;
	}

	//对应的图像和文本，并返回设置为显示文本和图像的标签
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		// Get the selected index. (The index param isn't
		// always valid, so just use the value.)
		int selectedIndex = ((Integer) value).intValue();

		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		// Set the icon and text. If icon was null, say so.
		ImageIcon icon = images[selectedIndex];
		String pet = imageNames[selectedIndex];
		setIcon(icon);
		if (icon != null)
		{
			setText(pet);
			setFont(list.getFont());
		}
		else
		{
			setUhOhText(pet + " (没有有效的图片)", list.getFont());
		}

		return this;
	}

	// 如果找不到文字或图片
	protected void setUhOhText(String uhOhText, Font normalFont)
	{
		if (uhOhFont == null)
		{ // lazily create this font
			uhOhFont = normalFont.deriveFont(Font.ITALIC);
		}
		setFont(uhOhFont);
		setText(uhOhText);
	}
}
