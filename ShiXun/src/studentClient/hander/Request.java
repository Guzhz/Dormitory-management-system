package studentClient.hander;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 传入用户名和新寝室号
 * 生成请求文件
 * @author 陌生人
 *
 */

public class Request {
	
	
	public static void setDnoRequestInfo(String username, String newDno) {
		BufferedWriter bw = null;
		File file = new File("RequestDno.txt");
		try {
			bw = new BufferedWriter(new FileWriter(file,true));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		//存储格式：姓名#学号#旧寝室号#新寝室号
		String info = BindingInfo.getName(username)+"#"+BindingInfo.getSno(username)+"#"+BindingInfo.getDno(username)+"#"+newDno;
		try {
			bw.write(info);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("发起更换寝室请求写入失败！");
		} finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("资源关闭失败！");
				}
			}
		}
	}
	
	public static void setEnterRequestInfo(String name, String sno, String dep, String classes, String username) {
		
		
		BufferedWriter bw = null;
		File file = new File("RequestEnter.txt");
		try {
			bw = new BufferedWriter(new FileWriter(file,true));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		//存储格式：姓名#学号#学院#班级#用户名
		String info = name+"#"+sno+"#"+dep+"#"+classes+"#"+username;
		try {
			bw.write(info);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("发起更换寝室请求写入失败！");
		} finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("资源关闭失败！");
				}
			}
		}
	}
}
