<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="eventos">
    <h2>Eventos</h2>

    <table id="eventosTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 200px;">Evento consultado</th>
                <th style="width: 200px;">Asunto</th>
                <th style="width: 200px;">Descripcion</th>
                <th style="width: 200px;">Fecha de la consulta</th>
                <th style="width: 200px;">Email a comunicar</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${consultas}" var="consulta">
            <tr>
                <td>
                    <c:out value="${consulta.evento.nombreEvento}"/>
                </td>
                <td>
                    <c:out value="${consulta.asunto}"/>
                </td>
                <td>
                    <c:out value="${consulta.descripcion}"/>
                </td>
                <td>
                    <c:out value="${consulta.fechaConsulta}"/>
                </td>
                <td>
                    <c:out value="${consulta.cliente.email}"/>
                </td>
            </tr>

           



            
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>


