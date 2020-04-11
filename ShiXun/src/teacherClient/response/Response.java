package teacherClient.response;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import teacherClient.addDorm.AddDorm;
import teacherClient.dormManage.AlterDorm;
import teacherClient.dormManage.DeleteDorm;
import teacherClient.dormManage.SearchDormInfo;
import teacherClient.hander.AddComonents;
import teacherClient.hander.SortInfo;
import teacherClient.handleEnter.HandleEnter;
import teacherClient.studentManage.AlterStudentInfo;
import teacherClient.studentManage.SearchStudentInfo;
import teacherClient.ui.Ui;
import teacherClient.studentManage.AddStudentInfo;

/**
 * 教师窗体按键响应类
 * @author 陌生人
 *
 */

public class Response {

	private JFrame tcf = null;
	private JFrame lf = null;
	private JPanel jp1,jp2;
	private JPanel jp2_1,jp2_2,jp2_3,jp2_4,jp2_5;
	
	private JButton jb1,jb1_1,jb1_2,jb1_3;
	private JButton jb2,jb2_1;
	private JButton jb3,jb3_1,jb3_2,jb3_3;
	private JButton jb4,jb4_1;
	private JButton jb5,jb5_1;
	
	private boolean jp1FoldFlag = false;
	private boolean jp2FoldFlag = false;
	private boolean jp3FoldFlag = false;
	private boolean jp4FoldFlag = false;
	private boolean jp5FoldFlag = false;
	
	private int size1 = 150;
	private int size2 = 90;
	private int size3 = 150;
	private int size4 = 90;
	private int size5 = 90;
	
	private int y1 = 0;
	private int y2 = 50;
	private int y3 = 100;
	private int y4 = 150;
	private int y5 = 200;
	
	private int x = 180;
	private int flod = 50;
	
	private Color c1 = new Color(187,206,219);
	
	private List<JPanel> list;
	
	public Response(JFrame tcf, List<JPanel> list,JFrame lf, JPanel jp1, JPanel jp2, JPanel jp2_1, JPanel jp2_2, JPanel jp2_3,
			JPanel jp2_4, JPanel jp2_5, JButton jb1, JButton jb1_1, JButton jb1_2, JButton jb1_3, JButton jb2,
			JButton jb2_1, JButton jb3, JButton jb3_1, JButton jb3_2, JButton jb3_3, JButton jb4, JButton jb4_1,
			JButton jb5, JButton jb5_1) {
		super();
		this.tcf = tcf;
		this.list = list;
		this.lf = lf;
		this.jp1 = jp1;
		this.jp2 = jp2;
		this.jp2_1 = jp2_1;
		this.jp2_2 = jp2_2;
		this.jp2_3 = jp2_3;
		this.jp2_4 = jp2_4;
		this.jp2_5 = jp2_5;
		this.jb1 = jb1;
		this.jb1_1 = jb1_1;
		this.jb1_2 = jb1_2;
		this.jb1_3 = jb1_3;
		this.jb2 = jb2;
		this.jb2_1 = jb2_1;
		this.jb3 = jb3;
		this.jb3_1 = jb3_1;
		this.jb3_2 = jb3_2;
		this.jb3_3 = jb3_3;
		this.jb4 = jb4;
		this.jb4_1 = jb4_1;
		this.jb5 = jb5;
		this.jb5_1 = jb5_1;
	}

	public Response() {
		super();
	}

	
	public void jb1Fold() {					//1
		if(jp1FoldFlag) {
			jb1.setText("▶　学生管理");
			jp2_1.setSize(x,flod);
			toUp(y2,y3,y4,y5,size1);
			jp1FoldFlag = false;
		} else {
			jb1.setText("▼　学生管理");
			jp2_1.setSize(x, size1);
			toDown(y2, y3, y4, y5, size1);
			jp1FoldFlag = true;
		}
	}
	
	public void addStudentInfo() {			//1-1
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new AddStudentInfo(tcf,jp,list);
		jp.setVisible(true);
	}
	
	public void alterStudentInfo() {		//1-2
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new AlterStudentInfo(tcf,jp,list);
		jp.setVisible(true);
	}
	
	public void searchStudentInfo() {		//1-3
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 500);		//这个传过去的是用于存放表格的面板
		new SearchStudentInfo(tcf,jp,list);
		jp.setVisible(true);
	}
	
	public void jb2Fold() {					//2
		if(jp2FoldFlag) {
			jb2.setText("▶　房间添加");
			jp2_2.setSize(x,flod);
			toUp(0, y3, y4, y5, size2);
			jp2FoldFlag = false;
		} else {
			jb2.setText("▼　房间添加");
			jp2_2.setSize(x, size2);
			toDown(0, y3, y4, y5, size2);
			jp2FoldFlag = true;
		}
	}
	
	public void addDorm() {					//2-1
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new AddDorm(tcf, jp, list);
		jp.setVisible(true);
	}
	
	public void jb3Fold() {					//3
		if(jp3FoldFlag) {
			jb3.setText("▶　房间管理");
			jp2_3.setSize(x, flod);
			toUp(0, 0, y4, y5, size3);
			jp3FoldFlag = false;
		} else {
			jb3.setText("▼　房间管理");
			jp2_3.setSize(x, size3);
			toDown(0, 0, y4, y5, size3);
			jp3FoldFlag = true;
		}
	}
	
	public void searchDormInfo() {			//3-1
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new SearchDormInfo(tcf, jp, list);
		jp.setVisible(true);
	}
	
	public void deleteDorm() {				//3-2
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new DeleteDorm(tcf, jp, list);
		jp.setVisible(true);
	}
	
	public void alterDorm() {				//3-3
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new AlterDorm(tcf, jp, list);
		jp.setVisible(true);
	}
	
	public void jb4Fold() {					//4
		if(jp4FoldFlag) {
			jb4.setText("▶　办理学生入住");
			jp2_4.setSize(x, flod);
			toUp(0, 0, 0, y5, size4);
			jp4FoldFlag = false;
		} else {
			jb4.setText("▼　办理学生入住");
			jp2_4.setSize(x, size4);
			toDown(0, 0, 0, y5, size4);
			jp4FoldFlag = true;
		}
	}
	
	public void receivingRequest() {	//4-1
		JPanel jp = AddComonents.addJPanel(tcf, 200, 90, 773, 655);
		new HandleEnter(tcf, jp, list);
		jp.setVisible(true);
	}
	
	public void jb5Fold() {			//系统设置折叠
		if(jp5FoldFlag) {
			jb5.setText("▶　系统设置");
			jp2_5.setSize(x, flod);
			jp5FoldFlag = false;
		} else {
			jb5.setText("▼　系统设置");
			jp2_5.setSize(x, size5);
			jp5FoldFlag = true;
		}
	}
	
	
	public void exitSystem() {		//退出系统
		tcf.setVisible(false);
		lf.setVisible(true);
		lf.setTitle("未登录");
		Ui.setFrameImage(lf, "NoLogin.jpg");
	}
	
	public void toUp(int a,int b, int c, int d,int temp) {
		if(a!=0) {
			a = a-temp+flod; y2 = a;
		}
		if(b!=0) {
			b = b-temp+flod; y3 = b;
		}
		if(c!=0) {
			c = c-temp+flod; y4 = c;
		}
		if(d!=0) {
			d = d-temp+flod; y5 = d;
		}
		jp2_2.setLocation(0, y2);
		jp2_3.setLocation(0, y3);
		jp2_4.setLocation(0, y4);
		jp2_5.setLocation(0, y5);
	}
	public void toDown(int a,int b, int c, int d,int temp) {
		if(a!=0) {
			a = a+temp-flod; y2 = a;
		}
		if(b!=0) {
			b = b+temp-flod; y3 = b;
		}
		if(c!=0) {
			c = c+temp-flod; y4 = c;
		}
		d = d+temp-flod; y5 = d;
		jp2_2.setLocation(0, y2);
		jp2_3.setLocation(0, y3);
		jp2_4.setLocation(0, y4);
		jp2_5.setLocation(0, y5);
	}
	
}
