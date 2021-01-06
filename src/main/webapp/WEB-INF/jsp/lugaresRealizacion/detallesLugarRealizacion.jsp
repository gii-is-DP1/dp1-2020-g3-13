<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="lugaresRealizacion">

    <h2>Detalles de <c:out value="${lugarRealizacion.nombre_recinto}"/></h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${lugarRealizacion.nombre_recinto}"/></b></td>
        </tr>
        <tr>
            <th>telefono</th>
            <td><c:out value="${lugarRealizacion.telefono}"/></td>
        </tr>
        <tr>
            <th>aforo</th>
            <td><c:out value="${lugarRealizacion.aforo}"/></td>
        </tr>
        <tr>
            <th>direccion</th>
            <td><c:out value="${lugarRealizacion.direccion}"/></td>
        </tr>
        <tr>
            <th>email</th>
            <td><c:out value="${lugarRealizacion.email}"/></td>
        </tr>
        <tr>
            <th>Caracteristicas</th>
            <td><c:out value="${lugarRealizacion.caracteristicas}"/></td>
        </tr>
        <tr>
            <th>Foto</th>
            <td><img src="${lugarRealizacion.urlFoto}" ></td>
        </tr>
    </table>
    <spring:url value="${lugarRealizacion.id}/edit" var="editUrl">
        
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Lugar</a>


</petclinic:layout>
