package teacherClient.dormManage;

import java.awt.BorderLayout;
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
import teacherClient.hander.ChangeDormInfo;
import teacherClient.hander.GetDormInfo;
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.JudgeInfo;
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

public class AlterDorm {

private JFrame tcf;
	
	
	private JPanel jp3_3,jp3_3_1,jp3_3_2,jp3_3_3;	//分别表示最下面的面板，标签面板，表格面板，按钮面板
	private List<JPanel> list;	//用于刷新的集合
	
	private JLabel jl1,jl2;
	private JButton jb1,jb2,jb3;
	private JTextField jtf;
	private JTable table;
	private Object[][] alterArr = new Object[2][1];	//两行，第一行存旧的寝室号，第二行存新的寝室号
	
	public AlterDorm(JFrame tcf, JPanel jp3_3, List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp3_3 = jp3_3;
		this.list = list;
		init();
	}
	
	public void init() {
		SortInfo.sortDormInfo();
		//面板3_1添加组件
		jp3_3_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 170);jp3_3_1.setVisible(true);
		
		jl1 = AddComonents.addJLabel("按指定寝室号查询：", jp3_3_1, 150, 60, 200, 30);jl1.setVisible(true);
		jtf = AddComonents.addJTextField(jp3_3_1, 275, 60, 200, 30);jtf.setVisible(true);
		jb1 = AddComonents.addJButton("查询", jp3_3_1, 500, 60, 60, 30, true, true, false);jb1.setVisible(true);
		jl2 = AddComonents.addJLabel("查询后在表格中编辑修改即可", jp3_3_1, 290, 120, 300, 50);jl2.setVisible(true);
		jtf.requestFocus();
		
		
		//面板3_2添加组件(存放表格的面板)
		jp3_3_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);jp3_3_2.setVisible(true);
		search(GetDormInfo.readInfo(null),false,false);
		//面板2_3添加组件
		jp3_3_3 = AddComonents.addJPanel(tcf, 0, 520, 773, 145);jp3_3_3.setVisible(true);
		jb2 = AddComonents.addJButton("取消修改", jp3_3_3, 260, 50, 100, 50, false, true, false);jb2.setVisible(true);
		jb3 = AddComonents.addJButton("确定修改", jp3_3_3, 410, 50, 100, 50, false, true, false);jb3.setVisible(true);
		
		jp3_3.add(jp3_3_1);
		jp3_3.add(jp3_3_2);
		jp3_3.add(jp3_3_3);
		list.add(jp3_3);
		UpPanel.upPanel(list, jp3_3, null);		//更新面板
		JOptionPane.showMessageDialog(tcf, "寝室号已全部加载！", "加载成功", 1);
		
		jb1.addActionListener(new ActionListener() {		//查询按钮响应
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = jtf.getText().trim();
				String dnoRegex = "^[1-9]+-\\d{3}$";
				if(!text.matches(dnoRegex)) {
					JOptionPane.showMessageDialog(tcf, "请输入正确的寝室号！", "查询失败", 0);
				}else {
					search(GetDormInfo.readInfo(text),true,true);
					JOptionPane.showMessageDialog(tcf, "已显示全部结果！", "查询成功", 1);
				}
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
		alterArr[1][0] = table.getValueAt(0, 1);	//修改的存到第二行
		ChangeDormInfo.changeInfo(tcf,alterArr);
		
		
	}

	protected void search(Object[][] arr, boolean newflag, boolean enedit) {
		if(newflag) {		//判断是否第二次生成表格
			jp3_3_2.setVisible(false);
			list.remove(jp3_3_2);
			jp3_3_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);	//新生成一个面板
		}
		
		Object[] title = {"序号","寝室号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp3_3_2.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp3_3_2.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(enedit);		//设置是否可编辑
		table.updateUI();
		jp3_3_2.setVisible(false);		//刷新
		jp3_3_2.setVisible(true);		//刷新
		
		jp3_3.add(jp3_3_2);
		
		alterArr[0][0] = table.getValueAt(0, 1);	//原数据存到第一行
		
	}
	
}
