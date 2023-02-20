<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<title>success</title>
</head>
<body style="font-size:20px;color:red;text-align:center;padding-top:100px" background="image/background.jpg">
Welcome,<%=session.getAttribute("username") %>！
<br>
<br>
<a href="index.jsp" target="_self"class="layui-btn layui-btn-lg layui-btn-normal">查看学生信息</a>
</body>
</html>