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
<title>用户登录</title>
<link rel="stylesheet" href="layui/css/layui.css">
<style type="text/css">
    .top{
        height:100px;
        border:1px;
    }
    .middle{
        height:500px;
        background-color:#EEEEEE;
        align:center;
        padding-top:80px;
    }
    .bottom{
        background-color:gray;
    }
    .middle1{
        width:38%;
        float:left;
        height:100%;
    }
    .middle2{
        background-color:white;
        width:24%;
        float:left;
        height:80%;
    }
    .middle3{
        width:38%;
        float:right;
        height:100%;
    }
    .middle2top{
        padding-top:15px;
        background-color:#F4A460;
        width:100%;
        height:50px;
        font-size:28px;
        color:white;
        text-align:center;
    }
    .submit{
        text-align:center;
    }
    .username{
        margin-top:30px;
        float:left;
    }
    .password{
        margin-top:10px;
        float:left;
    }
    .submit,.regist{
        margin:10px;
    }
    .regist{
        width:100%;
        text-align:center;
        margin-left:80px;
        margin-top:60px;
    }
</style>
</head>
<body background="image/background3.jpg">
<script src="layui/layui.js"></script>
<div class="top" align="center">
<font size="40px" color="black">学生信息管理系统</font>
</div>
<div class="middle">
  <div class="middle1"></div>
  <div class="middle2" style="border-color:red;border:1px;">
    <div class="middle2top">用户登录</div>
    <div class="form1">
      <form action="regist.jsp" method="post" id="loginForm" class="layui-form">
        <div class="username">
          <label class="layui-form-label">用户名<i class="layui-icon layui-icon-username"></i></label>
          <input style="width:50%;" type="text" name="user" id="user" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input"></input>
        </div>
        <div class="password">
          <label class="layui-form-label">密码<i class="layui-icon layui-icon-password"></i></label>
          <input style="width:50%;" type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></input>
        </div>
        <div align="center"><input style="margin-top:20px;" type="submit" value="提交" id="btnok" class="layui-btn layui-btn-normal"></div>
        <div style="padding-top:25px;text-align:right">还没有注册？ <a href="register.jsp" target="_self" class="layui-btn layui-btn-sm layui-btn-normal">立即注册</a></div>
      </form>
    </div>
  </div>
</div>

</body>
<html>