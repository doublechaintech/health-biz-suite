
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty supervisor}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Supervisor" style="color: green">${userContext.localeMap['supervisor']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['supervisor.id']}</span> ${supervisor.id}</li>
<li><span>${userContext.localeMap['supervisor.name']}</span> ${supervisor.name}</li>
<li><span>${userContext.localeMap['supervisor.gender']}</span> ${supervisor.gender}</li>
<li><span>${userContext.localeMap['supervisor.mobile']}</span> ${supervisor.maskedMobile}</li>

	
	</ul>
</div>



</c:if>


