<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html class="app_v">
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
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrapx-popoverx.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.18.custom.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.pnotify.js" />" ></script>
		
<style type="text/css">
.offset4 {
  margin-left: 350px;
}

.span0 {
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

input[disabled], input[readonly], textarea[disabled], textarea[readonly] {
background-color: #EAEDED;
border-color: transparent;
color: #CAD2D3;
cursor: default;
}

textarea, input[type=text], input[type=password], input[type=datetime], input[type=datetime-local], input[type=date], input[type=month], input[type=time], input[type=week], input[type=number], input[type=email], input[type=url], input[type=search], input[type=tel], input[type=color], .uneditable-input {
  background-color: #ffffff;
  border: 1px solid #E67E22;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
     -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
       -o-transition: border linear 0.2s, box-shadow linear 0.2s;
          transition: border linear 0.2s, box-shadow linear 0.2s;
}

textarea:focus, input[type=text]:focus, input[type=password]:focus, input[type=datetime]:focus, input[type=datetime-local]:focus, input[type=date]:focus, input[type=month]:focus, input[type=time]:focus, input[type=week]:focus, input[type=number]:focus, input[type=email]:focus, input[type=url]:focus, input[type=search]:focus, input[type=tel]:focus, input[type=color]:focus, .uneditable-input:focus {
  border-color: #E67E22;
  outline: 0;
  outline: thin dotted \9;
  /* IE6-9 */

  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
}

</style>	
	
</head>

<body >
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="none"/>
	</jsp:include>
    <div id="app_v" class="container">
		<div  class="row mt-30" style="font-size: 18" >
			<div class="appbg" style="margin-left: 250px;"></div>
			<div class="span8 offset4 mt-20" style="width: 569px; vertical-align: middle;">	
					<div class="control-group" style="height: 20px;">
					昵称
				    <span style="margin-left:40px">${application.applicant.name}</span></div>	
					<div class="control-group" style="height: 20px;">
					性别
					<span style="margin-left:40px">${application.applicant.gender}</span></div>	
					<div class="control-group" style="height: 20px;">
					邮箱
					<span style="margin-left:40px">${application.applicant.email}</span></div>	
					<b>&nbsp;</b>
					<br>
					<br>
					<c:if test="${activity.realNameReq eq true}">
						<div class="control-group" style="margin-bottom: 10px;">
						姓名
						<span style="margin-left:40px">${application.realName}</span>
						</div>
					</c:if>
					<c:if test="${activity.ageReq eq true}" >
						<div class="control-group" style="margin-bottom: 10px;">
						年龄
						<span style="margin-left:40px">${application.age}</span>
						</div>
					</c:if>
					<c:if test="${activity.birthdayReq eq true}">
						<div class="control-group" style="margin-bottom: 10px;">
						生日
						<span style="margin-left:40px">${application.birthday}</span>
						</div>
					</c:if>
					<c:if test="${activity.telReq eq true}">
						<div class="control-group" style="margin-bottom: 10px;">
						电话
						<span style="margin-left:40px">${application.tel}</span>
						</div>
					</c:if>
					<c:if test="${activity.schoolReq eq true}">					
						<div class="control-group" style="margin-bottom: 10px;">
						学校
						<span style="margin-left:40px">${application.school}</span>
						</div>
					</c:if>
					<c:if test="${activity.addressReq eq true}">
						<div class="control-group" style="margin-bottom: 10px;">
						地址
						<span style="margin-left:40px">${application.address}</span>
						</div>
					</c:if>
					<c:if test="${activity.noteReq eq true}">
						<div class="control-group" style="margin-bottom: 10px;">
						Ta的话
						<br><span style="margin-left:25px">${application.note}</span>
						</div>
					</c:if>	
					<c:choose>
					<c:when test="${application.status eq '申请中'}">
					<div style="margin-left:60px; margin-top:28px;">			    
						<a  id="a_agree" class=" btn btn-danger b" act="<c:url value="/application/${application.id}/agree" />" onclick="op.application_agree(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>" style="width:50px;">
								同意</a>
						<a  id="a_refuse" class="btn btn-blue-2 ml-10" act="<c:url value="/application/${application.id}/refuse" />" onclick="op.application_refuse(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>" style="width:50px; margin-left:20px;" >拒绝</a>
					</div>	
					</c:when>
					<c:when test="${application.status eq '已加入'}">
					<div style="margin-left:200px; margin-top:28px;">			    
						<a  id="a_kickout" class="btn btn-inverse ml-10" act="<c:url value="/application/${application.id}/kickout" />" onclick="op.application_kickout(event.currentTarget); event.preventDefault();" re="<c:url value="/activity/${application.activity.id}/applications"/>">踢出此活动</a>
						<a  class="btn ml-10" href="<c:url value="/activity/${application.activity.id}/applications"/>">返回</a>	
					</div>	
					</c:when>
					<c:otherwise>
						<a  class="btn" style="margin-left:240px" href="<c:url value="/activity/${application.activity.id}/applications"/>">返回</a>
					</c:otherwise>
					</c:choose>
			</div>
	    </div>
	</div>
						
<script type="text/javascript">
	$(function(){
		 $('#app_v').each(function(){
			op.pin_bind_event($(this));
		});	

	});
</script>
</body>
</html>
