<%@page import="java.sql.Statement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<title>新用户注册</title>
</head>
<body style="font-size:20px;color:red;text-align:center;padding-top:200px" background="image/background.jpg">
<%
    String newuser=new String(request.getParameter("newuser"));
    String password1=new String(request.getParameter("password1"));
    String password2=new String(request.getParameter("password2"));

%>
<%
try{
	//加载数据库驱动，注册到驱动管理器
	Class.forName("com.mysql.cj.jdbc.Driver");
	//数据库连接字符串
	String url="jdbc:mysql://localhost:3306/stu";
	//数据库用户名
	String username="root";
	//数据库密码
	String pwd="yezihui5201678";
	//创建Connection连接
	Connection conn=DriverManager.getConnection(url,username,pwd);
	//String url="jdbc:mysql://localhost:3306/stu";
	Statement stmt=conn.createStatement();
	//判断数据库连接是否为空
	if(conn!=null){
		String sql1="select*from user where username='"+newuser+"'";
		ResultSet rs = stmt.executeQuery(sql1);
		if(!rs.next()){
		if(password1.equals(password2)){
			String sql2="insert into user(username,password) values('"+newuser+"','"+password2+"')";
			stmt=conn.createStatement();
			int a=stmt.executeUpdate(sql2);
		    if(a==1){
			    request.getRequestDispatcher("register_success.jsp").forward(request,response);
		    }else{
		    	out.print("用户注册失败！");
		    	%>
		    	<a href="javascript:history.back()" target="_self" class="layui-btn layui-btn-normal">返回</a>
			    <%
		        }	
	    }else{
	    	out.print("两次密码不一致！");
		    %>
		    <a href="javascript:history.back()" target="_self" class="layui-btn layui-btn-normal">返回</a>
		    <%
	      }
		}else{
    	out.print("用户名相同！");
	    %>
	    <br>
	    <a href="javascript:history.back()" target="_self" class="layui-btn layui-btn-normal">返回</a>
	    <%
      }
	}else{
		//输出连接信息
		System.out.println("数据库连接失败！");
	  }
    
}catch(ClassNotFoundException cne){
    	System.out.println("----------");
    	cne.printStackTrace();
    }
%>
</body>
</html>