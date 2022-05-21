package 学生信息管理系统;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import 学生信息管理系统.BackGroundTest;
public class BackGroundTest extends JFrame{
 
	public BackGroundTest(){
 
        setTitle("测试背景");	
        ImageIcon bg = new ImageIcon("C:\\Users\\朱雨晴\\Desktop\\朱朱.jpg");	//创建一个背景图片
        setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
        JLabel label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());	//把标签设置为和图片等高等宽
        JPanel jp = (JPanel)this.getContentPane();		//把我的面板设置为内容面板
        jp.setOpaque(false);//把我的面板设置为不可视
        jp.setLayout(null);//把我的面板设置为绝对布局
        
        
 
        /**
         * 功能设置位置，加入面板jp中，其中面板设置为绝对布局，组件需要设置大小和出现的位置
         * 例如：JLabel Name_inf=new JLabel("管理员："+this.user_);
         *       设置大小：Name_inf.setSize(110,50);
         *       设置位置：Name_inf.setLocation(0,-16);
         */
 
         //样例示范：
         JLabel Name_inf=new JLabel("管理员：");
         Name_inf.setSize(200,100);
         Name_inf.setLocation(0,-16);
         label.add(Name_inf);
         add(label);
         //样例示范   
 
 
        add(label);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//把标签添加到分层面板的最底层
        //设置界面属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
    public static void main(String[] args) {
		new BackGroundTest();
	}
 
}
