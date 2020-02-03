
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty healthSurveyReport}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A HealthSurveyReport" style="color: green">${userContext.localeMap['health_survey_report']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['health_survey_report.id']}</span> ${healthSurveyReport.id}</li>
<li><span>${userContext.localeMap['health_survey_report.survey_name']}</span> ${healthSurveyReport.surveyName}</li>
<li><span>${userContext.localeMap['health_survey_report.survey_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${healthSurveyReport.surveyTime}" /></li>
<li><span>${userContext.localeMap['health_survey_report.teacher_name']}</span> ${healthSurveyReport.teacherName}</li>
<li><span>${userContext.localeMap['health_survey_report.school']}</span> ${healthSurveyReport.school}</li>
<li><span>${userContext.localeMap['health_survey_report.school_class']}</span> ${healthSurveyReport.schoolClass}</li>
<li><span>${userContext.localeMap['health_survey_report.student_name']}</span> ${healthSurveyReport.studentName}</li>
<li><span>${userContext.localeMap['health_survey_report.student_number']}</span> ${healthSurveyReport.studentNumber}</li>
<li><span>${userContext.localeMap['health_survey_report.guardian_name']}</span> ${healthSurveyReport.guardianName}</li>
<li><span>${userContext.localeMap['health_survey_report.guardian_mobile']}</span> ${healthSurveyReport.maskedGuardianMobile}</li>

	
	</ul>
</div>



</c:if>


