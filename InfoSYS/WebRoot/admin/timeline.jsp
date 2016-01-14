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
<title>DNS信息添加</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="<%=basePath %>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="<%=basePath %>css/ionicons.min.css" rel="stylesheet" type="text/css" />
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
      <h1> 时间线 <small>我的时间线</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">时间线</a></li>
        <li class="active">我的时间线</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <!-- Main content -->
                <section class="content">

                    <!-- row -->
                    <div class="row">                        
                        <div class="col-md-12">
                            <!-- The time line -->
                            <ul class="timeline">
                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-red">
                                        10 Feb. 2014
                                    </span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-envelope bg-blue"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>
                                        <h3 class="timeline-header"><a href="#">Support Team</a> sent you and email</h3>
                                        <div class="timeline-body">
                                            Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                            weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                            jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                            quora plaxo ideeli hulu weebly balihoo...
                                        </div>
                                        <div class='timeline-footer'>
                                            <a class="btn btn-primary btn-xs">Read more</a>
                                            <a class="btn btn-danger btn-xs">Delete</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-user bg-aqua"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>
                                        <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted your friend request</h3>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-comments bg-yellow"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>
                                        <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>
                                        <div class="timeline-body">
                                            Take me to your leader!
                                            Switzerland is small and neutral!
                                            We are more like Germany, ambitious and misunderstood!
                                        </div>
                                        <div class='timeline-footer'>
                                            <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-green">
                                        3 Jan. 2014
                                    </span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-camera bg-purple"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>
                                        <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>
                                        <div class="timeline-body">
                                            <img src="http://placehold.it/150x100" alt="..." class='margin' />
                                            <img src="http://placehold.it/150x100" alt="..." class='margin'/>
                                            <img src="http://placehold.it/150x100" alt="..." class='margin'/>
                                            <img src="http://placehold.it/150x100" alt="..." class='margin'/>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-video-camera bg-maroon"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 5 days ago</span>
                                        <h3 class="timeline-header"><a href="#">Mr. Doe</a> shared a video</h3>
                                        <div class="timeline-body">
                                            <iframe width="300" height="169" src="//www.youtube.com/embed/fLe_qO4AE-M" frameborder="0" allowfullscreen></iframe>
                                        </div>
                                        <div class="timeline-footer">
                                            <a href="#" class="btn btn-xs bg-maroon">See comments</a>
                                        </div>
                                    </div>
                                    
                                </li>
                                <!-- END timeline item -->
                                <li>
                                    <i class="fa fa-clock-o"></i>
                                </li>
                            </ul>
                            <div id="loadMore" class="np-load-more">
			                    <span>加载更多</span>
			                    <em style="display: none;">没有更多了</em>
			                </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </section><!-- /.content -->
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
<script src="<%=basePath %>js/custom.js" type="text/javascript"></script> 
<script src="<%=basePath %>js/common.js" type="text/javascript"></script> 

</body>
</html>
