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
<style type="text/css">
	.img_div {
		 border:red 1px solid;
		 width:130px;
		 height:200px;
		 float:left;
		 margin-left: 20px;
		 margin-top: 20px;
	}
	img {
		width: 100px;
	}
	span{
		font-size: 18px;
		color: red;
	}
</style>
<script type="text/javascript">
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body>
	<c:forEach items="${list_sku}" var="sku">
		<a href="get_sku_detail_by_id.do?sku_id=${sku.id }&spu_id=${sku.shp_id}" target="_blank">
			<div class="img_div">
				<img src="upload/image/${sku.spu.shp_tp}"/><br>
				<span>￥${sku.jg}</span><br>
				${sku.sku_mch}<br>
			</div>
		</a>
	</c:forEach>
</body>
</html>