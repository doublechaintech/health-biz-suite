
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty questionSource}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A QuestionSource" style="color: green">${userContext.localeMap['question_source']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['question_source.id']}</span> ${questionSource.id}</li>
<li><span>${userContext.localeMap['question_source.name']}</span> ${questionSource.name}</li>
<li><span>${userContext.localeMap['question_source.code']}</span> ${questionSource.code}</li>

	
	</ul>
</div>



</c:if>


