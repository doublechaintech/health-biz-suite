<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty dailySurveyQuestionList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['daily_survey_question']}! 
		 <a href="./${ownerBeanName}Manager/addDailySurveyQuestion/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty dailySurveyQuestionList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("dailySurveyQuestionList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['daily_survey_question']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addDailySurveyQuestion/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'dailySurveyQuestionList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${dailySurveyQuestionListName};${dailySurveyQuestionListName}CurrentPage=${page.pageNumber};${dailySurveyQuestionListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='dailySurveyQuestionListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['daily_survey_question.id']}</th>
</c:if>
<c:if test="${param.referName ne 'topic'}">
	<th>${userContext.localeMap['daily_survey_question.topic']}</th>
</c:if>
<c:if test="${param.referName ne 'questionType'}">
	<th>${userContext.localeMap['daily_survey_question.question_type']}</th>
</c:if>
<c:if test="${param.referName ne 'optionA'}">
	<th>${userContext.localeMap['daily_survey_question.option_a']}</th>
</c:if>
<c:if test="${param.referName ne 'optionB'}">
	<th>${userContext.localeMap['daily_survey_question.option_b']}</th>
</c:if>
<c:if test="${param.referName ne 'optionC'}">
	<th>${userContext.localeMap['daily_survey_question.option_c']}</th>
</c:if>
<c:if test="${param.referName ne 'optionD'}">
	<th>${userContext.localeMap['daily_survey_question.option_d']}</th>
</c:if>
<c:if test="${param.referName ne 'classDailyHealthSurvey'}">
	<th>${userContext.localeMap['daily_survey_question.class_daily_health_survey']}</th>
</c:if>
<c:if test="${param.referName ne 'surveyQuestion'}">
	<th>${userContext.localeMap['daily_survey_question.survey_question']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${dailySurveyQuestionList}">
				<tr currentVersion='${item.version}' id="dailySurveyQuestion-${item.id}" ><td><a class="link-action-removed" href="./dailySurveyQuestionManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'topic'}">	<td contenteditable='true' class='edit-value'  propertyToChange='topic' storedCellValue='${item.topic}' prefix='${ownerBeanName}Manager/updateDailySurveyQuestion/${result.id}/${item.id}/'>${item.topic}</td>
</c:if><c:if test="${param.referName ne 'questionType'}">
	<td class="select_candidate_td"
			data-candidate-method="./dailySurveyQuestionManager/requestCandidateQuestionType/${ownerBeanName}/${item.id}/"
			data-switch-method="./dailySurveyQuestionManager/transferToAnotherQuestionType/${item.id}/"
			data-link-template="./questionTypeManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.questionType}">
			<a href='./questionTypeManager/view/${item.questionType.id}/'>${item.questionType.displayName}</a>
			</c:if>
			<c:if test="${empty  item.questionType}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'optionA'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionA' storedCellValue='${item.optionA}' prefix='${ownerBeanName}Manager/updateDailySurveyQuestion/${result.id}/${item.id}/'>${item.optionA}</td>
</c:if><c:if test="${param.referName ne 'optionB'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionB' storedCellValue='${item.optionB}' prefix='${ownerBeanName}Manager/updateDailySurveyQuestion/${result.id}/${item.id}/'>${item.optionB}</td>
</c:if><c:if test="${param.referName ne 'optionC'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionC' storedCellValue='${item.optionC}' prefix='${ownerBeanName}Manager/updateDailySurveyQuestion/${result.id}/${item.id}/'>${item.optionC}</td>
</c:if><c:if test="${param.referName ne 'optionD'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionD' storedCellValue='${item.optionD}' prefix='${ownerBeanName}Manager/updateDailySurveyQuestion/${result.id}/${item.id}/'>${item.optionD}</td>
</c:if><c:if test="${param.referName ne 'classDailyHealthSurvey'}">
	<td class="select_candidate_td"
			data-candidate-method="./dailySurveyQuestionManager/requestCandidateClassDailyHealthSurvey/${ownerBeanName}/${item.id}/"
			data-switch-method="./dailySurveyQuestionManager/transferToAnotherClassDailyHealthSurvey/${item.id}/"
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
<c:if test="${param.referName ne 'surveyQuestion'}">
	<td class="select_candidate_td"
			data-candidate-method="./dailySurveyQuestionManager/requestCandidateSurveyQuestion/${ownerBeanName}/${item.id}/"
			data-switch-method="./dailySurveyQuestionManager/transferToAnotherSurveyQuestion/${item.id}/"
			data-link-template="./questionManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.surveyQuestion}">
			<a href='./questionManager/view/${item.surveyQuestion.id}/'>${item.surveyQuestion.displayName}</a>
			</c:if>
			<c:if test="${empty  item.surveyQuestion}">
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

				<a href='#${ownerBeanName}Manager/removeDailySurveyQuestion/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyDailySurveyQuestionFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


