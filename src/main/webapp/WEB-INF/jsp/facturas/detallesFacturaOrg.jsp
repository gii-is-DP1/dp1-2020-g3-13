<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Tus Facturas</h2>

    <table id="facturasTable" class="table table-striped">
        <p>Detalles de Factura</p>
        <thead>        
        <tr>
            <th style="width: 150px;">Alquiler Espacio</th>
            <th style="width: 200px;">Fecha Inicio Reserva</th>
            <th style="width: 200px;">Fecha Fin Reserva</th>
            <th style="width: 200px;">Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lineaFacturas}" var="lineaFactura">
            <tr>
                <td>

                    <c:out value="${lineaFactura.alquilerEspacio.lugarRealizacion.nombre_recinto}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.alquilerEspacio.fechaInicioReserva}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.alquilerEspacio.fechaFinReserva}" />

                </td>
                <td>

                    <c:out value="${lineaFactura.precio}" />

                </td>
                                  
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/facturas" var="facturasUrl">
                    </spring:url>
                    <a href="${fn:escapeXml(facturasUrl)}" class="btn btn-default"><c:out value="Volver"/></a>
</petclinic:layout>
