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
                                    <spring:url value="/eventos/{eventoId}/{tipoEntradaId}/entrada" var="ventaUrl">
                                            <spring:param name="eventoId" value="${evento.id}" />
                                            <spring:param name="tipoEntradaId" value="${tipoEntradas.id}" />
                                        </spring:url>
                                        <a href="${fn:escapeXml(ventaUrl)}">
                                            <c:out value="Comprar" /><br></a>
                            
                                

                                </c:forEach>
                                
     
                            </td>

                            </tr>
                        </table>
                        <form action="favoritos" method="POST">
                            <spring:url value="/eventos/{eventoId}/anadirEventosFavoritos" var="favoritosUrl">
                            <spring:param name="eventoId" value="${evento.id}" />
                            </spring:url>
                            <a href="${fn:escapeXml(favoritosUrl)}">
                            <c:out value="Añadir a favoritos" /><br></a>
                            </form>

                            <spring:url value="/eventos" var="eventosUrl">
                        </spring:url>
                        <a href="${fn:escapeXml(eventosUrl)}" class="btn btn-default"><c:out value="Volver"/></a>
                     </petclinic:layout>