package teacherClient.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ui {
	
	//设置窗口图标
	public static void setFrameImage(JFrame jf, String imageName) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image i = tk.getImage("src\\teacherClient\\image\\"+imageName);
		jf.setIconImage(i);
	}
	
	//设置图片界面返回一个面板
	public static JPanel setBackstageUI (JFrame jf) {
		Icon i = new ImageIcon("src\\teacherClient\\image\\ui1.jpg");
		JLabel jl = new JLabel(i);
		JPanel jp = new JPanel();
		jl.setBounds(0, 0, Global.WIDTH, 78);
		jp.setBounds(0, -8, Global.WIDTH, 78);
		jp.add(jl);
		jf.add(jp);
		jp.add(jl);
		return jp;
	}		
	
	//设置图片界面返回一个面板
	public static JPanel setBackstageUI2 (JFrame jf) {
		Icon i = new ImageIcon("src\\teacherClient\\image\\ui2.jpg");
		JLabel jl = new JLabel(i);
		JPanel jp = new JPanel();
		jl.setBounds(200, 90, 773, 655);
		jp.setBounds(200, 90, 773, 655);
		jp.add(jl);
		jf.add(jp);
		jp.add(jl);
		return jp;
	}		
	
	//设置居中
	public static void setFrameCenter(JFrame jf) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		double srceenWidth = d.getWidth();
		double srceenHight = d.getHeight();
		int frameWidth = jf.getWidth();
		int frameHeight = jf.getHeight();
		int width = (int)(srceenWidth - frameWidth)/2;
		int height = (int)(srceenHight - frameHeight)/2;
		jf.setLocation(width, height);
	}
}
