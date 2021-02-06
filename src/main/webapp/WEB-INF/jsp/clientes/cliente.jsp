<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Usuario</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 200px;">email</th>
            <th style="width: 200px;">telefono</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="clientes">
            <tr>
                <td>

                    <c:out value="${clientes.usuario.nombreUsuario}"/></a>
                </td>
                <td>
                    <c:out value="${clientes.nombre} ${clientes.apellidos}"/></a>
                </td>
                <td>
                    <c:out value="${clientes.email}"/>

                </td>

                <td>
                    <c:out value="${clientes.telefono}"/>

                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>