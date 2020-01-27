<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty classQuestionList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['class_question']}! 
		 <a href="./${ownerBeanName}Manager/addClassQuestion/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty classQuestionList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("classQuestionList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['class_question']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addClassQuestion/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'classQuestionList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${classQuestionListName};${classQuestionListName}CurrentPage=${page.pageNumber};${classQuestionListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='classQuestionListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['class_question.id']}</th>
</c:if>
<c:if test="${param.referName ne 'topic'}">
	<th>${userContext.localeMap['class_question.topic']}</th>
</c:if>
<c:if test="${param.referName ne 'questionType'}">
	<th>${userContext.localeMap['class_question.question_type']}</th>
</c:if>
<c:if test="${param.referName ne 'optionA'}">
	<th>${userContext.localeMap['class_question.option_a']}</th>
</c:if>
<c:if test="${param.referName ne 'optionB'}">
	<th>${userContext.localeMap['class_question.option_b']}</th>
</c:if>
<c:if test="${param.referName ne 'optionC'}">
	<th>${userContext.localeMap['class_question.option_c']}</th>
</c:if>
<c:if test="${param.referName ne 'optionD'}">
	<th>${userContext.localeMap['class_question.option_d']}</th>
</c:if>
<c:if test="${param.referName ne 'questionSource'}">
	<th>${userContext.localeMap['class_question.question_source']}</th>
</c:if>
<c:if test="${param.referName ne 'creator'}">
	<th>${userContext.localeMap['class_question.creator']}</th>
</c:if>
<c:if test="${param.referName ne 'cq'}">
	<th>${userContext.localeMap['class_question.cq']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${classQuestionList}">
				<tr currentVersion='${item.version}' id="classQuestion-${item.id}" ><td><a class="link-action-removed" href="./classQuestionManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'topic'}">	<td contenteditable='true' class='edit-value'  propertyToChange='topic' storedCellValue='${item.topic}' prefix='${ownerBeanName}Manager/updateClassQuestion/${result.id}/${item.id}/'>${item.topic}</td>
</c:if><c:if test="${param.referName ne 'questionType'}">
	<td class="select_candidate_td"
			data-candidate-method="./classQuestionManager/requestCandidateQuestionType/${ownerBeanName}/${item.id}/"
			data-switch-method="./classQuestionManager/transferToAnotherQuestionType/${item.id}/"
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
<c:if test="${param.referName ne 'optionA'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionA' storedCellValue='${item.optionA}' prefix='${ownerBeanName}Manager/updateClassQuestion/${result.id}/${item.id}/'>${item.optionA}</td>
</c:if><c:if test="${param.referName ne 'optionB'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionB' storedCellValue='${item.optionB}' prefix='${ownerBeanName}Manager/updateClassQuestion/${result.id}/${item.id}/'>${item.optionB}</td>
</c:if><c:if test="${param.referName ne 'optionC'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionC' storedCellValue='${item.optionC}' prefix='${ownerBeanName}Manager/updateClassQuestion/${result.id}/${item.id}/'>${item.optionC}</td>
</c:if><c:if test="${param.referName ne 'optionD'}">	<td contenteditable='true' class='edit-value'  propertyToChange='optionD' storedCellValue='${item.optionD}' prefix='${ownerBeanName}Manager/updateClassQuestion/${result.id}/${item.id}/'>${item.optionD}</td>
</c:if><c:if test="${param.referName ne 'questionSource'}">
	<td class="select_candidate_td"
			data-candidate-method="./classQuestionManager/requestCandidateQuestionSource/${ownerBeanName}/${item.id}/"
			data-switch-method="./classQuestionManager/transferToAnotherQuestionSource/${item.id}/"
			data-link-template="./questionSourceManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.questionSource}">
			<a href='./questionSourceManager/view/${item.questionSource.id}/'>${item.questionSource.displayName}</a>
			</c:if>
			<c:if test="${empty  item.questionSource}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'creator'}">
	<td class="select_candidate_td"
			data-candidate-method="./classQuestionManager/requestCandidateCreator/${ownerBeanName}/${item.id}/"
			data-switch-method="./classQuestionManager/transferToAnotherCreator/${item.id}/"
			data-link-template="./wechatUserManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.creator}">
			<a href='./wechatUserManager/view/${item.creator.id}/'>${item.creator.displayName}</a>
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
<c:if test="${param.referName ne 'cq'}">
	<td class="select_candidate_td"
			data-candidate-method="./classQuestionManager/requestCandidateCq/${ownerBeanName}/${item.id}/"
			data-switch-method="./classQuestionManager/transferToAnotherCq/${item.id}/"
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

				<a href='#${ownerBeanName}Manager/removeClassQuestion/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyClassQuestionFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


