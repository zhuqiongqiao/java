package 学生信息管理系统;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.xml.crypto.Data;

import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;


public class User extends JFrame{
    private JLabel tle,use,password;
    private JTextField k1;//用户名输入框
    private JPasswordField k2;//密码输入框
    private JButton b1,b2;

    //登录窗口
    public User(JFrame f) throws IOException {
        super("学生信息管理系统");
   

        Container c=getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER,20,40));
        
        use=new JLabel("输入用户名:");
        use.setFont(new Font("方正舒体",Font.PLAIN,30));//25是字体大小
        use.setForeground(Color.white);//username颜色

        password=new JLabel("输入密码:");
        password.setFont(new Font("方正舒体",Font.PLAIN,30));
        password.setForeground(Color.white);//password字体颜色
        
       
        k1=new JTextField(15);
        k1.setFont(new Font("serif",Font.PLAIN,20));
        k2=new JPasswordField(15);
        k2.setFont(new Font("serif",Font.PLAIN,20));
        b1=new JButton("登录");
        b1.setFont(new Font("楷体",Font.PLAIN,20));//登录退出字体大小
        b2=new JButton("退出");
        b2.setFont(new Font("楷体",Font.PLAIN,20));
        
        //	设置登录方法
        BHandler b=new BHandler();
        EXIT d=new EXIT();
        b1.addActionListener(b);
        b2.addActionListener(d);

        //添加控件
        c.add(use);
        c.add(k1);
        c.add(password);
        c.add(k2);
        c.add(b1);
        c.add(b2);

        setBounds(350,300,250,160);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon bg = new ImageIcon("C:\\Users\\朱雨晴\\Desktop\\朱朱.jpg");	//创建一个背景图片
        setBounds(500, 350,690,500);
        JLabel label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, 700,500);	//把标签设置为和图片等高等宽
        JPanel jp = (JPanel)this.getContentPane();		//把我的面板设置为内容面板
        jp.setOpaque(false);//把我的面板设置为不可视
        jp.setLayout(null);//把我的面板设置为绝对布局
        
        add(label);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));	//把标签添加到分层面板的最底层
        //设置界面属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //主函数
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        User f1=new User(new JFrame());
        //背景音乐启动
        backMusic.playMusic();

    }
    //登录按钮方法
    private class BHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(k1.getText().equals("")||k2.getText().equals("")){
                JOptionPane.showMessageDialog(User.this,"用户名密码不能为空！" );
            }
            else{
                Statement stmt=null;
                ResultSet rs=null;
                String sql;
                sql="select * from student.admin where username='"+k1.getText()+"'";
                try{
                    Connection dbConn1=CONN();
                    stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    rs=stmt.executeQuery(sql);
                    if(rs.next()){
                        String xm=rs.getString("password");
                        if(k2.getText().equals(xm.trim())){JOptionPane.showMessageDialog(User.this,"登录成功");
                            dispose();
                            new Menu();//管理窗口
                        }
                        else{JOptionPane.showMessageDialog(User.this,"密码错误");}
                    }
                    else{JOptionPane.showMessageDialog(User.this,"用户名错误");}
                    rs.close();
                    stmt.close();
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(User.this,"SQL Exception occur.Message is:"+e.getMessage());
                }
            }
        }
    }
    //退出方法结束
    private class EXIT implements ActionListener{
        public void actionPerformed(ActionEvent even){
            System.exit(0);
        }
    }

    //连接数据库方法
    public static Connection CONN(){
        String driverName = "com.mysql.cj.jdbc.Driver";   //加载JDBC驱动
        String dbURL = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8";   //连接服务器和数据库test //连接服务器和数据库test
        String userName = "root";   //默认用户名
        String userPwd = "123456";   //密码
        Connection dbConn=null;
        try {
            Class.forName(driverName);
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}//父类结束

