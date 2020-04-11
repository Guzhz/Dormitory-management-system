package teacherClient.core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import teacherClient.hander.AddComonents;
import teacherClient.hander.UpPanel;
import teacherClient.response.Response;
import teacherClient.ui.Global;
import teacherClient.ui.Ui;

/**
 * 教师窗体类
 * @author 陌生人
 *
 */

public class TeacherClientFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame lf = null;
	private JPanel jp0,jp1,jp2;
	private JPanel jp2_1,jp2_2,jp2_3,jp2_4,jp2_5;
	private JLabel label1,label2;
	private JButton jb1,jb1_1,jb1_2,jb1_3;
	private JButton jb2,jb2_1;
	private JButton jb3,jb3_1,jb3_2,jb3_3;
	private JButton jb4,jb4_1;
	private JButton jb5,jb5_1;
	private String username;
	private List<JPanel> list = new ArrayList<JPanel>();
	private Color c1 = new Color(187,206,219);
	
	public TeacherClientFrame(JFrame lf, String username) {
		super();
		this.lf = lf;
		this.username = username;
		initFrame();
		initComonents();
	}
	//设置基本窗口
	private void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("寝室管理系统");
		this.setResizable(false);
		Ui.setFrameCenter(this);
		Ui.setFrameImage(this, "Login.jpg");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(true);
	}
	//设置基本组件
	private void initComonents() {
		// TODO Auto-generated method stub
		jp0 = Ui.setBackstageUI2(this);
		jp1 = Ui.setBackstageUI(this);
		list.add(jp0);
		UpPanel.upPanel(list, jp0, null);
		
		//在面板jp2上再添加5个子面板，然后把每个组件添加到子面板中
		jp2 = AddComonents.addJPanel(this, 0, 71, 180, 730); jp2.setBackground(c1); 	//左边工具栏
		label1 = AddComonents.addJLabel("欢迎您："+username+"！", jp2, 40, 620, 180, 30); label1.setVisible(true);
		label2 = AddComonents.addJLabel("（教师）", jp2, 60, 650, 180, 30); label2.setVisible(true);
		jp2_1 = AddComonents.addJPanel_2(jp2, 0, 0, 180, 50);jp2_1.setBackground(c1);	//y=0,150 150;
		jp2_2 = AddComonents.addJPanel_2(jp2, 0, 50, 180, 50);jp2_2.setBackground(c1); //y=150,240 90;
		jp2_3 = AddComonents.addJPanel_2(jp2, 0, 100, 180, 50);jp2_3.setBackground(c1); //y=240,390 150;
		jp2_4 = AddComonents.addJPanel_2(jp2, 0, 150, 180, 50);jp2_4.setBackground(c1); //y=390,510 120;
		jp2_5 = AddComonents.addJPanel_2(jp2, 0, 200, 180, 50);jp2_5.setBackground(c1); //y=510,600 90;
		 
		//参数1是否透明，参数2是否要边框，参数3是否居左
		jb1 = AddComonents.addJButton("▶　学生管理",     jp2_1, 0, 0, 180, 50, false, true, true); jb1.setVisible(true);
		jb1_1 = AddComonents.addJButton("□　添加学生",   jp2_1, 0, 50, 180, 30, true, false, true);jb1_1.setVisible(true);
		jb1_2 = AddComonents.addJButton("□　修改学生信息", jp2_1, 0, 80, 180, 30, true, false, true);jb1_2.setVisible(true);
		jb1_3 = AddComonents.addJButton("□　查询学生信息", jp2_1, 0, 110, 180, 30, true, false, true);jb1_3.setVisible(true);

		jb2 = AddComonents.addJButton("▶　房间添加",      jp2_2, 0, 0, 180, 50, false, true, true);jb2.setVisible(true);
		jb2_1 = AddComonents.addJButton("□　添加房间",    jp2_2, 0, 50, 180, 30, true, false, true);jb2_1.setVisible(true);

		jb3 = AddComonents.addJButton("▶　房间管理",       jp2_3, 0, 0, 180, 50, false, true, true);jb3.setVisible(true);
		jb3_1 = AddComonents.addJButton("□　查询寝室信息",  jp2_3, 0, 50, 180, 30, true, false, true);jb3_1.setVisible(true);
		jb3_2 = AddComonents.addJButton("□　删除寝室",     jp2_3, 0, 80, 180, 30, true, false, true);jb3_2.setVisible(true);
		jb3_3 = AddComonents.addJButton("□　修改寝室",     jp2_3, 0, 110, 180, 30, true, false, true);jb3_3.setVisible(true);
		
		jb4 = AddComonents.addJButton("▶　办理学生入住",    jp2_4, 0, 0, 180, 50, false, true, true);jb4.setVisible(true);
		jb4_1 = AddComonents.addJButton("□　审批学生办理",  jp2_4, 0, 50, 180, 30, true, false, true);jb4_1.setVisible(true);

		jb5 = AddComonents.addJButton("▶　系统设置",      jp2_5, 0, 0, 180, 50, false, true, true);jb5.setVisible(true);
		jb5_1 = AddComonents.addJButton("□　退出系统",    jp2_5, 0, 50, 180, 30, true, false, true);jb5_1.setVisible(true);
		 
		
		
		//new一个响应对象，依次对每个按钮进行响应
		Response r = new Response(this, list, lf, jp1, jp2, jp2_1, jp2_2, 
				jp2_3, jp2_4, jp2_5, jb1, jb1_1, jb1_2, jb1_3, jb2, 
				jb2_1, jb3, jb3_1, jb3_2, jb3_3, jb4, jb4_1, jb5, jb5_1);
		
		
		jb1.addActionListener(new ActionListener() {		//1
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb1Fold();
			}
		});
		jb1_1.addActionListener(new ActionListener() {		//1-1学生添加
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.addStudentInfo();
			}
		});
		jb1_2.addActionListener(new ActionListener() {		//1-2学生修改
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.alterStudentInfo();
			}
		});
		jb1_3.addActionListener(new ActionListener() {		//1-3学生查询
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.searchStudentInfo();
			}
		});
		jb2.addActionListener(new ActionListener() {		//2
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb2Fold();
			}
		});
		
		jb2_1.addActionListener(new ActionListener() {		//2-1
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.addDorm();
			}
		});
		
		jb3.addActionListener(new ActionListener() {		//3
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb3Fold();
			}
		});
		
		jb3_1.addActionListener(new ActionListener() {		//3-1
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.searchDormInfo();
			}
		});
		
		jb3_2.addActionListener(new ActionListener() {		//3-2
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.deleteDorm();
			}
		});
			
		jb3_3.addActionListener(new ActionListener() {		//3-3
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.alterDorm();
			}
		});
		
		jb4.addActionListener(new ActionListener() {		//4
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb4Fold();
			}
		});
		
		jb4_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.receivingRequest();
			}
		});
		
		jb5.addActionListener(new ActionListener() {		//5
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb5Fold();
			}
		});
		jb5_1.addActionListener(new ActionListener() {		//5-1
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.exitSystem();
			}
		});
		
	}
	
}
