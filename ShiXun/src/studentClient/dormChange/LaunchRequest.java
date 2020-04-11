package studentClient.dormChange;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import studentClient.hander.BindingInfo;
import studentClient.hander.Request;
import teacherClient.hander.AddComonents;
import teacherClient.hander.BackRequestInfo;
import teacherClient.hander.UpPanel;

/**
 * 学生发起请求类
 * @author 陌生人
 *
 */

public class LaunchRequest {

	private JFrame scf;
	private JPanel jp2_1,   jp2_1_1,jp2_1_2,jp2_1_3,jp2_1_4;
	private JButton jb1,jb2,jb3,jb4;
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;
	private JTextField jtf1,jtf2,jtf3,jtf4,jtf5;			
	private List<JPanel> list;
	
	private String username;

	public LaunchRequest(JFrame scf, JPanel jp2_1, List<JPanel> list, String username) {
		super();
		this.scf = scf;
		this.jp2_1 = jp2_1;
		this.list = list;
		this.username = username;
		init();
		newMessage();
		
	}
	
	private void init() {

		//子面板1
		jp2_1_1 = AddComonents.addJPanel(scf, 0, 0, 773, 150);		
		jl1 = AddComonents.addJLabel("请选择你需要发起的请求类型：", jp2_1_1, 20, 0, 300, 50);jl1.setVisible(true);
		jb1 = AddComonents.addJButton("申请更换寝室", jp2_1_1, 200, 70, 150, 50, true, true, false);jb1.setVisible(true);
		jb2 = AddComonents.addJButton("申请新人入住", jp2_1_1, 400, 70, 150, 50, true, true, false);jb2.setVisible(true);
		
		//子面板2
		jp2_1_2 = AddComonents.addJPanel(scf, 0, 150, 773, 505);	
		if(BindingInfo.isBinding(username)) {
			jl2 = AddComonents.addJLabel("您当前的寝室号为："+BindingInfo.getDno(username), jp2_1_2, 300, 150, 150, 30);jl2.setVisible(true);
		}else {
			jl2 = AddComonents.addJLabel("（您还未入住）", jp2_1_2, 330, 150, 150, 30);jl2.setVisible(true);
		}
		jl3 = AddComonents.addJLabel("申请更换为：", jp2_1_2, 260, 220, 150, 30);jl3.setVisible(false);
		jtf1 = AddComonents.addJTextField(jp2_1_2, 345, 220, 150, 30);jtf1.setVisible(false);
		jb3 = AddComonents.addJButton("发起申请", jp2_1_2, 330, 350, 100, 50, false, true, false);jb3.setVisible(false);
		
		//子面板3
		jp2_1_3 = AddComonents.addJPanel(scf, 0, 150, 773, 505);jp2_1_3.setVisible(false);
		jl4 = AddComonents.addJLabel("请输入基本信息：", jp2_1_3, 330, 30, 150, 30);jl4.setVisible(true);
		jl5 = AddComonents.addJLabel("姓名：", jp2_1_3,    260, 90, 150, 30);jl5.setVisible(true);
		jtf2 = AddComonents.addJTextField(jp2_1_3, 		 320, 90, 150, 30);jtf2.setVisible(true);
		jl6 = AddComonents.addJLabel("学号：", jp2_1_3,     260, 140, 150, 30);jl6.setVisible(true);
		jtf3 = AddComonents.addJTextField(jp2_1_3,       320, 140, 150, 30);jtf3.setVisible(true);
		jl7 = AddComonents.addJLabel("学院：", jp2_1_3,     260, 190, 150, 30);jl7.setVisible(true);
		jtf4 = AddComonents.addJTextField(jp2_1_3,       320, 190, 150, 30);jtf4.setVisible(true);
		jl8 = AddComonents.addJLabel("班级：", jp2_1_3, 	260, 240, 150, 30);jl8.setVisible(true);
		jtf5 = AddComonents.addJTextField(jp2_1_3, 		320, 240, 150, 30);jtf5.setVisible(true);
		jb4 = AddComonents.addJButton("发起申请", jp2_1_3, 330, 350, 100, 50, false, true, false);jb4.setVisible(true);
		
		//子面板4
		jp2_1_4 = AddComonents.addJPanel(scf, 0, 150, 773, 505);jp2_1_4.setVisible(false);
		
		
		jp2_1.add(jp2_1_1);
		jp2_1.add(jp2_1_2);
		jp2_1.add(jp2_1_3);
		jp2_1.add(jp2_1_4);
		list.add(jp2_1);
		UpPanel.upPanel(list, jp2_1, null);		//更新面板
		
		jb1.addActionListener(new ActionListener() {		//申请更换寝室
			public void actionPerformed(ActionEvent e) {
				if(!BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "请您先入住！", "操作失败", 0);
				}else {
					jp2_1_2.setVisible(true);
					jl3.setVisible(true);
					jtf1.setVisible(true);
					jb3.setVisible(true);
					jp2_1_3.setVisible(false);
				}
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//申请入住
			public void actionPerformed(ActionEvent e) {
				if(BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "你已经入住了！", "操作失败", 0);
				}else {
					jp2_1_2.setVisible(false);
					jp2_1_3.setVisible(true);
				}
			}
		});
		
		jb3.addActionListener(new ActionListener() {		//申请按钮1
			public void actionPerformed(ActionEvent e) {
				String dno = jtf1.getText().trim();
				if(!dno.matches("^[1-9]-\\d{3}$")) {
					JOptionPane.showMessageDialog(scf, "寝室号格式有误！", "录入失败", 0);
				}else {
					Request.setDnoRequestInfo(username, dno);
					JOptionPane.showMessageDialog(scf, "你已发起更换寝室号请求成功，请等待老师审核！", "请求成功", 1);
				}
			}
		});
		
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sno = jtf3.getText().trim();		//判断学号格式
				if(!sno.matches("^\\d{10}$")) {
					JOptionPane.showMessageDialog(scf, "学号格式有误！", "录入失败", 0);
				}else {
					Request.setEnterRequestInfo(jtf2.getText().trim(), sno, jtf4.getText().trim(), jtf5.getText().trim(), username);
					JOptionPane.showMessageDialog(scf, "你发起申请入住请求成功，请等待老师审核！", "请求成功", 1);
				}
			}
		});
	}

	private void newMessage() {
		String message1 = BackRequestInfo.getDnoResponse(username);
		String message2 = BackRequestInfo.getEnterResponse(username);	//用用户名去匹配响应的留言信息
		if(message1!="") {
			JOptionPane.showMessageDialog(scf, message1, "你的申请老师已处理", 1);
		}
		if(message2!=""){
			JOptionPane.showMessageDialog(scf, message2, "你的申请老师已处理", 1);
		}
	}
}
