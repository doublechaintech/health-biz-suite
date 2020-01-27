
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty studentDailyAnswer}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A StudentDailyAnswer" style="color: green">${userContext.localeMap['student_daily_answer']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['student_daily_answer.id']}</span> ${studentDailyAnswer.id}</li>
<li><span>${userContext.localeMap['student_daily_answer.answer']}</span> ${studentDailyAnswer.answer}</li>
<li><span>${userContext.localeMap['student_daily_answer.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${studentDailyAnswer.createTime}" /></li>
<li><span>${userContext.localeMap['student_daily_answer.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${studentDailyAnswer.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


