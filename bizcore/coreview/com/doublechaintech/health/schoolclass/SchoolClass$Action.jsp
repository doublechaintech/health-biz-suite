
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty schoolClass}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A SchoolClass" style="color: green">${userContext.localeMap['school_class']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['school_class.id']}</span> ${schoolClass.id}</li>
<li><span>${userContext.localeMap['school_class.name']}</span> ${schoolClass.name}</li>
<li><span>${userContext.localeMap['school_class.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${schoolClass.createTime}" /></li>
<li><span>${userContext.localeMap['school_class.schoole']}</span> ${schoolClass.schoole}</li>

	
	</ul>
</div>



</c:if>


