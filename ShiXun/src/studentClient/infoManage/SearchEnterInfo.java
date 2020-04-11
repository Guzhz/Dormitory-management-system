package studentClient.infoManage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import studentClient.hander.BindingInfo;
import teacherClient.hander.AddComonents;
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.UpPanel;

public class SearchEnterInfo {
	private JFrame scf;
	private JPanel jp1_1,jp1_1_1,jp1_1_2;
	private JButton jb1,jb2;
	private List<JPanel> list;
	private String username;
	
	public SearchEnterInfo(JFrame scf, JPanel jp1_1, List<JPanel> list, String username) {
		super();
		this.scf = scf;
		this.jp1_1 = jp1_1;
		this.list = list;
		this.username = username;
		init();
	}

	private void init() {
		jp1_1_1 = AddComonents.addJPanel(scf, 0, 100, 773, 300);jp1_1_1.setVisible(true);
		initTable(GetStudentInfo.readInfo(""),false);
		
		jp1_1_2 = AddComonents.addJPanel(scf, 0, 400, 773, 355);jp1_1_2.setVisible(true);
		jb1 = AddComonents.addJButton("查询我的入住信息", jp1_1_2, 170, 70, 180, 60, false, true, false);jb1.setVisible(true);
		jb2 = AddComonents.addJButton("查询寝室的入住信息", jp1_1_2, 430, 70, 180, 60, false, true, false);jb2.setVisible(true);
		
		jp1_1.add(jp1_1_1);
		jp1_1.add(jp1_1_2);
		list.add(jp1_1);
		UpPanel.upPanel(list, jp1_1, null);
		
		jb1.addActionListener(new ActionListener() {		//查询自己的
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "请先绑定入住信息！", "操作失败", 0);
				}else {
					initTable(GetStudentInfo.readInfo(BindingInfo.getSno(username)),true);
				}
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//查询寝室的
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "请先绑定入住信息！", "操作失败", 0);
				}else {
					initTable(GetStudentInfo.readInfo(BindingInfo.getDno(username)),true);
				}
			}
		});
	}
	
	public void initTable(Object[][] arr, boolean newflag) {
		
		if(newflag) {		//判断是否第二次生成表格
			jp1_1_1.setVisible(false);
			list.remove(jp1_1_1);
			jp1_1_1 = AddComonents.addJPanel(scf, 0, 100, 773, 300);	//新生成一个面板
		}
		
		Object[] title = {"序号","姓名","学号","学院","班级","寝室号","床号"};	//表头
		JTable table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		
		jp1_1_1.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp1_1_1.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(false);		//设置不可编辑
		table.updateUI();
		jp1_1_1.setVisible(false);		//刷新
		jp1_1_1.setVisible(true);		//刷新
		
		jp1_1.add(jp1_1_1);
		
	}
}
