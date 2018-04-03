<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>在浙里——分享你我的社交</title>
		<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />	
		<style>	
		   @font-face {
			  font-family: 'Creepster';
			  font-style: normal;
			  font-weight: 400;
			  src: local('Creepster'), local('Creepster-Regular'), url(http://themes.googleusercontent.com/static/fonts/creepster/v2/un014as1qOcrVg1hrkn_UT8E0i7KZn-EPnyo3HZu7kw.woff) format('woff');
			}		
			*{
				margin:0;
				padding:0;
			}
			body{
				
				color:white;
				padding-bottom:20px;
			}
			.error-code{
				font-family: 'Creepster', cursive, arial, helvetica, sans-serif;
				font-size: 200px;
				color: white;
				color: rgba(255, 255, 255, 0.98);
				width: 50%;
				text-align: right;
				margin-top: 15%;
				text-shadow: 5px 5px hsl(0, 0%, 25%);
				float: left;
			}
			.not-found{
				width: 47%;
				float: right;
				margin-top: 10%;
				font-size: 50px;
				color: white;
				text-shadow: 2px 2px 5px hsl(0, 0%, 61%);
				padding-top: 70px;
			}
			.clear{
				float:none;
				clear:both;
			}
			.content{
				text-align:center;
				line-height: 30px;
				font-size: 18px;
			}
			input[type=text]{
				border: hsl(247, 89%, 72%) solid 1px;
				outline: none;
				padding: 5px 3px;
				font-size: 16px;
				border-radius: 8px;
			}
			a{
				text-decoration: none;
				color: #9ECDFF;
				text-shadow: 0px 0px 2px white;
			}
			a:hover{
				color:white;
			}

		</style>
		

		<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
		
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/comp/header.jsp">
			<jsp:param name="tab" value="home"/>
		</jsp:include>
		<p class="error-code">
			ing...
		</p>
		<p class="not-found">Android<br><br><br>IOS</p>
		<div class="clear"></div>
		<div class="content"><br><br><br>
			Programmers are fighting， 敬请期待<br><br>
			<a href="/">回到主页</a>
		</div>
		
	</body>
</html>