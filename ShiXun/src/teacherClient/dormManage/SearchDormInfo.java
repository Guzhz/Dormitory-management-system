package teacherClient.dormManage;

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

import teacherClient.hander.AddComonents;
import teacherClient.hander.GetDormInfo;
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

public class SearchDormInfo {
	
private JFrame tcf;
	
	
	private JPanel jp3_1,jp3_1_1,jp3_1_2,jp3_1_3;	//分别表示最下面的面板，标签面板，表格面板，按钮面板
	private JPanel jp3_1_4;			//新添用于查询寝室号的面板
	private List<JPanel> list;	//用于刷新的集合
	
	private JLabel jl1,jl2,jl3,jl4,jl5;
	private JButton jb0,jb1;
	private JTextField jtf;
	private JTable table;
	
	public SearchDormInfo(JFrame tcf, JPanel jp3_1, List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp3_1 = jp3_1;
		this.list = list;
		init();
	}
	
	public void init() {
		
		SortInfo.sortDormInfo();
		//面板1_1添加组件
		jp3_1_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 200);jp3_1_1.setVisible(true);
		jb0 = AddComonents.addJButton("查询所有寝室号", jp3_1_1, 310, 20, 130, 50, false, true, false);jb0.setVisible(true);
		jl1 = AddComonents.addJLabel("查询某个寝室的成员信息：", jp3_1_1, 130, 110, 200, 30);jl1.setVisible(true);
		jtf = AddComonents.addJTextField(jp3_1_1, 295, 110, 200, 30);jtf.setVisible(true);jtf.requestFocus();
		jb1 = AddComonents.addJButton("查询", jp3_1_1, 520, 110, 60, 30, true, true, false);jb1.setVisible(true);
		
		//面板1_2添加组件(存放表格的面板)
		jp3_1_2 = AddComonents.addJPanel(tcf, 0, 200, 773, 250);jp3_1_2.setVisible(true);jp3_1_2.setBackground(Color.black);
		search(GetStudentInfo.readInfo(""),false);
		
		//面板2_3添加组件
		jp3_1_3 = AddComonents.addJPanel(tcf, 0, 450, 773, 205);jp3_1_3.setVisible(true);
		jl2 = AddComonents.addJLabel("寝室人数：", jp3_1_3, 310, 50, 70, 30);jl2.setVisible(true);
		jl3 = AddComonents.addJLabel("0", 		jp3_1_3, 385, 50, 50, 30);jl3.setVisible(true);
		jl4 = AddComonents.addJLabel("是否满员：", jp3_1_3, 310, 100, 70, 30);jl4.setVisible(true);
		jl5 = AddComonents.addJLabel("否", 		jp3_1_3, 385, 100, 50, 30);jl5.setVisible(true);
		
		jp3_1_4 = AddComonents.addJPanel(tcf, 100, 200, 573, 350);
		
		jp3_1.add(jp3_1_1);
		jp3_1.add(jp3_1_2);
		jp3_1.add(jp3_1_3);
		jp3_1.add(jp3_1_4);
		list.add(jp3_1);
		UpPanel.upPanel(list, jp3_1, null);		//更新面板
		
		jb0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp3_1_2.setVisible(false);
				jp3_1_3.setVisible(false);
				jp3_1_4.setVisible(true);
				searchDormNumber(GetDormInfo.readInfo(null),true);
				JOptionPane.showMessageDialog(tcf, "已显示全部结果！", "查询成功", 1);
			}
		});
		
		jb1.addActionListener(new ActionListener() {		//查询按钮响应
			@Override
			public void actionPerformed(ActionEvent e) {
				jp3_1_2.setVisible(true);
				jp3_1_3.setVisible(true);
				jp3_1_4.setVisible(false);
				String text = jtf.getText().trim();
				String dnoRegex = "^[1-9]+-\\d{3}$";
				if(!text.matches(dnoRegex)) {
					JOptionPane.showMessageDialog(tcf, "请输入正确的寝室号！", "查询失败", 0);
				}else {
					search(GetStudentInfo.readInfo(text),true);
					JOptionPane.showMessageDialog(tcf, "已显示全部结果！", "查询成功", 1);
				}
			}
		});
		
		
	}
		
	protected void searchDormNumber(Object[][] arr, boolean newflag) {
		if(newflag) {		//判断是否第二次生成表格
			jp3_1_4.setVisible(false);
			list.remove(jp3_1_4);
			jp3_1_4 = AddComonents.addJPanel(tcf, 100, 200, 573, 350);	//新生成一个面板
		}
		
		Object[] title = {"序号","寝室号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp3_1_4.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp3_1_4.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(false);		//设置是否可编辑
		table.updateUI();
		jp3_1_4.setVisible(false);		//刷新
		jp3_1_4.setVisible(true);		//刷新
		
		jp3_1.add(jp3_1_4);
	}

	protected void search(Object[][] arr, boolean newflag) {
		
		if(newflag) {		//判断是否第二次生成表格
			jp3_1_2.setVisible(false);
			list.remove(jp3_1_2);
			jp3_1_2 = AddComonents.addJPanel(tcf, 0, 200, 773, 250);	//新生成一个面板
		}
		
		//对数组arr进行解析，获得人数，判断是否满员
		Object count = "";
		Object full = "否";
		for(int i = 0;i<arr.length; i++) {
			if(arr[i][0]!=null) {
				count = arr[i][0];
			}else {
				break;
			}
		}
		if(!count.equals("") && count.toString().equals("4"))
			full = "是";
		
		Object[] title = {"序号","姓名","学号","学院","班级","寝室号","床号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp3_1_2.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp3_1_2.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(false);		//设置是否可编辑
		table.updateUI();
		jp3_1_2.setVisible(false);		//刷新
		jp3_1_2.setVisible(true);		//刷新
		
		
		if(newflag) {		
			jl3.setText(count.toString());
			jl5.setText(full.toString());
		}
		jp3_1.add(jp3_1_2);
		
	}
	
	
}
