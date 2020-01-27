
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty user}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A User" style="color: green">${userContext.localeMap['user']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['user.id']}</span> ${user.id}</li>
<li><span>${userContext.localeMap['user.name']}</span> ${user.name}</li>
<li><span>${userContext.localeMap['user.avatar']}</span> ${user.avatar}</li>
<li><span>${userContext.localeMap['user.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${user.createTime}" /></li>

	
	</ul>
</div>



</c:if>


