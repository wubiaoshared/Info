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
<title>域名添加</title>
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
      <h1> 域名信息管理 <small>域名列表</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">域名管理</a></li>
        <li class="active">域名信息列表</li>
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
              <h3 class="box-title">未上线域名添加</h3>
            </div>
            <!-- /.box-header -->
            <form class="form-horizontal" method="post" action="<%=basePath %>admin/domain_add?refurl=${refurl}">
                <div class="form-group">
                  <label class="col-sm-2 control-label"> 域名 </label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="domain"
									placeholder="域名">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label"> DNS</label>
                  <div class="col-sm-10">
                    <select class="sdns form-control" name="dns">
                      <option>-- 请选择一个DNS --</option>
                      <option value="servlet/DNSMsg?method=add">-- 新增DNS信息 --</option>
                      <c:forEach var="dns" items="${dnslist}">
                      <option>${dns.dnsname }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <span class="warnmsg text-warning"></span> </div>
                  <div class="form-group">
                  <label class="col-sm-2 control-label"> 备注</label>
                  <div class="col-sm-10">
                    <textarea rows="4" cols="40" class="form-control" placeholder="备注信息" name="note"></textarea>
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
<script>
$(function() {
	$(".slan,.svps,.sdns").chosen();
	
	//日历插件
	$( "#inputsdate" ).datepicker({
		showButtonPanel: true,
		changeMonth: true,
      	changeYear: false,
      	showHour:true,
      	showMinute:true,
      	showSecond:true,
      	stepHour:2,
      	stepMinute:10,
      	stepSecond:10,
      	showAnim:"slide", 
      	showButtonPanel:true,
      	dateFormat:"yy-mm-dd",
      	timeFormat:"hh:mm:00",
      	maxDate: 0,
      	monthNamesShort:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
      	closeText: '确定'
	});
  });
</script>

</body>
</html>
