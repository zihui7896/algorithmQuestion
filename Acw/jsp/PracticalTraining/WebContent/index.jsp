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
<script src="layui/layui.js" charset="UTF-8"></script>
<title>学生信息管理系统</title>
<script type="text/javascript">
function f1(){
	//获取所有name为chk的多选框，返回一个多选框数组
	var chks=document.form.stu_id;
	//var str="";
	var str=new Array();
	var j=0;
	for (var i=0;i<chks.length;i++){
		if(chks[i].checked==true){
			//str += chks[i].value +",";
			str[j]=chks[i].value;
			j++;
		}
	}
	alert("您选择的是："+str);
	document.form.aaa.value=str;
}
function f2(){
	var chks=document.form.stu_id;
	for(var i=0;i<chks.length;i++){
		chks[i].checked=true;
	}
	
}
function f3(){
	var form2=document.getElementById("form2");
	ssname=form2.elements[0].value;
	ssnum=form2.elements[1].value;
	sssex=form2.elements[2].value;
	if(ssname.length!=0&&ssnum.length!=0&&sssex.length!=0){
		var sql="select * from stuinfo where name like"+" '%"+ssname+"%'"+" or "+"stu_id"+" like"+" '%"+ssnum+"%'"+" or "+"sex"+" like"+" '%"+sssex+"%'";
	}
	if(ssname.length!=0&&ssnum.length!=0&&sssex.length==0){
		var sql="select * from stuinfo where name like"+" '%"+ssname+"%'"+" or "+"stu_id"+" like"+" '%"+ssnum+"%'";
	}
	if(ssname.length!=0&&ssnum.length==0&&sssex.length!=0){
		var sql="select * from stuinfo where name like"+" '%"+ssname+"%'"+" or "+"sex"+" like"+" '%"+sssex+"%'";
	}
	if(ssname.length==0&&ssnum.length!=0&&sssex.length!=0){
		var sql="select * from stuinfo where stu_id like"+" '%"+ssnum+"%'"+" or "+"sex"+" like"+" '%"+sssex+"%'";
	}
	if(ssname.length!=0&&ssnum.length==0&&sssex.length==0){
		var sql="select * from stuinfo where name like"+" '%"+ssname+"%'";
	}
	if(ssname.length==0&&ssnum.length!=0&&sssex.length==0){
		var sql="select * from stuinfo where stu_id like"+" '%"+ssnum+"%'";
	}
	if(ssname.length==0&&ssnum.length==0&&sssex.length!=0){
		var sql="select * from stuinfo where sex like"+" '%"+sssex+"%'";
	}
	if(ssname==0&&ssnum==0&&sssex==0){
		var sql="select * from stuinfo";
	}
	alert("sql语句是"+sql);
	document.form2.str.value=sql;
}

</script>
</head>
<style>
    table{
        border:1px;
        border-color:#009688;
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
<%
    //request.setCharaterEncoding("UTF-8");
    String bbb = request.getParameter("aaa");
    String str=request.getParameter("str");
    if(str==null){
    	
    }else{
    	str=new String(request.getParameter("str").getBytes("ISO-8859-1"),"UTF-8");
    }
    System.out.println(str);
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
            <dd><a href="edit_pass.jsp">修改密码</a></dd>
            <dd><a href="loginout.jsp">退出登录</a></dd>
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
    String sql="select * from stuinfo";
    if(str!=null){
    	sql=str;
    	}
    stmt=conn.createStatement();
    ResultSet rs=stmt.executeQuery(sql);
    %>
    <div background-color="red" width="1000px" align="center"><font size="30" color="#F4A460">学生信息表</font></div>
    <div background-color="green">
      <form action="#" method="post" name="form2" id="form2">
      <table>
        <tr>
          <td width="100px"></td>
          <td height="50px">
            <input type="text" name="a" id="a" value="" placeholder="按姓名查询" width="200px" height="80px" style="border:solid #1E9FFF"></input>
          </td>
          <td width="25px"></td>
          <td>
            <input type="text" name="b" id="b" value="" placeholder="按学号查询" style="border:solid #1E9FFF"></input>
          </td>
          <td width="25px"></td>
          <td>
            <input type="text" name="c" id="c" value="" placeholder="按性别查询" style="border:solid #1E9FFF"></input>
          </td>
          <td width="25px"></td>
          <td>
            <input type="hidden" name="str" id="str"></input>
            <input type="submit" name="sel" value="查询" class="layui-btn layui-btn-normal" onclick="f3()"></input>
          </td>
        </tr>
      </table>
      </form>
      <br>
    </div>
  </div>    
<div background-color="blue">
</div>
<form name="form" action="multi_del_stu.jsp" method ="post" id="form">
  <table border="1px" align="center" >
    <caption></caption>
    <tr align="center" bgcolor="#F4A460">
      <td width="50px"></td>
      <td width="70px">
        <button type="button" id="btnSelectAll" value="全选" onclick="f2()" class="layui-btn layui-btn-xs layui-btn-normal">全选</button>
        <input type="hidden" name="aaa" id="aaa"/>
      </td>
      <td width="60px">学号</td>
        <td width="80px">姓名</td>
        <td width="60px">性别</td>
        <td width="100px">专业</td>
        <td width="90px">手机号</td>
        <td width="80px">地址</td>
        <td width="60px">年龄</td>
        <td width="110px">所在班级</td>
        <td width="80px">操作</td>
     </tr>
     <%
         int i =1;
         while(rs.next()){
     %>
     <tr align="center">
       <td><%=i++ %></td>
       <td><input type="checkbox" name="stu_id" id="stu_id" value="<%=rs.getInt(1)%>"/></td>
       <td><%=rs.getInt("stu_id") %></td>
       <td><%=rs.getString("name") %></td>
       <td><%=rs.getString("sex") %></td>
       <td><%=rs.getString("major") %></td>
       <td><%=rs.getString("phone") %></td>
       <td><%=rs.getString("address") %></td>
       <td><%=rs.getInt("age") %></td>
       <td><%=rs.getString("classname") %><div class="layui-anim lay-anim-up layui=anim-loop"></div></td>
       <td>
         <a href="edit_stu.jsp?stu_id=<%= rs.getInt(1) %>" span style=color:#1E9FFF>修改</a>
         <a href="del_stu.jsp?stu_id=<%= rs.getInt(1) %>" span style=color:#FF5722>删除</a>
       </td>     
     </tr>
 
    <%
    }
    %>
    <tr>
        <td colspan="11" align="center"><input type="submit" value="批量删除" onclick="f1()" class="layui-btn layui-btn-normal"></input></td>
    </tr>
  </table>
</form>
  </div>
</div>
 
</body>
</html>