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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function detail_show(sku_id){
		$("#show_detail_"+sku_id).show();
	}
	function detail_hide(sku_id){
		$("#show_detail_"+sku_id).hide();
	}
</script>
<title>商品详情页面</title>
</head>
<body>
	<jsp:include page="sale_header.jsp"/>
	商品详情页面
	<jsp:include page="sale_miniCart.jsp"/>
	<hr>
	<img alt="" src="upload/image/${detail_sku.spu.shp_tp }" width="100px" />
	<br> ${detail_sku.sku_mch }
	<br> ${detail_sku.jg }
	<br> ${detail_sku.kc }
	<br>
	<hr>
	<c:forEach items="${list_sku }" var="sku">
	${sku.id }
		<div onmouseover="detail_show(${sku.id })"
			onmouseout="detail_hide(${sku.id })">
			<a
				href="get_sku_detail_by_id.do?sku_id=${sku.id }&spu_id=${sku.shp_id}">${sku.sku_mch}</a>
			<div id="show_detail_${sku.id }" style="display: none">
				<form action="add_cart.do" method="post">
					<input type="hidden" name="sku_mch" value="${detail_sku.sku_mch}" />
					<input type="hidden" name="sku_jg" value="${detail_sku.jg}" />
					<input type="hidden" name="tjshl" value="1" />
					<input type="hidden" name="hj" value="${detail_sku.jg}" />
					<input type="hidden" name="shp_id" value="${detail_sku.spu.id}" />
					<input type="hidden" name="sku_id" value="${detail_sku.id}" />
					<input type="hidden" name="shp_tp" value="${detail_sku.spu.shp_tp}" />
					<input type="hidden" name="shfxz" value="1" />
					<input type="hidden" name="kcdzh" value="${detail_sku.kcdzh}" />
					<input type="submit" value="添加购物车"/>
				</form>
				<br>
				<c:forEach items="${detail_sku.list_av_name }" var="av">
					${av.shxm_mch }：${av.shxzh_mch }&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<br> ${detail_sku.spu.shp_msh }<br>
				<c:forEach items="${detail_sku.list_image }" var="img">
					<img alt="" src="upload/image/${img.url }" width="100px" />
				</c:forEach>
			</div>

		</div>
	</c:forEach>
	<hr>

	<hr>

</body>
</html>