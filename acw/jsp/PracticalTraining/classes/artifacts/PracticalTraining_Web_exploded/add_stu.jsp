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
<title>添加学生信息</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
</head>
<body style="text-align:center;" background="image/background2.jpg">
<%
    String stuclass=request.getParameter("phone");
%>
<form class="layui-form" action="add_stu_chuli.jsp" method="post">
    <table align="center" cellpadding="15" cellspacting="0">
        <caption style="margin-top:50px;margin-left:30px"><h1>添加学生</h1></caption>
        <tr>
            <td><label class="layui-form-label" style="margin-top:30px;margin-left:40px">姓名</label></td>
            <td><input style="margin-top:30px;" type="text" name="username" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input"></td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">性别</label></td>
            <td>
                <input style="margin-top:10px;" type="radio" name="sex" value="保密" title="保密" checked>
                <input style="margin-top:10px;" type="radio" name="sex" value="男" title="男">
                <input style="margin-top:10px;" type="radio" name="sex" value="女" title="女">
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">专业</label></td>
            <td>
                <select name="major" lay-verify="required">
                    <option value="大数据">大数据</option>
                    <option value="软件应用技术">软件应用技术</option>
                    <option value="网络技术">网络技术</option>
                    <option value="数字媒体">数字媒体</option>
                    <option value="物联网工程">物联网工程</option>
                    <option value="软件工程">软件工程</option>
                    <option value="人工智能">人工智能</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">电话</label></td>
            <td> 
                <input style="margin-top:10px;" type="text" name="phone" required lay-verify="required" placeholder="请输入电话" autocomplete="off" class="layui-input">
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">家庭住址</label></td>
            <td> 
                <input style="margin-top:10px;" type="text" name="address" required lay-verify="required" placeholder="请输入家庭住址" autocomplete="off" class="layui-input">
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">年龄</label></td>
            <td> 
                <input style="margin-top:10px;" type="text" name="age" required lay-verify="required" placeholder="请输入年龄" autocomplete="off" class="layui-input">
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-top:10px;margin-left:40px">班级</label></td>
            <td> 
                <input style="margin-top:10px;" type="text" name="classname" required lay-verify="required" placeholder="请输入所在班级" autocomplete="off" class="layui-input">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input style="margin-top:20px;margin-left:40px" type="submit" class="layui-btn layui-btn-normal" value="添加"></td>
        </tr>
        </table>
        </form>
       
</body>
</html>