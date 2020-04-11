package loginAndRegist.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import loginAndRegist.ui.Global;
import loginAndRegist.ui.Ui;
import loginAndRegist.response.Response;

/**
 * 登录界面
 * @author 陌生人
 *
 */
public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义基本组件
	private JPanel jp1,jp2;
	private JLabel jl1,jl2,jl3;
	private JTextField jtf1;
	private JPasswordField jpf1;
	private JButton jb_login,jb_regist,jb_reset;
	
	public LoginFrame() {
		initComonents();
		initFrame();
	}

	private void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("未登录");
		this.setResizable(false);
		Ui.setFrameImage(this, "NoLogin.jpg");
		Ui.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(true);
	}

	private void initComonents() {
		
		//面板
		jp1 = Global.addJPanel(this, 168, 136, 465, 250);
		jl1 = Global.addJLabel("用户名：", jp1, 50, 20, 100, 40);
		jl2 = Global.addJLabel("密　码：", jp1, 50, 60, 100, 40);
		jl3 = Global.addJLabel("（没有账号？　点击注册）", jp1, 160, 160, 200, 40);
		jtf1 = Global.addJTextField(jp1, 110, 28, 300, 26);
		jpf1 = Global.addJPasswordField(jp1, 110, 68, 300, 26);
		
		
		//登录按钮响应事件
		jb_login = Global.addJButton("登录", jp1, 250, 120, 80, 40);
		jb_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login_ActionPerformed(e);
			}
		});
		
		//注册按钮响应事件
		jb_reset = Global.addJButton("注册", jp1, 195, 200, 80, 40);
		jb_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regist_ActionPerformed(e);
			}
		});
		
		//重置按钮响应事件
		jb_regist = Global.addJButton("重置", jp1, 145, 120, 80, 40);
		jb_regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset_ActionPerformed(e);
			}
		});
		jp2 = Ui.setLoginAndRegistUI(this);
		
	}

	public void login_ActionPerformed(ActionEvent e) {
		Response.login_ActionEvent(this,jtf1,jpf1);
	}
	
	public void reset_ActionPerformed(ActionEvent e) {
		Response.reset_ActionEvent(jtf1,jpf1);
	}

	public void regist_ActionPerformed(ActionEvent e) {
		Response.regist_ActionEvent(this,jp1,jp2);
	}
}
