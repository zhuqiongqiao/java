package 学生信息管理系统;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UpdateC extends JPanel implements ActionListener{
	String save=null;
	JTextField 课号1,课号,课名;
	JButton 修改,查找;
	
public UpdateC(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	课号1=new JTextField(12);
	课号=new JTextField(12);
	课名=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("课号:",JLabel.CENTER));
	box1.add(课号);
	box2.add(new JLabel("课名:",JLabel.CENTER));
	box2.add(课名);
	box3.add(修改);
	box5.add(new JLabel("课号:",JLabel.CENTER));
	box5.add(课号1);
	box5.add(查找);
	
	修改.addActionListener(this);
    查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	ImageIcon bg = new ImageIcon("C:\\Users\\朱雨晴\\Desktop\\hello.jpg");	//创建一个背景图片
    setBounds(0, 0, 1100,880);
    JLabel label=new JLabel(bg);//把背景图片添加到标签里
    label.setBounds(0, 100, 1000,980);	//把标签设置为和图片等高等宽
   bg.setImage(bg.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
    add(label);
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC=null;
	
	if(obj==查找){if(课号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的课号！" );
	else{
	    sql1="select * from C where Cno='"+课号1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){课号.setText(rs1.getString("Cno").trim());
	                   课名.setText(rs1.getString("Cname").trim());
	                   save=课号1.getText();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个课号的课程" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==修改){if(save==null){JOptionPane.showMessageDialog(this,"还没查找需要修改的课程" );}
	else{
		if(课号.getText().equals("")||课名.getText().equals("")){
			JOptionPane.showMessageDialog(this,"课程信息填满才能修改！" );
		}
		else{sql="update C set Cno='"+课号.getText()+"',Cname='"+课名.getText()+"' where Cno='"+save+"'";
		if(save.trim().equals(课号.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			课号.setText("");
            课名.setText("");
            stmt.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="select * from C where Cno='"+课号.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"已存在此课号课程" );
		    }
		    else{sqlSC="update SC set Sno='"+课号.getText()+"' where Cno='"+save+"'";
		    stmt.executeUpdate(sql);
		    stmt.executeUpdate(sqlSC);
		    	save=null;
			JOptionPane.showMessageDialog(null,"修改完成" );
			课号.setText("");
            课名.setText("");}
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		}
	}}}}
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



