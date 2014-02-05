<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup"/>

<div class="span2">
    <h2><fmt:message key="signup.heading"/></h2>
    <p><fmt:message key="signup.message"/></p>
</div>
<div class="span7">
    

    <form:form commandName="CustomerAd" method="post" action="${ctx}/user/postCustomerAds" id="postCustomerAd" autocomplete="off"
               cssClass="well form-horizontal" onsubmit="return validateUser(this)">
       
        <fieldset class="control-group">
       
            <appfuse:label styleClass="control-label" key="ad.source"/>
            <div class="controls">
                <form:input path="source" id="source"/>
                <form:errors path="source" cssClass="help-inline"/>
            </div>
        </fieldset>
       
        <fieldset class="control-group">
      
            <appfuse:label styleClass="control-label" key="ad.destination"/>
            <div class="controls">
                <form:password path="destination" id="destination" showPassword="true"/>
                <form:errors path="destination" cssClass="help-inline"/>
            </div>
        </fieldset>
      
        <fieldset class="control-group">
       
            <appfuse:label styleClass="control-label" key="ad.weight"/>
            <div class="controls">
                <form:input path="weight" id="weight"/>
                <form:errors path="weight" cssClass="help-inline"/>
            </div>
        </fieldset>
       
        <fieldset class="control-group">
       
            <appfuse:label styleClass="control-label" key="ad.description"/>
            <div class="controls">
                <form:input path="description" id="description" maxlength="50"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </fieldset>
        
        <fieldset class="form-actions">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.register"/>
            </button>
            <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </fieldset>
    </form:form>
</div>

<c:set var="scripts" scope="request">
<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['signupForm']).focus();
    });
</script>
</c:set>