package teacherClient.hander;

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
 * 修改学生信息文件类
 * @author 陌生人
 *
 */

public class ChangeStudentInfo {

	static BufferedReader br = null;
	static BufferedWriter bw = null;
	private static File file = new File("AddStudent.txt");
	private static File file1 = new File("AddStudent1.txt");
	
	public static void changeInfo(JFrame tcf,Object[][] oldArr, Object[][] newArr) {
		
		boolean enAlter = true;
		String snoRegex = "^\\d{10}$";	//必须为10位纯数字
		String dnoRegex = "^[1-9]-\\d{3}$";	//最多为9栋宿舍楼
		String bedRegex = "^[1-4]$";		//床号1-4
		
		int count = 0; //记录传来的数组有效值有多少行，用于循环，因为可能存在小于4行
		
		//判断修改后的学号是否存在,寝室号是否存在，是否满员，是否床号可用！判断无误则可以修改数据文件！
		for(int i = 0; i<newArr.length; i++) {
			if(newArr[i][1]!=null) {
				count++;
			}else {
				break;
			}
			
			if(!oldArr[i][1].toString().equals(newArr[i][1]) && !newArr[i][1].toString().matches(snoRegex)) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "学号格式不正确！", newArr[i][0]+"的学号修改失败！", 0);
				break;
			}else {
				if(!oldArr[i][1].toString().equals(newArr[i][1]) && !JudgeInfo.isOnlySno(newArr[i][1].toString())) {
					enAlter = false;
					JOptionPane.showMessageDialog(tcf, "该学号已存在！", newArr[i][0]+"的学号修改失败！", 0);
					break;
				}
			}
			
			if(!oldArr[i][4].toString().equals(newArr[i][4]) && !newArr[i][4].toString().matches(dnoRegex)) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "寝室号格式不正确！", newArr[i][0]+"的寝室号修改失败！", 0);
				break;
			}else {
				if(!oldArr[i][4].toString().equals(newArr[i][4]) && !JudgeInfo.existDno(newArr[i][4].toString())) {
					enAlter = false;
					JOptionPane.showMessageDialog(tcf, "该寝室不存在！", newArr[i][0]+"的寝室号修改失败！", 0);
					break;
				}
			}
			
			if(!oldArr[i][4].toString().equals(newArr[i][4]) && JudgeInfo.isLimit(newArr[i][4].toString())) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "该寝室人数已满！", newArr[i][0]+"的寝室号修改失败！", 0);
				break;
			}else {
				if(!oldArr[i][5].toString().equals(newArr[i][5]) && !newArr[i][5].toString().matches(bedRegex)) {
					enAlter = false;
					JOptionPane.showMessageDialog(tcf, "床号格式不正确！", newArr[i][0]+"的床号修改失败！", 0);
					break;
				}else {
					if(!oldArr[i][4].toString().equals(newArr[i][4]) && !JudgeInfo.bedIsEmpty(newArr[i][4].toString(), newArr[i][5].toString())) {
						enAlter = false;
						JOptionPane.showMessageDialog(tcf, "该床号已被使用!", newArr[i][0]+"的床号修改失败！", 0);
						break;
					}
				}
			}
		}
		
		System.out.println("有效行数："+count);
		
		if(enAlter) {	//输入条件均正确后再做进一步判断！
			
			enAlter = false; 	//默认新旧数据都一样！
			String[][] splicingArr = new String[4][2];
			//将数组中的信息，还原为文件指定的格式
			for(int i = 0; i<count; i++) {
				String oldinfo = "";
				String newinfo = "";
				for(int j = 0; j<oldArr[i].length; j++) {		
					if(j!=oldArr[i].length-1) {	
						oldinfo = oldinfo+oldArr[i][j]+"#";
						newinfo = newinfo+newArr[i][j]+"#";
					}else {
						oldinfo = oldinfo+oldArr[i][j];
						newinfo = newinfo+newArr[i][j];
					}
				}
				splicingArr[i][0] = oldinfo;	//左边是旧的
				splicingArr[i][1] = newinfo;	//右边是新的
						
				if(!oldinfo.equals(newinfo)) {	//存在不同数据则可更改！
					enAlter = true;
				}
			}
			
			
			if(!enAlter) {
				JOptionPane.showMessageDialog(tcf, "信息没有改动！", "修改失败", 0);
			}else {
				
				//先读取学生信息文件，遇到相同，就替换，最后生成的文件再重命名回AddStudent.txt
				try {
					br = new BufferedReader(new FileReader(file));
					try {
						bw = new BufferedWriter(new FileWriter(file1));
					} catch (IOException e1) {
						System.out.println("信息文件更新失败!");
					}
					String line = null;
					try {
						int i = 0;
						while((line = br.readLine()) != null) {
							if((i!=count) && line.equals(splicingArr[i][0])) {
								bw.write(splicingArr[i][1]);
								bw.newLine();
								i++; 
							}else {
								bw.write(line);
								bw.newLine();
							}
							bw.flush();
							
						}
					} catch (IOException e) {
						System.out.println("存储被添加的用户信息的文件找不到！");
					}
				} catch (FileNotFoundException e) {
					System.out.println("查询失败！");
				} finally {
					if(bw!=null && br!=null) {
						try {
							bw.close();
							br.close();
							file.delete();
							file1.renameTo(file);
							JOptionPane.showMessageDialog(tcf, "信息已变更！", "修改成功", 1);
						} catch (IOException e) {
							System.out.println("查询功能关闭资源失败！");
						}
					}
				}
				
			}
			//else结束
		}			
	}
	
	public static void changeInfo(JFrame tcf,Object[][] oldArr, Object[][] newArr ,String username) {
		
		boolean enAlter = true;
		String snoRegex = "^\\d{10}$";	//必须为10位纯数字
		String dnoRegex = "^[1-9]-\\d{3}$";	//最多为9栋宿舍楼
		String bedRegex = "^[1-4]$";		//床号1-4
		
		int count = 0; //记录传来的数组有效值有多少行，用于循环，因为可能存在小于4行
		
		//判断修改后的学号是否存在,寝室号是否存在，是否满员，是否床号可用！判断无误则可以修改数据文件！
		for(int i = 0; i<newArr.length; i++) {
			if(newArr[i][1]!=null) {
				count++;
			}else {
				break;
			}
			
			if(!oldArr[i][1].toString().equals(newArr[i][1])) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "学号不可更改！", "修改失败", 0);
				break;
			}
			
			if(!oldArr[i][4].toString().equals(newArr[i][4])) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "寝室号不可更改！", "修改失败", 0);
				break;
			}
			
			if(!oldArr[i][5].toString().equals(newArr[i][5])) {
				enAlter = false;
				JOptionPane.showMessageDialog(tcf, "床号不可更改！", "修改失败", 0);
				break;
			}
		}
		
		System.out.println("有效行数："+count);
		
		if(enAlter) {	//输入条件均正确后再做进一步判断！
			
			enAlter = false; 	//默认新旧数据都一样！
			String[][] splicingArr = new String[4][2];
			//将数组中的信息，还原为文件指定的格式
			for(int i = 0; i<count; i++) {
				String oldinfo = "";
				String newinfo = "";
				for(int j = 0; j<oldArr[i].length; j++) {		
					if(j!=oldArr[i].length-1) {	
						oldinfo = oldinfo+oldArr[i][j]+"#";
						newinfo = newinfo+newArr[i][j]+"#";
					}else {
						oldinfo = oldinfo+oldArr[i][j];
						newinfo = newinfo+newArr[i][j];
					}
				}
				splicingArr[i][0] = oldinfo;	//左边是旧的
				splicingArr[i][1] = newinfo;	//右边是新的
						
				if(!oldinfo.equals(newinfo)) {	//存在不同数据则可更改！
					enAlter = true;
				}
			}
			
			
			if(!enAlter) {
				JOptionPane.showMessageDialog(tcf, "信息没有改动！", "修改失败", 0);
			}else {
				
				//先读取学生信息文件，遇到相同，就替换，最后生成的文件再重命名回AddStudent.txt
				try {
					br = new BufferedReader(new FileReader(file));
					try {
						bw = new BufferedWriter(new FileWriter(file1));
					} catch (IOException e1) {
						System.out.println("信息文件更新失败!");
					}
					String line = null;
					try {
						int i = 0;
						while((line = br.readLine()) != null) {
							if((i!=count) && line.equals(splicingArr[i][0])) {
								bw.write(splicingArr[i][1]);
								bw.newLine();
								i++; 
							}else {
								bw.write(line);
								bw.newLine();
							}
							bw.flush();
							
						}
					} catch (IOException e) {
						System.out.println("存储被添加的用户信息的文件找不到！");
					}
				} catch (FileNotFoundException e) {
					System.out.println("查询失败！");
				} finally {
					if(bw!=null && br!=null) {
						try {
							bw.close();
							br.close();
							file.delete();
							file1.renameTo(file);
							JOptionPane.showMessageDialog(tcf, "信息已变更！", "修改成功", 1);
						} catch (IOException e) {
							System.out.println("查询功能关闭资源失败！");
						}
					}
				}
			}
			//else结束
			
			
			
			
		}			
	}
}
