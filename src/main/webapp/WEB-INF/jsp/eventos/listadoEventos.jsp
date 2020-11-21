<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="eventos">
    <h2>Organizaciones</h2>

    <table id="eventosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Eventos</th>
            <th style="width: 200px;">Tipo Evento</th>
            <th style="width: 200px;">fechaInicio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${eventos}" var="evento">
            <tr>
                <td>

                    <c:out value="${evento.nombreEvento}"/></a>
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