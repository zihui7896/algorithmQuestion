<%@page import="javax.servlet.jsp.tagext.TryCatchFinally" %>
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
<title>批量删除学生信息</title>
</head>
<style>
    table{
        border:1px;
        border-color:#5FB878;
        align-content:center;
        align-items:center;
        cellpadding:"15";
        cellspacting:"0";
        width:900px;
    }
    tr{
        align-content:center;
        height:50px;
    }
</style>
<body>
<script src=layui/layui.js></script>
欢迎你，<%=session.getAttribute("username") %>
<input id='selectcheck' type='hidden' name="selectcheck">
<%
    String stu_id=request.getParameter("aaa");
%>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/stu";
    String username="root";
    String pwd="yezihui5201678";
    Connection conn=DriverManager.getConnection(url,username,pwd);
    Statement stmt=conn.createStatement();
    String sql="delete from stuinfo where stu_id in "+"("+stu_id+")"+"";
    stmt=conn.createStatement();
    int a=stmt.executeUpdate(sql);
    if(a>=1){
		out.print("删除成功！");
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}else{
		out.print("删除失败！");
        %>
        <a href="javascript:history.back()">返回</a>
        <%
	}
    %>
<a href="index.jsp" class="layui-btn">回到首页</a>
<a href="add_stu.jsp" class="layui-btn">添加学生</a>
<a href="multi_del_stu.jsp" class="layui-btn">批量删除</a>
<a href="register.jsp" class="layui-btn">注册新账号</a>
</body>
</html>