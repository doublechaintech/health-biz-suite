
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty dailyAnswer}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A DailyAnswer" style="color: green">${userContext.localeMap['daily_answer']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['daily_answer.id']}</span> ${dailyAnswer.id}</li>
<li><span>${userContext.localeMap['daily_answer.name']}</span> ${dailyAnswer.name}</li>

	
	</ul>
</div>



</c:if>


