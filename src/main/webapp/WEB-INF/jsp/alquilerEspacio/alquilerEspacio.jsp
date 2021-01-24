<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

    <!-- TODO -->
<petclinic:layout pageName="alquilarlugarSeleccionado">
    <jsp:body>
        <h2>
           ¿Quiere alquilar un espacio para su actividad?
        </h2>
        <form:form modelAttribute="alquilerEspacio" class="form-horizontal">
            <div class="form-group has-feedback">
                <div class="form-group">
                    <div class="col-sm-10">
                    </div>
                </div>
 
                <petclinic:selectField label="Seleccione donde le gustaría reservar: " name="lugarRealizacion" size="6" names="${lugares}"/>

            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Seleccionar Lugar</button>
                </div>
        </form:form>
        

    </div>
        <!--Terminar !!!! -->
        <table id="detallerLugarTabla" class="table table-striped">
            <tr>
                <th style="width: 150px;">Nombre Recinto</th>
                <th style="width: 200px;">Aforo</th>
                <th style="width: 200px;">Dirección</th>
                <th style="width: 200px;">Email de contacto</th>
                <th style="width: 200px;">Teléfono de contacto</th>
                <th style="width: 200px;">Características</th>
                <th style="width: 200px;">Precio/Dia</th>
            </tr>
                <tr>
                    <td>

                        <c:out value="${lugarSeleccionado.nombre_recinto}" />

                    </td>
                    <td>

                        <c:out value="${lugarSeleccionado.aforo}" />

                    </td>
                    <td>

                        <c:out value="${lugarSeleccionado.direccion}" />

                    </td> 
                    <td>

                        <c:out value="${lugarSeleccionado.email}" />

                    </td>
                    <td>

                        <c:out value="${lugarSeleccionado.telefono}" />

                    </td>
                    <td>

                        <c:out value="${lugarSeleccionado.caracteristicas}" />

                    </td>
                    <td>

                        <c:out value="${lugarSeleccionado.precio}" />

                    </td>
                </tr>
                    <spring:url value="/eventos/{evento_id}/actividades/{actividadId}/confirmar" var="confirmarUrl">
                    <spring:param name="evento_id" value="${evento.id}"/>
                    <spring:param name="actividadId" value="${actividad.id}"/>
                    <c:out value="${alquiler.id}"></c:out>
                    </spring:url>
                        <a href="${fn:escapeXml(confirmarUrl)}" class="btn btn-default">Confirmar Lugar y Añadir a Carrito</a>
        </table>




    </jsp:body>
</petclinic:layout>
