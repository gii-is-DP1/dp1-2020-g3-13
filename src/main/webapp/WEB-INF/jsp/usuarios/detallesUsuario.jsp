<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">

    <h2>admin Information</h2>


    <table class="table table-striped">
        <tr>
            <th>usuario</th>
            <td><b><c:out value="${usuarios.nombreUsuario}"/></b></td>
        </tr>
        <tr>
            <th>autoridades</th>
            <c:forEach items="${usuarios.autoridades}" var="autoridades">
                <c:out value="${autoridades.autoridad} "/>
            </c:forEach>
        </tr>
    </table>

    <spring:url value="{adminId}/edit" var="editUrl">
        <spring:param name="adminId" value="${admin.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Administrador</a>


</petclinic:layout>
