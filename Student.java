package 学生信息管理系统;

import java.sql.*;

import javax.swing.*;

import java.util.*;

public class Student extends JFrame {
	Vector rowData, columnNames;
	Statement stmt=null;

    String sql=null;
	JTable jt = null;
	JScrollPane jsp = null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public Student(String sql1){
		columnNames = new Vector();
		// 设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("系别");

		rowData=new Vector();
		sql=sql1;
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				Vector hang = new Vector();
				hang.add(rs.getString("Sno"));System.out.print(rs.getString("Sno"));
				hang.add(rs.getString("Sname"));System.out.print(rs.getString("Sname"));
				hang.add(rs.getString("Sx"));System.out.println(rs.getString("Sx"));
						rowData.add(hang);}
				jt=new JTable(rowData,columnNames);
				jsp=new JScrollPane(jt);
	this.add(jsp);
	this.setSize(400,300);
	this.setVisible(true);
		}catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		}

//	连接数据库方法
	public static Connection CONN(){
		 String driverName = "com.mysql.cj.jdbc.Driver";   //加载JDBC驱动
		 String dbURL = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC&characterEncoding=utf8";  //连接服务器和数据库test //连接服务器和数据库test
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
}

