<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<petclinic:layout pageName="organizaciones">
    <h2>Organizaciones</h2>

    <table id="organizacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Organizacion</th>
            <th style="width: 200px;">Info</th>
            <th style="width: 200px;">email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${organizaciones}" var="organizacion">
            <tr>

                <td>
                    <c:out value="${organizacion.nombreOrganizacion}"/></a>
                </td>

                <td>
                    <c:out value="${organizacion.info}"/></a>
                </td>
                <td>
                    <c:out value="${organizacion.email}"/>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
