package teacherClient.studentManage;

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
import teacherClient.hander.GetStudentInfo;
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

/**
 * 查询学生信息类
 * 可以指定条件查询
 * @author 陌生人
 *
 */

public class SearchStudentInfo {

	private JFrame tcf;
	private JPanel jp1_3,jp1_3_1;
	private List<JPanel> list;
	
	public SearchStudentInfo(JFrame tcf, JPanel jp1_3,List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp1_3 = jp1_3;
		this.list = list;
		initTable(GetStudentInfo.readInfo(""),false);
		initSearch();
	}
	

	//初始化表格,参数1为需要生成的数组，参数2为是否二次生成（因为GUI二次生成无法覆盖，所以需要重新new一个面板）
	public void initTable(Object[][] arr, boolean newflag) {
		
		if(newflag) {		//判断是否第二次生成表格
			jp1_3.setVisible(false);
			list.remove(jp1_3);
			jp1_3 = AddComonents.addJPanel(tcf, 200, 90, 773, 500);	//新生成一个面板
		}
		
		Object[] title = {"序号","姓名","学号","学院","班级","寝室号","床号"};	//表头
		JTable table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		
		jp1_3.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp1_3.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(false);		//设置不可编辑
		table.updateUI();
		jp1_3.setVisible(false);		//刷新
		jp1_3.setVisible(true);			//刷新
		
		list.add(jp1_3);
		
	}
		
	public void initSearch() {
		jp1_3_1 = AddComonents.addJPanel(tcf, 200, 600, 773, 150);
		
		//添加组件
		JButton jb1 = AddComonents.addJButton("查询全部学生信息", jp1_3_1, 300, 15, 150, 50, false, true, false);
		JButton jb2 = AddComonents.addJButton("搜索", jp1_3_1, 500, 80, 60, 30, true, true, false);
		JTextField jtf = AddComonents.addJTextField(jp1_3_1, 275, 80, 200, 30);
		JLabel jl = AddComonents.addJLabel("按指定条件搜索：", jp1_3_1, 155, 80, 200, 30);jl.setVisible(true);
		jtf.requestFocus();
		jb1.setVisible(true);
		jb2.setVisible(true);
		jtf.setVisible(true);
		
		list.add(jp1_3_1);							//添加到list集合中
		UpPanel.upPanel(list, jp1_3, jp1_3_1);		//更新面板
		
		jb1.addActionListener(new ActionListener() {		//查询按钮监听
			public void actionPerformed(ActionEvent e) {
				SortInfo.sortStudentIndo();  //排序一下
				initTable(GetStudentInfo.readInfo(null),true);
				JOptionPane.showMessageDialog(tcf, "已显示全部结果！", "查询成功", 1);
			}
		});
		
		jb2.addActionListener(new ActionListener() {		//条件查询按钮监听
			public void actionPerformed(ActionEvent e) {
				SortInfo.sortStudentIndo();
				String text = jtf.getText().trim();
				initTable(GetStudentInfo.readInfo(text),true);
				UpPanel.upPanel(list, jp1_3, jp1_3_1);		//更新面板
				jp1_3.setVisible(true);
				JOptionPane.showMessageDialog(tcf, "已显示条件查询的全部结果！", "查询成功", 1);
			}
		});
	}
	
}
