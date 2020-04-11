package teacherClient.handleEnter;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import teacherClient.hander.AddComonents;
import teacherClient.hander.BackRequestInfo;
import teacherClient.hander.GetRequestInfo;
import teacherClient.hander.JudgeInfo;
import teacherClient.hander.ReadRequestDno;
import teacherClient.hander.ReadRequestEnter;
import teacherClient.hander.UpPanel;

public class HandleEnter {
	
	private JFrame tcf;
	private JPanel jp4_1,jp4_1_1,jp4_1_2,jp4_1_3,jp4_1_4;
	private JButton jb1,jb2,jb3,jb4;
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13;
	private JTextArea jta1,jta2;
	private JTextField jtf1,jtf2;
	private List<JPanel> list;
	private File file = new File("AddStudent.txt");
	private File file1 = new File("BindingInfo.txt");
	
	public HandleEnter(JFrame tcf, JPanel jp4_1, List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp4_1 = jp4_1;
		this.list = list;
		init();
		initMessage();
	}
	
	public void init() {
		
		//子面板1
		jp4_1_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 150);		
		jb1 = AddComonents.addJButton("查看更换寝室申请", jp4_1_1, 200, 70, 150, 50, true, true, false);jb1.setVisible(true);
		jb2 = AddComonents.addJButton("查看新人入住申请", jp4_1_1, 400, 70, 150, 50, true, true, false);jb2.setVisible(true);
		
		
		//子面板2
		jp4_1_2 = AddComonents.addJPanel(tcf, 0, 150, 773, 505);
		jl1 = AddComonents.addJLabel("（暂无申请消息）", jp4_1_2, 330, 150, 150, 30);jl1.setVisible(false);
		jl2 = AddComonents.addJLabel("（你收到"+GetRequestInfo.getDnoMessageCount()+"条更换寝室号的申请）", jp4_1_2, 290, 150, 200, 30);jl2.setVisible(false);
		jl7 = AddComonents.addJLabel("（你收到"+GetRequestInfo.getEnterMessageCount()+"条新用户 入住的申请）", jp4_1_2, 290, 180, 200, 30);jl7.setVisible(false);
		
		//子面板3
		jp4_1_3 = AddComonents.addJPanel(tcf, 0, 150, 773, 505);
		jl3 = AddComonents.addJLabel("申请人姓名："+ReadRequestDno.getName(), jp4_1_3, 300, 50, 120, 30);jl3.setVisible(true);
		jl4 = AddComonents.addJLabel("申请人学号："+ReadRequestDno.getSno(), jp4_1_3, 300, 80, 150, 30);jl4.setVisible(true);
		jl5 = AddComonents.addJLabel("目前寝室号："+ReadRequestDno.getOldDno(), jp4_1_3, 300, 110, 150, 30);jl5.setVisible(true);
		jl6 = AddComonents.addJLabel("申请更换为："+ReadRequestDno.getNewDno(), jp4_1_3, 300, 140, 150, 30);jl6.setVisible(true);
		jta1 = AddComonents.addJTextArea(jp4_1_3, 190, 200, 370, 150);jta1.setVisible(false);
		jta1.setText("（处理后留言框，最多输入300个字符！）");
		jb3 = AddComonents.addJButton("已处理", jp4_1_3, 300, 400, 120, 50, false, true, false);jb3.setVisible(true);
		
		//子面板4
		jp4_1_4 = AddComonents.addJPanel(tcf, 0, 150, 773, 505);
		jl8 = AddComonents.addJLabel("申请人姓名："+ReadRequestEnter.getName(), jp4_1_4, 270, 20, 120, 30);jl8.setVisible(true);
		jl9 = AddComonents.addJLabel("申请人学号："+ReadRequestEnter.getSno(), jp4_1_4, 270, 50, 150, 30);jl9.setVisible(true);
		jl10 = AddComonents.addJLabel("申请人学院："+ReadRequestEnter.getDpar(), jp4_1_4, 270, 80, 150, 30);jl10.setVisible(true);
		jl11 = AddComonents.addJLabel("申请人班级："+ReadRequestEnter.getClasses(), jp4_1_4, 270, 110, 150, 30);jl11.setVisible(true);
		jta2 = AddComonents.addJTextArea(jp4_1_4, 190, 240, 370, 150);jta2.setVisible(false);
		jta2.setText("（处理后留言框，最多输入300个字符！）");
		jb4 = AddComonents.addJButton("已处理", jp4_1_4, 300, 430, 120, 50, false, true, false);jb4.setVisible(true);
		jl12 = AddComonents.addJLabel("安排到寝室：", jp4_1_4, 270, 150, 150, 30);jl12.setVisible(true);
		jl13 = AddComonents.addJLabel("安排到床号：", jp4_1_4, 270, 190, 150, 30);jl13.setVisible(true);
		jtf1 = AddComonents.addJTextField(jp4_1_4, 350, 150, 150, 30);		  jtf1.setVisible(false);
		jtf2 = AddComonents.addJTextField(jp4_1_4, 350, 190, 150, 30);		  jtf2.setVisible(false);
		
		jp4_1.add(jp4_1_1);
		jp4_1.add(jp4_1_2);
		jp4_1.add(jp4_1_3);
		jp4_1.add(jp4_1_4);
		list.add(jp4_1);
		UpPanel.upPanel(list, jp4_1, null);		//更新面板
		
		jb1.addActionListener(new ActionListener() {		//查看寝室号申请按钮
			public void actionPerformed(ActionEvent e) {
				if(GetRequestInfo.getDnoMessageCount()==0) {
					JOptionPane.showMessageDialog(tcf, "暂无申请消息！", "提示", 0);
				} else {
					jp4_1_2.setVisible(false);
					jp4_1_3.setVisible(true);
					jp4_1_4.setVisible(false);
					jta1.setVisible(true);
				}
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//查看入住申请按钮
			public void actionPerformed(ActionEvent e) {
				if(GetRequestInfo.getEnterMessageCount()==0) {
					JOptionPane.showMessageDialog(tcf, "暂无申请消息！", "提示", 0);
				} else {
					jp4_1_2.setVisible(false);
					jp4_1_3.setVisible(false);jta1.setVisible(false);
					jp4_1_4.setVisible(true);
					jta2.setVisible(true);
					jtf1.setVisible(true);
					jtf2.setVisible(true);
				}
			}
		});
		
		jb3.addActionListener(new ActionListener() {		//处理按钮1
			@Override
			public void actionPerformed(ActionEvent e) {
				BackRequestInfo.setDnoResponse(jta1.getText());
				ReadRequestDno.flushRequestInfo();
				jta1.setText("");
				JOptionPane.showMessageDialog(tcf, "当前请求已处理！", "处理成功", 1);
			}
		});
		
		jb4.addActionListener(new ActionListener() {		//处理按钮2
			public void actionPerformed(ActionEvent e) {
				addStudent();
			}
		});
	}
	
	public void addStudent() {
		
		String dnoRegex = "^[1-9]-\\d{3}$";	//最多为9栋宿舍楼
		String bedRegex = "^[1-4]$";		//床号1-4
		String s1 = ReadRequestEnter.getName();
		String s2 = ReadRequestEnter.getSno();
		String s3 = ReadRequestEnter.getDpar();
		String s4 = ReadRequestEnter.getClasses();
		String s5 = jtf1.getText().trim();
		String s6 = jtf2.getText().trim();
		
		if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
			JOptionPane.showMessageDialog(tcf, "必要信息没有填完，请重新输入！", "添加失败", 0);
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
			
			//同时添加到绑定用户文件中
			try {
				bw = new BufferedWriter(new FileWriter(file1, true));
				bw.write(s1+"#"+s2+"#"+s5+"#"+ReadRequestEnter.getUsername());
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				System.out.println("更新绑定信息失败！");
			} finally {
				if(bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						System.out.println("更新绑定信息关闭资源失败！");
					}
				}
			}
			BackRequestInfo.setEnterResponse(jta2.getText(), s2);
			ReadRequestEnter.flushRequestInfo();
		}
	}
	
	public void initMessage() {
		int m1 = GetRequestInfo.getDnoMessageCount();
		int m2 = GetRequestInfo.getEnterMessageCount();
		if(m1 != 0 && m2 == 0) {
			jl1.setVisible(false);
			jl2.setVisible(true);
			jl7.setVisible(true);
			JOptionPane.showMessageDialog(tcf, "你收到"+m1+"条更换寝室号的申请，请留意处理！", "收到申请消息", 1);
		} else if(m1 == 0 && m2 != 0){
			jl1.setVisible(false);
			jl2.setVisible(true);
			jl7.setVisible(true);
			JOptionPane.showMessageDialog(tcf, "你收到"+m2+"条新用户 入住的申请，请留意处理！", "收到申请消息", 1);
		} else {
			jl1.setVisible(true);
			jl2.setVisible(false);
			jl7.setVisible(false);
		}
	}
}
