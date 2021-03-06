<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty changeRequestTypeList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['change_request_type']}! 
		 <a href="./${ownerBeanName}Manager/addChangeRequestType/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty changeRequestTypeList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("changeRequestTypeList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['change_request_type']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addChangeRequestType/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'changeRequestTypeList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${changeRequestTypeListName};${changeRequestTypeListName}CurrentPage=${page.pageNumber};${changeRequestTypeListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='changeRequestTypeListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['change_request_type.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['change_request_type.name']}</th>
</c:if>
<c:if test="${param.referName ne 'code'}">
	<th>${userContext.localeMap['change_request_type.code']}</th>
</c:if>
<c:if test="${param.referName ne 'icon'}">
	<th>${userContext.localeMap['change_request_type.icon']}</th>
</c:if>
<c:if test="${param.referName ne 'displayOrder'}">
	<th>${userContext.localeMap['change_request_type.display_order']}</th>
</c:if>
<c:if test="${param.referName ne 'bindTypes'}">
	<th>${userContext.localeMap['change_request_type.bind_types']}</th>
</c:if>
<c:if test="${param.referName ne 'stepConfiguration'}">
	<th>${userContext.localeMap['change_request_type.step_configuration']}</th>
</c:if>
<c:if test="${param.referName ne 'platform'}">
	<th>${userContext.localeMap['change_request_type.platform']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${changeRequestTypeList}">
				<tr currentVersion='${item.version}' id="changeRequestType-${item.id}" ><td><a class="link-action-removed" href="./changeRequestTypeManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'code'}">	<td contenteditable='true' class='edit-value'  propertyToChange='code' storedCellValue='${item.code}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'>${item.code}</td>
</c:if><c:if test="${param.referName ne 'icon'}">	<td contenteditable='true' class='edit-value'  propertyToChange='icon' storedCellValue='${item.icon}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'>${item.icon}</td>
</c:if><c:if test="${param.referName ne 'displayOrder'}">	<td contenteditable='true' class='edit-value'  propertyToChange='displayOrder' storedCellValue='${item.displayOrder}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'>${item.displayOrder}</td>
</c:if><c:if test="${param.referName ne 'bindTypes'}">	<td contenteditable='true' class='edit-value'  propertyToChange='bindTypes' storedCellValue='${item.bindTypes}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'><a title='${item.bindTypes}'>${fn:substring(item.bindTypes,0,10)}...</a></td>
</c:if><c:if test="${param.referName ne 'stepConfiguration'}">	<td contenteditable='true' class='edit-value'  propertyToChange='stepConfiguration' storedCellValue='${item.stepConfiguration}' prefix='${ownerBeanName}Manager/updateChangeRequestType/${result.id}/${item.id}/'><a title='${item.stepConfiguration}'>${fn:substring(item.stepConfiguration,0,10)}...</a></td>
</c:if><c:if test="${param.referName ne 'platform'}">
	<td class="select_candidate_td"
			data-candidate-method="./changeRequestTypeManager/requestCandidatePlatform/${ownerBeanName}/${item.id}/"
			data-switch-method="./changeRequestTypeManager/transferToAnotherPlatform/${item.id}/"
			data-link-template="./platformManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.platform}">
			<a href='./platformManager/view/${item.platform.id}/'>${item.platform.displayName}</a>
			</c:if>
			<c:if test="${empty  item.platform}">
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

				<a href='#${ownerBeanName}Manager/removeChangeRequestType/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyChangeRequestTypeFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


