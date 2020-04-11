package studentClient.hander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeBindingInfo {

	private static File file = new File("BindingInfo.txt");
	private static File file1 = new File("BindingInfo1.txt");
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	//如果修改了姓名，则读取表格的数据同步修改绑定信息的姓名（通过用户名去读取）
	public static void changeBindingInfo(Object[][] newArr ,String username) {

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e2) {
			System.out.println("信息文件更新失败!");
		}
		try {
			bw = new BufferedWriter(new FileWriter(file1));
		} catch (IOException e1) {
			System.out.println("信息文件更新失败!");
		}
		String line = null;
		try {
			while((line = br.readLine()) != null) {
				String data[] = line.split("#");
				if(data[3].equals(username)) {
					bw.write(newArr[0][0].toString()+"#"+data[1]+"#"+data[2]+"#"+data[3]);
					bw.newLine();
				}else {
					bw.write(line);
					bw.newLine();
				}
				bw.flush();
			}
		} catch (IOException e) {
			System.out.println("存储被添加的用户信息的文件找不到！");
		} finally {
			if(bw!=null && br!=null) {
				try {
					bw.close();
					br.close();
					file.delete();
					file1.renameTo(file);
				} catch (IOException e) {
					System.out.println("查询功能关闭资源失败！");
				}
			}
		}
	}
}