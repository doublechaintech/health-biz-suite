<%@ page import='java.util.*,com.doublechaintech.health.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty wechatLoginInfoList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['wechat_login_info']}! 
		 <a href="./${ownerBeanName}Manager/addWechatLoginInfo/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty wechatLoginInfoList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("wechatLoginInfoList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['wechat_login_info']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addWechatLoginInfo/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'wechatLoginInfoList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${wechatLoginInfoListName};${wechatLoginInfoListName}CurrentPage=${page.pageNumber};${wechatLoginInfoListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='wechatLoginInfoListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['wechat_login_info.id']}</th>
</c:if>
<c:if test="${param.referName ne 'user'}">
	<th>${userContext.localeMap['wechat_login_info.user']}</th>
</c:if>
<c:if test="${param.referName ne 'appId'}">
	<th>${userContext.localeMap['wechat_login_info.app_id']}</th>
</c:if>
<c:if test="${param.referName ne 'openId'}">
	<th>${userContext.localeMap['wechat_login_info.open_id']}</th>
</c:if>
<c:if test="${param.referName ne 'sessionKey'}">
	<th>${userContext.localeMap['wechat_login_info.session_key']}</th>
</c:if>
<c:if test="${param.referName ne 'lastUpdateTime'}">
	<th>${userContext.localeMap['wechat_login_info.last_update_time']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${wechatLoginInfoList}">
				<tr currentVersion='${item.version}' id="wechatLoginInfo-${item.id}" ><td><a class="link-action-removed" href="./wechatLoginInfoManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'user'}">
	<td class="select_candidate_td"
			data-candidate-method="./wechatLoginInfoManager/requestCandidateUser/${ownerBeanName}/${item.id}/"
			data-switch-method="./wechatLoginInfoManager/transferToAnotherUser/${item.id}/"
			data-link-template="./userManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.user}">
			<a href='./userManager/view/${item.user.id}/'>${item.user.displayName}</a>
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
<c:if test="${param.referName ne 'appId'}">	<td contenteditable='true' class='edit-value'  propertyToChange='appId' storedCellValue='${item.appId}' prefix='${ownerBeanName}Manager/updateWechatLoginInfo/${result.id}/${item.id}/'>${item.appId}</td>
</c:if><c:if test="${param.referName ne 'openId'}">	<td contenteditable='true' class='edit-value'  propertyToChange='openId' storedCellValue='${item.openId}' prefix='${ownerBeanName}Manager/updateWechatLoginInfo/${result.id}/${item.id}/'>${item.openId}</td>
</c:if><c:if test="${param.referName ne 'sessionKey'}">	<td contenteditable='true' class='edit-value'  propertyToChange='sessionKey' storedCellValue='${item.sessionKey}' prefix='${ownerBeanName}Manager/updateWechatLoginInfo/${result.id}/${item.id}/'>${item.sessionKey}</td>
</c:if><c:if test="${param.referName ne 'lastUpdateTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='lastUpdateTime' storedCellValue='${item.lastUpdateTime}' prefix='${ownerBeanName}Manager/updateWechatLoginInfo/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.lastUpdateTime}" /></td>
</c:if>
				<td>

				<a href='#${ownerBeanName}Manager/removeWechatLoginInfo/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyWechatLoginInfoFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


