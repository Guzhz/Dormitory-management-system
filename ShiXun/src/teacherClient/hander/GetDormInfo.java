package teacherClient.hander;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetDormInfo {
	static BufferedReader br = null;
	private static File file = new File("DormNumber.txt");
	
	//读取文件，把数据存储到datas[][]中，参数text为搜索指定信息，可以为null
	public static Object[][] readInfo(String text){
		Object[][] datas = new Object[100][2];	//先设置100个寝室
		int row = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					if(text == null) {		//text为空则全部读取
						datas[row][1] = line;
						datas[row][0] = ++row;
					} else {
						if(line.equals(text)) {
							datas[row][1] = line;
							datas[row][0] = ++row;
						}
					}
				}
			} catch (IOException e) {
				System.out.println("寝室号文件找不到！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("查询失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("查询寝室号功能关闭资源失败！");
				}
			}
		}
		return datas;
	}
}
