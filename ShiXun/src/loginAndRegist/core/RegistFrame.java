package loginAndRegist.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import loginAndRegist.ui.Global;
import loginAndRegist.response.Response;
import loginAndRegist.ui.Ui;

/*
 * 注册界面
 */
public class RegistFrame {

	JFrame jf;
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1;
	JPasswordField jpf1,jpf2;
	JRadioButton jrb1,jrb2;
	JButton jb1,jb2,jb3;
	
	public RegistFrame(JFrame jf, JPanel jp1, JPanel jp2) {
		super();
		this.jf = jf;
		this.jp1 = jp1;
		this.jp2 = jp2;
		initComponents();
	}
	
	public void initComponents() {
		//初始化各种组件
		jp3 = Global.addJPanel(jf, 168, 136, 465, 250);
		jp4 = Ui.setLoginAndRegistUI(jf);
		jl1 = Global.addJLabel("用户名：", jp3, 50, 20, 100, 40);
		jl2 = Global.addJLabel("密　码：", jp3, 50, 60, 100, 40);
		jl3 = Global.addJLabel("确认密码：", jp3, 50, 100, 100, 40);
		jl3 = Global.addJLabel("我　是：", jp3, 50, 135, 100, 40);
		jtf1 = Global.addJTextField(jp3, 110, 28, 300, 26);
		jpf1 = Global.addJPasswordField(jp3, 110, 68, 300, 26);
		jpf2 = Global.addJPasswordField(jp3, 110, 108, 300, 26);
		//单选框
		jrb1 = Global.addJRadioButton("学生", jp3, 160, 140, 60, 30);
		jrb2 = Global.addJRadioButton("老师", jp3, 270, 140, 60, 30);
		//按钮
		jb2 = Global.addJButton("重置", 	  jp3, 90, 180, 60, 40);
		jb1 = Global.addJButton("确认注册", jp3, 185, 180, 110, 40);
		jb3 = Global.addJButton("取消",    jp3, 330, 180, 60, 40);
		jrb1.setSelected(true);
		
		jrb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioButton_ActionPerformed(e);
			}
		});
		
		jrb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioButton2_ActionPerformed(e);
			}
		});
		//确认注册响应事件
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yesRegist_ActionPerformed(e);
			}
		});
		//重置按钮响应事件
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yesReset_ActionPerformed(e);
			}
		});
		
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel_ActionPerformed(e);
			}
		});
	}

	protected void yesRegist_ActionPerformed(ActionEvent e) {
		Response.yesRegist_ActionEvent(jf,jp1,jp2,jp3,jp4,jtf1,jpf1,jpf2,jrb1,jrb2);
	}
	
	protected void yesReset_ActionPerformed(ActionEvent e) {
		Response.yesReset_ActionEvent(jtf1, jpf1, jpf2);
	}
	
	protected void radioButton_ActionPerformed(ActionEvent e) {
		Response.radioButton_ActionEvent(jrb1,jrb2);
	}
	
	protected void radioButton2_ActionPerformed(ActionEvent e) {
		Response.radioButton2_ActionEvent(jrb1,jrb2);
	}
	
	protected void cancel_ActionPerformed(ActionEvent e) {
		Response.cancel_ActionEvent(jf, jp1, jp2, jp3, jp4);
	}
}
