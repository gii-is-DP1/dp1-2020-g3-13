<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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
                                        </div>

                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <th>Tipos de entrada</th>
                                <td>
                                    <div class="cuadro-entrada"></div>
                                    <c:forEach items="${evento.tipoEntradas}" var="tipoEntradas">

                                        <h3>
                                            Tipo de entrada:
                                            <c:out value="${tipoEntradas.nombre}" />
                                        </h3>
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
                        <spring:url value="{eventoId}/actividades/nuevo" var="actividadesUrl">
                        <spring:param name="eventoId" value="${evento.id}" />
                     </spring:url>



                    </petclinic:layout>