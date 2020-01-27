<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty studentDailyAnswerList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['student_daily_answer']}! 
		 <a href="./${ownerBeanName}Manager/addStudentDailyAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty studentDailyAnswerList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("studentDailyAnswerList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['student_daily_answer']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addStudentDailyAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'studentDailyAnswerList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${studentDailyAnswerListName};${studentDailyAnswerListName}CurrentPage=${page.pageNumber};${studentDailyAnswerListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='studentDailyAnswerListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['student_daily_answer.id']}</th>
</c:if>
<c:if test="${param.referName ne 'studentHealthSurvey'}">
	<th>${userContext.localeMap['student_daily_answer.student_health_survey']}</th>
</c:if>
<c:if test="${param.referName ne 'question'}">
	<th>${userContext.localeMap['student_daily_answer.question']}</th>
</c:if>
<c:if test="${param.referName ne 'answer'}">
	<th>${userContext.localeMap['student_daily_answer.answer']}</th>
</c:if>
<c:if test="${param.referName ne 'createTime'}">
	<th>${userContext.localeMap['student_daily_answer.create_time']}</th>
</c:if>
<c:if test="${param.referName ne 'lastUpdateTime'}">
	<th>${userContext.localeMap['student_daily_answer.last_update_time']}</th>
</c:if>
<c:if test="${param.referName ne 'cq'}">
	<th>${userContext.localeMap['student_daily_answer.cq']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${studentDailyAnswerList}">
				<tr currentVersion='${item.version}' id="studentDailyAnswer-${item.id}" ><td><a class="link-action-removed" href="./studentDailyAnswerManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'studentHealthSurvey'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentDailyAnswerManager/requestCandidateStudentHealthSurvey/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentDailyAnswerManager/transferToAnotherStudentHealthSurvey/${item.id}/"
			data-link-template="./studentHealthSurveyManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.studentHealthSurvey}">
			<a href='./studentHealthSurveyManager/view/${item.studentHealthSurvey.id}/'>${item.studentHealthSurvey.displayName}</a>
			</c:if>
			<c:if test="${empty  item.studentHealthSurvey}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'question'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentDailyAnswerManager/requestCandidateQuestion/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentDailyAnswerManager/transferToAnotherQuestion/${item.id}/"
			data-link-template="./dailySurveyQuestionManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.question}">
			<a href='./dailySurveyQuestionManager/view/${item.question.id}/'>${item.question.displayName}</a>
			</c:if>
			<c:if test="${empty  item.question}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'answer'}">	<td contenteditable='true' class='edit-value'  propertyToChange='answer' storedCellValue='${item.answer}' prefix='${ownerBeanName}Manager/updateStudentDailyAnswer/${result.id}/${item.id}/'>${item.answer}</td>
</c:if><c:if test="${param.referName ne 'createTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='createTime' storedCellValue='${item.createTime}' prefix='${ownerBeanName}Manager/updateStudentDailyAnswer/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.createTime}" /></td>
</c:if><c:if test="${param.referName ne 'lastUpdateTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='lastUpdateTime' storedCellValue='${item.lastUpdateTime}' prefix='${ownerBeanName}Manager/updateStudentDailyAnswer/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.lastUpdateTime}" /></td>
</c:if><c:if test="${param.referName ne 'cq'}">
	<td class="select_candidate_td"
			data-candidate-method="./studentDailyAnswerManager/requestCandidateCq/${ownerBeanName}/${item.id}/"
			data-switch-method="./studentDailyAnswerManager/transferToAnotherCq/${item.id}/"
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

				<a href='#${ownerBeanName}Manager/removeStudentDailyAnswer/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyStudentDailyAnswerFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


