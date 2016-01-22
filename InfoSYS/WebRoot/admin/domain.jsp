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
<title>域名列表</title>
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
                <table id="example1" class="table table-bordered main table-striped table-hover dataTable dbc">
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
                      <th>上线时间</th>
                      <th>添加时间</th>
                      <th>死亡时间</th>
                    </tr>
                  </thead>
                  <tbody>
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
                      <th>上线时间</th>
                      <th>添加时间</th>
                      <th>死亡时间</th>
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
           	<div class="box box-success">
                  <div class="box-header">
                      <h3 class="box-title">语种分布图</h3>
                  </div>
                  <div class="box-body chart-responsive">
                      <div class="chart" id="bar-chart" style="height: 300px;"></div>
                  </div><!-- /.box-body -->
              </div><!-- /.box -->
              <!-- Chat box -->
              <div class="box box-success">
                  <div class="box-header">
                      <h3 class="box-title"><i class="fa fa-comments-o"></i> 聊天室</h3>
                      <div class="box-tools pull-right" data-toggle="tooltip" title="Status">
                      	  <a href="javascript:;" class="cls">清屏</a>
                          <div class="btn-group" data-toggle="btn-toggle" >
                              <button type="button" class="btn btn-default btn-sm active"><i class="fa fa-square text-green"></i></button>                                            
                              <button type="button" class="btn btn-default btn-sm"><i class="fa fa-square text-red"></i></button>
                          </div>
                      </div>
                  </div>
                  <div class="box-body chat" id="chat-box">
                      <!-- chat item -->
                      <div class="item">
                          <img src="../images/avatar.png" alt="user image" class="online"/>
                          <p class="message">
                              <a href="#" class="name">
                                  <small class="text-muted pull-right"><i class="fa fa-clock-o"></i> 2:15</small>
                                  Mike Doe
                              </a>
                              I would like to meet you to discuss the latest news about
                              the arrival of the new theme. They say it is going to be one the
                              best themes on the market
                          </p>
                          <div class="attachment">
                              <h4>Attachments:</h4>
                              <p class="filename">
                                  Theme-thumbnail-image.jpg
                              </p>
                              <div class="pull-right">
                                  <button class="btn btn-primary btn-sm btn-flat">Open</button>
                              </div>
                          </div><!-- /.attachment -->
                      </div><!-- /.item -->
                      <!-- chat item -->
                      <div class="item">
                          <img src="../images/avatar2.png" alt="user image" class="offline"/>
                          <p class="message">
                              <a href="#" class="name">
                                  <small class="text-muted pull-right"><i class="fa fa-clock-o"></i> 5:15</small>
                                  Jane Doe
                              </a>
                              I would like to meet you to discuss the latest news about
                              the arrival of the new theme. They say it is going to be one the
                              best themes on the market
                          </p>
                      </div><!-- /.item -->
                      <!-- chat item -->
                      <div class="item">
                          <img src="../images/avatar3.png" alt="user image" class="offline"/>
                          <p class="message">
                              <a href="#" class="name">
                                  <small class="text-muted pull-right"><i class="fa fa-clock-o"></i> 5:30</small>
                                  Susan Doe
                              </a>
                              I would like to meet you to discuss the latest news about
                              the arrival of the new theme. They say it is going to be one the
                              best themes on the market
                          </p>
                      </div><!-- /.item -->
                  </div><!-- /.chat -->
                  <div class="box-footer">
                      <div class="input-group">
                          <input class="form-control" type="text" placeholder="请输入要发送内容..."/>
                          <div class="input-group-btn">
                              <button class="btn btn-success"><i class="fa fa-plus"></i></button>
                          </div>
                      </div>
                  </div>
              </div><!-- /.box (chat box) -->
              
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
<script type="text/javascript">
$(function(){
	$.ajax({
		dataType:"json",
		timeout : 6000,
		url:"statistics?method=data",
		success:
			onDataRecived
	});
	
	function onDataRecived(data) {
		var bar_data = [
						{
							label : "语种",
						    data: data.statistical,
						    color: "#3c8dbc"
						}
	                ];
		$.plot("#bar-chart", bar_data, {
			//开启鼠标移动和点击事件  折线图外框颜色 和 外框的宽度  
	        grid: {
	        	show:true,	//显示网格
	        	hoverable: true,
	        	autoHighlight: true,
	        	clickable: true,
	            borderWidth: 1,
	            borderColor: "#f3f3f3",
	            tickColor: "#f3f3f3",
	            autoHighlight:true
	        },
	      	//控制线的填充、点的显示  
	        series: {
	            bars: {
	                show: true,
	                barWidth: 0.2,
	                align: "center"
	            }
	        },
	        //x轴的最大最小范围 和 刻度自定义
	        xaxis: {
	            mode: "categories",
	            tickLength: 0
	        },
	        yaxis: {//y轴的最小范围  
	            min: 0,
	            // max: 50  
	        }
	    });
		
	}
	
	function showTooltip(x, y, contents) {//浮动块信息  
        $('<div id="tooltip">' + contents + '</div>').css( {  
            position: 'absolute',  
            display: 'none',  
            top: y - 30,  
            left: x - 9,  
            border: '1px solid #fdd',  
            padding: '2px',  
            'background-color': '#000',
            'color': '#fff',
            opacity: 0.80  
        }).appendTo("body").fadeIn(200);  
    }
	var previousPoint = null;  
    $("#bar-chart").bind("plothover", function (event, pos, item) {  
      if (item) {  
          if (previousPoint != item.dataIndex) {  
              previousPoint = item.dataIndex;  
              $("#tooltip").remove();  
              var x = item.datapoint[0],//x y 轴位置  
                  y = item.datapoint[1],  
                  n = x;  
                  var language = item.series['data'][n][0],//获取当前焦点的 数据信息 通过n（即x轴位置获取数据）  
                      count = item.series['data'][n][1];
              var contents = count;
  
              showTooltip(item.pageX, item.pageY,contents);  
          }  
      }  
      else {  
          $("#tooltip").remove();  
          previousPoint = null;              
      }  
    }); 
    
});
</script>
</body>
</html>
