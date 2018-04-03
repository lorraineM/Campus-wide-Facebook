<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<ul id="side-nav" class="dis-n">
	<li class="about"><a class ="btn-ora" href="<c:url value="/activities/${activity.id}" />" title="活动主页" >活动主页 &nbsp;&nbsp;&nbsp;<i class="icon-home icon-white"></i></a></li>
	<c:choose>
	<c:when test="${signInUser.id == activity.createdBy.id}">
		<li class="code"><a class="btn-blue-2" href="<c:url value="/activity/${activity.id}/edit"/>" title="活动编辑">活动编辑&nbsp;&nbsp;&nbsp;<i class="icon-edit icon-white"></i></a></li>
		<li class="code"><a class="btn-green-2" href="<c:url value="/activity/${activity.id}/applications"/>" title="申请处理">申请处理&nbsp;&nbsp;&nbsp;<i class="icon-file icon-white"></i></a></li>
	</c:when>
	<c:otherwise>
		 <c:if test="${activity.status eq '征集成员中'}"> 	
			<c:if test="${status eq 0}">
				<li class="code"><a id="toJoin" class="btn-primary" act="<c:url value="/activity/${activity.id}/join"/>" onclick="op.apply(event.currentTarget); event.preventDefault();" title="加入活动">加入活动&nbsp;&nbsp;&nbsp;<i class="icon-star-empty icon-white"></i></a></li>  
			</c:if>
		</c:if>  
		<li class="code"><a class="btn-success" href="<c:url value="/activity/${activity.id}/joiners"/>" title="参加人员">参加人员&nbsp;&nbsp;&nbsp;<i class="icon-user icon-white"></i></a></li>                                     
	</c:otherwise>
	</c:choose>
	<li class="vote"><a class="btn-red" href="<c:url value="/activity/${activity.id}/gallery"/>"  title="活动图库">活动图库&nbsp;&nbsp;&nbsp;<i class=" icon-picture icon-white"></i></a></li>
</ul>
<script type="text/javascript">
	$(function() {
		/*$('#side-nav a').stop().animate({
			'marginLeft' : '-85px'
		}, 1000);*/
		$('#toJoin').each(function(){
			op.pin_bind_event($(this));
		});	
		$('#side-nav').css('top', Math.max(100, ($(window).height()-$('#side-nav').height())/2));
		$(window).resize(function(){
			$('#side-nav').css('top', Math.max(100, ($(window).height()-$('#side-nav').height())/2));
		});
		$('#side-nav a').css('marginLeft', '-85px');
		$('#side-nav').show();
		$('#side-nav > li').hover(function() {
			$('a', $(this)).stop().animate({
				'marginLeft' : '-2px'
			}, 200);
		}, function() {
			$('a', $(this)).stop().animate({
				'marginLeft' : '-85px'
			}, 200);
		});
	});
</script>