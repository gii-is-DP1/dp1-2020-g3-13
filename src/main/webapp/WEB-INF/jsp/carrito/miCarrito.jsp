<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <%@page pageEncoding="UTF-8"%>

                    <petclinic:layout pageName="carrito">

                        <h2>Detalles del carrito
                        </h2>

                        <h3>¿Qué llevo en mi carrito?</h3>
                      
                            <!-- <div class="cuadro-carrito"></div> -->
                            <c:forEach items="${lineasFactura}" var="lineasFacturas">
                                <p>
                                    Evento:
                                        <c:out value="${lineasFacturas.entrada.tipoEntrada.evento.nombreEvento}" />
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
                                        <c:out value="${lineasFacturas.entrada.tipoEntrada.nombre}" />
                                </p>
                                <p>
                                    Fecha Inicio:
                                        <c:out value="${lineasFacturas.entrada.tipoEntrada.fechaInicio}" />
                                </p>
                                <p>
                                    Precio:
                                        <c:out value="${lineasFacturas.precio}" />
                                </p>
                                <p>
                                    Cantidad:
                                        <c:out value="${lineasFacturas.cantidad}" />
                                </p>
                                <spring:url value="/carrito/cliente/{lineaFacturaId}/delete" var="borrarUrl">
                                            <spring:param name="lineaFacturaId" value="${lineasFacturas.id}" />
                                        </spring:url>
                                        <a class="btn btn-default" href="${fn:escapeXml(borrarUrl)}">
                                            <c:out value="Borrar" /><br></a>
                            </c:forEach>
                            <p> Total: </p>
                            <c:out value="${carrito.total}" />

                            <spring:url value="/carrito/finalizarCompra" var="ventaUrl">
                                        </spring:url>
                                        <a class="btn btn-default" href="${fn:escapeXml(ventaUrl)}">
                                            <c:out value="Comprar" /><br></a>

                    </petclinic:layout>