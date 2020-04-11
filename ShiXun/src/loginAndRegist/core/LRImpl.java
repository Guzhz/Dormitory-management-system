package loginAndRegist.core;

import javax.swing.JFrame;

public interface LRImpl {
	//0代表登录不成功，1代表登录教师系统，2代表登录学生系统
	public abstract int Login(JFrame jf, String username, String password);		
	public abstract void Regist(UserInfo user);
}
