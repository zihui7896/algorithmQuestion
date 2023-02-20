<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<title>login</title>
</head>
<body style="text-align:center;padding-top:200px;font-size:20px;color:red">
<%
      String user = new String(request.getParameter("user"));
      String password=new String(request.getParameter("password"));
      request.setCharacterEncoding("UTF-8");
%>
<%
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/stu";
	String username="root";
	String pwd="yezihui5201678";
	Connection conn=DriverManager.getConnection(url,username,pwd);
	Statement stmt=conn.createStatement();
	if(conn!=null){
		String sql="select * from user where username='"+user+"' and password='"+ password + "'";
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			session.setAttribute("username",user);
			response.sendRedirect("login_success.jsp");
		}else{
			out.println("用户名或密码错误，请重新输入!");
			%>
			<br>
			<a href="login.jsp" target="_self" class="layui-btn-lg layui-btn-normal">返回</a>
			<% 
		}
		conn.close();
	}else{
		System.out.println("数据库连接失败!");
	}
}catch(ClassNotFoundException cne){
	System.out.println("-------------");
	cne.printStackTrace();
}

%>
</body>
</html>
