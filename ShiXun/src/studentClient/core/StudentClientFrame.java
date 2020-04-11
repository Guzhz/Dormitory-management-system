package studentClient.core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import studentClient.hander.BindingInfo;
import studentClient.response.Response;
import studentClient.ui.Global;
import studentClient.ui.Ui;
import teacherClient.hander.AddComonents;
import teacherClient.hander.UpPanel;

public class StudentClientFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jp0,jp1,jp2,jp2_1,jp2_2,jp2_3;
	private JButton jb1,jb1_1,jb1_2,jb2,jb2_1,jb3,jb3_1;
	private List<JPanel> list = new ArrayList<JPanel>();
	private Color c1 = new Color(187,206,219);
	private JLabel label1,label2;
	private JFrame lf;
	private String username;
	public StudentClientFrame(JFrame lf, String username) {
		super();
		this.lf = lf;
		this.username = username;
		initFrame();
		initComonents();
	}
	
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

	private void initComonents() {
		jp0 = Ui.setBackstageUI2(this); jp0.setVisible(true);
		jp1 = Ui.setBackstageUI(this);	jp1.setVisible(true);
		
		list.add(jp0);
		UpPanel.upPanel(list, jp0, null);
		
		jp2 = AddComonents.addJPanel(this, 0, 71, 180, 730); jp2.setBackground(c1); 		//左边工具栏,父面板
		jp2_1 = AddComonents.addJPanel_2(jp2, 0, 0, 180, 50);jp2_1.setBackground(c1);
		jp2_2 = AddComonents.addJPanel_2(jp2, 0, 50, 180, 50);jp2_2.setBackground(c1);
		jp2_3 = AddComonents.addJPanel_2(jp2, 0, 100, 180, 50);jp2_3.setBackground(c1);
		
		
		label1 = AddComonents.addJLabel("欢迎您："+username+"！", jp2, 45, 620, 180, 30); label1.setVisible(true);
		label2 = AddComonents.addJLabel("（未绑定）", jp2, 60, 650, 180, 30); label2.setVisible(true);
		if(BindingInfo.isBinding(username)) {
			label2.setText("（"+BindingInfo.getName(username)+"）");
		}
		//参数1是否透明，参数2是否要边框，参数3是否居左
		jb1 = AddComonents.addJButton("▶　信息管理",     jp2_1, 0, 0, 180, 50, false, true, true); jb1.setVisible(true);
		jb1_1 = AddComonents.addJButton("□　查询入住信息",   jp2_1, 0, 50, 180, 30, true, false, true);jb1_1.setVisible(true);
		jb1_2 = AddComonents.addJButton("□　修改入住信息", jp2_1, 0, 80, 180, 30, true, false, true);jb1_2.setVisible(true);
	
		jb2 = AddComonents.addJButton("▶　寝室更换",      jp2_2, 0, 0, 180, 50, false, true, true);jb2.setVisible(true);
		jb2_1 = AddComonents.addJButton("□　发起更换申请",    jp2_2, 0, 50, 180, 30, true, false, true);jb2_1.setVisible(true);
	
		jb3 = AddComonents.addJButton("▶　系统设置",      jp2_3, 0, 0, 180, 50, false, true, true);jb3.setVisible(true);
		jb3_1 = AddComonents.addJButton("□　退出系统",    jp2_3, 0, 50, 180, 30, true, false, true);jb3_1.setVisible(true);
	
		
		Response r = new Response(this, lf, username, label2, jp0, jp1, jp2, jp2_1, jp2_2, jp2_3, jb1,
				jb1_1, jb1_2, jb2, jb2_1, jb3, jb3_1, list);
		jb1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb1Fold();
			}
		});
		
		jb1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.searchEnterInfo();
			}
		});
		
		jb1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.alterEnterInfo();
			}
		});
		jb2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb2Fold();
			}
		});
		jb2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.request();
			}
		});
		jb3.addActionListener(new ActionListener() {		//系统设置
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.jb3Fold();
			}
		});
		jb3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				r.exitSystem();
			}
		});
	}
	
}
