<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<c:if test="${not empty pins}">
<c:forEach var="pin" items="${pins}">
<div class="thumbnail pin wft" data-act-id="${pin.actId}">
	<div class="op-view p-r">
		<c:choose>
		<c:when test="${pin.imageHeight > 800}">
			<a class="img long" href="<c:url value="/activities/${pin.activityId}" />">
			<img alt="${pin.title}" width=192 height=${pin.imageHeight} src="${pin.imageUrl}">
			<span class="stop"></span></a>
		</c:when>
		<c:otherwise>
			<a class="img" href="<c:url value="/activities/${pin.activityId}" />">
			<img alt="${pin.title}" width=192 height=${pin.imageHeight} src="${pin.imageUrl}"></a>
		</c:otherwise>
		</c:choose>
		<div class="op-list p-a dis-n " style="top:8px; right: 2px">
		<ul class="ls-n ta-r">
			<li><a class="btn mb-5 btn-green-1 track" act="<c:url value="/ops/track/${pin.activityId}" />"
				style="margin-bottom:5px;" onclick="op.change_track(event.currentTarget); event.preventDefault();">
				<i class="icon-star icon-white"></i>&nbsp;<span>追踪(${pin.trackCount})</span></a></li>
			<li><a class="btn mb-5 btn-pink" act="<c:url value="/ops/agree/${pin.activityId}" />"
				style="margin-bottom:5px;" onclick="op.give_agreement(event.currentTarget); event.preventDefault(); ">
				<i class="icon-heart icon-white"></i>&nbsp;&nbsp;&nbsp;<span>赞(${pin.agreeCount})&nbsp;</span></a></li>
			<li><a class="btn mb-5 btn-purple" act="<c:url value="/ops/share/${activity.id}" />"
				style="margin-bottom:5px;" onclick="showOrhide();">
				<i class="icon-share-alt icon-white"></i>&nbsp;<span>转发(${pin.shareCount})</span></a></li>
			
			<div class="share-choice" name="share-choice" style="visibility:hidden">
				<div class="bshare-custom" style="">
					<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
					<a title="分享到QQ好友" class="bshare-qqim"></a>
					<a title="分享到人人网" class="bshare-renren"></a>
					<a title="分享到豆瓣" class="bshare-douban"></a>
				</div>
				<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
				<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
			</div>
			
		</ul>
		</div>
	</div>
	<div class="caption">
		<p><a href="#" data-id="${pin.placeId}" data-type="placemap" 
			data-html="true" data-toggle="popoverx" data-original-title="详细地图"
			data-city="${pin.city}" data-lngLat="${pin.lngLat[1]},${pin.lngLat[0]}">
			<i class="icon-map-marker icon-my"></i>${pin.placeAddr}</a></p>
		
		<a href="<c:url value="/activities/${pin.activityId}" />"><h4 style="color:#757575" >${pin.title}</h4></a>
	</div>
	
	<div class="convo clear">
		<p class="clear mb-0">
			<span><font color="#856e7e"><i class="icon-bookmark icon-my"></i>&nbsp;&nbsp;参与人数(${pin.currentNum})&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<span><font color="#856e7e"><i class="icon-comment icon-my"></i>评论(${pin.commentCount})</span> 
		</p>
	</div> 
	<div class="convo clear" style="border-top:1px solid white; border-bottom:1px solid #EDEDED;">
		<p class="clear mb-0">
			<a class="img f-l mr-5 mb-5" href="<c:url value="/profiles/${pin.createdById}" />"
				data-id="${pin.createdById}" data-type="namecard" data-original-title="用户信息"
				data-html="true" data-trigger="manual" data-toggle="popoverx">
				<img src="${pin.createdByAvatarUrl}" width=30 height=30 /></a>
			<a class="" href="<c:url value="/profiles/${pin.createdById}" />" >${pin.createdByName}</a>
			&nbsp;在&nbsp;
			<a href="<c:url value="/?city=&category=${pin.type}&startTime=&endTime=&keyword=" />">${pin.type}</a>&nbsp;中创建了此活动，快来围观吧<br>		
			<span style="float:right">(<span class="timeago" title="<fmt:formatDate 
				value="${pin.createdAt}" pattern="yyyy-MM-dd HH:mm:ss Z"/>"></span>)</span>
		</p>
	</div>

<script type="text/javascript">
function showOrhide()
{
    var nameList = document.getElementsByName("share-choice");
    for(var i=0; i<nameList.length;i++)
    {
    	if(nameList[i].style.visibility =="hidden")
        	nameList[i].style.visibility ="visible";
    	else
        	nameList[i].style.visibility = "hidden";
	}	
}
</script>

</div>
</c:forEach>
</c:if>
