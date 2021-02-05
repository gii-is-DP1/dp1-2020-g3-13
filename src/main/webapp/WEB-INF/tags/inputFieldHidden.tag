<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>
<%@ attribute name="id" required="false" rtexprvalue="true"
              description="id for javascript use" %>
<%@ attribute name="value" required="false" rtexprvalue="true"
              description="value of the input" %>


<spring:bind path="${name}">
    <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
    <div class="${cssGroup}">

        <div class="col-sm-10">
            <form:input type="hidden" id="${id}" class="form-control" path="${name}" value="${value}"/>

        </div>
    </div>
</spring:bind>
