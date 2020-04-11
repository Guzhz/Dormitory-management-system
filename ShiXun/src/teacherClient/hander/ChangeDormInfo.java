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

public class ChangeDormInfo {
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	private static File file = new File("DormNumber.txt");
	private static File file1 = new File("DormNumber1.txt");
	
	
	public static void changeInfo(JFrame tcf,Object[][] arr) {
		
		boolean edited = false;	//默认认为没有改动
		if(!arr[0][0].equals(arr[1][0])) {
			edited = true;
		}
		
		if(!edited) {
			JOptionPane.showMessageDialog(tcf, "信息没有改动！", "修改失败", 0);
		} else if(!JudgeInfo.isOnlyDno(arr[1][0].toString())){
			JOptionPane.showMessageDialog(tcf, "该寝室已存在！", "修改失败", 0);
		} else{
		//先读取寝室号信息文件，遇到相同，就替换，最后生成的文件再重命名回DormNumber.txt
			try {
				br = new BufferedReader(new FileReader(file));
				try {
					bw = new BufferedWriter(new FileWriter(file1));
				} catch (IOException e1) {
					System.out.println("信息文件更新失败!");
				}
				String line = null;
				try {
					while((line = br.readLine()) != null) {
						if(line.equals(arr[0][0].toString())) {
							bw.write(arr[1][0].toString());
							bw.newLine();
						}else {
							bw.write(line);
							bw.newLine();
						}
						bw.flush();
					}
				} catch (IOException e) {
					System.out.println("存储被寝室号的的文件找不到！");
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
	}
	
	
}
