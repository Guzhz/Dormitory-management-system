package teacherClient.hander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 读取寝室号更换申请文件
 * 可返回姓名、学号、旧寝室、新寝室、删除已处理的请求
 * @author 陌生人
 *
 */
public class ReadRequestDno {

	private static File file = new File("RequestDno.txt");
	private static File file1 = new File("RequestDno1.txt");
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	
	//刷新寝室号请求信息
	public static void flushRequestInfo() {
		try {
			br = new BufferedReader(new FileReader(file));
			bw = new BufferedWriter(new FileWriter(file1));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			br.readLine();
			while((line = br.readLine())!=null) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null && bw!=null) {
				try {
					bw.close();
					br.close();
					System.out.println(file.delete());
					file1.renameTo(file);
				} catch (IOException e) {
					System.out.println("资源关闭失败！");
				}
			}
		}
	}
	
	//获取文件中的姓名
	public static String getName() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null){
				String data[] = line.split("#");
				return data[0];
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return "";
	}
	//获取文件中的学号
	public static String getSno() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null){
				String data[] = line.split("#");
				return data[1];
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return "";
	}
	//获取文件中的旧寝室号
	public static String getOldDno() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null){
				String data[] = line.split("#");
				return data[2];
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return "";
	}
	
	//获取文件中的新寝室号
	public static String getNewDno() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		}
		String line = null;
		try {
			while((line=br.readLine()) != null){
				String data[] = line.split("#");
				return data[3];
			}
		} catch (IOException e) {
			System.out.println("文件读取失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("关闭资源失败！");
				}
			}
		}
		return "";
	}
}
