<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新用户注册</title>
<link rel="stylesheet" href="layui/css/layui.css">
<style type="text/css">
    .top{
        height:100px;
        text-align:center;
    }
    .middle{
        height:500px;
        background-color:#EEEEEE;
        align:center;
        padding-top:80px;
    }
    .bottom{
        
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
        font-family:'微软雅黑';
    }
    .username,.password1,.password2{
        margin-top:20px;
        margin-left:30px;
        float:left;
    }
    input{
        height:30px;
        margin-top:10px;
    }
</style>
</head>
<body style="text-align:center;" background="image/background3.jpg">
<div class="top">
  <font size="40px" color="black">学生信息管理系统</font>
</div>
<div class="middle">
  <div class="middle1"></div>
  <div class="middle2">
    <form id="form2" name="form2" method="post" action="checkregister.jsp">
      <div class="middle2top">新用户注册</div>
      <div class="username">
        <label class="layui-form-label" style="margin-top:10px;">用户名<i class="layui-icon layui-icon-username"></i></label>    
        <input style="width:50%;" type="text" name="newuser" id="newuser" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input"></div>
      <div class="password1">
        <label class="layui-form-label" style="margin-top:10px;">密码<i class="layui-icon layui-icon-password"></i></label>
        <input style="width:50%;" type="password" name="password1" id="password1" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></div>
      <div class="password2">
        <label class="layui-form-label" style="margin-top:10px;">确认密码<i class="layui-icon layui-icon-vercode"></i></label>
        <input style="width:50%;" type="password" name="password2" id="password2" required lay-verify="required" placeholder="请确认密码" autocomplete="off" class="layui-input"></div>
      <div style="text-align:center;">
        <input style="width:70%;margin-top:30px;" type="submit" value="立即注册" id="btonk" class="layui-btn layui-btn-normal"></div>
    </form>
  </div>
  <div class="middle3"></div>
</div>
<div class="bottom"></div>
</body>
</html>