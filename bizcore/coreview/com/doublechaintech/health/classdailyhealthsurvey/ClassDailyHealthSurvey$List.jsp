<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty classDailyHealthSurveyList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['class_daily_health_survey']}! 
		 <a href="./${ownerBeanName}Manager/addClassDailyHealthSurvey/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty classDailyHealthSurveyList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("classDailyHealthSurveyList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['class_daily_health_survey']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addClassDailyHealthSurvey/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'classDailyHealthSurveyList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${classDailyHealthSurveyListName};${classDailyHealthSurveyListName}CurrentPage=${page.pageNumber};${classDailyHealthSurveyListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='classDailyHealthSurveyListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['class_daily_health_survey.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['class_daily_health_survey.name']}</th>
</c:if>
<c:if test="${param.referName ne 'teacher'}">
	<th>${userContext.localeMap['class_daily_health_survey.teacher']}</th>
</c:if>
<c:if test="${param.referName ne 'surveyTime'}">
	<th>${userContext.localeMap['class_daily_health_survey.survey_time']}</th>
</c:if>
<c:if test="${param.referName ne 'creator'}">
	<th>${userContext.localeMap['class_daily_health_survey.creator']}</th>
</c:if>
<c:if test="${param.referName ne 'downloadUrl'}">
	<th>${userContext.localeMap['class_daily_health_survey.download_url']}</th>
</c:if>
<c:if test="${param.referName ne 'changeRequest'}">
	<th>${userContext.localeMap['class_daily_health_survey.change_request']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${classDailyHealthSurveyList}">
				<tr currentVersion='${item.version}' id="classDailyHealthSurvey-${item.id}" ><td><a class="link-action-removed" href="./classDailyHealthSurveyManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateClassDailyHealthSurvey/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'teacher'}">
	<td class="select_candidate_td"
			data-candidate-method="./classDailyHealthSurveyManager/requestCandidateTeacher/${ownerBeanName}/${item.id}/"
			data-switch-method="./classDailyHealthSurveyManager/transferToAnotherTeacher/${item.id}/"
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
<c:if test="${param.referName ne 'surveyTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='surveyTime' storedCellValue='${item.surveyTime}' prefix='${ownerBeanName}Manager/updateClassDailyHealthSurvey/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.surveyTime}" /></td>
</c:if><c:if test="${param.referName ne 'creator'}">
	<td class="select_candidate_td"
			data-candidate-method="./classDailyHealthSurveyManager/requestCandidateCreator/${ownerBeanName}/${item.id}/"
			data-switch-method="./classDailyHealthSurveyManager/transferToAnotherCreator/${item.id}/"
			data-link-template="./userManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.creator}">
			<a href='./userManager/view/${item.creator.id}/'>${item.creator.displayName}</a>
			</c:if>
			<c:if test="${empty  item.creator}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'downloadUrl'}">	<td contenteditable='true' class='edit-value'  propertyToChange='downloadUrl' storedCellValue='${item.downloadUrl}' prefix='${ownerBeanName}Manager/updateClassDailyHealthSurvey/${result.id}/${item.id}/'><a title='${item.downloadUrl}'>${fn:substring(item.downloadUrl,0,10)}...</a></td>
</c:if><c:if test="${param.referName ne 'changeRequest'}">
	<td class="select_candidate_td"
			data-candidate-method="./classDailyHealthSurveyManager/requestCandidateChangeRequest/${ownerBeanName}/${item.id}/"
			data-switch-method="./classDailyHealthSurveyManager/transferToAnotherChangeRequest/${item.id}/"
			data-link-template="./changeRequestManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.changeRequest}">
			<a href='./changeRequestManager/view/${item.changeRequest.id}/'>${item.changeRequest.displayName}</a>
			</c:if>
			<c:if test="${empty  item.changeRequest}">
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

				<a href='#${ownerBeanName}Manager/removeClassDailyHealthSurvey/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyClassDailyHealthSurveyFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


