<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html  class="app_back">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>活动申请 在浙里——分享你我的社交</title>
<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-formhelpers.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.default.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.pnotify.zaizheli.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/languages/zh-cn/jquery.validationEngine.lang.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-formhelpers-datepicker.en_US.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-formhelpers-datepicker.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.pnotify.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrapx-popoverx.js" />" ></script>
<style type="text/css">
.offset4 {
  margin-left: 300px;
}
input.span4 {
  margin-left: 20px;
  width: 330px;
}

input.span5 {
  margin-left: 20px;
  width: 330px;
  height: 80px;
}

input,
textarea,
.uneditable-input {
  width: 330px;
}


input[type=text], input[type=password], input[type=datetime], input[type=datetime-local], input[type=date], input[type=month], input[type=time], input[type=week], input[type=number], input[type=email], input[type=url], input[type=search], input[type=tel], input[type=color], .uneditable-input {
  background: #ffffff;
 -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
     -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
       -o-transition: border linear 0.2s, box-shadow linear 0.2s;
          transition: border linear 0.2s, box-shadow linear 0.2s;
    display: inline-block;
    font-size: 13px;
    height: 30px;
    line-height: 18px;
    margin-bottom: 9px;
    padding: 4px;
}

textarea:focus, input[type=text]:focus, input[type=password]:focus, input[type=datetime]:focus, input[type=datetime-local]:focus, input[type=date]:focus, input[type=month]:focus, input[type=time]:focus, input[type=week]:focus, input[type=number]:focus, input[type=email]:focus, input[type=url]:focus, input[type=search]:focus, input[type=tel]:focus, input[type=color]:focus, .uneditable-input:focus {
  border-color: #E67E22;
  border-width: 2px;
  outline: 0;
  outline: thin dotted \9;
  /* IE6-9 */

  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
}

</style>	
	
</head>

<body class="app_back">
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="none"/>
	</jsp:include>
    <div class="container" style="height: 756px">
		<div class="row mt-30" style="margin-left: 250px;" >
			<div class="appbg"></div>
			<!--<div class="span8 offset4 mt-20" style="width: 569px"> -->
			<div class="applogin-form" style="width:450px; vertical-align:middle;">
				<c:url value="/application/${activity.id}/create" var="url"/> 
				<form id="requestForm"  modelAttribute="ApplicationVo" method="post" action="${url}">
				    <c:if test="${activity.realNameReq eq true}">
						<div class="control-group" style="margin-bottom: 6px;">
						真实姓名
						<input id="realName" name="realName" type="text" value="${user.realName}" class="applogin-field validate[required]" style="height: 40px;">
						</div>
					</c:if>
					<c:if test="${activity.ageReq eq true}" >
						<div class="control-group" style="margin-bottom: 6px; margin-top:15px; ">
						您的年龄
						<input id="age" name="age" type="text" value="${user.age}" class="applogin-field validate[required,custom[integer],min[0],max[150]]" style="height:40px;">
						</div>
					</c:if>
					<c:if test="${activity.birthdayReq eq true}">
						<div class="control-group" style="margin-bottom: 6px; margin-top:15px; width:600px;">
						<div class="bfh-datepicker controls" data-format="y-m-d" data-date="${(!empty user.birthday)?user.birthday:'1993-01-01'}">

							<div class="bfh-datepicker-toggle" data-toggle="bfh-datepicker" >
								您的生日
								<input id="birthday" class="applogin-field validate[required,past[now]] " type="text" name="birthday" value="${user.birthday}"  style="height:40px; readonly">
							</div>
							<div class="bfh-datepicker-calendar">
							    <table class="calendar table table-bordered">
							      <thead>
							        <tr class="months-header">
							          <th class="month" colspan="4">
							            <a class="previous" href="#"><i class="icon-chevron-left"></i></a>
							            <span></span>
							            <a class="next" href="#"><i class="icon-chevron-right"></i></a>
							          </th>
							          <th class="year" colspan="3">
							            <a class="previous" href="#"><i class="icon-chevron-left"></i></a>
							            <span></span>
							            <a class="next" href="#"><i class="icon-chevron-right"></i></a>
							          </th>
							        </tr>
							        <tr class="days-header">
							        </tr>
							      </thead>
							      <tbody>
							      </tbody>
							    </table>
							  </div>
							</div>
						</div>
					
					</c:if>
					<c:if test="${activity.telReq eq true}">
						<div class="control-group" style="margin-bottom: 6px;margin-top:15px;">
						联系电话
						<input  id="tel" name="tel" type="text" placeholder="长号或短号均可" value="${user.tel}" class="applogin-field validate[required,custom[integer],minSize[6],maxSize[17]]" style="height: 40px;">
						</div>
					</c:if>
					<c:if test="${activity.schoolReq eq true}">					
						<div class="control-group" style="margin-bottom: 6px; margin-top:15px;">
						您的学校
						<input id="school" name="school" type="text" value="${user.school}" placeholder="浙大/玉泉" class="applogin-field" style="height: 40px;">
						</div>
					</c:if>
					<c:if test="${activity.addressReq eq true}">
						<div class="control-group" style="margin-bottom: 6px; margin-top:15px;">
						联系地址
						<input id="address" name="address" type="text" value="${user.address}" placeholder="浙江大学玉泉校区xx舍xx室" class="applogin-field" style="height: 40px;">
						</div>
					</c:if>
					<c:if test="${activity.noteReq eq true}">
						<div class="control-group" style="margin-bottom: 6px; margin-top:15px; margin-bottom:10px;">
						说点什么
						<textarea class="applogin-field validate[required,maxSize[200]]" placeholder="限200字以内" rows="3" cols="20" type="text" name="note" id="note" style="margin-left:5px;">		
						</textarea>
						</div>
					</c:if>
					<div class="control-group" style="margin-left: 75px; margin-top: 20px;">
					<button id="go" type="submit" class="btn btn-info">提交</button>
							 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="btn" href="<c:url value="/activities/${activity.id}" />">返回</a>
							
					</div>
			
				</form>
			</div>
	    </div>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h3 id="myModalLabel">提 示</h3>
	</div>
	<div class="modal-body">
	<p>已成功提交了申请表 >o< </p>
	</div>
	<div class="modal-footer">
	<a class="btn-or" href="<c:url value="/activities/${activity.id}" />">确定</a>
	</div>
	</div>

	<div id="myModal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h3 id="myModalLabel">提 示</h3>
	</div>
	<div class="modal-body">
	<p id="modal_text"></p>
	</div>
	<div class="modal-footer">
	<a class="btn-or" href="<c:url value="/activities/${activity.id}" />">确定</a>
	</div>
	</div>
					
<script type="text/javascript" src="/resources/js/ga.js" ></script>
<script type="text/javascript">
    adjustWebWidth();
	$("#requestForm").validationEngine('attach', {
		promptPosition : "bottomRight", 
		scroll: false	
	});
	$(function(){
	    $('#go').click(function(){
	    	$('#requestForm').ajaxForm({ 
	        	dataType:  'json', 
	        	success: function(data){
						  if(!data) return;
						  if(data && data.resultCode == 'INVALID'){
						  	    $('#modal_text').text(data.exceptionMsg);	  	
								$('#myModal2').modal('show');
						  }
						  if(data && data.resultCode == 'SUCCESS'){
						   		$('#myModal').modal('show');
						  }
				}
	    	}); 	 
	    })
	});
</script>
</body>
</html>
