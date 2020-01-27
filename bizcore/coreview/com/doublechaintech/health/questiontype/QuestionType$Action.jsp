
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty questionType}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A QuestionType" style="color: green">${userContext.localeMap['question_type']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['question_type.id']}</span> ${questionType.id}</li>
<li><span>${userContext.localeMap['question_type.name']}</span> ${questionType.name}</li>
<li><span>${userContext.localeMap['question_type.code']}</span> ${questionType.code}</li>

	
	</ul>
</div>



</c:if>


