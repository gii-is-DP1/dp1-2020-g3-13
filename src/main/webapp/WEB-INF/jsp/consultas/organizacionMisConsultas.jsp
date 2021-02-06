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
                <th style="width: 200px;">Evento consultado</th>
                <th style="width: 200px;">Asunto</th>
                <th style="width: 200px;">Fecha de la consulta</th>
                <th style="width: 200px;">Opciones</th>
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
                    <c:out value="${consulta.fechaConsulta}"/>
                </td>
                <td>
                    <input type="button" class="btn btn-default" value="Detalles consulta" onclick="btn_click('${consulta.evento.nombreEvento}','${consulta.asunto}','${consulta.descripcion}','${consulta.fechaConsulta}','${consulta.cliente.email}')"></input>
                    <spring:url value="/consultas/organizacion/misConsultas/{consulta_id}" var="respuestaUrl">
                    <spring:param name="consulta_id" value="${consulta.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(respuestaUrl)}" class="btn btn-default">Responder consulta</a>

                    </div>
                </td>
                

            </tr>

           



            
        </c:forEach>
        <div style="display: none;" id="detallesTabla">
            <div class="seccionDetalles">
                <p id="tituloDetalle"></p></label>
                <p id="asuntoDetalle"></p>
                <p id="descripcionDetalle"></p>
                <p id="fechaDetalle"></p>
                <p id="emailDetalle"></p>
            </div>
        </div>
                         
        </tbody>
    </table>

</petclinic:layout>


