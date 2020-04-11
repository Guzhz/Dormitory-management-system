package loginAndRegist.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import loginAndRegist.ui.Ui;

public class LoginOrRegist implements LRImpl {

	//创建个文件用于保存数据
	private static File file = new File("UserInfo.txt");
	static {
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件创建失败！");
		} 
	}
	
	@Override
	public int Login(JFrame jf, String username, String password) {
		int flag = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] datas = line.split("=");
					if(datas[0].equals(username) && datas[1].equals(password)) {
						if(datas[2].equals("老师")) {			//1为教师
							flag = 1;
						} else {							//2为学生
							flag = 2;
						}
						Ui.setFrameImage(jf, "Login.jpg");
						jf.setTitle("已登录");
						return flag;
					}
				}
			} catch (IOException e) {
				System.out.println("用户登录数据文件找不到！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("登录失败！");
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("用户登录关闭资源失败！");
				}
			}
		}
		return 0;
	}

	@Override
	public void Regist(UserInfo user) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(user.getUsername()+"="+user.getPassword()+"="+user.getIdentity());
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println("用户注册失败！");
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("用户注册关闭资源失败！");
				}
			}
		}
	}
	
}
