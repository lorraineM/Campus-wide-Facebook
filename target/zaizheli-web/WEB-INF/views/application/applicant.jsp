<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${not empty applications}">
<c:forEach var="application" items="${applications}">
<div class="thumbnail pin wft" style="-moz-transition: left 0.5s ease-in-out 0s, top 0.5s ease-in-out 0s; margin:6px;">
	<div class="op-view p-r">
		<a class="img" <c:if test="${empty application.applicant.avatar}"> title="${f:thirdPerson(application.applicant.gender)}还没有设置头像"</c:if> 
			href="<c:url value="/profiles/${application.applicant.id}" />">
			<img alt="${application.applicant.name}" width=192 height=192 src="${f:avatarUrl(application.applicant.avatar, application.applicant.gender)}"></a>
		<div class="op-list p-a dis-n " style="top:8px; right: 2px"><ul class="ls-n ta-r">
			<li><a class="btn mb-5 btn-success follow" style="margin-bottom:5px;" act="<c:url value="/ops/follow/${application.applicant.id}" />"
				onclick="op.change_follow(event.currentTarget); event.preventDefault();">
				<i class="icon-heart icon-white" ></i>&nbsp;<span>关注Ta</span></a></li>
			<li><a class="btn" style="margin-bottom:5px;" onclick="op.pm('${application.applicant.id}','${application.applicant.name}'); event.preventDefault();" >
				<i class="icon-envelope"></i>&nbsp;私信Ta</a></li>
		</ul></div>
	</div>
	<div class="caption">
		<h3><a href="<c:url value="/profiles/${application.applicant.id}"/>">${application.applicant.name}</a></h3>	
	</div>
	<div class="convo row-fluid">
		<c:if test="${application.activity.apply == 1}">
			<h4><a href="<c:url value="/activity/${application.activity.id}/application/${application.id}/view" />" style="text-align:center">查看该成员的申请表</a></h4>
		</c:if>
		<c:if test="${application.status eq '申请中'}">
		<div style="float:right; margin-top:8px;">			    
			<a  class=" btn btn-danger b" act="<c:url value="/application/${application.id}/agree" />" onclick="op.apply_agree(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>">
					同意</a>
			<a  class="btn btn-info ml-10" act="<c:url value="/application/${application.id}/refuse" />" onclick="op.apply_refuse(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>">拒绝</a>
		</div>	
		</c:if>
		<c:if test="${application.status eq '已加入'}">
		<div style="float:right; margin-top:8px;">			    
			<a  class="btn btn-inverse ml-10" act="<c:url value="/application/${application.id}/kickout" />" onclick="op.apply_kickout(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>">踢出此活动</a>
		</div>	
		</c:if>
	</div>
</div>
</c:forEach>
</c:if>