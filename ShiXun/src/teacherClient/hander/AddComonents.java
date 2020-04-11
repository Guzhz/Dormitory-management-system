package teacherClient.hander;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 添加面板类
 * @author 陌生人
 *
 */

public class AddComonents {

	//添加面板
	public static JPanel addJPanel(JFrame jf, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jf.add(jp);
		jp.setVisible(true);
		return jp;
	}
	//添加二级面板
	public static JPanel addJPanel_2(JPanel jpanel, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jpanel.add(jp);
		jp.setVisible(true);
		return jp;
	}

	//添加按钮
	private static Color c = new Color(187,206,219);
	public static JButton addJButton(String s, JPanel jp, int x, int y ,int width, int height,
			boolean opaque, boolean border, boolean left) {
		
		JButton jb = new JButton(s);
		jb.setBounds(x, y, width, height);
		if(opaque) {
			jb.setBackground(c);
			jb.setOpaque(false);
		}
		jb.setBorderPainted(border);
		if(left) {
			jb.setHorizontalAlignment(JButton.LEFT);
			jb.setMargin(new Insets(0,10,0,0));
		}
		
		jp.add(jb); 
		jb.setVisible(false);
		return jb;
	}
	//添加标签
	public static JLabel addJLabel(String s, JPanel jp, int x, int y ,int width, int height) {
		JLabel jl = new JLabel(s);
		jl.setBounds(x, y, width, height);
		jp.add(jl);
		jl.setVisible(false);
		return jl;
	}
	//添加文本框
	public static JTextField addJTextField(JPanel jp, int x, int y ,int width, int height) {
		JTextField jtf = new JTextField(10);
		jtf.setBounds(x, y, width, height);
		jp.add(jtf);
		jtf.setVisible(false);
		return jtf;
	}
	
	//添加文本域返回封装好的面板
	public static JTextArea addJTextArea(JPanel jp, int x, int y ,int width, int height) {
		JTextArea jta = new JTextArea();
		jta.setBounds(x, y, width, height);
		jp.add(jta);
		jta.setVisible(false);
		jta.setLineWrap(true);  //自动换行
		jta.setWrapStyleWord(true);  //不断字
		return jta;
	}
}
