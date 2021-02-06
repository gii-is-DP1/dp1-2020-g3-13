<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="usuario">

    <h2>Detalles Usuario <c:out value="${usuario.nombreUsuario}"/></h2>


    <table class="table table-striped">
        <tr>
            <th>usuario</th>
            <td><b><c:out value="${usuario.nombreUsuario}"/></b></td>
        </tr>
        <tr>
            <th>Autoridades</th>
            <td>
                <c:out value="${usuario.autoridades.autoridad} "/>
        </td>
            
        </tr>
    </table>

    <!-- <spring:url value="{adminId}/edit" var="editUrl">
        <spring:param name="adminId" value="${admin.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Administrador</a> -->


</petclinic:layout>
