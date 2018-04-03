<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="spot-filter-bar" class="filter-bar subnav board row-fluid content-wrapper" style="height: 34px; padding-top: 2px; box-shadow:0 0 1px 2px rgba(255, 251, 251, 0.5); margin-top:10px;">
	<div class="span101 row-fluid">
		<c:if test="${param.viewType ne 'rm'}">
		<div class="p-5 f-l fs-16" style="line-height:30px;" >
			<span>活动搜索：</span>
		</div>
		<form id="spot-search" class="navbar-search mt-0" action="">
		<c:forEach var="filter" items="${filters}">
			<c:choose>
			<c:when test="${filter.type eq 'city'}">
			<div class="btn-group ${filter.type} bin fs-14 mr-10 f-l">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="c-666">${filter.typeLabel}:</span>
					<strong>${filter.label}</strong>
					<input type="hidden" name="${filter.type}" value="${filter.value}"></input>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" data-value="">无所谓</a></li>
					<li><a href="#" data-value="杭州">杭州</a></li>
					<li><a href="#" data-value="浙江省_除杭州">浙江省(除杭州)</a></li>											
					<li><a href="#" data-value="国内_除浙江">国内(除浙江)</a></li>
					<li><a href="#" data-value="国外">国外</a></li>
				</ul>
			</div>
			</c:when>
			<c:when test="${filter.type eq 'category'}">
			<div class="btn-group ${filter.type} bin fs-14 mr-10 f-l">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="c-666">${filter.typeLabel}:</span>
					<strong>${filter.label}</strong>
					<input type="hidden" name="${filter.type}" value="${filter.value}"></input>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" data-value="">全部</a></li>
					<li><a href="#" data-value="聚餐">聚餐</a></li>
					<li><a href="#" data-value="出游">出游</a></li>											
					<li><a href="#" data-value="电影">电影</a></li>
					<li><a href="#" data-value="购物">逛街</a></li>
					<li><a href="#" data-value="讨论">讨论</a></li>
					<li><a href="#" data-value="运动">运动</a></li>
					<li><a href="#" data-value="班级活动">班级活动</a></li>
					<li><a href="#" data-value="社会实践">社会实践</a></li>
					<li><a href="#" data-value="学习活动">学习活动</a></li>
					<li><a href="#" data-value="其它">其它</a></li>
				</ul>
			</div>
			</c:when>
			<c:when test="${filter.type eq 'startTime'}">
			<div class="fs-14 f-l" style="margin-left:12px;margin-top:5px;">
				<div class="bfh-datepicker" data-format="y-m-d" style="float:right">
					<div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker" >
						${filter.typeLabel}: &nbsp;<input id="startDate" class="input-small" type="text" name="${filter.type}" value="${filter.value}" style="display:inline-block;  border-radius:8px 8px 8px 8px; min-height:24px; width:80px"   readonly>
					</div>																		
					<div class="bfh-datepicker-calendar">
						<table class="calendar table table-bordered">
							<thead>
								<tr class="months-header">
									<th class="month" colspan="4">
										<a class="previous" href="#">
											<i class="icon-chevron-left"></i>
										</a>
										<span></span>
										<a class="next" href="#">
											<i class="icon-chevron-right"></i>
										</a>
									</th>
									<th class="year" colspan="3">
										<a class="previous" href="#">
											<i class="icon-chevron-left"></i>
										</a>
										<span></span>
										<a class="next" href="#">
											<i class="icon-chevron-right"></i>
										</a>
									</th>
								</tr>
								<tr class="days-header"></tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>				
			</div>
			</c:when>
			<c:when test="${filter.type eq 'endTime'}">
			<div class="fs-14 f-l" style="margin-top:5px;margin-left:12px;margin-top:5px;">
				<div class="bfh-datepicker" data-format="y-m-d" style="float:right">
					<div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker" >
						${filter.typeLabel}: &nbsp;<input id="startDate" class="input-small" type="text" name="${filter.type}" value="${filter.value}" style="display:inline-block;  border-radius:8px 8px 8px 8px; min-height:24px; width:80px"   readonly>
					</div>																		
					<div class="bfh-datepicker-calendar">
						<table class="calendar table table-bordered">
							<thead>
								<tr class="months-header">
									<th class="month" colspan="4">
										<a class="previous" href="#">
											<i class="icon-chevron-left"></i>
										</a>
										<span></span>
										<a class="next" href="#">
											<i class="icon-chevron-right"></i>
										</a>
									</th>
									<th class="year" colspan="3">
										<a class="previous" href="#">
											<i class="icon-chevron-left"></i>
										</a>
										<span></span>
										<a class="next" href="#">
											<i class="icon-chevron-right"></i>
										</a>
									</th>
								</tr>
								<tr class="days-header"></tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>				
			</div>
			</c:when>
			<c:otherwise>
			<div class="search-box fs-14 f-l">
				<span style="margin-left:12px;">${filter.typeLabel}: &nbsp;</span>
				<input class="search-query search-input span3" name="${filter.type}" 
					type="text" placeholder="请输入想要搜索的关键词..." value="${filter.value}"
					style="display:inline-block; width:160px; border-radius:8px 8px 8px 8px;">				
			</div>
			<div class="f-l"><a class="icon-search " style=" margin-top: 5px; margin-left:10px;" id="toSearch">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Go</a></div>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</form>
		</c:if>
		<c:if test="${param.viewType eq 'rm'}">
			<div class="p-5 f-l fs-16" style="line-height:30px;" >
			<span>热门活动：</span>
			</div>
			<div id="nav-options" class="nav-options f-l">
					<span id="navPlay">Play</span>
					<span id="navPause">Pause</span>
			</div>
		</c:if>
	</div>
	<div class="span202 view-t" style="margin-top:5px; position:absolute; width: 120px; margin-left: 1000px; ">
		<div class="btn-groupsbar f-r" data-toggle="buttons-radio">
			<button class="btn rm <c:if test="${param.viewType eq 'rm'}">active</c:if>" data-href="<c:url value="/hot" />" data-original-title="热门活动" rel="tooltip"><i class="icon-star-empty icon-my"></i></button>
			<button class="btn wf <c:if test="${param.viewType eq 'wf'}">active</c:if>" data-href="<c:url value="/" />" data-original-title="瀑布布局" rel="tooltip" ><i class="icon-th icon-my"></i></button>
			<button class="btn mv <c:if test="${param.viewType eq 'mv'}">active</c:if>" data-href="<c:url value="/map" />" data-original-title="地图布局" rel="tooltip"><i class="icon-map-marker icon-my"></i></button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('#spot-filter-city-picker a').click(function(){
			var pinyin = $(this).attr('title').toLowerCase();
			var p_container = $(this).parents('.btn-group.city');
			var h_input = p_container.find('input');
			var s_label = p_container.find('a strong');
			h_input.val(pinyin);
			s_label.text($(this).text());
			$('#spot-search').submit();
		});
		
		$('.btn-group.category li a').click(function(){
			var data_value = $(this).attr('data-value');
			var p_container = $(this).parents('.btn-group.category');
			var h_input = p_container.find('input');
			var s_label = p_container.find('a strong');
			h_input.val(data_value);
			s_label.text($(this).text());
			$('#spot-search').submit();
		});

		$('.btn-group.city li a').click(function(){
			var data_value = $(this).attr('data-value');
			var p_container = $(this).parents('.btn-group.city');
			var h_input = p_container.find('input');
			var s_label = p_container.find('a strong');
			h_input.val(data_value);
			s_label.text($(this).text());
			$('#spot-search').submit();
		});
		$('#toSearch').click(function(){
			$('#spot-search').submit();
		});
		$('#spot-search').on('submit', function(){
			var params = {};
			$(this).find('input').each(function(){
				params[$(this).attr('name')] = $(this).val();
			});
			setTimeout(function(){
				op.update_query_str(params);
			}, 500);
			return false;
		});
		$('.view-t button').click(function(){
			var href = $(this).attr('data-href');
			window.location.href= href;
		});
		$('[rel="tooltip"]').tooltip();
	});
</script>
<style type="text/css">
.row-fluid [class*="span101"] {
  width: 1000px;
}
.span202 {
	width: 100px;
}
</style>
