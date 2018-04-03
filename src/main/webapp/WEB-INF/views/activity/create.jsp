<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>我在浙里 在浙里——分享你我的社交</title>
	<link rel="SHORTCUT ICON" href= "<c:url value="/resources/img/head-logo.png" />" /> 
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-base.css" />" />	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zaizheli-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" />	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/uploadify.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style_v.css" />" media="screen" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/validationEngine.bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-formhelpers.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.timelinr-0.9.53.js" />"></script>
	<script src="<c:url value="/ckeditor/ckeditor.js"/>"></script>
	<script>
	    window.onload = function() {
	        CKEDITOR.replace( 'editor1' );

	    }; 
	    function editor() {
	   		jQuery('#editor1').val(CKEDITOR.instances.editor1.getData());
	   	}
	</script>
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/comp/header.jsp">
		<jsp:param name="tab" value="create"/>
	</jsp:include>
	
	<c:import url="../modal/upload.jsp"/>
	<c:import url="../modal/locate.jsp"/>
	<form id="createactivity-form" modelAttribute="activityCreationVo" method="post">
		<div id="timeline">
			<ul id="dates">
				<li>
					<a href="#上传海报" class="selected time">上传活动封面(15%)</a>
				</li>
				<li>
					<a href="#活动细节" >活动细节(45%)</a>
				</li>
				<li>
					<a href="#活动规划" >活动规划(75%)</a>
				</li>
				<li>
					<a href="#活动的补充要点" >补充要点(100%)</a>
				</li>
			</ul>
			<input id="image-url-hid" data-prompt-position="centerRight:0,-4" name="imageUrl" type="hidden" />
			<input id="place-id-hid" name="placeId" type="hidden" />
			
			<ul id="issues" >
				<li id="上传海报" class="selected time " style="margin-top: 100px; margin-left:30px;">
					<div class="upload-img p-r row-fluid">
						<img id="spot-image" class="" alt="" width=300 src="http://placehold.it/300&text=Upload+Image">
						<a id="upload-image-btn" class="p-a upload-btn mt-20" 
               				 href="#upload-image-modal" data-toggle="modal" title="上传,引用图片" style="margin-left: 60px;">+</a>
					</div>
				</li>
				<li id="活动细节" style="height:600px;">
					<div class="row-fluid">
							<div class="control-group " >
								<label class="control-label fs-15 lh-20 c-ffc" for="title">* 给你的活动取个名称吧</label>
								<div class="controls fs-16 lh-18">
									<div class="input-prepend">
										<span class="add-on"> <i class="icon-leaf"></i>
										</span>
										<input id="title" class="validate[required,maxSize[15]] input-xlarge" type="text" name="title" 
	              							data-prompt-position="centerRight:0,-4" value="${activityCreationVo.title}"  />
									</div>
								</div>
							</div>
							<div class="control-group mt-20" style="margin-bottom: 20px;">
								<label class="control-label fs-15 lh-20 c-ffc" for="type">* 选择活动分类</label>
								<div class="input-prepend controls">
									<span class="add-on"> <i class="icon-th"></i></span>
									<select id="type" class="validate[required] span5" name="type" style="width:280px">
											<option value="聚餐">聚餐</option>
											<option value="出游">出游</option>											
											<option value="电影">电影</option>
											<option value="购物">逛街</option>
											<option value="讨论">讨论</option>
											<option value="运动">运动</option>
											<option value="班级活动">班级活动</option>
											<option value="社会实践">社会实践</option>
											<option value="学习活动">学习活动</option>
											<option value="其它">其它</option>
									</select>
								</div>
							</div>

							
							<div class="control-group " >
								<label class="control-label fs-15 lh-20 c-ffc" for="startDate">* 活动开始时间</label>
								<div>
									<div class="bfh-datepicker controls fs-16 lh-18" data-format="y-m-d" style="float:left" data-date="<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>">
										<div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker" >
											<span class="add-on">
												<i class="icon-calendar"></i>
											</span>
											<input id="startDate" class="validate[required] input-small" type="text" name="startDate" 
	             							value="${activityCreationVo.startDate}"  readonly>
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
									<div class="bfh-timepicker ml-90">
									  <div class="input-prepend bfh-timepicker-toggle ml-90" data-toggle="bfh-timepicker">
									    <span class="add-on"><i class="icon-time"></i></span>
									    <input id="startTime" name="startTime" type="text" class="validate[required] input-small" 
									    value= "${activityCreationVo.startTime}" readonly>
									  </div>
									  <div class="bfh-timepicker-popover ml-90">
									    <table class="table">
									      <tbody>
									        <tr>
									          <td class="hour">
									            <a class="next" href="#"><i class="icon-chevron-up"></i></a><br>
									            <input type="text" class="input-mini" readonly><br>
									            <a class="previous" href="#"><i class="icon-chevron-down"></i></a>
									          </td>
									          <td class="separator">:</td>
									          <td class="minute">
									            <a class="next" href="#"><i class="icon-chevron-up"></i></a><br>
									            <input type="text" class="input-mini" readonly><br>
									            <a class="previous" href="#"><i class="icon-chevron-down"></i></a>
									          </td>
									        </tr>
									      </tbody>
									    </table>
									  </div>
									</div>
								</div>
							</div>
					

							<div class="control-group mt-20" >
								<label class="control-label fs-15 lh-20 c-ffc" for="endDate">* 活动结束时间</label>
								<div>
									<div class="bfh-datepicker controls fs-16 lh-18" data-format="y-m-d" style="float:left" data-date="<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>">
										<div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker" >
											<span class="add-on">
												<i class="icon-calendar"></i>
											</span>
											<input id="endDate" class="validate[required,future[#startDate]] input-small" type="text" name="endDate" 
	             							value="${activityCreationVo.endDate}"  readonly>
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
									<div class="bfh-timepicker ml-90">
									  <div class="input-prepend bfh-timepicker-toggle ml-90" data-toggle="bfh-timepicker">
									    <span class="add-on"><i class="icon-time"></i></span>
									    <input id="endTime" name="endTime" type="text" class="validate[required] input-small" 
									     value= "${activityCreationVo.endTime}" readonly>
									  </div>
									  <div class="bfh-timepicker-popover ml-90">
									    <table class="table">
									      <tbody>
									        <tr>
									          <td class="hour">
									            <a class="next" href="#"><i class="icon-chevron-up"></i></a><br>
									            <input type="text" class="input-mini" readonly><br>
									            <a class="previous" href="#"><i class="icon-chevron-down"></i></a>
									          </td>
									          <td class="separator">:</td>
									          <td class="minute">
									            <a class="next" href="#"><i class="icon-chevron-up"></i></a><br>
									            <input type="text" class="input-mini" readonly><br>
									            <a class="previous" href="#"><i class="icon-chevron-down"></i></a>
									          </td>
									        </tr>
									      </tbody>
									    </table>
									  </div>
									</div>
								</div>
							</div>
							<div class="control-group mt-20" >
								<label class="control-label fs-15 lh-20 c-ffc" for="num">* 允许参与活动的最大人数/参加人数</label>
								<div class="controls fs-16 lh-18">
									<div class="input-prepend">
										<span class="add-on"> <i class="icon-tint"></i>
										</span>
										<input id="num" class="validate[required,custom[integer],min[1],max[65000]] input-xlarge" type="text" name="num" value=100 data-prompt-position="centerRight:0,-4"  />
									</div>
								</div>
							</div>
							<div class="control-group" style="margin-top: 20px;">
								<label class="control-label fs-15 lh-15 c-ffc" for="status">* 活动状态</label>
								<div class="input-prepend controls">
									<span class="add-on"> <i class="icon-heart"></i></span>
									<select id="status" class="validate[required] span5" name="status" value="${activityCreationVo.status}" style="width:280px">
											<option>征集成员中</option>
											<option>晒活动</option>
											<option>放弃</option>
											<option>已结束</option>
									</select>
								</div>
							</div>
						
					</div>
				</li>

				<li id="活动规划" style="height:600px;">
					<div class="row-fluid">
						<div class="control-group mt-20" >
							<div class="input-append">
								<label class="control-label fs-15 lh-20 c-ffc" for="fullAddr">* 活动地点</label>
								<span class="add-on">
									<a id="place-locate-btn" class="" 
											href="#place-locate-modal" data-toggle="modal" title="手动定位地址">
									<i class="icon-map-marker"></i></a></span>
								<input id="full-addr-input" name="fullAddr" type="text" class="controls input-xlarge"
										placeholder="点击右边的图标选择地点吧" autocomplete="off" value="<c:out value="${signUpUserVo.fullAddr}" />" />
								
							</div>
							<div><form:errors path="fullAddr" cssClass="alert alert-error"/></div>
					    </div>
					    <div class="control-group mt-20">
					    	<label class="control-label fs-15 lh-20 c-ffc" for="addr">地址备注</label>
							<div class="controls fs-16 lh-18">
								<div class="input-prepend">
									<span class="add-on"> <i class="icon-tint"></i>
									</span>
									<input id="addr" name="addr" class="input-xlarge" type="text"  
          									data-prompt-position="centerRight:0,-4"  value="${activityCreationVo.addr}"  />
								</div>
							</div>
					    </div>
					    <div class="control-group mt-20">
					    	<label class="control-label fs-15 lh-20 c-ffc" for="fee">费用</label>
							<div class="controls fs-16 lh-18">
		    					<input id="fee" class="validate[required]" type="hidden" 
		    					data-prompt-position="centerRight:0,-4" name="fee" value="${activityCreationVo.fee}" >
			    				<div class="btn-group" data-toggle="buttons-radio" data-toggle-name="fee">
			    					<button id="inputMale" class="btn btn-large  fs-16" type="button" data-val="1">是</button>
			    					<button id="gender_target" class="btn btn-large fs-16" type="button" data-val="0">无</button>
			    				</div>
							</div>
					    </div>
					    <div class="control-group mt-20">
					    	<label class="control-label fs-15 lh-20 c-ffc" for="apply">是否需要参与者提供详细个人申请资料</label>
							<div class="controls fs-16 lh-18">
			    				<input id="apply" class="validate[required]" type="hidden" 
			    					data-prompt-position="centerRight:0,-4" name="apply" value="${activityCreationVo.apply}" >
			    				<div class="btn-group" data-toggle="buttons-radio" data-toggle-name="apply">
			    					<button id="inputMale" class="btn btn-large  fs-16" type="button" data-val="1">是</button>
			    					<button id="gender_target" class="btn btn-large  fs-16" type="button" data-val="0">否</button>
			    				</div>
							</div>
					    
					    <div class="control-group mt-20">
					    	<label class="control-label fs-15 lh-20 c-ffc" for="apply_req">若需申请表,请选择需要的信息（邮箱、昵称、性别默认个人基本信息）：</label><br>
							<div class="controls fs-16 lh-18" style="color:#FF0000 ">
			    				<label class="checkbox inline">
								<input type="checkbox" id="realNameReq" name="realNameReq" value=true>
									真实姓名
								</label>
									<label class="checkbox inline">
								<input type="checkbox" id="ageReq" name="ageReq" value=true>
									年龄
								</label>
									<label class="checkbox inline">
								<input type="checkbox" id="birthdayReq" name="birthdayReq"  value=true>
									生日
								</label>
									<label class="checkbox inline">
								<input type="checkbox" id="telReq" name="telReq" value=true>
									电话
								</label>
									<label class="checkbox inline">
								<input type="checkbox" id="schoolReq" name="schoolReq" value=true>
									学校
								</label>
									<label class="checkbox inline">
								<input type="checkbox" id="addressReq" name="addressReq" value=true>
									联系地址
								</label>
								<label class="checkbox inline">
								<input type="checkbox" id="noteReq" name="noteReq" value=true>
									申请理由(想说的话)
								</label>
							</div>
					    </div>
	

					</div>
				</li>
				<li id="活动的补充要点" >
					<div class="row-fluid">
						<div class="control-group " >
							<label class="control-label fs-15 lh-20 c-ffc" for="title">还有什么想说的</label>
						<div class="controls">
							<textarea id="editor1" name="editor1" value="${activityCreationVo.editor1}"
							data-prompt-position="centerRight:0,-4" type="text"></textarea>
						</div>
						
					</div>
					<input id="err" name="err" data-prompt-position="centerRight:0,-4" style="display:none"/>
			
					<div style="float:right; margin-top:5px;">
						    
							<button  id="submit-btn" type="submit" class="btn-orange btn-large" data-loading-text="创建中..." onclick="editor()">
								提交该活动</button>
						
						<a id="return-btn" class="btn btn-large ml-10" href="<c:url value="/" />">返回主页</a>
					</div>	
								
				</li>
			</ul>
			
			<a href="#" id="next">+</a>
			<a href="#" id="prev">-</a>
		</div>
	</form>	


<script type="text/javascript">adjustWebWidth();</script>

<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/zaizheli.op.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-formhelpers-datepicker.en_US.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-formhelpers-datepicker.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-formhelpers-timepicker.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/swfobject.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.uploadify.v2.1.4.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js" />"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$().timelinr({
	        orientation:  'vertical',
	        issuesSpeed:  200,
	        datesSpeed:   100,
	        arrowKeys:    'true',
	        startAt:    1
	      });
		function attachValidationForForm(){
			$('#createactivity-form').validationEngine({
				prettySelect: true,				
				promptPosition: 'centerRight',
				autoPositionUpdate: true,
				ajaxFormValidation: true,
				ajaxFormValidationMethod: 'post',
				ajaxFormValidationURL: '<c:url value="/activities/create/validate" />',
				onBeforeAjaxFormValidation: function(form, options){
					$(form).find('#submit-btn').button('loading');
				},
				onAjaxFormComplete: function(status, form, errors, options){
					$('#submit-btn').button('reset');
					if(status == true){
						form.validationEngine('detach');
						form.ajaxSubmit({
					        dataType:  'json', 
					        beforeSubmit: function(formData, jqForm, options){
					        	$('submit-btn').button('loading');					 
					        },
					        success:  function(data){
					        	if(!data || data.resultCode != 'SUCCESS' ) return;				      
					        	window.location.href = $('#return-btn').attr('href');
					        },
					        complete: function(jqXHR, textStatus){	
					        	$('#submit-btn').button('reset');
					        	attachValidationForForm();			          	
				          	}
					    });
					}
				}	
			});
		}
		attachValidationForForm();

	    $('#spot-image').load(function(){
	    	$('#image-url-hid').val($(this).attr('src'));
			var top = ($('#spot-image').height()-$('#upload-image-btn').height())/2;
			var left = ($('#spot-image').width()-$('#upload-image-btn').width())/2;
			$('#upload-image-btn').css('top', top);
	    	$('#upload-image-btn').css('left', left);
    	});
    	$('div.btn-group[data-toggle-name=*]').each(function(){
		    var group   = $(this);
		    var form    = group.parents('form').eq(0);
		    var name    = group.attr('data-toggle-name');
		    var hidden  = $('input[name="' + name + '"]', form);
		    $('button', group).each(function(){
		    	var button = $(this);
		      	button.live('click', function(){
		        	hidden.val($(this).attr('data-val'));
		      	});
		      	if(button.attr('data-val') == hidden.val()) {
		     		button.addClass('active');
		    	}
		    });
		});
	   
	});
	function getLocateObj(){
		return {fullAddr : $('#full-addr-input').val(), 
			lngLat: $('#full-addr-input').data('lngLat'),
			placeId: $('#place-id-hid').val()};
	}
	function locateCallback(place){
		$('#place-id-hid').val(place.id);
		$('#full-addr-input').val(place.fullAddr);
		$('#full-addr-input').data('lngLat', place.lngLat);
	}
	
</script>
</body>
</html>