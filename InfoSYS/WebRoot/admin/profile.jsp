<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="<%=basePath %>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="<%=basePath %>css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/morris/morris.css" rel="stylesheet" type="text/css" />
<link href="//cdn.bootcss.com/chosen/1.4.2/chosen.min.css" rel="stylesheet">
<link href="<%=basePath %>css/jquery-ui.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="<%=basePath %>css/AdminLTE.css" rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>
<body class="skin-blue">
<!-- header logo: style can be found in header.less -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="wrapper row-offcanvas row-offcanvas-left"> 
  <jsp:include page="../inc/sidebar.jsp"></jsp:include>
  
  <!-- Right side column. Contains the navbar and content of the page -->
  <aside class="right-side"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1> 个人信息管理 <small>个人信息</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">个人信息管理</a></li>
        <li class="active">个人信息</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
          	<div class="box-body">
          		<div class="row">
          			<div class="col-md-8">
          				<div class="box-header">
              <h3 class="box-title">个人信息</h3>
            </div>
            <!-- /.box-header -->
            <form class="form-horizontal" method="post" action="<%=basePath %>admin/profile?method=save">
                <div class="form-group">
                  <label class="col-sm-2 control-label">用户名</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="id"
									placeholder="用户名" value="${user.id }" readonly="readonly">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label"> 昵称 </label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="name"
									placeholder="昵称" value="${user.name }">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">生日</label>
                  <div class="col-sm-10">
                  	<span id="BirthYear" class="select-inline-block"></span>
                    <span id="BirthMonth" class="select-inline-block"></span>
                    <span id="BirthDay" class="select-inline-block"></span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label"> 原密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="pass"
									placeholder="请输入原密码">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label"> 新密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="pass"
									placeholder="请输入新密码">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label"></label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="pass"
									placeholder="请再次输入新密码">
                  </div>
                </div>
                  <div class="form-group">
                  <label class="col-sm-2 control-label"> 个人备注</label>
                  <div class="col-sm-10">
                    <textarea rows="4" cols="40" class="form-control" placeholder="个人备注" name="note">${user.note }</textarea>
                  </div>
                  <span class="warnmsg text-warning"></span> </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-1">
                    <button class="btn btn-default submit">提交</button>
                    <span class="tipmsg text-success"></span> </div>
                    <div class="col-sm-9"><a href="${backurl }" class="btn btn-default">返回</a></div>
                </div>
              </form>
          			</div>
          		</div>
          	</div>
            
          </div>
          <!-- /.box --> 
          
        </div>
      </div>
    </section>
    <!-- /.content --> 
  </aside>
  <!-- /.right-side --> 
</div>
<!-- ./wrapper --> 

<!-- jQuery 2.0.2 --> 
<script src="<%=basePath %>js/jquery.min.js"></script> 
<!-- Bootstrap --> 
<script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script> 
<!-- AdminLTE App --> 
<script src="<%=basePath %>js/app.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/plugins/morris/morris.min.js" type="text/javascript"></script> 
<script src="//cdn.bootcss.com/chosen/1.4.2/chosen.jquery.min.js"></script> 
<script src="//cdn.bootcss.com/jqueryui/1.11.4/jquery-ui.min.js"></script> 
<script src="<%=basePath %>js/custom.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/common.js" type="text/javascript"></script> 
<script type="text/javascript">
var month = [];
var year = [];
var day = [];
for(var i=1;i<=12;i++) {
	month.push({
		selectid : ":"+i,
		text : i+"月"
	});
}
for(var i=1965;i<=2016;i++) {
	year.push({
		selectid : ":"+i,
		text : i+"年"
	});
}
for(var i=1;i<=30;i++) {
	day.push({
		selectid : "::"+i,
		text : i+"日"
	});
}

$.MsgBox.select("#BirthMonth",{
	selectname : "月",
	items : month
});
$.MsgBox.select("#BirthYear",{
	selectname : "年",
	items : year
});
$.MsgBox.select("#BirthDay",{
	selectname : "日",
	items : day
});

</script>
</body>
</html>
