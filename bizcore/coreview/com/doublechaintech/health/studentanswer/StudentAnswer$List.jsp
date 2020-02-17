<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty studentAnswerList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['student_answer']}! 
		 <a href="./${ownerBeanName}Manager/addStudentAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty studentAnswerList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("studentAnswerList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['student_answer']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addStudentAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'studentAnswerList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${studentAnswerListName};${studentAnswerListName}CurrentPage=${page.pageNumber};${studentAnswerListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='studentAnswerListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['student_answer.id']}</th>
</c:if>
<c:if test="${param.referName ne 'healthSurveyReport'}">
	<th>${userContext.localeMap['student_answer.health_survey_report']}</th>
</c:if>
<c:if test="${param.referName ne 'dailyAnswer'}">
	<th>${userContext.localeMap['student_answer.daily_answer']}</th>
</c:if>
<c:if test="${param.referName ne 'questionTopic'}">
	<th>${userContext.localeMap['student_answer.question_topic']}</th>
</c:if>
<c:if test="${param.referName ne 'answer'}">
	<th>${userContext.localeMap['student_answer.answer']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${studentAnswerList}">
				<tr currentVersion='${item.version}' id="studentAnswer-${item.id}" ><td><a class="link-action-removed" href="./studentAnswerManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'healthSurveyReport'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentAnswerManager/requestCandidateHealthSurveyReport/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentAnswerManager/transferToAnotherHealthSurveyReport/${item.id}/"
			data-link-template="./healthSurveyReportManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.healthSurveyReport}">
			<a href='./healthSurveyReportManager/view/${item.healthSurveyReport.id}/'>${item.healthSurveyReport.displayName}</a>
			</c:if>
			<c:if test="${empty  item.healthSurveyReport}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'dailyAnswer'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentAnswerManager/requestCandidateDailyAnswer/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentAnswerManager/transferToAnotherDailyAnswer/${item.id}/"
			data-link-template="./studentDailyAnswerManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.dailyAnswer}">
			<a href='./studentDailyAnswerManager/view/${item.dailyAnswer.id}/'>${item.dailyAnswer.displayName}</a>
			</c:if>
			<c:if test="${empty  item.dailyAnswer}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'questionTopic'}">	<td contenteditable='true' class='edit-value'  propertyToChange='questionTopic' storedCellValue='${item.questionTopic}' prefix='${ownerBeanName}Manager/updateStudentAnswer/${result.id}/${item.id}/'>${item.questionTopic}</td>
</c:if><c:if test="${param.referName ne 'answer'}">	<td contenteditable='true' class='edit-value'  propertyToChange='answer' storedCellValue='${item.answer}' prefix='${ownerBeanName}Manager/updateStudentAnswer/${result.id}/${item.id}/'>${item.answer}</td>
</c:if>
				<td>

				<a href='#${ownerBeanName}Manager/removeStudentAnswer/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyStudentAnswerFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


