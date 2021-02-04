<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <%@ page contentType="text/html; charset=UTF-8" %>

                    <petclinic:layout pageName="carrito">

                        <h2>Detalles del carrito ;)
                        </h2>

                        <h3>¿Qué llevo en mi carrito?</h3>

                            <!-- <div class="cuadro-carrito"></div> -->
                            <c:forEach items="${carrito.lineasFacturas}" var="lineasFacturas" varStatus="i">

                                <p>
                                    Espacio Reservado:
                                        <c:out value="${lineasFacturas.alquilerEspacio.lugarRealizacion.nombre_recinto}" />
                                </p>
                                <p>
                                    Actividad asociada:
                                        <c:out value="${actividades[i.index].tematicaActividad}"/>
                                </p>
                                <p>
                                    Fecha Inicio:
                                        <c:out value="${lineasFacturas.alquilerEspacio.fechaInicioReserva}" />
                                </p>
                                <p>
                                    Fecha Fin:
                                        <c:out value="${lineasFacturas.alquilerEspacio.fechaFinReserva}" />
                                </p>
                                <p>
                                    Precio:
                                        <c:out value="${lineasFacturas.precio}" />
                                </p>
                                <spring:url value="/carrito/organizacion/${lineasFacturas.id}/delete" var="borrarAlquilerEspacioUrl">
                                <spring:param name="lineaFacturaId" value="${lineasFacturas.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(borrarAlquilerEspacioUrl)}" class="btn btn-default">Borrar elemento</a>

                            </c:forEach>
                            <p> Total: </p>
                            <c:out value="${carrito.total}" />

                            <spring:url value="/carrito/{carritoId}/org/finalizarCompra" var="ventaUrl">
                                            <spring:param name="carritoId" value="${carrito.id}" />
                                        </spring:url>
                                        <a href="${fn:escapeXml(ventaUrl)}">
                                            <c:out value="Comprar" /><br></a>

                    </petclinic:layout>