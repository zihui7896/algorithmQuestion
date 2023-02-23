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
<link rel="stylesheet" href="layui/css/layui.css" tppabs="http://res.layui.com/layui/dist/css/layui.css" media="all">
<title>处理学生修改信息</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String stu_id=request.getParameter("stu_id");
    System.out.println(stu_id);
    String name=request.getParameter("username");
    String sex=request.getParameter("sex");
    String major=request.getParameter("major");
    String phone=request.getParameter("phone");
    String address=request.getParameter("address");
    String age=request.getParameter("age");
    String classname=request.getParameter("classname");
%>
<%! ResultSet rs;%>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/stu";
    String user="root";
	String password="yezihui5201678";
	Connection conn=DriverManager.getConnection(url,user,password);
	Statement stmt=conn.createStatement();
	String sql="update stuinfo set name='"+name+"',sex='"+sex+"',major='"+major+"',phone='"+phone+"',address='"+address+"',age="+age+",classname='"+classname+"'where stu_id="+stu_id+" ";
	System.out.println(sql);
	int result=stmt.executeUpdate(sql);
	if(result>0){
		out.println("<script text='text/javascript'>alert('修改成功');</script>");
		response.sendRedirect("index.jsp");
	}else{
		out.println("<script text='text/javascript'>alert('修改失败');</script>");
	}
	//sql="select * from stuinfo";
	//rs=stmt.executeQuery(sql);
	//geimy.ini文件的[mysql]后添加：character-set-server=utf8

%>
    <%
        //rs.close();
        stmt.close();
        conn.close();
    %>
</table>
</body>
</html>