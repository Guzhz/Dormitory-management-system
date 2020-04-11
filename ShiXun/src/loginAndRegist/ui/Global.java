package loginAndRegist.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 全局类常量
 * 常用添加组件方法
 * @author 陌生人
 *
 */
public class Global {
	public static final int WIDTH = 800; 
	public static final int HEIGHT = 600;
	
	//添加面板
	public static JPanel addJPanel(JFrame jf, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jf.add(jp);
		return jp;
	}
	//添加标签
	public static JLabel addJLabel(String s, JPanel jp, int x, int y ,int width, int height) {
		JLabel jl = new JLabel(s);
		jl.setBounds(x, y, width, height);
		jp.add(jl);
		return jl;
	}
	//添加账号框
	public static JTextField addJTextField(JPanel jp, int x, int y ,int width, int height) {
		JTextField jtf = new JTextField(10);
		jtf.setBounds(x, y, width, height);
		jp.add(jtf);
		return jtf;
	}
	//添加密码框
	public static JPasswordField addJPasswordField(JPanel jp, int x, int y ,int width, int height) {
		JPasswordField jpf = new JPasswordField(10);
		jpf.setBounds(x, y, width, height);
		jp.add(jpf);
		return jpf;
	}
	//添加按钮
	public static JButton addJButton(String s, JPanel jp, int x, int y ,int width, int height) {
		JButton jb = new JButton(s);
		jb.setBounds(x, y, width, height);
		jp.add(jb);
		return jb;
	}
	public static JRadioButton addJRadioButton(String s, JPanel jp, int x, int y ,int width, int height) {
		JRadioButton jrb = new JRadioButton(s);
		jrb.setBounds(x, y, width, height);
		jp.add(jrb);
		return jrb;
	}
}
