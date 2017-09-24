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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function sku_attr_show(attr_id,isChecked){
		if(isChecked){
			$("#sku_inner_"+attr_id).show();
		}else{
			$("#sku_inner_"+attr_id).hide();
		}
	}
	
</script>
<title>硅谷商城后台</title>
</head>
<body>

	
		<!-- 展示所有属性的列表 -->
		<c:forEach items="${list_attr}" var="attr" varStatus="status">
			<input type="checkbox" value="${attr.id}" name="list_av[${status.index }].shxm_id" onclick="sku_attr_show(${attr.id },this.checked)">${attr.shxm_mch } 
		</c:forEach>
		<hr>
		
		<!-- 展示每个属性对应的所有属性值和属性值名称的列表 -->
		<c:forEach items="${list_attr}" var="attr" varStatus="status">
			<div id="sku_inner_${attr.id }" style="display:none;">
				<c:forEach items="${attr.list_value}" var="val">
					<input type="radio" value="${val.id}" name="list_av[${status.index }].shxzh_id">${val.shxzh }${val.shxzh_mch }
				</c:forEach>
				<br>
			</div>
		</c:forEach>
		<hr>
		库存名称：<input type="text" name="sku_mch">
		库存数量：<input type="text" name="kc">
		库存价格：<input type="text" name="jg">
		库存地址：<input type="text" name="kcdzh">
		<br>
		<input type="submit" value="提交">

</body>
</html>