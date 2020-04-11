package teacherClient.addDorm;

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

/**
 * 添加寝室
 * @author 陌生人
 *
 */

public class AddDorm {
	
	private static File file = new File("DormNumber.txt");
	static {
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("存放寝室号的文件创建失败！");
		}
	}

	private BufferedWriter bw;
	private BufferedReader br;
	private JFrame tcf;
	private JPanel jp2_1,jp2_1_1,jp2_1_2;
	private List<JPanel> list;
	private JLabel jl1,jl2;
	private JTextField jtf;
	private JButton jb1;
	private JTable table;
	
	public AddDorm(JFrame tcf,JPanel jp2_1,List<JPanel> list) {
		super();
		this.tcf = tcf;
		this.jp2_1 = jp2_1;
		this.list = list;
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		SortInfo.sortDormInfo();
		jp2_1_1 = AddComonents.addJPanel(tcf, 0, 0, 773, 170);jp2_1_1.setVisible(true);
		jl1 = AddComonents.addJLabel("请输入需要添加的寝室号：", jp2_1_1, 110, 60, 200, 30);jl1.setVisible(true);
		jtf = AddComonents.addJTextField(jp2_1_1, 275, 60, 200, 30);jtf.setVisible(true);
		jb1 = AddComonents.addJButton("添加", jp2_1_1, 500, 60, 60, 30, true, true, false);jb1.setVisible(true);
		jl2 = AddComonents.addJLabel("目前已有的寝室号如下", jp2_1_1, 310, 120, 300, 50);jl2.setVisible(true);
		jtf.requestFocus();
		
		//面板1_2添加组件(存放表格的面板)
		jp2_1_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);jp2_1_2.setVisible(true);
		search(GetDormInfo.readInfo(null),false,false);
		
		jp2_1.add(jp2_1_1);
		jp2_1.add(jp2_1_2);
		list.add(jp2_1);
		UpPanel.upPanel(list, jp2_1, null);		//更新面板
		
		list.add(jp2_1);
		UpPanel.upPanel(list, jp2_1, null);
		JOptionPane.showMessageDialog(tcf, "寝室号已全部加载！", "加载成功", 1);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDorm();
			}
		});
	}

	protected void search(Object[][] arr, boolean newflag, boolean enedit) {
		if(newflag) {		//判断是否第二次生成表格
			jp2_1_2.setVisible(false);
			list.remove(jp2_1_2);
			jp2_1_2 = AddComonents.addJPanel(tcf, 100, 170, 573, 350);	//新生成一个面板
			
		}
		
		Object[] title = {"序号","寝室号"};	//表头
		table = new JTable(arr,title);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 50));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp2_1_2.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp2_1_2.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(enedit);		//设置是否可编辑
		table.updateUI();
		jp2_1_2.setVisible(false);		//刷新
		jp2_1_2.setVisible(true);		//刷新
		
		jp2_1.add(jp2_1_2);
	}
	
	//确定按钮响应
	protected void addDorm() {
		// TODO Auto-generated method stub
		String dnoRegex = "^[1-9]+-\\d{3}$";
		String text = jtf.getText();
		
		if(text.equals("")) {
			JOptionPane.showMessageDialog(tcf, "输入不可为空！", "添加失败", 0);
			jtf.setText("");
			
		} else if(!text.matches(dnoRegex)){
			JOptionPane.showMessageDialog(tcf, "寝室号格式不正确！", "添加失败", 0);
			jtf.setText("");
			
		}else {
			//先读取判断是否存在
			boolean isexist = false;
			try {
				br = new BufferedReader(new FileReader(file));
				String line = null;
				try {
					while((line = br.readLine()) != null) {
						if(line.equals(text)) {
							isexist = true;
							JOptionPane.showMessageDialog(tcf, "该寝室已经存在！", "添加失败！", 0);
							break;
						}
					}
				} catch (IOException e) {
					System.out.println("读取寝室号文件失败！");
				}
			} catch (FileNotFoundException e1) {
				System.out.println("添加寝室失败！");
			} finally {
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						System.out.println("添加寝室判断时关闭资源失败！");
					}
				}
			}
			
			//如果不存在就添加
			if(!isexist) {
				try {
					bw = new BufferedWriter(new FileWriter(file, true));
					bw.write(text);
					bw.newLine();
					bw.flush();
				} catch (IOException e) {
					System.out.println("寝室添加失败！");
				} finally {
					if(bw != null) {
						try {
							bw.close();
							JOptionPane.showMessageDialog(tcf, "新寝室已添加！", "添加成功", 1);
							jtf.setText("");
						} catch (IOException e) {
							System.out.println("添加寝室关闭资源失败！");
						}
					}
				}
			}
		}
	}
}
