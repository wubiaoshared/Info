<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- Left side column. contains the logo and sidebar -->
  <aside class="left-side sidebar-offcanvas"> 
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar"> 
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image"> <img src="<%=basePath %>images/avatar5.png" class="img-circle" alt="User Image" /> </div>
        <div class="pull-left info">
          <p>Hello, Jane</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a> </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search..."/>
          <span class="input-group-btn">
          <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
          </span> </div>
      </form>
      <!-- /.search form --> 
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="treeview active"> <a href="#"> <i class="fa fa-dashboard"></i> <span>域名管理</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath %>admin/statistics"><i class="fa fa-angle-double-right"></i> 已上线域名</a></li>
            <li><a href="<%=basePath %>admin/statistics?method=d2"><i class="fa fa-angle-double-right"></i> 未上线域名</a></li>
            <li><a href="<%=basePath %>admin/statistics?method=add"><i class="fa fa-angle-double-right"></i> 添加已上线域名</a></li>
            <li><a href="<%=basePath %>admin/statistics?method=add2"><i class="fa fa-angle-double-right"></i> 添加未上线域名</a></li>
          </ul>
        </li>
        <li class="treeview"> <a href="#"> <i class="fa fa-th"></i> <span>DNS管理</span><i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath %>admin/dns"><i class="fa fa-angle-double-right"></i> DNS信息</a></li>
            <li><a href="<%=basePath %>admin/dns?method=add"><i class="fa fa-angle-double-right"></i> 添加DNS信息</a></li>
          </ul>
        </li>
        <li class="treeview"> <a href="#"> <i class="fa fa-bar-chart-o"></i> <span>VPS管理</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-angle-double-right"></i> 我的VPS</a></li>
            <li><a href="#"><i class="fa fa-angle-double-right"></i> 添加VPS</a></li>
          </ul>
        </li>
        <li class="treeview"> <a href="#"> <i class="fa fa-calendar"></i> <span>日程</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="../UI/general.html"><i class="fa fa-angle-double-right"></i> 今日日程</a></li>
            <li><a href="../UI/icons.html"><i class="fa fa-angle-double-right"></i> 日历</a></li>
            <li><a href="../UI/buttons.html"><i class="fa fa-angle-double-right"></i> 待办事项</a></li>
          </ul>
        </li>
        <li class="treeview"> <a href="#"> <i class="fa fa-edit"></i> <span>工具</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="../forms/general.html"><i class="fa fa-angle-double-right"></i> General Elements</a></li>
            <li><a href="../forms/advanced.html"><i class="fa fa-angle-double-right"></i> Advanced Elements</a></li>
            <li><a href="../forms/editors.html"><i class="fa fa-angle-double-right"></i> Editors</a></li>
          </ul>
        </li>
        <li class="treeview"> <a href="#"> <i class="fa fa-table"></i> <span>Tables</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="../tables/simple.html"><i class="fa fa-angle-double-right"></i> Simple tables</a></li>
            <li><a href="../tables/data.html"><i class="fa fa-angle-double-right"></i> Data tables</a></li>
          </ul>
        </li>
        <li> <a href="../calendar.html"> <i class="fa fa-calendar"></i> <span>Calendar</span> <small class="badge pull-right bg-red">3</small> </a> </li>
        <li> <a href="../mailbox.html"> <i class="fa fa-envelope"></i> <span>Mailbox</span> <small class="badge pull-right bg-yellow">12</small> </a> </li>
        <li class="treeview active"> <a href="#"> <i class="fa fa-folder"></i> <span>Examples</span> <i class="fa fa-angle-left pull-right"></i> </a>
          <ul class="treeview-menu">
            <li><a href="invoice.html"><i class="fa fa-angle-double-right"></i> Invoice</a></li>
            <li><a href="login.html"><i class="fa fa-angle-double-right"></i> Login</a></li>
            <li><a href="register.html"><i class="fa fa-angle-double-right"></i> Register</a></li>
            <li><a href="lockscreen.html"><i class="fa fa-angle-double-right"></i> Lockscreen</a></li>
            <li><a href="404.html"><i class="fa fa-angle-double-right"></i> 404 Error</a></li>
            <li><a href="500.html"><i class="fa fa-angle-double-right"></i> 500 Error</a></li>
            <li class="active"><a href="blank.html"><i class="fa fa-angle-double-right"></i> Blank Page</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar --> 
  </aside>
