<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="eventos">
    <h2>Detalles de la consulta</h2>
    <tbody>
        
        <div>
            <label for="">Nombre del evento:</label>
            <c:out value="${consulta.evento.nombreEvento}"/>
        </div>
        <div>
            <label for="">Asunto:</label>
            <c:out value="${consulta.asunto}"/>
        </div>
        <div>
            <label for="">Descripción:</label>
            <c:out value="${consulta.descripcion}"/>
        </div>
        <div>
            <label for="">Fecha:</label>
            <c:out value="${consulta.fechaConsulta}"/>
        </div>
        <div>
            <label for="">Respuesta de la consulta:</label>
            <p class="SinRespuestaConsulta"><c:out value="${consulta.respuesta}"/></p>
        </div>
        
    </tbody>

</petclinic:layout>