<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${messages != null}">
	<c:forEach var="message" varStatus="stat" items="${messages}">
		<dl class="mes-list row-fluid">
			<dt class="face">
				<c:if test="${not empty message.activity}">
				<a class="img f-l  of-h" title = "${message.activity.title}" href="<c:url value="/activities/${message.activity.id}"/>" data-id="${message.activity.id}" data-type="namecard" data-original-title="活动信息" data-html="true" data-trigger="manual" data-toggle="popoverx">
					<img width=35 src="${f:imageUrl(message.activity.image.resId)}" />
				</a>
				</c:if>
			</dt>
			<dd class="content" style="margin-left: 65px;margin-top: 6px;">	
				<c:choose>
				<c:when test="${message.status eq 0}">	
				<p class="">
					<b>${message.content}</b>	
					<a class="f-r btn-or btn-mini" onclick="op.setRead('${message.id}',event.currentTarget);event.preventDefault();">设为已读</a>
				</p>
				</c:when>
				<c:otherwise>
				<p class="">
					${message.content}		
				</p>
				</c:otherwise>
				</c:choose>
					<p><span class="f-r"><fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
				
			</dd>
		</dl>
	</c:forEach>
</c:if>
<div id="mes_page"></div>
<script type="text/javascript">
	var options = {
		currentPage: '${pageVo.number}',
		totalPages: '${pageVo.totalPages}',
		numberOfPages: 5,
		alignment: 'center',
		pageUrl: function(type, page, current){
                return '<c:url value="/message/inform/" />' + page ;
            }		
		}
	$('#mes_page').bootstrapPaginator(options);	
</script>
