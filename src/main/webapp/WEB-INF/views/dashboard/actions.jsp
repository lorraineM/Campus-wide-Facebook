<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${actions != null}">
	<c:forEach var="action" varStatus="stat" items="${actions}">
		<dl class="act-list row-fluid">
			<dt class="face">
				<a class="img f-l w-60 h-60 of-h" title = "${action.owner.name}" href="<c:url value="/profiles/${action.owner.id}"/>" data-id="${action.owner.id}" data-type="namecard" data-original-title="用户信息" data-html="true" data-trigger="manual" data-toggle="popoverx">
					<img width=60 src="${f:avatarUrl(action.owner.avatar, action.owner.gender)}" />
				</a>
			</dt>
			<dd class="content">
				<c:choose>
				<c:when test="${action.type == 'ACTIVITY'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						建立了新活动:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
					</p></a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
							<!-- <span class="f-r">
								<a href="<c:url value="/activities/${action.targetActivity.id}"/>">追踪(${action.targetActivity.trackCount})</a>
								<i class="mr-5 ml-5 c-aaa">|</i>
							   	<a href="<c:url value="/activities/${action.targetActivity.id}"/>" href="">转发(${action.targetActivity.shareCount})</a>
							   	<i class="mr-5 ml-5 c-aaa">|</i>
							   	<a href="<c:url value="/activities/${action.targetActivity.id}"/>">评论(${action.targetActivity.commentCount})</a>
							</span>	 -->
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'FORWARD'}">
					<p class="mb-15">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						${action.content}</p>
					<c:if test="${not empty action.targetActivity}">
					<dl class="target">
						<dd class="arrow bgcolor_arrow">
							<em>◆</em><span>◆</span></dd>
						<dt>
							<a href="/profiles/${action.targetActivity.createdBy.id}">@${action.targetActivity.createdBy.name}</a>&nbsp;
							<a href="/activities/${action.targetActivity.id}">${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a><br/>
							
						</dt>
						<dd class="ml-0"><div class="">
							<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
						</div></dd>
						<dd class="ml-0 mt-8 row-fluid">
							<span class="f-r">
								<fmt:formatDate value="${action.targetActivity.createdAt}" 
									pattern="MM月dd日 HH:mm:ss"/></span>
							
						</dd>
					</dl>
					</c:if>
					<!-- <p class="mb-0 row-fluid">
						<span class="timeago f-l" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						<span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span>
					</p> -->
				</c:when>
				<c:when test="${action.type == 'COMMENT'}"> 
                    <c:choose>
                    <c:when test="${not empty action.targetUser}">
						<p><a href="<c:url value="/profiles/${action.owner.id}" />">
								${action.owner.name}</a>在&nbsp;
								<c:if test="${not empty action.targetActivity}">
								<a href="<c:url value="/activities/${action.targetActivity.id}" /> ">${action.targetActivity.title} @ 
										${action.targetActivity.place.fullAddr}</a><br/>
								</c:if>	
							   <p>${action.content}</p>						
					</c:when>
					<c:otherwise>
					<p><a href="<c:url value="/profiles/${action.owner.id}" />">
								${action.owner.name}</a>
							对&nbsp;</p>
							<p>
							<c:if test="${not empty action.basedOn}">
							<a href="<c:url value="/profiles/${action.basedOn.owner.id}" /> ">@${action.basedOn.owner.name} </a>回复了:&nbsp;
							<span>${action.basedOn.content}</span>
							</c:if>
							<c:if test="${not empty action.targetActivity}">
								<a href="<c:url value="/activities/${action.targetActivity.id}" /> ">${action.targetActivity.title} @ 
										${action.targetActivity.place.fullAddr}</a><br/>
								
							</c:if>评论了:&nbsp;
							</p>
							<p>${action.content}</p>	
					</c:otherwise>
					</c:choose>	
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						<!-- <c:if test="${sessionScope.signInUser.id != action.owner.id}">
						<span class="f-r">
							<a onclick="op.toggle_rep_dashboard(event.currentTarget); event.preventDefault();">
								回复</a>
						</span>
						</c:if> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'JOIN'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						参加了活动:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
					</p></a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						
						</dd>	
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'TRACK'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						追踪了活动:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
					</p></a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						
							
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'SETTING'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						更新了活动:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
					</p>,</a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'FOLLOW'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						关注了:&nbsp;
						<a href="<c:url value="/profiles/${action.targetUser.id}" />">
							${action.targetUser.name}</a></p>
					<a href="<c:url value="/profiles/${action.targetUser.id}" />">
					<p class="">
						<img class="" width="100" src="${f:avatarUrl(action.targetUser.avatar, action.targetUser.gender)}">
					</p></a>
						
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'UPLOAD'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						上传了图片到:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.picId)}">
					</p></a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
						
			
						
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				<c:when test="${action.type == 'APPLY'}">
					<p class="">
						<a href="<c:url value="/profiles/${action.owner.id}" />">
							${action.owner.name}</a>
						申请加入:&nbsp;
						<a href="<c:url value="/activities/${action.targetActivity.id}" />">
							${action.targetActivity.title}&nbsp;@&nbsp;${action.targetActivity.place.fullAddr}</a></p>
					<a href="<c:url value="/activities/${action.targetActivity.id}" />">
					<p class="">
						<img class="" width="100" src="${f:imageUrl(action.targetActivity.image.resId)}">
					</p></a>
					<p class="mb-0 row-fluid">
						<span class="timeago f-r" 
							title="<fmt:formatDate value="${action.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>
							
						
						<!-- <span class="f-r">
							<a onclick="op.toggle_fwd_dashboard(event.currentTarget); event.preventDefault();">
								转发(${action.forwardedCount})</a>
						   	<i class="mr-5 ml-5 c-aaa">|</i>
						   	<a onclick="op.toggle_cmt_dashboard(event.currentTarget); event.preventDefault();">
						   		评论(${action.commentedCount})</a>
						</span> -->
					</p>
				</c:when>
				</c:choose>
				

			</dd>
		</dl>
	</c:forEach>
</c:if>