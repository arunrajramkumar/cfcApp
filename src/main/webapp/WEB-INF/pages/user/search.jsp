<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>
<h2><fmt:message key="searchUser.heading"/></h2>

<br/>
<div class="span5">
    

    <form:form method="POST" action="${ctx}/user/search" id="searchForm" class="form-search" commandName="CFCUser">
    <div id="search" class="input-append">
        
        <fieldset class="control-group">
            <appfuse:label styleClass="control-label" key="user.type"/>
            <div class="controls">
                <form:select path="userType" id="userType">
                <form:option value="">
										<fmt:message key="selectAny.title" />
									</form:option>
									<c:forEach items="${userTypeList}" var="userType"
										varStatus="status">
										
										<form:option value='${userType}'>
											${userType}
										</form:option>
										
									</c:forEach>
								</form:select>
            </div>
        </fieldset>
        
        
        <fieldset class="control-group">
            <appfuse:label styleClass="control-label" key="user.name"/>
            <div class="controls">
                <form:input path="username" id="username"/>
            </div>
        </fieldset>
        
   
    <div id="actions" class="form-actions">
         <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>

        <a class="btn" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
     </div>
    </form:form>

    

 
</div>
<div class="span8">   
     <display:table name="${usersLst}" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="username" escapeXml="true" sortable="true" titleKey="user.username" style="width: 25%"
                        url="/userform?from=list" paramId="id" paramProperty="id"/>
        <display:column property="fullName" escapeXml="true" sortable="true" titleKey="activeUsers.fullName"
                        style="width: 34%"/>
        <display:column property="email" sortable="true" titleKey="user.email" style="width: 25%" autolink="true"
                        media="html"/>
        <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="user.enabled"
                        style="width: 16%; padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="user.enabled" media="csv xml excel pdf"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="usersLst.user"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="usersLst.users"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="User List.xls"/>
        <display:setProperty name="export.csv.filename" value="User List.csv"/>
        <display:setProperty name="export.pdf.filename" value="User List.pdf"/>
    </display:table>
	</div>
