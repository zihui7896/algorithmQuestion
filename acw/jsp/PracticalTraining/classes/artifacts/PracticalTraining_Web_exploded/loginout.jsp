<%@page import="javax.servlet.jsp.tagext.TryCatchFinally" %>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="layui/css/layui.css">
<title>退出登录</title>
<script type="text/javascript">
    function cancel(){
    	alert("确定要退出登录吗？");
    	sessionStorage.removeItem("username");
    	window.location.href="login.jsp";
    }
  
    
</script>
</head>
<body style="font-size:20px;color:red;text-align:center;padding-top:150px" background="image/background1.jpg">
    <button type="button" name="button1" value="退出登录"  onClick="cancel()" class="layui-btn layui-btn-lg layui-btn-normal">退出登录</button>

  
</body>
</html>