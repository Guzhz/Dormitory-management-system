package teacherClient.hander;

import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;


/**
 * 
 * 这是一个更新功能区的面板类
 * 实现每次只显示指定功能的面板
 * 其余均不显示。
 * @author 陌生人
 */

public class UpPanel {

	public UpPanel() {
		super();
	}
	
	public static void upPanel(List<JPanel> list, JPanel jp1, JPanel jp2) {
		Iterator<JPanel> it = list.listIterator();
		while(it.hasNext()) {
			JPanel temp = it.next();
			if(temp == jp1) {
				jp1.setVisible(true);
			}else if(temp == jp2){
				jp2.setVisible(true);
			}else {
				temp.setVisible(false);
			}
		}
	}
}
