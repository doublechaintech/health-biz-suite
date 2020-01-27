<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty studentHealthSurveyList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['student_health_survey']}! 
		 <a href="./${ownerBeanName}Manager/addStudentHealthSurvey/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty studentHealthSurveyList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("studentHealthSurveyList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['student_health_survey']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addStudentHealthSurvey/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'studentHealthSurveyList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${studentHealthSurveyListName};${studentHealthSurveyListName}CurrentPage=${page.pageNumber};${studentHealthSurveyListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='studentHealthSurveyListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['student_health_survey.id']}</th>
</c:if>
<c:if test="${param.referName ne 'student'}">
	<th>${userContext.localeMap['student_health_survey.student']}</th>
</c:if>
<c:if test="${param.referName ne 'answerTime'}">
	<th>${userContext.localeMap['student_health_survey.answer_time']}</th>
</c:if>
<c:if test="${param.referName ne 'surveyStatus'}">
	<th>${userContext.localeMap['student_health_survey.survey_status']}</th>
</c:if>
<c:if test="${param.referName ne 'teacher'}">
	<th>${userContext.localeMap['student_health_survey.teacher']}</th>
</c:if>
<c:if test="${param.referName ne 'classDailyHealthSurvey'}">
	<th>${userContext.localeMap['student_health_survey.class_daily_health_survey']}</th>
</c:if>
<c:if test="${param.referName ne 'createTime'}">
	<th>${userContext.localeMap['student_health_survey.create_time']}</th>
</c:if>
<c:if test="${param.referName ne 'lastUpdateTime'}">
	<th>${userContext.localeMap['student_health_survey.last_update_time']}</th>
</c:if>
<c:if test="${param.referName ne 'cq'}">
	<th>${userContext.localeMap['student_health_survey.cq']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${studentHealthSurveyList}">
				<tr currentVersion='${item.version}' id="studentHealthSurvey-${item.id}" ><td><a class="link-action-removed" href="./studentHealthSurveyManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'student'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentHealthSurveyManager/requestCandidateStudent/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentHealthSurveyManager/transferToAnotherStudent/${item.id}/"
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
<c:if test="${param.referName ne 'answerTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='answerTime' storedCellValue='${item.answerTime}' prefix='${ownerBeanName}Manager/updateStudentHealthSurvey/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.answerTime}" /></td>
</c:if><c:if test="${param.referName ne 'surveyStatus'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentHealthSurveyManager/requestCandidateSurveyStatus/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentHealthSurveyManager/transferToAnotherSurveyStatus/${item.id}/"
			data-link-template="./surveyStatusManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.surveyStatus}">
			<a href='./surveyStatusManager/view/${item.surveyStatus.id}/'>${item.surveyStatus.displayName}</a>
			</c:if>
			<c:if test="${empty  item.surveyStatus}">
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
			data-candidate-method="./studentHealthSurveyManager/requestCandidateTeacher/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentHealthSurveyManager/transferToAnotherTeacher/${item.id}/"
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
<c:if test="${param.referName ne 'classDailyHealthSurvey'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentHealthSurveyManager/requestCandidateClassDailyHealthSurvey/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentHealthSurveyManager/transferToAnotherClassDailyHealthSurvey/${item.id}/"
			data-link-template="./classDailyHealthSurveyManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.classDailyHealthSurvey}">
			<a href='./classDailyHealthSurveyManager/view/${item.classDailyHealthSurvey.id}/'>${item.classDailyHealthSurvey.displayName}</a>
			</c:if>
			<c:if test="${empty  item.classDailyHealthSurvey}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'createTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='createTime' storedCellValue='${item.createTime}' prefix='${ownerBeanName}Manager/updateStudentHealthSurvey/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.createTime}" /></td>
</c:if><c:if test="${param.referName ne 'lastUpdateTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='lastUpdateTime' storedCellValue='${item.lastUpdateTime}' prefix='${ownerBeanName}Manager/updateStudentHealthSurvey/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.lastUpdateTime}" /></td>
</c:if><c:if test="${param.referName ne 'cq'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentHealthSurveyManager/requestCandidateCq/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentHealthSurveyManager/transferToAnotherCq/${item.id}/"
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

				<a href='#${ownerBeanName}Manager/removeStudentHealthSurvey/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyStudentHealthSurveyFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


