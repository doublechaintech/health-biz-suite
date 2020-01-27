
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty guardian}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Guardian" style="color: green">${userContext.localeMap['guardian']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['guardian.id']}</span> ${guardian.id}</li>
<li><span>${userContext.localeMap['guardian.name']}</span> ${guardian.name}</li>
<li><span>${userContext.localeMap['guardian.mobile']}</span> ${guardian.maskedMobile}</li>
<li><span>${userContext.localeMap['guardian.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${guardian.createTime}" /></li>

	
	</ul>
</div>



</c:if>


