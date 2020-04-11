package teacherClient.dormManage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import teacherClient.hander.SortInfo;
import teacherClient.hander.UpPanel;

public class DeleteDorm {
	
	private static File file = new File("DormNumber.txt");
	private static File file1 = new File("DormNumber1.txt");
	static {
		try {
			file1.createNewFile();
		} catch (IOException e) {
			System.out.println("存放寝室号的文件创建失败！");
		}
	}

	private BufferedWriter bw;
	private BufferedReader br;
	private JFrame tcf;
	private JPanel jp3_2,jp3_2_1,jp3_2_2;
	private List<JPanel> list;
	private JLabel jl1,jl2;
	private JButton jb1;
	private JTextField jtf;
	private JTable table;
	
	public DeleteDorm(JFrame tcf,JPanel jp3_2,List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp3_2 = jp3_2;
		this.list = list;
		init();
	}

	public void init() {
		SortInfo.sortDormInfo();
		// TODO Auto-generated method stub
		jp3_2_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 170);jp3_2_1.setVisible(true);
		jl1 = AddComonents.addJLabel("请输入需要删除的寝室号：", jp3_2_1, 110, 60, 200, 30);jl1.setVisible(true);
		jtf = AddComonents.addJTextField(jp3_2_1, 275, 60, 200, 30);jtf.setVisible(true);
		jb1 = AddComonents.addJButton("删除", jp3_2_1, 500, 60, 60, 30, true, true, false);jb1.setVisible(true);
		jl2 = AddComonents.addJLabel("目前已有的寝室号如下", jp3_2_1, 310, 120, 300, 50);jl2.setVisible(true);
		jtf.requestFocus();
		
		//面板3_2添加组件(存放表格的面板)
		jp3_2_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);jp3_2_2.setVisible(true);
		search(GetDormInfo.readInfo(null),false,false);
		
		jp3_2.add(jp3_2_1);
		jp3_2.add(jp3_2_2);
		list.add(jp3_2);
		UpPanel.upPanel(list, jp3_2, null);		//更新面板
		JOptionPane.showMessageDialog(tcf, "寝室号已全部加载！", "加载成功", 1);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDorm();
				search(GetDormInfo.readInfo(null),false,false);
			}
		});
		
		list.add(jp3_2);
		UpPanel.upPanel(list, jp3_2, null);
	}

	protected void search(Object[][] arr, boolean newflag, boolean enedit) {
		if(newflag) {		//判断是否第二次生成表格
			jp3_2_2.setVisible(false);
			list.remove(jp3_2_2);
			jp3_2_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);	//新生成一个面板
			
		}
		
		Object[] title = {"序号","寝室号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp3_2_2.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp3_2_2.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(enedit);		//设置是否可编辑
		table.updateUI();
		jp3_2_2.setVisible(false);		//刷新
		jp3_2_2.setVisible(true);		//刷新
		
		jp3_2.add(jp3_2_2);
	}

	protected void deleteDorm() {
		String dnoRegex = "^[1-9]+-\\d{3}$";
		String text = jtf.getText();
		
		if(text.equals("")) {
			JOptionPane.showMessageDialog(tcf, "输入不可为空！", "删除失败", 0);
			jtf.setText("");
		} else if(!text.matches(dnoRegex)){
			JOptionPane.showMessageDialog(tcf, "寝室号格式不正确！", "删除失败", 0);
			jtf.setText("");
		}else {
			//先读取判断是否存在
			boolean exist = false;
			try {
				br = new BufferedReader(new FileReader(file));
				try {
					bw = new BufferedWriter(new FileWriter(file1));
				} catch (IOException e1) {
					System.out.println("寝室删除失败！");
				}
				String line = null;
				try {
					while((line = br.readLine()) != null) {
						if(!line.equals(text)) {	//与需要删除的寝室号比较，如果不能就写入新的文件中
							bw.write(line);
							bw.newLine();
						} else {					//如果不进来这里说明，则该寝室号不存在，就删除失败！
							exist = true;  		
						}
					}
				} catch (IOException e) {
					System.out.println("读取寝室号文件失败！");
				}
			} catch (FileNotFoundException e1) {
				System.out.println("删除寝室失败！");
			} finally {
				if(br!=null && bw!=null) {
					try {
						br.close();
						bw.close();
					} catch (IOException e) {
						System.out.println("删除寝室判断时关闭资源失败！");
					}
				}
			}
			
			//对标志进行判断处理
			if(exist) {
				file.delete();
				file1.renameTo(file);
				JOptionPane.showMessageDialog(tcf, "该寝室已删除！", "删除成功", 1);
			}else {
				file1.delete();
				JOptionPane.showMessageDialog(tcf, "该寝室不存在！", "删除失败", 0);
			}
		}
	}
	
}
