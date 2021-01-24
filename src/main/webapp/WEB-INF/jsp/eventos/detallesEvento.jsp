<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@ page contentType="text/html; charset=UTF-8" %>
                    

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
                                <th>Descripcion</th>
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
                                <th>Categoria</th>
                                <td>
                                    <c:out value="${evento.categoria}" />
                                </td>
                            </tr>
                            <tr>
                                <th>Medidas Sanitarias</th>
                                <td>
                                    <c:out value="${evento.medidasSanitarias}" />
                                </td>
                            </tr>
                            <tr>
                                <th>Actividades</th>
                                <td>
                                    <div class="cuadro-entrada"></div>
                                    <c:forEach items="${evento.actividades}" var="actividad">
                                        <p>Tematica:
                                            <c:out value="${actividad.tematicaActividad} " /></p>
                                            <spring:url value="{eventoId}/actividades/{actividadId}" var="detallesActividadesUrl">
                                            <spring:param name="eventoId" value="${evento.id}" />
                                            <spring:param name="actividadId" value="${actividad.id}" />
                                            </spring:url>
                                            <a href="${fn:escapeXml(detallesActividadesUrl)}">
                                            <c:out value="Ver mas" /><br></a>
                                        </div>

                                    </c:forEach>
                                </td>
                            <tr>
                                <th>Sponsors</th>
                                <td>
                                    <div class="cuadro-sponsor"></div>
                                    <c:forEach items="${evento.sponsors}" var="sponsor">
                                        <a href="${sponsor.urlWeb}" target="_self">    
                                    <img src="${sponsor.urlLogo}" width=15%></a>
                                    </div>

                                    </c:forEach>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>Tipos de entrada</th>
                                <td>
                                <div class="cuadro-entrada"></div>
                                <c:forEach items="${evento.tipoEntradas}" var="tipoEntradas">
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
                        
                        
                        <spring:url value="/eventos/{eventoId}/hacerPublico" var="volverAEvento">
                        <spring:param name="eventoId" value="${evento.id}" />
                        </spring:url>
                        <div class="publicar">
                        <c:choose>
                        <c:when test="${evento.esPublico}">
                        </c:when>
                        <c:otherwise >
                        <a href="${fn:escapeXml(volverAEvento)}" class="btn btn-default">Hacer publico</a>
                        </c:otherwise>
                        </c:choose>
                        </div>
                    </petclinic:layout>