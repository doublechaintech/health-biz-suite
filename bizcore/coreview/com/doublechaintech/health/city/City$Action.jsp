
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty city}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A City" style="color: green">${userContext.localeMap['city']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['city.id']}</span> ${city.id}</li>
<li><span>${userContext.localeMap['city.name']}</span> ${city.name}</li>
<li><span>${userContext.localeMap['city.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${city.createTime}" /></li>

	
	</ul>
</div>



</c:if>


