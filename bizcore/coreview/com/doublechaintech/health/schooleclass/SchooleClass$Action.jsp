
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty schooleClass}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A SchooleClass" style="color: green">${userContext.localeMap['schoole_class']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['schoole_class.id']}</span> ${schooleClass.id}</li>
<li><span>${userContext.localeMap['schoole_class.name']}</span> ${schooleClass.name}</li>
<li><span>${userContext.localeMap['schoole_class.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${schooleClass.createTime}" /></li>
<li><span>${userContext.localeMap['schoole_class.schoole']}</span> ${schooleClass.schoole}</li>

	
	</ul>
</div>



</c:if>


