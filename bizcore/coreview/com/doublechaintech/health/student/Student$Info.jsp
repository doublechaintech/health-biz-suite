
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty student}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A Student">${userContext.localeMap['student']} ${referName}</b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./studentManager/view/${student.id}/"> ${student.id}</a></li>
<li><span>${userContext.localeMap['student.student_name']}</span> ${student.studentName}</li>
<li><span>${userContext.localeMap['student.student_number']}</span> ${student.studentNumber}</li>
<li><span>${userContext.localeMap['student.student_avatar']}</span> ${student.studentAvatar}</li>
<li><span>${userContext.localeMap['student.guardian_name']}</span> ${student.guardianName}</li>
<li><span>${userContext.localeMap['student.guardian_mobile']}</span> ${student.maskedGuardianMobile}</li>
<li><span>${userContext.localeMap['student.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${student.createTime}" /></li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




