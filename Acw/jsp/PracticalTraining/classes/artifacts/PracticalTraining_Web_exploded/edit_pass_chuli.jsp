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
<title>修改用户密码</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String uid=request.getParameter("uid");
    System.out.println(uid);
    String upassword=request.getParameter("newpassword");
%>
<%! ResultSet rs;%>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/stu";
    String user="root";
	String password="yezihui5201678";
	Connection conn=DriverManager.getConnection(url,user,password);
	Statement stmt=conn.createStatement();
	String sql="update user set password='"+upassword+"'where id="+uid+"";
	System.out.println(sql);
	int result=stmt.executeUpdate(sql);
	if(result==1){
		out.println("<script text='text/javascript'>alert('修改密码成功');</script>");
		response.sendRedirect("index.jsp");
	}else{
		out.println("<script text='text/javascript'>alert('修改密码失败');</script>");
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
</body>
</html>