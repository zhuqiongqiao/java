package 学生信息管理系统;

import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.awt.event.*;


public  class Addstu extends JPanel implements ActionListener{
	JTextField 学号,姓名,系别;
	JButton 录入;
	
public Addstu(){
	ImageIcon bg = new ImageIcon("C:\\Users\\朱雨晴\\Desktop\\hello.jpg");	//创建一个背景图片
    setBounds(0, 0, 1100,880);
    JLabel label=new JLabel(bg);//把背景图片添加到标签里
    label.setBounds(0, 100, 1000,980);	//把标签设置为和图片等高等宽
   bg.setImage(bg.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
    add(label);
    
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	学号=new JTextField(12);
	姓名=new JTextField(12);
	系别=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	box1.add(new JLabel("学号:"/*,JLabel.CENTER*/));
	box1.add(学号);
	box2.add(new JLabel("姓名:"/*,JLabel.CENTER*/));
	box2.add(姓名);
	box3.add(new JLabel("系别:"/*,JLabel.CENTER*/));
	box3.add(系别);
	box4.add(录入);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	validate();
	}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==录入){
		if(学号.getText().equals("")||姓名.getText().equals("")||系别.getText().equals("")){
			JOptionPane.showMessageDialog(this,"学生信息请填满再录入！" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="select * from S where Sno='"+学号.getText()+"'";
		    sql="insert into S values('"+学号.getText()+"','"+姓名.getText()+"','"+系别.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"该学号以存在，无法添加");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			}		
			rs1.close();
			stmt.close();
	   }
	   catch(SQLException e){
		   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
		   }
	}
}

//连接数据库方法
public static Connection CONN(){
	String driverName = "com.mysql.cj.jdbc.Driver";   //加载JDBC驱动
	 String dbURL = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8";  //连接服务器和数据库test
	   String userName = "root";   //默认用 户名
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

}

