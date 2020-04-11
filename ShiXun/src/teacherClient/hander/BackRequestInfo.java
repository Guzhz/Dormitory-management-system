package teacherClient.hander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import studentClient.hander.BindingInfo;

/**
 * 返回请求处理的消息留言
 * @author 陌生人
 *
 */
public class BackRequestInfo {

	static File BackDno = new File("BackDno.txt");
	static File BackDno1 = new File("BackDno1.txt");
	static File BackEnter = new File("BackEnter.txt");
	static File BackEnter1 = new File("BackEnter1.txt");
	
	static BufferedWriter bw = null;
	static BufferedReader br = null;
	static BufferedWriter bw1 = null;
	static BufferedReader br1 = null;
	//设置（处理）寝室号处理请求
	public static void setDnoResponse(String message) {
		//存储留言
		try {
			bw = new BufferedWriter(new FileWriter(BackDno,true));
		} catch (IOException e) {
			System.out.println("创建文件失败！");
		}
		try {
			bw.write(ReadRequestDno.getSno()+"#"+message);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println("文件写入失败！");
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("文件关流失败！");
				}
			}
		}
		
	}

	//设置（处理）入住请求
	public static void setEnterResponse(String message,String sno) {
		//存储留言
		try {
			bw = new BufferedWriter(new FileWriter(BackEnter,true));
		} catch (IOException e) {
			System.out.println("创建文件失败！");
		}
		try {
			bw.write(sno+"#"+message);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println("文件写入失败！");
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("文件关流失败！");
				}
			}
		}
		
	}
	
	//获取寝室审批后的留言消息同时删除这条信息
	public static String getDnoResponse(String username) {
		try {
			br = new BufferedReader(new FileReader(BackDno));
			bw = new BufferedWriter(new FileWriter(BackDno1));
		} catch (IOException e) {
			System.out.println("创建文件失败！");
		}
		try {
			String line = null;
			while((line=br.readLine()) != null) {
				String data[] = line.split("#");
				if(data[0].equals(BindingInfo.getSno(username))) {
					return data[1];
				}else {
					bw.write(line);
					bw.newLine();
					bw.flush();
				}
			}
		} catch (IOException e) {
			System.out.println("文件写入失败！");
		} finally {
			if(bw != null) {
				try {
					br.close();
					bw.close();
					BackDno.delete();
					BackDno1.renameTo(BackDno);
				} catch (IOException e) {
					System.out.println("文件关流失败！");
				}
			}
		}
		return "";
	}
	
	//获取审批入住申请的留言同时删除这条信息
	public static String getEnterResponse(String sno) {
		try {
			br1 = new BufferedReader(new FileReader(BackEnter));
			bw1 = new BufferedWriter(new FileWriter(BackEnter1));
		} catch (IOException e) {
			System.out.println("创建文件失败！");
		}
		try {
			String line = null;
			while((line=br1.readLine()) != null) {
				String data[] = line.split("#");
				if(data[0].equals(BindingInfo.getSno(sno))) {
					System.out.println(data[1]);
					return data[1];
				}else {
					bw1.write(line);
					bw1.newLine();
					bw1.flush();
				}
			}
		} catch (IOException e) {
			System.out.println("文件写入失败！");
		} finally {
			if(bw1 != null) {
				try {
					br1.close();
					bw1.close();
					BackEnter.delete();
					BackEnter1.renameTo(BackEnter);
				} catch (IOException e) {
					System.out.println("文件关流失败！");
				}
			}
		}
		return "";
	}
}
