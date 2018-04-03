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
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/comp/header.jsp">
			<jsp:param name="tab" value="none"/>
		</jsp:include>
		<div id="content-wrapper">
			<div class="site">
			<div class="markdown-format"> 
				<h3>在浙里</h3>
				<p class="intro">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“在浙里”社交化活动应用主要为人们提供了一个活动发布、参与、分享的平台，其分类包括聚餐，运动，班级活动，线上讨论等人们常做的活动。用户所需要的就是发布活动的名称，时间，地点.，其他用户科方便地寻找“志同道合”的人，共同参与一项活动，丰富生活;当活动结束后，还可上传活动图片，与大家一起分享。<br><br>
				(为享受最佳体验，推荐使用最新版本的 FireFox or Chrome 浏览器）
					</p>
				<p>
					<h4>“在浙里”主要实现功能：</h4>
					<ul>
						<li>	最热活动</li>
						<li>	活动地图布局</li>
						<li>	发布活动</li>
						<li>	查找用户，可进行关注、私信</li>
						<li>	申请加入活动(根据活动状况判断是否填写申请表)</li>
						<li>	跟帖讨论回复</li>
						<li>	可进行 @ </li>
						<li>	重新编辑活动</li>
						<li>	查看已参与用户</li>
						<li>	上传图片或查看活动图库</li>
						<li>	跟踪、转发、赞活动</li>
						<li>	我的关注——查看追踪的活动及关注的用户的动态</li>
						<li>	在这里的我——我的动态</li>
						<li>	消息功能</li>
						<li>	用户设置，可设置头像、昵称、固定申请表等</li>
					</ul>
				</p>
				<h3>联系及反馈邮箱</h3>
					<h4> <a target="_blank" href="mailto: blue.snow13@163.com">blue.snow13@163.com</a>  </h4>
					<br>
				<h3>有空多来踩踩吧~ 即使来喂喂鱼也挺好的</h3>
				<div style="margin-bottom:70px">		
					<center><embed height=300 type=application/x-shockwave-flash width=660 src=http://fishgadget.googlecode.com/svn/trunk/fish.swf FLASHVARS="up_numFish=6&amp;up_fishColor4=#FFFFFF&amp;up_backgroundColor=F6F6F6&amp;up_fishColor1=F4A61C&amp;up_fishColor7=F45540&amp;up_fishColor6=F45540&amp;up_fishColor8=F45540&amp;up_fishColor2=C4C4C4&amp;up_fishColor9=F45540&amp;up_fishColor3=#600000&amp;up_fishName=Fish&amp;up_fishColor5=F45540&amp;up_fishColor10=F45540&amp;up_backgroundImage=http://&amp;up_foodColor=FCB347&amp;" BGCOLOR="#F6F6F6" WMODE="zaizheli" ></embed></center>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>