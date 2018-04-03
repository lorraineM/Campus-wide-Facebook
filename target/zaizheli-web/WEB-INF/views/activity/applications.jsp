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
	<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
</head>
<body class="front" style="padding-top:46px; ">
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="none"/>
	</jsp:include>
	<jsp:include page="/WEB-INF/views/comp/side.nav.jsp" />
	<c:import url="../modal/sendPM.jsp"/>
    <div id="main-content"> 
	   <div class="inside-main-content cf">
		    <section class="jobs-top" id="jobs"> 
			     <article class="job-panel" style="width:1000px;"> 
				      <header class="job-panel-header cf"> 
				      		<ul id="profile-nav-tabs" class="nav nav-tabs " >
				    		<li class="in_app <c:if test="${view eq 'in'}">active</c:if>"  style="margin-left:30px">
				   				<a class="app_tab" href="<c:url value="/activity/${activity.id}/applications/in"/>">申请中( ${ activity.inJudgingCount} )</a></li>
				    		<li class="agree <c:if test="${view eq 'agree'}">active</c:if>"  >
				   				<a class="app_tab" href="<c:url value="/activity/${activity.id}/applications/agree"/>">已申请加入( ${activity.currentNum-1} )</a></li>				  
				    		<li class="refuse <c:if test="${view eq 'refuse'}">active</c:if>">
				    			<a class="app_tab" href="<c:url value="/activity/${activity.id}/applications/refuse"/>">已拒绝( ${activity.applicationCount-activity.inJudgingCount-activity.currentNum + 1})</a></li>
				    		</ul>
				     </header> 
				     <div class="content cf" style="padding:0px;">   	
				       <div class="tab-content bg-gray p-20" id="water-fall-wrapper" style="background:#DDDFEF;-moz-transition: left 0.5s ease-in-out 0s, top 0.5s ease-in-out 0s;">	
						    <div class="tab-pane active" id="water-fall">
						    	<c:import url="/activity/${activity.id}/applications/${view}s/0"></c:import>
						    </div>
						</div>
					    <div id="page-nav">
							<a href="<c:url value="/activity/${activity.id}/applications/${view}s/1" />"></a>
						</div>    
				      </div>
				 
				      
			    </article> 
			      
		    </section> 
		 </div>
	</div>
		    
	<jsp:include page="/WEB-INF/views/comp/back.top.jsp" />
	<script type="text/javascript">
		adjustWebWidth();
	</script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrapx-popoverx.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.pnotify.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.imagesloaded.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.masonry.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.infinitescroll.js" />" ></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=zh_cn&region=CN"></script>
<script type="text/javascript" src="<c:url value="/resources/js/gmap3.js" />"></script>
<script type="text/javascript">
	$(function(){

		$('.pin').each(function(){
			op.pin_bind_event($(this));
		});

		var $wf = $('#water-fall');
			
		$wf.masonry({
			itemSelector : '.pin',
		    columnWidth : 222,
		    gutterWidth: 15,
		    isAnimated: false,
		    animationOptions: {
		    	queue: false
		    },
		    isFitWidth: true
		});
			
		$wf.infinitescroll(
			{
				navSelector  : '#page-nav', // selector for the paged navigation
				nextSelector : '#page-nav a', // selector for the NEXT link (to page 2)
				itemSelector : '.pin', // selector for all items you'll retrieve
				debug        : false,
				animate	 	 : false,
				animationOptions: {
				    duration: 750,
				    easing: 'linear',
				    queue: false
				},
				loading: {
					selector: '#water-fall-wrapper',
					finishedMsg: '没有更多了',
					msgText: '加载中...',
					img: '<c:url value="/resources/img/big-loading.gif" />',
					speed: 0
				},
				state : {
					currPage: 0
				},
				pathParse: function() {
			        return ['<c:url value="/activity/${activity.id}/applications/${view}s/" />', ''];
			    }
			},
			// trigger Masonry as a callback
			function( newElements ) {
				// hide new items while they are loading
				var $newElems = $( newElements ).hide();
				$newElems.each(function(){
					op.pin_bind_event($(this));
				});
				$newElems.find(".timeago").timeago();
				// ensure that images load before adding to masonry layout
				//$newElems.imagesLoaded(function(){
					// show elems now they're ready
					$wf.append( $newElems ).masonry( 'appended', 
							$newElems, false, function(){
						$newElems.fadeIn('slow');
					});
				//}); 
			}
		);
	});
</script>
<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
</body>
</html>