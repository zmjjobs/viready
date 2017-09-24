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

</script>
<title>硅谷商城-订单确认</title>
</head>
<body>
	<form action="save_order.do" method="post">
		<c:forEach items="${list_address }" var="address">
			<input type="radio" name="address_id" value="${address.id }">${address.dzh_mch }  ${address.shjr }
		</c:forEach>
		<hr>
		${order.jdh }  ￥${order.zje }<br>
		<c:forEach items="${order.list_flow }" var="flow">
			${flow.psfsh }：${flow.mqdd }<br>
			<div style="border:red 1px solid;">
				<c:forEach items="${flow.list_order_info}" var="order_info">
					<img src="upload/image/${order_info.shp_tp }" width="70px">
					${order_info.sku_mch }   ${order_info.sku_jg }<br>
				</c:forEach>
			</div>
			<br>
		</c:forEach>
		<br>
		<input type="submit" value="提交">
	</form>
</body>
</html>