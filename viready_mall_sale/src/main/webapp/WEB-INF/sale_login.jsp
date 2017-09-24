<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>


<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function to_submit(){
		$("#login_form").submit();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
		<div class="up">
			<img src="images/logo.jpg" height="45px;" class="hy_img"/>
			<div class="hy">
				欢迎登录
			</div>
		</div>
		<div class="middle">
			<div class="login">
				<div class="l1 ">
					<div class="l1_font_01">硅谷会员</div>
					<a class="l1_font_02" href="<%=application.getContextPath() %>/to_regist.action">用户注册</a>
				</div>
				<div class="blank_01"></div>
				<div class="ts">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${err}
				</div>
				<div class="blank_01"></div>
			<form action="login.do" id="login_form" method="post">
				<div class="input1">
					<input type="text" class="input1_01" name="yh_mch" />
				</div>
				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="input2">
					<input type="text" class="input1_01" name="yh_mm" />
				</div>

				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="box_01">
					<input type="checkbox" class="box_01_box" />
					<div class="box_01_both">
						<div class="box_01_both_1">
						系统用户<input type="radio" name="user_type" value="user">&nbsp;&nbsp;&nbsp;
						测试用户<input type="radio" name="user_type" value="testuser">
						</div>
						<div class="box_01_both_2">忘记密码？</div>
					</div>
				</div>
			</form>
			<div class="blank_01"></div>
				<a href="javascript:;" class="aline">
					<div class="red_button" onclick="to_submit()">
						登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
					</div>
				</a>
				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="box_down">
					<div class="box_down_1">使用合作网站账号登录京东：</div>
					<div class="box_down_1">京东钱包&nbsp;&nbsp;|&nbsp;&nbsp;QQ 
					&nbsp;&nbsp;|&nbsp;&nbsp;微信
					</div>
				</div>
			</div>	
		</div>
		
		<div class="down">
			<br />
			Copyright©2004-2015  xu.jb.com 版权所有
		</div>
</body>
</html>