<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>在浙里 在浙里——分享你我的社交</title>
	<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.default.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.zaizheli.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/activity1.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/activity2.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.atwho.css" />"/>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-paginator.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.uploadify.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js//jquery.atwho.js"  />" ></script>

    <script type="text/javascript">
	    $(function() {
	        $('#image_upload').uploadify({
	            height        : 30,
	            swf   	      : '<c:url value="/resources/js/uploadify.swf" />',
		   		uploader   	  : '<c:url value="/gallery/upload" />',
		        width         : 200,
		        buttonClass   : 'track apply noauth record-exit img_select',
		        buttonText    : '上传活动图片',
		        uploadLimit   : 50,
		        fileTypeDesc  : 'Image Files',
			    fileTypeExts  : '*.gif; *.jpg; *.png; *.jped; *.bmp',
			    fileSizeLimit : '10000KB',
			    progressData  : 'percentage',
			    formData      : {'actId' : '${activity.id}', 'userId': '${signInUser.id}'},
			    onUploadStart : function(file) {
            		$("#image__upload").uploadify("settings", "actId", '${activity.id}');
            		$("#image__upload").uploadify("settings", "userId", '${signInUser.id}');
        		} ,
			    onUploadError : function(file, errorCode, errorMsg, errorString) {
			    	op.notify_header('啊啊，上传失败了 >o<');
        		},
        		onUploadSuccess : function(file) {
        			op.notify_header('上传成功！ o@o');
           	    }  
			    
		    }); 

	    });
	    </script> 
</head>
<body class="front" style="padding-top:46px;">
	
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="none"/>
	</jsp:include>
	<jsp:include page="/WEB-INF/views/comp/side.nav.jsp" />
	<c:import url="../modal/sendPM.jsp"/>
    <div id="main-content"> 
	   <div class="inside-main-content cf">
		    <div class="page"> 
		    <header class="team-header cf"> 
		      	<div class="team-logo">
			       <a  href="<c:url value="/profiles/${activity.createdBy.id}" />">
					  <img class="team-page-avatar" width=104 height=104 src="${f:avatarUrl(activity.createdBy.avatar, activity.createdBy.gender)}" />
				   </a>
			    </div>    
			    <h1 style="color:#ffca00">${activity.createdBy.name}</h1><br><br><br><br><br><br><br>
			    <div calss="span6">
			    	<div class="span3-5">
						<a class="btn btn-green-1 mr-5 track" act="<c:url value="/ops/track/${activity.id}" />" onclick="op.change_track(event.currentTarget); event.preventDefault();"><i class="icon-star icon-white"></i>&nbsp;<span>追踪(${activity.trackCount})</span></a></li>

						<a class="btn btn-pink" act="<c:url value="/ops/agree/${activity.id}" />" onclick="op.give_agreement(event.currentTarget); event.preventDefault(); "><i class="icon-heart icon-white"></i>&nbsp;&nbsp;<span>赞(${activity.agreeCount})&nbsp;</span></a></li>&nbsp;&nbsp;
						</a>

						<a class="btn btn-purple" act="<c:url value="/ops/share/${activity.id}" />" onclick="toggle('div1')"><i class="icon-share-alt icon-white"></i>&nbsp;<span>转发(${activity.shareCount})</span></a></li>
					</div>

					<!--一键分享——START-->
					<script language="JavaScript" type="text/JavaScript">
					<!--
						function toggle(targetid){
						     if (document.getElementById){
						         target=document.getElementById(targetid);
						             if (target.style.display=="block"){
						                 target.style.display="none";
						             } else {
						                 target.style.display="block";
						             }
						     }
						}
					-->
					</script>
					<div id="div1" style="display:none;margin-top:5px" class="span2">
						<div class="bshare-custom"><div class="bsPromo bsPromo2"></div><a title="分享到新浪微博" class="bshare-sinaminiblog" style="padding-left:20px"></a><a title="分享到QQ好友" class="bshare-qqim" href="javascript:void(0);"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到豆瓣" class="bshare-douban" href="javascript:void(0);"></a></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
					</div>
					<!--一键分享——END-->					
				</div>
		    </header> 
		    <div class="join-us-banner">
		      	<a class="view-jobs-status">${activity.status}</a> 
		      	<a class="view-jobs" style="margin-left:200px" href="<c:url value="/activity/${activity.id}/joiners"/>" ><font color="#a76745">查看参与人员</font></a>
		      	<a class="view-jobs" href="#comment"><font color="#a76745">去评论吧</font></a>
	     	</div>  
		    <section class="cf" id="challenges">
		    	<div>
		    		<header class="job-panel-header cf"> 
				       	<p size="8px" style="text-align: center;font-size:36px;font-weight:bold;">${activity.title}</p> 
				    </header>
		    	</div>
		       	<div class="member-details" style="margin-left:50px"> 
			       <h3>活动 基本信息</h3> 
			       <p class=""> </p> 
		       	</div> 
		       	<div class="caption" style="margin-left:50px">
					<p><a href="#" data-id="${activity.place.id}" data-type="placemap" 
						data-html="true" data-toggle="popoverx" data-original-title="详细地图"
						data-city="${activity.city}" data-lngLat="${activity.place.lngLat[1]},${activity.place.lngLat[0]}">
						<i class="icon-map-marker"></i>${activity.place.fullAddr}</a></p>
					<c:if test="${not empty activity.addr}">			
						<h4>地址备注：${activity.addr}</h4>
					</c:if>
				</div>
				<br>
		       	<div class="box half" style="margin-left:50px"> 
		       		<div class="icon" style="background-color:#a0d4ae"></div> 
			       	<header> 
			          	<h3>活 动 细 节</h3> 
			       	</header>
		       		<p>类别：${activity.type}</p> 
		       		<p>开始时间：<fmt:formatDate value="${activity.startedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		       		<p>结束时间：<fmt:formatDate value="${activity.endedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		       		<p>参与的最大人数：${activity.maxNum}</p> 
		       		<p>当前人数：${activity.currentNum}</p> 
		      	</div> 
		      	<div class="box half" style="width:230px"> 
		      		<div class="icon blub" style="background-color:#a0d4ae"></div> 
			       	<header> 
			        <h3>活 动 情 况</h3> 
			       	</header> 
				       	<p>是否需要费用：<c:choose> <c:when test="${activity.fee eq 0}">否 </c:when>
				                                   	<c:when test="${activity.fee eq 1}">是 </c:when>	
				                        </c:choose>
				       	<p>被赞：${activity.agreeCount}</p> 
				       	<p>转发：${activity.shareCount}</p>
				      	<p>评论：${activity.commentCount}</p> 
				       	<p>追踪：${activity.trackCount}</p> 
		      	</div> 
		     </section> 
		<div>
			<section class="jobs-top" id="jobs"> 
			     <article class="job-panel"> 
			     	  
				      <div class="content cf"> 
				       <c:choose>
						<c:when test="${activity.image.orgSize[1]>=720}">
							<a class="img" href="#">
								<img class="block-h-c" width="720" height="${(720*activity.image.orgSize[0])/activity.image.orgSize[1]}" 
									src="${f:imageUrl(activity.image.resId)}" alt="${activity.title}"/>
							</a>
						</c:when>
						<c:otherwise>
							 <a class="img" href="#" style="text-align:center;display:block">
								<img class="block-h-c" width="${activity.image.orgSize[1]}" height="${activity.image.orgSize[0]}" 
									src="${f:imageUrl(activity.image.resId)}" alt="${activity.title}"/>
							</a>
						</c:otherwise>
						</c:choose> 
				      </div> 
				      <footer class="job-panel-footer cf">
				      <c:choose>
				      	<c:when test="${activity.status eq '放弃'}">
                        	<a  class="track apply noauth record-exit" data-action="view job application" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity">此 活 动 已 被 放 弃 </a> 
                        </c:when>
                        <c:when test="${signInUser.id == activity.createdBy.id}">
					       <input type="file" id="image_upload" name="image_upload">    
					     </c:when>
				      	<c:when test="${activity.status eq '已结束'}">
                        	<a  data-action="view job application" class="track apply noauth record-exit" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity">已 结 束 </a> 
                        </c:when>
                        <c:when test="${activity.status eq '晒活动'}">
                        	<a href="<c:url value="/activity/${activity.id}/gallery"/>" class="track apply noauth record-exit" data-action="view job application" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity">晒 活 动 中 </a> 
                        </c:when>                        
                        <c:when test="${activity.status eq '征集成员中'}"> 
                        	<c:choose>
                        	<c:when test="${status eq 0}">                                         
	                        	<a act="<c:url value="/activity/${activity.id}/join"/>" onclick="op.apply(event.currentTarget); event.preventDefault();"  class="track apply noauth record-exit" data-action="view job application" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity"><span>加 入 活 动</span> </a> 					   
			                   <c:if test="${activity.apply==1}">
						       		<a class="other-jobs">加入该活动需要填写申请表</a> 
						       </c:if>
						    </c:when>
						    <c:when test="${status eq 1}"> 
						    	<a  class="track apply noauth record-exit" data-action="view job application" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity"><span>等待审核中</span> </a> 
						    </c:when>
						    <c:otherwise>					   
			                 	<a  id="quit_act" class="track apply noauth record-exit" data-action="view job application" data-from="job on team page" data-opportunity-visit-path="/teams/4f57ea5e8617b7000d000002/opportunities/176/visit" data-target-type="job-opportunity"><span>退 出 活 动</span> </a>   
			                </c:otherwise>
			            	</c:choose>
					     </c:when>
					    </c:choose>
				      </footer> 
			    </article>
		    </section> 
		</div>
    <section class="cf" id="favourite-benefits" style="background-color:#ffca00;filter:alpha(opacity=50);-moz-opacity:0.5;opacity:0.5">
     	<div style="margin-left:50px;padding-left:80px; padding-right:80px;">
	     	<font size="5px" color="#2894ff" ><strong>活 动 补 充</strong></font>  
	   			${activity.content}
  		</div>
    </section> 
    <section class="cf" id="why-work"> 
      <header class="header"> 
       <h2 class="heading">一 共 ${activity.commentCount} 条 评 论</h2> 
      </header>
      <div class="inside">  
      <ol class="reasons"> 
       	<c:import url="/activity/${activity.id}/comments/${no}"></c:import>	

	  </div>
		<form action="<c:url value="/activity/addcmt" />" method="post" id="commentform">
		   	<p><textarea name="content" id="comment" cols="58" rows="10"   required="true"></textarea></p>
		
		   	<p>
		   		<input name="submit" id="cmt_submit" tabindex="5" value="提交评论" type="submit">
				<input name="actId" value="${activity.id}" id="actId" type="hidden">
				<input name="replyToId" value="" id="replyToId" type="hidden">
				<input name="cmtId" value="" id="cmtId" type="hidden">
		 	</p>
		 	
	 	</form>
       
     
     </section>
     <footer class="page-footer"> 

      <p class="watermark">Zai Zhe Li</p> 
     </footer> 
    </div> 
    <div id="dimmer"></div> 
   </div> 
  </div> 
	</div>
	<jsp:include page="/WEB-INF/views/comp/back.top.jsp"></jsp:include>
	<script type="text/javascript">
		adjustWebWidth();
	</script>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">提 醒</h3>
		</div>
		<div class="modal-body">
		<p id="modal_text">确定要退出此活动？</p>
		</div>
		<div class="modal-footer">
		<a id="sure" class="btn-or" act="<c:url value="/activity/${activity.id}/quit"/>"  >确定</a>
		<a  data-dismiss="modal"  class="btn btn-primary">取消</a>
		</div>
	</div>
	

<script type="text/javascript" src="<c:url value="/resources/js/bootstrapx-popoverx.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.timeago.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.18.custom.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.imagesloaded.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.pnotify.js" />" ></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=zh_cn&region=CN"></script>
<script type="text/javascript" src="<c:url value="/resources/js/gmap3.js" />"></script>
<script type="text/javascript">
	$(function(){
		var names = $.map(${myUsers},function(value,i) {
           return {'name':value};
         });
		 $(".timeago").timeago();
		 $('#main-content').each(function(){
			op.pin_bind_event($(this));
		});	
		 $('#comment')
		  .atwho({
		    at: "@",
		    data: names
		  });
		 $('#quit_act').click(function(){
		 	$('#myModal').modal('toggle');
		 });
		 $('#sure').click(function(){
		 	op.apply_quit(event.currentTarget);
		 	event.preventDefault(); 
		 	setTimeout(function(){window.location.reload();}, 1500);
		 });

	});
</script>
</body>
</html>