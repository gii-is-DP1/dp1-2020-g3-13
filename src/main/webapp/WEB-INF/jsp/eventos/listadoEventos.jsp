<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="eventos">
    <h2>Eventos</h2>

    <table id="eventosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Evento</th>
            <th style="width: 200px;">TipoEvento</th>
            <th style="width: 200px;">FechaInicio</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${eventos}" var="evento">
            <tr>
                <td>
                    <spring:url value="/eventos/{eventosId}" var="eventoUrl">
                    <spring:param name="eventosId" value="${evento.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(eventoUrl)}"><c:out value="${evento.nombreEvento}"/></a>    
                </td>
                <td>
                    <c:out value="${evento.tipoEvento}"/></a>
                </td>
                <td>
                    <c:out value="${evento.fechaInicio}"/>
                </td>
            </tr>
        </c:forEach>
                         
        </tbody>
    </table>

</petclinic:layout>


