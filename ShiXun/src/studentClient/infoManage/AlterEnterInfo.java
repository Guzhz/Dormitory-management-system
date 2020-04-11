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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import studentClient.hander.BindingInfo;
import studentClient.hander.ChangeBindingInfo;
import teacherClient.hander.AddComonents;
import teacherClient.hander.ChangeStudentInfo;
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

public class AlterEnterInfo {

	private JFrame scf;
	private JPanel jp1_2,	jp1_2_1,jp1_2_2,jp1_2_3,jp1_2_4;
	private JLabel label2,jl1,jl2,jl3,jl4;
	private	JButton jb1,jb2,jb3,jb4;
	private JTextField jtf1,jtf2;
	private List<JPanel> list;
	private JTable table;
	private String username;
	private Object[][] oldArr = new Object[1][6];	//设定最多修改1行
	private Object[][] newArr = new Object[1][6];
	
	public AlterEnterInfo(JFrame scf, JPanel jp1_2, List<JPanel> list, String username, JLabel label2) {
		super();
		this.scf = scf;
		this.jp1_2 = jp1_2;		//1-2为父面板
		this.list = list;
		this.label2 = label2;
		this.username = username;
		init();
	}
	private void init() {
		jp1_2_1 = AddComonents.addJPanel(scf, 0, 0, 773, 200);jp1_2_1.setVisible(true);		//上面面板
		jl1 = AddComonents.addJLabel("请先绑定入住信息", jp1_2_1, 20, 0, 300, 50);jl1.setVisible(true);
		jb1 = AddComonents.addJButton("点我绑定入住信息", jp1_2_1, 200, 80, 150, 50, true, true, false);jb1.setVisible(true);
		jb2 = AddComonents.addJButton("点我修改入住信息", jp1_2_1, 400, 80, 150, 50, true, true, false);jb2.setVisible(true);
		jl2 = AddComonents.addJLabel("在表格中直接修改即可（学号、寝室号、床号不可更改，如需更改请发起更换请求）", jp1_2_1, 150, 150, 500, 50);jl2.setVisible(false);
		
		jp1_2_2 = AddComonents.addJPanel(scf, 0, 200, 773, 250);jp1_2_2.setVisible(true);
		search(GetStudentInfo.readInfo(""),false);
		
		//3为录入绑定信息面板
		jp1_2_3 = AddComonents.addJPanel(scf, 0, 200, 773, 350);jp1_2_3.setVisible(false);
		jl3 = AddComonents.addJLabel("请输入你的姓名：", jp1_2_3, 200, 50, 300, 50);jl3.setVisible(true);
		jtf1 = AddComonents.addJTextField(jp1_2_3, 			 320, 60, 200, 30);jtf1.setVisible(true);
		jl4 = AddComonents.addJLabel("请输入你的学号：", jp1_2_3, 200, 100, 300, 50);jl4.setVisible(true);
		jtf2 = AddComonents.addJTextField(jp1_2_3, 			 320, 110, 200, 30);jtf2.setVisible(true);
		jb3 = AddComonents.addJButton("确认绑定", jp1_2_3, 	 300, 200, 150, 70, false, true, false);jb3.setVisible(true);
		
		if(BindingInfo.isBinding(username)) {
			jtf1.setText("(不可输入)");
			jtf2.setText("(不可输入)");
			jb3.setText("已绑定");
			jtf1.setEditable(false);
			jtf2.setEditable(false);
		}
		
		jp1_2_4 = AddComonents.addJPanel(scf, 0, 450, 773, 205);jp1_2_4.setVisible(true);
		jb4 = AddComonents.addJButton("确认修改 ", jp1_2_4, 	 300, 50, 150, 70, false, true, false);jb4.setVisible(true);
		
		jp1_2.add(jp1_2_1);
		jp1_2.add(jp1_2_2);
		jp1_2.add(jp1_2_3);
		jp1_2.add(jp1_2_4);
		list.add(jp1_2);
		UpPanel.upPanel(list, jp1_2, null);		//更新面板
		
		jb1.addActionListener(new ActionListener() {		//点我绑定入住信息按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				jl2.setVisible(false);
				jp1_2_2.setVisible(false);
				jp1_2_3.setVisible(true);
				jp1_2_4.setVisible(false);
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//点我修改入住信息按钮响应
			@Override
			public void actionPerformed(ActionEvent e) {
				//判断是否已经绑定！
				if(!BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "请先绑定入住信息！", "操作失败", 0);
				}else {
					jl2.setVisible(true);
					jp1_2_2.setVisible(true);
					jp1_2_3.setVisible(false);
					jp1_2_4.setVisible(true);
					search(GetStudentInfo.readInfo(BindingInfo.getSno(username)),true);
				}
			}
		});
		
		jb3.addActionListener(new ActionListener() {		//确认绑定按钮-已绑定
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jb3.getText().equals("已绑定")) {
					JOptionPane.showMessageDialog(scf, "当前账号已绑定入住信息，请勿重复操作！", "绑定出错", 0);
				}else {
					if(BindingInfo.toBinding(scf,jtf1.getText().trim(), jtf2.getText().trim(),username)) {
						JOptionPane.showMessageDialog(scf, "当前账号绑定入住信息成功！", "绑定成功", 1);
						label2.setText("（"+jtf1.getText().trim()+"）");
						jtf1.setText("(不可输入)");
						jtf2.setText("(不可输入)");
						jb3.setText("已绑定");
						jtf1.setEditable(false);
						jtf2.setEditable(false);
					}
				}
			}
		});
		
		jb4.addActionListener(new ActionListener() {		//确认修改按钮响应
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!BindingInfo.isBinding(username)) {
					JOptionPane.showMessageDialog(scf, "请先绑定入住信息！", "操作失败", 0);
				}else {
					getTableInfo();
				}
			}
		});
	}
	
	protected void search(Object[][] arr, boolean newflag) {
		SortInfo.sortStudentIndo();
		if(newflag) {		//判断是否第二次生成表格
			jp1_2_2.setVisible(false);
			list.remove(jp1_2_2);
			jp1_2_2 = AddComonents.addJPanel(scf, 0, 200, 773, 250);	//新生成一个面板
		}
		
		Object[] title = {"序号","姓名","学号","学院","班级","寝室号","床号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp1_2_2.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp1_2_2.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(true);		//设置是否可编辑
		table.updateUI();
		jp1_2_2.setVisible(false);		//刷新
		jp1_2_2.setVisible(true);		//刷新
		
		jp1_2.add(jp1_2_2);
		
//		查询得到的结果存到旧数组中
		for(int i = 0; i<1; i++) {		//table为7列，数组为6列
			for(int j = 0; j<6; j++)
			oldArr[i][j] = table.getValueAt(i, j+1);	//存到第一行
		}
	}
	
	protected void getTableInfo() {
		if (table.isEditing()) 
		    table.getCellEditor().stopCellEditing();
		
		for(int i = 0; i<1; i++) {		//修改后数据存到新数组中
			for(int j = 0; j<6; j++)
			newArr[i][j] = table.getValueAt(i, j+1);	
		}
		ChangeStudentInfo.changeInfo(scf,oldArr,newArr,username);
		ChangeBindingInfo.changeBindingInfo(newArr, username);
		label2.setText("（"+BindingInfo.getName(username)+"）");
	}
}
