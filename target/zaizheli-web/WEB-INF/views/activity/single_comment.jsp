<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${not empty comments}">
<c:forEach var="comment" items="${comments}">
<div id="${comment.id}">
<c:if test="${comment.floor != 1}" >
 	<div style="border-bottom:2px dashed #F9DBB4; margin:3em 6em 3em; width:68em; color:#325D72"></div>
 </c:if>
<li class="num"> 
	<div class="number" > 
          <span>${comment.floor}</span> 
    </div> 
	<a class="img f-l" href="<c:url value="/profiles/${comment.createdBy.id}" />">
	<img width=60 height=60
		src="${f:avatarUrl(comment.createdBy.avatar, comment.createdBy.gender)}" /></a>
	<p class="fs-20"><a href="<c:url value="/profiles/${comment.createdBy.id}" />">${comment.createdBy.name}</a></p>
	<p class="fs-14 mb-0">
		<span style="color:#757575"> <fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	</p>
	<br>
	<div  style="background-color:#343131 float:right;"></div> 
	<p style="padding-left:100px;">${comment.content}</p> 
</li> 
<p class="f-r"> 
	<a href="#" act="<c:url value="/comment/agree/${comment.id}" />" onclick="op.give_cagreement(event.currentTarget); event.preventDefault(); " class="cmt_tool btn-pink btn-mini" ><i class="icon-heart icon-white"></i>&nbsp;&nbsp;<span>赞(${comment.agreeCount})&nbsp;</span></a>
	<a  href="#comment" user-id="${comment.createdBy.id}" class="reply cmt_tool btn-purple btn-mini" style="padding-left:5px;" onclick="op.replyto('${comment.createdBy.id}','${comment.createdBy.name}','${comment.id}');"><i class="icon-comment icon-white"></i>&nbsp;<span>&nbsp;回复&nbsp;</span></a> 
</p>
</div>
</c:forEach>
</c:if>
</ol>
<div id="cmt_page"></div>

<script type="text/javascript">
	var options = {
		currentPage: '${pageVo.number}',
		totalPages: '${pageVo.totalPages}',
		numberOfPages: 5,
		alignment: 'center',
		pageUrl: function(type, page, current){
                return '<c:url value="/activities/${activity.id}/" />' + page + '#why-work';
            }		
		}
	$('#cmt_page').bootstrapPaginator(options);	
</script>

