<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html id="login_html">
<head>
	<meta charset=utf-8>
    <title>登陆 在浙里 分享你我的社交</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/languages/zh-cn/jquery.validationEngine.lang.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js" />" ></script>
</head>
<body>
	<div class="mt-80" style="width:380px;margin-left:700px;">
	<div class="hero-unit board-1">
	<form:form id="signInForm"  cssClass="sign-in" modelAttribute="signInCredentialVo" method="post">	
		<div class="row-fluid">
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-envelope"></i></span>
						<input id="user-name" class="validate[required,custom[email]] input-xlarge" name="signInName" type="email" style="height:18px" placeholder="在浙里注册邮箱" autocomplete="off" value="<c:out value="${signInCredentialVo.signInName}" />"/>
						<div class="f-l hint"><form:errors path="signInName" cssClass="alert alert-error" /></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid" style="margin-top:10px">
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-lock"></i></span>
						<input id="user-pwd" class="validate[required] input-xlarge" name="signInPassword" type="password" maxlength="64" style="height:18px" placeholder="密码" autocomplete="off" value="<c:out value="${signInCredentialVo.signInPassword}" />" />
						<div class="f-l hint"><form:errors path="signInPassword" cssClass="alert alert-error" /></div>
					</div>
				</div>
			</div>
		</div>	
		<div class="row-fluid" style="margin-top:15px;">
			<div class="span8">
				<button type="submit" class="btn btn-ora btn-large">登 录</button>
				<a class="btn btn-large ml-10" href="<c:url value="/" />">返回</a>&nbsp;&nbsp;&nbsp;
				<a href="<c:url value="/password" />"><font>忘记密码？</font></a>
			</div>
		</div>
	</form:form>
	<br>
	<a href="<c:url value="/signup" />"><font size="3px">还没有在浙里的账号？立即注册！</font></a>
	<hr>
	<div class="connect">
        <font size="4px" class="third-signin">用合作网站账户直接登录：</font>
        <br><br>
        <div class="bd">
	        <a  class="weibo"><i></i>新浪微博</a>
	        <a  class="qq"><i></i>QQ</a>
	        <a  class="renren"><i></i>人人网</a>
	        <a  class="douban"><i></i>豆瓣</a>
	        <div class="clear"></div>
        </div>
    </div>
	</div>
	</div>

<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
<script type="text/javascript">
	$("#signInForm").validationEngine('attach', {promptPosition : "bottomRight", scroll: false});
	
</script>
</body>
</html>