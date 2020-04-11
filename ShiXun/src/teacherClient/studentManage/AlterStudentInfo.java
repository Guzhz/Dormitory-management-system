package teacherClient.studentManage;

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
import teacherClient.hander.ChangeStudentInfo;
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

/**
 * 修改学生信息类
 * @author 陌生人
 *
 */
public class AlterStudentInfo {

	private JFrame tcf;
	
	
	private JPanel jp1_2,jp1_2_1,jp1_2_2,jp1_2_3;	//分别表示最下面的面板，标签面板，表格面板，按钮面板
	private List<JPanel> list;	//用于刷新的集合
	
	private JLabel jl1,jl2,jl3;
	private JButton jb1,jb2,jb3;
	private JTextField jtf;
	private JTable table;
	private Object[][] oldArr = new Object[4][6];	//设定最多修改4行
	private Object[][] newArr = new Object[4][6];
	
	public AlterStudentInfo(JFrame tcf, JPanel jp1_2, List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp1_2 = jp1_2;
		this.list = list;
		init();
	}
	
	public void init() {
		
		//面板2_1添加组件
		jp1_2_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 200);jp1_2_1.setVisible(true);
		
		jl1 = AddComonents.addJLabel("请先查询需要修改的学生信息：", jp1_2_1, 20, 0, 300, 50);jl1.setVisible(true);
		jl2 = AddComonents.addJLabel("按指定条件查询：", jp1_2_1, 155, 80, 200, 30);jl2.setVisible(true);
		jtf = AddComonents.addJTextField(jp1_2_1, 275, 80, 200, 30);jtf.setVisible(true);
		jb1 = AddComonents.addJButton("查询", jp1_2_1, 500, 80, 60, 30, true, true, false);jb1.setVisible(true);
		jl3 = AddComonents.addJLabel("直接在表格中编辑修改即可", jp1_2_1, 300, 140, 300, 50);jl3.setVisible(true);
		
		jtf.requestFocus();
		
		
		//面板2_2添加组件(存放表格的面板)
		jp1_2_2 = AddComonents.addJPanel(tcf, 0, 200, 773, 250);jp1_2_2.setVisible(true);
		search(GetStudentInfo.readInfo(""),false);
		//面板2_3添加组件
		jp1_2_3 = AddComonents.addJPanel(tcf, 0, 450, 773, 205);jp1_2_3.setVisible(true);
		jb2 = AddComonents.addJButton("取消修改", jp1_2_3, 260, 50, 100, 50, false, true, false);jb2.setVisible(true);
		jb3 = AddComonents.addJButton("确定修改", jp1_2_3, 410, 50, 100, 50, false, true, false);jb3.setVisible(true);
		
		jp1_2.add(jp1_2_1);
		jp1_2.add(jp1_2_2);
		jp1_2.add(jp1_2_3);
		list.add(jp1_2);
		UpPanel.upPanel(list, jp1_2, null);		//更新面板
		
		jb1.addActionListener(new ActionListener() {		//查询按钮响应
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = jtf.getText().trim();
				search(GetStudentInfo.readInfo(text),true);
				JOptionPane.showMessageDialog(tcf, "已显示全部结果！", "查询成功", 1);
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//取消更改
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing()) 
				    table.getCellEditor().stopCellEditing();
				JOptionPane.showMessageDialog(tcf, "已取消修改！", "修改失败", 0);
			}
		});
		jb3.addActionListener(new ActionListener() {		//确定更改
			public void actionPerformed(ActionEvent e) {
				getTableInfo();
			}
		});
	}

	protected void getTableInfo() {
		if (table.isEditing()) 
		    table.getCellEditor().stopCellEditing();
		
		for(int i = 0; i<4; i++) {		//修改后数据存到新数组中
			for(int j = 0; j<6; j++)
			newArr[i][j] = table.getValueAt(i, j+1);	

		}
		ChangeStudentInfo.changeInfo(tcf,oldArr,newArr);
	}

	protected void search(Object[][] arr, boolean newflag) {
		SortInfo.sortStudentIndo();
		if(newflag) {		//判断是否第二次生成表格
			jp1_2_2.setVisible(false);
			list.remove(jp1_2_2);
			jp1_2_2 = AddComonents.addJPanel(tcf, 0, 200, 773, 250);	//新生成一个面板
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
		
		//查询得到的结果存到旧数组中
		for(int i = 0; i<4; i++) {		//table为7列，数组为6列
			for(int j = 0; j<6; j++)
			oldArr[i][j] = table.getValueAt(i, j+1);	//存到第一行
		}
	}
	
	
	
}
