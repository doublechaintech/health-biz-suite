
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty location}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Location" style="color: green">${userContext.localeMap['location']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['location.id']}</span> ${location.id}</li>
<li><span>${userContext.localeMap['location.name']}</span> ${location.name}</li>
<li><span>${userContext.localeMap['location.address']}</span> ${location.address}</li>
<li><span>${userContext.localeMap['location.latitude']}</span> ${location.latitude}</li>
<li><span>${userContext.localeMap['location.longitude']}</span> ${location.longitude}</li>

	
	</ul>
</div>



</c:if>


