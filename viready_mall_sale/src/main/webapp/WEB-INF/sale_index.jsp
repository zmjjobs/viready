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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#index_class_1_ul").append("<li onmouseover='sale_get_class_2_by_class_1(this.value)' value="+json.id+"><a href='javascript:;'>"+json.flmch1+"</a></li>");
			});
		});
		
	});
	function sale_get_class_2_by_class_1(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_class_2_ul").empty();
			$(data).each(function(i,json){
				/* $("#index_class_2_ul").append("<li value="+json.id+"><a href='class_2_sku_search_restful/"+json.id+"/"+json.flmch2+".do'>"+json.flmch2+"</a></li>"); */
 				$("#index_class_2_ul").append("<li value="+json.id+"><a href='class_2_sku_search.do?class_2_id="+json.id+"&class_2_name="+json.flmch2+"'>"+json.flmch2+"</a></li>");
			});
			$("#index_class_2_ul").show();
		});
	}
	
	
	function keywords_submit() {
		var val = $(".lf").val().replace(/(^\s*)|(\s*$)/g,"");
		if (val == "") {
			alert("搜索条件不能为空！");
			$(".lf").focus();
			return false;
		}
		var regErr = /[`~￥【】!@#$%^&*_+<=()->{}\/'[\]]/im;
		if (regErr.test(val)) {
			alert("存在特殊字符！");
			$(".lf").focus();
			return false;
		}
		var regRight = /[\u4e00-\u9fa5_a-zA-Z0-9]+$/im;
		if (regRight.test(val)) {
			$("#keywords_form").submit();
		}else{
			alert("只能输入中文、数字和英文！");
			$(".lf").focus();
			return false;
		}
	}

</script>
<title>硅谷商城</title>
</head>
<body>

	<jsp:include page="sale_header.jsp" />
	<div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>
	<div class="search">
		<div class="logo">
			<img src="./images/logo.jpg" alt="">
		</div>
		<div class="search_on">
			<div class="se">
				<form id="keywords_form" action="search_by_keywords.do" method="post">
					<input type="text" name="keywords" class="lf"> 
					<input type="button" onclick="keywords_submit()" style="cursor: pointer;"  class="clik" value="搜索">
				</form>
			</div>
			<div class="se">
				<a href="">取暖神奇</a> <a href="">1元秒杀</a> <a href="">吹风机</a> <a
					href="">玉兰油</a>
			</div>
		</div>
		<jsp:include page="sale_miniCart.jsp" />
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul  id="index_class_1_ul">
							<li>
								<a href="">家用电器</a>
								<div class="two_nav"  id="index_class_2_ul">
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
								</div>
							</li>
							
							<li><a href="">母婴</a></li>
							<li><a href="">图书</a></li>
							<li><a href="">彩票</a></li>
							<li><a href="">理财</a></li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="banner">
		<div class="ban">
			<img src="images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>
</body>
</html>