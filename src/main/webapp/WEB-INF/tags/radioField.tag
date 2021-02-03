<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="names" required="true" rtexprvalue="true" type="java.util.List"
              description="Names in the list" %>
<%@ attribute name="id" required="false" rtexprvalue="true"
              description="Size of Select" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>

<spring:bind path="${name}">
    <c:set var="cssGroup" value="form-group ${status.error ? 'error' : '' }"/>
    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
    <div class="${cssGroup}">

        <div class="col-sm-10">
        <label class="col-sm-2 control-label">${label}</label>

            <c:forEach items="${names}" var="items">
            <div>
             <label><c:out value="${items.tematicaActividad}+${items.id}"/></label>
                <input type="radio" id="${id}" name="${name}" value="${items.id}">
            </div>
            </c:forEach>
            <c:if test="${valid}">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            </c:if>
            <c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>
</spring:bind>