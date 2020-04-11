package teacherClient.hander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 排序文件信息的类
 * @author 陌生人
 *
 */

public class SortInfo {
	
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	private static File file1 = new File("AddStudent.txt");
	private static File file2 = new File("AddStudent1.txt");
	
	private static File file3 = new File("DormNumber.txt");
	private static File file4 = new File("DormNumber1.txt");
	
	
	//学生信息排序，按寝室排序
	public static void sortStudentIndo() {
		String[] temp1 = new String[500];		//开辟一个空间为500的数组 
		int[] temp2 = new int[500];				//开辟一个空间为500的数组 
		int count = 0;		//记录数组存放有效值的行数
		try {
			br = new BufferedReader(new FileReader(file1));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					temp1[count] = line;			//保存为：数据-寝室号和床号（String）的数组
					temp2[count++] = getIntegerValue(data[4]+data[5]);
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
		//开始排序-冒泡
		for(int i = 0; i<count-1; i++) {					//外层循环控制排序趟数
			for(int j = 0; j<count-1-i; j++){		//内层循环控制每一趟排序多少次
				if(temp2[j] > temp2[j+1]) {
					String ss = temp1[j];
					temp1[j] = temp1[j+1];
					temp1[j+1] = ss;
					
					int t = temp2[j];
					temp2[j] = temp2[j+1];
					temp2[j+1] = t;
				}
			}
		}
		
		
		//写入
		try {
			int i = 0;
			bw = new BufferedWriter(new FileWriter(file2));
			while(i<count) {
				bw.write(temp1[i]);
				bw.newLine();
				bw.flush();
				i++;
			}
		} catch (IOException e) {
			System.out.println("排序写入文件失败！");
		} finally {
			if(bw!=null) {
				try {
					bw.close();
					file1.delete();
					file2.renameTo(file1);
				} catch (IOException e) {
					System.out.println("排序关闭资源失败！");
				}
			}
		}
	}
	
	public static int getIntegerValue(String value) {
		String[] arr = value.split("-");
		String s = arr[0]+arr[1];
		return Integer.valueOf(s);		//把寝室号转化为int值去比较
	}

	//寝室号排序
	public static void sortDormInfo() {
		
		String[] temp1 = new String[100];		//开辟一个空间为100的数组 
		int[] temp2 = new int[100];				//开辟一个空间为100的数组 
		int count = 0;		//记录数组存放有效值的行数
		try {
			br = new BufferedReader(new FileReader(file3));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("-");
					temp1[count] = line;				
					temp2[count++] = Integer.valueOf(data[0]+data[1]);
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
		//开始排序-冒泡
		for(int i = 0; i<count-1; i++) {					//外层循环控制排序趟数
			for(int j = 0; j<count-1-i; j++){				//内层循环控制每一趟排序多少次
				if(temp2[j] > temp2[j+1]) {
					String ss = temp1[j];
					temp1[j] = temp1[j+1];
					temp1[j+1] = ss;
					
					int t = temp2[j];
					temp2[j] = temp2[j+1];
					temp2[j+1] = t;
				}
			}
		}
		
		
		//写入
		try {
			int i = 0;
			bw = new BufferedWriter(new FileWriter(file4));
			while(i<count) {
				bw.write(temp1[i]);
				bw.newLine();
				bw.flush();
				i++;
			}
		} catch (IOException e) {
			System.out.println("排序写入文件失败！");
		} finally {
			if(bw!=null) {
				try {
					bw.close();
					file3.delete();
					file4.renameTo(file3);
				} catch (IOException e) {
					System.out.println("排序关闭资源失败！");
				}
			}
		}
	}
}
