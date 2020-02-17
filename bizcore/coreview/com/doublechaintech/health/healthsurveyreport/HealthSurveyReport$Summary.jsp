
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty healthSurveyReport}">
<div class="col-xs-12 col-md-12 section">
	<b title="A HealthSurveyReport"> 
		${userContext.localeMap['health_survey_report']}${userContext.localeMap['@word_space']}${userContext.localeMap['@summary']}
		</b>
		
		
	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'main' eq action.actionGroup}">
		
		
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		
		
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
	<hr/>
	<div>
	
	
	<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.id']}</span>
<span >${result.id}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.survey_name']}</span>
<span >${result.surveyName}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.survey_time']}</span>
<span ><fmt:formatDate pattern="yyyy-MM-dd" value="${result.surveyTime}" /></span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.teacher_name']}</span>
<span >${result.teacherName}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.school']}</span>
<span >${result.school}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.school_class']}</span>
<span >${result.schoolClass}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.student_name']}</span>
<span >${result.studentName}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.student_number']}</span>
<span >${result.studentNumber}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.guardian_name']}</span>
<span >${result.guardianName}</span>
</div>
<div class="col-xs-12 col-md-3 summary-section">
<span class="summary-label">${userContext.localeMap['health_survey_report.guardian_mobile']}</span>
<span >${result.maskedGuardianMobile}</span>
</div>

	</div>
	
</div>

</c:if>




