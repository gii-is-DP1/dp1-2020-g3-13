<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form"%>  
                                <%@page pageEncoding="UTF-8"%>

            <petclinic:layout pageName="error">
                <spring:url value="/resources/images/pets.png" var="petsImage" />
                <img src="${petsImage}" />
                <h2>There is no page here!</h2>
                <p>We're sorry, but the page you requested is not available in Petclinic.</p>
            </petclinic:layout>