<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

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
            <td><c:out value="${actividad.lugarRealizacion.nombre}"/></td>
        </tr>
        <tr>
            <th>Exponentes</th>
            <td>
                <div class="cuadro-entrada"></div>
                <c:forEach items="${actividad.exponentes}" var="exponente">
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
    <spring:url value="/eventos/{eventoId}/actividades/{actividadId}/alquilarEspacio" var="alquilarEspacioUrl">
        <spring:param name="eventoId" value="${evento.id}"/>
        <spring:param name="actividadId" value="${actividad.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(alquilarEspacioUrl)}" class="btn btn-default">¿Quieres reservar el espacio?</a>
    <spring:url value="/eventos/{eventoId}/actividades/{actividadId}/nuevo" var="anadirExponenteUrl">
        <spring:param name="eventoId" value="${evento.id}"/>
        <spring:param name="actividadId" value="${actividad.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(anadirExponenteUrl)}" class="btn btn-default">Añadir más exponentes</a>


</petclinic:layout>
