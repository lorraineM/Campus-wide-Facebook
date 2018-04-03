<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${messages != null}">
	<c:forEach var="message" varStatus="stat" items="${messages}">
		<dl class="mes-list row-fluid">
			<dt class="face">
				<a class="img f-l  of-h" title = "${message.to.name}" href="<c:url value="/profiles/${message.to.id}"/>" data-id="${message.to.id}" data-type="namecard" data-original-title="用户信息" data-html="true" data-trigger="manual" data-toggle="popoverx">
					<img width=35 src="${f:avatarUrl(message.to.avatar, message.to.gender)}" />
				</a>
			</dt>
			<dd class="content" style="margin-left: 65px;margin-top: 6px;">	
				<p class="">
					<span style="margin-right:80px;">发送给 <a href="<c:url value="/profiles/${message.to.id}"/>">${message.to.name}:</a></span>
					<a style="color:#000000;" href="<c:url value="/message/pm/${message.id}/detail"/>">${message.title}</a>				
				</p>
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
                return '<c:url value="/message/pm/from/" />' + page ;
            }		
		}
	$('#mes_page').bootstrapPaginator(options);	
</script>
