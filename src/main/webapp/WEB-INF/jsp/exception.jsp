<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
        <%@page pageEncoding="UTF-8"%>

            <petclinic:layout pageName="error">

                <spring:url value="/resources/images/errorimage.png" var="errorImage" />
                <img width="20%" src="${errorImage}" />
                <br>
                <br>
                <br>
                <br>

                <h2>Algo ha salido mal...</h2>

                <p>${exception.message}</p>

            </petclinic:layout>