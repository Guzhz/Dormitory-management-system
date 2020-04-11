package teacherClient.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import teacherClient.hander.AddComonents;
import teacherClient.hander.JudgeInfo;
import teacherClient.hander.UpPanel;

/**
 * 添加学生信息类
 * @author 陌生人
 *
 */

public class AddStudentInfo {
	private JFrame tcf = null;
	private JPanel jp1_1;
	
	private JLabel jl0,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
	private JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	private JButton jb1,jb2;
	
	private List<JPanel> list;
	//创建一个用于存放学生信息的文件
	private static File file = new File("AddStudent.txt");
	
	public AddStudentInfo(JFrame tcf,JPanel jp1_1,List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp1_1 = jp1_1;
		this.list = list;
		init();
	}
	
	//初始化组件
	public void init() {
		
		list.add(jp1_1);
		UpPanel.upPanel(list, jp1_1, null);		//更新面板
		
		jl0 = AddComonents.addJLabel("请输入学生信息：", jp1_1, 20, 0, 150, 50);jl0.setVisible(true);
		jl1 = AddComonents.addJLabel("姓名：", jp1_1, 220, 120, 70, 50);jl1.setVisible(true);
		jl2 = AddComonents.addJLabel("学号：", jp1_1, 220, 170, 70, 50);jl2.setVisible(true);
		jl3 = AddComonents.addJLabel("学院：", jp1_1, 220, 220, 70, 50);jl3.setVisible(true);
		jl4 = AddComonents.addJLabel("班级：", jp1_1, 220, 270, 70, 50);jl4.setVisible(true);
		jl5 = AddComonents.addJLabel("寝室号：", jp1_1, 220, 320, 70, 50);jl5.setVisible(true);
		jl6 = AddComonents.addJLabel("床号：", jp1_1, 220, 370, 70, 50);jl6.setVisible(true);
		
		jl7 = AddComonents.addJLabel("(10位纯数字)",    jp1_1, 500, 170, 150, 50);jl7.setVisible(true);
		jl8 = AddComonents.addJLabel("(例如：5-523)", jp1_1, 500, 320, 150, 50);jl8.setVisible(true);
		jl9 = AddComonents.addJLabel("(1~4)",       jp1_1, 500, 370, 150, 50);jl9.setVisible(true);
		
		jtf1 = AddComonents.addJTextField(jp1_1, 290, 130, 200, 30);jtf1.setVisible(true);jtf1.requestFocus();
		jtf2 = AddComonents.addJTextField(jp1_1, 290, 180, 200, 30);jtf2.setVisible(true);
		jtf3 = AddComonents.addJTextField(jp1_1, 290, 230, 200, 30);jtf3.setVisible(true);
		jtf4 = AddComonents.addJTextField(jp1_1, 290, 280, 200, 30);jtf4.setVisible(true);
		jtf5 = AddComonents.addJTextField(jp1_1, 290, 330, 200, 30);jtf5.setVisible(true);
		jtf6 = AddComonents.addJTextField(jp1_1, 290, 380, 200, 30);jtf6.setVisible(true);
		
		jb1 = AddComonents.addJButton("确认", jp1_1, 420, 500, 100, 50, false, true, false);jb1.setVisible(true);
		jb2 = AddComonents.addJButton("重置", jp1_1, 260, 500, 100, 50, false, true, false);jb2.setVisible(true);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudentInfo();
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
	}

	public void getStudentInfo() {
		
		String snoRegex = "^\\d{10}$";	//必须为10位纯数字
		String dnoRegex = "^[1-9]-\\d{3}$";	//最多为9栋宿舍楼
		String bedRegex = "^[1-4]$";		//床号1-4
		String s1 = jtf1.getText().trim();
		String s2 = jtf2.getText().trim();
		String s3 = jtf3.getText().trim();
		String s4 = jtf4.getText().trim();
		String s5 = jtf5.getText().trim();
		String s6 = jtf6.getText().trim();
		
		
		
		if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
			JOptionPane.showMessageDialog(tcf, "必要信息没有填完，请重新输入！", "添加失败", 0);
		} else if(!s2.matches(snoRegex)){
			JOptionPane.showMessageDialog(tcf, "学号必须为10位数字！", "添加失败", 0);
		} else if(!JudgeInfo.isOnlySno(s2)) {
			JOptionPane.showMessageDialog(tcf, "当前学号已经存在！", "添加失败", 0);
		} else if(!s5.matches(dnoRegex)){
			JOptionPane.showMessageDialog(tcf, "请输入正确的寝室号！", "添加失败", 0);
		} else if(JudgeInfo.isLimit(s5)) {
			JOptionPane.showMessageDialog(tcf, "当前寝室人数已经上限！", "添加失败", 0);
		} else if(!s6.matches(bedRegex)){
			//判断床号是否正确
			JOptionPane.showMessageDialog(tcf, "请输入正确床号！", "添加失败", 0);
		} else if(!JudgeInfo.bedIsEmpty(s5, s6)) {
			JOptionPane.showMessageDialog(tcf, "该床号已经被使用！", "添加失败", 0);
		}else{
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(s1+"#"+s2+"#"+s3+"#"+s4+"#"+s5+"#"+s6);
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				System.out.println("添加学生信息失败！");
			} finally {
				if(bw != null) {
					try {
						bw.close();
						JOptionPane.showMessageDialog(tcf, "添加学生成功！");
					} catch (IOException e) {
						System.out.println("添加学生信息关闭资源失败！");
					}
				}
			}
		}
	}
	
	public void reset() {
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
		jtf4.setText("");
		jtf5.setText("");
		jtf6.setText("");
		jtf1.requestFocus();
	}
	
}
