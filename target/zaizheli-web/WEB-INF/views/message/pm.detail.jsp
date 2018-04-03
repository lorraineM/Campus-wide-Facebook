<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>私信 ${user.name}的在浙里 在浙里——分享你我的社交</title>
	<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-paginator.js" />" ></script>
</head>
<body class="front">
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="none"/>
	</jsp:include>
	<c:import url="../modal/sendPM.jsp"/>
	<div class="main-wrapper mt-20 mb-30">
		<div class="main block-h-c content-wrapper row-fluid p-r">			
			<div class="span8 board">
				<div class="pl-30 pr-30 pt-20">
				    <ul id="profile-nav-tabs" class="nav nav-tabs fs-14" 
				    	style="margin-left: -30px; margin-right: -30px;  margin-bottom:0px">
			    		<li class="cmt-me active" style="margin-left:30px;">
			    			<a data-toggle="tab" href="#cmt-me-act-list">标题：   ${message.title}</a></li>
				    </ul>
			    </div>
			    <div id="act-list-wrapper"  class="tab-content bg-white p-20" >
			    	<c:if test="${user.id eq message.to.id}">
				    	<a class="btn-orange" onclick="op.pmTo('${message.from.id}','${message.from.name}','${message.title}','${message.id}'); event.preventDefault();" >回复Ta</a>
					</c:if>
					<dl class="row-fluid">
						<p class="msgtime"><fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<dt class="face">
							<a class="img f-l  of-h" title = "${message.from.name}" href="<c:url value="/profiles/${message.from.id}"/>" data-id="${message.from.id}" data-type="namecard" data-original-title="用户信息" data-html="true" data-trigger="manual" data-toggle="popoverx">
								<img width=60 src="${f:avatarUrl(message.from.avatar, message.from.gender)}" />
							</a>
						</dt>
						<dd class="msgcontent" style="margin-left: 100px;margin-top: 6px;">	
							${message.content}
						</dd>
					</dl>
					<c:if test="${not empty message.base}">
						<dl class="row-fluid" style="margin-top:60px;">
							<p class="msgtime"><fmt:formatDate value="${message.base.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							<dt class="face">
								<a class="img f-r  of-h" title = "${message.base.from.name}" href="<c:url value="/profiles/${message.base.from.id}"/>" data-id="${message.base.from.id}" data-type="namecard" data-original-title="用户信息" data-html="true" data-trigger="manual" data-toggle="popoverx">
									<img width=60 src="${f:avatarUrl(message.base.from.avatar, message.base.from.gender)}" />
								</a>
							</dt>
							<dd class="msgcontent" style="margin-left: 200px;margin-top: 6px;">	
								${message.base.content}
							</dd>
						</dl>
					</c:if>
				</div>    
			</div>
			<div class="span4">
				<jsp:include page="/WEB-INF/views/message/info.jsp" />					
				<jsp:include page="/WEB-INF/views/message/nav.jsp">
					<jsp:param name="tab" value="pm"/>
				</jsp:include>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		adjustWebWidth();
	</script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.timeago.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.18.custom.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.scrollTo.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.imagesloaded.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.masonry.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.infinitescroll.js" />" ></script>
<script type="text/javascript">
$(function(){
	$('#act-list-wrapper').each(function(){
		op.pin_bind_event($(this));
	});
});
</script>
<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
</body>
</html>