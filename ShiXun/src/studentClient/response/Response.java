package studentClient.response;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import studentClient.dormChange.LaunchRequest;
import studentClient.infoManage.AlterEnterInfo;
import studentClient.infoManage.SearchEnterInfo;
import teacherClient.hander.AddComonents;
import teacherClient.ui.Ui;


/**
 * 学生客户系统功能响应类
 * @author 陌生人
 *
 */
public class Response {
	
	private JFrame scf = null;
	private JFrame lf;
	private JPanel jp0,jp1,jp2,jp2_1,jp2_2,jp2_3;
	private JButton jb1,jb1_1,jb1_2,jb2,jb2_1,jb3,jb3_1;
	private JLabel label2;
	private String username;
	private boolean jp1FoldFlag = false;
	private boolean jp2FoldFlag = false;
	private boolean jp3FoldFlag = false;
	private List<JPanel> list;
	
	private int y1 = 0;
	private int y2 = 50;
	private int y3 = 100;
	
	private int size1 = 120;
	private int size2 = 90;
	private int size3 = 90;
	
	public Response(JFrame scf, JFrame lf, String username,JLabel label2, JPanel jp0, JPanel jp1, JPanel jp2, JPanel jp2_1, JPanel jp2_2, JPanel jp2_3, JButton jb1,
			JButton jb1_1, JButton jb1_2, JButton jb2, JButton jb2_1, JButton jb3, JButton jb3_1,List<JPanel> list) {
		super();
		this.scf = scf;
		this.lf = lf;
		this.jp0 = jp0;
		this.jp1 = jp1;
		this.jp2 = jp2;
		this.jp2_1 = jp2_1;
		this.jp2_2 = jp2_2;
		this.jp2_3 = jp2_3;
		this.jb1 = jb1;
		this.jb1_1 = jb1_1;
		this.jb1_2 = jb1_2;
		this.jb2 = jb2;
		this.jb2_1 = jb2_1;
		this.jb3 = jb3;
		this.jb3_1 = jb3_1;
		this.list = list;
		this.label2 = label2;
		this.username = username;
	}
	
	public Response() {
		super();
	}

	public void jb1Fold() {					//1
		if(jp1FoldFlag) {
			jb1.setText("▶　信息管理");
			jp2_1.setSize(180,50);
			toUp(y2, y3, size1);
			jp1FoldFlag = false;
		} else {
			jb1.setText("▼　信息管理");
			jp2_1.setSize(180,size1);
			toDown(y2, y3, size1);
			jp1FoldFlag = true;
		}
	}

	public void searchEnterInfo() {			//1-1
		JPanel jp = AddComonents.addJPanel(scf, 200, 90, 773, 655);
		new SearchEnterInfo(scf,jp,list, username);
		jp.setVisible(true);
	}
	
	public void alterEnterInfo() {			//1-2
		JPanel jp = AddComonents.addJPanel(scf, 200, 90, 773, 655);
		new AlterEnterInfo(scf, jp, list, username,label2);
		jp.setVisible(true);
	}
	
	public void jb2Fold() {					//2
		if(jp2FoldFlag) {
			jb2.setText("▶　寝室更换");
			jp2_2.setSize(180,50);
			toUp(0, y3, size2);
			jp2FoldFlag = false;
		} else {
			jb2.setText("▼　寝室更换");
			jp2_2.setSize(180, size2);
			toDown(0, y3, size2);
			jp2FoldFlag = true;
		}
	}

	public void request() {					//2-1
		JPanel jp = AddComonents.addJPanel(scf, 200, 90, 773, 655);
		new LaunchRequest(scf, jp, list, username);
		jp.setVisible(true);
	}
	
	public void jb3Fold() {					//3
		if(jp3FoldFlag) {
			jb3.setText("▶　系统设置");
			jp2_3.setSize(180,50);
			jp3FoldFlag = false;
		} else {
			jb3.setText("▼　系统设置");
			jp2_3.setSize(180, size3);
			jp3FoldFlag = true;
		}
	}
	
	public void exitSystem() {				//3-1
		scf.setVisible(false);
		lf.setVisible(true);
		lf.setTitle("未登录");
		Ui.setFrameImage(lf, "NoLogin.jpg");
	}
	
	public void toUp(int a,int b, int temp) {
		if(a!=0) {
			a = a-temp+50; y2 = a;
		}
		if(b!=0) {
			b = b-temp+50; y3 = b;
		}
		jp2_2.setLocation(0, y2);
		jp2_3.setLocation(0, y3);
	}
	public void toDown(int a,int b, int temp) {
		if(a!=0) {
			a = a+temp-50; y2 = a;
		}
		b = b+temp-50; y3 = b;
		jp2_2.setLocation(0, y2);
		jp2_3.setLocation(0, y3);
	}
}
