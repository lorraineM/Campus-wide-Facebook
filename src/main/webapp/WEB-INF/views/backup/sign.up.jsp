<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html id="login_html">
<head>
	<meta charset=utf-8>
    <title>注册 在浙里 分享你我的社交</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/languages/zh-cn/jquery.validationEngine.lang.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
</head>
<body>
    <div class="container">
		<div class="row mt-30" >
			<div class="span8 offset2 mt-20">
				<div class="hero-unit board-1">
					<form:form id="signUpForm" cssClass="sign-in" modelAttribute="signUpUserVo" method="post">
					<img src="<c:url value="/resources/img/zaizheli-icon.png" />" /><hr>					
						<div class="row-fluid ">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i></span>
									<input id="email" class="validate[required,custom[email]] input-xlarge" name="email" type="text" placeholder="输入在浙里注册邮箱" autocomplete="off" value="<c:out value="${signUpUserVo.email}" />" />
									<div class="f-l hint"><form:errors path="email" cssClass="alert alert-error" /></div>
								</div>
							</div>
						</div>
						</div>
						<div class="row-fluid mt-10">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span>
									<input id="name" class="validate[required,minSize[2]] input-xlarge" name="name" type="text" maxlength="64" placeholder="给自己取个昵称" autocomplete="off" value="<c:out value="${signUpUserVo.name}" />" />
									<div class="f-l hint"><form:errors path="name" cssClass="alert alert-error"/></div>
								</div>
							</div>
						</div>							
						</div>
						<div class="row-fluid mt-10">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span>
									<input id="password" class="validate[required,minSize[6],maxSize[30]] input-xlarge" name="password" type="password" maxlength="64" placeholder="输入密码" autocomplete="off" value="<c:out value="${signUpUserVo.password}" />" />
									<div class="f-l hint"><form:errors path="password" cssClass="alert alert-error"/></div>
								</div>
							</div>
						</div>
						</div>
						<div class="row-fluid mt-10">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span>
									<input id="password-re" class="validate[required,equals[password]] input-xlarge" name="passwordRe" type="password" placeholder="再次输入密码" autocomplete="off" value="<c:out value="${signUpUserVo.passwordRe}" />" />
								</div>
							</div>
						</div>							
						</div>
						<div class="row-fluid mt-10">
							<div class="span8">
							 	<button type="submit" class="btn btn-primary btn-large">注册</button>
							 	<a class="btn btn-large ml-10" href="<c:url value="/" />">返回</a>
								<p><font size="2"><br>
								一旦点击"注册"意味着您同意"在浙里"的 <a href="<c:url value="/resources/login/terms.html" />">服务条款</a>。<br> 
								另请参阅我们的 <a href="<c:url value="/resources/login/privacy.html" />">隐私政策。</a></font></p>
							 </div>
							<a class="span4 ta-r"><a href="<c:url value="/signin" />">我有账号了，立即去登录！</a></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
    <script type="text/javascript">
	$("#signUpForm").validationEngine('attach', {promptPosition : "bottomRight", scroll: false});
	</script>
</body>
</html>