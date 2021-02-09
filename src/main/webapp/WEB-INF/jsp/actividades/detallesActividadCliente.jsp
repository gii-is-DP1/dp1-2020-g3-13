<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="detallesActividad">

    <h2>Detalles de <c:out value="${actividad.tematicaActividad}"/></h2>
    <table class="table table-striped">
        <tr>
            <th>Descripción</th>
            <td><b><c:out value="${actividad.descripcionActividad}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de Inicio</th>
            <td><c:out value="${actividad.fechaInicio}"/></td>
        </tr>
        <tr>
            <th>Fecha de Fin</th>
            <td><c:out value="${actividad.fechaFin}"/></td>
        </tr>
        <tr>
            <th>¿Dónde se va a realizar?</th>
            <td><c:out value="${actividad.alquilerEspacio.lugarRealizacion.nombre_recinto}"/></td>
        </tr>
        <tr>
            <th>Exponentes</th>
            <td>
                <div class="cuadro-entrada"></div>
                <c:forEach items="${exponentes}" var="exponente">
                    <p>Nombre:
                        <c:out value="${exponente.nombreExponente} " /></p>
                    <p>Apellidos:
                        <c:out value="${exponente.apellidosExponente} " /></p>
                    <p>Alias: "<c:out value="${exponente.alias} " />"</p>
                        
                    </div>

                </c:forEach>
            </td>
        </tr>
       
    </table>
<spring:url value="/eventos/{evento_id}" var="volverUrl">
<spring:param name="evento_id" value="${evento.id}" />
</spring:url>
<a href="${fn:escapeXml(volverUrl)}" class="btn btn-default"><c:out value="Volver"/></a>        


</petclinic:layout>
