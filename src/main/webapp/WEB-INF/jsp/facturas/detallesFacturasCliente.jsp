<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Tus Facturas</h2>

    <table id="facturasTable" class="table table-striped">
        <p>Detalles de Entradas</p>
        <thead>        
        <tr>
            <th style="width: 150px;">Entrada</th>
            <th style="width: 200px;">DNI</th>
            <th style="width: 200px;">Nombre Asistente</th>
            <th style="width: 200px;">Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lineaFacturas}" var="lineaFactura">
            <tr>
                <td>

                    <c:out value="${lineaFactura.entrada.tipoEntrada.nombre}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.entrada.dni}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.entrada.nombreAsistente}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.precio}" />

                </td>
                          
            </tr>
        </c:forEach>
        <td>
            <spring:url value="/facturas" var="facturasUrl">
            </spring:url>
            <a href="${fn:escapeXml(facturasUrl)}" class="btn btn-default"><c:out value="Volver"/></a>
        </td>
        </tbody>
    </table>
</petclinic:layout>
