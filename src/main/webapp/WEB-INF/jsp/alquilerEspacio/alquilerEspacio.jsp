<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

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
 
                <petclinic:selectField label="Seleccione donde le gustaría reservar: " name="lugarRealizacion" size="6" names="${lugares}" onchange="miselect(lugarRealizacion)"/>

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
                    <p id="nombreRecintoSeleccionado"></p>
                </td>
                <td>
                    <p id="aforoSeleccionado"></p>
                </td>
                <td>
                    <p id="direccionSeleccionado"></p>
                </td> 
                <td>
                    <p id="emailSeleccionado"></p>
                </td>
                <td>
                    <p id="telefonoContacto"></p>
                </td>
                <td>
                    <p id="caracteristicasSeleccionado"></p>
                </td>
                <td>
                    <p id="precioSeleccionado"></p>
                </td>
            </tr>
        </table>         
            <c:forEach items="${lugares}" var="items">
                    <div style="display: none;">
                        <p class="${items.id}nombreRecintoSeleccionado"><c:out value="${items.nombre_recinto}" /></p>
                    </div>
                    <div class="${items.id}aforoSeleccionado" style="display: none;">
                        <c:out value="${items.aforo}" />
                    </div>
                    <div class="${items.id}direccionSeleccionado" style="display: none;">
                        <c:out value="${items.direccion}" />
                    </div>
                    <div class="${items.id}emailSeleccionado" style="display: none;">
                        <c:out value="${items.email}" />
                    </div>
                    <div class="${items.id}telefonoContacto" style="display: none;">
                        <c:out value="${items.telefono}" />
                    </div>
                    <div class="${items.id}caracteristicasSeleccionado" style="display: none;">
                        <c:out value="${items.caracteristicas}" />
                    </div>
                    <div class="${items.id}precioSeleccionado" style="display: none;">
                        <c:out value="${items.precio}" />
                    </div>
            </c:forEach>      

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Seleccionar Lugar</button>
                </div>
            </div>
        </form:form>


    </jsp:body>
</petclinic:layout>
