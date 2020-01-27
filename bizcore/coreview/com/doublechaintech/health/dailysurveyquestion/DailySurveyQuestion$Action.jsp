
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty dailySurveyQuestion}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A DailySurveyQuestion" style="color: green">${userContext.localeMap['daily_survey_question']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['daily_survey_question.id']}</span> ${dailySurveyQuestion.id}</li>
<li><span>${userContext.localeMap['daily_survey_question.topic']}</span> ${dailySurveyQuestion.topic}</li>
<li><span>${userContext.localeMap['daily_survey_question.option_a']}</span> ${dailySurveyQuestion.optionA}</li>
<li><span>${userContext.localeMap['daily_survey_question.option_b']}</span> ${dailySurveyQuestion.optionB}</li>
<li><span>${userContext.localeMap['daily_survey_question.option_c']}</span> ${dailySurveyQuestion.optionC}</li>
<li><span>${userContext.localeMap['daily_survey_question.option_d']}</span> ${dailySurveyQuestion.optionD}</li>

	
	</ul>
</div>



</c:if>


