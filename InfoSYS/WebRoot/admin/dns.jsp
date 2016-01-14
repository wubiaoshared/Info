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
<title>DNS列表</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="<%=basePath %>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="<%=basePath %>css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
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
      <h1> DNS信息管理 <small>DNS列表</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">DNS管理</a></li>
        <li class="active">DNS信息列表</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">DNS信息列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive">
              <div class="dataTables_wrapper form-inline" role="grid">
                <div class="row">
                  <div class="col-xs-6">
                  	<div class="btn-group">
                          <button type="button" name="back" class="btn btn-default btn-flat"><i class="fa fa-mail-reply"></i>返回</button>
                          <button type="button" name="refresh" class="btn btn-default btn-flat"><i class="fa fa-refresh"></i>	刷新</button>
                          <button type="button" name="delete" class="btn btn-default btn-flat"><i class="fa fa-trash-o"></i>删除</button>
                          <button type="button" name="add" class="btn btn-default btn-flat"><i class="fa fa-plus"></i>添加</button>
                      </div>
                  </div>
                  <div class="col-xs-6">
                  	<form action="<%=basePath %>admin/search" method="GET">
                  		<div class="search-box">
	                      <div class="input-group">
	                        <div class="input-group-btn select-group">
	                          <select class="form-control" name="domaintype">
	                            <option value="all">所有域名</option>
	                        	<option value="active">存活的域名</option>
	                        	<option value="died">死亡的域名</option>
	                        	<option value="pending">待定的域名</option>
	                          </select>
	                        </div>
	                        <input type="text" class="form-control" name="kws" placeholder="请输入搜索关键词" autoComplete="off">
	                        <span class="input-group-btn">
	                        <button class="btn btn-info btn-flat" type="submit"><i class="fa fa-search"></i></button>
	                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#compose-modal"><span class="fa fa-caret-down"></span></button>
	                        </span>
	                     </div>
	                      <!-- /input-group --> 
	                    </div>
                  	</form>
                  </div>
                </div>
                <table id="example1" class="table table-bordered table-striped table-hover dataTable">
                  <thead>
                    <tr>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>dns账号</th>
                      <th>域名服务器</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="dns" items="${dnslist}" varStatus="c">
                      <tr>
                        <td><input type="checkbox" class="chkbox" name="chk_list" value="${dns.id }"/><span class="lbl"></span></td>
                        <td>${c.count }</td>
                        <td>${dns.dnsname }</td>
                        <td>${dns.nameserver }</td>
                        <td><a href="https://www.cloudflare.com/a/overview" target="_blank" >登录</a></td>
                      </tr>
                    </c:forEach>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>dns账号</th>
                      <th>域名服务器</th>
                      <th>操作</th>
                    </tr>
                  </tfoot>
                </table>
                <div class="row">
                  <div class="col-xs-6">
                    <div id="example1_length" class="dataTables_length">
                      <label>
	                        <select size="1" name="example1_length" aria-controls="example1">
	                          <option value="10" selected="selected">10</option>
	                          <option value="20">25</option>
	                          <option value="50">50</option>
	                        </select>
		                        条记录每页</label>
                    </div>
                    <div class="dataTables_info" id="example2_info">当前第 ${pager.page }页，共 ${pager.pageCount }页，共 ${pager.totalCount }条结果</div>
                  </div>
                  <div class="col-xs-6">
                    <div class="dataTables_paginate paging_bootstrap">
                      <ul class="pagination">
                        <li class="prev"><a href="?page=${pager.prePage }"><i class="fa fa-angle-double-left"></i></a></li>	<c:choose><c:when test="${pager.pageCount>=6}">	<c:forEach var="page" begin="0" end="6"><c:choose><c:when test="${page+1==pager.page }"><li class="active"><a href="?page=${page+1 }">${page+1 }</a></li></c:when><c:otherwise><li><a href="?page=${page+1 }">${page+1 }</a></li></c:otherwise></c:choose></c:forEach></c:when><c:otherwise><c:forEach var="page" begin="0" end="${pager.pageCount-1}"><c:choose><c:when test="${page+1==pager.page }"><li class="active"><a href="?page=${page+1 }">${page+1 }</a></li></c:when><c:otherwise><li><a href="?page=${page+1 }">${page+1 }</a></li></c:otherwise></c:choose></c:forEach>	</c:otherwise>	</c:choose><li class="next"><a href="?page=${pager.nextPage }"><i class="fa fa-angle-double-right"></i></a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.box-body --> 
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
<div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><i class="fa fa-location-arrow"></i> 精确搜索</h4>
            </div>
            <form action="<%=basePath %>admin/search" method="GET" class="search">
                <div class="modal-body">
                	<div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">关键词:</span>
                            <input name="kws" type="text" class="form-control" placeholder="请输入搜索关键词" autoComplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                        	<span class="input-group-addon">域名状态:</span>
		                        <select name="domaintype" class="form-control select-control">
		                        	<option value="all">所有域名</option>
		                        	<option value="active">存活的域名</option>
		                        	<option value="died">死亡的域名</option>
		                        	<option value="pending">待定的域名</option>
		                        </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                        	<span class="input-group-addon">时间范围:</span>
                            <input type="text" name="timerange" class="form-control" placeholder="请选择域名上线时间范围" readonly="readonly">
                            <span class="input-group-btn">
                            <button class="btn btn-default pull-right" id="daterange-btn">
                                <i class="fa fa-calendar"></i> 时间选择
                                <i class="fa fa-caret-down"></i>
                            </button>
                            </span>
                        </div>

                    </div>
                    <div class="col-xs-6">
	                    <div class="form-group">
	                    	<label>请选择要搜索的字段类型:</label>
	                        <div class="input-group">
		                            <label>
		                                <input type="radio" name="searchtype" value="domain" class="minimal" checked="checked"/>
		                             	域名
		                            </label>
	                        </div>
	                        
	                        <div class="input-group">
	                            <label>
	                                <input type="radio" name="searchtype" value="dns" class="minimal"/>
	                                DNS账号
	                            </label>
	                        </div>
	                        <div class="input-group">
	                            <label>
	                                <input type="radio" name="searchtype" value="language" class="minimal"/>
	                               	语种
	                            </label>
	                        </div>
	                        <div class="input-group">
	                            <label>
	                                <input type="radio" name="searchtype" value="vps" class="minimal"/>
	                               	VPS信息
	                            </label>
	                        </div>
	                    </div>
                    </div>
                    <div class="col-xs-6">
	                    <div class="form-group">
	                    	<label>请选择域名上线状态:</label>
	                        <div class="input-group">
		                            <label>
		                                <input type="radio" name="onlinetype" value="all" class="minimal" checked="checked"/>
		                             	查看所有域名
		                            </label>
	                        </div>
	                        
	                        <div class="input-group">
	                            <label>
	                                <input type="radio" name="onlinetype" value="online" class="minimal"/>
	                             		 只看已上线域名
	                            </label>
	                        </div>
	                        <div class="input-group">
	                            <label>
	                                <input type="radio" name="onlinetype" value="offline" class="minimal"/>
	                               		 只看未上线域名
	                            </label>
	                        </div>
	                    </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group">
	                	<label>域名后缀:</label>
	                	<div class="checkbox">
		                     <input type="checkbox" class="chkbox" value="all" name="chk_all"/><span class="lbl">所有后缀</span>
		                     <input type="checkbox" class="chkbox" value="india" name="chk_list"/><span class="lbl">印度域名</span>
		                     <input type="checkbox" class="chkbox" value=".com" name="chk_list"/><span class="lbl">.com</span>
		                     <input type="checkbox" class="chkbox" value=".org" name="chk_list"/><span class="lbl">.org</span>
		                     <input type="checkbox" class="chkbox" value=".net" name="chk_list"/><span class="lbl">.net</span>
		                     <input type="checkbox" class="chkbox" value=".mx" name="chk_list"/><span class="lbl">.mx</span>
		                     <input type="checkbox" class="chkbox" value=".ru" name="chk_list"/><span class="lbl">.ru</span>
		                     <input type="checkbox" class="chkbox" value=".biz" name="chk_list"/><span class="lbl">.biz</span>
		                     <input type="checkbox" class="chkbox" value=".asia" name="chk_list"/><span class="lbl">.asia</span>
		                     <input type="checkbox" class="chkbox" value=".us" name="chk_list"/><span class="lbl">.us</span>
		                     <input type="checkbox" class="chkbox" value=".info" name="chk_list"/><span class="lbl">.info</span>
		                     <input type="checkbox" class="chkbox" value=".pw" name="chk_list"/><span class="lbl">.pw</span>
		                     <input type="checkbox" class="chkbox" value=".free" name="chk_list"/><span class="lbl">免费域名</span>
		                     <input type="checkbox" class="chkbox" value=".other" name="chk_list"/><span class="lbl">其它</span>
			             </div>
	                </div>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer clearfix">

                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 搜索</button>
                    
                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal"><i class="fa fa-times"></i> 关闭窗口</button>

                </div>
            </form>
        </div>
    </div>
</div>


<!-- jQuery 2.0.2 --> 
<script src="<%=basePath %>js/jquery.min.js"></script> 
<!-- Bootstrap --> 
<script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script> 
<!-- AdminLTE App --> 
<script src="<%=basePath %>js/app.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 
<script src="<%=basePath%>js/zeroclipboard/ZeroClipboard.js" type="text/javascript"></script>
<script src="<%=basePath%>js/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/custom.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/common.js" type="text/javascript"></script> 
</body>
</html>
