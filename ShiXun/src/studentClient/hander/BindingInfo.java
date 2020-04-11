package studentClient.hander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 绑定用户入住信的类
 * 方法：绑定用户信息、判断是否已经绑定
 * @author 陌生人
 * 
 */

public class BindingInfo {

	private static File afile = new File("AddStudent.txt");
	private static File bfile = new File("BindingInfo.txt");	
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	private static String s = "";
	
	//提取姓名
	public static String getName(String username) {
		try {
			br = new BufferedReader(new FileReader(bfile));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[3].equals(username)) {
						return data[0];		//匹配上了返回学号
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("资源关闭失败！");
				}
			}
		}
		return "";
	}
	
	//通过已知的信息，提取学号
	public static String getSno(String username) {
		try {
			br = new BufferedReader(new FileReader(bfile));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[3].equals(username)) {
						return data[1];		//匹配上了返回学号
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("资源关闭失败！");
				}
			}
		}
		return "";
	}
	
	//通过已知信息，提取寝室号
	public static String getDno(String username) {
		try {
			br = new BufferedReader(new FileReader(bfile));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[3].equals(username)) {
						return data[2];		//匹配上了返回寝室号
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("资源关闭失败！");
				}
			}
		}
		return "";
	}
	
	//判断当前账号是否已经绑定（即当前账号→信息）
	public static boolean isBinding(String username) {
		try {
			br = new BufferedReader(new FileReader(bfile));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[3].equals(username)) {
						return true;
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("资源关闭失败！");
				}
			}
		}
		return false;
	}
	
	//判断输入的信息是否已经被其他账号绑定（信息→账号）
	public static boolean snoIsBinded(String sno) {
		try {
			br = new BufferedReader(new FileReader(bfile));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[1].equals(sno)) {
						return true;			//匹配上了返回学号
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("资源关闭失败！");
				}
			}
		}
		return false;
	}
	
	//绑定用户
	public static boolean toBinding(JFrame scf,String name, String sno,String username) {
		
		if(!sno.equals("") && !sno.matches("^\\d{10}$")) {
			JOptionPane.showMessageDialog(scf, "学号必须为10位数字！", "绑定失败", 0);
			return false;
		}
		
		if(snoIsBinded(sno)) {
			JOptionPane.showMessageDialog(scf, "当前住户已经被其他账号绑定！", "绑定失败", 0);
			return false;
		}
		
		//读取学生信息文件
		boolean flag = false;
		try {
			br = new BufferedReader(new FileReader(afile));
			String line = null;
			try {
				while((line = br.readLine())!=null) {
					String data[] = line.split("#");
					if(data[0].equals(name) && data[1].equals(sno)) {
						s = name+"#"+sno+"#"+data[4]+"#"+username;
						flag = true;
					}
				}
			} catch (IOException e) {
				System.out.println("文件读取失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
			if(s.equals("")) {
				JOptionPane.showMessageDialog(scf, "该住户信息不存在！", "绑定失败", 0);
				return false;
			}
		}
		
		if(flag) {
			if(isBinding(username)) {
				JOptionPane.showMessageDialog(scf, "该账号已绑定了入住信息！", "操作失败", 0);
				return false;
			}
		}
		
		//写入新的绑定文件中
		try {
			bw = new BufferedWriter(new FileWriter(bfile,true));
			bw.write(s);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println("文件写出失败！");
		} finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("关闭资源失败！");
				}
			}
		}
		return true;
	}
}
