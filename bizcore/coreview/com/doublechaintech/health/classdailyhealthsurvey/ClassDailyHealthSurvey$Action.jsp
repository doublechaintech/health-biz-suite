
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty classDailyHealthSurvey}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A ClassDailyHealthSurvey" style="color: green">${userContext.localeMap['class_daily_health_survey']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['class_daily_health_survey.id']}</span> ${classDailyHealthSurvey.id}</li>
<li><span>${userContext.localeMap['class_daily_health_survey.name']}</span> ${classDailyHealthSurvey.name}</li>
<li><span>${userContext.localeMap['class_daily_health_survey.survey_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${classDailyHealthSurvey.surveyTime}" /></li>
<li><span>${userContext.localeMap['class_daily_health_survey.download_url']}</span> ${classDailyHealthSurvey.downloadUrl}</li>

	
	</ul>
</div>



</c:if>


