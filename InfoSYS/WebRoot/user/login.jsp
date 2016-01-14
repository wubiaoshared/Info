<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${user!=null }">
<c:redirect url="../admin/statistics"></c:redirect>
</c:if>
<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="<%=basePath %>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="<%=basePath %>css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="header">登录</div>
            <form action="loginService" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" placeholder="账号"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="pass" class="form-control" placeholder="密码"/>
                    </div>          
                    <div class="form-group">
                        <input type="checkbox" name="RememeberMe"/> 记住我
                    </div>
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-olive btn-block submit" name="signin">登录</button>  
                    
                    <p><a href="#">忘记密码</a></p>
                    
                    <a href="register.html" class="text-center">注册</a>
                </div>
            </form>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="<%=basePath %>js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script>        
		<script type="text/javascript">
		$(function(){
			$("button.submit").click(function(e){
				var _form = $(this).parents("form");
				var _url = _form.attr("action");
				$.ajax({
					dataType:"json",
					type: "POST",
					timeout : 6000,
					data : _form.serialize(),
					url: _url,
					success:function(data,status,xhr){
						if(typeof data.url != 'undefined' && data.url != '') {
							data.url = data.url.replace('&amp;','&');
							var win = top || window;
								win.location.replace(data.url);
						}else {
							alert(data.msg);
						}
						
					}
				});
				e.preventDefault();
			});
		});
		</script>
    </body>
</html>