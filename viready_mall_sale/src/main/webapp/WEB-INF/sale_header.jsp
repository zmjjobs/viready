<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="shortcut icon" type="image/icon" href="images/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var yh_mch = get_cookie_value("yh_mch");

		var name = get_cookie_value("test");

		$("#header_yh_mch").prepend(yh_mch);
	});

	function get_cookie_value(key) {
		var cookies = document.cookie;
		//解码带有中文的cookie
		cookies = decodeURIComponent(cookies);
		cookies.replace(/\s/, "");
		var cookie_array = cookies.split(";");
		for (i = 0; i < cookie_array.length; i++) {
			var cookie_kv = cookie_array[i].split("=");
			if (cookie_kv[0] == key) {
				return cookie_kv[1];
			}
		}
		return "";
	}
</script>
<title>硅谷商城-页面顶部</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${empty login_user }">
				<a href="register.do">免费注册</a>
				<a id="header_yh_mch" href="goto_login.htm">您好，请登录</a>
			</c:if>
			<c:if test="${not empty login_user }">
				<a href="register.do">我的订单 </a>
				<a href="register.do">欢迎【${login_user.yh_nch }】光临</a>
				<a href="logout.do">注销</a>
			</c:if>
		</div>
	</div>

	<hr>
</body>
</html>