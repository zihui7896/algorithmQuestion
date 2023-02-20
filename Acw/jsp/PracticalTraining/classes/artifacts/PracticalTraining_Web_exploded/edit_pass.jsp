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
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="layui/css/layui.css">
<title>修改密码</title>
</head>
<style>
    table{
        border:1px;
        border-color:#009688;
        align-content:center;
        align-items:center;
        cellpadding:"15";
        cellspacting:"0";
      }
      tr{
          align-content:center;
          height:50px;
      }
      .middle2{
          background-color:#EEEEEE;
          width:100%;
          height:600px;
      }
      .middle21{
          width:35%;
          height:500px;
          float:left;
      }
      .middle23{
          width:35%;
          height:500px;
          float:right;
      }
      .middle22{
          background-color:white;
          width:30%;
          float:left;
          height:500px;
      }
      .reset{
          font-family:'微软雅黑';
          font-size:20px;
          text-align:center;
          background-color:#F4A460;
          width:100%;
          height:50px;
          color:white;
          padding-top:20px;
      }
      .id1{
          width:100%;
          height:50px;
    
      }
      .username{
          width:100%;
          height:50px;
      }
      .newpassword1{
          width:100%;
          height:50px;
      }
</style>
<body>

<%
    String bbb=request.getParameter("aaa");
%>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black">学生信息管理系统</div>
    <!--头部区域（可配合layui已有的水平导航）-->
    <ul class="layui-nav layui-layout-left">
      <!--移动端显示-->
      <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
        <i class="layui-icon layui-icon-spread-left"></i>
      </li>
        
      <li class="layui-nav-item layui-hide-xs"><a href="index.jsp">回到首页</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="add_stu.jsp">添加学生</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="register.jsp">新用户注册</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">帮助</a>
        <dl class="layui-nav-child">
          <dd><a href="">menu 11</a></dd>
          <dd><a href="">menu 22</a></dd>
          <dd><a href="">menu 33</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">
        <a href="javascript:;">
          <img src="image/sculpture.jpg" class="layui-nav-img">
                             您好,<%=session.getAttribute("username")%>
        </a>
        <dl class="layui-nav-child">
            <dd><a href="">个人信息</a></dd>
            <dd><a href="">修改密码</a></dd>
            <dd><a href="">退出登录</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
        <a href="javascript:;">
          <i class="layui-icon layui-icon-more-vertical"></i>
        </a>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!--左侧导航区域（可配合layui已有的垂直导航）-->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
          <li class="layui-nav-item layui-nav-itemed">
              <a href="javascript:;">基本操作</a>
              <dl class="layui-nav-child">
                  <dd><a href="javascript:;">添加学生</a></dd>
                  <dd><a href="javascript:;">删除学生</a></dd>
                  <dd><a href="javascript:;">更新学生</a></dd>
                  <dd><a href="javascript:">查询学生</a></dd>
              </dl>
          </li>
      </ul>
    </div>
  </div>
        
  <div class="layui-body">
    <!--内容主体区域-->
    <div style="padding:15px;">
    <%  
    //加载数据库驱动，注册到驱动管理器
    Class.forName("com.mysql.cj.jdbc.Driver");
    //数据库连接字符串
    String url="jdbc:mysql://localhost:3306/stu";
    String username="root";
    String pwd="yezihui5201678";
    //创建Connection连接
    Connection conn=DriverManager.getConnection(url,username,pwd);
    Statement stmt=conn.createStatement();
    String uname=(String)session.getAttribute("username");
    String sql="select * from user where username='"+uname+"'";
    stmt=conn.createStatement();
    ResultSet rs=stmt.executeQuery(sql);
    %>
<div class="middle2">
    <div class="middle21"></div>
    <div class="middle22">
        <%=rs.next() %>
        <div class="reset"><font>重置密码</font></div>
        <form name="form2" action="edit_pass_chuli.jsp" method="post" id="form2">
            <div class="id1">
                <label class="layui-form-label">ID</label>
                <input style="width:50%;margin-top:30px;" readonly="readonly" name="uid" id="uid" value="<%=rs.getInt("id")%>" class="layui-input"></input></div>
            <div class="username">
                <label class="layui-form-label">用户名</label>
                <input style="width:50%;" readonly="readonly" name="uname" id="uname" value="<%=rs.getString("username")%>" class="layui-input"></input></div>
            <div class="newpassword1">
                <label class="layui-form-label">新密码</label>
                <input style="width:50%;height:30px;" type="password" name="newpassword" id="newpassword" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></div>
            <div style="text-align:center">
                <input style="width:70%;margin-top:30px;" type="submit" value="立即修改" id="btonk" class="layui-btn layui-btn-normal"></div>
        </form>
    </div>
    <div class="middle23"></div>
</div>
</div>
</div>
</div>
<script src="layui/layui.js"></script>
<script>
//JS
layui.use(['element','layer','util'],function(){
  var element=layui.element
  ,layer=layui.layer
  ,util=layui.util
  ,$=layui.$;
  
  //头部事件
  util.event('lay-header-event',{
	//左侧菜单事件
	menuLeft:function(othis){
		layer.msg('展开左侧菜单的操作',{icon:0});
	}
  ,menuRight:function(){
	  layer.open({
		  type:1
		  ,content:'<div style="padding:15px;">处理右侧面板的操作</div>'
		  ,area:['260px','100%']
		  ,offset:'rt'//右上角
		  ,anim:5
		  ,shadeClose:true
	  });
    }
  });
});
</script>
</body>
</html>