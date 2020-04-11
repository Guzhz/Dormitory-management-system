package loginAndRegist.response;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import loginAndRegist.core.LRImpl;
import loginAndRegist.core.LoginOrRegist;
import loginAndRegist.core.RegistFrame;
import loginAndRegist.core.UserInfo;
import studentClient.core.StudentClientFrame;
import teacherClient.core.TeacherClientFrame;

public class Response {
	
	//登录界面的重置按钮
	public static void reset_ActionEvent(JTextField jtf1,JPasswordField jpf1) {
		// TODO Auto-generated method stub
		jtf1.setText("");
		jpf1.setText("");
		jtf1.requestFocus();
	}
	//登录界面的登录按钮
	public static void login_ActionEvent(JFrame jf, JTextField jtf1,JPasswordField jpf1) {
		
		String username = jtf1.getText().trim();
		String password = String.valueOf(jpf1.getPassword()).trim();
		
		LRImpl lri = new LoginOrRegist();
		int flag = lri.Login(jf, username, password);
		if(flag == 1) {
			JOptionPane.showMessageDialog(jf, "当前为教师用户，正在跳转管理界面...","登录成功",1);
			jf.setVisible(false);
			new TeacherClientFrame(jf,username);
		}else if(flag == 2) {
			JOptionPane.showMessageDialog(jf, "当前为学生用户，正在跳转管理界面...","登录成功",1);
			jf.setVisible(false);
			new StudentClientFrame(jf,username);
		} else {
			JOptionPane.showMessageDialog(jf, "用户名或者密码错误！");
			reset_ActionEvent(jtf1,jpf1);
		}
	}
	//登录界面的注册按钮
	public static void regist_ActionEvent(JFrame jf, JPanel jp1, JPanel jp2) {
		jp1.setVisible(false);
		jp2.setVisible(false);
		RegistFrame rf = new RegistFrame(jf,jp1,jp2);
	}
	
	//注册界面的注册按钮
	public static void yesRegist_ActionEvent(JFrame jf, JPanel jp1,JPanel jp2,
			JPanel jp3,JPanel jp4, JTextField jtf1, JPasswordField jpf1, 
			JPasswordField jpf2,JRadioButton jrb1, JRadioButton jrb2) {
		
		String username = jtf1.getText();
		String password1 = String.valueOf(jpf1.getPassword()).trim();
		String password2 = String.valueOf(jpf2.getPassword()).trim();
		String identify = Response.getIdentity(jrb1, jrb2);
		 
		String usernameRegex = "[a-zA-Z]\\w{5}";
		String passwordRegex = "\\w{6,12}";
		
		if(!username.matches(usernameRegex)) {
			JOptionPane.showMessageDialog(jf, "用户名必须为英文字母开头，共六个字符");
			yesReset_ActionEvent(jtf1,jpf1,jpf2);
			return;
		}
		if(!password1.matches(passwordRegex)) {
			JOptionPane.showMessageDialog(jf, "密码必须为6-12位字符");
			yesReset_ActionEvent(jtf1,jpf1,jpf2);
			return;
		}
		if(!password1.equals(password2)) {
			JOptionPane.showMessageDialog(jf, "两次输入的密码不匹配，请重新输入");
			yesReset_ActionEvent(jtf1,jpf1,jpf2);
			return;
		}
		
		UserInfo userinfo = new UserInfo();		//封装成用户对象
		userinfo.setUsername(username);
		userinfo.setPassword(password1);
		userinfo.setIdentity(identify);
		
		LRImpl lri = new LoginOrRegist();
		lri.Regist(userinfo);					//把用户对象传过去
		
		JOptionPane.showMessageDialog(jf, "注册成功，正在跳转登录界面...");
		
		cancel_ActionEvent(jf, jp1, jp2, jp3, jp4);
	}
	
	//登录界面的重置按钮
	public static void yesReset_ActionEvent(JTextField jtf1,JPasswordField jpf1,JPasswordField jpf2) {
		jtf1.setText("");
		jpf1.setText("");
		jpf2.setText("");
		jtf1.requestFocus();
	}
	//注册界面的学生单选框按钮
	public static void radioButton_ActionEvent(JRadioButton jrb1, JRadioButton jrb2) {
		// TODO Auto-generated method stub
		jrb1.setSelected(true);
		if(jrb1.isSelected()) {
			jrb2.setSelected(false);
		}
	}
	//注册界面的老师单选框按钮
	public static void radioButton2_ActionEvent(JRadioButton jrb1, JRadioButton jrb2) {
		jrb2.setSelected(true);
		if(jrb2.isSelected()) {
			jrb1.setSelected(false);
		}
	}
	//注册界面的取消按钮
	public static void cancel_ActionEvent(JFrame jf, JPanel jp1,JPanel jp2,JPanel jp3,JPanel jp4) {
		//返回登录界面
		jp1.setVisible(true);
		jp2.setVisible(true);
		jp3.setVisible(false);
		jp4.setVisible(false);
	}
	
	public static String getIdentity(JRadioButton jrb1, JRadioButton jrb2) {
		if(jrb1.isSelected()) {
			return "学生";
		} else {
			return "老师";
		}
	}
}
