<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty studentList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['student']}! 
		 <a href="./${ownerBeanName}Manager/addStudent/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty studentList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("studentList"); 
 	int totalCount = list.getTotalCount();
 	List pages = list.getPages();
 	pageContext.setAttribute("rowsPerPage",list.getRowsPerPage()); 
 	
 	pageContext.setAttribute("pages",pages); 
 	pageContext.setAttribute("totalCount",totalCount); 
 	//pageContext.setAttribute("accessible",list.isAccessible()); 
 	//the reason using scriptlet here is the el express is currently not able resolv common property from a subclass of list
%>
    
 	   
    <div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		<i class='fa fa-table'></i> ${userContext.localeMap['student']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addStudent/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'studentList' eq action.actionGroup}">
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
		 
		 
		 
		 </div>
 </div>
    
    
<div class="table-responsive">


<c:set var="currentPageNumber" value="1"/>	



<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var="page" items="${pages}"> 
<c:set var="classType" value=""/>
<c:if test='${page.selected}' >
<c:set var="classType" value="active"/>
<c:set var="currentPageNumber" value="${page.pageNumber}"/>
</c:if>


	<li class="page-item ${classType}">
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${studentListName};${studentListName}CurrentPage=${page.pageNumber};${studentListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='studentListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['student.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['student.name']}</th>
</c:if>
<c:if test="${param.referName ne 'gender'}">
	<th>${userContext.localeMap['student.gender']}</th>
</c:if>
<c:if test="${param.referName ne 'guardian'}">
	<th>${userContext.localeMap['student.guardian']}</th>
</c:if>
<c:if test="${param.referName ne 'schoolClass'}">
	<th>${userContext.localeMap['student.school_class']}</th>
</c:if>
<c:if test="${param.referName ne 'studentId'}">
	<th>${userContext.localeMap['student.student_id']}</th>
</c:if>
<c:if test="${param.referName ne 'cq'}">
	<th>${userContext.localeMap['student.cq']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${studentList}">
				<tr currentVersion='${item.version}' id="student-${item.id}" ><td><a class="link-action-removed" href="./studentManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateStudent/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'gender'}">	<td contenteditable='true' class='edit-value'  propertyToChange='gender' storedCellValue='${item.gender}' prefix='${ownerBeanName}Manager/updateStudent/${result.id}/${item.id}/'>${item.gender}</td>
</c:if><c:if test="${param.referName ne 'guardian'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentManager/requestCandidateGuardian/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentManager/transferToAnotherGuardian/${item.id}/"
			data-link-template="./guardianManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.guardian}">
			<a href='./guardianManager/view/${item.guardian.id}/'>${item.guardian.displayName}</a>
			</c:if>
			<c:if test="${empty  item.guardian}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'schoolClass'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentManager/requestCandidateSchoolClass/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentManager/transferToAnotherSchoolClass/${item.id}/"
			data-link-template="./schoolClassManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.schoolClass}">
			<a href='./schoolClassManager/view/${item.schoolClass.id}/'>${item.schoolClass.displayName}</a>
			</c:if>
			<c:if test="${empty  item.schoolClass}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'studentId'}">	<td contenteditable='true' class='edit-value'  propertyToChange='studentId' storedCellValue='${item.studentId}' prefix='${ownerBeanName}Manager/updateStudent/${result.id}/${item.id}/'>${item.studentId}</td>
</c:if><c:if test="${param.referName ne 'cq'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentManager/requestCandidateCq/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentManager/transferToAnotherCq/${item.id}/"
			data-link-template="./changeRequestManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.cq}">
			<a href='./changeRequestManager/view/${item.cq.id}/'>${item.cq.displayName}</a>
			</c:if>
			<c:if test="${empty  item.cq}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>

				<td>

				<a href='#${ownerBeanName}Manager/removeStudent/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyStudentFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


