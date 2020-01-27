
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty surveyQuestion}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A SurveyQuestion" style="color: green">${userContext.localeMap['survey_question']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['survey_question.id']}</span> ${surveyQuestion.id}</li>
<li><span>${userContext.localeMap['survey_question.name']}</span> ${surveyQuestion.name}</li>

	
	</ul>
</div>



</c:if>


