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
<link type="text/css" rel="stylesheet" href="css/validate.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>硅谷商城</title>
</head>
<body>
<div id="main">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="bg bg_top_left"></td>
    <td class="bg_top"></td>
    <td class="bg bg_top_right"></td>
  </tr>
  <tr>
    <td class="bg_left"></td>
    <td class="content">
      <form action="" method="post" name="myform" onsubmit="return checkForm()">
        <dl>
          <dt>用户名：</dt>
          <dd><input type="text" id="userName" class="inputs" onfocus="userNameFocus()" onblur="userNameBlur()" /> </dd>
          <div id="userNameId"></div>
        </dl>
         <dl>
          <dt>登录密码：</dt>
          <dd><input type="password" id="pwd" class="inputs"  onfocus="pwdFocus()" onblur="pwdBlur()"/></dd>
          <div id="pwdId"></div>
        </dl>
         <dl>
          <dt>重复登录密码：</dt>
          <dd><input type="password" id="repwd" class="inputs"  onblur="repwdBlur()"/></dd>
          <div id="repwdId"></div>
        </dl>
        <dl>
          <dt>性别：</dt>
          <dd><input name="sex" type="radio" value="" checked="checked"/>男 <input name="sex" type="radio" value="" />女 </dd>
        </dl>
        <dl>
          <dt>真实姓名：</dt>
          <dd><input type="text" id="realName" class="inputs" onblur="aa()" /></dd>
        </dl>
        <dl>
          <dt>昵称：</dt>
          <dd><input type="text" id="nickName" class="inputs"  onfocus="nickNameFocus()" onblur="nickNameBlur()"/></dd>
          <div id="nickNameId"></div>
        </dl>
        <dl>
          <dt>关联手机号：</dt>
          <dd><input type="text" id="tel" class="inputs"  onfocus="telFocus()" onblur="telBlur()" /></dd>
          <div id="telId"></div>
        </dl>
        <dl>
          <dt>保密邮箱：</dt>
          <dd><input type="text" id="email" class="inputs" onfocus="emailFocus()" onblur="emailBlur()" /></dd>
          <div id="emailId"></div>
        </dl>
        <dl>
          <dt></dt>
          <dd><input name=" " type="image" src="images/submit.png"/></dd>
        </dl>
      </form>
    </td>
    <td class="bg_right"></td>
  </tr>
  <tr>
     <td class="bg bg_end_left"></td>
    <td class="bg_end"></td>
    <td class="bg bg_end_right"></td>
  </tr>
</table>

</div>
</body>

</html>