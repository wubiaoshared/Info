<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="pager" value="${pg }" target="com.test.bean.Pager"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>搜索结果  - 第${pager.page }页</title>
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
      <h1> 域名信息管理 <small>搜索结果</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">域名管理</a></li>
        <li class="active">搜索结果列表</li>
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
                <table id="example1" class="table table-bordered table-striped table-hover dataTable dbc">
                  <thead>
                  <tr>
                 		<th colspan="12" class="th-header">
                 		<div class="row">
                 			<div class="col-xs-6">
                 			<span>共条${pager.totalCount }条记录<span class="chk_tips">&nbsp;</span></span>
                  		<div class="btn-group com_handle">
	                          <button type="button" name="back" class="btn btn-default btn-flat"><i class="fa fa-mail-reply"></i>返回</button>
	                          <button type="button" name="refresh" class="btn btn-default btn-flat"><i class="fa fa-refresh"></i>	刷新</button>
	                          <button type="button" name="add" class="btn btn-default btn-flat"><i class="fa fa-plus"></i>添加</button>
	                    </div>
                  		<div class="btn-group chk_handle">
                          <button type="button" name="delete" class="btn btn-default btn-flat"><i class="fa fa-trash-o"></i>删除</button>
                          <button type="button" name="sign" class="btn btn-default btn-flat dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">标记为<span class="caret"></span></button>
                          <ul class="dropdown-menu sign-dropdown" role="menu">
                          	<li class="dropdown-header"><strong>标记域名状态</strong></li>
                       		<li class="divider"></li>		
	                         <li><a name="died">已死亡</a></li>
	                         <li><a name="active">未死亡</a></li>
	                         <li><a name="pending">待定</a></li>
	                         <li class="divider"></li>
	                         
	                     </ul>
                      </div>
                      </div>
                      <div class="col-xs-6">
	                  	<form action="<%=basePath %>admin/search" method="GET">
	                  		<div class="search-box">
		                      <div class="input-group">
		                        <div class="input-group-btn select-group">
		                          <select class="form-control input-sm" name="domaintype">
		                            <option value="all">所有域名</option>
		                        	<option value="active">存活的域名</option>
		                        	<option value="died">死亡的域名</option>
		                        	<option value="pending">待定的域名</option>
		                          </select>
		                        </div>
		                        <input type="text" class="form-control input-sm" name="kws" placeholder="请输入搜索关键词" autoComplete="off">
		                        <span class="input-group-btn">
		                        <button class="btn btn-info btn-flat btn-sm" type="submit"><i class="fa fa-search"></i></button>
		                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#compose-modal"><span class="fa fa-caret-down"></span></button>
		                        </span>
		                     </div>
		                      <!-- /input-group --> 
		                    </div>
	                  	</form>
	                  </div>
                 		</div>
                 		</th>
                 	</tr>
                    <tr>
                      <th class="collapse-all td-plus"><i class="fa fa-plus"></i></th>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>域名</th>
                      <th>状态</th>
                      <th>DNS账号</th>
                      <th>VPS</th>
                      <th>上线域名</th>
                      <th>语种</th>
                      <th><a href="${sdateurl }">上线时间</a></th>
                      <th><a href="${addurl }">添加时间</a></th>
                      <th><a href="${dieurl }">死亡时间</a></th>
                    </tr>
                  </thead>
                  <tbody>
	               <c:choose>
                    	<c:when test="${requestScope.domains==null}">
                    	<tr><td colspan="11">没有查询到数据！</td></tr>
                    	</c:when>
                    	<c:otherwise>
                    <c:forEach var="domain" items="${domains}" varStatus="c">
                      <tr>
                        <td class="td-plus" data-target="#collapse${c.count }" data-toggle="collapse" aria-expanded="false" aria-controls="collapse${c.count }"><i class="fa fa-plus"></i></td>
                        <td><input type="checkbox" class="chkbox" name="chk_list" value="${domain.id }"/><span class="lbl"></span></td>
                        <td>${c.count }</td>
                        <td><a href="javascript:;" class="clipboard" title="${domain.domain }" data-clipboard-text="${domain.domain }">${domain.domain }</a></td>
                        <td><c:choose><c:when test="${domain.active==1}"><span class="label label-success">存活</span></c:when>
                          <c:when test="${domain.active==2}"><span class="label label-warning">待定</span></c:when>
                          <c:otherwise><span class="label label-danger">死亡</span></c:otherwise></c:choose></td>
                        <td>${domain.dnsname }</td>
                        <td>${domain.vpsname }</td>
                        <td><a href="javascript:;" class="clipboard" title="${domain.sdomain }" data-clipboard-text="${domain.sdomain }">${domain.sdomain }</a></td>
                        <td>${domain.language }</td>
                        <td>${domain.sdate }</td>
                        <td>${domain.addtime }</td>
                        <td>${domain.dietime }</td>
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
                    </c:otherwise>
                    </c:choose>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th class="collapse-all td-plus"><i class="fa fa-plus"></i></th>
                      <th><input type="checkbox" class="chkbox" name="chk_all"/><span class="lbl"></span></th>
                      <th>序号</th>
                      <th>域名</th>
                      <th>状态</th>
                      <th>DNS账号</th>
                      <th>VPS</th>
                      <th>上线域名</th>
                      <th>语种</th>
                      <th><a href="${sdateurl }">上线时间</a></th>
                      <th><a href="${addurl }">添加时间</a></th>
                      <th><a href="${dieurl }">死亡时间</a></th>
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
	                          <li> <a href="search${pager.pageUrl }&page=${pager.prePage }" aria-label="上一页"> <span aria-hidden="true">&laquo;</span> </a> </li>
	                          <c:choose>
	                          	<c:when test="${pager.pageCount>=6}">
		                          	<c:forEach var="page" begin="0" end="6">
		                          <c:choose>
		                          <c:when test="${page+1==pager.page }">
		                          <li class="active"><a href="admin/search${pager.pageUrl }&page=${page+1 }">${page+1 }</a></li>
		                          </c:when>
		                          <c:otherwise>
		                          <li><a href="search${pager.pageUrl }&page=${page+1 }">${page+1 }</a></li>
		                          </c:otherwise>
		                          </c:choose>
		                          </c:forEach>
	                          	</c:when>
	                          	<c:otherwise>
	                          	<c:forEach var="page" begin="1" end="${pager.pageCount}">
		                          <c:choose>
		                          <c:when test="${page==pager.page }">
		                          <li class="active"><a href="search${pager.pageUrl }&page=${page }">${page }</a></li>
		                          </c:when>
		                          <c:otherwise>
		                          <li><a href="search${pager.pageUrl }&page=${page }">${page }</a></li>
		                          </c:otherwise>
		                          </c:choose>
		                          </c:forEach>
	                          </c:otherwise>
	                          </c:choose>
	                          <li> <a href="search${pager.pageUrl }&page=${pager.nextPage }" aria-label="下一页"> <span aria-hidden="true">&raquo;</span> </a> </li>
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
		                     <input type="checkbox" class="chkbox" value="com" name="chk_list"/><span class="lbl">.com</span>
		                     <input type="checkbox" class="chkbox" value="org" name="chk_list"/><span class="lbl">.org</span>
		                     <input type="checkbox" class="chkbox" value=".net" name="chk_list"/><span class="lbl">.net</span>
		                     <input type="checkbox" class="chkbox" value="mx" name="chk_list"/><span class="lbl">.mx</span>
		                     <input type="checkbox" class="chkbox" value="ru" name="chk_list"/><span class="lbl">.ru</span>
		                     <input type="checkbox" class="chkbox" value="biz" name="chk_list"/><span class="lbl">.biz</span>
		                     <input type="checkbox" class="chkbox" value="asia" name="chk_list"/><span class="lbl">.asia</span>
		                     <input type="checkbox" class="chkbox" value="us" name="chk_list"/><span class="lbl">.us</span>
		                     <input type="checkbox" class="chkbox" value="info" name="chk_list"/><span class="lbl">.info</span>
		                     <input type="checkbox" class="chkbox" value="pw" name="chk_list"/><span class="lbl">.pw</span>
		                     <input type="checkbox" class="chkbox" value="free" name="chk_list"/><span class="lbl">免费域名</span>
		                     <input type="checkbox" class="chkbox" value="other" name="chk_list"/><span class="lbl">其它</span>
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
<script src="<%=basePath %>js/custom.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/common.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script> 
<script src="<%=basePath%>js/zeroclipboard/ZeroClipboard.js" type="text/javascript"></script>
<script type="text/javascript">

$('#daterange-btn').daterangepicker(
        {
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract('days', 1), moment().subtract('days', 1)],
                '最近7天': [moment().subtract('days', 6), moment()],
                '最近30天': [moment().subtract('days', 29), moment()],
                '当前月份': [moment().startOf('month'), moment().endOf('month')],
                '上个月份': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            showDropdowns : true,
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2015',    //最小时间
            maxDate : moment(),	//最大时间
            locale : {
                applyLabel : '确定',
                cancelLabel : '取消',
                fromLabel : '起始时间',
                toLabel : '结束时间',
                customRangeLabel : '自定义',
                daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '腊月' ],
                firstDay : 1
            }
        },
		function(start, end) {
		    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' / ' + end.format('MMMM D, YYYY'));
		    $("input[name='timerange']").val(start.format('YYYY-MM-DD') + ' / ' + end.format('YYYY-MM-DD'));
		}
);

$("#compose-modal").modal({
	backdrop : "static",
	show : false
});
</script>
</body>
</html>
