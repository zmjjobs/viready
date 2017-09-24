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
<style type="text/css">
	button {
		width: 28px;
	}
</style>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	function delete_carts_by_sku_id(sku_id){
		$.post("delete_carts_by_sku_id.do",{sku_id:sku_id});
	}

	function sale_change_cart(checked,tjshl,sku_id){
		if(checked){
			checked	= "1";
		}else{
			checked = "0";
		}
		
		/* 返回的页面还是自己，自己刷新自己 */
		$.post("change_cart.do",{shfxzh:checked,tjshl:tjshl,sku_id:sku_id},function(data){
			$("#sale_cart_list_inner").html(data);
			//location.reload();
			//location.replace(location.href);
			$("name="+sku_id).val(tjshl);
		});
	}
</script>
<title>硅谷商城-购物车列表</title>
</head>
<body>
	购物车列表${test }<br>
	<form action="goto_order.htm" method="post">
		<c:forEach items="${list_cart }" var="cart">
			<input onclick="sale_change_cart(this.checked,-1,${cart.sku_id})" type="checkbox" ${cart.shfxzh == 1 ? "checked" : "" }/><img src="upload/image/${cart.shp_tp }" width="100px"/>${cart.sku_mch }${cart.sku_jg }
			<button onclick="sale_change_cart(0,${cart.tjshl - 1},${cart.sku_id})">-</button>
			<input name="${cart.sku_id}" value="${cart.tjshl }" style="width:20px;text-align: center;"/>
			<button onclick="sale_change_cart(0,${cart.tjshl + 1},${cart.sku_id})">+</button>
			<input type="button" onclick="delete_carts_by_sku_id(${cart.sku_id})" value="删除">
			<br>
		</c:forEach>
		<br>
		总计：${sum }<br>
		<input type="submit" value="结算">
	</form>
	
</body>
</html>