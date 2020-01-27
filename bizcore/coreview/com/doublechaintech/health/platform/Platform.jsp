<%@ page import='java.util.*,com.doublechaintech.health.*,com.doublechaintech.health.platform.Platform'%>

<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<!DOCTYPE html>
<html lang="en" slick-uniqueid="3"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="${baseURL}/" />    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
   
    <title>  ${userContext.localeMap['@system_name']} </title>

    <!-- Bootstrap core CSS -->
    <link href="./bootstrap/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
 	<link href="./bootstrap/jquery-ui.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="./bootstrap/dashboard.css" rel="stylesheet">
    <link href="./bootstrap/font-awesome.min.css" rel="stylesheet">
   
   

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./bootstrap/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
 <style type="text/css">* {
 text-shadow: transparent 0px 0px 0px, rgba(0,0,0,0.68) 0px 0px 0px !important; 
}

</style>


</head>

  <body >


    <div class="container-fluid">
      <div class="row">
      
        <div class="col-xs-12 col-sm-12  col-md-12  main">
          
          <div class="table-responsive" id="content">
          <!-- real content -->

<c:set var="ownerBeanName" value="platform" scope="request"/>
<c:set var="ownerClassName" value="Platform" scope="request"/>




<div id="msg"></div>

<div class="row">

	<div class="col-xs-12 col-md-12">
			    <c:if test="${not empty result.errorMessageList}" >
    <ul>
    	<c:forEach var="item" items="${result.errorMessageList}">
    		
    		<div class="alert alert-success">
   ${item.sourcePosition} ${item.body}
</div>
    	</c:forEach>
    	</ul>
    </c:if>
</div>



</div> <!--<div class="row">-->

<div class="row">
	
</div>

<div class="row">
	
	<div class="col-xs-12 col-md-12">

	<ul class="nav nav-tabs" id="navi-tabs">
	  <li class="active"><a data-toggle="tab" href="#summary" class="disabled"><i class="fa  fa-home"></i> ${userContext.localeMap['@summary']}</a></li>
	 
	<% Platform result = (Platform)request.getAttribute("result");  %>
			<li><a data-toggle="tab" href="#provinceList" class="disabled"> ${userContext.localeMap['province']}</a></li>
			<li><a data-toggle="tab" href="#cityList" class="disabled"> ${userContext.localeMap['city']}</a></li>
			<li><a data-toggle="tab" href="#districtList" class="disabled"> ${userContext.localeMap['district']}</a></li>
			<li><a data-toggle="tab" href="#teacherList" class="disabled"> ${userContext.localeMap['teacher']}</a></li>
			<li><a data-toggle="tab" href="#studentList" class="disabled"> ${userContext.localeMap['student']}</a></li>
			<li><a data-toggle="tab" href="#questionList" class="disabled"> ${userContext.localeMap['question']}</a></li>
			<li><a data-toggle="tab" href="#questionTypeList" class="disabled"> ${userContext.localeMap['question_type']}</a></li>
			<li><a data-toggle="tab" href="#surveyStatusList" class="disabled"> ${userContext.localeMap['survey_status']}</a></li>
			<li><a data-toggle="tab" href="#userList" class="disabled"> ${userContext.localeMap['user']}</a></li>
			<li><a data-toggle="tab" href="#changeRequestList" class="disabled"> ${userContext.localeMap['change_request']}</a></li>
			<li><a data-toggle="tab" href="#changeRequestTypeList" class="disabled"> ${userContext.localeMap['change_request_type']}</a></li>
 
	</ul>
	</div>
</div>
<div class="tab-content"  id='tab-content'>
<div id="summary" class="tab-pane fade in active">
<div class="row">
	
	<div class="col-xs-12 col-md-12">
	
	</div>
</div>

<div class="row" desc="show parent objects">
	
	   
	<c:set var="platform" value="${ result}" scope="request" />
<sky:include page="com/doublechaintech/health/platform/Platform$Summary.jsp" />

	
</div>

	
	

	







	





	</div><!-- end of <div id="summary" class="tab-pane fade in active">-->

	

		<c:if test='${not empty userContext.accessTokens["provinceList"] or ignoreListAccessControl}'>
		<c:set var="provinceList" value="${result.provinceList}" scope="request"/>
		<c:set var="provinceListName" value="provinceList" scope="request"/>
		<div id="provinceList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/province/Province$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["cityList"] or ignoreListAccessControl}'>
		<c:set var="cityList" value="${result.cityList}" scope="request"/>
		<c:set var="cityListName" value="cityList" scope="request"/>
		<div id="cityList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/city/City$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["districtList"] or ignoreListAccessControl}'>
		<c:set var="districtList" value="${result.districtList}" scope="request"/>
		<c:set var="districtListName" value="districtList" scope="request"/>
		<div id="districtList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/district/District$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["teacherList"] or ignoreListAccessControl}'>
		<c:set var="teacherList" value="${result.teacherList}" scope="request"/>
		<c:set var="teacherListName" value="teacherList" scope="request"/>
		<div id="teacherList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/teacher/Teacher$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["studentList"] or ignoreListAccessControl}'>
		<c:set var="studentList" value="${result.studentList}" scope="request"/>
		<c:set var="studentListName" value="studentList" scope="request"/>
		<div id="studentList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/student/Student$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["questionList"] or ignoreListAccessControl}'>
		<c:set var="questionList" value="${result.questionList}" scope="request"/>
		<c:set var="questionListName" value="questionList" scope="request"/>
		<div id="questionList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/question/Question$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["questionTypeList"] or ignoreListAccessControl}'>
		<c:set var="questionTypeList" value="${result.questionTypeList}" scope="request"/>
		<c:set var="questionTypeListName" value="questionTypeList" scope="request"/>
		<div id="questionTypeList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/questiontype/QuestionType$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["surveyStatusList"] or ignoreListAccessControl}'>
		<c:set var="surveyStatusList" value="${result.surveyStatusList}" scope="request"/>
		<c:set var="surveyStatusListName" value="surveyStatusList" scope="request"/>
		<div id="surveyStatusList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/surveystatus/SurveyStatus$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["userList"] or ignoreListAccessControl}'>
		<c:set var="userList" value="${result.userList}" scope="request"/>
		<c:set var="userListName" value="userList" scope="request"/>
		<div id="userList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/user/User$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["changeRequestList"] or ignoreListAccessControl}'>
		<c:set var="changeRequestList" value="${result.changeRequestList}" scope="request"/>
		<c:set var="changeRequestListName" value="changeRequestList" scope="request"/>
		<div id="changeRequestList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/changerequest/ChangeRequest$List.jsp"
					referName="platform"/>
		</div>
	</c:if>
	<c:if test='${not empty userContext.accessTokens["changeRequestTypeList"] or ignoreListAccessControl}'>
		<c:set var="changeRequestTypeList" value="${result.changeRequestTypeList}" scope="request"/>
		<c:set var="changeRequestTypeListName" value="changeRequestTypeList" scope="request"/>
		<div id="changeRequestTypeList" class="tab-pane fade sublist" refer-name="platform">
			<sky:include page="com/doublechaintech/health/changerequesttype/ChangeRequestType$List.jsp"
					referName="platform"/>
		</div>
	</c:if>

	

</div><!--<div class="tab-content" style="padding-top: 10px">-->




 <!-- /real content -->
          
          
          </div>
        </div><!--  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
        </div><!-- <div class="row"> -->
        </div>
        
  
<div id="footer">
  <div class="col-xs-12 navbar-inverse navbar-fixed-bottom">
  <div class="row" id="bottomNav">
    <div class="col-xs-3 text-center">
    	<a href="./secUserManager/home/"><i class="glyphicon glyphicon-home"></i><br/>${userContext.localeMap['@home_page']}</a>
    </div>
    <div class="col-xs-3 text-center">
    	<a href="./secUserManager/home/"><i class="glyphicon glyphicon-envelope"></i><br>${userContext.localeMap['@message']}</a>
    </div>
    <div class="col-xs-3 text-center">
    	<a href="./secUserManager/home/"><i class="glyphicon glyphicon-envelope"></i><br>${userContext.localeMap['@message']}</a>
    </div>
    <div class="col-xs-3 text-center">
    	<a href="./secUserManager/showlogin/">
    	<i class="glyphicon glyphicon-user"></i><br>${userContext.localeMap['@log_out']}</a>
    </div>
  </div>
  </div>
</div>   <!-- --><!-- /.footer -->
        
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./bootstrap/jquery.min.js"></script>
    <script src="./bootstrap/jquery-ui.min.js"></script>
    
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./bootstrap/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./bootstrap/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./bootstrap/ie10-viewport-bug-workaround.js"></script>
    <script src="./scripts/qrcode.js" type="text/javascript"></script>    
    <script>

 	var qrLocaleLabel = "${userContext.localeMap['@qr_code']}";
 	var scanQRTips = "${userContext.localeMap['@qr_scan_to_continue']}";
 	$("a[data-toggle='tab']").removeClass("disabled");
 	
	
</script>
     <script src="./scripts/common.js" type="text/javascript"></script>
     <script src="./bootstrap/bootstrap3-typeahead.js" type="text/javascript"></script>


</body></html>


