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
<title>未上线域名列表</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="<%=basePath %>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="<%=basePath %>css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/morris/morris.css" rel="stylesheet" type="text/css" />
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
            <div class="box-header">
              <h3 class="box-title">域名信息列表</h3>
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
                          <button type="button" name="sign" class="btn btn-default btn-flat dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">标记为<span class="caret"></span></button>
                          <ul class="dropdown-menu" role="menu">
	                         <li><a href="#">Mark as read</a></li>
	                         <li><a href="#">Mark as unread</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">Move to junk</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">Delete</a></li>
	                     </ul>
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
                <table id="example1" class="table table-bordered table-striped table-hover dataTable dbc">
                  <thead>
                    <tr>
                      <th class="collapse-all td-plus"><i class="fa fa-plus"></i></th>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>域名</th>
                      <th>DNS账号</th>
                      <th>添加时间</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="domain" items="${domains}" varStatus="c">
                      <tr>
                        <td class="td-plus" data-target="#collapse${c.count }" data-toggle="collapse" aria-expanded="false" aria-controls="collapse${c.count }"><i class="fa fa-plus"></i></td>
                        <td><input type="checkbox" class="chkbox" name="chk_list" value="${domain.id }"/><span class="lbl"></span></td>
                        <td>${c.count }</td>
                        <td><a href="javascript:;" class="clipboard" title="${domain.domain }" data-clipboard-text="${domain.domain }">${domain.domain }</a></td>
                        <td>${domain.dnsname }</td>
                        <td>${domain.addtime }</td>
                        <td><a href="statistics?method=edit&id=${domain.id }&status=sonline">上线</a></td>
                      </tr>
                      <tr>
                      <c:choose>
                      	<c:when test="${domain.note==null }">
                      		<td colspan="12" class="td-collapse">
	                      		<div class="collapse table-collapse" id="collapse${c.count }">
	                      			<div class="table-collapse-note">
	                      				没有查询到相关数据！
	                      			</div>
	                      		</div>
	                      	</td>
                      	</c:when>
                      	<c:otherwise>
                      		<td colspan="12" class="td-collapse">
	                      		<div class="collapse table-collapse" id="collapse${c.count }">
	                      			<div class="table-collapse-note">
	                      				<span class="note-tip">备注:</span>
	                      				<div class="note-content" data-toggle="tooltip" data-placement="top" title="双击以编辑该区域">
	                      					${domain.note }
	                      				</div>
	                      			</div>
		                      		<div class="table-collapse-time">
		                      			最后更新时间：${domain.notedate }
		                      		</div>
	                      		</div>
	                      	</td>
                      	</c:otherwise>
                      </c:choose>
                      </tr>
                    </c:forEach>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th class="collapse-all td-plus"><i class="fa fa-plus"></i></th>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>域名</th>
                      <th>DNS账号</th>
                      <th>添加时间</th>
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
                        <li class="prev"><a href="?method=d2&page=${pager.prePage }"><i class="fa fa-angle-double-left"></i></a></li>	<c:choose><c:when test="${pager.pageCount>=6}">	<c:forEach var="page" begin="0" end="6"><c:choose><c:when test="${page+1==pager.page }"><li class="active"><a href="?method=d2&page=${page+1 }">${page+1 }</a></li></c:when><c:otherwise><li><a href="?method=d2&page=${page+1 }">${page+1 }</a></li></c:otherwise></c:choose></c:forEach></c:when><c:otherwise><c:forEach var="page" begin="0" end="${pager.pageCount-1}"><c:choose><c:when test="${page+1==pager.page }"><li class="active"><a href="?method=d2&page=${page+1 }">${page+1 }</a></li></c:when><c:otherwise><li><a href="?method=d2&page=${page+1 }">${page+1 }</a></li></c:otherwise></c:choose></c:forEach>	</c:otherwise>	</c:choose><li class="next"><a href="?method=d2&page=${pager.nextPage }"><i class="fa fa-angle-double-right"></i></a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
           	<div class="box box-success">
                  <div class="box-header">
                      <h3 class="box-title">Bar Chart</h3>
                  </div>
                  <div class="box-body chart-responsive">
                      <div class="chart" id="bar-chart" style="height: 300px;"></div>
                  </div><!-- /.box-body -->
              </div><!-- /.box -->
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

<!-- jQuery 2.0.2 --> 
<script src="<%=basePath %>js/jquery.min.js"></script> 
<!-- Bootstrap --> 
<script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script> 
<!-- AdminLTE App --> 
<script src="<%=basePath %>js/app.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/plugins/morris/morris.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>js/zeroclipboard/ZeroClipboard.js" type="text/javascript"></script>
<script src="<%=basePath %>js/custom.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/common.js" type="text/javascript"></script> 
<script type="text/javascript">
            $(function() {

                "use strict";

                


              //BAR CHART
                var bar = new Morris.Bar({
                    element: 'bar-chart',
                    resize: true,
                    data: [
                        {y: '2006', a: 100, b: 90},
                        {y: '2007', a: 75, b: 65},
                        {y: '2008', a: 50, b: 40},
                        {y: '2009', a: 75, b: 65},
                        {y: '2010', a: 50, b: 40},
                        {y: '2011', a: 75, b: 65},
                        {y: '2012', a: 100, b: 90}
                    ],
                    barColors: ['#00a65a', '#f56954'],
                    xkey: 'y',
                    ykeys: ['a', 'b'],
                    labels: ['CPU', 'DISK'],
                    hideHover: 'auto'
                });
            });


        </script>
</body>
</html>
