
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty teacher}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A Teacher">${userContext.localeMap['teacher']} ${referName}</b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./teacherManager/view/${teacher.id}/"> ${teacher.id}</a></li>
<li><span>${userContext.localeMap['teacher.name']}</span> ${teacher.name}</li>
<li><span>${userContext.localeMap['teacher.mobile']}</span> ${teacher.maskedMobile}</li>
<li><span>${userContext.localeMap['teacher.school']}</span> ${teacher.school}</li>
<li><span>${userContext.localeMap['teacher.school_class']}</span> ${teacher.schoolClass}</li>
<li><span>${userContext.localeMap['teacher.class_size']}</span> ${teacher.classSize}</li>
<li><span>${userContext.localeMap['teacher.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${teacher.createTime}" /></li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




