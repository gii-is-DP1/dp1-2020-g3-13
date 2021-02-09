<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="lugaresRealizacion">
    <h2>Lugares de realizacion de eventos</h2>

    <table id="lugaresRealizacionTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Dirección</th>
            <th style="width: 200px;">Aforo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lugaresRealizacion}" var="lugaresRealizacion">
            <tr>
                <td>
                    <spring:url value="/lugaresRealizacion/{lugarId}" var="lugarUrl">
                    <spring:param name="lugarId" value="${lugaresRealizacion.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(lugarUrl)}"><c:out value="${lugaresRealizacion.nombre_recinto}"/></a>
                </td>
                <td>
                    <c:out value="${lugaresRealizacion.direccion}"/></a>
                </td>
                <td>
                    <c:out value="${lugaresRealizacion.aforo}"/>
                </td>
                
            </tr>
        </c:forEach>
        <spring:url value="/lugaresRealizacion/nuevo" var="newUrl">
    </spring:url>
    <a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Añadir lugar</a>
        </tbody>
    </table>
</petclinic:layout>
