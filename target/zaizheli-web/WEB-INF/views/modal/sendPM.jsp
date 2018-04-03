<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />

<div id="pmModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header mes_title">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h2 id="myModalLabel">私 信</h2>
	</div>
<form id="pmForm" modelAttribute="PMVo" method="post" action="<c:url value="/message/pm/create" />">
<div class="modal-body"> 
	<input name="to" value="" id="to" type="hidden"> 
	<input name="base" value="" id="base" type="hidden"> 
	<div class="control-group " >
		<div class="controls" style="margin-bottom:12px">发&nbsp;&nbsp;给: <span id="sendto" style="margin-left:25px;";></span></div>
		<div class="controls"  style="margin-bottom:12px">标&nbsp;&nbsp;题:
			<input name="title"  id="title" class="validate[required,maxSize[30]]" style="margin-left:25px; width:240px"></div>   
		<div class="controls">内&nbsp;&nbsp;容：	
			<textarea id="content" name="content" rows="6" cols="20" class="validate[required,maxSize[400]]" style="margin-left:15px; width:440px; border-color: rgb(232, 232, 232); border-style: solid; border-width: 1px; font-size: 12px;" ></textarea>
		</div>
	</div>
</div>
<div class="modal-footer">
<button class="btn btn-ora" type="submit" id="send">发 送</button>
<button type="reset" style="display:none"></button>
</div>
</form>
</div>
<script type="text/javascript" src="<c:url value="/resources/languages/zh-cn/jquery.validationEngine.lang.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js" />" ></script>
<script type="text/javascript">
	$("#pmForm").validationEngine('attach', {
		promptPosition : "bottomLeft", 
		scroll: false	
	});
	$(function(){
	    $('#send').click(function(){
	    	$('#pmForm').ajaxForm({ 
	        	dataType:  'json', 
	        	success: function(data){
						  if(!data) return;
						  if(data && data.resultCode == 'NEED_SIGNIN'){
						  	    window.location.href= web_context + "/signin";
						  }
						  if(data && data.resultCode == 'INVALID'){
						  	    alert(data.exceptionMsg);
						  }
						  if(data && data.resultCode == 'SUCCESS'){
						   		$('#pmModal').modal('hide');
						   		$("button[type=reset]").trigger("click");
						  }
				}
	    	}); 	 
	    })
	});
</script>
