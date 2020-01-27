
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty parent}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Parent" style="color: green">${userContext.localeMap['parent']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['parent.id']}</span> ${parent.id}</li>
<li><span>${userContext.localeMap['parent.name']}</span> ${parent.name}</li>
<li><span>${userContext.localeMap['parent.mobile']}</span> ${parent.maskedMobile}</li>
<li><span>${userContext.localeMap['parent.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${parent.createTime}" /></li>

	
	</ul>
</div>



</c:if>


