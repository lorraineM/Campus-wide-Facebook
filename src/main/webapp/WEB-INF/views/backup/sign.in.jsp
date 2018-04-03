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

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/languages/zh-cn/jquery.validationEngine.lang.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js" />" ></script>
</head>
<body>
    <div class="container">
		<div class="row mt-30" >
			<div class="span8 offset2 mt-20">
				<div class="hero-unit board-2">
					<form:form id="signInForm"  cssClass="sign-in" modelAttribute="signInCredentialVo" method="post">
					<img src="<c:url value="/resources/img/zaizheli-icon.png" />" /><hr>					
						<div class="row-fluid">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i></span>
									<input id="user-name" class="validate[required,custom[email]] input-xlarge" name="signInName" type="email" placeholder="在浙里注册邮箱" autocomplete="off" value="<c:out value="${signInCredentialVo.signInName}" />"/>
									<div class="f-l hint"><form:errors path="signInName" cssClass="alert alert-error" /></div>
								</div>
							</div>
						</div>
						</div>
						<div class="row-fluid mt-10">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span>
									<input id="user-pwd" class="validate[required] input-xlarge" name="signInPassword" type="password" maxlength="64" placeholder="密码" autocomplete="off" value="<c:out value="${signInCredentialVo.signInPassword}" />" />
									<div class="f-l hint"><form:errors path="signInPassword" cssClass="alert alert-error" />
								</div>
							</div>
						</div>
						</div>
						<div class="row-fluid" style="margin-top:50px;">
							 <div class="span6">
							 	<button type="submit" class="btn btn-primary btn-large">登 录</button>
							 	<a class="btn btn-large ml-10" href="<c:url value="/" />">返回</a>&nbsp;&nbsp;&nbsp;
								<a class="span6 ta-r"><a href="<c:url value="/password" />">忘记密码？</a></a>
							</div>
						</div>
					</form:form>
					<p><a href="<c:url value="/signup" />">还没有在浙里的账号？立即注册！</a></p>
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