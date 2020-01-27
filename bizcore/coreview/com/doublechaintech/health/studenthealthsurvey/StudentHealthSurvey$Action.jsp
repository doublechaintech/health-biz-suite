
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty studentHealthSurvey}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A StudentHealthSurvey" style="color: green">${userContext.localeMap['student_health_survey']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['student_health_survey.id']}</span> ${studentHealthSurvey.id}</li>
<li><span>${userContext.localeMap['student_health_survey.answer_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${studentHealthSurvey.answerTime}" /></li>
<li><span>${userContext.localeMap['student_health_survey.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${studentHealthSurvey.createTime}" /></li>
<li><span>${userContext.localeMap['student_health_survey.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${studentHealthSurvey.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


