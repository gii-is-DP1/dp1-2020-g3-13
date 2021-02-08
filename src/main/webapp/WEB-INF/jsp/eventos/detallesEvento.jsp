<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@page contentType="text/html"%>
                    <%@page pageEncoding="UTF-8"%>

                    <petclinic:layout pageName="evento">

                        <h2>Detalles de
                            <c:out value="${evento.nombreEvento}" />
                        </h2>

                        <table class="table table-striped">
                            <tr>
                                <th>Tipo de Evento</th>
                                <td><b><c:out value="${evento.tipoEvento}"/></b></td>
                            </tr>
                            <tr>
                                <th>Nombre del Evento</th>
                                <td>
                                    <c:out value="${evento.nombreEvento}" />
                                </td>
                            </tr>
                            <tr>
                                <th>Descripción</th>
                                <td>
                                    <c:out value="${evento.descripcion}" />
                                </td>
                            </tr>

                            <tr>
                                <th>Fecha de Inicio</th>
                                <td>
                                    <c:out value="${evento.fechaInicio}" />
                                </td>
                            </tr>
                            <tr>
                                <th>Fecha de Fin</th>
                                <td>
                                    <c:out value="${evento.fechaFin}" />
                                </td>
                            </tr>
                            <tr>
                                <th>Actividades</th>
                                <td>
                                    <div class="cuadro-entrada"></div>
                                    <c:forEach items="${actividades}" var="actividad">
                                        <p>Temática:
                                            <c:out value="${actividad.tematicaActividad} " /></p>
                                            <spring:url value="{eventoId}/actividades/{actividadId}" var="detallesActividadesUrl">
                                            <spring:param name="eventoId" value="${evento.id}" />
                                            <spring:param name="actividadId" value="${actividad.id}" />
                                            </spring:url>
                                            <a href="${fn:escapeXml(detallesActividadesUrl)}">
                                            <c:out value="Ver más" /><br></a>
                                            <c:choose>
                                                <c:when test="${not evento.esPublico}">
                                                    <spring:url value="{eventoId}/actividades/{actividadId}/editar" var="modificarActividadUrl">
                                                    <spring:param name="eventoId" value="${evento.id}" />
                                                    <spring:param name="actividadId" value="${actividad.id}" />
                                                    </spring:url>
                                                    <a href="${fn:escapeXml(modificarActividadUrl)}">
                                                    <c:out value="Modificar Actividad"/><br></a>
                                                </c:when>
                                            </c:choose>
                                            
                                        </div>

                                    </c:forEach>
                                </td>
                            <tr>
                                <th>Sponsors</th>
                                <td>
                                    <div class="cuadro-sponsor"></div>
                                    <c:forEach items="${sponsors}" var="sponsorIterado">
                                        <a href="${sponsorIterado.urlWeb}" target="_self">    
                                    <img src="${sponsorIterado.urlLogo}" width=15%></a>
                                    </div>

                                    </c:forEach>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>Tipos de entrada</th>
                                <td>
                                <div class="cuadro-entrada"></div>
                                <c:forEach items="${listaTipoEntrada}" var="tipoEntradas">
                                    <p>Tipo de entrada:
                                        <c:out value="${tipoEntradas.nombre}" />
                                    </p>
                                    <p>Precio:
                                        <c:out value="${tipoEntradas.precio} " />EUR </p>
                                    </div>
                                    <p>Fecha inicio:
                                        <c:out value="${tipoEntradas.fechaInicio}" />
                                    </p>
                                    <p>Fecha Fin:
                                        <c:out value="${tipoEntradas.fechaFin}" />
                                    </p>
                                    <p>Entradas disponibles:
                                        <c:out value="${tipoEntradas.numEntradas}" />
                                    </p>
                                </c:forEach>
                            </td>
                            </tr>
                        </table>
                                                
                        <c:choose>
                            <c:when test="${not evento.esPublico}">
                                <spring:url value="{eventoId}/actividades/nuevo" var="actividadesUrl">
                                <spring:param name="eventoId" value="${evento.id}" />
                             </spring:url>
                                <a href="${fn:escapeXml(actividadesUrl)}">
                                <c:out value="Añadir actividades" /><br></a>
                                <spring:url value="{eventoId}/sponsors/nuevo" var="sponsorUrl">
                                <spring:param name="eventoId" value="${evento.id}" />
                             </spring:url>
                                <a href="${fn:escapeXml(sponsorUrl)}">
                                <c:out value="Añadir Sponsors" /><br></a>
        
        
                                <spring:url value="{eventoId}/tipoEntradas/nuevo" var="tipoEntradasUrl">
                                <spring:param name="eventoId" value="${evento.id}" />
                                </spring:url>
                                <a href="${fn:escapeXml(tipoEntradasUrl)}">
                                <c:out value="Añadir Tipos de Entradas" /><br></a>
                                <spring:url value="/eventos/{eventoId}/delete" var="borrarEvento">
                                <spring:param name="eventoId" value="${evento.id}" />
                                </spring:url>
    
                                <a href="${fn:escapeXml(borrarEvento)}" class="btn btn-default">Eliminar evento</a>
                            </c:when>
                        </c:choose>  

                        <c:choose>
                            <c:when test="${empty actividades}">
                                     <h3 style="text-align: center; color:rgb(228, 30, 30); size:20;">No hay actividades añadidas</h3>
                            </c:when>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty actividades and not evento.esPublico and not empty listaTipoEntrada and estaPagado}">
                                <div class="publicar">    
                                <spring:url value="/eventos/{eventoId}/hacerPublico" var="volverAEvento">
                                <spring:param name="eventoId" value="${evento.id}" />
                                </spring:url>
    
                            <a href="${fn:escapeXml(volverAEvento)}" class="btn btn-default">Hacer publico</a>
                            </div>
                        </c:when>
                        </c:choose> 
                        <c:choose>
                            <c:when test="${not evento.esPublico}">
                                <div class="editarEvento">    
                                <spring:url value="/eventos/{eventoId}/editar" var="editarEvento">
                                <spring:param name="eventoId" value="${evento.id}" />
                                </spring:url>
    
                            <a href="${fn:escapeXml(editarEvento)}" class="btn btn-default">Editar Evento</a>
                            </div>
                        </c:when>
                        </c:choose>    


 




                     </petclinic:layout>