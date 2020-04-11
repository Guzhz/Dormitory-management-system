package teacherClient.hander;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 功能：判断所添加的信息是否正确
 * @author 陌生人
 *
 */
public class JudgeInfo {
	
	static BufferedReader br = null;
	private static File file = new File("AddStudent.txt");
	private static File file1 = new File("DormNumber.txt");
	public static final int LITMITNUMBER = 4; //设定限定人数为4
	
	static {
		try {
			file1.createNewFile();
		} catch (IOException e) {
			System.out.println("存放寝室号的文件创建失败！");
		}
	}
	//判断学号是否唯一
	public static boolean isOnlySno(String sno){
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[1].equals(sno)) {
						return false;
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
		return true;
	}
	
	//判断寝室号是否唯一
	public static boolean isOnlyDno(String dno) {
		
		try {
			br = new BufferedReader(new FileReader(file1));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					if(line.equals(dno)) {
						return false;
					} 
				}
			} catch (IOException e) {
				System.out.println("存储寝室号信息的文件找不到！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("判断失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("判断功能关闭资源失败！");
				}
			}
		}
		return true;
	}
	
	//判断寝室号是否存在
	public static boolean existDno(String dno) {
		
		try {
			br = new BufferedReader(new FileReader(file1));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					if(line.equals(dno)) {
						return true;
					} 
				}
			} catch (IOException e) {
				System.out.println("存储寝室号信息的文件找不到！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("判断失败！");
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("判断功能关闭资源失败！");
				}
			}
		}
		return false;
	}
	
	//判断寝室床号是否被占用
	public static boolean bedIsEmpty(String dno,String bedno) {
		
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[4].equals(dno) && data[5].equals(bedno)) {
						return false;		//床号不可用
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
		return true;
	}
	
	//判断宿舍人数是否上限
	public static boolean isLimit(String dno) {
		int count = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					String[] data = line.split("#");
					if(data[4].equals(dno)) {
						count++;
					}
					if(count == LITMITNUMBER) {
						return true;
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
		return false;
	}
}
