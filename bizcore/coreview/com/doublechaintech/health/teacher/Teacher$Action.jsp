
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty teacher}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Teacher" style="color: green">${userContext.localeMap['teacher']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['teacher.id']}</span> ${teacher.id}</li>
<li><span>${userContext.localeMap['teacher.name']}</span> ${teacher.name}</li>
<li><span>${userContext.localeMap['teacher.mobile']}</span> ${teacher.maskedMobile}</li>
<li><span>${userContext.localeMap['teacher.school']}</span> ${teacher.school}</li>
<li><span>${userContext.localeMap['teacher.school_class']}</span> ${teacher.schoolClass}</li>
<li><span>${userContext.localeMap['teacher.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${teacher.createTime}" /></li>

	
	</ul>
</div>



</c:if>


