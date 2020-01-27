
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty classQuestion}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A ClassQuestion" style="color: green">${userContext.localeMap['class_question']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['class_question.id']}</span> ${classQuestion.id}</li>
<li><span>${userContext.localeMap['class_question.topic']}</span> ${classQuestion.topic}</li>
<li><span>${userContext.localeMap['class_question.option_a']}</span> ${classQuestion.optionA}</li>
<li><span>${userContext.localeMap['class_question.option_b']}</span> ${classQuestion.optionB}</li>
<li><span>${userContext.localeMap['class_question.option_c']}</span> ${classQuestion.optionC}</li>
<li><span>${userContext.localeMap['class_question.option_d']}</span> ${classQuestion.optionD}</li>

	
	</ul>
</div>



</c:if>


