package teacherClient.hander;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetRequestInfo {

	private static File file = new File("RequestDno.txt");
	private static File file1 = new File("RequestEnter.txt");
	private static BufferedReader br = null;
	
	public static int getDnoMessageCount() {
		int sum = 0;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null) {
				sum++;
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return sum;
	}
	
	public static int getEnterMessageCount() {
		int sum = 0;
		try {
			br = new BufferedReader(new FileReader(file1));
		} catch (FileNotFoundException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null) {
				sum++;
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return sum;
	}
}
