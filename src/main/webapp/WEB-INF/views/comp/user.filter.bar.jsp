<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="user-filter-bar" class="filter-bar subnav board row-fluid content-wrapper" style="height: 34px; padding-top: 2px; box-shadow:0 0 1px 2px rgba(255, 251, 251, 0.5); margin-top:10px;">
		<div class="p-5 f-l fs-16" style="line-height:30px;" >
			<span>用户搜索：</span>
		</div>
		<div id="bin-template" class="bin mr-5 f-l fs-14 dis-n">
			<a class="close" data-dismiss="alert">×</a>
			<strong data-type="" data-value=""></strong>
		</div>
		<form id="profile-search" class="navbar-search mt-0" action="">
		<c:forEach var="filter" items="${filters}">
			<c:choose>
			<c:when test="${filter.type eq 'gender'}">
			<div class="btn-group ${filter.type} bin fs-14 mr-10 f-l">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="c-666">${filter.typeLabel}:</span>
					<strong>${filter.label}</strong>
					<input type="hidden" name="${filter.type}" value="${filter.value}"></input>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" data-value="">无所谓</a></li>
					<li><a href="#" data-value="male">帅哥</a></li>
					<li><a href="#" data-value="female">美女</a></li>
				</ul>
			</div>
			</c:when>
			<c:otherwise>
			<div class="search-box ${filter.type} fs-14 f-l" style="padding-left:15px;">
				<span>${filter.typeLabel}: &nbsp;</span>
				<input class="search-query search-input span3" name="${filter.type}" 
					type="text" placeholder="请输入想要搜索的关键词..." value="${filter.value}"
					style="display:inline-block; width:200px; border-radius:8px 8px 8px 8px;">
				<i class="icon-search p-a" style="right: 5px; top: 7px;"></i>
			</div>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</form>
</div>
<script type="text/javascript">
	$(function(){
		$('#user-filter-city-picker a').click(function(){
			var pinyin = $(this).attr('title').toLowerCase();
			var p_container = $(this).parents('.btn-group.city');
			var h_input = p_container.find('input');
			var s_label = p_container.find('a strong');
			h_input.val(pinyin);
			s_label.text($(this).text());
			$('#profile-search').submit();
		});
		
		$('.btn-group.gender li a').click(function(){
			var data_value = $(this).attr('data-value');
			var p_container = $(this).parents('.btn-group.gender');
			var h_input = p_container.find('input');
			var s_label = p_container.find('a strong');
			h_input.val(data_value);
			s_label.text($(this).text());
			$('#profile-search').submit();
		});
		
		$('#profile-search').on('submit', function(){
			var params = {};
			$(this).find('input').each(function(){
				params[$(this).attr('name')] = $(this).val();
			});
			setTimeout(function(){
				op.update_query_str(params);
			}, 500);
			return false;
		});
	});
</script>