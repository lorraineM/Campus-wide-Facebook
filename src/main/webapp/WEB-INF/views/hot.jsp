<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zaizheli.net/functions" prefix="f" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
	<title>热门活动 在浙里——分享你我的社交</title>	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-formhelpers.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/slicebox.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/custom.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/modernizr.custom.46884.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.slicebox.js" />"></script>
</head>
<body class="front">
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="home"/>
	</jsp:include>
	<div id="water-fall-wrapper" class="main-wrapper">
		<jsp:include page="/WEB-INF/views/comp/spot.filter.bar.jsp" >
			<jsp:param name="viewType" value="rm"/>
		</jsp:include>
		<div id="map-view" class="content-wrapper p-r">
			<div class="wrapper">
				<ul id="sb-slider" class="sb-slider">
					<c:if test="${not empty activities}">
						<c:forEach var="activity" items="${activities}">
							<li>
								<a href="<c:url value="/activities/${activity.id}" />" style="text-align:center;">
								  <c:choose>
									<c:when test="${activity.image.orgSize[1]>=840}">
										<img width="840px" src="${f:imageUrl(activity.image.resId)}" alt="${activity.title}"/>
									</c:when>
									<c:otherwise>
										 <img src="${f:imageUrl(activity.image.resId)}" alt="${activity.title}"/>
									</c:otherwise>
								</c:choose> 
								
								<div class="sb-description" >
									<h3>${activity.title}</h3>
								</div>
								</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>

				<div id="shadow" class="shadow"></div>

				<div id="nav-arrows" class="nav-arrows">
					<a href="#">Next</a>
					<a href="#">Previous</a>
				</div>

				<div id="nav-dots" class="nav-dots">
					<c:if test="${not empty activities}">
						<c:forEach var="activity" items="${activities}">
						<span></span>
					</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		adjustWebWidth();
	</script>

<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
<script type="text/javascript">
	$(function() {
		var Page = (function() {
			var $navArrows = $( '#nav-arrows' ).hide(),
				$navOptions = $( '#nav-options' ).hide(),
				$navDots = $( '#nav-dots' ).hide(),
				$nav = $navDots.children( 'span' ),
				$shadow = $( '#shadow' ).hide(),
				slicebox = $( '#sb-slider' ).slicebox( {
					onReady : function() {
						$navArrows.show();
						$navOptions.show();
						$navDots.show();
						$shadow.show();
					},
					onBeforeChange : function( pos ) {
						$nav.removeClass( 'nav-dot-current' );
						$nav.eq( pos ).addClass( 'nav-dot-current' );
					},
					orientation : 'r',
					cuboidsCount : 3,
					autoplay : true
				} ),				
				init = function() {
					initEvents();					
				},
				initEvents = function() {
					// add navigation events
					$navArrows.children( ':first' ).on( 'click', function() {
						slicebox.next();
						return false;
					} );
					$navArrows.children( ':last' ).on( 'click', function() {					
						slicebox.previous();
						return false;
					} );
					$( '#navPlay' ).on( 'click', function() {						
						slicebox.play();
						return false;
					} );
					$( '#navPause' ).on( 'click', function() {						
						slicebox.pause();
						return false;
					} );
					$nav.each( function( i ) {				
						$( this ).on( 'click', function( event ) {							
							var $dot = $( this );							
							if( !slicebox.isActive() ) {
								$nav.removeClass( 'nav-dot-current' );
								$dot.addClass( 'nav-dot-current' );							
							}							
							slicebox.jump( i + 1 );
							return false;						
						} );						
					} );
				};
				return { init : init };
		})();
		if( !Modernizr.csstransforms3d ) {
			$('#sb-note').show();
			$('#sb-examples > li:gt(2)').remove();
			if( !Modernizr.csstransforms3d || navigator.userAgent.indexOf("Firefox") != -1 ) {
				$('body').append(
					$('script').attr( 'type', 'text/javascript' ).attr( 'src', 'js/jquery.easing.1.3.js' )
				);
			}
		}
		Page.init();
	});
</script>
<script type="text/javascript" src="<c:url value="/resources/js/ga.js" />" ></script>
</body>
</html>