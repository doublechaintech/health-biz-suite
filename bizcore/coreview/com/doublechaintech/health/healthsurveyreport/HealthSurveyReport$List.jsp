<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty healthSurveyReportList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['health_survey_report']}! 
		 <a href="./${ownerBeanName}Manager/addHealthSurveyReport/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty healthSurveyReportList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("healthSurveyReportList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['health_survey_report']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addHealthSurveyReport/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'healthSurveyReportList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${healthSurveyReportListName};${healthSurveyReportListName}CurrentPage=${page.pageNumber};${healthSurveyReportListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='healthSurveyReportListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['health_survey_report.id']}</th>
</c:if>
<c:if test="${param.referName ne 'surveyName'}">
	<th>${userContext.localeMap['health_survey_report.survey_name']}</th>
</c:if>
<c:if test="${param.referName ne 'surveyTime'}">
	<th>${userContext.localeMap['health_survey_report.survey_time']}</th>
</c:if>
<c:if test="${param.referName ne 'teacherName'}">
	<th>${userContext.localeMap['health_survey_report.teacher_name']}</th>
</c:if>
<c:if test="${param.referName ne 'school'}">
	<th>${userContext.localeMap['health_survey_report.school']}</th>
</c:if>
<c:if test="${param.referName ne 'schoolClass'}">
	<th>${userContext.localeMap['health_survey_report.school_class']}</th>
</c:if>
<c:if test="${param.referName ne 'studentName'}">
	<th>${userContext.localeMap['health_survey_report.student_name']}</th>
</c:if>
<c:if test="${param.referName ne 'studentNumber'}">
	<th>${userContext.localeMap['health_survey_report.student_number']}</th>
</c:if>
<c:if test="${param.referName ne 'guardianName'}">
	<th>${userContext.localeMap['health_survey_report.guardian_name']}</th>
</c:if>
<c:if test="${param.referName ne 'guardianMobile'}">
	<th>${userContext.localeMap['health_survey_report.guardian_mobile']}</th>
</c:if>
<c:if test="${param.referName ne 'student'}">
	<th>${userContext.localeMap['health_survey_report.student']}</th>
</c:if>
<c:if test="${param.referName ne 'teacher'}">
	<th>${userContext.localeMap['health_survey_report.teacher']}</th>
</c:if>
<c:if test="${param.referName ne 'survey'}">
	<th>${userContext.localeMap['health_survey_report.survey']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${healthSurveyReportList}">
				<tr currentVersion='${item.version}' id="healthSurveyReport-${item.id}" ><td><a class="link-action-removed" href="./healthSurveyReportManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'surveyName'}">	<td contenteditable='true' class='edit-value'  propertyToChange='surveyName' storedCellValue='${item.surveyName}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.surveyName}</td>
</c:if><c:if test="${param.referName ne 'surveyTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='surveyTime' storedCellValue='${item.surveyTime}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.surveyTime}" /></td>
</c:if><c:if test="${param.referName ne 'teacherName'}">	<td contenteditable='true' class='edit-value'  propertyToChange='teacherName' storedCellValue='${item.teacherName}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.teacherName}</td>
</c:if><c:if test="${param.referName ne 'school'}">	<td contenteditable='true' class='edit-value'  propertyToChange='school' storedCellValue='${item.school}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.school}</td>
</c:if><c:if test="${param.referName ne 'schoolClass'}">	<td contenteditable='true' class='edit-value'  propertyToChange='schoolClass' storedCellValue='${item.schoolClass}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.schoolClass}</td>
</c:if><c:if test="${param.referName ne 'studentName'}">	<td contenteditable='true' class='edit-value'  propertyToChange='studentName' storedCellValue='${item.studentName}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.studentName}</td>
</c:if><c:if test="${param.referName ne 'studentNumber'}">	<td contenteditable='true' class='edit-value'  propertyToChange='studentNumber' storedCellValue='${item.studentNumber}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.studentNumber}</td>
</c:if><c:if test="${param.referName ne 'guardianName'}">	<td contenteditable='true' class='edit-value'  propertyToChange='guardianName' storedCellValue='${item.guardianName}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.guardianName}</td>
</c:if><c:if test="${param.referName ne 'guardianMobile'}">	<td contenteditable='true' class='edit-value'  propertyToChange='guardianMobile' storedCellValue='${item.maskedGuardianMobile}' prefix='${ownerBeanName}Manager/updateHealthSurveyReport/${result.id}/${item.id}/'>${item.maskedGuardianMobile}</td>
</c:if><c:if test="${param.referName ne 'student'}">
	<td class="select_candidate_td"
			data-candidate-method="./healthSurveyReportManager/requestCandidateStudent/${ownerBeanName}/${item.id}/"
			data-switch-method="./healthSurveyReportManager/transferToAnotherStudent/${item.id}/"
			data-link-template="./studentManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.student}">
			<a href='./studentManager/view/${item.student.id}/'>${item.student.displayName}</a>
			</c:if>
			<c:if test="${empty  item.student}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'teacher'}">
	<td class="select_candidate_td"
			data-candidate-method="./healthSurveyReportManager/requestCandidateTeacher/${ownerBeanName}/${item.id}/"
			data-switch-method="./healthSurveyReportManager/transferToAnotherTeacher/${item.id}/"
			data-link-template="./teacherManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.teacher}">
			<a href='./teacherManager/view/${item.teacher.id}/'>${item.teacher.displayName}</a>
			</c:if>
			<c:if test="${empty  item.teacher}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'survey'}">
	<td class="select_candidate_td"
			data-candidate-method="./healthSurveyReportManager/requestCandidateSurvey/${ownerBeanName}/${item.id}/"
			data-switch-method="./healthSurveyReportManager/transferToAnotherSurvey/${item.id}/"
			data-link-template="./classDailyHealthSurveyManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.survey}">
			<a href='./classDailyHealthSurveyManager/view/${item.survey.id}/'>${item.survey.displayName}</a>
			</c:if>
			<c:if test="${empty  item.survey}">
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

				<a href='#${ownerBeanName}Manager/removeHealthSurveyReport/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyHealthSurveyReportFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


