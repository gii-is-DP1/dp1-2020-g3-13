<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <petclinic:layout pageName="carrito">

                        <h2>Detalles del carrito ;)
                        </h2>

                        <h3>¿Qué llevo en mi carrito?</h3>
                      
                            <!-- <div class="cuadro-carrito"></div> -->
                            <c:forEach items="${carrito.lineasFacturas}" var="lineasFacturas">
                                <p>
                                    Evento:
                                        <c:out value="${lineasFacturas.tipoEntrada.evento.nombreEvento}" />
                                </p>
                                <p>
                                    Asistente:
                                        <c:out value="${lineasFacturas.entrada.nombreAsistente}" />
                                </p>
                                <p>
                                   DNI:
                                        <c:out value="${lineasFacturas.entrada.dni}" />
                                </p>
                                <p>
                                  Tipo Entrada:
                                        <c:out value="${lineasFacturas.tipoEntrada.nombre}" />
                                </p>
                                <p>
                                    Fecha Inicio:
                                        <c:out value="${lineasFacturas.tipoEntrada.fechaInicio}" />
                                </p>
                                <p>
                                    Precio:
                                        <c:out value="${lineasFacturas.precio}" />
                                </p>
                                <p>
                                    Cantidad:
                                        <c:out value="${lineasFacturas.cantidad}" />
                                </p>
                            </c:forEach>
                            <p> Total: </p>
                            <c:out value="${carrito.total}" />

                            <spring:url value="/carrito/{carritoId}/finalizarCompra" var="ventaUrl">
                                            <spring:param name="carritoId" value="${carrito.id}" />
                                        </spring:url>
                                        <a href="${fn:escapeXml(ventaUrl)}">
                                            <c:out value="Comprar" /><br></a>

                    </petclinic:layout>