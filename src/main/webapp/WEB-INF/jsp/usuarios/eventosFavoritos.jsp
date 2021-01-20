<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="eventosFavoritosUsuario">

    <h2>Aqui estan tus eventos Favoritos! ;) <c:out value="${usuario.nombreUsuario}"/></h2>


    <table class="table table-striped">

       <c:forEach items="${cliente.eventosFavoritos}" var="eventos">
            <h3>Nombre del Evento:
            </h3>
            <c:out value="${eventos.nombreEvento}" />
            <h3>Fecha de Inicio:</h3>
                <c:out value="${eventos.fechaInicio} " />
            <h3>Fecha de Fin:</h3>
            <c:out value="${eventos.fechaFin}" />
            <spring:url value="/usuarios/myprofile/eventosFavoritos/{eventosFavId}/borrar" var="borrarFavUrl">
                    <spring:param name="eventosFavId" value="${cliente.eventosFavoritos.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(borrarFavUrl)}" class="btn btn-default">Borrar Evento</a>
            
            </c:forEach>
    </table>

    <!-- <spring:url value="{adminId}/edit" var="editUrl">
        <spring:param name="adminId" value="${admin.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Administrador</a> -->


</petclinic:layout>
