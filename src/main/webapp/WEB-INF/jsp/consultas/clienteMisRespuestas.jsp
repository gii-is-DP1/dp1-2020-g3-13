<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="eventos">
    <h2>Eventos</h2>
    <tbody>
            
            <div>
            <c:out value="${consulta.evento.nombreEvento}"/>
            </div>
            <div>
            <c:out value="${consulta.asunto}"/>
            </div>
            <div>
        
            <c:out value="${consulta.descripcion}"/>
            </div>
            <div>
            <c:out value="${consulta.fechaConsulta}"/>
            </div>
            <div>
            <c:out value="${consulta.respuesta}"/>
            </div>

        
    </tbody>

</petclinic:layout>