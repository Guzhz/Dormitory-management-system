package teacherClient.hander;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 按指定条件读取学生信息并返回一个数组
 * @author 陌生人
 *
 */
public class GetStudentInfo {

	static BufferedReader br = null;
	private static File file = new File("AddStudent.txt");
	
	//读取文件，把数据存储到datas[][]中，参数text为搜索指定信息，可以为null
	public static Object[][] readInfo(String text){
		Object[][] datas = new Object[100][7];	//先设置100个名单
		int row = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(text == null) {
						for(int i = 0; i<data.length; i++) {
							datas[row][i+1] = data[i];		//数据从第二列开始存储
						}
						datas[row][0] = ++row;
					} else {
						if(data[0].equals(text) || data[1].equals(text) || data[2].equals(text) || data[3].equals(text) || data[4].equals(text) || data[5].equals(text))
						{
							for(int i = 0; i<data.length; i++) {
								datas[row][i+1] = data[i];		//数据从第二列开始存储
							}
							datas[row][0] = ++row;
						}
					}
				}
			} catch (IOException e) {
				System.out.println("存储被添加的用户信息的文件找不到！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("查询失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("查询功能关闭资源失败！");
				}
			}
		}
		return datas;
	}
}
