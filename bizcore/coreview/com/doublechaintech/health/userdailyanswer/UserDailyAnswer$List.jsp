<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty userDailyAnswerList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['user_daily_answer']}! 
		 <a href="./${ownerBeanName}Manager/addUserDailyAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty userDailyAnswerList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("userDailyAnswerList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['user_daily_answer']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addUserDailyAnswer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'userDailyAnswerList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${userDailyAnswerListName};${userDailyAnswerListName}CurrentPage=${page.pageNumber};${userDailyAnswerListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='userDailyAnswerListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['user_daily_answer.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['user_daily_answer.name']}</th>
</c:if>
<c:if test="${param.referName ne 'user'}">
	<th>${userContext.localeMap['user_daily_answer.user']}</th>
</c:if>
<c:if test="${param.referName ne 'answerTime'}">
	<th>${userContext.localeMap['user_daily_answer.answer_time']}</th>
</c:if>
<c:if test="${param.referName ne 'isAnswerSubmitted'}">
	<th>${userContext.localeMap['user_daily_answer.is_answer_submitted']}</th>
</c:if>
<c:if test="${param.referName ne 'organization'}">
	<th>${userContext.localeMap['user_daily_answer.organization']}</th>
</c:if>
<c:if test="${param.referName ne 'dailyHealthSurvey'}">
	<th>${userContext.localeMap['user_daily_answer.daily_health_survey']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${userDailyAnswerList}">
				<tr currentVersion='${item.version}' id="userDailyAnswer-${item.id}" ><td><a class="link-action-removed" href="./userDailyAnswerManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateUserDailyAnswer/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'user'}">
	<td class="select_candidate_td"
			data-candidate-method="./userDailyAnswerManager/requestCandidateUser/${ownerBeanName}/${item.id}/"
			data-switch-method="./userDailyAnswerManager/transferToAnotherUser/${item.id}/"
			data-link-template="./wechatUserManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.user}">
			<a href='./wechatUserManager/view/${item.user.id}/'>${item.user.displayName}</a>
			</c:if>
			<c:if test="${empty  item.user}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'answerTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='answerTime' storedCellValue='${item.answerTime}' prefix='${ownerBeanName}Manager/updateUserDailyAnswer/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.answerTime}" /></td>
</c:if><c:if test="${param.referName ne 'isAnswerSubmitted'}">	<td contenteditable='true' class='edit-value'  propertyToChange='isAnswerSubmitted' storedCellValue='${item.isAnswerSubmitted}' prefix='${ownerBeanName}Manager/updateUserDailyAnswer/${result.id}/${item.id}/'>${item.isAnswerSubmitted}</td>
</c:if><c:if test="${param.referName ne 'organization'}">
	<td class="select_candidate_td"
			data-candidate-method="./userDailyAnswerManager/requestCandidateOrganization/${ownerBeanName}/${item.id}/"
			data-switch-method="./userDailyAnswerManager/transferToAnotherOrganization/${item.id}/"
			data-link-template="./organizationManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.organization}">
			<a href='./organizationManager/view/${item.organization.id}/'>${item.organization.displayName}</a>
			</c:if>
			<c:if test="${empty  item.organization}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'dailyHealthSurvey'}">
	<td class="select_candidate_td"
			data-candidate-method="./userDailyAnswerManager/requestCandidateDailyHealthSurvey/${ownerBeanName}/${item.id}/"
			data-switch-method="./userDailyAnswerManager/transferToAnotherDailyHealthSurvey/${item.id}/"
			data-link-template="./dailyHealthSurveyManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.dailyHealthSurvey}">
			<a href='./dailyHealthSurveyManager/view/${item.dailyHealthSurvey.id}/'>${item.dailyHealthSurvey.displayName}</a>
			</c:if>
			<c:if test="${empty  item.dailyHealthSurvey}">
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

				<a href='#${ownerBeanName}Manager/removeUserDailyAnswer/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyUserDailyAnswerFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


