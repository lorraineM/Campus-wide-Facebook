<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${actions != null}">
<div class="p-15 board mb-20">
	<c:forEach var="action" varStatus="stat" items="${actions}">
		<div class="row-fluid
			<c:if test="${!stat.last}">
				bb-divider mb-10
			</c:if> 
			">
			<c:choose>
				<c:when test="${action.targetActivity!=null}">
				<a class="img f-l w-60 h-60 of-h mr-10 mb-10"
					title="${action.targetActivity.title}" 
					href="<c:url value="/activities/${action.targetActivity.id}"/>" >
					<img width=60 src="${f:imageUrl(action.targetActivity.image.resId)}" />
				</a>
				</c:when>
				<c:otherwise>
				<a class="img f-l w-60 h-60 of-h mr-10 mb-10"
					title = "${action.targetUser.name}" 
					href="<c:url value="/profiles/${action.targetUser.id}"/>" >
					<img width=60 src="${f:avatarUrl(action.targetUser.avatar, action.targetUser.gender)}" />
				</a>
				</c:otherwise>
			</c:choose>
			${f:action(action)}&nbsp;
			<span class="c-999">(<span class="timeago" title="<fmt:formatDate 
				value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>)</span>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">
	$(function(){
		$('.timeago').timeago();
	});
</script>
</c:if>