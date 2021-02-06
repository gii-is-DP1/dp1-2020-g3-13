<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="admins">

    <h2>admin Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${admin.nombre} ${admin.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${admin.email}"/></td>
        </tr>
        <tr>
            <th>Usuario</th>
            <td><c:out value="${admin.usuario.nombreUsuario}"/></td>
        </tr>
    </table>

    <spring:url value="{adminId}/edit" var="editUrl">
        <spring:param name="adminId" value="${admin.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Administrador</a>


</petclinic:layout>
    