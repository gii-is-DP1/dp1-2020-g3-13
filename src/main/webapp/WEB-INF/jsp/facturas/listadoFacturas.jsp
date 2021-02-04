<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Tus Facturas</h2>

    <table id="facturasTable" class="table table-striped">
        <p>Numero de facturas: <c:out value="${facturas.size()} "/></p>
        <thead>
        
        <tr>
            <th style="width: 150px;">Num. Factura</th>
            <th style="width: 200px;">Fecha de la Factura</th>
            <th style="width: 200px;">PDF</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${facturas}" var="facturas">
            <tr>
                <td>

                    <c:out value="${facturas.id}" />

                </td>
                <td>

                    <c:out value="${facturas.fechaFactura}" />

                </td>
                <td>
                    <spring:url value="/facturas" var="facturasUrl">
                    <spring:param name="facturasId" value="${facturas.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(facturasUrl)}"><c:out value="Descargar PDF"/></a>
                </td>              
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
