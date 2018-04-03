<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>在浙里——分享你我的社交</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.default.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.8.18.custom.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.zaizheli.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	</head>
	<body class="front">
		<jsp:include page="/WEB-INF/views/comp/header.jsp">
			<jsp:param name="tab" value="home"/>
		</jsp:include>
		<jsp:include page="/WEB-INF/views/comp/back.top.jsp" />
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrapx-popoverx.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.pnotify.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.imagesloaded.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.masonry.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.infinitescroll.js" />" ></script>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=zh_cn&region=CN"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/gmap3.js" />"></script>
		
	</body>
</html>