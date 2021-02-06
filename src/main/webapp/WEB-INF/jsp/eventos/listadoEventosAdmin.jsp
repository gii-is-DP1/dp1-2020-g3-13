<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="eventos">
    <h2>Eventos</h2>

    <table id="eventosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Evento</th>
            <th style="width: 200px;">TipoEvento</th>
            <th style="width: 200px;">FechaInicio</th>
            <th style="width: 200px;">FechaFin</th>
            <th style="width: 200px;">categoria</th>
            <th style="width: 50px;"></th>
            <th style="width: 50px;"></th>
            

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
                <td>
                    <c:out value="${evento.fechaFin}"/>
                </td>
                <td>
                    <c:out value="${evento.categoria}"/>
                </td>
                <td>
                    <spring:url value="eventos/${evento.id}/edit" var="edicionUrl"></spring:url>
                        <a href="${fn:escapeXml(edicionUrl)}">
                            <img class="img-responsive" src="/resources/images/icons/editar.png" width="38%" />
                        </a>
                </td>
                <td>
                    
                    <spring:url value="eventos/delete/${evento.id}" var="borrarUrl" ></spring:url>
                        <a href="${fn:escapeXml(borrarUrl)}">
                            <img class="img-responsive" src="/resources/images/icons/borrar.jpg" width="61%" />
                        </a>
                </td>

            </tr>
        </c:forEach>
                         
        </tbody>
    </table>

</petclinic:layout>


