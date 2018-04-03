<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<div class="board" style="height:170px">
	<div class="pl-5 pt-5 pr-5 pb-5 row-fluid" style="height:90px">
		<a class="img f-l mr-10" href="<c:url value="/profiles/${user.id}" />" >
			<img width=60 height=60 src="${f:avatarUrl(user.avatar, user.gender)}" /></a>
		<div class="f-l">
			<h3 class="mb-7">${user.name}</h3>
			<p class="fs-14 lh-25 c-888">
				<a href="<c:url value="/profiles/${user.id}/activity" />">
					<span class="fw-b fs-20">${user.activityCount}</span></a>&nbsp;个活动,&nbsp;&nbsp;
				<a href="<c:url value="/profiles/${user.id}/follow" />">
					<span class="fw-b fs-20">${user.followCount}</span></a>&nbsp;个关注,&nbsp;&nbsp;
				<a href="<c:url value="/profiles/${user.id}/fan" />">
					<span id="fans-idr" class="fw-b fs-20">${user.fansCount}</span></a>&nbsp;个粉丝
			</p>
			<p class="fs-14 lh-20 c-888">
				<c:if test="${not empty user.city}">
					城市: <span class="c-333">${user.city}</span>&nbsp;
				</c:if>
				<c:if test="${user.gender != null}">
					性别: <span class="c-333">${f:gender(user.gender)}</span>
				</c:if>
			</p>
		</div>
	</div>
			<p class="fs-14 lh-20 c-888" style="margin-left:20px">
				<c:choose>
					<c:when test="${not empty user.summary}">
						${user.summary}
					</c:when>
					<c:otherwise>
						 这家伙很懒 什么都没留下
					</c:otherwise>
				</c:choose>
			</p>
		
	
	<div class="pb-5 pt-5 row-fluid bgc-gray" >
		<div class="f-l" style="margin-left:80px">
			<p><a class="btn btn-pink follow" act="<c:url value="/ops/follow/${user.id}" />" 
					onclick="op.change_follow(event.currentTarget); event.preventDefault();" data-idr="#fans-idr">
					<i class="icon-eye-open icon-white"></i>&nbsp;<span>关注Ta</span></a>
				<a class="btn btn-purple" onclick="op.pm('${user.id}','${user.name}'); event.preventDefault();" >
					<i class="icon-envelope icon-white"></i>&nbsp;私信Ta</a>
			</p>
		</div>
	</div>
</div>