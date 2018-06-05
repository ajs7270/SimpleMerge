package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BtnImage extends JButton{
	
	private ArrayList<ImageIcon> icons;
	private int imgIndex = 0;
	private int iconWidth, iconHeight;
	private String desc;
	
	private Dimension size;
	private boolean isBorder = true;
	private boolean isMouseEntered = false;
	
	private int
		paddingTop    = 5,
		paddingLeft   = 5,
		paddingRight  = 5,
		paddingBottom = 5,
		paddingText   = 18;
	
	public BtnImage(ImageIcon icon) {
		this.icons = new ArrayList<>();
		this.icons.add(icon);
		this.iconWidth = icon.getIconWidth();
		this.iconHeight = icon.getIconHeight();
		this.desc = null;
		this.mouseListener();
	}
	
	public BtnImage(ImageIcon icon, String desc) {
		this.icons = new ArrayList<>();
		this.icons.add(icon);
		this.iconWidth = icon.getIconWidth();
		this.iconHeight = icon.getIconHeight();
		this.desc = desc;
		this.mouseListener();
	}
	
	public BtnImage(ArrayList<ImageIcon> images) {
		this.icons = images;
		this.iconWidth = images.get(0).getIconWidth();
		this.iconHeight = images.get(0).getIconHeight();
		this.desc = null;
		this.mouseListener();
	}
	
	private void mouseListener() {
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				isMouseEntered = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				isMouseEntered = true;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
	}
	
	public void setImageSize(int width, int height) {
		// image resize
		for (int i = 0; i < icons.size(); i++) {
			ImageIcon icon = icons.get(i);
			Image newImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
			icon.setImage(newImage);
		}
		
		// save data
		iconWidth = width;
		iconHeight = height;
		
		size = new Dimension(iconWidth + paddingLeft + paddingRight, iconHeight + paddingTop + paddingBottom + paddingText+ 20);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
	}
	
	public boolean isBorder() {
		return isBorder;
	}

	public void setBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}
	
	public void setImageIndex(int imgIndex) {
		if (0 <= imgIndex && imgIndex < icons.size()) this.imgIndex = imgIndex;
		else return;
	}
	
	public int getImageIndex() {
		return this.imgIndex;
	}
	
	@Override
	public void paint(Graphics g) {
		
		// size calculator
		g.setColor(Color.BLACK);
		int textWidth = (desc != null) ? g.getFontMetrics().stringWidth(desc) : 0 ;
		
		if (textWidth > iconWidth) { // if text is more longer
			size = new Dimension(textWidth + paddingLeft + paddingRight, iconHeight + paddingTop + paddingBottom + paddingText+ 20);
		}
		else {
			size = new Dimension(iconWidth + paddingLeft + paddingRight, iconHeight + paddingTop + paddingBottom + ((desc!=null) ? paddingText+ 20 : 0));
		}
		//g.clearRect(0, 0, (int)size.getWidth(), (int)size.getHeight());
		
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		// draw image
		g.drawImage(icons.get(imgIndex).getImage(), this.getWidth()/2 - iconWidth/2, paddingTop, null);
		
		// draw text
		if (desc != null) {
			g.setColor(Color.BLACK);
			g.drawString(desc, this.getWidth()/2 - textWidth/2 , paddingTop + iconHeight + paddingText);
		}
		
		// draw border
		if (isBorder) {
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, this.getWidth()-2, this.getHeight()-2);
		}
		
		
		if (isMouseEntered) {
			g.setColor(new Color(100,100,100, 100));
			g.fillRect(0, 0, (int)size.getWidth(), (int)size.getHeight());
		}
		
		
	}
}
